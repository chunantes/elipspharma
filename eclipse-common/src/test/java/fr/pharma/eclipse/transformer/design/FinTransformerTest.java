package fr.pharma.eclipse.transformer.design;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.design.UniteTemps;
import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du transformer FinTransformer.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class FinTransformerTest extends AbstractEclipseJUnitTest {
    /**
     * Transformer.
     */
    private FinTransformer transformer;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.transformer = new FinTransformer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.transformer = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.transformer);
    }

    /**
     * Test de la méthode transform.
     */
    @Test
    public void testTransformJourDebutSemaineDuree() {
        final PrescriptionType p = new PrescriptionType();
        p.setDebut(this.makeTempsPrescriptionForTest(1, UniteTemps.JOUR));
        p.setDuree(this.makeTempsPrescriptionForTest(3, UniteTemps.SEMAINE));
        final TempsPrescription fin = (TempsPrescription) this.transformer.transform(p);

        Assert.assertEquals(new Integer(22), fin.getNb());
        Assert.assertEquals(UniteTemps.JOUR, fin.getUnite());
    }

    /**
     * Test de la méthode transform.
     */
    @Test
    public void testTransformJourDureeSemaineDebut() {
        final PrescriptionType p = new PrescriptionType();
        p.setDuree(this.makeTempsPrescriptionForTest(1, UniteTemps.JOUR));
        p.setDebut(this.makeTempsPrescriptionForTest(3, UniteTemps.SEMAINE));
        final TempsPrescription fin = (TempsPrescription) this.transformer.transform(p);

        Assert.assertEquals(new Integer(22), fin.getNb());
        Assert.assertEquals(UniteTemps.JOUR, fin.getUnite());
    }

    /**
     * Test de la méthode transform.
     */
    @Test
    public void testTransformSemaineDebutMoisDuree() {
        final PrescriptionType p = new PrescriptionType();
        p.setDebut(this.makeTempsPrescriptionForTest(10, UniteTemps.SEMAINE));
        p.setDuree(this.makeTempsPrescriptionForTest(3, UniteTemps.MOIS));
        final TempsPrescription fin = (TempsPrescription) this.transformer.transform(p);

        Assert.assertEquals(new Integer(22), fin.getNb());
        Assert.assertEquals(UniteTemps.SEMAINE, fin.getUnite());
    }

    /**
     * Test de la méthode transform.
     */
    @Test
    public void testTransformSemaineDureeMoisDebut() {
        final PrescriptionType p = new PrescriptionType();
        p.setDuree(this.makeTempsPrescriptionForTest(10, UniteTemps.SEMAINE));
        p.setDebut(this.makeTempsPrescriptionForTest(3, UniteTemps.MOIS));
        final TempsPrescription fin = (TempsPrescription) this.transformer.transform(p);

        Assert.assertEquals(new Integer(22), fin.getNb());
        Assert.assertEquals(UniteTemps.SEMAINE, fin.getUnite());
    }

    /**
     * Test de la méthode transform.
     */
    @Test
    public void testTransformSemaineDuree() {
        final PrescriptionType p = new PrescriptionType();
        p.setDuree(this.makeTempsPrescriptionForTest(10, UniteTemps.SEMAINE));
        p.setDebut(this.makeTempsPrescriptionForTest(3, UniteTemps.JOUR));
        final TempsPrescription fin = (TempsPrescription) this.transformer.transform(p);

        Assert.assertEquals(new Integer(73), fin.getNb());
        Assert.assertEquals(UniteTemps.JOUR, fin.getUnite());
    }

    /**
     * Test de la méthode transform.
     */
    @Test
    public void testTransformJourDureeMoisDebut() {
        final PrescriptionType p = new PrescriptionType();
        p.setDuree(this.makeTempsPrescriptionForTest(10, UniteTemps.JOUR));
        p.setDebut(this.makeTempsPrescriptionForTest(3, UniteTemps.MOIS));
        final TempsPrescription fin = (TempsPrescription) this.transformer.transform(p);

        Assert.assertEquals(new Integer(100), fin.getNb());
        Assert.assertEquals(UniteTemps.JOUR, fin.getUnite());
    }

    /**
     * Test de la méthode transform.
     */
    @Test
    public void testTransformJour() {
        final PrescriptionType p = new PrescriptionType();
        p.setDuree(this.makeTempsPrescriptionForTest(10, UniteTemps.JOUR));
        p.setDebut(this.makeTempsPrescriptionForTest(3, UniteTemps.JOUR));
        final TempsPrescription fin = (TempsPrescription) this.transformer.transform(p);

        Assert.assertEquals(new Integer(13), fin.getNb());
        Assert.assertEquals(UniteTemps.JOUR, fin.getUnite());
    }

    /**
     * Test de la méthode transform.
     */
    @Test
    public void testTransformMois() {
        final PrescriptionType p = new PrescriptionType();
        p.setDuree(this.makeTempsPrescriptionForTest(10, UniteTemps.MOIS));
        p.setDebut(this.makeTempsPrescriptionForTest(3, UniteTemps.MOIS));
        final TempsPrescription fin = (TempsPrescription) this.transformer.transform(p);

        Assert.assertEquals(new Integer(13), fin.getNb());
        Assert.assertEquals(UniteTemps.MOIS, fin.getUnite());
    }

    /**
     * Test de la méthode transform.
     */
    @Test
    public void testTransformSemaine() {
        final PrescriptionType p = new PrescriptionType();
        p.setDuree(this.makeTempsPrescriptionForTest(10, UniteTemps.SEMAINE));
        p.setDebut(this.makeTempsPrescriptionForTest(3, UniteTemps.SEMAINE));
        final TempsPrescription fin = (TempsPrescription) this.transformer.transform(p);

        Assert.assertEquals(new Integer(13), fin.getNb());
        Assert.assertEquals(UniteTemps.SEMAINE, fin.getUnite());
    }

    /**
     * Test de la méthode transform.
     */
    @Test
    public void testTransformMoisDureeJourDebut() {
        final PrescriptionType p = new PrescriptionType();
        p.setDuree(this.makeTempsPrescriptionForTest(10, UniteTemps.MOIS));
        p.setDebut(this.makeTempsPrescriptionForTest(3, UniteTemps.JOUR));
        final TempsPrescription fin = (TempsPrescription) this.transformer.transform(p);

        Assert.assertEquals(new Integer(303), fin.getNb());
        Assert.assertEquals(UniteTemps.JOUR, fin.getUnite());
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
