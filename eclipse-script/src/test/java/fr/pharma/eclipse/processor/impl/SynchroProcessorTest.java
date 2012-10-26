package fr.pharma.eclipse.processor.impl;

import java.io.File;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

/**
 * Test du processor : SynchroProcessor.
 
 * @version $Revision$ $Date$
 */
public class SynchroProcessorTest
{
    /**
     * Processor à tester.
     */
    private SynchroProcessor processor;

    /**
     * Contexte Camel.
     */
    private CamelContext context;

    /**
     * Set Up.
     * @throws Exception
     */
    @Before
    public void setUp()
    {
        this.processor = new SynchroProcessor();
        this.context = Mockito.mock(CamelContext.class);
        this.processor.setCamelContext(this.context);
        this.processor.setCheminFichier("src/test/resources/example.xml");
    }
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.processor);
        Assert.assertEquals(this.context,
                            this.processor.getCamelContext());
        Assert.assertEquals("src/test/resources/example.xml",
                            this.processor.getCheminFichier());
    }

    /**
     * Test.
     */
    @Test
    public void test()
    {
        final ProducerTemplate t = Mockito.mock(ProducerTemplate.class);
        Mockito.when(this.context.createProducerTemplate()).thenReturn(t);
        this.processor.process();
        Mockito.verify(t,
                       Mockito.times(1)).sendBody(Matchers.anyString(),
                                                  Matchers.any(File.class));
    }

}
