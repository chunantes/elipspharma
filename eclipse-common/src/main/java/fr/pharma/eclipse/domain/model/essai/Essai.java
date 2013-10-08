package fr.pharma.eclipse.domain.model.essai;

import java.util.Calendar;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.comparator.common.EclipseListComparator;
import fr.pharma.eclipse.comparator.evenement.EvenementComparator;
import fr.pharma.eclipse.comparator.incident.IncidentComparator;
import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.common.BeanObjectSuivi;
import fr.pharma.eclipse.domain.model.common.BeanParentDocument;
import fr.pharma.eclipse.domain.model.common.BeanWithNom;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.DetailAdministratif;
import fr.pharma.eclipse.domain.model.essai.detail.autresdocs.DetailAutresDocuments;
import fr.pharma.eclipse.domain.model.essai.detail.contact.DetailContacts;
import fr.pharma.eclipse.domain.model.essai.detail.dates.DetailDates;
import fr.pharma.eclipse.domain.model.essai.detail.design.DetailDesign;
import fr.pharma.eclipse.domain.model.essai.detail.etat.DetailEtatEssai;
import fr.pharma.eclipse.domain.model.essai.detail.faisabilite.DetailFaisabilite;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.DetailDonneesPharma;
import fr.pharma.eclipse.domain.model.essai.detail.produit.DetailProduit;
import fr.pharma.eclipse.domain.model.essai.detail.recherche.DetailRecherche;
import fr.pharma.eclipse.domain.model.essai.detail.surcout.DetailSurcout;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.domain.model.incident.Incident;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.suivi.essai.EssaiSuivi;

