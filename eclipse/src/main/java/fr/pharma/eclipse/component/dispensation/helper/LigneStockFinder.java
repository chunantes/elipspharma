package fr.pharma.eclipse.component.dispensation.helper;

import fr.pharma.eclipse.component.stock.SortieManager;

/**
 * Interface commune définissant la recherche de lignes de stocks pour une
 * dispensation en fonction du type de dispensation (GLobale / Nominative).
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface LigneStockFinder {
    /**
     * Méthode en charge d'intiailiser les lignes de stocks dans le
     * SortieManager.
     * @param sortieManager Le sortieManager.
     */
    void initLignesStocks(final SortieManager sortieManager);
}
