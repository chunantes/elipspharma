package fr.pharma.eclipse.factory.incident;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.incident.Incident;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la factory IncidentFactory.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class IncidentFactoryTest extends AbstractEclipseJUnitTest {

    /**
     * Factory.
     */
    private IncidentFactory factory;

    /**
     * Factory spring.
     */
    private BeanFactory beanFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.beanFactory = Mockito.mock(BeanFactory.class);
        this.factory = new IncidentFactory(Incident.class);
        this.factory.setBeanFactory(this.beanFactory);
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
    @Test
    public void testInit() {
        Assert.assertNotNull(this.factory);
    }

    /**
     * Test de la méthode .
     */
    @Test
    public void testGetInitializedObject() {
        Mockito.when(this.beanFactory.getBean(Matchers.anyString())).thenReturn(new Incident());
        final Incident incident = this.factory.getInitializedObject();
        Assert.assertNotNull(incident);
        Assert.assertNotNull(incident.getDate());
    }

    /**
     * Test de la méthode .
     */
    @Test
    public void testGetInitializedObjectEssai() {
        Mockito.when(this.beanFactory.getBean(Matchers.anyString())).thenReturn(new Incident());
        final Incident incident = this.factory.getInitializedObject(new Essai());
        Assert.assertNotNull(incident);
        Assert.assertNotNull(incident.getDate());
        Assert.assertNotNull(incident);
        Assert.assertNotNull(incident.getEssai());
    }
}
