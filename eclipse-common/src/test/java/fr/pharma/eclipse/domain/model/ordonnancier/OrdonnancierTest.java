package fr.pharma.eclipse.domain.model.ordonnancier;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester les méthodes présentes dans la classe
 * Ordonnancier.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class OrdonnancierTest {
    /**
     * Année 2011.
     */
    private static final int AN_2011 = 2011;

    /**
     * Méthode en charge de tester la méthode de descriptio.
     */
    @Test
    public void testGetDescription() {
        final OrdonnancierDisp ordonnancier = new OrdonnancierDisp();
        final Calendar dateDebut = Calendar.getInstance(EclipseConstants.LOCALE);
        dateDebut.set(OrdonnancierTest.AN_2011, Calendar.JANUARY, 1, 0, 0);
        final Calendar dateFin = Calendar.getInstance(EclipseConstants.LOCALE);
        dateFin.set(OrdonnancierTest.AN_2011, Calendar.FEBRUARY, 1, 0, 0);
        ordonnancier.setDateDebut(dateDebut);
        ordonnancier.setDateFin(dateFin);
        Assert.assertEquals("Du 01/01/2011 Au 01/02/2011", ordonnancier.getDescription());
    }

}
