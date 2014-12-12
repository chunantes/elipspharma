package fr.pharma.eclipse.predicate.stock;

import org.apache.commons.collections15.Predicate;

import fr.pharma.eclipse.domain.model.stock.LigneStock;

/**
 * Prédicat permettant de trouver les lignes stock dont la
 * "quantité en stock à la pharmacie" est > 0.
 * @author sebastien.helbert
 */
public class LigneStockPharmacieNonEpuisePredicate implements Predicate<LigneStock> {
    /**
     * @return true si la quantité pharmacie en stock de la ligne spécifiée est
     * > 0
     */
    @Override
    public boolean evaluate(final LigneStock ligneStock) {
        return (ligneStock != null) && (ligneStock.getQteEnStock() != null) && (ligneStock.getQtePharmacie() > 0);
    }
}
