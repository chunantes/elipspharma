package fr.pharma.eclipse.service.dispensation.processor.helper;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.design.UniteTemps;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe UniteTempsConverter.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class UniteTempsConverterTest extends AbstractEclipseJUnitTest {

    /**
     * Converter à tester.
     */
    private UniteTempsConverter converter;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.converter = new UniteTempsConverter();
        final Map<UniteTemps, Integer> conversion = new HashMap<UniteTemps, Integer>();
        conversion.put(UniteTemps.MOIS, 30);
        conversion.put(UniteTemps.JOUR, 1);
        conversion.put(UniteTemps.SEMAINE, 7);
        this.converter.setConversion(conversion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.converter = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.converter);
    }

    /**
     * Test de la méthode support.
     */
    @Test
    public void testSupportKo() {
        Assert.assertFalse(this.converter.support(UniteTemps.MINUTE));
    }

    /**
     * Test de la méthode support.
     */
    @Test
    public void testSupportOk() {
        Assert.assertTrue(this.converter.support(UniteTemps.SEMAINE));
    }

    /**
     * Test de la méthode convert.
     */
    @Test
    public void testConvert() {
        Assert.assertEquals(35, this.converter.convert(5, UniteTemps.SEMAINE));
    }

    /**
     * Test de la méthode convert.
     */
    @Test
    public void testConvertJours() {
        Assert.assertEquals(5, this.converter.convert(5, UniteTemps.JOUR));
    }

    /**
     * Test de la méthode convert.
     */
    @Test
    public void testConvertMois() {
        Assert.assertEquals(60, this.converter.convert(2, UniteTemps.MOIS));
    }

}
