package fr.pharma.eclipse.factory.document.essai;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.domain.model.essai.DocumentEssai;

/**
 * Interface des fabriques de documents Essai.
 * @param <DOC> Type de document d'essai créé.
 * @param <PARENT> Type de l'objet porteur du document.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface DocumentEssaiFactory<DOC extends DocumentEssai, PARENT extends BeanObject> extends Serializable {
    /**
     * Méthode de création d'un nouveau document d'essai.
     * @param parent Object parent du document.
     * @param fichier Fichier importé par l'utilisateur.
     * @param commentaire Commentaire.
     * @return Un nouveau document d'essai.
     */
    DOC getInitializedObject(PARENT parent,
                             Fichier fichier,
                             String commentaire);

    /**
     * Méthode de création d'un nouveau document d'essai.
     * @param parent Object parent du document.
     * @param fichier Fichier importé par l'utilisateur.
     * @return Un nouveau document d'essai.
     */
    DOC getInitializedObject(final PARENT parent,
                             final Fichier fichier);
}
