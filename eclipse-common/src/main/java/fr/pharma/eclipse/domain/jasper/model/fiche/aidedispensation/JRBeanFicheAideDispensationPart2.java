package fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation;

import net.sf.jasperreports.engine.JRDataSource;
import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;

/**
 * Classe représentant la source de données de la partie 2 (infos. des produits)<br>
 * du rapport Jasper intitulé<br>
 * "Fiche de gestion et d'aide à la dispensation d'un médicament en essai clinique"
 * . *
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRBeanFicheAideDispensationPart2 implements JasperReportBean {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4120682763514365242L;

    /**
     * Informations des médicaments.
     */
    private JRDataSource medicaments;

    /**
     * Informations des dispositifs médicaux.
     */
    private JRDataSource dispoMedicaux;

    /**
     * Informations des produits thérapeuthiques.
     */
    private JRDataSource pduitsTherapeutiques;

    /**
     * Actes pharmaceutiques spécifiques liés.
     */
    private JRDataSource actesPharmacies;

    /**
     * Getter sur medicaments.
     * @return Retourne le medicaments.
     */
    public JRDataSource getMedicaments() {
        return this.medicaments;
    }

    /**
     * Setter pour medicaments.
     * @param medicaments le medicaments à écrire.
     */
    public void setMedicaments(final JRDataSource medicaments) {
        this.medicaments = medicaments;
    }

    /**
     * Getter sur dispoMedicaux.
     * @return Retourne le dispoMedicaux.
     */
    public JRDataSource getDispoMedicaux() {
        return this.dispoMedicaux;
    }

    /**
     * Setter pour dispoMedicaux.
     * @param dispoMedicaux le dispoMedicaux à écrire.
     */
    public void setDispoMedicaux(final JRDataSource dispoMedicaux) {
        this.dispoMedicaux = dispoMedicaux;
    }

    /**
     * Getter sur pduitsTherapeutiques.
     * @return Retourne le pduitsTherapeutiques.
     */
    public JRDataSource getPduitsTherapeutiques() {
        return this.pduitsTherapeutiques;
    }

    /**
     * Setter pour pduitsTherapeutiques.
     * @param pduitsTherapeutiques le pduitsTherapeutiques à écrire.
     */
    public void setPduitsTherapeutiques(final JRDataSource pduitsTherapeutiques) {
        this.pduitsTherapeutiques = pduitsTherapeutiques;
    }

    /**
     * Getter sur actesPharmacies.
     * @return Retourne le actesPharmacies.
     */
    public JRDataSource getActesPharmacies() {
        return this.actesPharmacies;
    }

    /**
     * Setter pour actesPharmacies.
     * @param actesPharmacies le actesPharmacies à écrire.
     */
    public void setActesPharmacies(final JRDataSource actesPharmacies) {
        this.actesPharmacies = actesPharmacies;
    }

}
