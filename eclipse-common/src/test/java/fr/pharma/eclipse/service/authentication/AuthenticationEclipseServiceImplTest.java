package fr.pharma.eclipse.service.authentication;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;

import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.service.authentication.helper.AuthenticationHelper;

/**
 * Classe en charge de tester le service d'authentification pour Eclipse.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AuthenticationEclipseServiceImplTest {
    /**
     * AuthenticationEclipseServiceImpl à tester.
     */
    private AuthenticationEclipseServiceImpl helper;

    /**
     * Helper de Authentification mocké.
     */
    private AuthenticationHelper mockAuthenticationHelper;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.helper = new AuthenticationEclipseServiceImpl();
        this.mockAuthenticationHelper = Mockito.mock(AuthenticationHelper.class);
        this.helper.setHelper(this.mockAuthenticationHelper);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.helper = null;
        this.mockAuthenticationHelper = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.helper);
        Assert.assertNotNull(this.mockAuthenticationHelper);
    }

    /**
     * Méthode en charge de tester le chargement de User à partir du login.
     */
    @Test
    public void testLoadUserByUsernameKO1() {
        final String login = "login";
        final Personne personne = new Investigateur();
        Mockito.when(this.mockAuthenticationHelper.getPersonneEclipse(login)).thenReturn(personne);
        final UserDetails user = this.helper.loadUserByUsername(login);
        Assert.assertNull(user);
    }

    /**
     * Méthode en charge de tester le chargement de User à partir du login.
     */
    @Test
    public void testLoadUserByUsernameKO2() {
        final String login = "login";
        final Personne personne = new Investigateur();
        personne.setLogin(login);
        Mockito.when(this.mockAuthenticationHelper.getPersonneEclipse(login)).thenReturn(personne);
        final UserDetails user = this.helper.loadUserByUsername(login);
        Assert.assertNull(user);
    }

    /**
     * Méthode en charge de tester le chargement de User à partir du login.
     */
    @Test
    public void testLoadUserByUsernameKO3() {
        final String login = "login";
        Mockito.when(this.mockAuthenticationHelper.getPersonneEclipse(login)).thenReturn(null);
        final UserDetails user = this.helper.loadUserByUsername(login);
        Assert.assertNull(user);
    }

    /**
     * Méthode en charge de tester le chargement de User à partir du login.
     */
    @Test
    public void testLoadUserByUsernameOK() {
        final String login = "login";
        final Personne personne = new Investigateur();
        personne.setLogin(login);
        personne.setPassword("password");
        personne.setType(TypePersonne.INVESTIGATEUR);
        Mockito.when(this.mockAuthenticationHelper.getPersonneEclipse(login)).thenReturn(personne);
        final UserDetails user = this.helper.loadUserByUsername(login);
        Assert.assertNotNull(user);
    }

}
