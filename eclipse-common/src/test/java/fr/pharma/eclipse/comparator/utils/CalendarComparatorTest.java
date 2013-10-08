package fr.pharma.eclipse.comparator.utils;

import java.text.ParseException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.utils.Utils;

/**
 * Test de la classe CalendarComparator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class CalendarComparatorTest {
    /**
     * Comparateur.
     */
    private CalendarComparator comparator;

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void setUp() {
        this.comparator = new CalendarComparator();
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown() {
        this.comparator = null;
    }

    /**
     * Comparaison avec deux propriétés égales.
     * @throws ParseException En cas d'erreur de création des jeux de données.
     */
    @Test
    public void compareEgal() throws ParseException {
        final String date = "15/03/2010";
        final String pattern = "dd/MM/yyyy";
        Assert.assertEquals(0, this.comparator.compare(Utils.parseDate(date, pattern), Utils.parseDate(date, pattern)));
    }

    /**
     * Comparaison avec deux propriétés inégales.
     * @throws ParseException En cas d'erreur de création des jeux de données.
     */
    @Test
    public void compareInegal() throws ParseException {
        final String date1 = "15/03/2010";
        final String date2 = "16/03/2010";
        final String pattern = "dd/MM/yyyy";
        Assert.assertTrue(this.comparator.compare(Utils.parseDate(date1, pattern), Utils.parseDate(date2, pattern)) < 0);
        Assert.assertTrue(this.comparator.compare(Utils.parseDate(date2, pattern), Utils.parseDate(date1, pattern)) > 0);
    }

}
