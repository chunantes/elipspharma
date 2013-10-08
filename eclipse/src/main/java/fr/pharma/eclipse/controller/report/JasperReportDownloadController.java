package fr.pharma.eclipse.controller.report;

import java.io.Serializable;
import java.util.Map;

import org.primefaces.model.StreamedContent;

import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.factory.primefaces.DefaultStreamedContentFactory;
import fr.pharma.eclipse.jasper.engine.manager.JasperReportBuildManager;
import fr.pharma.eclipse.jasper.exception.JasperReportBuildException;

/**
 * Classe de contrôleur pour la gestion du download de rapports Jasper.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JasperReportDownloadController implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 8432728890802334950L;

    /**
     * Fabrique des objets StreamedContent.
     */
    private DefaultStreamedContentFactory streamedContentFactory;

    /**
     * Map des managers de génération de rapport Jasper par type de rapport.
     */
    private Map<String, JasperReportBuildManager> buildManagers;

    /**
     * Méthode de téléchargement de rapport Jasper.
     * @param source Source de données servant de base au rapport.
     * @param typeRapport Type du rapport à générer et télécharger.
     * @return L'objet StreamedContent pour PrimeFaces.
     * @throws JasperReportBuildException
     */
    public StreamedContent downloadRapport(final Object source,
                                           final TypeRapportJasper typeRapport) throws JasperReportBuildException {
        final JasperReportBuildManager manager = this.buildManagers.get(typeRapport.name());
        final byte[] reportBytes = manager.build(source);
        final String reportName = manager.buildFileName(source);
        return this.streamedContentFactory.getInitializedObject(reportBytes, reportName);
    }

    /**
     * Setter pour streamedContentFactory.
     * @param streamedContentFactory le streamedContentFactory à écrire.
     */
    public void setStreamedContentFactory(final DefaultStreamedContentFactory streamedContentFactory) {
        this.streamedContentFactory = streamedContentFactory;
    }

    /**
     * Getter sur streamedContentFactory.
     * @return Retourne le streamedContentFactory.
     */
    DefaultStreamedContentFactory getStreamedContentFactory() {
        return this.streamedContentFactory;
    }

    /**
     * Getter sur buildManagers.
     * @return Retourne le buildManagers.
     */
    Map<String, JasperReportBuildManager> getBuildManagers() {
        return this.buildManagers;
    }

    /**
     * Setter pour buildManagers.
     * @param buildManagers le buildManagers à écrire.
     */
    public void setBuildManagers(final Map<String, JasperReportBuildManager> buildManagers) {
        this.buildManagers = buildManagers;
    }

}
