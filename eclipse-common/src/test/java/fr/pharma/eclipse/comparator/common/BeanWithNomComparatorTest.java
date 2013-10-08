package fr.pharma.eclipse.comparator.common;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Test de la classe BeanWithNomComparator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BeanWithNomComparatorTest {
    /**
     * Comparator à tester.
     */
    private BeanWithNomComparator comparator;

    /**
     * Méthoe en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.comparator = new BeanWithNomComparator();
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.comparator = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.comparator);
    }

    /**
     * Méthode en charge de tester la méthode de comparaison. Cas de l'égalité.
     */
    @Test
    public void testCompareEquals() {
        final Pharmacie pharma1 = new Pharmacie();
        pharma1.setNom("pharma");
        final Pharmacie pharma2 = new Pharmacie();
        pharma2.setNom("pharma");
        Assert.assertEquals(0, this.comparator.compare(pharma1, pharma2));
    }

    /**
     * Méthode en charge de tester la méthode de comparaison. Cas de
     * l'infériorité.
     */
    @Test
    public void testCompareInferieur() {
        final Pharmacie pharma1 = new Pharmacie();
        pharma1.setNom("apharma");
        final Pharmacie pharma2 = new Pharmacie();
        pharma2.setNom("pharma");
        Assert.assertTrue(this.comparator.compare(pharma1, pharma2) < 0);
    }

    /**
     * Méthode en charge de tester la méthode de comparaison. Cas de la
     * supériorité.
     */
    @Test
    public void testCompareSuperieur() {
        final Pharmacie pharma1 = new Pharmacie();
        pharma1.setNom("zpharma");
        final Pharmacie pharma2 = new Pharmacie();
        pharma2.setNom("pharma");
        Assert.assertTrue(this.comparator.compare(pharma1, pharma2) > 0);
    }
}
