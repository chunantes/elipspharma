package fr.pharma.eclipse.comparator.ordonnancier;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.dispensation.Dispensation;

/**
 * Classe en charge de tester le comparator de Dispensation pour la gestion des
 * ordonnanciers de Dispensation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class OrdonnancierDispComparatorTest {
    /**
     * OrdonnancierDispComparator à tester.
     */
    private OrdonnancierDispComparator comparator;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.comparator = new OrdonnancierDispComparator();
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
        final Dispensation disp1 = new Dispensation();
        disp1.setNumOrdonnancier(1);
        final Dispensation disp2 = new Dispensation();
        disp2.setNumOrdonnancier(1);
        Assert.assertEquals(0, this.comparator.compare(disp1, disp2));
    }

    /**
     * Méthode en charge de tester le comparator.
     */
    @Test
    public void testCompareInferieur() {
        final Dispensation disp1 = new Dispensation();
        disp1.setNumOrdonnancier(1);
        final Dispensation disp2 = new Dispensation();
        disp2.setNumOrdonnancier(2);
        Assert.assertTrue(this.comparator.compare(disp1, disp2) < 0);
    }

    /**
     * Méthode en charge de tester le comparator.
     */
    @Test
    public void testCompareSuperieur() {
        final Dispensation disp1 = new Dispensation();
        disp1.setNumOrdonnancier(2);
        final Dispensation disp2 = new Dispensation();
        disp2.setNumOrdonnancier(1);
        Assert.assertTrue(this.comparator.compare(disp1, disp2) > 0);
    }
}
