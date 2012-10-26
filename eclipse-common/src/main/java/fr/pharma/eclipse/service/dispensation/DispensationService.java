package fr.pharma.eclipse.service.dispensation;

import java.util.List;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Interface du service Dispensaiton.
 
 * @version $Revision$ $Date$
 */
public interface DispensationService
    extends GenericService<Dispensation>
{

    /**
     * Méthode en charge de rechercher les dispensation en courcicuitant le purge liée aux
     * habilitations.
     * @param criteria Critere de recherche.
     * @return La liste de dispensations.
     */
    List<Dispensation> getAllWithoutPurge(SearchCriteria criteria);

    /**
     * Méthode en charge de générer le numéro d'ordonnancier.
     * @param object La dispensation.
     * @return la dispensation.
     */
    Dispensation genererNumOrdonnancier(final Dispensation object);

}
