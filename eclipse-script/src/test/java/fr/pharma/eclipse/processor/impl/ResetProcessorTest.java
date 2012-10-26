package fr.pharma.eclipse.processor.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Test du processor : ResetProcessor.
 
 * @version $Revision$ $Date$
 */
public class ResetProcessorTest
{
    /**
     * Processor à tester.
     */
    private ResetProcessor processor;

    /**
     * Service des promoteurs Sigrec mocké.
     */
    private GenericService<EssaiSigrec> essaiSigrecService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp()
    {
        this.essaiSigrecService = Mockito.mock(GenericService.class);

        this.processor = new ResetProcessor();
        this.processor.setEssaiSigrecService(this.essaiSigrecService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown()
    {
        this.essaiSigrecService = null;
        this.processor = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.processor);
    }

    /**
     * Test de la méthode process().
     */
    @Test
    public void testProcess()
    {
        this.processor.process(null);
        Mockito.verify(this.essaiSigrecService,
                       Mockito.times(1)).getAll();
        Mockito.verify(this.essaiSigrecService,
                       Mockito.times(1)).remove(Matchers.anyList());
    }
}
