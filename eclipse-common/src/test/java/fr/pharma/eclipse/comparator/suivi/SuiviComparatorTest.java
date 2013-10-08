package fr.pharma.eclipse.comparator.suivi;

import java.util.Calendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.suivi.stockage.PharmacieSuivi;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester le comparator sur les beans de suivis.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SuiviComparatorTest {

    /**
     * Comparator à tester.
     */
    private SuiviComparator comparator;

    /**
     * Méthoe en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.comparator = new SuiviComparator();
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
        final Calendar dateMaj = Calendar.getInstance(EclipseConstants.LOCALE);
        final PharmacieSuivi pharmacieSuivi1 = new PharmacieSuivi();
        pharmacieSuivi1.setDateMaj(dateMaj);
        final PharmacieSuivi pharmacieSuivi2 = new PharmacieSuivi();
        pharmacieSuivi2.setDateMaj(dateMaj);
        Assert.assertEquals(0, this.comparator.compare(pharmacieSuivi1, pharmacieSuivi2));
    }

    /**
     * Méthode en charge de tester la méthode de comparaison. Cas de
     * l'infériorité.
     */
    @Test
    public void testCompareInferieur() {
        final Calendar dateMaj1 = Calendar.getInstance(EclipseConstants.LOCALE);
        final PharmacieSuivi pharmacieSuivi1 = new PharmacieSuivi();
        pharmacieSuivi1.setDateMaj(dateMaj1);

        final Calendar dateMaj2 = Calendar.getInstance(EclipseConstants.LOCALE);
        dateMaj2.setTime(dateMaj1.getTime());
        dateMaj2.add(Calendar.MINUTE, 1);

        final PharmacieSuivi pharmacieSuivi2 = new PharmacieSuivi();
        pharmacieSuivi2.setDateMaj(dateMaj2);
        Assert.assertTrue(this.comparator.compare(pharmacieSuivi2, pharmacieSuivi1) < 0);
    }

    /**
     * Méthode en charge de tester la méthode de comparaison. Cas de la
     * supériorité.
     */
    @Test
    public void testCompareSuperieur() {
        final Calendar dateMaj1 = Calendar.getInstance(EclipseConstants.LOCALE);
        final PharmacieSuivi pharmacieSuivi1 = new PharmacieSuivi();
        pharmacieSuivi1.setDateMaj(dateMaj1);

        final Calendar dateMaj2 = Calendar.getInstance(EclipseConstants.LOCALE);
        dateMaj2.setTime(dateMaj1.getTime());
        dateMaj2.add(Calendar.MINUTE, 1);

        final PharmacieSuivi pharmacieSuivi2 = new PharmacieSuivi();
        pharmacieSuivi2.setDateMaj(dateMaj2);
        Assert.assertTrue(this.comparator.compare(pharmacieSuivi1, pharmacieSuivi2) > 0);
    }

}
