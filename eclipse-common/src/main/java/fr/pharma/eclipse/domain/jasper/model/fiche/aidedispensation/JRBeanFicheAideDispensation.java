package fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation;

import net.sf.jasperreports.engine.JRDataSource;
import fr.pharma.eclipse.domain.jasper.model.common.AbstractJRBeanWithHeader;
import fr.pharma.eclipse.domain.jasper.model.common.JRBeanHeader;
import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;

/**
 * Classe représentant la source de données principale<br>
 * du rapport Jasper intitulé<br>
 * "Fiche de gestion et d'aide à la dispensation d'un médicament en essai clinique"
 * .
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRBeanFicheAideDispensation extends AbstractJRBeanWithHeader implements JasperReportBean {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -7069286927579671691L;

    /**
     * Données de l'en-tête.
     */
    private JRBeanHeader header;

    /**
     * Données de la partie 1 : générale.
     */
    private JRDataSource partie1;

    /**
     * Données de la partie 2 : produits.
     */
    private JRDataSource partie2;

    /**
     * Données de la partie 3 : randomisation.
     */
    private JRDataSource partie3;

    /**
     * Données de la partie 4 : dispensation.
     */
    private JRDataSource partie4;

    /**
     * Données de la partie 5 : stocks.
     */
    private JRDataSource partie5;

    /**
     * Données de la partie 6 : retours.
     */
    private JRDataSource partie6;

    /**
     * Données de la partie 7 : levée de l'insu.
     */
    private JRDataSource partie7;

    /**
     * Getter sur header.
     * @return Retourne le header.
     */
    @Override
    public JRBeanHeader getHeader() {
        return this.header;
    }

    /**
     * Setter pour header.
     * @param header le header à écrire.
     */
    public void setHeader(final JRBeanHeader header) {
        this.header = header;
    }

    /**
     * Getter sur partie1.
     * @return Retourne le partie1.
     */
    public JRDataSource getPartie1() {
        return this.partie1;
    }

    /**
     * Setter pour partie1.
     * @param partie1 le partie1 à écrire.
     */
    public void setPartie1(final JRDataSource partie1) {
        this.partie1 = partie1;
    }

    /**
     * Getter sur partie2.
     * @return Retourne le partie2.
     */
    public JRDataSource getPartie2() {
        return this.partie2;
    }

    /**
     * Setter pour partie2.
     * @param partie2 le partie2 à écrire.
     */
    public void setPartie2(final JRDataSource partie2) {
        this.partie2 = partie2;
    }

    /**
     * Getter sur partie3.
     * @return Retourne le partie3.
     */
    public JRDataSource getPartie3() {
        return this.partie3;
    }

    /**
     * Setter pour partie3.
     * @param partie3 le partie3 à écrire.
     */
    public void setPartie3(final JRDataSource partie3) {
        this.partie3 = partie3;
    }

    /**
     * Getter sur partie4.
     * @return Retourne le partie4.
     */
    public JRDataSource getPartie4() {
        return this.partie4;
    }

    /**
     * Setter pour partie4.
     * @param partie4 le partie4 à écrire.
     */
    public void setPartie4(final JRDataSource partie4) {
        this.partie4 = partie4;
    }

    /**
     * Getter sur partie5.
     * @return Retourne le partie5.
     */
    public JRDataSource getPartie5() {
        return this.partie5;
    }

    /**
     * Setter pour partie5.
     * @param partie5 le partie5 à écrire.
     */
    public void setPartie5(final JRDataSource partie5) {
        this.partie5 = partie5;
    }

    /**
     * Getter sur partie6.
     * @return Retourne le partie6.
     */
    public JRDataSource getPartie6() {
        return this.partie6;
    }

    /**
     * Setter pour partie6.
     * @param partie6 le partie6 à écrire.
     */
    public void setPartie6(final JRDataSource partie6) {
        this.partie6 = partie6;
    }

    /**
     * Getter sur partie7.
     * @return Retourne le partie7.
     */
    public JRDataSource getPartie7() {
        return this.partie7;
    }

    /**
     * Setter pour partie7.
     * @param partie7 le partie7 à écrire.
     */
    public void setPartie7(final JRDataSource partie7) {
        this.partie7 = partie7;
    }

}
