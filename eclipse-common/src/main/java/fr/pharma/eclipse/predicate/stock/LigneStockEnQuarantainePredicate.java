package fr.pharma.eclipse.predicate.stock;

import org.apache.commons.collections15.Predicate;

import fr.pharma.eclipse.domain.model.stock.LigneStock;

/**
 * Prédicat permettant de trouver les lignes stock dont le stockage est de type
 * "En quarantaine".
 * @author sebastien.helbert
 */
public class LigneStockEnQuarantainePredicate implements Predicate<LigneStock> {
    /**
     * @return true si le stockage de la ligne spécifiée est égal à
     * LigneStock.EN_QUARANTAINE
     */
    @Override
    public boolean evaluate(LigneStock ligneStock) {
        return ligneStock != null && LigneStock.EN_QUARANTAINE.equals(ligneStock.getStockage());
    }
}
