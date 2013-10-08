package fr.pharma.eclipse.comparator.patient;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.comparator.acteur.HistoriquePatientComparator;
import fr.pharma.eclipse.domain.model.patient.HistoriquePatient;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du comparateur HistoriquePatientComparateur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class HistoriquePatientComparatorTest extends AbstractEclipseJUnitTest {
    /**
     * Comparateur d'HistoriquePatient.
     */
    private HistoriquePatientComparator comparator;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.comparator = new HistoriquePatientComparator();
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
    public void testInit() {
        Assert.assertNotNull(this.comparator);
    }

    /**
     * Test de la méthode compare.
     */
    @Test
    public void testCompareInf() {
        final HistoriquePatient h1 = new HistoriquePatient();
        final Calendar dateAnterieure = new GregorianCalendar();
        dateAnterieure.add(Calendar.DAY_OF_YEAR, -5);
        h1.setDate(dateAnterieure);
        final HistoriquePatient h2 = new HistoriquePatient();
        h2.setDate(new GregorianCalendar());
        Assert.assertEquals(1, this.comparator.compare(h1, h2));
    }

    /**
     * Test de la méthode compare.
     */
    @Test
    public void testCompareSup() {
        final HistoriquePatient h1 = new HistoriquePatient();
        final Calendar date = new GregorianCalendar();
        date.add(Calendar.DAY_OF_YEAR, 5);
        h1.setDate(date);
        final HistoriquePatient h2 = new HistoriquePatient();
        h2.setDate(new GregorianCalendar());
        Assert.assertEquals(-1, this.comparator.compare(h1, h2));
    }

    /**
     * Test de la méthode compare.
     */
    @Test
    public void testCompareEq() {
        final HistoriquePatient h1 = new HistoriquePatient();
        final Calendar date = new GregorianCalendar();
        h1.setDate(date);
        final HistoriquePatient h2 = new HistoriquePatient();
        h2.setDate(date);
        Assert.assertEquals(0, this.comparator.compare(h1, h2));
    }

}
