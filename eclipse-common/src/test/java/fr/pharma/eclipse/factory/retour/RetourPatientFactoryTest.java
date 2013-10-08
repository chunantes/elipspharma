package fr.pharma.eclipse.factory.retour;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.enums.EtatRetour;
import fr.pharma.eclipse.domain.model.stock.RetourPatient;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la factory RetourPatientFactory.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class RetourPatientFactoryTest extends AbstractEclipseJUnitTest {
    /**
     * RetourPatientFactory.
     */
    private RetourPatientFactory factory;

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
        this.factory = new RetourPatientFactory(RetourPatient.class);
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
        Mockito.when(this.mockedBeanFactory.getBean(RetourPatient.class.getSimpleName())).thenReturn(new RetourPatient());
        final RetourPatient h = this.factory.getInitializedObject();
        Assert.assertNotNull(h);
        Assert.assertEquals(h.getEtat(), EtatRetour.PRESENT);

    }

}
