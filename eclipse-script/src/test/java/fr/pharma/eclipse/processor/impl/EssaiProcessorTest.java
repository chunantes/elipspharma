package fr.pharma.eclipse.processor.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.sigrec.MultiTrialType;
import fr.pharma.eclipse.domain.model.sigrec.SigrecMultiTrialsTransfert;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.converter.BeanConverter;

/**
 * Test du processor EssaiProcessor.
 
 * @version $Revision$ $Date$
 */
public class EssaiProcessorTest
{

    /**
     * Processor à tester.
     */
    private EssaiProcessor processor;

    /**
     * COnverter Mocké.
     */
    private BeanConverter<TrialType, EssaiSigrec> converter;

    /**
     * Service mocké.
     */
    private GenericService<EssaiSigrec> service;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp()
    {
        this.converter = Mockito.mock(BeanConverter.class);
        this.service = Mockito.mock(GenericService.class);
        this.processor = new EssaiProcessor();
        this.processor.setConverter(this.converter);
        this.processor.setEssaiService(this.service);
    }
    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown()
    {
        this.converter = null;
        this.service = null;
        this.processor = null;
    }
    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.processor);
        Assert.assertEquals(this.converter,
                            this.processor.getConverter());
        Assert.assertEquals(this.service,
                            this.processor.getEssaiService());
    }

    /**
     * Test de la méthode process.
     */
    @Test
    public void testProcess()
    {
        final SigrecMultiTrialsTransfert bean = new SigrecMultiTrialsTransfert();
        bean.setMultiTrial(new MultiTrialType());
        bean.getMultiTrial().getTrial().add(new TrialType());
        bean.getMultiTrial().getTrial().add(new TrialType());
        this.processor.process(bean);
        Mockito.verify(this.converter,
                       Mockito.times(2)).convert(Matchers.any(TrialType.class));
        Mockito.verify(this.service,
                       Mockito.times(1)).saveAll(Matchers.anyList());
    }
}
