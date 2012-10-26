package fr.pharma.eclipse.factory.primefaces;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.SortedMap;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.utils.file.FileHelper;

/**
 * Fabrique d'objets {@link DefaultStreamedContent} pour les composants Primefaces de gestion de
 * fichiers.
 
 * @version $Revision$ $Date$
 */
public class DefaultStreamedContentFactory
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 306456815792784469L;

    /**
     * Log.
     */
    private static final Logger LOG =
        LoggerFactory.getLogger(DefaultStreamedContentFactory.class);

    /**
     * Helper pour la gestion des fichiers.
     */
    private FileHelper fileHelper;

    /**
     * Dictionnaire de correspondances extension/type du fichier dans la response.
     */
    private SortedMap<String, String> dicoExtensions;

    /**
     * Méthode de création d'un nouvel objet DefaultStreamedContent.
     * @param stream Flux correspondant au fichier.
     * @param fileName Nom du fichier à exporter (avec extension).
     * @return Un objet DefaultStreamedContent.
     */
    public DefaultStreamedContent getInitializedObject(final InputStream stream,
                                                       final String fileName)
    {
        return new DefaultStreamedContent(stream,
                                          this.getApplicationType(fileName),
                                          fileName);
    }

    /**
     * Méthode de création d'un nouvel objet DefaultStreamedContent.
     * @param bytes Tableau de bytes représentant le flux à exporter.
     * @param fileName Nom du fichier à exporter (avec extension).
     * @return Un objet DefaultStreamedContent.
     */
    public DefaultStreamedContent getInitializedObject(final byte[] bytes,
                                                       final String fileName)
    {
        final InputStream stream = new ByteArrayInputStream(bytes);
        return new DefaultStreamedContent(stream,
                                          this.getApplicationType(fileName),
                                          fileName);
    }

    /**
     * Méthode de création d'un objet DefaultStreamedContent à renvoyer lorsque le traitement ne
     * s'est pas bien déroulé.
     * @return Un objet DefaultStreamedContent.
     */
    public DefaultStreamedContent getInitializedObjectInError()
    {
        return new DefaultStreamedContent(new ByteArrayInputStream(new byte[0]));
    }

    /**
     * Méthode en charge de récupérer le type d'application à indiquer dans la response lors du
     * téléchargement de fichier, à partir du nom du fichier.
     * @param filename Nom du fichier à exporter.
     * @return Le type d'application à indiquer dans la response.
     */
    private String getApplicationType(final String filename)
    {
        final String applicationType =
            this.dicoExtensions.get(this.fileHelper.getExtension(filename));
        if (applicationType == null)
        {
            DefaultStreamedContentFactory.LOG.warn(new StringBuilder("[getApplicationType] ")
                    .append("Aucune correspondance trouvée ")
                    .append("dans le dictionnaire pour le fichier '")
                    .append(filename)
                    .append("'.")
                    .toString());
            return StringUtils.EMPTY;
        }
        return applicationType;
    }

    /**
     * Getter sur dicoExtensions.
     * @return Retourne le dicoExtensions.
     */
    SortedMap<String, String> getDicoExtensions()
    {
        return this.dicoExtensions;
    }

    /**
     * Setter pour dicoExtensions.
     * @param dicoExtensions le dicoExtensions à écrire.
     */
    public void setDicoExtensions(final SortedMap<String, String> dicoExtensions)
    {
        this.dicoExtensions = dicoExtensions;
    }

    /**
     * Getter sur fileHelper.
     * @return Retourne le fileHelper.
     */
    FileHelper getFileHelper()
    {
        return this.fileHelper;
    }

    /**
     * Setter pour fileHelper.
     * @param fileHelper le fileHelper à écrire.
     */
    public void setFileHelper(final FileHelper fileHelper)
    {
        this.fileHelper = fileHelper;
    }

}
