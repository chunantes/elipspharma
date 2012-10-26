package fr.pharma.eclipse.service.ordonnancier;

import java.util.Calendar;

import fr.pharma.eclipse.domain.criteria.ordonnancier.OrdonnancierSearchCriteria;
import fr.pharma.eclipse.domain.model.ordonnancier.Ordonnancier;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Interface de service de gestion des ordonnanciers.
 
 * @version $Revision$ $Date$
 */
public interface OrdonnancierService<BEAN extends Ordonnancier>
    extends GenericService<BEAN>
{

    /**
     * Méthode en charge de récupérer la date de début du prochain ordonnancier de la pharmacie.
     * La date de début est Date de fin du précédent + 1 ou 01/01/2011 si pas de précédent.
     * @param pharmacie Pharmacie.
     * @return Date de début du prochain ordonnancier.
     */
    Calendar getDateDebut(final Pharmacie pharmacie);

    /**
     * Méthode en charge de récupérer la date de fin du prochain ordonnancier.<br />
     * La date de fin est J - 1.
     * @return Date de fin du prochain ordonnancier.
     */
    Calendar getDateFin();

    /**
     * Méthode en charge de calculer l'ordonnancier de dispensation pour une pharmacie.
     * @param criteria Critère de recherche portant les informations pour le calcul.
     * @return Nouvel ordonnancier.
     */
    BEAN calculerOrdonnancier(final OrdonnancierSearchCriteria criteria);

}
