package fr.pharma.eclipse.comparator.incident;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.incident.Incident;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du comparator IncidentComparator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class IncidentComparatorTest extends AbstractEclipseJUnitTest {

    /**
     * Comparator.
     */
    private IncidentComparator comparator;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.comparator = new IncidentComparator();
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
    @Test
    @Override
    public void testInit() {
        Assert.assertNotNull(this.comparator);
    }

    /**
     * Test de la méthode evaluate.
     */
    @Test
    public void testCompareEquals() {
        final Calendar date = Calendar.getInstance();
        final Incident i = new Incident();
        i.setDate(date);
        i.setLibelle("libelle");
        i.setCommentaire("commentaire");
        Assert.assertEquals(0, this.comparator.compare(i, i));
    }

    /**
     * Test de la méthode evaluate.
     */
    @Test
    public void testCompareInf() {
        final Calendar date = Calendar.getInstance();
        final Incident i = new Incident();
        final Incident i2 = new Incident();
        i.setDate(date);
        i.setLibelle("libelle");
        i.setCommentaire("commentaire");
        i2.setDate(date);
        i2.setCommentaire("commentaire2");
        i2.setLibelle("libelle");
        Assert.assertEquals(-1, this.comparator.compare(i, i2));
    }

    /**
     * Test de la méthode evaluate.
     */
    @Test
    public void testCompareSup() {
        final Calendar date = Calendar.getInstance();
        final Incident i = new Incident();
        final Incident i2 = new Incident();
        i.setDate(date);
        i.setLibelle("libelle");
        i.setCommentaire("commentaire");
        i2.setDate(date);
        i2.setCommentaire("commentaire2");
        i2.setLibelle("libelle");
        Assert.assertEquals(1, this.comparator.compare(i2, i));
    }

}
