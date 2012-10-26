package fr.pharma.eclipse.domain.model.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import fr.pharma.eclipse.domain.enums.RolePersonne;

/**
 * Bean représenant les informations de l'utilisateur courant (jeton de sécurité).
 
 * @version $Revision$ $Date$
 */
public class UserSecurity
    extends User
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5673880697824741362L;

    /**
     * Identifiant technique de la personne correspondant à l'utilisateur.
     */
    private Long idPersonne;

    /**
     * Role de l'utilisateur.
     */
    private RolePersonne role;

    /**
     * Booléen indiquant si l'utilisateur est un administrateur.
     */
    private Boolean isAdmin;

    /**
     * Salt de l'utilisateur.
     */
    private String salt;

    /**
     * Constructeur des informations du jeton de sécurité.
     * @param username Login de l'utilisateur.
     * @param password Password de l'utilisateur.
     * @param authorities Autorités contenant le rôle de l'utilisateur.
     * @param idPersonne Identifiant technique de la personne correspondant à l'utilisateur.
     * @param role Role de l'utilisateur.
     * @param isAdmin Booléen indiquant si l'utilisateur est administrateur.
     */
    public UserSecurity(
                        final String username,
                        final String password,
                        final String salt,
                        final Collection<? extends GrantedAuthority> authorities,
                        final Long idPersonne,
                        final RolePersonne role,
                        final Boolean isAdmin)
    {
        super(username,
              password,
              true,
              true,
              true,
              true,
              authorities);
        this.setIdPersonne(idPersonne);
        this.setRole(role);
        this.setIsAdmin(isAdmin);
        this.setSalt(salt);
    }

    /**
     * Getter pour idPersonne.
     * @return Le idPersonne
     */
    public Long getIdPersonne()
    {
        return this.idPersonne;
    }

    /**
     * Setter pour idPersonne.
     * @param idPersonne Le idPersonne à écrire.
     */
    public void setIdPersonne(final Long idPersonne)
    {
        this.idPersonne = idPersonne;
    }

    /**
     * Getter pour role.
     * @return Le role
     */
    public RolePersonne getRole()
    {
        return this.role;
    }

    /**
     * Setter pour role.
     * @param role Le role à écrire.
     */
    public void setRole(final RolePersonne role)
    {
        this.role = role;
    }

    /**
     * Getter pour isAdmin.
     * @return Le isAdmin
     */
    public Boolean getIsAdmin()
    {
        return this.isAdmin;
    }

    /**
     * Setter pour isAdmin.
     * @param isAdmin Le isAdmin à écrire.
     */
    public void setIsAdmin(final Boolean isAdmin)
    {
        this.isAdmin = isAdmin;
    }

    /**
     * Getter pour salt.
     * @return Le salt
     */
    public String getSalt()
    {
        return this.salt;
    }

    /**
     * Setter pour salt.
     * @param salt Le salt à écrire.
     */
    public void setSalt(final String salt)
    {
        this.salt = salt;
    }

}
