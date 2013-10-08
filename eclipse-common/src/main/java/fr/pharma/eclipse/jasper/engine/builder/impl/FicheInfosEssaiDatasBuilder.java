package fr.pharma.eclipse.jasper.engine.builder.impl;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.domain.jasper.model.common.JRBeanHeader;
import fr.pharma.eclipse.domain.jasper.model.fiche.essai.JRBeanFicheEssai;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.jasper.engine.builder.JasperReportDatasBuilder;
import fr.pharma.eclipse.jasper.engine.builder.helper.common.JRBeanHeaderBuilder;
import fr.pharma.eclipse.jasper.engine.factory.JRDataSourceFactory;
import fr.pharma.eclipse.jasper.engine.helper.ReportNameBuildHelper;
import fr.pharma.eclipse.jasper.engine.helper.SourceCheckingHandler;
import fr.pharma.eclipse.jasper.exception.JasperReportBuildException;
import fr.pharma.eclipse.jasper.utils.JasperUtils;
import fr.pharma.eclipse.service.habilitation.helper.HabilitationsHelper;

/**
 * Classe en charge de constuire les données pour le rapport Jasper de type
 * TypeRapportJasper.FICHE_INFO_ESSAI.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class FicheInfosEssaiDatasBuilder implements JasperReportDatasBuilder {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5298740608308317133L;

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
     * Helper pour la gestion des habilitations.
     */
    private HabilitationsHelper habilitationsHelper;

    /**
     * Helper pour la construction de l'en-tête.
     */
    private JRBeanHeaderBuilder headerBuilder;

    /**
     * {@inheritDoc}
     */
    @Override
    public void checkSource(final Object source) throws JasperReportBuildException {
        this.checkHandler.handleCheck(source != null, new StringBuilder("[FicheInfosEssaiDatasBuilder] ").append("La source est nulle.").toString());
        this.checkHandler.handleCheck(source instanceof Essai, new StringBuilder("[FicheInfosEssaiDatasBuilder] ").append("Le type attendu de la source est Essai (source: ")
                .append(source).append(").").toString());
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
    public JRDataSource buildDataSource(final Object source) {
        final Essai essai = (Essai) source;
        final Investigateur invPrincipal = this.habilitationsHelper.getInvestigateurPrincipal(essai);

        // Conctruction du bean.
        final JRBeanFicheEssai dataSource = new JRBeanFicheEssai();
        dataSource.setPromoteur(essai.getPromoteur().getRaisonSociale());
        dataSource.setCodeProtocole(essai.getNumInterne());
        dataSource.setNomUsuel(essai.getNom());

        dataSource.setInvestigateur(JasperUtils.makeLibelleInvestigateur(invPrincipal));

        // Construction de l'en-tête.
        final JRBeanHeader dataHeader = this.headerBuilder.build("Fiche d'information 'essais cliniques'", "Management", "Essais cliniques", "Pharmacie");
        dataSource.setHeader(dataHeader);

        // Retour
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
     * Getter sur habilitationsHelper.
     * @return Retourne le habilitationsHelper.
     */
    HabilitationsHelper getHabilitationsHelper() {
        return this.habilitationsHelper;
    }

    /**
     * Setter pour habilitationsHelper.
     * @param habilitationsHelper le habilitationsHelper à écrire.
     */
    public void setHabilitationsHelper(final HabilitationsHelper habilitationsHelper) {
        this.habilitationsHelper = habilitationsHelper;
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
}
