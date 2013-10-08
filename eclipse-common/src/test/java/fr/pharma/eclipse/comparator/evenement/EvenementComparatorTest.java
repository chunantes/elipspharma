package fr.pharma.eclipse.comparator.evenement;

import java.util.Calendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.evenement.Evenement;

/**
 * Classe en charge de tester le comparator de Evenement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EvenementComparatorTest {
    /**
     * EvenementComparator à tester.
     */
    private EvenementComparator comparator;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.comparator = new EvenementComparator();
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
     * Méthode en charge de tester la comparaison.
     */
    @Test
    public void testCompareEquals() {
        final Calendar dateDebut = Calendar.getInstance();
        final Evenement evt1 = new Evenement();
        evt1.setDateDebut(dateDebut);
        final Evenement evt2 = new Evenement();
        evt2.setDateDebut(dateDebut);
        Assert.assertEquals(0, this.comparator.compare(evt1, evt2));
    }

    /**
     * Méthode en charge de tester la comparaison Inférieur.
     */
    @Test
    public void testCompareInferieur() {
        final Calendar dateDeb1 = Calendar.getInstance();
        final Evenement evt1 = new Evenement();
        evt1.setDateDebut(dateDeb1);
        final Calendar dateDeb2 = Calendar.getInstance();
        final Evenement evt2 = new Evenement();
        dateDeb2.add(Calendar.DAY_OF_MONTH, 1);
        evt2.setDateDebut(dateDeb2);
        Assert.assertTrue(this.comparator.compare(evt1, evt2) < 0);
    }

}
