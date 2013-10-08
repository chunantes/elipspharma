package fr.pharma.eclipse.factory.dispensation;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test dela factory DispensationFactory.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationFactoryTest extends AbstractEclipseJUnitTest {

    /**
     * DispensationFactory.
     */
    private DispensationFactory factory;

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
        this.factory = new DispensationFactory(Dispensation.class);
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
    @Test
    public void testInit() {
        Assert.assertNotNull(this.factory);
        Assert.assertNotNull(this.mockedBeanFactory);
    }

    /**
     * Test de la méthode getInitializedObject().
     */
    @Test
    public void testGetInitializedObject() {
        Mockito.when(this.mockedBeanFactory.getBean(Dispensation.class.getSimpleName())).thenReturn(new Dispensation());
        final Dispensation d = this.factory.getInitializedObject(new Prescription());
        Assert.assertNotNull(d);
        Assert.assertNotNull(d.getPrescription());

    }
}
