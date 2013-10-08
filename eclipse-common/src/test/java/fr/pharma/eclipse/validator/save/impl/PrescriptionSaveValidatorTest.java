package fr.pharma.eclipse.validator.save.impl;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.comparator.common.EclipseListComparator;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Classe en charge de tester le validator de sauvegarde de Prescription.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PrescriptionSaveValidatorTest {
    /**
     * PrescriptionSaveValidator à tester.
     */
    private PrescriptionSaveValidator validator;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.validator = new PrescriptionSaveValidator();
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
     * Prescription.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testValidate() {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        final Inclusion inclusion = new Inclusion();
        final Prescription prescription = new Prescription();
        prescription.setInclusion(inclusion);
        inclusion.setEssai(essai);

        final SortedSet<ProduitPrescrit> produits = new TreeSet<ProduitPrescrit>(new EclipseListComparator());
        final ProduitPrescrit prod = new ProduitPrescrit();
        produits.add(prod);
        prescription.setProduitsPrescrits(produits);

        final GenericService<Prescription> mockedService = Mockito.mock(GenericService.class);
        try {
            this.validator.validate(prescription, mockedService);
        } catch (final ValidationException e) {
            Assert.fail("Exception not expected");
        }
    }

    /**
     * Méthode en charge de tester la validation KO de la sauvegarde d'un
     * Prescription.
     */
    @SuppressWarnings("unchecked")
    @Test(expected = ValidationException.class)
    public void testValidateKO() {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        final Inclusion inclusion = new Inclusion();
        final Prescription prescription = new Prescription();
        prescription.setInclusion(inclusion);
        inclusion.setEssai(essai);

        final GenericService<Prescription> mockedService = Mockito.mock(GenericService.class);
        this.validator.validate(prescription, mockedService);
    }
}
