package fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation;

import net.sf.jasperreports.engine.JRDataSource;
import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.jasper.constants.JasperConstants;

/**
 * Classe représentant la source de données de la partie 1 (générale)<br>
 * du rapport Jasper intitulé<br>
 * "Fiche de gestion et d'aide à la dispensation d'un médicament en essai clinique".
 
 * @version $Revision$ $Date$
 */
public class JRBeanFicheAideDispensationPart1
    implements JasperReportBean
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2078149467216800792L;

    /**
     * Contacts promoteur.
     */
    private JRDataSource contactsPromoteurs;

    /**
     * Contacts investigateurs.
     */
    private JRDataSource contactsInvestigateurs;

    /**
     * Contacts pharmacies.
     */
    private JRDataSource contactsPharmacies;

    /**
     * Groupes de traitement.
     */
    private JRDataSource groupesTraitement;

    /**
     * Promoteur.
     */
    private String promoteur = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Code protocole.
     */
    private String codeProtocole = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Nom usuel.
     */
    private String nomUsuel = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Titre du protocole.
     */
    private String titreProtocole = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Investigateur principal.
     */
    private String investigateur = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Numéro du centre.
     */
    private String numeroCentre = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Service investigateur.
     */
    private String serviceInvestigateur = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Phase.
     */
    private String phase = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Essai multicentrique.
     */
    private String multicentrique = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Nombre de centres.
     */
    private String nbCentres = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Nombre de patients.
     */
    private String nbPatients = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Nombre de groupes.
     */
    private String nbGroupes = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Qualité de l'insu.
     */
    private String qualiteInsu = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Date de mise en place.
     */
    private String dateMiseEnPlace = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Date d'activation.
     */
    private String dateActivation = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Date prévue de fin d'inclusions.
     */
    private String datePrevueFinInclusions = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Date prévue de fin d'essai.
     */
    private String datePrevueFinEssai = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Getter sur promoteur.
     * @return Retourne le promoteur.
     */
    public String getPromoteur()
    {
        return this.promoteur;
    }

    /**
     * Setter pour promoteur.
     * @param promoteur le promoteur à écrire.
     */
    public void setPromoteur(final String promoteur)
    {
        this.promoteur = promoteur;
    }

    /**
     * Getter sur codeProtocole.
     * @return Retourne le codeProtocole.
     */
    public String getCodeProtocole()
    {
        return this.codeProtocole;
    }

    /**
     * Setter pour codeProtocole.
     * @param codeProtocole le codeProtocole à écrire.
     */
    public void setCodeProtocole(final String codeProtocole)
    {
        this.codeProtocole = codeProtocole;
    }

    /**
     * Getter sur nomUsuel.
     * @return Retourne le nomUsuel.
     */
    public String getNomUsuel()
    {
        return this.nomUsuel;
    }

    /**
     * Setter pour nomUsuel.
     * @param nomUsuel le nomUsuel à écrire.
     */
    public void setNomUsuel(final String nomUsuel)
    {
        this.nomUsuel = nomUsuel;
    }

    /**
     * Getter sur titreProtocole.
     * @return Retourne le titreProtocole.
     */
    public String getTitreProtocole()
    {
        return this.titreProtocole;
    }

    /**
     * Setter pour titreProtocole.
     * @param titreProtocole le titreProtocole à écrire.
     */
    public void setTitreProtocole(final String titreProtocole)
    {
        this.titreProtocole = titreProtocole;
    }

    /**
     * Getter sur investigateur.
     * @return Retourne le investigateur.
     */
    public String getInvestigateur()
    {
        return this.investigateur;
    }

    /**
     * Setter pour investigateur.
     * @param investigateur le investigateur à écrire.
     */
    public void setInvestigateur(final String investigateur)
    {
        this.investigateur = investigateur;
    }

    /**
     * Getter sur serviceInvestigateur.
     * @return Retourne le serviceInvestigateur.
     */
    public String getServiceInvestigateur()
    {
        return this.serviceInvestigateur;
    }

    /**
     * Setter pour serviceInvestigateur.
     * @param serviceInvestigateur le serviceInvestigateur à écrire.
     */
    public void setServiceInvestigateur(final String serviceInvestigateur)
    {
        this.serviceInvestigateur = serviceInvestigateur;
    }

    /**
     * Getter sur phase.
     * @return Retourne le phase.
     */
    public String getPhase()
    {
        return this.phase;
    }

    /**
     * Setter pour phase.
     * @param phase le phase à écrire.
     */
    public void setPhase(final String phase)
    {
        this.phase = phase;
    }

    /**
     * Getter sur multicentrique.
     * @return Retourne le multicentrique.
     */
    public String getMulticentrique()
    {
        return this.multicentrique;
    }

    /**
     * Setter pour multicentrique.
     * @param multicentrique le multicentrique à écrire.
     */
    public void setMulticentrique(final String multicentrique)
    {
        this.multicentrique = multicentrique;
    }

    /**
     * Getter sur nbCentres.
     * @return Retourne le nbCentres.
     */
    public String getNbCentres()
    {
        return this.nbCentres;
    }

    /**
     * Setter pour nbCentres.
     * @param nbCentres le nbCentres à écrire.
     */
    public void setNbCentres(final String nbCentres)
    {
        this.nbCentres = nbCentres;
    }

    /**
     * Getter sur nbPatients.
     * @return Retourne le nbPatients.
     */
    public String getNbPatients()
    {
        return this.nbPatients;
    }

    /**
     * Setter pour nbPatients.
     * @param nbPatients le nbPatients à écrire.
     */
    public void setNbPatients(final String nbPatients)
    {
        this.nbPatients = nbPatients;
    }

    /**
     * Getter sur nbGroupes.
     * @return Retourne le nbGroupes.
     */
    public String getNbGroupes()
    {
        return this.nbGroupes;
    }

    /**
     * Setter pour nbGroupes.
     * @param nbGroupes le nbGroupes à écrire.
     */
    public void setNbGroupes(final String nbGroupes)
    {
        this.nbGroupes = nbGroupes;
    }

    /**
     * Getter sur dateMiseEnPlace.
     * @return Retourne le dateMiseEnPlace.
     */
    public String getDateMiseEnPlace()
    {
        return this.dateMiseEnPlace;
    }

    /**
     * Setter pour dateMiseEnPlace.
     * @param dateMiseEnPlace le dateMiseEnPlace à écrire.
     */
    public void setDateMiseEnPlace(final String dateMiseEnPlace)
    {
        this.dateMiseEnPlace = dateMiseEnPlace;
    }

    /**
     * Getter sur dateActivation.
     * @return Retourne le dateActivation.
     */
    public String getDateActivation()
    {
        return this.dateActivation;
    }

    /**
     * Setter pour dateActivation.
     * @param dateActivation le dateActivation à écrire.
     */
    public void setDateActivation(final String dateActivation)
    {
        this.dateActivation = dateActivation;
    }

    /**
     * Getter sur datePrevueFinInclusions.
     * @return Retourne le datePrevueFinInclusions.
     */
    public String getDatePrevueFinInclusions()
    {
        return this.datePrevueFinInclusions;
    }

    /**
     * Setter pour datePrevueFinInclusions.
     * @param datePrevueFinInclusions le datePrevueFinInclusions à écrire.
     */
    public void setDatePrevueFinInclusions(final String datePrevueFinInclusions)
    {
        this.datePrevueFinInclusions = datePrevueFinInclusions;
    }

    /**
     * Getter sur datePrevueFinEssai.
     * @return Retourne le datePrevueFinEssai.
     */
    public String getDatePrevueFinEssai()
    {
        return this.datePrevueFinEssai;
    }

    /**
     * Setter pour datePrevueFinEssai.
     * @param datePrevueFinEssai le datePrevueFinEssai à écrire.
     */
    public void setDatePrevueFinEssai(final String datePrevueFinEssai)
    {
        this.datePrevueFinEssai = datePrevueFinEssai;
    }

    /**
     * Getter sur qualiteInsu.
     * @return Retourne le qualiteInsu.
     */
    public String getQualiteInsu()
    {
        return this.qualiteInsu;
    }

    /**
     * Setter pour qualiteInsu.
     * @param qualiteInsu le qualiteInsu à écrire.
     */
    public void setQualiteInsu(final String qualiteInsu)
    {
        this.qualiteInsu = qualiteInsu;
    }

    /**
     * Getter sur contactsPromoteurs.
     * @return Retourne le contactsPromoteurs.
     */
    public JRDataSource getContactsPromoteurs()
    {
        return this.contactsPromoteurs;
    }

    /**
     * Setter pour contactsPromoteurs.
     * @param contactsPromoteurs le contactsPromoteurs à écrire.
     */
    public void setContactsPromoteurs(final JRDataSource contactsPromoteurs)
    {
        this.contactsPromoteurs = contactsPromoteurs;
    }

    /**
     * Getter sur contactsInvestigateurs.
     * @return Retourne le contactsInvestigateurs.
     */
    public JRDataSource getContactsInvestigateurs()
    {
        return this.contactsInvestigateurs;
    }

    /**
     * Setter pour contactsInvestigateurs.
     * @param contactsInvestigateurs le contactsInvestigateurs à écrire.
     */
    public void setContactsInvestigateurs(final JRDataSource contactsInvestigateurs)
    {
        this.contactsInvestigateurs = contactsInvestigateurs;
    }

    /**
     * Getter sur contactsPharmacies.
     * @return Retourne le contactsPharmacies.
     */
    public JRDataSource getContactsPharmacies()
    {
        return this.contactsPharmacies;
    }

    /**
     * Setter pour contactsPharmacies.
     * @param contactsPharmacies le contactsPharmacies à écrire.
     */
    public void setContactsPharmacies(final JRDataSource contactsPharmacies)
    {
        this.contactsPharmacies = contactsPharmacies;
    }

    /**
     * Getter sur groupesTraitement.
     * @return Retourne le groupesTraitement.
     */
    public JRDataSource getGroupesTraitement()
    {
        return this.groupesTraitement;
    }

    /**
     * Setter pour groupesTraitement.
     * @param groupesTraitement le groupesTraitement à écrire.
     */
    public void setGroupesTraitement(final JRDataSource groupesTraitement)
    {
        this.groupesTraitement = groupesTraitement;
    }

    /**
     * Getter pour numeroCentre.
     * @return Le numeroCentre
     */
    public String getNumeroCentre()
    {
        return this.numeroCentre;
    }

    /**
     * Setter pour numeroCentre.
     * @param numeroCentre Le numeroCentre à écrire.
     */
    public void setNumeroCentre(final String numeroCentre)
    {
        this.numeroCentre = numeroCentre;
    }

}
