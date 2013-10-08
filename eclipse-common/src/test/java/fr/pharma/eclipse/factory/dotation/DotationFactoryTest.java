package fr.pharma.eclipse.factory.dotation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.model.dotation.Dotation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.localisation.Service;

/**
 * Test de la fabrique de Dotation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DotationFactoryTest {
    /**
     * Fabrique testée.
     */
    private DotationFactory factory;

    /**
     * Factory Spring mockée.
     */
    private BeanFactory mockedBeanFactory;

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void init() {
        this.mockedBeanFactory = Mockito.mock(BeanFactory.class);
        this.factory = new DotationFactory(Dotation.class);
        this.factory.setBeanFactory(this.mockedBeanFactory);
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void end() {
        this.factory = null;
        this.mockedBeanFactory = null;
    }

    /**
     * Test de la méthode getInitializedObject.
     */
    @Test
    public void testGetInitializedObject() {
        final Dotation expectedDotation = Mockito.mock(Dotation.class);
        final String expectedClassName = Dotation.class.getSimpleName();
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(expectedDotation);

        final Dotation created = this.factory.getInitializedObject();
        Mockito.verify(this.mockedBeanFactory).getBean(expectedClassName);
        Assert.assertEquals(expectedDotation, created);
    }

    /**
     * Test de la méthode getInitializedObject.
     */
    @Test
    public void testGetInitializedObjectWithParams() {
        final Dotation expectedDotation = Mockito.mock(Dotation.class);
        final String expectedClassName = Dotation.class.getSimpleName();
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(expectedDotation);

        final Service service = Mockito.mock(Service.class);
        final Essai essai = Mockito.mock(Essai.class);

        final Dotation created = this.factory.getInitializedObject(essai, service);
        Mockito.verify(this.mockedBeanFactory).getBean(expectedClassName);
        Assert.assertEquals(expectedDotation, created);
        Mockito.verify(expectedDotation).setService(service);
        Mockito.verify(expectedDotation).setTraitee(Boolean.FALSE);
    }

}
