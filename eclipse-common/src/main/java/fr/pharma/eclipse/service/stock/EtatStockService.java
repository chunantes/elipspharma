package fr.pharma.eclipse.service.stock;

import java.util.List;

import fr.pharma.eclipse.domain.criteria.stock.StockSearchCriteria;
import fr.pharma.eclipse.domain.model.stock.EtatStock;

/**
 * Interface de service d'état de stock.
 
 * @version $Revision$ $Date$
 */
public interface EtatStockService
{
    /**
     * Méthode en charge de retourner les lignes d'état de stock en fonction d'un critère de
     * recherche.
     * @param criteria Critère de recherche.
     * @return Lignes d'état de stock.
     */
    List<EtatStock> getLinesEtatStock(final StockSearchCriteria criteria);

}
