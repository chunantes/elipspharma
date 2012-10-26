package fr.pharma.eclipse.service.authentication;

import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.user.UserSecurity;
import fr.pharma.eclipse.service.authentication.helper.AuthenticationHelper;

/**
 * Classe métier de gestion d'authentification (Gestion de l'authentification depuis SIR +
 * Eclipse).
 
 * @version $Revision$ $Date$
 */
public class AuthenticationEclipseServiceImpl
    implements UserDetailsService, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6219049986259997965L;

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(AuthenticationEclipseServiceImpl.class);

    /**
     * Classe Helper pour la partie Authentification.
     */
    @Resource(name = "authenticationHelper")
    private AuthenticationHelper helper;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String login)
        throws UsernameNotFoundException,
            DataAccessException
    {
        this.log.debug("Authentification par Eclipse");
        final Personne personneEclipse = this.helper.getPersonneEclipse(login);

        this.log.debug("Personne Eclispe : "
                       + personneEclipse);

        // Personne présente dans Eclipse
        if (personneEclipse != null
            && StringUtils.isNotEmpty(personneEclipse.getLogin())
            && StringUtils.isNotEmpty(personneEclipse.getPassword()))
        {
            this.log.debug("Authentification par Eclipse OK");

            return new UserSecurity(personneEclipse.getLogin(),
                                    personneEclipse.getPassword(),
                                    null,
                                    this.helper.getAuthorities(personneEclipse),
                                    personneEclipse.getId(),
                                    personneEclipse.getType().getRolePersonne(),
                                    personneEclipse.getIsAdmin());
        }
        else
        {
            this.log.debug("Authentification par Eclipse Echouée");
            return null;
        }
    }

    /**
     * Setter pour helper.
     * @param helper Le helper à écrire.
     */
    public void setHelper(final AuthenticationHelper helper)
    {
        this.helper = helper;
    }

}
