package fr.pharma.eclipse.validator.save.impl;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Classe en charge de tester le validator de sauvegarde de Pharmacien.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PharmacienSaveValidatorTest {
    /**
     * PharmacienSaveValidator à tester.
     */
    private PharmacienSaveValidator validator;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.validator = new PharmacienSaveValidator();
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
     * Méthode en charge de tester la validation OK de la sauvegarde d'un
     * pharmacien.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testValidate() {
        final Pharmacien pharmacien = new Pharmacien();
        final SortedSet<Pharmacie> pharmacies = new TreeSet<Pharmacie>(new BeanWithNomComparator());
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setNom("pharmacie");
        pharmacies.add(pharmacie);
        pharmacien.setPharmacies(pharmacies);

        final GenericService<Pharmacien> mockedService = Mockito.mock(GenericService.class);
        try {
            this.validator.validate(pharmacien, mockedService);
        } catch (final ValidationException e) {
            Assert.fail("Exception not expected");
        }
    }

    /**
     * Méthode en charge de tester la validation KO de la sauvegarde d'un
     * pharmacien.
     */
    @SuppressWarnings("unchecked")
    @Test(expected = ValidationException.class)
    public void testValidateKO() {
        final Pharmacien pharmacien = new Pharmacien();
        final GenericService<Pharmacien> mockedService = Mockito.mock(GenericService.class);
        this.validator.validate(pharmacien, mockedService);
    }
}
