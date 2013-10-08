package fr.pharma.eclipse.utils;

import java.text.ParseException;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Test de la classe Utils.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class UtilsTest {

    /**
     * Test de la méthode formatDate.
     */
    @Test
    public void testFormatDate() {
        final Calendar cal = Calendar.getInstance(EclipseConstants.LOCALE);
        cal.set(Calendar.DATE, 15);
        cal.set(Calendar.MONTH, 2);
        cal.set(Calendar.YEAR, 2010);
        Assert.assertEquals("15/03/2010", Utils.formatDate(cal.getTime(), "dd/MM/yyyy"));
        Assert.assertNull(Utils.formatDate(null, "dd/MM/yyyy"));
    }

    /**
     * Test de la méthode parseDate.
     */
    @Test
    public void testParseDate() {
        try {
            final Calendar cal = Utils.parseDate("15/03/2010", "dd/MM/yyyy");
            Assert.assertEquals(15, cal.get(Calendar.DATE));
            Assert.assertEquals(2, cal.get(Calendar.MONTH));
            Assert.assertEquals(2010, cal.get(Calendar.YEAR));
        } catch (final ParseException e) {
            Assert.fail("Aucune exception n'est attendue ici!\n " + e.getMessage());
        }
    }

    /**
     * Test de la méthode getCalendarForFirstDayOfYear.
     */
    @Test
    public void getCalendarForFirstDayOfYear() {
        final int year = 2010;
        final Calendar cal = Utils.getCalendarForFirstDayOfYear(year);
        Assert.assertNotNull(cal);
        Assert.assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals(Calendar.JANUARY, cal.get(Calendar.MONTH));
        Assert.assertEquals(year, cal.get(Calendar.YEAR));
    }

    /**
     * Test de la méthode getCalendarForLastDayOfYear.
     */
    @Test
    public void getCalendarForLastDayOfYear() {
        final int year = 2010;
        final Calendar cal = Utils.getCalendarForLastDayOfYear(year);
        Assert.assertNotNull(cal);
        Assert.assertEquals(31, cal.get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals(Calendar.DECEMBER, cal.get(Calendar.MONTH));
        Assert.assertEquals(year, cal.get(Calendar.YEAR));
    }
}
