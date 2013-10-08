package fr.pharma.eclipse.comparator.design;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.design.UniteTemps;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du comparator TempsPrescriptionComparator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TempsPrescriptionComparatorTest extends AbstractEclipseJUnitTest {

    /**
     * Comparator.
     */
    private TempsPrescriptionComparator comparator;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.comparator = new TempsPrescriptionComparator();

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
    public void testInit() {
        Assert.assertNotNull(this.comparator);
    }

    /**
     * Test de la méthode compare.
     */
    @Test
    public void testCompareInf() {
        final TempsPrescription t1 = new TempsPrescription();
        t1.setNb(1);
        t1.setUnite(UniteTemps.JOUR);

        final TempsPrescription t2 = new TempsPrescription();
        t2.setNb(3);
        t2.setUnite(UniteTemps.MOIS);

        Assert.assertEquals(-1, this.comparator.compare(t1, t2));
    }

    /**
     * Test de la méthode compare.
     */
    @Test
    public void testCompareSup() {
        final TempsPrescription t1 = new TempsPrescription();
        t1.setNb(5);
        t1.setUnite(UniteTemps.SEMAINE);

        final TempsPrescription t2 = new TempsPrescription();
        t2.setNb(3);
        t2.setUnite(UniteTemps.JOUR);

        Assert.assertEquals(1, this.comparator.compare(t1, t2));
    }

    /**
     * Test de la méthode compare.
     */
    @Test
    public void testCompareEq() {
        final TempsPrescription t1 = new TempsPrescription();
        t1.setNb(5);
        t1.setUnite(UniteTemps.JOUR);

        final TempsPrescription t2 = new TempsPrescription();
        t2.setNb(5);
        t2.setUnite(UniteTemps.JOUR);

        Assert.assertEquals(1, this.comparator.compare(t1, t2));
    }
}
