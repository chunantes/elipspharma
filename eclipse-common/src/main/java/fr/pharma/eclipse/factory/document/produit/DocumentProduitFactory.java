package fr.pharma.eclipse.factory.document.produit;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.domain.model.produit.DocumentProduit;
import fr.pharma.eclipse.domain.model.produit.Produit;

/**
 * Interface des fabriques de documents Produit.
 * @param <DOC> Type de document de produit créé.
 
 * @version $Revision$ $Date$
 */
public interface DocumentProduitFactory<DOC extends DocumentProduit>
    extends Serializable
{
    /**
     * Méthode de création d'un nouveau document de Produit.
     * @param fichier Fichier importé par l'utilisateur.
     * @param produit Le produit auquel est rattaché le document.
     * @return Un nouveau document de produit.
     */
    DOC getInitializedObject(Fichier fichier,
                             Produit produit);
}
