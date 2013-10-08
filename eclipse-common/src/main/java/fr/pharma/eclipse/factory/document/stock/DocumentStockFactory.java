package fr.pharma.eclipse.factory.document.stock;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.domain.model.stock.DocumentStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;

/**
 * Interface des fabriques de documents Stock.
 * @param <DOC> Type de document de produit créé.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface DocumentStockFactory<DOC extends DocumentStock> extends Serializable {
    /**
     * Méthode de création d'un nouveau document de Stock.
     * @param fichier Fichier importé par l'utilisateur.
     * @param mvtStock Le mouvement de stock auquel est rattaché le document.
     * @return Un nouveau document de stock.
     */
    DOC getInitializedObject(Fichier fichier,
                             MvtStock mvtStock);
}
