package fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation;

import org.springframework.util.StringUtils;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.jasper.constants.JasperConstants;

/**
 * Classe représentant la source de données de la partie 6 (infos. retours)<br>
 * du rapport Jasper intitulé<br>
 * "Fiche de gestion et d'aide à la dispensation d'un médicament en essai clinique"
 * . *
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRBeanFicheAideDispensationPart6 implements JasperReportBean {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 829284437147503802L;

    /**
     * Responsable des retours.
     */
    private String respRetour = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Flag qui indique s'il faut montrer le type de retour.
     */
    private boolean showTypeRetour = false;

    /**
     * Type de retour.
     */
    private String typeRetour = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Getter sur respRetour.
     * @return Retourne le respRetour.
     */
    public String getRespRetour() {
        return this.respRetour;
    }

    /**
     * Setter pour respRetour.
     * @param respRetour le respRetour à écrire.
     */
    public void setRespRetour(final String respRetour) {
        this.respRetour = respRetour;
    }

    /**
     * Getter sur typeRetour.
     * @return Retourne le typeRetour.
     */
    public String getTypeRetour() {
        return this.typeRetour;
    }

    /**
     * Setter pour typeRetour.
     * @param typeRetour le typeRetour à écrire.
     */
    public void setTypeRetour(final String typeRetour) {
        this.typeRetour = typeRetour;
        this.showTypeRetour = StringUtils.hasText(typeRetour);
    }

    /**
     * Getter sur showTypeRetour.
     * @return Retourne le showTypeRetour.
     */
    public boolean getShowTypeRetour() {
        return this.showTypeRetour;
    }

}
