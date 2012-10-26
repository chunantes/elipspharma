package fr.pharma.eclipse.service.authentication;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.dao.sir.GenericSirDao;
import fr.pharma.eclipse.domain.criteria.sir.PersonneSirSearchCriteria;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.sir.PersonneSir;
import fr.pharma.eclipse.domain.model.user.UserSecurity;
import fr.pharma.eclipse.service.authentication.helper.AuthenticationHelper;

/**
 * Classe métier de gestion d'authentification depuis SIR.
 
 * @version $Revision$ $Date$
 */
public class AuthenticationSirServiceImpl
    implements UserDetailsService, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -279737227442026722L;

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(AuthenticationSirServiceImpl.class);

    /**
     * Dao de gestion des personnes de SIR.
     */
    @Resource(name = "personneSirDao")
    private GenericSirDao<PersonneSir> personneSirDao;

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

        this.log.debug("Authentification par SIR.");
        final PersonneSir personneSir = this.getPersonneSir(login);
        final Personne personneEclipse = this.helper.getPersonneEclipse(login);

        this.log.debug("Personne SIR : "
                       + personneSir);
        this.log.debug("Personne Eclipse : "
                       + personneEclipse);

        // Personne présente dans SIR + Eclipse
        // Login + password récupéré depuis SIR
        // Authorities récupérées depuis Eclipse
        if (personneSir != null
            && StringUtils.isNotEmpty(personneSir.getPassword())
            && personneEclipse != null)
        {
            this.log.debug("Authentification PAR SIR Ok ");
            return new UserSecurity(personneSir.getLogin(),
                                    personneSir.getPassword(),
                                    personneSir.getSalt(),
                                    this.helper.getAuthorities(personneEclipse),
                                    personneEclipse.getId(),
                                    personneEclipse.getType().getRolePersonne(),
                                    personneEclipse.getIsAdmin());
        }
        else
        {
            this.log.debug("Authentification PAR SIR Echouée ");
            return null;
        }
    }

    /**
     * Méthode en charge de récupérer la personne dans SIR.
     * @param login Login.
     * @return Personne SIR.
     */
    protected PersonneSir getPersonneSir(final String login)
    {
        final PersonneSirSearchCriteria criteria = new PersonneSirSearchCriteria();
        criteria.setLogin(login);
        criteria.setStrictSearchLogin(Boolean.TRUE);

        final List<PersonneSir> personneSirs = this.personneSirDao.getAll(criteria);

        // Récupération OK dans SIR
        if (personneSirs.size() == 1)
        {
            return personneSirs.get(0);
        }
        else
        {
            return null;
        }
    }

    /**
     * Setter pour personneSirDao.
     * @param personneSirDao Le personneSirDao à écrire.
     */
    public void setPersonneSirDao(final GenericSirDao<PersonneSir> personneSirDao)
    {
        this.personneSirDao = personneSirDao;
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
