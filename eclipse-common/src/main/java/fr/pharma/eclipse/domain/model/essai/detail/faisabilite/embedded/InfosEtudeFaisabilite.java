package fr.pharma.eclipse.domain.model.essai.detail.faisabilite.embedded;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.hibernate.annotations.Where;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.model.essai.detail.faisabilite.CommentaireEssaiFaisabilite;
import fr.pharma.eclipse.domain.model.localisation.Service;

/**
 * Informations, de la partie détail de faisabilité de l'essai, relatives à l'étude de
 * faisabilité.
 
 * @version $Revision$ $Date$
 */
public class InfosEtudeFaisabilite
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3416607862284498735L;

    /**
     * Prestations particulières par la pharmacie.
     */
    @Column(name = "etude_prestaPharma")
    private Boolean prestaParticulieresPharmacie;

    /* ******************************** */
    /* <ACHATS DE PRODUITS PAR LA PUI> */
    /* ******************************** */
    /**
     * Achats de produits par la PUI.
     */
    @Column(name = "etude_achatsPUI")
    private Boolean achatsProduitsPUI;

    /**
     * Référencement des produits nécessaires au CHU.
     */
    @Column(name = "etude_referencement")
    private Boolean refProduitsCHU;

    /**
     * Accord pharmacie centrale.
     */
    @Column(name = "etude_accordPharmaCentrale")
    private Boolean accordPharmaCentrale;

    /**
     * Services d'imputation.
     */
    @ManyToMany(targetEntity = Service.class)
    @JoinTable(name = "essai_detail_faisabilite_service", joinColumns = @JoinColumn(name = "id_essai"), inverseJoinColumns = @JoinColumn(name = "id_service"))
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = BeanWithNomComparator.class)
    private SortedSet<Service> servicesImputation =
        new TreeSet<Service>(new BeanWithNomComparator());

    /**
     * Commentaires sur la partie achats de produits par la PUI.
     */
    @OneToMany(mappedBy = "detailFaisabilite", cascade = CascadeType.ALL, orphanRemoval = true)
    @Where(clause = "type='FAISABILITE_ACHAT_PROD'")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private SortedSet<CommentaireEssaiFaisabilite> commentairesAchatsPUI =
        new TreeSet<CommentaireEssaiFaisabilite>(new SuiviComparator());

    /* ******************************** */
    /* </ACHATS DE PRODUITS PAR LA PUI> */
    /* ******************************** */

    /**
     * Demande d'importation à effectuer.
     */
    @Column(name = "etude_dmdImportation")
    private Boolean demandeImportation;

    /* ******************************** */
    /* <RANDOMISATION> */
    /* ******************************** */

    /**
     * Randomisation des patiens par la pharmacie.
     */
    @Column(name = "etude_randomPharma")
    private Boolean randomisationParPharma;

    /**
     * Randomisation possible en garde.
     */
    @Column(name = "etude_randomEnGarde")
    private Boolean randomisationPossEnGarde;

    /* ******************************** */
    /* </RANDOMISATION> */
    /* ******************************** */

    /**
     * Gestion de l'aveugle par la PUI.
     */
    @Column(name = "etude_gestionAveugle")
    private Boolean gestionAveugle;

    /**
     * Dispendation possible en garde.
     */
    @Column(name = "etude_dispPossible")
    private Boolean dispensationPossEnGarde;

    /* ******************************** */
    /* <DISTRIBUTION AUTRES PHARMA> */
    /* ******************************** */

    /**
     * Distribution possible à d'autres pharmacies.
     */
    @Column(name = "etude_distribAutresPharmas")
    private Boolean distribAutresPharmaPossible;

    /**
     * Circuit de distribution défini.
     */
    @Column(name = "etude_circuitDef")
    private Boolean circuitDistribDefini;

    /**
     * Société de transport définie.
     */
    @Column(name = "etude_socTranspDef")
    private Boolean socTransportDefinie;

    /**
     * Suivi de température nécessaire pendant le transport.
     */
    @Column(name = "etude_suiviTemp")
    private Boolean suiviTempNecessairePdtTransp;

    /**
     * Suivi des stocks de chaque centre effectué par la pharmacie.
     */
    @Column(name = "etude_suiviStocks")
    private Boolean suiviStocksParPharmacie;

    /**
     * Commentaires sur la partie distribution aux autres pharmacies.
     */
    @OneToMany(mappedBy = "detailFaisabilite", cascade = CascadeType.ALL, orphanRemoval = true)
    @Where(clause = "type='FAISABILITE_DISTRIB_PHARMA'")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private SortedSet<CommentaireEssaiFaisabilite> commentairesDistribAutresPharma =
        new TreeSet<CommentaireEssaiFaisabilite>(new SuiviComparator());

    /* ******************************** */
    /* </DISTRIBUTION AUTRES PHARMA> */
    /* ******************************** */

    /**
     * Réalisation de reconstitutions.
     */
    @Column(name = "etude_reconstitutions")
    private Boolean reconstitutions;

    /**
     * Réalisation de préparations.
     */
    @Column(name = "etude_preparations")
    private Boolean preparations;

    /**
     * Réalisation d'étiquetages à réception des produits.
     */
    @Column(name = "etude_etiquetages")
    private Boolean etiquetagesPduits;

    /**
     * Réalisation de conditionnement de produits.
     */
    @Column(name = "etude_conditionnement")
    private Boolean conditionnementPduits;

    /**
     * Données de stabilité, modalités de préparation et matériel fournis.
     */
    @Column(name = "etude_donneesStabilite")
    private Boolean donneesStabilite;

    /**
     * Commentaires généraux.
     */
    @OneToMany(mappedBy = "detailFaisabilite", cascade = CascadeType.ALL, orphanRemoval = true)
    @Where(clause = "type='FAISABILITE_ETUDE'")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private SortedSet<CommentaireEssaiFaisabilite> commentaires =
        new TreeSet<CommentaireEssaiFaisabilite>(new SuiviComparator());

    /**
     * Getter sur prestaParticulieresPharmacie.
     * @return Retourne le prestaParticulieresPharmacie.
     */
    public Boolean getPrestaParticulieresPharmacie()
    {
        return this.prestaParticulieresPharmacie;
    }

    /**
     * Setter pour prestaParticulieresPharmacie.
     * @param prestaParticulieresPharmacie le prestaParticulieresPharmacie à écrire.
     */
    public void setPrestaParticulieresPharmacie(final Boolean prestaParticulieresPharmacie)
    {
        this.prestaParticulieresPharmacie = prestaParticulieresPharmacie;
    }

    /**
     * Getter sur achatsProduitsPUI.
     * @return Retourne le achatsProduitsPUI.
     */
    public Boolean getAchatsProduitsPUI()
    {
        return this.achatsProduitsPUI;
    }

    /**
     * Setter pour achatsProduitsPUI.
     * @param achatsProduitsPUI le achatsProduitsPUI à écrire.
     */
    public void setAchatsProduitsPUI(final Boolean achatsProduitsPUI)
    {
        this.achatsProduitsPUI = achatsProduitsPUI;
    }

    /**
     * Getter sur refProduitsCHU.
     * @return Retourne le refProduitsCHU.
     */
    public Boolean getRefProduitsCHU()
    {
        return this.refProduitsCHU;
    }

    /**
     * Setter pour refProduitsCHU.
     * @param refProduitsCHU le refProduitsCHU à écrire.
     */
    public void setRefProduitsCHU(final Boolean refProduitsCHU)
    {
        this.refProduitsCHU = refProduitsCHU;
    }

    /**
     * Getter sur accordPharmaCentrale.
     * @return Retourne le accordPharmaCentrale.
     */
    public Boolean getAccordPharmaCentrale()
    {
        return this.accordPharmaCentrale;
    }

    /**
     * Setter pour accordPharmaCentrale.
     * @param accordPharmaCentrale le accordPharmaCentrale à écrire.
     */
    public void setAccordPharmaCentrale(final Boolean accordPharmaCentrale)
    {
        this.accordPharmaCentrale = accordPharmaCentrale;
    }

    /**
     * Getter sur servicesImputation.
     * @return Retourne le servicesImputation.
     */
    public SortedSet<Service> getServicesImputation()
    {
        return this.servicesImputation;
    }

    /**
     * Setter pour servicesImputation.
     * @param servicesImputation le servicesImputation à écrire.
     */
    public void setServicesImputation(final SortedSet<Service> servicesImputation)
    {
        this.servicesImputation = servicesImputation;
    }

    /**
     * Getter sur commentairesAchatsPUI.
     * @return Retourne le commentairesAchatsPUI.
     */
    public SortedSet<CommentaireEssaiFaisabilite> getCommentairesAchatsPUI()
    {
        return this.commentairesAchatsPUI;
    }

    /**
     * Setter pour commentairesAchatsPUI.
     * @param commentairesAchatsPUI le commentairesAchatsPUI à écrire.
     */
    public void setCommentairesAchatsPUI(final SortedSet<CommentaireEssaiFaisabilite> commentairesAchatsPUI)
    {
        this.commentairesAchatsPUI = commentairesAchatsPUI;
    }

    /**
     * Getter sur demandeImportation.
     * @return Retourne le demandeImportation.
     */
    public Boolean getDemandeImportation()
    {
        return this.demandeImportation;
    }

    /**
     * Setter pour demandeImportation.
     * @param demandeImportation le demandeImportation à écrire.
     */
    public void setDemandeImportation(final Boolean demandeImportation)
    {
        this.demandeImportation = demandeImportation;
    }

    /**
     * Getter sur randomisationParPharma.
     * @return Retourne le randomisationParPharma.
     */
    public Boolean getRandomisationParPharma()
    {
        return this.randomisationParPharma;
    }

    /**
     * Setter pour randomisationParPharma.
     * @param randomisationParPharma le randomisationParPharma à écrire.
     */
    public void setRandomisationParPharma(final Boolean randomisationParPharma)
    {
        this.randomisationParPharma = randomisationParPharma;
    }

    /**
     * Getter sur randomisationPossEnGarde.
     * @return Retourne le randomisationPossEnGarde.
     */
    public Boolean getRandomisationPossEnGarde()
    {
        return this.randomisationPossEnGarde;
    }

    /**
     * Setter pour randomisationPossEnGarde.
     * @param randomisationPossEnGarde le randomisationPossEnGarde à écrire.
     */
    public void setRandomisationPossEnGarde(final Boolean randomisationPossEnGarde)
    {
        this.randomisationPossEnGarde = randomisationPossEnGarde;
    }

    /**
     * Getter sur gestionAveugle.
     * @return Retourne le gestionAveugle.
     */
    public Boolean getGestionAveugle()
    {
        return this.gestionAveugle;
    }

    /**
     * Setter pour gestionAveugle.
     * @param gestionAveugle le gestionAveugle à écrire.
     */
    public void setGestionAveugle(final Boolean gestionAveugle)
    {
        this.gestionAveugle = gestionAveugle;
    }

    /**
     * Getter sur dispensationPossEnGarde.
     * @return Retourne le dispensationPossEnGarde.
     */
    public Boolean getDispensationPossEnGarde()
    {
        return this.dispensationPossEnGarde;
    }

    /**
     * Setter pour dispensationPossEnGarde.
     * @param dispensationPossEnGarde le dispensationPossEnGarde à écrire.
     */
    public void setDispensationPossEnGarde(final Boolean dispensationPossEnGarde)
    {
        this.dispensationPossEnGarde = dispensationPossEnGarde;
    }

    /**
     * Getter sur distribAutresPharmaPossible.
     * @return Retourne le distribAutresPharmaPossible.
     */
    public Boolean getDistribAutresPharmaPossible()
    {
        return this.distribAutresPharmaPossible;
    }

    /**
     * Setter pour distribAutresPharmaPossible.
     * @param distribAutresPharmaPossible le distribAutresPharmaPossible à écrire.
     */
    public void setDistribAutresPharmaPossible(final Boolean distribAutresPharmaPossible)
    {
        this.distribAutresPharmaPossible = distribAutresPharmaPossible;
    }

    /**
     * Getter sur circuitDistribDefini.
     * @return Retourne le circuitDistribDefini.
     */
    public Boolean getCircuitDistribDefini()
    {
        return this.circuitDistribDefini;
    }

    /**
     * Setter pour circuitDistribDefini.
     * @param circuitDistribDefini le circuitDistribDefini à écrire.
     */
    public void setCircuitDistribDefini(final Boolean circuitDistribDefini)
    {
        this.circuitDistribDefini = circuitDistribDefini;
    }

    /**
     * Getter sur socTransportDefinie.
     * @return Retourne le socTransportDefinie.
     */
    public Boolean getSocTransportDefinie()
    {
        return this.socTransportDefinie;
    }

    /**
     * Setter pour socTransportDefinie.
     * @param socTransportDefinie le socTransportDefinie à écrire.
     */
    public void setSocTransportDefinie(final Boolean socTransportDefinie)
    {
        this.socTransportDefinie = socTransportDefinie;
    }

    /**
     * Getter sur suiviTempNecessairePdtTransp.
     * @return Retourne le suiviTempNecessairePdtTransp.
     */
    public Boolean getSuiviTempNecessairePdtTransp()
    {
        return this.suiviTempNecessairePdtTransp;
    }

    /**
     * Setter pour suiviTempNecessairePdtTransp.
     * @param suiviTempNecessairePdtTransp le suiviTempNecessairePdtTransp à écrire.
     */
    public void setSuiviTempNecessairePdtTransp(final Boolean suiviTempNecessairePdtTransp)
    {
        this.suiviTempNecessairePdtTransp = suiviTempNecessairePdtTransp;
    }

    /**
     * Getter sur suiviStocksParPharmacie.
     * @return Retourne le suiviStocksParPharmacie.
     */
    public Boolean getSuiviStocksParPharmacie()
    {
        return this.suiviStocksParPharmacie;
    }

    /**
     * Setter pour suiviStocksParPharmacie.
     * @param suiviStocksParPharmacie le suiviStocksParPharmacie à écrire.
     */
    public void setSuiviStocksParPharmacie(final Boolean suiviStocksParPharmacie)
    {
        this.suiviStocksParPharmacie = suiviStocksParPharmacie;
    }

    /**
     * Getter sur commentairesDistribAutresPharma.
     * @return Retourne le commentairesDistribAutresPharma.
     */
    public SortedSet<CommentaireEssaiFaisabilite> getCommentairesDistribAutresPharma()
    {
        return this.commentairesDistribAutresPharma;
    }

    /**
     * Setter pour commentairesDistribAutresPharma.
     * @param commentairesDistribAutresPharma le commentairesDistribAutresPharma à écrire.
     */
    public void setCommentairesDistribAutresPharma(final SortedSet<CommentaireEssaiFaisabilite> commentairesDistribAutresPharma)
    {
        this.commentairesDistribAutresPharma = commentairesDistribAutresPharma;
    }

    /**
     * Getter sur reconstitutions.
     * @return Retourne le reconstitutions.
     */
    public Boolean getReconstitutions()
    {
        return this.reconstitutions;
    }

    /**
     * Setter pour reconstitutions.
     * @param reconstitutions le reconstitutions à écrire.
     */
    public void setReconstitutions(final Boolean reconstitutions)
    {
        this.reconstitutions = reconstitutions;
    }

    /**
     * Getter sur preparations.
     * @return Retourne le preparations.
     */
    public Boolean getPreparations()
    {
        return this.preparations;
    }

    /**
     * Setter pour preparations.
     * @param preparations le preparations à écrire.
     */
    public void setPreparations(final Boolean preparations)
    {
        this.preparations = preparations;
    }

    /**
     * Getter sur etiquetagesPduits.
     * @return Retourne le etiquetagesPduits.
     */
    public Boolean getEtiquetagesPduits()
    {
        return this.etiquetagesPduits;
    }

    /**
     * Setter pour etiquetagesPduits.
     * @param etiquetagesPduits le etiquetagesPduits à écrire.
     */
    public void setEtiquetagesPduits(final Boolean etiquetagesPduits)
    {
        this.etiquetagesPduits = etiquetagesPduits;
    }

    /**
     * Getter sur conditionnementPduits.
     * @return Retourne le conditionnementPduits.
     */
    public Boolean getConditionnementPduits()
    {
        return this.conditionnementPduits;
    }

    /**
     * Setter pour conditionnementPduits.
     * @param conditionnementPduits le conditionnementPduits à écrire.
     */
    public void setConditionnementPduits(final Boolean conditionnementPduits)
    {
        this.conditionnementPduits = conditionnementPduits;
    }

    /**
     * Getter sur donneesStabilite.
     * @return Retourne le donneesStabilite.
     */
    public Boolean getDonneesStabilite()
    {
        return this.donneesStabilite;
    }

    /**
     * Setter pour donneesStabilite.
     * @param donneesStabilite le donneesStabilite à écrire.
     */
    public void setDonneesStabilite(final Boolean donneesStabilite)
    {
        this.donneesStabilite = donneesStabilite;
    }

    /**
     * Getter sur commentaires.
     * @return Retourne le commentaires.
     */
    public SortedSet<CommentaireEssaiFaisabilite> getCommentaires()
    {
        return this.commentaires;
    }

    /**
     * Setter pour commentaires.
     * @param commentaires le commentaires à écrire.
     */
    public void setCommentaires(final SortedSet<CommentaireEssaiFaisabilite> commentaires)
    {
        this.commentaires = commentaires;
    }

}
