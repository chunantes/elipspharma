package fr.pharma.eclipse.factory.patient;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.model.patient.HistoriquePatient;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la factory HistoriquePatientFactory.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class HistoriquePatientFactoryTest extends AbstractEclipseJUnitTest {

    /**
     * HistoriquePatientFactory.
     */
    private HistoriquePatientFactory factory;

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
        this.factory = new HistoriquePatientFactory(HistoriquePatient.class);
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
        Mockito.when(this.mockedBeanFactory.getBean(HistoriquePatient.class.getSimpleName())).thenReturn(new HistoriquePatient());
        final Patient patient = new Patient();
        patient.setId(1L);
        final HistoriquePatient h = this.factory.getInitializedObject(patient);
        Assert.assertNotNull(h);
        Assert.assertNotNull(h.getDate());
        Assert.assertEquals(patient.getId(), h.getPatient().getId());

    }
}
