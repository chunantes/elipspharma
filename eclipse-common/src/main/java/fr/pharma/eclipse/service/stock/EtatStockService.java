package fr.pharma.eclipse.service.stock;

import java.util.List;

import fr.pharma.eclipse.domain.criteria.stock.StockSearchCriteria;
import fr.pharma.eclipse.domain.model.stock.EtatStock;

/**
 * Interface de service d'état de stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface EtatStockService {
    /**
     * Méthode en charge de retourner les lignes d'état de stock en fonction
     * d'un critère de recherche.
     * @param criteria Critère de recherche.
     * @return Lignes d'état de stock.
     */
    @Deprecated
    List<EtatStock> getLinesEtatStock(final StockSearchCriteria criteria);

    /**
     * Méthode en charge de retourner les lignes d'état de stock en fonction
     * d'un critère de recherche.
     * @param criteria Critère de recherche.
     * @param datesPeremptionFusionnees Indique si les lignes ayant des dates de
     * peremption differentes sont fusionnées : <br>
     * - true : les lignes de stock d'un produit sont regroupées par num lot +
     * num traitement + date peremption.<br>
     * - false : les lignes de stock d'un produit sont regroupées par num lot +
     * num traitement.
     * @return Lignes d'état de stock.
     */
    List<EtatStock> getLinesEtatStock(final StockSearchCriteria criteria,
                                      boolean datesPeremptionFusionnees);

}
