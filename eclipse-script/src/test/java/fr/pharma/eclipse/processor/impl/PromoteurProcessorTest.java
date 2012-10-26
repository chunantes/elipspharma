package fr.pharma.eclipse.processor.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.sigrec.SigrecMultiTrialsTransfert;
import fr.pharma.eclipse.domain.model.sigrec.SponsorType;
import fr.pharma.eclipse.domain.model.sigrec.acteur.PromoteurSigrec;
import fr.pharma.eclipse.processor.helper.SponsorExtractorHelper;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.converter.BeanConverter;

/**
 * Classe de test de PromoteurProcessor.
 
 * @version $Revision$ $Date$
 */
public class PromoteurProcessorTest
{
    /**
     * Processor à tester.
     */
    private PromoteurProcessor processor;

    /**
     * Helper mocké.
     */
    private SponsorExtractorHelper helper;

    /**
     * COnverter Mocké.
     */
    private BeanConverter<SponsorType, PromoteurSigrec> converter;

    /**
     * Service mocké.
     */
    private GenericService<PromoteurSigrec> service;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp()
    {
        this.helper = Mockito.mock(SponsorExtractorHelper.class);
        this.converter = Mockito.mock(BeanConverter.class);
        this.service = Mockito.mock(GenericService.class);
        this.processor = new PromoteurProcessor();
        this.processor.setExtractor(this.helper);
        this.processor.setConverter(this.converter);
        this.processor.setService(this.service);
    }
    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown()
    {
        this.helper = null;
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
        Assert.assertEquals(this.helper,
                            this.processor.getExtractor());
        Assert.assertEquals(this.converter,
                            this.processor.getConverter());
        Assert.assertEquals(this.service,
                            this.processor.getService());
    }

    /**
     * Test de la méthode process.
     */
    @Test
    public void testProcess()
    {
        final SigrecMultiTrialsTransfert bean = new SigrecMultiTrialsTransfert();
        final List<SponsorType> liste = new ArrayList<SponsorType>();
        liste.add(new SponsorType());
        liste.add(new SponsorType());
        Mockito.when(this.helper.extract(bean)).thenReturn(liste);
        this.processor.process(bean);
        Mockito.verify(this.helper,
                       Mockito.times(1)).extract(bean);
        Mockito.verify(this.converter,
                       Mockito.times(2)).convert(Matchers.any(SponsorType.class));
        Mockito.verify(this.service,
                       Mockito.times(1)).saveAll(Matchers.anyList());
    }
}
