package fr.pharma.eclipse.domain.criteria.essai;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.localisation.Site;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Critère de recherche sur Essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiSearchCriteria extends AbstractSearchCriteria {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3409282531875035453L;

    /**
     * Numéro d'identification interne.
     */
    private String numInterne;

    /**
     * Numéro d'identification interne strict (égalité).
     */
    private String numInterneStrict;

    /**
     * Numéro SIGREC.
     */
    private String numSigrec;

    /**
     * Nom.
     */
    private String nom;

    /**
     * Année de création.
     */
    private Integer anneeCreation;

    /**
     * DCI.
     */
    private String dci;

    /**
     * Etat de l'essai.
     */
    private EtatEssai etat;

    /**
     * Investigateur principal.
     */
    private Investigateur investigateur;

    /**
     * Promoteur.
     */
    private Promoteur promoteur;

    /**
     * Site. Attention, la recherche par site n'utilise
     * EssaiSearchCriteriaMaker. Utiliser EssaiService.getAllEssais().
     */
    private Site site;

    /**
     * Pharmacie (principale ou autre).
     */
    private Pharmacie pharmacie;

    /**
     * Service.
     */
    private Service service;

    /**
     * Mots clés.
     */
    private String motsCles;

    /**
     * NumInterne ou Nom ou Promoteur (recherche sur les 3 champs).
     */
    private String numInterneOrNomOrPromoteur;

    /**
     * Booléen indiquant la récupération des essais dont le type de dispensation
     * est null ou globale.
     */
    private Boolean essaisDispensationGlobale;

    /**
     * Numero EUDRACT.
     */
    private String numEudract;

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
     * Getter sur pharmacie.
     * @return Retourne le pharmacie.
     */
    public Pharmacie getPharmacie() {
        return this.pharmacie;
    }

    /**
     * Setter pour pharmacie.
     * @param pharmacie le pharmacie à écrire.
     */
    public void setPharmacie(final Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
    }

    /**
     * Getter sur site.
     * @return Retourne le site.
     */
    public Site getSite() {
        return this.site;
    }

    /**
     * Setter pour site.
     * @param site le site à écrire.
     */
    public void setSite(final Site site) {
        this.site = site;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.setDci(null);
        this.setEtat(null);
        this.setNom(null);
        this.setNumInterne(null);
        this.setPharmacie(null);
        this.setSite(null);
        this.setInvestigateur(null);
        this.setPromoteur(null);
        this.setService(null);
        this.setNumSigrec(null);
        this.setNumInterneOrNomOrPromoteur(null);
        this.setAnneeCreation(null);
        this.setMotsCles(null);
        this.setEssaisDispensationGlobale(null);
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
     * Getter sur service.
     * @return Retourne le service.
     */
    public Service getService() {
        return this.service;
    }

    /**
     * Setter pour service.
     * @param service le service à écrire.
     */
    public void setService(final Service service) {
        this.service = service;
    }

    /**
     * Getter sur investigateur.
     * @return Retourne le investigateur.
     */
    public Investigateur getInvestigateur() {
        return this.investigateur;
    }

    /**
     * Setter pour investigateur.
     * @param investigateur le investigateur à écrire.
     */
    public void setInvestigateur(final Investigateur investigateur) {
        this.investigateur = investigateur;
    }

    /**
     * Getter pour motsCles.
     * @return Le motsCles
     */
    public String getMotsCles() {
        return this.motsCles;
    }

    /**
     * Setter pour motsCles.
     * @param motsCles Le motsCles à écrire.
     */
    public void setMotsCles(final String motsCles) {
        this.motsCles = motsCles;
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
     * Getter sur numSigrec.
     * @return Retourne le numSigrec.
     */
    public String getNumSigrec() {
        return this.numSigrec;
    }

    /**
     * Setter pour numSigrec.
     * @param numSigrec le numSigrec à écrire.
     */
    public void setNumSigrec(final String numSigrec) {
        this.numSigrec = numSigrec;
    }

    /**
     * Getter pour numInterneOrNomOrPromoteur.
     * @return Le numInterneOrNomOrPromoteur
     */
    public String getNumInterneOrNomOrPromoteur() {
        return this.numInterneOrNomOrPromoteur;
    }

    /**
     * Setter pour numInterneOrNomOrPromoteur.
     * @param numInterneOrNomOrPromoteur Le numInterneOrNomOrPromoteur à écrire.
     */
    public void setNumInterneOrNomOrPromoteur(final String numInterneOrNomOrPromoteur) {
        this.numInterneOrNomOrPromoteur = numInterneOrNomOrPromoteur;
    }

    /**
     * Getter pour essaisDispensationGlobale.
     * @return Le essaisDispensationGlobale
     */
    public Boolean getEssaisDispensationGlobale() {
        return this.essaisDispensationGlobale;
    }

    /**
     * Setter pour essaisDispensationGlobale.
     * @param essaisDispensationGlobale Le essaisDispensationGlobale à écrire.
     */
    public void setEssaisDispensationGlobale(final Boolean essaisDispensationGlobale) {
        this.essaisDispensationGlobale = essaisDispensationGlobale;
    }

    /**
     * Getter pour numEudract.
     * @return Le numEudract
     */
    public String getNumEudract() {
        return this.numEudract;
    }

    /**
     * Setter pour numEudract.
     * @param numEudract Le numEudract à écrire.
     */
    public void setNumEudract(final String numEudract) {
        this.numEudract = numEudract;
    }

    /**
     * Getter pour numInterneStrict.
     * @return Le numInterneStrict
     */
    public String getNumInterneStrict() {
        return this.numInterneStrict;
    }

    /**
     * Setter pour numInterneStrict.
     * @param numInterneStrict Le numInterneStrict à écrire.
     */
    public void setNumInterneStrict(final String numInterneStrict) {
        this.numInterneStrict = numInterneStrict;
    }

}
