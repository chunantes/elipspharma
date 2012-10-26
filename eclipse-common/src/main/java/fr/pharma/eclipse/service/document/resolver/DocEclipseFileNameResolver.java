package fr.pharma.eclipse.service.document.resolver;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.common.BeanParentDocument;
import fr.pharma.eclipse.domain.model.common.DocumentEclipse;

/**
 * Interface des classe en charge de résoudre la nom de l'objet File représentant un document
 * Eclipse.
 
 * @version $Revision$ $Date$
 */
public interface DocEclipseFileNameResolver
    extends Serializable
{
    /**
     * Méthode qui indique si le résolveur prend en charge un document.
     * @param doc Document à traiter.
     * @return True ssi le résolveur est capable de prendre en charge la résolution de nom du
     * document.
     */
    boolean supports(DocumentEclipse doc);

    /**
     * Méthode de résolution du nom de l'objet File d'un document Eclipse.
     * @param bean Objet métier porteur du document.
     * @param doc Document dont on souhaite résoudre le nom de l'objet File correspondant.
     * @return Le filename (complet) de l'objet File correspondant au document.
     */
    String resolve(BeanParentDocument bean,
                   DocumentEclipse doc);
}
