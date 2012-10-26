package fr.pharma.eclipse.validator.stock;

import java.util.List;

import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.ReceptionLot;
import fr.pharma.eclipse.domain.model.stock.Sortie;

/**
 * Interface de validator de stock.
 
 * @version $Revision$ $Date$
 */
public interface StockValidator
{

    /**
     * Méthode en charge de valider les lignes de stock d'une sortie.
     * @param sorties Liste de sorties.
     * @param currentSortie Sortie courante à valider.
     */
    void validateLignesStockSortie(final List<Sortie> sorties,
                                   final Sortie currentSortie);

    /**
     * Méthode en charge de vérifier une liste de lignes de stock par rapport à la quantite.
     * @param lignesStock Lignes de stock à valider.
     */
    void validateQteLignesStock(final List<LigneStock> lignesStock);

    /**
     * Méthode en charge de valider une réception de lot.
     * @param receptionLot Réception de lot.
     */
    void validateReceptionLot(final ReceptionLot receptionLot);

}
