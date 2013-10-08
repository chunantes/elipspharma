package fr.pharma.eclipse.utils.converter.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;
import fr.pharma.eclipse.utils.converter.filler.Filler;
import fr.pharma.eclipse.utils.introspection.GenericFetcher;

/**
 * Classe de test de GenericConverterManager.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GenericConverterTest {
    /**
     * Converter.
     */
    GenericBeanConverter<Essai, BeanObject> converter;

    /**
     * Factory
     */
    private BeanObjectFactory<BeanObject> factory;

    /**
     * fetcher.
     */
    private GenericFetcher<Essai, BeanObject> fetcher;

    /**
     * Filler.
     */
    private Filler<Essai, BeanObject> filler;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp() {
        this.fetcher = Mockito.mock(GenericFetcher.class);
        this.filler = Mockito.mock(Filler.class);
        this.factory = Mockito.mock(BeanObjectFactory.class);
        this.converter = new GenericBeanConverter<Essai, BeanObject>(this.fetcher, this.factory);
        final List<Filler<Essai, BeanObject>> fillers = new ArrayList<Filler<Essai, BeanObject>>();
        fillers.add(this.filler);
        this.converter.setFillers(fillers);
    }
    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown() {
        this.fetcher = null;
        this.factory = null;
        this.filler = null;
        this.converter = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInitialisation() {
        Assert.assertNotNull(this.fetcher);
        Assert.assertNotNull(this.converter);
        Assert.assertTrue(this.converter.getFillers().contains(this.filler));
    }
    /**
     * Test de la méthode convert();
     */
    @Test
    public void convert() {
        final Essai bean = Mockito.mock(Essai.class);
        final BeanObject dest = Mockito.mock(BeanObject.class);
        Mockito.when(this.filler.support(bean)).thenReturn(true);
        Mockito.when(this.factory.getInitializedObject()).thenReturn(dest);

        this.converter.convert(bean);

        Mockito.verify(this.factory, Mockito.times(1)).getInitializedObject();
        Mockito.verify(this.fetcher, Mockito.times(1)).fetch(Matchers.any(Essai.class), Matchers.any(BeanObject.class));
        Mockito.verify(this.filler, Mockito.times(1)).fill(Matchers.any(Essai.class), Matchers.any(BeanObject.class));

    }
}
