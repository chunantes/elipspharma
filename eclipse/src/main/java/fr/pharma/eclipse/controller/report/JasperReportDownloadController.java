package fr.pharma.eclipse.controller.report;

import java.io.Serializable;
import java.util.Map;

import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.factory.primefaces.DefaultStreamedContentFactory;
import fr.pharma.eclipse.jasper.engine.manager.JasperReportBuildManager;
import fr.pharma.eclipse.jasper.exception.JasperReportBuildException;
import fr.pharma.eclipse.utils.Utils;

/**
 * Classe de contrôleur pour la gestion du download de rapports Jasper.
 
 * @version $Revision$ $Date$
 */
public class JasperReportDownloadController
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 8432728890802334950L;

    /**
     * Log.
     */
    private static final Logger LOG =
        LoggerFactory.getLogger(JasperReportDownloadController.class);

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
     * @return L'objet StreamedContent pour PrimeFaces.
     * @deprecated Seulement pour les premiers tests.
     */
    @Deprecated
    public StreamedContent downloadRapport()
    {
        final TypeRapportJasper typeRapport = TypeRapportJasper.FICHE_INFO_ESSAI;
        return this.downloadRapport(null,
                                    typeRapport);
    }

    /**
     * Méthode de téléchargement de rapport Jasper.
     * @param source Source de données servant de base au rapport.
     * @param typeRapport Type du rapport à générer et télécharger.
     * @return L'objet StreamedContent pour PrimeFaces.
     */
    public StreamedContent downloadRapport(final Object source,
                                           final TypeRapportJasper typeRapport)
    {
        final JasperReportBuildManager manager = this.buildManagers.get(typeRapport.name());
        try
        {
            final byte[] reportBytes = manager.build(source);
            final String reportName = manager.buildFileName(source);
            return this.streamedContentFactory.getInitializedObject(reportBytes,
                                                                    reportName);
        }
        catch (final JasperReportBuildException e)
        {
            return this.handleException(source,
                                        typeRapport,
                                        e);
        }
    }

    /**
     * Méthode en charge de traiter les exception levées lors de la tentative de download.
     * @param source Source de données servant de base au rapport.
     * @param typeRapport Type du rapport à générer et télécharger.
     * @param exc Exception.
     * @return StreamedContent à retourner.
     */
    private StreamedContent handleException(final Object source,
                                            final TypeRapportJasper typeRapport,
                                            final Exception exc)
    {
        JasperReportDownloadController.LOG
                .error(new StringBuilder("Erreur de download du rapport Jasper de type ")
                        .append(typeRapport)
                        .append(" avec la source de données ")
                        .append(source)
                        .append(" :\n")
                        .append(Utils.getStringStack(exc))
                        .toString());
        return this.streamedContentFactory.getInitializedObjectInError();
    }

    /**
     * Setter pour streamedContentFactory.
     * @param streamedContentFactory le streamedContentFactory à écrire.
     */
    public void setStreamedContentFactory(final DefaultStreamedContentFactory streamedContentFactory)
    {
        this.streamedContentFactory = streamedContentFactory;
    }

    /**
     * Getter sur streamedContentFactory.
     * @return Retourne le streamedContentFactory.
     */
    DefaultStreamedContentFactory getStreamedContentFactory()
    {
        return this.streamedContentFactory;
    }

    /**
     * Getter sur buildManagers.
     * @return Retourne le buildManagers.
     */
    Map<String, JasperReportBuildManager> getBuildManagers()
    {
        return this.buildManagers;
    }

    /**
     * Setter pour buildManagers.
     * @param buildManagers le buildManagers à écrire.
     */
    public void setBuildManagers(final Map<String, JasperReportBuildManager> buildManagers)
    {
        this.buildManagers = buildManagers;
    }

}
