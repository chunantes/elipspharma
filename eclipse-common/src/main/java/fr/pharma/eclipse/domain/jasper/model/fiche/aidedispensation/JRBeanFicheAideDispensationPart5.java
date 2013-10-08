package fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation;

import net.sf.jasperreports.engine.JRDataSource;
import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.jasper.constants.JasperConstants;

/**
 * Classe représentant la source de données de la partie 5 (infos. stock)<br>
 * du rapport Jasper intitulé<br>
 * "Fiche de gestion et d'aide à la dispensation d'un médicament en essai clinique"
 * . *
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRBeanFicheAideDispensationPart5 implements JasperReportBean {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 4685605545037762741L;

    /**
     * Responsabilité des commandes.
     */
    private String respCommande = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Modalités de réception.
     */
    private JRDataSource modalitesReception;

    /**
     * Getter sur respCommande.
     * @return Retourne le respCommande.
     */
    public String getRespCommande() {
        return this.respCommande;
    }

    /**
     * Setter pour respCommande.
     * @param respCommande le respCommande à écrire.
     */
    public void setRespCommande(final String respCommande) {
        this.respCommande = respCommande;
    }

    /**
     * Getter sur modalitesReception.
     * @return Retourne le modalitesReception.
     */
    public JRDataSource getModalitesReception() {
        return this.modalitesReception;
    }

    /**
     * Setter pour modalitesReception.
     * @param modalitesReception le modalitesReception à écrire.
     */
    public void setModalitesReception(final JRDataSource modalitesReception) {
        this.modalitesReception = modalitesReception;
    }

}
