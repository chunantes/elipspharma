package fr.pharma.eclipse.controller.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;

import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentStatique;
import fr.pharma.eclipse.domain.model.common.BeanParentDocument;
import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.factory.primefaces.DefaultStreamedContentFactory;
import fr.pharma.eclipse.factory.utils.IOStreamsFactory;
import fr.pharma.eclipse.service.document.DocumentService;
import fr.pharma.eclipse.utils.Utils;

/**
 * Classe de contrôleur pour la gestion du download de documents Eclipse depuis
 * le serveur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DocumentDownloadController implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 8432728890802334950L;

    /**
     * Log.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DocumentDownloadController.class);

    /**
     * Fabrique d'objets FileInputStream.
     */
    private IOStreamsFactory fileInStreamFactory;

    /**
     * Fabrique des objets StreamedContent.
     */
    private DefaultStreamedContentFactory streamedContentFactory;

    /**
     * Service de gestion des documents.
     */
    private DocumentService docService;

    /**
     * Bibliothèque contenant les chemins vers les documents statiques.
     */
    private DocumentLibrary library;

    /**
     * Méthode en charge de préparer le téléchargement d'un document Eclipse.
     * @param bean Bean porteur du document.
     * @param doc Document à télécharger.
     * @return Le StreamedContent nécessaire au composant fileDownload de
     * PrimeFaces.
     */
    public StreamedContent downloadDocument(final BeanParentDocument bean,
                                            final DocumentEclipse doc) {
        Assert.notNull(bean);
        final File file = this.docService.getFile(bean, doc);

        try {
            return this.streamedContentFactory.getInitializedObject(this.fileInStreamFactory.getInitializedInputStream(file), doc.getNomUtilisateur());
        } catch (final FileNotFoundException e) {
            return this.handleException(bean, doc, e);
        }
    }

    /**
     * Méthode en charge de préparer le téléchargement d'un fichier.
     * @param type Type du document à télécharger.
     * @return Le StreamedContent nécessaire au composant fileDownload de
     * PrimeFaces.
     */
    public StreamedContent downloadDocument(final TypeDocumentStatique type) {
        Assert.notNull(type);
        try {
            return this.streamedContentFactory.getInitializedObject(this.fileInStreamFactory.getInitializedInputStream(new File(this.library.getPathToDocument(type))),
                                                                    type.getNom());
        } catch (final FileNotFoundException e) {
            DocumentDownloadController.LOG.error(new StringBuilder("Erreur de download du document ").append(type.getLibelle()).toString());
            return this.streamedContentFactory.getInitializedObjectInError();
        }
    }

    /**
     * Méthode en charge de traiter les exception levées lors de la tentative de
     * download.
     * @param bean Bean porteur du document.
     * @param doc Document à télécharger.
     * @param exc Exception.
     * @return StreamedContent à retourner.
     */
    private StreamedContent handleException(final BeanParentDocument bean,
                                            final DocumentEclipse doc,
                                            final Exception exc) {
        DocumentDownloadController.LOG.error(new StringBuilder("Erreur de download du document de type ").append(doc.getTypeDocument()).append(" n° ").append(doc.getId())
                .append(" à partir du bean métier ").append(bean).append(" d'id ").append(bean.getId()).append(" : ").append(Utils.getStringStack(exc)).toString());
        return this.streamedContentFactory.getInitializedObjectInError();
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
     * Setter pour fileInStreamFactory.
     * @param fileInStreamFactory le fileInStreamFactory à écrire.
     */
    public void setFileInStreamFactory(final IOStreamsFactory fileInStreamFactory) {
        this.fileInStreamFactory = fileInStreamFactory;
    }

    /**
     * Getter sur fileInStreamFactory.
     * @return Retourne le fileInStreamFactory.
     */
    IOStreamsFactory getFileInStreamFactory() {
        return this.fileInStreamFactory;
    }

    /**
     * Getter sur docService.
     * @return Retourne le docService.
     */
    DocumentService getDocService() {
        return this.docService;
    }

    /**
     * Setter pour docService.
     * @param docService le docService à écrire.
     */
    public void setDocService(final DocumentService docService) {
        this.docService = docService;
    }

    /**
     * Getter pour library.
     * @return Le library
     */
    public DocumentLibrary getLibrary() {
        return this.library;
    }

    /**
     * Setter pour library.
     * @param library Le library à écrire.
     */
    public void setLibrary(final DocumentLibrary library) {
        this.library = library;
    }

}
