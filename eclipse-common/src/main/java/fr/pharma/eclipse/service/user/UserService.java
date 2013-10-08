package fr.pharma.eclipse.service.user;

import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.user.UserSecurity;

/**
 * Interface de service des utilisateurs.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface UserService {
    /**
     * Méthode en charge de retouner le bean Personne correspondant à
     * l'utilisateur courant.
     * @return Personne.
     */
    Personne getPersonne();

    /**
     * Méthode en charge de retourner les informations de l'utilisateur connecté
     * présentes sur le jeton de sécurité.
     * @return User.
     */
    UserSecurity getUser();

    /**
     * Méthode en charge de passer l'utilisateur courant en administrateur.
     */
    void setAdministrateur();

    Boolean hasRole(String[] roles);
}
