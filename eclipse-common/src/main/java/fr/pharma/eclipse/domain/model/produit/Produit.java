package fr.pharma.eclipse.domain.model.produit;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.hibernate.validator.constraints.NotEmpty;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.comparator.produit.detail.ConditionnementComparator;
import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.enums.produit.TypeProduit;
import fr.pharma.eclipse.domain.model.common.BeanObjectSuivi;
import fr.pharma.eclipse.domain.model.common.BeanParentDocument;
import fr.pharma.eclipse.domain.model.common.BeanWithNom;
import fr.pharma.eclipse.domain.model.common.Clonable;
import fr.pharma.eclipse.domain.model.essai.detail.produit.DetailProduit;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.produit.detail.DetailLogistique;
import fr.pharma.eclipse.domain.model.suivi.produit.ProduitSuivi;

/**
 * Classe métier représentant un Produit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "produit")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Produit extends BeanObjectSuivi implements BeanWithNom, Clonable<Produit>, BeanParentDocument {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 4463138564255484144L;

    /**
     * Dénomination du produit.
     */
    @Column(name = "denomination")
    @NotEmpty
    @NotNull
    private String denomination;

    /**
     * Code du produit.
     */
    @Column(name = "code")
    private String code;

    /**
     * Conseils pharmaceutiques.
     */
    @Column(name = "conseils", columnDefinition = "TEXT")
    private String conseils;

    /**
     * Classe thérapeutique.
     */
    @Column(name = "classeTherapeutique")
    private String classeTherapeutique;

    /**
     * Type de produit.
     */
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeProduit type;

    /**
     * Imputation à une UF.
     */
    @Column(name = "imputationUf")
    private Boolean imputationUf;

    /**
     * Alerte active sur le produit. <br />
     * Prise en compte du produit dans les alertes.
     */
    @Column(name = "alerteActive")
    @NotNull
    private Boolean alerteActive;

    /**
     * Services.
     */
    @ManyToMany(targetEntity = Service.class, cascade = CascadeType.REFRESH)
    @JoinTable(name = "produit_service", joinColumns = @JoinColumn(name = "id_produit"), inverseJoinColumns = @JoinColumn(name = "id_service"))
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = BeanWithNomComparator.class)
    private final SortedSet<Service> services = new TreeSet<Service>(new BeanWithNomComparator());

    /**
     * Détail : Logistique.
     */
    @OneToOne(mappedBy = "produit", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private DetailLogistique detailLogistique;

    /**
     * Objet auquel est rattaché le produit.
     */
    @ManyToOne()
    @JoinColumn(name = "id_detailProduit", nullable = false)
    @Index(name = "idx_essai_produit_detail_produit")
    private DetailProduit detailProduit;

    /**
     * Liste des Conditionnements.
     */
    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = ConditionnementComparator.class)
    private SortedSet<Conditionnement> conditionnements = new TreeSet<Conditionnement>(new ConditionnementComparator());

    /**
     * Liste des modifications de l'essai.
     */
    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private SortedSet<ProduitSuivi> modifs = new TreeSet<ProduitSuivi>(new SuiviComparator());

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Produit cloneMe();

    /**
     * Méthode en charge de populer les données de la super classe Produit sur
     * l'appel de ses enfants.
     * @param p Le produit à populer.
     */
    protected void cloneMe(final Produit p) {
        p.setClasseTherapeutique(this.classeTherapeutique);
        p.setCode(this.code);
        p.setConseils(this.conseils);
        p.setDenomination(this.denomination);
        p.setDetailProduit(this.getDetailProduit());
        p.setType(this.type);
        p.setAlerteActive(this.getAlerteActive());
        p.setDetailLogistique(this.detailLogistique.cloneMe());
        p.getDetailLogistique().setProduit(p);
    }
    /**
     * Getter sur denomination.
     * @return Retourne le denomination.
     */
    public String getDenomination() {
        return this.denomination;
    }

    /**
     * Setter pour modifs.
     * @param modifs le modifs à écrire.
     */
    public void setModifs(final SortedSet<ProduitSuivi> modifs) {
        this.modifs = modifs;
    }

    /**
     * Setter pour denomination.
     * @param denomination le denomination à écrire.
     */
    public void setDenomination(final String denomination) {
        this.denomination = denomination;
    }

    /**
     * Getter sur code.
     * @return Retourne le code.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Setter pour code.
     * @param code le code à écrire.
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * Getter sur classeTherapeutique.
     * @return Retourne le classeTherapeutique.
     */
    public String getClasseTherapeutique() {
        return this.classeTherapeutique;
    }

    /**
     * Setter pour classeTherapeutique.
     * @param classeTherapeutique le classeTherapeutique à écrire.
     */
    public void setClasseTherapeutique(final String classeTherapeutique) {
        this.classeTherapeutique = classeTherapeutique;
    }

    /**
     * Getter sur type.
     * @return Retourne le type.
     */
    public TypeProduit getType() {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type le type à écrire.
     */
    public void setType(final TypeProduit type) {
        this.type = type;
    }

    /**
     * Getter sur modifs.
     * @return Retourne le modifs.
     */
    @Override
    public SortedSet<ProduitSuivi> getModifs() {
        return this.modifs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNom() {
        return this.denomination;
    }

    /**
     * Getter sur detailProduit.
     * @return Retourne le detailProduit.
     */
    public DetailProduit getDetailProduit() {
        return this.detailProduit;
    }

    /**
     * Setter pour detailProduit.
     * @param detailProduit le detailProduit à écrire.
     */
    public void setDetailProduit(final DetailProduit detailProduit) {
        this.detailProduit = detailProduit;
    }

    /**
     * Getter sur conseils.
     * @return Retourne le conseils.
     */
    public String getConseils() {
        return this.conseils;
    }

    /**
     * Setter pour conseils.
     * @param conseils le conseils à écrire.
     */
    public void setConseils(final String conseils) {
        this.conseils = conseils;
    }

    /**
     * Getter sur imputationUf.
     * @return Retourne le imputationUf.
     */
    public Boolean getImputationUf() {
        if (this.imputationUf == null) {
            return false;
        }
        return this.imputationUf;
    }

    /**
     * Setter pour imputationUf.
     * @param imputationUf le imputationUf à écrire.
     */
    public void setImputationUf(final Boolean imputationUf) {
        this.imputationUf = imputationUf;
    }

    /**
     * Getter sur services.
     * @return Retourne le services.
     */
    public SortedSet<Service> getServices() {
        return this.services;
    }

    /**
     * Getter sur detailLogistique.
     * @return Retourne le detailLogistique.
     */
    public DetailLogistique getDetailLogistique() {
        return this.detailLogistique;
    }

    /**
     * Setter pour detailLogistique.
     * @param detailLogistique le detailLogistique à écrire.
     */
    public void setDetailLogistique(final DetailLogistique detailLogistique) {
        this.detailLogistique = detailLogistique;
    }

    /**
     * Getter sur conditionnements.
     * @return Retourne le conditionnements.
     */
    public SortedSet<Conditionnement> getConditionnements() {
        return this.conditionnements;
    }

    /**
     * Setter pour conditionnements.
     * @param conditionnements le conditionnements à écrire.
     */
    public void setConditionnements(final SortedSet<Conditionnement> conditionnements) {
        this.conditionnements = conditionnements;
    }

    /**
     * Getter pour alerteActive.
     * @return Le alerteActive
     */
    public Boolean getAlerteActive() {
        return this.alerteActive;
    }

    /**
     * Setter pour alerteActive.
     * @param alerteActive Le alerteActive à écrire.
     */
    public void setAlerteActive(final Boolean alerteActive) {
        this.alerteActive = alerteActive;
    }

    @Override
    public String toString() {
        return this.denomination + ":" + this.code + ":" + this.type;
    }
}
