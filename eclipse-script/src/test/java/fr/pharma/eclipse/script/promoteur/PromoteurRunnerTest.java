package fr.pharma.eclipse.script.promoteur;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.core.context.SecurityContextHolder;

import fr.pharma.eclipse.processor.impl.PromoteurEclipseProcessor;

/**
 * Test du runner PromoteurRunner.
 
 * @version $Revision$ $Date$
 */
public class PromoteurRunnerTest
{

    /**
     * Runner à tester.
     */
    private PromoteurRunner runner;

    /**
     * Processor mocké.
     */
    private PromoteurEclipseProcessor processor;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp()
    {
        this.processor = Mockito.mock(PromoteurEclipseProcessor.class);
        this.runner = new PromoteurRunner();
        this.runner.setPromoteurProcessor(this.processor);
    }
    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown()
    {
        this.processor = null;
        this.runner = null;
    }

    /**
     *Test init.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.runner);
    }
    /**
     * Test de la méthode run().
     */
    @Test
    public void testRun()
    {
        this.runner.run(null);
        Assert.assertEquals("Sigrec",
                            SecurityContextHolder.getContext().getAuthentication().getName());
        Mockito.verify(this.processor,
                       Mockito.times(1)).process();
    }
}
