package fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation;

import net.sf.jasperreports.engine.JRDataSource;
import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.jasper.constants.JasperConstants;

/**
 * Classe représentant la source de données de la partie 4 (infos. dispensation)<br>
 * du rapport Jasper intitulé<br>
 * "Fiche de gestion et d'aide à la dispensation d'un médicament en essai clinique". *
 
 * @version $Revision$ $Date$
 */
public class JRBeanFicheAideDispensationPart4
    implements JasperReportBean
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3822289663637625283L;

    /**
     * Investigateur principal.
     */
    private String investigateurPrincipal = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Co-investigateurs.
     */
    private String coInvestigateurs = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Type de dispensation.
     */
    private String typeDispensation = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Flag pour afficher la traçabilité.
     */
    private Boolean hasTracabilite = false;

    /**
     * Traçabilité obligatoire.
     */
    private Boolean tracabiliteObligatoire;

    /**
     * Destinataires de la dispensation.
     */
    private String destinatairesDispensation = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Flag pour indiquer s'il existe, sur l'essai,<br>
     * au moins un produit avec numéro de traitement.
     */
    private Boolean hasNumeroTraitement;

    /**
     * Source de données pour le bloc des informations patient.
     */
    private JRDataSource infosPatient;

    /**
     * Flag pour indiquer si les retours patients<br>
     * sont gérés par la pharmacie.
     */
    private Boolean hasGestionRetoursPharma = Boolean.FALSE;

    /**
     * Source de données pour le bloc d'aide à la dispensation.
     */
    private JRDataSource aideDispensation;

    /**
     * Getter sur investigateurPrincipal.
     * @return Retourne le investigateurPrincipal.
     */
    public String getInvestigateurPrincipal()
    {
        return this.investigateurPrincipal;
    }

    /**
     * Setter pour investigateurPrincipal.
     * @param investigateurPrincipal le investigateurPrincipal à écrire.
     */
    public void setInvestigateurPrincipal(final String investigateurPrincipal)
    {
        this.investigateurPrincipal = investigateurPrincipal;
    }

    /**
     * Getter sur coInvestigateurs.
     * @return Retourne le coInvestigateurs.
     */
    public String getCoInvestigateurs()
    {
        return this.coInvestigateurs;
    }

    /**
     * Setter pour coInvestigateurs.
     * @param coInvestigateurs le coInvestigateurs à écrire.
     */
    public void setCoInvestigateurs(final String coInvestigateurs)
    {
        this.coInvestigateurs = coInvestigateurs;
    }

    /**
     * Getter sur typeDispensation.
     * @return Retourne le typeDispensation.
     */
    public String getTypeDispensation()
    {
        return this.typeDispensation;
    }

    /**
     * Setter pour typeDispensation.
     * @param typeDispensation le typeDispensation à écrire.
     */
    public void setTypeDispensation(final String typeDispensation)
    {
        this.typeDispensation = typeDispensation;
    }

    /**
     * Getter sur hasTracabilite.
     * @return Retourne le hasTracabilite.
     */
    public Boolean getHasTracabilite()
    {
        return this.hasTracabilite;
    }

    /**
     * Setter pour hasTracabilite.
     * @param hasTracabilite le hasTracabilite à écrire.
     */
    public void setHasTracabilite(final Boolean hasTracabilite)
    {
        this.hasTracabilite = hasTracabilite;
    }

    /**
     * Getter sur tracabiliteObligatoire.
     * @return Retourne le tracabiliteObligatoire.
     */
    public Boolean getTracabiliteObligatoire()
    {
        return this.tracabiliteObligatoire;
    }

    /**
     * Setter pour tracabiliteObligatoire.
     * @param tracabiliteObligatoire le tracabiliteObligatoire à écrire.
     */
    public void setTracabiliteObligatoire(final Boolean tracabiliteObligatoire)
    {
        this.tracabiliteObligatoire = tracabiliteObligatoire;
    }

    /**
     * Getter sur destinatairesDispensation.
     * @return Retourne le destinatairesDispensation.
     */
    public String getDestinatairesDispensation()
    {
        return this.destinatairesDispensation;
    }

    /**
     * Setter pour destinatairesDispensation.
     * @param destinatairesDispensation le destinatairesDispensation à écrire.
     */
    public void setDestinatairesDispensation(final String destinatairesDispensation)
    {
        this.destinatairesDispensation = destinatairesDispensation;
    }

    /**
     * Getter sur hasNumeroTraitement.
     * @return Retourne le hasNumeroTraitement.
     */
    public Boolean getHasNumeroTraitement()
    {
        return this.hasNumeroTraitement;
    }

    /**
     * Setter pour hasNumeroTraitement.
     * @param hasNumeroTraitement le hasNumeroTraitement à écrire.
     */
    public void setHasNumeroTraitement(final Boolean hasNumeroTraitement)
    {
        this.hasNumeroTraitement = hasNumeroTraitement;
    }

    /**
     * Getter sur infosPatient.
     * @return Retourne le infosPatient.
     */
    public JRDataSource getInfosPatient()
    {
        return this.infosPatient;
    }

    /**
     * Setter pour infosPatient.
     * @param infosPatient le infosPatient à écrire.
     */
    public void setInfosPatient(final JRDataSource infosPatient)
    {
        this.infosPatient = infosPatient;
    }

    /**
     * Getter sur hasGestionRetoursPharma.
     * @return Retourne le hasGestionRetoursPharma.
     */
    public Boolean getHasGestionRetoursPharma()
    {
        return this.hasGestionRetoursPharma;
    }

    /**
     * Setter pour hasGestionRetoursPharma.
     * @param hasGestionRetoursPharma le hasGestionRetoursPharma à écrire.
     */
    public void setHasGestionRetoursPharma(final Boolean hasGestionRetoursPharma)
    {
        this.hasGestionRetoursPharma = hasGestionRetoursPharma;
    }

    /**
     * Getter sur aideDispensation.
     * @return Retourne le aideDispensation.
     */
    public JRDataSource getAideDispensation()
    {
        return this.aideDispensation;
    }

    /**
     * Setter pour aideDispensation.
     * @param aideDispensation le aideDispensation à écrire.
     */
    public void setAideDispensation(final JRDataSource aideDispensation)
    {
        this.aideDispensation = aideDispensation;
    }

}
