package fr.pharma.eclipse.factory.design;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la factory PrescriptionTypeFactory.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PrescriptionTypeFactoryTest extends AbstractEclipseJUnitTest {

    /**
     * Factory de PrescriptionType.
     */
    private PrescriptionTypeFactory factory;

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
        this.factory = new PrescriptionTypeFactory(PrescriptionType.class);
        this.factory.setBeanFactory(this.mockedBeanFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.factory = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.factory);
    }

    /**
     * Test de la méthdoe : getInitializedObject.
     */
    @Test
    public void testGetInitializedObject() {
        final Sequence sequence = new Sequence();
        sequence.setId(1L);

        Mockito.when(this.mockedBeanFactory.getBean(PrescriptionType.class.getSimpleName())).thenReturn(new PrescriptionType());

        final PrescriptionType result = this.factory.getInitializedObject(sequence);

        Assert.assertEquals(sequence.getId(), result.getSequence().getId());
    }

}
