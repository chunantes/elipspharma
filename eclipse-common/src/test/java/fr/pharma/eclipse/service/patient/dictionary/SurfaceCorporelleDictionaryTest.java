package fr.pharma.eclipse.service.patient.dictionary;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.patient.FormuleSurfaceCorporelle;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe SurfaceCorporelleDictionary.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SurfaceCorporelleDictionaryTest extends AbstractEclipseJUnitTest {
    /**
     * Dictionnary.
     */
    private SurfaceCorporelleDictionary dictionary;

    /**
     * Processor mocké.
     */
    private SurfaceCorporelleProcessor mockedProcessor;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedProcessor = Mockito.mock(SurfaceCorporelleProcessor.class);
        this.dictionary = new SurfaceCorporelleDictionary();
        final Map<FormuleSurfaceCorporelle, SurfaceCorporelleProcessor> map = new HashMap<FormuleSurfaceCorporelle, SurfaceCorporelleProcessor>();
        map.put(FormuleSurfaceCorporelle.DUBOIS, this.mockedProcessor);
        this.dictionary.setDictionary(map);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.mockedProcessor = null;
        this.dictionary = null;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.dictionary);
        Assert.assertNotNull(this.mockedProcessor);
    }

    /**
     * Test de la méthode process().
     */
    @Test
    public void testProces() {
        Mockito.when(this.mockedProcessor.process(4, 5)).thenReturn(new Double(10));
        final double result = this.dictionary.process(FormuleSurfaceCorporelle.DUBOIS, 4, 5);
        Assert.assertTrue(result == 10.0);
        Mockito.verify(this.mockedProcessor).process(4, 5);
    }

}
