package fr.pharma.eclipse.service.alerte.builder;

import java.util.List;

import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Interface de build des alertes.
 
 * @version $Revision$ $Date$
 */
public interface AlerteBuilder
{
    /**
     * Méthode en charge de construire les alertes en prenant en compte les essais et les
     * pharmacies accessibles pour l'utilisateur.
     * @param essais Liste des essais accessibles à l'utilisateur.
     * @param pharmacies Liste des pharmacies accessibles à l'utilisateur.
     * @param alertes Liste des alertes à compléter.
     */
    void build(final List<Essai> essais,
               final List<Pharmacie> pharmacies,
               final List<Alerte> alertes);

}
