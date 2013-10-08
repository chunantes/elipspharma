package fr.pharma.eclipse.validator.remove.impl;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.DetailDonneesPharma;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.exception.ValidationException;

/**
 * Classe en charge de tester le validator sur la suppression de Pharmacie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PharmacieRemoveValidatorTest {
    /**
     * Validator de suppression à tester.
     */
    private PharmacieRemoveValidator validator;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.validator = new PharmacieRemoveValidator();
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
     * Méthode en charge de tester la validation de la suppression de pharmacie.
     */
    @Test
    public void testValidateOK() {
        final Pharmacie pharmacie = new Pharmacie();
        try {
            this.validator.validate(pharmacie);
        } catch (final ValidationException e) {
            Assert.fail("ValidationException not expected");
        }
    }

    /**
     * Méthode en charge de tester la validation de la suppression de pharmacie.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKOEssais() {
        final Pharmacie pharmacie = new Pharmacie();
        final SortedSet<DetailDonneesPharma> details = new TreeSet<DetailDonneesPharma>();
        final DetailDonneesPharma detail = new DetailDonneesPharma();
        detail.setId(1L);
        details.add(detail);
        pharmacie.setDetailsDonneesPharma(details);
        this.validator.validate(pharmacie);
    }

    /**
     * Méthode en charge de tester la validation de la suppression de pharmacie.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKOPharmaciens() {
        final Pharmacie pharmacie = new Pharmacie();
        final SortedSet<Pharmacien> pharmaciens = new TreeSet<Pharmacien>();
        final Pharmacien pharmacien = new Pharmacien();
        pharmacien.setId(1L);
        pharmacien.setNom("nom");
        pharmaciens.add(pharmacien);
        pharmacie.setPharmaciens(pharmaciens);
        this.validator.validate(pharmacie);
    }

}
