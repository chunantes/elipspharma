package fr.pharma.eclipse.service.dispensation.checker.impl;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du checker GenericNotNullChecker.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GenericNotNullCheckerTest extends AbstractEclipseJUnitTest {

    /**
     * Checker à tester.
     */
    private GenericNotNullChecker<ProduitPrescrit> checker;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.checker = new GenericNotNullChecker<ProduitPrescrit>("produit");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.checker = null;
    }

    /**
     * {@inheritDoc}
     */
    @Test
    @Override
    public void testInit() {
        Assert.assertNotNull(this.checker);
    }

    /**
     * Test de la méthode check.
     */
    @Test
    public void testCheckKo() {
        Assert.assertFalse(this.checker.check(new ProduitPrescrit()));
    }

    /**
     * Test de la méthode check.
     */
    @Test
    public void testCheckOk() {
        final ProduitPrescrit produitPrescrit = new ProduitPrescrit();
        produitPrescrit.setProduit(new Medicament());
        Assert.assertTrue(this.checker.check(produitPrescrit));
    }
}
