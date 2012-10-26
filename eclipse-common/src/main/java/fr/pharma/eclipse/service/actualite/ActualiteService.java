package fr.pharma.eclipse.service.actualite;

import java.util.List;

import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Interface de gestion des actualités.
 
 * @version $Revision$ $Date$
 */
public interface ActualiteService
{
    /**
     * Méthode en charge de retourner les derniers essais créés.
     * @return Derniers essais créés.
     */
    List<Essai> getLastEssais();

}
