package fr.pharma.eclipse.processor.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.sigrec.common.SigrecElement;
import fr.pharma.eclipse.processor.filter.SigrecFilter;
import fr.pharma.eclipse.processor.impl.FilterProcessor;

/**
 * Test du processor FilterProcessor.
 
 * @version $Revision$ $Date$
 */
public class FilterProcessorTest
{
    /**
     * Processor.
     */
    private FilterProcessor<SigrecElement> processor;

    /**
     * Filter mck.
     */
    private SigrecFilter<SigrecElement> filter;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp()
    {
        this.processor = new FilterProcessor<SigrecElement>();
        final List<SigrecFilter<SigrecElement>> filters =
            new ArrayList<SigrecFilter<SigrecElement>>();
        this.filter = Mockito.mock(SigrecFilter.class);
        filters.add(this.filter);
        this.processor.setFilters(filters);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown()
    {
        this.processor = null;
    }

    /**
     * Test de l'initialisation.
     */
    public void testInit()
    {
        Assert.assertNotNull(this.processor);
        Assert.assertTrue(this.processor.getFilters().contains(this.filter));
    }
    @Test
    public void processWithFilterOk()
    {
        final SigrecElement element = Mockito.mock(SigrecElement.class);
        Mockito.when(this.filter.support(element)).thenReturn(true);
        this.processor.process(element);
        Mockito.verify(this.filter,
                       Mockito.times(1)).support(element);
        Mockito.verify(this.filter).filter(element);
    }

    @Test
    public void processWithFilterKo()
    {
        final SigrecElement element = Mockito.mock(SigrecElement.class);
        Mockito.when(this.filter.support(element)).thenReturn(false);
        this.processor.process(element);
        Mockito.verify(this.filter,
                       Mockito.times(1)).support(element);
        Mockito.verify(this.filter,
                       Mockito.never()).filter(element);
    }

    @Test
    public void processWithoutFilters()
    {
        this.processor.getFilters().clear();
        final SigrecElement element = Mockito.mock(SigrecElement.class);
        this.processor.process(element);
        Mockito.verify(this.filter,
                       Mockito.never()).support(element);
        Mockito.verify(this.filter,
                       Mockito.never()).filter(element);
    }
}
