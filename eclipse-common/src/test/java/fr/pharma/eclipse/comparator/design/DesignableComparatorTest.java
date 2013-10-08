package fr.pharma.eclipse.comparator.design;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.design.UniteTemps;
import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du comparator DesignableComparator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DesignableComparatorTest extends AbstractEclipseJUnitTest {

    /**
     * Comparator.
     */
    private DesignableComparator comparator;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.comparator = new DesignableComparator();

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
        final Bras bras = new Bras();
        bras.setNom("bras");
        final Sequence s1 = new Sequence();
        final TempsPrescription t1 = new TempsPrescription();
        t1.setNb(1);
        t1.setUnite(UniteTemps.JOUR);
        s1.setDebut(t1);
        s1.setNom("nom");
        s1.setParent(bras);
        final Sequence s2 = new Sequence();
        final TempsPrescription t2 = new TempsPrescription();
        t2.setNb(3);
        t2.setUnite(UniteTemps.JOUR);
        s2.setDebut(t2);
        s2.setNom("nom");
        s2.setParent(bras);

        Assert.assertTrue(this.comparator.compare(s1, s2) < 0);
    }

    /**
     * Test de la méthode compare.
     */
    @Test
    public void testCompareSup() {
        final Bras bras = new Bras();
        bras.setNom("bras");
        final Sequence s1 = new Sequence();
        final TempsPrescription t1 = new TempsPrescription();
        t1.setNb(5);
        t1.setUnite(UniteTemps.JOUR);
        s1.setDebut(t1);
        s1.setNom("nom");
        s1.setParent(bras);

        final Sequence s2 = new Sequence();
        final TempsPrescription t2 = new TempsPrescription();
        t2.setNb(3);
        t2.setUnite(UniteTemps.JOUR);
        s2.setDebut(t2);
        s2.setNom("nom");
        s2.setParent(bras);

        Assert.assertEquals(2, this.comparator.compare(s1, s2));
    }

    /**
     * Test de la méthode compare.
     */
    @Test
    public void testCompareEq() {
        final Bras bras = new Bras();
        bras.setNom("bras");
        final Sequence s1 = new Sequence();
        final TempsPrescription t1 = new TempsPrescription();
        t1.setNb(5);
        t1.setUnite(UniteTemps.JOUR);
        s1.setDebut(t1);
        s1.setNom("nom");
        s1.setParent(bras);

        final Sequence s2 = new Sequence();
        final TempsPrescription t2 = new TempsPrescription();
        t2.setNb(5);
        t2.setUnite(UniteTemps.JOUR);
        s2.setDebut(t2);
        s2.setNom("nom");
        s2.setParent(bras);

        Assert.assertEquals(0, this.comparator.compare(s1, s2));
    }
}
