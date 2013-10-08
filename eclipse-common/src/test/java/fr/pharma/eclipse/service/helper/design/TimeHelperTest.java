package fr.pharma.eclipse.service.helper.design;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.design.UniteTemps;
import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe TimeHelper.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TimeHelperTest extends AbstractEclipseJUnitTest {
    /**
     * Helper à tester.
     */
    private TimeHelper helper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.helper = new TimeHelper();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.helper = null;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.helper);
    }

    /**
     * TEst de la méthode getMinimum().
     */
    @Test
    public void testGetMinimum() {
        final List<PrescriptionType> collection = new ArrayList<PrescriptionType>();

        final PrescriptionType p1 = new PrescriptionType();
        p1.getDebut().setNb(5);
        p1.getDebut().setUnite(UniteTemps.JOUR);
        final PrescriptionType p2 = new PrescriptionType();
        p2.getDebut().setNb(2);
        p2.getDebut().setUnite(UniteTemps.MOIS);
        final PrescriptionType p4 = new PrescriptionType();
        p4.getDebut().setNb(2);
        p4.getDebut().setUnite(UniteTemps.MOIS);
        final PrescriptionType p3 = new PrescriptionType();

        collection.add(p1);
        collection.add(p2);
        collection.add(p3);
        collection.add(p4);

        final TempsPrescription result = this.helper.getDebut(collection);
        Assert.assertEquals(new Integer(5), result.getNb());
        Assert.assertEquals(UniteTemps.JOUR, result.getUnite());
    }

    /**
     * TEst de la méthode getMinimum().
     */
    @Test
    public void testGetFin() {
        final List<PrescriptionType> collection = new ArrayList<PrescriptionType>();

        final PrescriptionType p1 = new PrescriptionType();
        p1.setDebut(this.makeTempsPrescriptionForTest(2, UniteTemps.JOUR));
        p1.setDuree(this.makeTempsPrescriptionForTest(3, UniteTemps.MOIS));
        final PrescriptionType p2 = new PrescriptionType();
        p2.setDebut(this.makeTempsPrescriptionForTest(2, UniteTemps.SEMAINE));
        p2.setDuree(this.makeTempsPrescriptionForTest(3, UniteTemps.SEMAINE));
        final PrescriptionType p4 = new PrescriptionType();
        p4.setDebut(this.makeTempsPrescriptionForTest(15, UniteTemps.JOUR));
        p4.setDuree(this.makeTempsPrescriptionForTest(15, UniteTemps.JOUR));
        final PrescriptionType p3 = new PrescriptionType();

        collection.add(p1);
        collection.add(p2);
        collection.add(p3);
        collection.add(p4);

        final TempsPrescription result = this.helper.getFin(collection);
        Assert.assertEquals(new Integer(92), result.getNb());
        Assert.assertEquals(UniteTemps.JOUR, result.getUnite());
    }

    /**
     * TEst de la méthode getFin().
     */
    @Test
    public void testGetFinEmpty() {
        final TempsPrescription result = this.helper.getFin(new ArrayList<PrescriptionType>());
        Assert.assertNotNull(result);
        Assert.assertNull(result.getNb());
        Assert.assertNull(result.getUnite());
    }

    /**
     * TEst de la méthode getDebut().
     */
    @Test
    public void testGetDebutEmpty() {
        final TempsPrescription result = this.helper.getDebut(new ArrayList<PrescriptionType>());
        Assert.assertNotNull(result);
        Assert.assertNull(result.getNb());
        Assert.assertNull(result.getUnite());
    }

    /**
     * En charge de construire un TempsPrescription pour le test.
     * @param value La valeur.
     * @param unite L'unité.
     * @return Le tempsPrescription
     */
    private TempsPrescription makeTempsPrescriptionForTest(final int value,
                                                           final UniteTemps unite) {
        final TempsPrescription t = new TempsPrescription();
        t.setNb(value);
        t.setUnite(unite);
        return t;
    }
}
