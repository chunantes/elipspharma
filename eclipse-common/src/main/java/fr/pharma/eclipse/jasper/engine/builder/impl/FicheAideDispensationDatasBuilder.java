package fr.pharma.eclipse.jasper.engine.builder.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.domain.jasper.model.common.JRBeanHeader;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.jasper.engine.builder.JasperReportDatasBuilder;
import fr.pharma.eclipse.jasper.engine.builder.helper.common.JRBeanHeaderBuilder;
import fr.pharma.eclipse.jasper.engine.factory.JRDataSourceFactory;
import fr.pharma.eclipse.jasper.engine.filler.JasperReportBeanFiller;
import fr.pharma.eclipse.jasper.engine.helper.ReportNameBuildHelper;
import fr.pharma.eclipse.jasper.engine.helper.SourceCheckingHandler;
import fr.pharma.eclipse.jasper.exception.JasperReportBuildException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Classe en charge de constuire les données pour le rapport Jasper de type
 * {@link TypeRapportJasper}.FICHE_AIDE_DISPENSATION.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class FicheAideDispensationDatasBuilder implements JasperReportDatasBuilder {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -6192935837802735312L;

    /**
     * Helper pour la création du nom de fichier.
     */
    private ReportNameBuildHelper reportNameHelper;

    /**
     * Fabrique de JRDataSource.
     */
    private JRDataSourceFactory jrDataSourceFactory;

    /**
     * Helper pour la levée d'exception JasperReportBuildException sur
     * condition.
     */
    private SourceCheckingHandler checkHandler;

    /**
     * Helper pour la construction de l'en-tête.
     */
    private JRBeanHeaderBuilder headerBuilder;

    /**
     * Fillers de la dataSource.
     */
    private List<JasperReportBeanFiller> dataSourceFillers;

    /**
     * {@inheritDoc}
     */
    @Override
    public JRDataSource buildDataSource(final Object source) {
        final Essai essai = (Essai) source;

        // Construction du bean.
        final JRBeanFicheAideDispensation dataSource = new JRBeanFicheAideDispensation();
        for (final JasperReportBeanFiller filler : this.dataSourceFillers) {
            filler.fill(essai, dataSource);
        }

        // Construction de l'en-tête.
        final String sousTitre = "Fiche de gestion et d'aide à la dispensation d'un médicament en essai clinique";
        final JRBeanHeader dataHeader = this.headerBuilder.build(sousTitre, "Management", "Essais cliniques", "Pharmacie");
        dataSource.setHeader(dataHeader);
        return this.jrDataSourceFactory.getInitializedObject(dataSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> buildParameters(final Object source) {
        return new HashMap<String, Object>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildReportName(final Object source,
                                  final TypeRapportJasper typeRapport) {
        final Essai essai = (Essai) source;
        final StringBuilder builder = new StringBuilder();
        this.reportNameHelper.addCommonNamePart(builder, typeRapport);
        this.reportNameHelper.addIdEssaiPart(builder, essai);
        this.reportNameHelper.addDatePart(builder);
        this.reportNameHelper.addCommonExtensionPart(builder, typeRapport);
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void checkSource(final Object source) throws JasperReportBuildException {
        this.checkHandler.handleCheck(source != null, new StringBuilder("[FicheAideDispensationDatasBuilder] ").append("La source est nulle.").toString());
        this.checkHandler.handleCheck(source instanceof Essai, new StringBuilder("[FicheAideDispensationDatasBuilder] ").append("Le type attendu de la source est Essai (source: ")
                .append(source).append(").").toString());
    }

    /**
     * Getter sur reportNameHelper.
     * @return Retourne le reportNameHelper.
     */
    ReportNameBuildHelper getReportNameHelper() {
        return this.reportNameHelper;
    }

    /**
     * Setter pour reportNameHelper.
     * @param reportNameHelper le reportNameHelper à écrire.
     */
    public void setReportNameHelper(final ReportNameBuildHelper reportNameHelper) {
        this.reportNameHelper = reportNameHelper;
    }

    /**
     * Getter sur jrDataSourceFactory.
     * @return Retourne le jrDataSourceFactory.
     */
    JRDataSourceFactory getJrDataSourceFactory() {
        return this.jrDataSourceFactory;
    }

    /**
     * Setter pour jrDataSourceFactory.
     * @param jrDataSourceFactory le jrDataSourceFactory à écrire.
     */
    public void setJrDataSourceFactory(final JRDataSourceFactory jrDataSourceFactory) {
        this.jrDataSourceFactory = jrDataSourceFactory;
    }

    /**
     * Getter sur checkHandler.
     * @return Retourne le checkHandler.
     */
    SourceCheckingHandler getCheckHandler() {
        return this.checkHandler;
    }

    /**
     * Setter pour checkHandler.
     * @param checkHandler le checkHandler à écrire.
     */
    public void setCheckHandler(final SourceCheckingHandler checkHandler) {
        this.checkHandler = checkHandler;
    }

    /**
     * Getter sur headerBuilder.
     * @return Retourne le headerBuilder.
     */
    JRBeanHeaderBuilder getHeaderBuilder() {
        return this.headerBuilder;
    }

    /**
     * Setter pour headerBuilder.
     * @param headerBuilder le headerBuilder à écrire.
     */
    public void setHeaderBuilder(final JRBeanHeaderBuilder headerBuilder) {
        this.headerBuilder = headerBuilder;
    }

    /**
     * Getter sur dataSourceFillers.
     * @return Retourne le dataSourceFillers.
     */
    public List<JasperReportBeanFiller> getDataSourceFillers() {
        return this.dataSourceFillers;
    }

    /**
     * Setter pour dataSourceFillers.
     * @param dataSourceFillers le dataSourceFillers à écrire.
     */
    public void setDataSourceFillers(final List<JasperReportBeanFiller> dataSourceFillers) {
        this.dataSourceFillers = dataSourceFillers;
    }

}
