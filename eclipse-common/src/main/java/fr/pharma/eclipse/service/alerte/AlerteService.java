package fr.pharma.eclipse.service.alerte;

import java.util.List;

import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Interface de service de gestion des alertes.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface AlerteService {
    /**
     * Méthode en charge de retourner les alertes.
     * @return Liste des alertes.
     */
    List<Alerte> getAlertes();

    /**
     * Méthode en charge de retourner les alertes relatives à un essai.
     * @param essai Essai.
     * @return Liste des alertes de l'essai.
     */
    List<Alerte> getAlertes(final Essai essai);
}
