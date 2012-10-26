package fr.pharma.eclipse.json;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.design.UniteTemps;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.service.helper.design.TimeHelper;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe SequenceToJSONCOnverter.
 
 * @version $Revision$ $Date$
 */
public class SequenceToJSONConverterTest
    extends AbstractEclipseJUnitTest
{

    /**
     * Converter.
     */
    private SequenceToJSONConverter converter;

    /**
     * Helper.
     */
    private TimeHelper helper;

    @Override
    public void setUp()
    {
        this.helper = Mockito.mock(TimeHelper.class);
        this.converter = new SequenceToJSONConverter();
        this.converter.setTimehelper(this.helper);
    }

    @Override
    public void tearDown()
    {
        this.helper = null;
        this.converter = null;
    }

    @Override
    public void testInit()
    {
        Assert.assertNotNull(this.helper);
        Assert.assertNotNull(this.converter);
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.json.SequenceToJSONConverter#convert(fr.pharma.eclipse.domain.model.design.Sequence, java.util.Calendar)}
     * .
     */
    @Test
    public void testSupportFalse()
    {
        final Sequence sequence = new Sequence();
        Assert.assertFalse(this.converter.support(sequence));
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.json.SequenceToJSONConverter#convert(fr.pharma.eclipse.domain.model.design.Sequence, java.util.Calendar)}
     * .
     */
    @Test
    public void testSupportFalse2()
    {
        final Sequence sequence = new Sequence();
        sequence.setDebut(new TempsPrescription());
        sequence.getDebut().setNb(1);
        sequence.getDebut().setUnite(UniteTemps.JOUR);
        Assert.assertFalse(this.converter.support(sequence));
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.json.SequenceToJSONConverter#convert(fr.pharma.eclipse.domain.model.design.Sequence, java.util.Calendar)}
     * .
     */
    @Test
    public void testSupportFalse3()
    {
        final Sequence sequence = new Sequence();
        sequence.setDebut(new TempsPrescription());
        sequence.getDebut().setUnite(UniteTemps.JOUR);
        Assert.assertFalse(this.converter.support(sequence));
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.json.SequenceToJSONConverter#convert(fr.pharma.eclipse.domain.model.design.Sequence, java.util.Calendar)}
     * .
     */
    @Test
    public void testSupportFalse4()
    {
        final Sequence sequence = new Sequence();
        sequence.setDebut(new TempsPrescription());
        Assert.assertFalse(this.converter.support(sequence));
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.json.SequenceToJSONConverter#convert(fr.pharma.eclipse.domain.model.design.Sequence, java.util.Calendar)}
     * .
     */
    @Test
    public void testSupportFalse5()
    {
        final Sequence sequence = new Sequence();
        sequence.setDebut(new TempsPrescription());
        sequence.getDebut().setNb(1);
        sequence.getDebut().setUnite(UniteTemps.JOUR);
        sequence.setFin(new TempsPrescription());
        Assert.assertFalse(this.converter.support(sequence));
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.json.SequenceToJSONConverter#convert(fr.pharma.eclipse.domain.model.design.Sequence, java.util.Calendar)}
     * .
     */
    @Test
    public void testSupportFalse6()
    {
        final Sequence sequence = new Sequence();
        sequence.setDebut(new TempsPrescription());
        sequence.getDebut().setNb(1);
        sequence.getDebut().setUnite(UniteTemps.JOUR);
        sequence.setFin(new TempsPrescription());
        sequence.getFin().setNb(1);
        Assert.assertFalse(this.converter.support(sequence));
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.json.SequenceToJSONConverter#convert(fr.pharma.eclipse.domain.model.design.Sequence, java.util.Calendar)}
     * .
     */
    @Test
    public void testSupportTrue()
    {
        final Sequence sequence = new Sequence();
        sequence.setDebut(new TempsPrescription());
        sequence.getDebut().setNb(1);
        sequence.getDebut().setUnite(UniteTemps.JOUR);
        sequence.setFin(new TempsPrescription());
        sequence.getFin().setNb(1);
        sequence.getFin().setUnite(UniteTemps.JOUR);
        Assert.assertTrue(this.converter.support(sequence));
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.json.SequenceToJSONConverter#support(fr.pharma.eclipse.domain.model.design.Sequence)}
     * .
     */
    @Test
    public void testConvert()
    {
        final Sequence sequence = new Sequence();
        sequence.setDebut(new TempsPrescription());
        sequence.getDebut().setNb(1);
        sequence.getDebut().setUnite(UniteTemps.JOUR);
        sequence.setFin(new TempsPrescription());
        sequence.getFin().setNb(1);
        sequence.getFin().setUnite(UniteTemps.JOUR);
        Mockito.when(this.helper.convertTime(Matchers.any(Calendar.class),
                                             Matchers.any(TempsPrescription.class)))
                .thenReturn(Calendar.getInstance());
        Assert.assertNotNull(this.converter.convert(sequence,
                                                    Calendar.getInstance()));
    }
}
