package fr.pharma.eclipse.service.stock;

import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Interface de service de gestion de mouvement de stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 * @param <MVT> Bean Objet Mouvement de Stock.
 */
public interface MvtStockService<MVT extends MvtStock> extends GenericService<MVT> {

}
