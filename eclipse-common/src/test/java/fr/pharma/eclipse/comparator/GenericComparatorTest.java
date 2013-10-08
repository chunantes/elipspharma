package fr.pharma.eclipse.comparator;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Test du comparateur générique GenericComparator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GenericComparatorTest {
    /**
     * Comparaison avec deux propriétés égales.
     */
    @Test
    public void compareEgal() {
        final Essai essai1 = new Essai();
        essai1.setNom(TestConstants.NOM1);
        final Essai essai2 = new Essai();
        essai2.setNom(TestConstants.NOM1);

        final GenericComparator<BeanObject> comparator = new GenericComparator<BeanObject>("nom");

        Assert.assertEquals(0, comparator.compare(essai1, essai2));
        Assert.assertEquals(0, comparator.compare(essai2, essai1));
    }

    /**
     * Comparaison avec deux propriétés inégales.
     */
    @Test
    public void compareInegal() {
        final Essai essai1 = new Essai();
        essai1.setNom(TestConstants.NOM1);
        final Essai essai2 = new Essai();
        essai2.setNom(TestConstants.NOM2);

        final GenericComparator<BeanObject> comparator = new GenericComparator<BeanObject>("nom");

        Assert.assertTrue(0 > comparator.compare(essai1, essai2));
        Assert.assertTrue(0 < comparator.compare(essai2, essai1));
    }
}
