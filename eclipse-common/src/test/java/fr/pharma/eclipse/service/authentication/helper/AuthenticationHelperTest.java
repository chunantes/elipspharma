package fr.pharma.eclipse.service.authentication.helper;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.security.core.GrantedAuthority;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.enums.TypePharmacien;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester la classe helper de Authentication.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AuthenticationHelperTest {

    /**
     * AuthenticationHelper à tester.
     */
    private AuthenticationHelper helper;

    /**
     * Service de gestion des personnes mocké.
     */
    private GenericService<Personne> mockPersonneService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.helper = new AuthenticationHelper();
        this.mockPersonneService = Mockito.mock(GenericService.class);
        this.helper.setPersonneService(this.mockPersonneService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.helper = null;
        this.mockPersonneService = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.helper);
        Assert.assertNotNull(this.mockPersonneService);
    }

    /**
     * Méthode en charge de tester la récupération d'une Personne Eclipse.
     */
    @Test
    public void testGetPersonneEclipseOK() {
        final String login = "login";
        final Personne personneExpected = new Investigateur();
        personneExpected.setId(1L);
        personneExpected.setLogin(login);
        final List<Personne> personnes = new ArrayList<Personne>();
        personnes.add(personneExpected);
        Mockito.when(this.mockPersonneService.getAll((SearchCriteria) Matchers.any())).thenReturn(personnes);
        final Personne personneEclipse = this.helper.getPersonneEclipse(login);
        Assert.assertEquals(personneExpected, personneEclipse);
    }

    /**
     * Méthode en charge de tester la récupération d'une Personne Eclipse.
     */
    @Test
    public void testGetPersonneEclipseKO() {
        final String login = "login";
        final List<Personne> personnes = new ArrayList<Personne>();
        Mockito.when(this.mockPersonneService.getAll((SearchCriteria) Matchers.any())).thenReturn(personnes);
        final Personne personneEclipse = this.helper.getPersonneEclipse(login);
        Assert.assertNull(personneEclipse);
    }

    /**
     * Méthode en charge de tester la récupération des authorities d'une
     * Personne.
     */
    @Test
    public void testGetAuthorities() {
        final Personne personne = new Investigateur();
        personne.setType(TypePersonne.INVESTIGATEUR);
        final List<GrantedAuthority> authorities = this.helper.getAuthorities(personne);
        Assert.assertNotNull(authorities);
        Assert.assertEquals(1, authorities.size());
        final GrantedAuthority authority = authorities.get(0);
        Assert.assertEquals(EclipseConstants.ROLE + TypePersonne.INVESTIGATEUR.name(), authority.getAuthority());
    }

    /**
     * Méthode en charge de tester la récupération des authorities d'une
     * Personne de type pharmacien.
     */
    @Test
    public void testGetAuthoritiesPharmacien() {
        final Pharmacien personne = new Pharmacien();
        personne.setType(TypePersonne.PHARMACIEN);
        personne.setTypePharmacien(TypePharmacien.TITULAIRE);
        final List<GrantedAuthority> authorities = this.helper.getAuthorities(personne);
        Assert.assertNotNull(authorities);
        Assert.assertEquals(1, authorities.size());
        final GrantedAuthority authority = authorities.get(0);
        Assert.assertEquals(EclipseConstants.ROLE + TypePersonne.PHARMACIEN.name() + EclipseConstants.UNDERSCORE + TypePharmacien.TITULAIRE.name(), authority.getAuthority());
    }

}
