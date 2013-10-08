package fr.pharma.eclipse.validator.remove.impl;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.domain.model.localisation.Site;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.exception.ValidationException;

/**
 * Classe en charge de tester le validator de suppression de site.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SiteRemoveValidatorTest {
    /**
     * Validator de suppression à tester.
     */
    private SiteRemoveValidator validator;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.validator = new SiteRemoveValidator();
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.validator = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.validator);
    }

    /**
     * Méthode en charge de tester la validation de la suppression de site.
     */
    @Test
    public void testValidateOK() {
        final Site site = new Site();
        try {
            this.validator.validate(site);
        } catch (final ValidationException e) {
            Assert.fail("ValidationException not expected");
        }
    }

    /**
     * Méthode en charge de tester la validation de la suppression de site.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKO() {
        final Site site = new Site();
        final SortedSet<Pharmacie> pharmacies = new TreeSet<Pharmacie>(new BeanWithNomComparator());
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setNom("nom");
        pharmacies.add(pharmacie);
        site.setPharmacies(pharmacies);
        this.validator.validate(site);
    }

}
