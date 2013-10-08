package fr.pharma.eclipse.comparator.ordonnancier;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.stock.PreparationEntree;

/**
 * Classe en charge de tester le comparator de PreparationEntree pour la gestion
 * des ordonnanciers de Fabrication/Reconstitution.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class OrdonnancierFabReconstComparatorTest {
    /**
     * OrdonnancierFabReconstComparator à tester.
     */
    private OrdonnancierFabReconstComparator comparator;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.comparator = new OrdonnancierFabReconstComparator();
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.comparator = null;
    }

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.comparator);
    }

    /**
     * Méthode en charge de tester le comparator.
     */
    @Test
    public void testCompare() {
        final PreparationEntree elt1 = new PreparationEntree();
        elt1.setNumLot("1");
        final PreparationEntree elt2 = new PreparationEntree();
        elt2.setNumLot("1");
        Assert.assertEquals(0, this.comparator.compare(elt1, elt2));
    }

    /**
     * Méthode en charge de tester le comparator.
     */
    @Test
    public void testCompareInferieur() {
        final PreparationEntree elt1 = new PreparationEntree();
        elt1.setNumLot("1");
        final PreparationEntree elt2 = new PreparationEntree();
        elt2.setNumLot("2");
        Assert.assertTrue(this.comparator.compare(elt1, elt2) < 0);
    }

    /**
     * Méthode en charge de tester le comparator.
     */
    @Test
    public void testCompareSuperieur() {
        final PreparationEntree elt1 = new PreparationEntree();
        elt1.setNumLot("2");
        final PreparationEntree elt2 = new PreparationEntree();
        elt2.setNumLot("1");
        Assert.assertTrue(this.comparator.compare(elt1, elt2) > 0);
    }
}
