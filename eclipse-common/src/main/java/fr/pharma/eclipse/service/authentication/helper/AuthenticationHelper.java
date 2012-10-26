package fr.pharma.eclipse.service.authentication.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import fr.pharma.eclipse.domain.criteria.acteur.PersonneSearchCriteria;
import fr.pharma.eclipse.domain.enums.RolePersonne;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe de Helper pour l'authentification des utilisateurs.
 
 * @version $Revision$ $Date$
 */
public class AuthenticationHelper
    implements Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1652717881817513631L;

    /**
     * Service de gestion des personnes de Eclipse.
     */
    @Resource(name = "personneService")
    private GenericService<Personne> personneService;

    /**
     * Méthode en charge de récupérer la personne dans Eclipse depuis un login.
     * @param login Login.
     * @return Personne.
     */
    public Personne getPersonneEclipse(final String login)
    {
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        criteria.setLogin(login);

        final List<Personne> personnes = this.personneService.getAll(criteria);

        // Récupération OK dans Eclipse
        if (personnes.size() == 1)
        {
            return personnes.get(0);
        }
        else
        {
            return null;
        }
    }

    /**
     * Méthode en charge de construire la liste des authorities d'une personne.
     * @param personne Personne.
     * @return Authorities de la personne (seulement 1 rôle).
     */
    public List<GrantedAuthority> getAuthorities(final Personne personne)
    {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        final String role = this.buildRole(personne);
        authorities.add(new GrantedAuthorityImpl(EclipseConstants.ROLE
                                                 + role));
        return authorities;
    }

    /**
     * Méthode en charge de gérer la construction du rôle d'une personne.
     * @param personne Personne.
     * @return Rôle de la personne.
     */
    protected String buildRole(final Personne personne)
    {
        String role = personne.getType().getRolePersonne().name();

        // Dans le cas d'un rôle de type pharmacien
        if (RolePersonne.PHARMACIEN.name().equals(role))
        {
            final Pharmacien pharmacien = (Pharmacien) personne;
            // Concaténation du role et du type de pharmacien
            role += EclipseConstants.UNDERSCORE
                    + pharmacien.getTypePharmacien().name();
        }

        return role;
    }

    /**
     * Setter pour personneService.
     * @param personneService Le personneService à écrire.
     */
    public void setPersonneService(final GenericService<Personne> personneService)
    {
        this.personneService = personneService;
    }

}
