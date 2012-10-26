package fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.jasper.constants.JasperConstants;

/**
 * Classe représentant la source de données de la partie 3 (infos. randomisation)<br>
 * du rapport Jasper intitulé<br>
 * "Fiche de gestion et d'aide à la dispensation d'un médicament en essai clinique". *
 
 * @version $Revision$ $Date$
 */
public class JRBeanFicheAideDispensationPart3
    implements JasperReportBean
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2382989183346640258L;

    /**
     * Responsabilité de la randomisation.
     */
    private String respRandomisation = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * MO associé.
     */
    private String moAssocie = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Flag pour indiquer si l'acte a un MO associé.
     */
    private Boolean hasMoAssocie = Boolean.FALSE;

    /**
     * Getter sur respRandomisation.
     * @return Retourne le respRandomisation.
     */
    public String getRespRandomisation()
    {
        return this.respRandomisation;
    }

    /**
     * Setter pour respRandomisation.
     * @param respRandomisation le respRandomisation à écrire.
     */
    public void setRespRandomisation(final String respRandomisation)
    {
        this.respRandomisation = respRandomisation;
    }

    /**
     * Getter sur moAssocie.
     * @return Retourne le moAssocie.
     */
    public String getMoAssocie()
    {
        return this.moAssocie;
    }

    /**
     * Setter pour moAssocie.
     * @param moAssocie le moAssocie à écrire.
     */
    public void setMoAssocie(final String moAssocie)
    {
        this.moAssocie = moAssocie;
    }

    /**
     * Getter sur hasMoAssocie.
     * @return Retourne le hasMoAssocie.
     */
    public Boolean getHasMoAssocie()
    {
        return this.hasMoAssocie;
    }

    /**
     * Setter pour hasMoAssocie.
     * @param hasMoAssocie le hasMoAssocie à écrire.
     */
    public void setHasMoAssocie(final Boolean hasMoAssocie)
    {
        this.hasMoAssocie = hasMoAssocie;
    }

}
