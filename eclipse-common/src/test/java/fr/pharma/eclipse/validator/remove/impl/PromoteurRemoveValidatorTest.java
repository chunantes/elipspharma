package fr.pharma.eclipse.validator.remove.impl;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.acteur.ArcPromoteur;
import fr.pharma.eclipse.domain.model.acteur.ContactPromoteur;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.essai.EssaiService;

/**
 * Classe en charge de tester le validator sur la suppression de Promoteur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PromoteurRemoveValidatorTest {
    /**
     * Validator de suppression à tester.
     */
    private PromoteurRemoveValidator validator;

    /**
     * Service de gestion des essais mocké.
     */
    private EssaiService mockEssaiService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.validator = new PromoteurRemoveValidator();
        this.mockEssaiService = Mockito.mock(EssaiService.class);
        this.validator.setEssaiService(this.mockEssaiService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.validator = null;
        this.mockEssaiService = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.validator);
        Assert.assertNotNull(this.mockEssaiService);
    }

    /**
     * Méthode en charge de tester la validation de la suppression de Promoteur.
     */
    @Test
    public void testValidateOK() {
        final Promoteur promoteur = new Promoteur();
        try {
            Mockito.when(this.mockEssaiService.hasResult((SearchCriteria) Matchers.any())).thenReturn(Boolean.FALSE);
            this.validator.validate(promoteur);
        } catch (final ValidationException e) {
            Assert.fail("ValidationException not expected");
        }
    }

    /**
     * Méthode en charge de tester la validation de la suppression de Promoteur.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKOEssais() {
        final Promoteur promoteur = new Promoteur();
        Mockito.when(this.mockEssaiService.hasResult((SearchCriteria) Matchers.any())).thenReturn(Boolean.TRUE);
        this.validator.validate(promoteur);
    }

    /**
     * Méthode en charge de tester la validation de la suppression de Promoteur.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKOArcPromoteurs() {
        final Promoteur promoteur = new Promoteur();
        Mockito.when(this.mockEssaiService.hasResult((SearchCriteria) Matchers.any())).thenReturn(Boolean.FALSE);
        final SortedSet<ArcPromoteur> arcPromoteurs = new TreeSet<ArcPromoteur>(new BeanWithNomComparator());
        final ArcPromoteur arcPromoteur = new ArcPromoteur();
        arcPromoteur.setNom("nom");
        arcPromoteurs.add(arcPromoteur);
        promoteur.setArcPromoteurs(arcPromoteurs);
        this.validator.validate(promoteur);
    }

    /**
     * Méthode en charge de tester la validation de la suppression de Promoteur.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKOContactPromoteurs() {
        final Promoteur promoteur = new Promoteur();
        Mockito.when(this.mockEssaiService.hasResult((SearchCriteria) Matchers.any())).thenReturn(Boolean.FALSE);
        final SortedSet<ContactPromoteur> contactPromoteurs = new TreeSet<ContactPromoteur>(new BeanWithNomComparator());
        final ContactPromoteur contactPromoteur = new ContactPromoteur();
        contactPromoteur.setNom("nom");
        contactPromoteurs.add(contactPromoteur);
        promoteur.setContactPromoteurs(contactPromoteurs);
        this.validator.validate(promoteur);
    }
}
