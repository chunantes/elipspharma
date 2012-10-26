package fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation;

import org.springframework.util.StringUtils;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.jasper.constants.JasperConstants;

/**
 * Classe représentant la source de données de la partie 7 (infos. levée de l'insu)<br>
 * du rapport Jasper intitulé<br>
 * "Fiche de gestion et d'aide à la dispensation d'un médicament en essai clinique". *
 
 * @version $Revision$ $Date$
 */
public class JRBeanFicheAideDispensationPart7
    implements JasperReportBean
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8399622421838881955L;

    /**
     * Responsable de la levée de l'insu.
     */
    private String respLeveeInsu = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Flag qui indique s'il faut montrer le fichier associé.
     */
    private boolean showMoAssocie = false;

    /**
     * MO Associe.
     */
    private String moAssocie = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Getter sur respLeveeInsu.
     * @return Retourne le respLeveeInsu.
     */
    public String getRespLeveeInsu()
    {
        return this.respLeveeInsu;
    }

    /**
     * Setter pour respLeveeInsu.
     * @param respLeveeInsu le respLeveeInsu à écrire.
     */
    public void setRespLeveeInsu(final String respLeveeInsu)
    {
        this.respLeveeInsu = respLeveeInsu;
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
        this.showMoAssocie = StringUtils.hasText(moAssocie);
    }

    /**
     * Getter sur showMoAssocie.
     * @return Retourne le showMoAssocie.
     */
    public boolean getShowMoAssocie()
    {
        return this.showMoAssocie;
    }

}
