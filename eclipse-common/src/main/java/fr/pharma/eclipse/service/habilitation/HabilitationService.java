package fr.pharma.eclipse.service.habilitation;

import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Interface de service de gestion des habilitations.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface HabilitationService extends GenericService<Habilitation> {
    /**
     * Méthode en charge de mettre à jour la table habilitation pour une personne
     * pour une personne.
     * @param personne Personne.
     */
    public void updateHabilitationPersonne(final Personne personne);
}
