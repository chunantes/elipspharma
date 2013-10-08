package fr.pharma.eclipse.domain.model.stockage;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.hibernate.validator.constraints.NotEmpty;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.comparator.common.EclipseListComparator;
import fr.pharma.eclipse.comparator.stockage.StockageComparator;
import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.common.BeanObjectSuivi;
import fr.pharma.eclipse.domain.model.common.BeanWithNom;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.DetailDonneesPharma;
import fr.pharma.eclipse.domain.model.localisation.Etablissement;
import fr.pharma.eclipse.domain.model.localisation.Site;
import fr.pharma.eclipse.domain.model.suivi.stockage.PharmacieSuivi;

/**
 * Classe métier représentant une Pharmacie physique (lieu de stockage).
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "pharmacie")
public class Pharmacie extends BeanObjectSuivi implements BeanWithNom {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2112496734371591396L;

    /**
     * Nom.
     */
    @Column(name = "nom")
    @NotNull
    @NotEmpty
    private String nom;

    /**
     * Adresse.
     */
    @Column(name = "adresse")
    private String adresse;

    /**
     * Adresse de livraison.
     */
    @Column(name = "adresseLivraison")
    private String adresseLivraison;

    /**
     * Téléphone.
     */
    @Column(name = "telephone")
    private String telephone;

    /**
     * Fax.
     */
    @Column(name = "fax")
    private String fax;

    /**
     * Responsable principal.
     */
    @Column(name = "responsablePrincipal")
    private String responsablePrincipal;

    /**
     * Numéro courant de l'ordonnancier de dispensation.
     */
    @Column(name = "numOrdonnancierDisp")
    @NotNull
    private Integer numOrdonnancierDisp;

    /**
     * Numéro courant de l'ordonnancier de fabrication / reconstitution.
     */
    @Column(name = "numOrdonnancierFab")
    @NotNull
    private Integer numOrdonnancierFab;

    /**
     * Etablissement.
     */
    @ManyToOne
    @JoinColumn(name = "id_etablissement", nullable = false)
    @Index(name = "idx_etab_pharma")
    @NotNull
    private Etablissement etablissement;

    /**
     * Details données pharma.
     */
    @ManyToMany(targetEntity = DetailDonneesPharma.class, mappedBy = "pharmacies", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = EclipseListComparator.class)
    private SortedSet<DetailDonneesPharma> detailsDonneesPharma = new TreeSet<DetailDonneesPharma>(new EclipseListComparator());

    /**
     * Sites.
     */
    @ManyToMany(targetEntity = Site.class)
    @JoinTable(name = "pharmacie_site", joinColumns = @JoinColumn(name = "id_pharmacie"), inverseJoinColumns = @JoinColumn(name = "id_site"))
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = BeanWithNomComparator.class)
    private SortedSet<Site> sites = new TreeSet<Site>(new BeanWithNomComparator());

    /**
     * Pharmaciens.
     */
    @ManyToMany(targetEntity = Pharmacien.class, mappedBy = "pharmacies", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = BeanWithNomComparator.class)
    private SortedSet<Pharmacien> pharmaciens = new TreeSet<Pharmacien>(new BeanWithNomComparator());

    /**
     * Liste des stockages de la pharmacie.
     */
    @OneToMany(mappedBy = "pharmacie", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = StockageComparator.class)
    private SortedSet<Stockage> stockages = new TreeSet<Stockage>(new StockageComparator());

    /**
     * Liste des modifications de la pharmacie.
     */
    @OneToMany(mappedBy = "pharmacie", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private final SortedSet<PharmacieSuivi> modifs = new TreeSet<PharmacieSuivi>(new SuiviComparator());

    /**
     * Getter pour nom.
     * @return Retourne le nom.
     */
    @Override
    public String getNom() {
        return this.nom;
    }

    /**
     * Setter pour nom.
     * @param nom le nom à écrire.
     */
    public void setNom(final String nom) {
        this.nom = nom;
    }

    /**
     * Getter sur modifs.
     * @return Retourne le modifs.
     */
    @Override
    public SortedSet<PharmacieSuivi> getModifs() {
        return this.modifs;
    }

    /**
     * Getter sur adresse.
     * @return Retourne le adresse.
     */
    public String getAdresse() {
        return this.adresse;
    }

    /**
     * Setter pour adresse.
     * @param adresse le adresse à écrire.
     */
    public void setAdresse(final String adresse) {
        this.adresse = adresse;
    }

    /**
     * Getter sur adresseLivraison.
     * @return Retourne le adresseLivraison.
     */
    public String getAdresseLivraison() {
        return this.adresseLivraison;
    }

    /**
     * Setter pour adresseLivraison.
     * @param adresseLivraison le adresseLivraison à écrire.
     */
    public void setAdresseLivraison(final String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    /**
     * Getter sur etablissement.
     * @return Retourne le etablissement.
     */
    public Etablissement getEtablissement() {
        return this.etablissement;
    }

    /**
     * Setter pour etablissement.
     * @param etablissement le etablissement à écrire.
     */
    public void setEtablissement(final Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    /**
     * Getter sur detailsDonneesPharma.
     * @return Retourne le detailsDonneesPharma.
     */
    public SortedSet<DetailDonneesPharma> getDetailsDonneesPharma() {
        return this.detailsDonneesPharma;
    }

    /**
     * Setter pour detailsDonneesPharma.
     * @param detailsDonneesPharma le detailsDonneesPharma à écrire.
     */
    public void setDetailsDonneesPharma(final SortedSet<DetailDonneesPharma> detailsDonneesPharma) {
        this.detailsDonneesPharma = detailsDonneesPharma;
    }

    /**
     * Getter sur sites.
     * @return Retourne le sites.
     */
    public SortedSet<Site> getSites() {
        return this.sites;
    }

    /**
     * Setter pour sites.
     * @param sites le sites à écrire.
     */
    public void setSites(final SortedSet<Site> sites) {
        this.sites = sites;
    }

    /**
     * Getter pour pharmaciens.
     * @return Le pharmaciens
     */
    public SortedSet<Pharmacien> getPharmaciens() {
        return this.pharmaciens;
    }

    /**
     * Setter pour pharmaciens.
     * @param pharmaciens Le pharmaciens à écrire.
     */
    public void setPharmaciens(final SortedSet<Pharmacien> pharmaciens) {
        this.pharmaciens = pharmaciens;
    }

    /**
     * Getter pour stockages.
     * @return Le stockages
     */
    public SortedSet<Stockage> getStockages() {
        return this.stockages;
    }

    /**
     * Setter pour stockages.
     * @param stockages Le stockages à écrire.
     */
    public void setStockages(final SortedSet<Stockage> stockages) {
        this.stockages = stockages;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("[");
        builder.append("id: ").append(this.getId());
        builder.append(", nom: ").append(this.nom);
        builder.append(", selected: ").append(this.getSelected());
        return builder.append("]").toString();
    }

    /**
     * Getter pour telephone.
     * @return Le telephone
     */
    public String getTelephone() {
        return this.telephone;
    }

    /**
     * Setter pour telephone.
     * @param telephone Le telephone à écrire.
     */
    public void setTelephone(final String telephone) {
        this.telephone = telephone;
    }

    /**
     * Getter pour fax.
     * @return Le fax
     */
    public String getFax() {
        return this.fax;
    }

    /**
     * Setter pour fax.
     * @param fax Le fax à écrire.
     */
    public void setFax(final String fax) {
        this.fax = fax;
    }

    /**
     * Getter pour responsablePrincipal.
     * @return Le responsablePrincipal
     */
    public String getResponsablePrincipal() {
        return this.responsablePrincipal;
    }

    /**
     * Setter pour responsablePrincipal.
     * @param responsablePrincipal Le responsablePrincipal à écrire.
     */
    public void setResponsablePrincipal(final String responsablePrincipal) {
        this.responsablePrincipal = responsablePrincipal;
    }

    /**
     * Getter pour numOrdonnancierDisp.
     * @return Le numOrdonnancierDisp
     */
    public Integer getNumOrdonnancierDisp() {
        return this.numOrdonnancierDisp;
    }

    /**
     * Setter pour numOrdonnancierDisp.
     * @param numOrdonnancierDisp Le numOrdonnancierDisp à écrire.
     */
    public void setNumOrdonnancierDisp(final Integer numOrdonnancierDisp) {
        this.numOrdonnancierDisp = numOrdonnancierDisp;
    }

    /**
     * Getter pour numOrdonnancierFab.
     * @return Le numOrdonnancierFab
     */
    public Integer getNumOrdonnancierFab() {
        return this.numOrdonnancierFab;
    }

    /**
     * Setter pour numOrdonnancierFab.
     * @param numOrdonnancierFab Le numOrdonnancierFab à écrire.
     */
    public void setNumOrdonnancierFab(final Integer numOrdonnancierFab) {
        this.numOrdonnancierFab = numOrdonnancierFab;
    }
}
