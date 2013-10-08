package fr.pharma.eclipse.service.dispensation;

import java.util.List;

import fr.pharma.eclipse.domain.criteria.dispensation.DispensationSearchCriteria;
import fr.pharma.eclipse.domain.dto.DispensationDTO;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.stock.Sortie;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Interface du service Dispensaiton.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface DispensationService extends GenericService<Dispensation> {

    /**
     * Méthode en charge de générer le numéro d'ordonnancier.
     * @param object La dispensation.
     * @return la dispensation.
     */
    Dispensation genererNumOrdonnancier(final Dispensation object);

    Dispensation dispenser(final Dispensation object,
                           final List<Sortie> sorties);

    void validRemoveProduitPrescrit(final ProduitPrescrit p);

    /**
     * Méthode de chargement des associations de dispensation (prescription /
     * inclusion / patient).
     * @param d Dispensation lazy chargée.
     * @return Dispensation prête pour une exploitation côté IHM (liens
     * chargés).
     */
    Dispensation loadDispensation(final Dispensation d);

    /**
     * Méthode en charge de retourner les dispensations associées au critère de
     * recherche.
     * @param criteria Critère de recherche.
     * @return Liste des dispensations.
     */
    List<DispensationDTO> retrieveResults(final DispensationSearchCriteria criteria);

}
