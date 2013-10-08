package fr.pharma.eclipse.service.stock;

import java.util.Calendar;
import java.util.List;

import fr.pharma.eclipse.domain.criteria.stock.ExtensionPeremptionSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stock.ReceptionLot;
import fr.pharma.eclipse.domain.model.stock.ResultApprovisionnement;

/**
 * Interface de service gérant les approvisionnements.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface ApprovisionnementService<MVT extends Approvisionnement> extends MvtStockService<MVT> {

    /**
     * Retourne true si l'essai en paramètre a déjà fait l'objet d'une
     * réception.
     * @param essai Essai.
     * @return true si l'essai en paramètre a déjà fait l'objet d'une réception.
     */
    boolean hasAlreadyApprovisionnement(final Essai essai);

    /**
     * Méthode en charge de gérer l'enregistrement des approvisionnements.
     * @param receptionLots Liste de réceptions de lots.
     * @return Résultat de l'enregistrement d'approvisionnement.
     */
    ResultApprovisionnement save(final List<ReceptionLot> receptionLots);

    /**
     * Récupère la liste des approvisionnements avec stock non nul.
     * @param extensionPeremptionSearchCriteria
     * @return Liste d'approvisionnement avec du stock.
     */
    List<MVT> getAllApprovisionnementAvecStockPositif(final ExtensionPeremptionSearchCriteria criteria);

    /**
     * Il faut historiser la dérniere date de péremption avant de la mettre à
     * jour. L'histoire est gardée dans un String avec une ligne par changement.
     * Nous mettons à jour aussi le boolean "extensionPeremption" si la nouvelle
     * date est après l'originale.
     */
    void updateDatePeremption(final Approvisionnement appro,
                              Calendar newDatePeremption);
}
