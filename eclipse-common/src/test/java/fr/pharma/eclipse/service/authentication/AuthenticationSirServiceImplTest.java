package fr.pharma.eclipse.service.authentication;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;

import fr.pharma.eclipse.dao.sir.GenericSirDao;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.sir.PersonneSir;
import fr.pharma.eclipse.service.authentication.helper.AuthenticationHelper;

/**
 * Classe en charge de tester le service d'authentification pour SIR.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AuthenticationSirServiceImplTest {
    /**
     * AuthenticationSirServiceImpl à tester.
     */
    private AuthenticationSirServiceImpl helper;

    /**
     * Dao de gestion des personnes SIR mocké.
     */
    private GenericSirDao<PersonneSir> mockPersonneSirDao;

    /**
     * Helper de Authentification mocké.
     */
    private AuthenticationHelper mockAuthenticationHelper;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.helper = new AuthenticationSirServiceImpl();
        this.mockPersonneSirDao = Mockito.mock(GenericSirDao.class);
        this.helper.setPersonneSirDao(this.mockPersonneSirDao);
        this.mockAuthenticationHelper = Mockito.mock(AuthenticationHelper.class);
        this.helper.setHelper(this.mockAuthenticationHelper);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.helper = null;
        this.mockPersonneSirDao = null;
        this.mockAuthenticationHelper = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.helper);
        Assert.assertNotNull(this.mockPersonneSirDao);
        Assert.assertNotNull(this.mockAuthenticationHelper);
    }

    /**
     * Méthode en charge de tester la récupération d'une Personne SIR.
     */
    @Test
    public void testGetPersonneSirOK() {
        final String login = "login";
        final PersonneSir personneExpected = new PersonneSir();
        personneExpected.setId(1);
        personneExpected.setLogin(login);
        final List<PersonneSir> personnes = new ArrayList<PersonneSir>();
        personnes.add(personneExpected);
        Mockito.when(this.mockPersonneSirDao.getAll((SearchCriteria) Matchers.any())).thenReturn(personnes);
        final PersonneSir personneSir = this.helper.getPersonneSir(login);
        Assert.assertEquals(personneExpected, personneSir);
    }

    /**
     * Méthode en charge de tester la récupération d'une Personne SIR.
     */
    @Test
    public void testGetPersonneSirKO() {
        final String login = "login";
        final List<PersonneSir> personnes = new ArrayList<PersonneSir>();
        Mockito.when(this.mockPersonneSirDao.getAll((SearchCriteria) Matchers.any())).thenReturn(personnes);
        final PersonneSir personneSir = this.helper.getPersonneSir(login);
        Assert.assertNull(personneSir);
    }

    /**
     * Méthode en charge de tester le chargement de User à partir du login.
     */
    @Test
    public void testLoadUserByUsernameKO1() {
        final String login = "login";

        final PersonneSir personneSir = new PersonneSir();
        final List<PersonneSir> personnes = new ArrayList<PersonneSir>();
        personnes.add(personneSir);
        Mockito.when(this.mockPersonneSirDao.getAll((SearchCriteria) Matchers.any())).thenReturn(personnes);

        Mockito.when(this.mockAuthenticationHelper.getPersonneEclipse(login)).thenReturn(null);
        final UserDetails user = this.helper.loadUserByUsername(login);
        Assert.assertNull(user);
    }

    /**
     * Méthode en charge de tester le chargement de User à partir du login.
     */
    @Test
    public void testLoadUserByUsernameKO2() {
        final String login = "login";

        final List<PersonneSir> personnes = new ArrayList<PersonneSir>();
        Mockito.when(this.mockPersonneSirDao.getAll((SearchCriteria) Matchers.any())).thenReturn(personnes);

        Mockito.when(this.mockAuthenticationHelper.getPersonneEclipse(login)).thenReturn(null);
        final UserDetails user = this.helper.loadUserByUsername(login);
        Assert.assertNull(user);
    }

    /**
     * Méthode en charge de tester le chargement de User à partir du login.
     */
    @Test
    public void testLoadUserByUsernameKO3() {
        final String login = "login";

        final PersonneSir personneSir = new PersonneSir();
        personneSir.setLogin(login);
        personneSir.setPassword("password");
        final List<PersonneSir> personnes = new ArrayList<PersonneSir>();
        personnes.add(personneSir);
        Mockito.when(this.mockPersonneSirDao.getAll((SearchCriteria) Matchers.any())).thenReturn(personnes);

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

        final PersonneSir personneSir = new PersonneSir();
        personneSir.setLogin(login);
        personneSir.setPassword("password");
        final List<PersonneSir> personnes = new ArrayList<PersonneSir>();
        personnes.add(personneSir);
        Mockito.when(this.mockPersonneSirDao.getAll((SearchCriteria) Matchers.any())).thenReturn(personnes);

        final Personne personne = new Investigateur();
        personne.setLogin(login);
        personne.setType(TypePersonne.INVESTIGATEUR);
        Mockito.when(this.mockAuthenticationHelper.getPersonneEclipse(login)).thenReturn(personne);
        final UserDetails user = this.helper.loadUserByUsername(login);
        Assert.assertNotNull(user);
    }

}
