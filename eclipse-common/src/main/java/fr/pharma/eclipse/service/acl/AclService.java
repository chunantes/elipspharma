package fr.pharma.eclipse.service.acl;

import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Interface de gestion des ACLs.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface AclService {

    /**
     * Méthode en charge de mettre à jour les acls concernant les pharmacies
     * pour une personne.
     * @param personne Personne.
     */
    void updateAclsPharmacies(final Personne personne);

    /**
     * Méthode en charge de mettre à jour les acls concernant les essais pour
     * une personne.
     * @param personne Personne.
     */
    void updateAclsEssais(final Personne personne);

    /**
     * Méthode en charge de supprimer les alcs concernant les pharmacies d'une
     * personne.
     * @param personne Personne.
     */
    void removeAclsPharmacies(final Personne personne);

    /**
     * Méthode en charge de mettre à jour les acls concernant les essais pour un
     * essai.
     * @param essai Essai.
     */
    void updateAclsEssais(final Essai essai);

}
