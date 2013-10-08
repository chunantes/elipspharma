package fr.pharma.eclipse.component.essai.helper;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.habilitation.Habilitation;

/**
 * Interface des classes en charge d'initialiser le droit d'une nouvelle
 * habilitation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface DroitHabilitationInitializer extends Serializable {
    /**
     * Initialise une habilitation.<br>
     * ATTENTION : Une personne doit être associée à l'habilitation.
     * @param habilitation Habilitation.
     */
    void initialize(Habilitation habilitation);
}
