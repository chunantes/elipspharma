package fr.pharma.eclipse.service.user.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import fr.pharma.eclipse.domain.enums.RolePersonne;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.user.UserSecurity;
import fr.pharma.eclipse.service.acteur.PersonneService;

/**
 * Classe en charge de tester le service de gestion des utilisateurs.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class UserServiceImplTest {
    /**
     * UserServiceImpl à tester.
     */
    private UserServiceImpl userServiceImpl;

    /**
     * Service de gestion des personnes.
     */
    private PersonneService<Personne> mockPersonneService;

    /**
     * UserSecurity.
     */
    private UserSecurity mockUser;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.userServiceImpl = new UserServiceImpl();

        final SecurityContext mockContext = Mockito.mock(SecurityContext.class);
        final Authentication mockAuthentication = Mockito.mock(Authentication.class);
        this.mockUser = Mockito.mock(UserSecurity.class);
        Mockito.when(mockContext.getAuthentication()).thenReturn(mockAuthentication);
        Mockito.when(mockAuthentication.getPrincipal()).thenReturn(this.mockUser);
        Mockito.when(this.mockUser.getUsername()).thenReturn("login");
        Mockito.when(this.mockUser.getIdPersonne()).thenReturn(1L);
        SecurityContextHolder.setContext(mockContext);

        this.mockPersonneService = Mockito.mock(PersonneService.class);
        this.userServiceImpl.setPersonneService(this.mockPersonneService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.userServiceImpl = null;
        this.mockPersonneService = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.userServiceImpl);
        Assert.assertNotNull(this.mockPersonneService);
    }

    /**
     * Méthode en charge de tester la récupération du bean Personne
     * correspondant à l'utilisateur connecté.
     */
    @Test
    public void testGetPersonneOK() {
        final Personne personneExpected = new Investigateur();
        personneExpected.setId(1L);
        Mockito.when(this.mockPersonneService.get(1L)).thenReturn(personneExpected);
        final Personne result = this.userServiceImpl.getPersonne();
        Assert.assertEquals(personneExpected, result);
    }

    /**
     * Méthode en charge de tester la récupération du bean Personne
     * correspondant à l'utilisateur connecté.
     */
    @Test
    public void testGetPersonneKO() {
        Mockito.when(this.mockPersonneService.get(1L)).thenReturn(null);
        final Personne result = this.userServiceImpl.getPersonne();
        Assert.assertNull(result);
    }

    /**
     * Méthode en charge de tester le passage en admiistrateur.
     */
    @Test
    public void testSetAministrateur() {
        this.userServiceImpl.setAdministrateur();
        Mockito.verify(this.mockUser).setRole(RolePersonne.ADMIN);
    }
}
