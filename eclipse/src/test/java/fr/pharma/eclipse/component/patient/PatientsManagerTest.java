package fr.pharma.eclipse.component.patient;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.primefaces.event.SelectEvent;

import fr.pharma.eclipse.domain.criteria.patient.PatientSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 *Test du manager PatientsManager.
 
 * @version $Revision$ $Date$
 */
public class PatientsManagerTest
    extends AbstractEclipseJUnitTest
{
    /**
     * Manager.
     */
    private PatientsManager manager;

    /**
     * Critere.
     */
    private PatientSearchCriteria criteria;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp()
    {
        this.criteria = new PatientSearchCriteria();
        this.manager = new PatientsManager(this.criteria);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown()
    {
        this.manager = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.criteria);
    }

    /**
     * Test de la méthode handleSelectEssai.
     */
    @Test
    public void testHandleSelectEssai()
    {
        final SelectEvent event = Mockito.mock(SelectEvent.class);
        Mockito.when(event.getObject()).thenReturn(new Essai());
        this.manager.handleSelectEssai(event);
        Assert.assertNotNull(this.criteria.getEssai());
    }
}
