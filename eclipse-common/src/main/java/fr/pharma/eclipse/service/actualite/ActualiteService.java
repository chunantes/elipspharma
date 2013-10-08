package fr.pharma.eclipse.service.actualite;

import java.util.List;

import fr.pharma.eclipse.domain.model.actualite.Actualite;

/**
 * Interface de gestion des actualités.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface ActualiteService {
    /**
     * Méthode en charge de retourner les derniers essais créés pour la rubrique
     * Actualité.
     * @return Derniers essais créés.
     */
    List<Actualite> getLastEssais();

}
