package fr.pharma.eclipse.comparator.surcout;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du comparateur ItemComparator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ItemComparatorTest extends AbstractEclipseJUnitTest {

    /**
     * Comparateur à tester.
     */
    private ItemComparator comparator;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.comparator = new ItemComparator();
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
    public void compareSup() {
        final Item o1 = new Item();
        final Item o2 = new Item();
        o1.setTheme("theme");
        o2.setTheme("theme");
        o1.setCategorie("categorie1");
        o2.setCategorie("categorie2");

        Assert.assertTrue(this.comparator.compare(o1, o2) < 0);
    }

    /**
     * Test de la méthode compare.
     */
    @Test
    public void compareInf() {
        final Item o1 = new Item();
        final Item o2 = new Item();
        o1.setTheme("theme");
        o2.setTheme("theme");
        o1.setCategorie("categorie2");
        o2.setCategorie("categorie1");

        Assert.assertTrue(this.comparator.compare(o1, o2) > 0);
    }

    /**
     * Test de la méthode compare.
     */
    @Test
    public void compareEq() {
        final Item o1 = new Item();
        final Item o2 = new Item();
        o1.setTheme("theme");
        o2.setTheme("theme");

        Assert.assertTrue(this.comparator.compare(o1, o2) == 0);
    }
}
