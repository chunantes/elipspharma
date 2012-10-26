package fr.pharma.eclipse.service.stock;

import java.util.List;

import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stock.ReceptionLot;
import fr.pharma.eclipse.domain.model.stock.ResultApprovisionnement;

/**
 * Interface de service de gestion des approvisionnements.
 
 * @version $Revision$ $Date$
 */
public interface ApprovisionnementService<MVT extends Approvisionnement>
    extends MvtStockService<MVT>
{
    /**
     * Méthode en charge de gérer l'enregistrement des approvisionnements.
     * @param receptionLots Liste de réceptions de lots.
     * @return Résultat de l'enregistrement d'approvisionnement.
     */
    ResultApprovisionnement save(final List<ReceptionLot> receptionLots);

}
