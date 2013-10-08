package fr.pharma.eclipse.comparator.surcout;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.surcout.regle.Regle;

/**
 * Test du comparator : RegleBorneMinComparator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class RegleBorneMinComparatorTest {
    /**
     * Test de la méthode compare().
     */
    @Test
    public void testCompareInf() {
        final Regle o1 = new Regle();
        final Regle o2 = new Regle();
        o1.setMax(5);
        o2.setMax(6);
        Assert.assertTrue(new RegleBorneMinComparator().compare(o1, o2) < 0);
    }

    /**
     * Test de la méthode compare().
     */
    @Test
    public void testCompareInf1() {
        final Regle o1 = new Regle();
        final Regle o2 = new Regle();
        o2.setMin(5);
        Assert.assertTrue(new RegleBorneMinComparator().compare(o1, o2) < 0);
    }

    /**
     * Test de la méthode compare().
     */
    @Test
    public void testCompareInf2() {
        final Regle o1 = new Regle();
        o1.setMin(1);
        final Regle o2 = new Regle();
        o2.setMin(5);
        Assert.assertTrue(new RegleBorneMinComparator().compare(o1, o2) < 0);
    }

    /**
     * Test de la méthode compare().
     */
    @Test
    public void testCompareSup() {
        final Regle o1 = new Regle();
        final Regle o2 = new Regle();
        o2.setMax(5);
        o1.setMax(6);
        Assert.assertTrue(new RegleBorneMinComparator().compare(o1, o2) > 0);
    }

    /**
     * Test de la méthode compare().
     */
    @Test
    public void testCompareSup1() {
        final Regle o1 = new Regle();
        final Regle o2 = new Regle();
        o1.setMin(5);
        Assert.assertTrue(new RegleBorneMinComparator().compare(o1, o2) > 0);
    }

    /**
     * Test de la méthode compare().
     */
    @Test
    public void testCompareSup2() {
        final Regle o1 = new Regle();
        o1.setMin(6);
        final Regle o2 = new Regle();
        o2.setMin(5);
        Assert.assertTrue(new RegleBorneMinComparator().compare(o1, o2) > 0);
    }
}
