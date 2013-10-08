package fr.pharma.eclipse.service.surcout.checker;

import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Interface commune des checker en charge d'appliquer des conditions pour le
 * traitement de frais fixe.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface SurcoutChecker {
    /**
     * Méthode en charge de checké la condition.
     * @param essai L'essai.
     * @return true si la condition est vérifiée.
     */
    boolean check(Essai essai);
}
