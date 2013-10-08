package fr.pharma.eclipse.domain.model.produit.detail;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.hibernate.annotations.Where;

import fr.pharma.eclipse.comparator.produit.detail.DetailStockageComparator;
import fr.pharma.eclipse.domain.enums.ConditionConservation;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.common.Clonable;
import fr.pharma.eclipse.domain.model.produit.Produit;

/**
 * Classe métier représentant les informations de détail liés à la logistique
 * d'un produit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "produit_detail_logistique")
public class DetailLogistique extends BeanObject implements Clonable<DetailLogistique> {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 2834238547393178642L;

    /**
     * Produit auquel est rattaché le détail.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produit")
    private Produit produit;

    /**
     * Condition de conservation.
     */
    @Column(name = "conditionConservation")
    @Enumerated(EnumType.STRING)
    private ConditionConservation conditionConservation;

    /**
     * Mist à disposition par le promoteur.
     */
    @Column(name = "miseADispo")
    private Boolean misADisposition;

    /**
     * Quantité autorisée en dotation.
     */
    @Column(name = "quantiteAutorise")
    private Integer quantiteAutorise;

    /**
     * Stock seuil.
     */
    @Column(name = "stockSeuil")
    private Integer stockSeuil;

    /**
     * Délai d'alerte avant la date d'expiration (en j).
     */
    @Column(name = "delaiAlerteAvtDateExpiration")
    private Integer delaiAlerteAvtDateExpiration;

    /**
     * Comptabilité des traitements retournés.
     */
    @Column(name = "comptabiliteRetour")
    private Boolean comptabiliteRetour;

    /**
     * Abri de la lumière.
     */
    @Column(name = "abriLumiere")
    private Boolean abriLumiere;

    /**
     * Produit fourni par la PUI.
     */
    @Column(name = "produitNonFourni")
    private Boolean produitNonFourni;

    /**
     * Liste des détails de stockage de produit.
     */
    @OneToMany(mappedBy = "detailLogistique", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Where(clause = "type='STOCK'")
    @Sort(type = SortType.COMPARATOR, comparator = DetailStockageComparator.class)
    private SortedSet<DetailStockage> detailsStockages = new TreeSet<DetailStockage>(new DetailStockageComparator());

    /**
     * Liste des stockages retours.
     */
    @OneToMany(mappedBy = "detailLogistique", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Where(clause = "type='RETOUR'")
    @Sort(type = SortType.COMPARATOR, comparator = DetailStockageComparator.class)
    private SortedSet<DetailStockage> stockagesRetours = new TreeSet<DetailStockage>(new DetailStockageComparator());

    /**
     * {@inheritDoc}
     */
    @Override
    public DetailLogistique cloneMe() {
        final DetailLogistique detail = new DetailLogistique();
        detail.setAbriLumiere(this.getAbriLumiere());
        detail.setComptabiliteRetour(this.getComptabiliteRetour());
        detail.setConditionConservation(this.getConditionConservation());
        detail.setMisADisposition(this.getMisADisposition());
        detail.setQuantiteAutorise(this.getQuantiteAutorise());
        detail.setStockSeuil(this.getStockSeuil());
        return detail;
    }

    /**
     * Getter sur produit.
     * @return Retourne le produit.
     */
    public Produit getProduit() {
        return this.produit;
    }

    /**
     * Setter pour produit.
     * @param produit le produit à écrire.
     */
    public void setProduit(final Produit produit) {
        this.produit = produit;
    }

    /**
     * Getter sur conditionConservation.
     * @return Retourne le conditionConservation.
     */
    public ConditionConservation getConditionConservation() {
        return this.conditionConservation;
    }

    /**
     * Setter pour conditionConservation.
     * @param conditionConservation le conditionConservation à écrire.
     */
    public void setConditionConservation(final ConditionConservation conditionConservation) {
        this.conditionConservation = conditionConservation;
    }

    /**
     * Getter sur misADisposition.
     * @return Retourne le misADisposition.
     */
    public Boolean getMisADisposition() {
        return this.misADisposition;
    }

    /**
     * Setter pour misADisposition.
     * @param misADisposition le misADisposition à écrire.
     */
    public void setMisADisposition(final Boolean misADisposition) {
        this.misADisposition = misADisposition;
    }

    /**
     * Getter sur quantiteAutorise.
     * @return Retourne le quantiteAutorise.
     */
    public Integer getQuantiteAutorise() {
        return this.quantiteAutorise;
    }

    /**
     * Setter pour quantiteAutorise.
     * @param quantiteAutorise le quantiteAutorise à écrire.
     */
    public void setQuantiteAutorise(final Integer quantiteAutorise) {
        this.quantiteAutorise = quantiteAutorise;
    }

    /**
     * Getter sur stockSeuil.
     * @return Retourne le stockSeuil.
     */
    public Integer getStockSeuil() {
        return this.stockSeuil;
    }

    /**
     * Setter pour stockSeuil.
     * @param stockSeuil le stockSeuil à écrire.
     */
    public void setStockSeuil(final Integer stockSeuil) {
        this.stockSeuil = stockSeuil;
    }

    /**
     * Getter sur comptabiliteRetour.
     * @return Retourne le comptabiliteRetour.
     */
    public Boolean getComptabiliteRetour() {
        return this.comptabiliteRetour;
    }

    /**
     * Setter pour comptabiliteRetour.
     * @param comptabiliteRetour le comptabiliteRetour à écrire.
     */
    public void setComptabiliteRetour(final Boolean comptabiliteRetour) {
        this.comptabiliteRetour = comptabiliteRetour;
    }

    /**
     * Getter sur abriLumiere.
     * @return Retourne le abriLumiere.
     */
    public Boolean getAbriLumiere() {
        return this.abriLumiere;
    }

    /**
     * Setter pour abriLumiere.
     * @param abriLumiere le abriLumiere à écrire.
     */
    public void setAbriLumiere(final Boolean abriLumiere) {
        this.abriLumiere = abriLumiere;
    }

    /**
     * Getter sur stockages.
     * @return Retourne le stockages.
     */
    public SortedSet<DetailStockage> getDetailsStockages() {
        return this.detailsStockages;
    }

    /**
     * Getter pour les references dans le xhtml
     * @return Retourne le stockages.
     */
    @Deprecated
    public SortedSet<DetailStockage> getStockages() {
        return this.detailsStockages;
    }

    /**
     * Getter sur stockagesRetours.
     * @return Retourne le stockagesRetours.
     */
    public SortedSet<DetailStockage> getStockagesRetours() {
        return this.stockagesRetours;
    }

    /**
     * Setter pour stockages.
     * @param stockages le stockages à écrire.
     */
    public void setDetailsStockages(final SortedSet<DetailStockage> stockages) {
        this.detailsStockages = stockages;
    }

    /**
     * Getter sur produitNonFourni.
     * @return Retourne le produitNonFourni.
     */
    public Boolean getProduitNonFourni() {
        return this.produitNonFourni;
    }

    /**
     * Setter pour produitNonFourni.
     * @param produitNonFourni le produitNonFourni à écrire.
     */
    public void setProduitNonFourni(final Boolean produitNonFourni) {
        this.produitNonFourni = produitNonFourni;
    }

    /**
     * Setter pour stockagesRetours.
     * @param stockagesRetours le stockagesRetours à écrire.
     */
    public void setStockagesRetours(final SortedSet<DetailStockage> stockagesRetours) {
        this.stockagesRetours = stockagesRetours;
    }

    /**
     * Getter pour delaiAlerteAvtDateExpiration.
     * @return Le delaiAlerteAvtDateExpiration
     */
    public Integer getDelaiAlerteAvtDateExpiration() {
        return this.delaiAlerteAvtDateExpiration;
    }

    /**
     * Setter pour delaiAlerteAvtDateExpiration.
     * @param delaiAlerteAvtDateExpiration Le delaiAlerteAvtDateExpiration à
     * écrire.
     */
    public void setDelaiAlerteAvtDateExpiration(final Integer delaiAlerteAvtDateExpiration) {
        this.delaiAlerteAvtDateExpiration = delaiAlerteAvtDateExpiration;
    }

}
