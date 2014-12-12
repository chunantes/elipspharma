package fr.pharma.eclipse.jasper.engine.manager;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.jasper.engine.builder.JasperReportDatasBuilder;
import fr.pharma.eclipse.jasper.engine.exporter.JasperPrintExporter;
import fr.pharma.eclipse.jasper.engine.factory.JasperPrintFactory;
import fr.pharma.eclipse.jasper.engine.helper.CommonParametersHelper;
import fr.pharma.eclipse.jasper.exception.JasperReportBuildException;

/**
 * Classe en charge de manager la création d'un rapport Jasper.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JasperReportBuildManager implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -695976003183435333L;

    /**
     * Builder des données du rapport.
     */
    private JasperReportDatasBuilder datasBuilder;

    /**
     * Helper pour la gestion des paramètres communs des rapports.
     */
    private CommonParametersHelper commonParametersHelper;

    /**
     * Fabrique d'objets JasperPrint.
     */
    private JasperPrintFactory jpFactory;

    /**
     * Exporter d'objet JasperPrint.
     */
    private JasperPrintExporter jpExporter;

    /**
     * Type du rapport Jasper supporté.
     */
    private final TypeRapportJasper typeRapport;
    
    /**
     * Constructeur.
     * @param typeRapportName Type du rapport Jasper supporté sous forme de
     * chaîne de caractères.
     */
    public JasperReportBuildManager(final String typeRapportName) {
        this(TypeRapportJasper.valueOf(typeRapportName));
    }

    /**
     * Constructeur.
     * @param typeRapport Type du rapport Jasper supporté.
     */
    public JasperReportBuildManager(final TypeRapportJasper typeRapport) {
        this.typeRapport = typeRapport;
    }

    /**
     * Méthode en charge de piloter la génération du rapport Jasper.
     * @param source Source de données servant de base au rapport.
     * @return Le flux du rapport.
     * @throws JasperReportBuildException En cas d'erreur lors de la génération
     * du rapport.
     */
    public byte[] build(final Object source) throws JasperReportBuildException {
        // Construction des données.
        this.datasBuilder.checkSource(source);
        final JRDataSource datasource = this.datasBuilder.buildDataSource(source);
        final Map<String, Object> mapParameters = this.datasBuilder.buildParameters(source);

        // Ajout des paramètres communs.
        this.commonParametersHelper.addCommonParameters(mapParameters);

        // Génération du résultat.
        final JasperPrint jasperPrint = this.jpFactory.getInitializedObject(datasource, mapParameters);
        // Export du résultat.
        final ByteArrayOutputStream stream = this.jpExporter.export(this.typeRapport, jasperPrint);
        return stream.toByteArray();
    }

    /**
     * Méthode en charge de construire le nom du rapport.
     * @param source Source de données servant de base au rapport.
     * @return Le nom du rapport.
     * @throws JasperReportBuildException En cas d'erreur lors de la génération
     * du rapport.
     */
    public String buildFileName(final Object source) throws JasperReportBuildException {
        this.datasBuilder.checkSource(source);
        return this.datasBuilder.buildReportName(source, this.typeRapport);
    }

    /**
     * Getter sur datasBuilder.
     * @return Retourne le datasBuilder.
     */
    JasperReportDatasBuilder getDatasBuilder() {
        return this.datasBuilder;
    }

    /**
     * Setter pour datasBuilder.
     * @param datasBuilder le datasBuilder à écrire.
     */
    public void setDatasBuilder(final JasperReportDatasBuilder datasBuilder) {
        this.datasBuilder = datasBuilder;
    }

    /**
     * Getter sur jpFactory.
     * @return Retourne le jpFactory.
     */
    JasperPrintFactory getJpFactory() {
        return this.jpFactory;
    }

    /**
     * Setter pour jpFactory.
     * @param jpFactory le jpFactory à écrire.
     */
    public void setJpFactory(final JasperPrintFactory jpFactory) {
        this.jpFactory = jpFactory;
    }

    /**
     * Getter sur jpExporter.
     * @return Retourne le jpExporter.
     */
    JasperPrintExporter getJpExporter() {
        return this.jpExporter;
    }

    /**
     * Setter pour jpExporter.
     * @param jpExporter le jpExporter à écrire.
     */
    public void setJpExporter(final JasperPrintExporter jpExporter) {
        this.jpExporter = jpExporter;
    }

    /**
     * Getter sur typeRapport.
     * @return Retourne le typeRapport.
     */
    TypeRapportJasper getTypeRapport() {
        return this.typeRapport;
    }

    /**
     * Getter sur commonParametersHelper.
     * @return Retourne le commonParametersHelper.
     */
    CommonParametersHelper getCommonParametersHelper() {
        return this.commonParametersHelper;
    }

    /**
     * Setter pour commonParametersHelper.
     * @param commonParametersHelper le commonParametersHelper à écrire.
     */
    public void setCommonParametersHelper(final CommonParametersHelper commonParametersHelper) {
        this.commonParametersHelper = commonParametersHelper;
    }

}
