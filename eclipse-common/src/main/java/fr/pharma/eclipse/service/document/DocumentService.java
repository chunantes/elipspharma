package fr.pharma.eclipse.service.document;

import java.io.File;

import fr.pharma.eclipse.domain.model.common.BeanParentDocument;
import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Interface du service de gestion des documents.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface DocumentService extends GenericService<DocumentEclipse> {
    /**
     * Méthode en charge de créer un objet File à partir d'un document et de son
     * parent.
     * @param bean Bean porteur du document.
     * @param doc Document à télécharger.
     * @return L'objet File correspondant au document.
     */
    File getFile(final BeanParentDocument bean,
                 final DocumentEclipse doc);

    /**
     * Méthode en charge de stocker sur le disque un nouveau document.
     * @param bean Bean porteur du document.
     * @param doc Document objet.
     * @param fichier Fichier physique.
     */
    void saveOnDisk(final BeanParentDocument bean,
                    DocumentEclipse doc,
                    Fichier fichier);

    /**
     * Méthode qui indique si un document peut être téléchargé.
     * @param doc Document.
     * @return true ssi le fichier peut être téléchargé.
     */
    boolean canBeDownloaded(DocumentEclipse doc);
}
