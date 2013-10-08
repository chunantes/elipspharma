package fr.pharma.eclipse.service.user.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import fr.pharma.eclipse.domain.enums.RolePersonne;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.user.UserSecurity;
import fr.pharma.eclipse.service.acteur.PersonneService;
import fr.pharma.eclipse.service.user.UserService;

/**
 * Classe d'implémentation du service de gestion des utilisateurs.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class UserServiceImpl implements UserService, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1424194309327221154L;

    /**
     * Service de gestion des personnes.
     */
    @Resource(name = "personneService")
    private PersonneService<Personne> personneService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Personne getPersonne() {
        final UserSecurity user = this.getUser();
        return this.personneService.get(user.getIdPersonne());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAdministrateur() {
        final UserSecurity user = this.getUser();
        user.setRole(RolePersonne.ADMIN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserSecurity getUser() {
        return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public Boolean hasRole(final String[] roles) {
        final List<String> rolesAsList = Arrays.asList(roles);
        for (final GrantedAuthority auth : this.getUser().getAuthorities()) {
            if (rolesAsList.contains(auth.getAuthority())) {
                return true;
            }
        }
        return false;
    }
    /**
     * Setter pour personneService.
     * @param personneService Le personneService à écrire.
     */
    public void setPersonneService(final PersonneService<Personne> personneService) {
        this.personneService = personneService;
    }
}
