package fr.pharma.eclipse.comparator.dispensation;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.dispensation.ElementToCheck;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du comparateur ElementToCheckComparator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ElementToCheckComparatorTest extends AbstractEclipseJUnitTest {
    /**
     * Comparator à tester.
     */
    private ElementToCheckComparator comparator;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.comparator = new ElementToCheckComparator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.comparator = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.comparator);
    }

    /**
     * Test de la méthode compare.
     */
    @Test
    public void testCompareSup() {
        final ProduitPrescrit produitPrecrit = new ProduitPrescrit();
        produitPrecrit.setId(1L);
        final ElementToCheck p1 = new ElementToCheck();
        final ElementToCheck p2 = new ElementToCheck();
        p1.setProduitPrescrit(produitPrecrit);
        p2.setProduitPrescrit(produitPrecrit);
        p1.setNomChamps("a");
        p2.setNomChamps("b");
        Assert.assertTrue(this.comparator.compare(p2, p1) > 0);
    }

    /**
     * Test de la méthode compare.
     */
    @Test
    public void testCompareInf() {
        final ProduitPrescrit produitPrecrit = new ProduitPrescrit();
        produitPrecrit.setId(1L);
        final ElementToCheck p1 = new ElementToCheck();
        final ElementToCheck p2 = new ElementToCheck();
        p1.setProduitPrescrit(produitPrecrit);
        p2.setProduitPrescrit(produitPrecrit);
        p1.setNomChamps("b");
        p2.setNomChamps("a");
        Assert.assertTrue(this.comparator.compare(p2, p1) < 0);
    }

    /**
     * Test de la méthode compare.
     */
    @Test
    public void testCompareEq() {
        final ProduitPrescrit produitPrecrit = new ProduitPrescrit();
        produitPrecrit.setId(1L);
        final ElementToCheck p1 = new ElementToCheck();
        final ElementToCheck p2 = new ElementToCheck();
        p1.setProduitPrescrit(produitPrecrit);
        p2.setProduitPrescrit(produitPrecrit);
        p1.setNomChamps("a");
        p2.setNomChamps("a");
        Assert.assertTrue(this.comparator.compare(p2, p1) == 0);
    }

}
