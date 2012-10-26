package fr.pharma.eclipse.service.evenement;

import java.util.List;

import fr.pharma.eclipse.domain.criteria.evenement.EvenementSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Interface de service de gestion des événements.
 
 * @version $Revision$ $Date$
 */
public interface EvenementService
    extends GenericService<Evenement>
{

    /**
     * Méthode en charge de retourner les prochains événements à venir.
     * @return Evénements.
     */
    List<Evenement> getNextEvenements();

    /**
     * Méthode en charge de récupérer les évènement en courcicuitant la purge en fonction de
     * habilitations.
     * @return les évènement en courcicuitant la purge en fonction de habilitations.
     */
    List<Evenement> getAllWithoutPurge(EvenementSearchCriteria criteria);

    /**
     * Retourne la visite de monitoring.
     * @param essai L'essai.
     * @return La viste de monitoring si elle existe.
     */
    Evenement getVisiteMonitoring(final Essai essai);
}
