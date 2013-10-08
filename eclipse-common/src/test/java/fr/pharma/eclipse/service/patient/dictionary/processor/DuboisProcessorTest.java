package fr.pharma.eclipse.service.patient.dictionary.processor;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du processor DuboisProcessor.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DuboisProcessorTest extends AbstractEclipseJUnitTest {
    /**
     * Processor.
     */
    private DuboisProcessor processor;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.processor = new DuboisProcessor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.processor = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.processor);
    }

    /**
     * Test de la méthode process.
     */
    @Test
    public void testProcess() {
        Assert.assertTrue(1.893869096533032 == this.processor.process(181, 70));
    }

}
