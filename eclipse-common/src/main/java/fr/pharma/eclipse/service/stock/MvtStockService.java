package fr.pharma.eclipse.service.stock;

import java.util.List;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Interface de service de gestion de mouvement de stock.
 
 * @version $Revision$ $Date$
 * @param <MVT> Bean Objet Mouvement de Stock.
 */
public interface MvtStockService<MVT extends MvtStock>
    extends GenericService<MVT>
{

    /**
     * Méthode en charge de faire une recherche sans appliquer la purge par habilitation.
     * @param criteria Critere de recherche.
     * @return Les resultats.
     */
    List<MVT> getAllWithoutPurge(SearchCriteria criteria);

}
