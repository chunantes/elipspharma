package fr.pharma.eclipse.predicate.alerte;

import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Predicat permettant de savoir si un essai est visible dans la gestion des
 * alertes.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AlerteEssaiVisiblePredicate implements Predicate {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate(final Object object) {
        // Essai visible dans les alertes si :
        // - Les alertes sont activées sur l'essai
        // - L'état actuel de l'essai est différent de Archivé
        final Essai essai = (Essai) object;
        final EtatEssai etat = essai.getEtat();

        return essai.getAlerteActive() && !EtatEssai.ARCHIVE.equals(etat);
    }
}
