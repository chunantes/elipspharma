package fr.pharma.eclipse.service.patient.dictionary.processor;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de MostellerProcessor.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class MostellerProcessorTest extends AbstractEclipseJUnitTest {
    /**
     * Processor.
     */
    private MostellerProcessor processor;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.processor = new MostellerProcessor();
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
    @Test
    @Override
    public void testInit() {
        Assert.assertNotNull(this.processor);
    }

    /**
     * Test de la méthode process().
     */
    @Test
    public void testProcess() {
        Assert.assertTrue(1.876018242034028 == this.processor.process(181, 70));
    }

}
