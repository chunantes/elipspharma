package fr.pharma.eclipse.factory.inclusion;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la factory InclusionFactory.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class InclusionFactoryTest extends AbstractEclipseJUnitTest {

    /**
     * InclusionFactory.
     */
    private InclusionFactory factory;

    /**
     * Factory Spring mockée.
     */
    private BeanFactory mockedBeanFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedBeanFactory = Mockito.mock(BeanFactory.class);
        this.factory = new InclusionFactory(Inclusion.class);
        this.factory.setBeanFactory(this.mockedBeanFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.factory = null;
        this.mockedBeanFactory = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.factory);
        Assert.assertNotNull(this.mockedBeanFactory);
    }

    /**
     * Test de la méthode getInitializedObject().
     */
    @Test
    public void testGetInitializedObject() {
        Mockito.when(this.mockedBeanFactory.getBean(Inclusion.class.getSimpleName())).thenReturn(new Inclusion());
        final Inclusion h = this.factory.getInitializedObject();
        Assert.assertNotNull(h);
        Assert.assertTrue(h.getActif());

    }
}
