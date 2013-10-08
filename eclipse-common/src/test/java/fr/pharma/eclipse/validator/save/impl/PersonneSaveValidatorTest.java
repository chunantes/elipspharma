package fr.pharma.eclipse.validator.save.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.acteur.PersonneService;

/**
 * Classe en charge de tester le validator de sauvegarde de Personne.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PersonneSaveValidatorTest {
    /**
     * PersonneSaveValidator à tester.
     */
    private PersonneSaveValidator validator;

    /**
     * Service de gestion de personne mocké.
     */
    private PersonneService<Personne> mockPersonneService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.validator = new PersonneSaveValidator();
        this.mockPersonneService = Mockito.mock(PersonneService.class);
        this.validator.setPersonneService(this.mockPersonneService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.validator = null;
        this.mockPersonneService = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.validator);
        Assert.assertNotNull(this.mockPersonneService);
    }

    /**
     * Méthode en charge de tester la validation OK.
     */
    @Test
    public void testValidateOK1() {
        final Personne personne = new Investigateur();
        try {
            final List<Personne> personnes = new ArrayList<Personne>();
            Mockito.when(this.mockPersonneService.getAll((SearchCriteria) Matchers.any())).thenReturn(personnes);
            this.validator.validate(personne);
        } catch (final ValidationException e) {
            Assert.fail("Exception not expected");
        }
    }

    /**
     * Méthode en charge de tester la validation OK.
     */
    @Test
    public void testValidateOK2() {
        final Personne personne = new Investigateur();
        personne.setId(1L);
        try {
            final List<Personne> personnes = new ArrayList<Personne>();
            final Personne pers1 = new Investigateur();
            pers1.setId(1L);
            personnes.add(pers1);
            Mockito.when(this.mockPersonneService.getAll((SearchCriteria) Matchers.any())).thenReturn(personnes);
            this.validator.validate(personne);
        } catch (final ValidationException e) {
            Assert.fail("Exception not expected");
        }
    }

    /**
     * Méthode en charge de tester la validation KO.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKO() {
        final Personne personne = new Investigateur();
        personne.setId(1L);
        final List<Personne> personnes = new ArrayList<Personne>();
        final Personne pers1 = new Investigateur();
        pers1.setId(2L);
        personnes.add(pers1);
        Mockito.when(this.mockPersonneService.getAll((SearchCriteria) Matchers.any())).thenReturn(personnes);
        this.validator.validate(personne);
    }

    /**
     * Méthode en charge de tester la validation KO.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKOPassword() {
        final List<Personne> personnes = new ArrayList<Personne>();
        final Personne personne = new Investigateur();
        personne.setId(1L);
        personne.setPassword("password");
        personnes.add(personne);
        Mockito.when(this.mockPersonneService.getAll((SearchCriteria) Matchers.any())).thenReturn(personnes);
        this.validator.validate(personne);
    }

    /**
     * Méthode en charge de tester la validation OK au niveau password.
     */
    public void testValidateOKPassword() {
        final List<Personne> personnes = new ArrayList<Personne>();
        final Personne personne = new Investigateur();
        personne.setId(1L);
        personne.setPassword("password");
        personne.setConfirmPassword("password");
        personnes.add(personne);
        Mockito.when(this.mockPersonneService.getAll((SearchCriteria) Matchers.any())).thenReturn(personnes);
        try {
            this.validator.validate(personne);
        } catch (final ValidationException e) {
            Assert.fail("Exception not expected");
        }
    }

}