/**
 * Classe métier représentant un Essai clinique (appelé aussi étude).
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "essai")
public class Essai extends BeanObjectSuivi implements BeanWithNom, BeanParentDocument {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 8359894786830830474L;

    /**
     * Nom usuel.
     */
    @Column(name = "nom")
    private String nom;

    /**
     * Numéro d'identification interne.
     */
    @Column(name = "numInterne")
    private String numInterne;

    /**
     * Code promoteur.
     */
    @Column(name = "codePromoteur")
    private String codePromoteur;

    /**
     * DCI.
     */
    @Column(name = "dci")
    private String dci;

    /**
     * Libellé du produit évalué.
     */
    @Column(name = "libelleProduitEvalue")
    private String libelleProduitEvalue;

    /**
     * Etat de l'essai.
     */
    @Column(name = "etat")
    @Enumerated(EnumType.STRING)
    private EtatEssai etat;

    /**
     * Liste de détails sur l'état de l'essai.
     */
    @OneToMany(mappedBy = "essai", cascade = CascadeType.ALL, orphanRemoval = true)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private SortedSet<DetailEtatEssai> detailsEtatEssai = new TreeSet<DetailEtatEssai>(new SuiviComparator());

    /**
     * Type du promoteur sur l'essai.
     */
    @Column(name = "typePromoteur")
    @Enumerated(EnumType.STRING)
    private TypePromoteur typePromoteur;

    /**
     * Emplacement physique du dossier.
     */
    @Column(name = "emplacementDossier")
    private String emplacementPhysiqueDossier;

    /**
     * Année de création dans Eclipse.
     */
    @Column(name = "anneeCreation")
    private Integer anneeCreation;

    /**
     * Promoteur.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_promoteur", nullable = false)
    @Index(name = "idx_promo_essai")
    @Fetch(FetchMode.JOIN)
    private Promoteur promoteur;

    /**
     * Pharmacie principale.
     */
    @ManyToOne
    @JoinColumn(name = "id_pharma", nullable = false)
    @Index(name = "idx_pharma_essai")
    private Pharmacie pharmaciePrincipale;

    /**
     * Alerte active sur l'essai. <br />
     * Prise en compte de l'essai dans les alertes.
     */
    @Column(name = "alerteActive")
    private Boolean alerteActive;

    /**
     * Détail : recherche.
     */
    @OneToOne(mappedBy = "essai", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private DetailRecherche detailRecherche;

    /**
     * Détail : contacts.
     */
    @OneToOne(mappedBy = "essai", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private DetailContacts detailContacts;

    /**
     * Détail : faisabilité.
     */
    @OneToOne(mappedBy = "essai", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private DetailFaisabilite detailFaisabilite;

    /**
     * Détail : dates.
     */
    @OneToOne(mappedBy = "essai", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private DetailDates detailDates;

    /**
     * Détail : administratif.
     */
    @OneToOne(mappedBy = "essai", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private DetailAdministratif detailAdministratif;

    /**
     * Détail : Produit.
     */
    @OneToOne(mappedBy = "essai", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private DetailProduit detailProduit;

    /**
     * Détail : Données pharma.
     */
    @OneToOne(mappedBy = "essai", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private DetailDonneesPharma detailDonneesPharma;

    /**
     * Détail : Design.
     */
    @OneToOne(mappedBy = "essai", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private DetailDesign detailDesign;

    /**
     * Détail : autres documents.
     */
    @OneToOne(mappedBy = "essai", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private DetailAutresDocuments detailAutresDocuments;

    /**
     * Détail : surcouts.
     */
    @OneToOne(mappedBy = "essai", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private DetailSurcout detailSurcout;

    /**
     * Services.
     */
    @ManyToMany(targetEntity = Service.class)
    @JoinTable(name = "essai_service", joinColumns = @JoinColumn(name = "id_essai"), inverseJoinColumns = @JoinColumn(name = "id_service"))
    @Sort(type = SortType.COMPARATOR, comparator = BeanWithNomComparator.class)
    private SortedSet<Service> services = new TreeSet<Service>(new BeanWithNomComparator());

    /**
     * Liste des événements de l'essai.
     */
    @OneToMany(mappedBy = "essai", cascade = CascadeType.ALL, orphanRemoval = true)
    @Sort(type = SortType.COMPARATOR, comparator = EvenementComparator.class)
    private final SortedSet<Evenement> evenements = new TreeSet<Evenement>(new EvenementComparator());

    /**
     * Liste des incidents de l'essai.
     */
    @OneToMany(mappedBy = "essai", cascade = CascadeType.ALL, orphanRemoval = true)
    @Sort(type = SortType.COMPARATOR, comparator = IncidentComparator.class)
    private SortedSet<Incident> incidents = new TreeSet<Incident>(new IncidentComparator());

    /**
     * Liste des modifications de l'essai.
     */
    @OneToMany(mappedBy = "essai", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private final SortedSet<EssaiSuivi> modifs = new TreeSet<EssaiSuivi>(new SuiviComparator());

    /**
     * Tous les mvts.
     */
    @OneToMany(mappedBy = "essai", cascade = {CascadeType.MERGE, CascadeType.REFRESH }, orphanRemoval = true)
    @Sort(type = SortType.COMPARATOR, comparator = EclipseListComparator.class)
    private final SortedSet<MvtStock> mvts = new TreeSet<MvtStock>(new EclipseListComparator());

    /**
     * Date de signature.
     */
    @Column(name = "conv_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateSignature;

    /**
     * Getter sur numInterne.
     * @return Retourne le numInterne.
     */
    public String getNumInterne() {
        return this.numInterne;
    }

    /**
     * Setter pour numInterne.
     * @param numInterne le numInterne à écrire.
     */
    public void setNumInterne(final String numInterne) {
        this.numInterne = numInterne;
    }

    /**
     * Getter sur nom.
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
     * Getter sur dci.
     * @return Retourne le dci.
     */
    public String getDci() {
        return this.dci;
    }

    /**
     * Setter pour dci.
     * @param dci le dci à écrire.
     */
    public void setDci(final String dci) {
        this.dci = dci;
    }

    /**
     * Getter sur promoteur.
     * @return Retourne le promoteur.
     */
    public Promoteur getPromoteur() {
        return this.promoteur;
    }

    /**
     * Setter pour promoteur.
     * @param promoteur le promoteur à écrire.
     */
    public void setPromoteur(final Promoteur promoteur) {
        this.promoteur = promoteur;
    }

    /**
     * Getter sur etat.
     * @return Retourne le etat.
     */
    public EtatEssai getEtat() {
        return this.etat;
    }

    /**
     * Setter pour etat.
     * @param etat le etat à écrire.
     */
    public void setEtat(final EtatEssai etat) {
        this.etat = etat;
    }

    /**
     * Getter sur modifs.
     * @return Retourne le modifs.
     */
    @Override
    public SortedSet<EssaiSuivi> getModifs() {
        return this.modifs;
    }

    /**
     * Getter sur codePromoteur.
     * @return Retourne le codePromoteur.
     */
    public String getCodePromoteur() {
        return this.codePromoteur;
    }

    /**
     * Setter pour codePromoteur.
     * @param codePromoteur le codePromoteur à écrire.
     */
    public void setCodePromoteur(final String codePromoteur) {
        this.codePromoteur = codePromoteur;
    }

    /**
     * Getter sur typePromoteur.
     * @return Retourne le typePromoteur.
     */
    public TypePromoteur getTypePromoteur() {
        return this.typePromoteur;
    }

    /**
     * Setter pour typePromoteur.
     * @param typePromoteur le typePromoteur à écrire.
     */
    public void setTypePromoteur(final TypePromoteur typePromoteur) {
        this.typePromoteur = typePromoteur;
    }

    /**
     * Getter sur emplacementPhysiqueDossier.
     * @return Retourne le emplacementPhysiqueDossier.
     */
    public String getEmplacementPhysiqueDossier() {
        return this.emplacementPhysiqueDossier;
    }

    /**
     * Setter pour emplacementPhysiqueDossier.
     * @param emplacementPhysiqueDossier le emplacementPhysiqueDossier à écrire.
     */
    public void setEmplacementPhysiqueDossier(final String emplacementPhysiqueDossier) {
        this.emplacementPhysiqueDossier = emplacementPhysiqueDossier;
    }

    /**
     * Getter sur detailRecherche.
     * @return Retourne le detailRecherche.
     */
    public DetailRecherche getDetailRecherche() {
        return this.detailRecherche;
    }

    /**
     * Setter pour detailRecherche.
     * @param detailRecherche le detailRecherche à écrire.
     */
    public void setDetailRecherche(final DetailRecherche detailRecherche) {
        this.detailRecherche = detailRecherche;
    }

    /**
     * Getter sur libelleProduitEvalue.
     * @return Retourne le libelleProduitEvalue.
     */
    public String getLibelleProduitEvalue() {
        return this.libelleProduitEvalue;
    }

    /**
     * Setter pour libelleProduitEvalue.
     * @param libelleProduitEvalue le libelleProduitEvalue à écrire.
     */
    public void setLibelleProduitEvalue(final String libelleProduitEvalue) {
        this.libelleProduitEvalue = libelleProduitEvalue;
    }

    /**
     * Getter sur services.
     * @return Retourne le services.
     */
    public SortedSet<Service> getServices() {
        return this.services;
    }

    /**
     * Setter pour services.
     * @param services le services à écrire.
     */
    public void setServices(final SortedSet<Service> services) {
        this.services = services;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("Essai #");
        builder.append(this.getId());
        builder.append("[nom: ").append(this.nom);
        builder.append(", selected: ").append(this.getSelected());
        return builder.append("]").toString();
    }

    /**
     * Getter sur detailDates.
     * @return Retourne le detailDates.
     */
    public DetailDates getDetailDates() {
        return this.detailDates;
    }

    /**
     * Setter pour detailDates.
     * @param detailDates le detailDates à écrire.
     */
    public void setDetailDates(final DetailDates detailDates) {
        this.detailDates = detailDates;
    }

    /**
     * Getter sur detailFaisabilite.
     * @return Retourne le detailFaisabilite.
     */
    public DetailFaisabilite getDetailFaisabilite() {
        return this.detailFaisabilite;
    }

    /**
     * Setter pour detailFaisabilite.
     * @param detailFaisabilite le detailFaisabilite à écrire.
     */
    public void setDetailFaisabilite(final DetailFaisabilite detailFaisabilite) {
        this.detailFaisabilite = detailFaisabilite;
    }

    /**
     * Getter sur anneeCreation.
     * @return Retourne le anneeCreation.
     */
    public Integer getAnneeCreation() {
        return this.anneeCreation;
    }

    /**
     * Setter pour anneeCreation.
     * @param anneeCreation le anneeCreation à écrire.
     */
    public void setAnneeCreation(final Integer anneeCreation) {
        this.anneeCreation = anneeCreation;
    }

    /**
     * Getter sur detailAdministratif.
     * @return Retourne le detailAdministratif.
     */
    public DetailAdministratif getDetailAdministratif() {
        return this.detailAdministratif;
    }

    /**
     * Setter pour detailAdministratif.
     * @param detailAdministratif le detailAdministratif à écrire.
     */
    public void setDetailAdministratif(final DetailAdministratif detailAdministratif) {
        this.detailAdministratif = detailAdministratif;
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
     * Getter sur pharmaciePrincipale.
     * @return Retourne le pharmaciePrincipale.
     */
    public Pharmacie getPharmaciePrincipale() {
        return this.pharmaciePrincipale;
    }

    /**
     * Setter pour pharmaciePrincipale.
     * @param pharmaciePrincipale le pharmaciePrincipale à écrire.
     */
    public void setPharmaciePrincipale(final Pharmacie pharmaciePrincipale) {
        this.pharmaciePrincipale = pharmaciePrincipale;
    }

    /**
     * Getter sur detailContacts.
     * @return Retourne le detailContacts.
     */
    public DetailContacts getDetailContacts() {
        return this.detailContacts;
    }

    /**
     * Setter pour detailContacts.
     * @param detailContacts le detailContacts à écrire.
     */
    public void setDetailContacts(final DetailContacts detailContacts) {
        this.detailContacts = detailContacts;
    }

    /**
     * Getter sur detailDonneesPharma.
     * @return Retourne le detailDonneesPharma.
     */
    public DetailDonneesPharma getDetailDonneesPharma() {
        return this.detailDonneesPharma;
    }

    /**
     * Setter pour detailDonneesPharma.
     * @param detailDonneesPharma le detailDonneesPharma à écrire.
     */
    public void setDetailDonneesPharma(final DetailDonneesPharma detailDonneesPharma) {
        this.detailDonneesPharma = detailDonneesPharma;
    }

    /**
     * Getter sur detailDesign.
     * @return Retourne le detailDesign.
     */
    public DetailDesign getDetailDesign() {
        return this.detailDesign;
    }

    /**
     * Setter pour detailDesign.
     * @param detailDesign le detailDesign à écrire.
     */
    public void setDetailDesign(final DetailDesign detailDesign) {
        this.detailDesign = detailDesign;
    }

    /**
     * Setter pour detailAutresDocuments.
     * @param detailAutresDocuments le detailAutresDocuments à écrire.
     */
    public void setDetailAutresDocuments(final DetailAutresDocuments detailAutresDocuments) {
        this.detailAutresDocuments = detailAutresDocuments;
    }

    /**
     * Getter sur detailAutresDocuments.
     * @return Retourne le detailAutresDocuments.
     */
    public DetailAutresDocuments getDetailAutresDocuments() {
        return this.detailAutresDocuments;
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

    /**
     * Getter pour evenements.
     * @return Le evenements
     */
    public SortedSet<Evenement> getEvenements() {
        return this.evenements;
    }

    /**
     * Getter pour detailsEtatEssai.
     * @return Le detailsEtatEssai
     */
    public SortedSet<DetailEtatEssai> getDetailsEtatEssai() {
        return this.detailsEtatEssai;
    }

    /**
     * Setter pour detailsEtatEssai.
     * @param detailsEtatEssai Le detailsEtatEssai à écrire.
     */
    public void setDetailsEtatEssai(final SortedSet<DetailEtatEssai> detailsEtatEssai) {
        this.detailsEtatEssai = detailsEtatEssai;
    }

    /**
     * Getter sur detailSurcout.
     * @return Retourne le detailSurcout.
     */
    public DetailSurcout getDetailSurcout() {
        return this.detailSurcout;
    }

    /**
     * Setter pour detailSurcout.
     * @param detailSurcout le detailSurcout à écrire.
     */
    public void setDetailSurcout(final DetailSurcout detailSurcout) {
        this.detailSurcout = detailSurcout;
    }

    /**
     * Getter sur incidents.
     * @return Retourne le incidents.
     */
    public SortedSet<Incident> getIncidents() {
        return this.incidents;
    }

    /**
     * Setter pour incidents.
     * @param incidents le incidents à écrire.
     */
    public void setIncidents(final SortedSet<Incident> incidents) {
        this.incidents = incidents;
    }

    /**
     * Getter pour mvts.
     * @return Le mvts
     */
    public SortedSet<MvtStock> getMvts() {
        return this.mvts;
    }

    /**
     * Getter pour dateSignature.
     * @return Le dateSignature
     */
    public Calendar getDateSignature() {
        return this.dateSignature;
    }

    /**
     * Setter pour dateSignature.
     * @param dateSignature Le dateSignature à écrire.
     */
    public void setDateSignature(final Calendar dateSignature) {
        this.dateSignature = dateSignature;
    }

}
