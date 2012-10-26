package fr.pharma.eclipse.component.inclusion;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.primefaces.event.SelectEvent;

import fr.pharma.eclipse.component.inclusion.InclusionManager;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.patient.PatientService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Test de la classe InclusionManager.
 
 * @version $Revision$ $Date$
 */
public class InlcusionManagerTest
    extends AbstractEclipseJUnitTest
{

    /**
     * Manager.
     */
    private InclusionManager manager;

    /**
     * Faces utils mocké.
     */
    private FacesUtils mockedFacesUtils;

    /**
     * PatientService mocké.
     */
    private PatientService mockedPatientService;

    /**
     * Service inclusion.
     */
    private GenericService<Inclusion> mockedInclusionService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp()
    {
        this.mockedFacesUtils = Mockito.mock(FacesUtils.class);
        this.mockedInclusionService = Mockito.mock(GenericService.class);
        this.mockedPatientService = Mockito.mock(PatientService.class);
        this.manager = new InclusionManager(this.mockedInclusionService);
        this.manager.setPatientService(this.mockedPatientService);
        this.manager.setFacesUtils(this.mockedFacesUtils);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown()
    {
        this.mockedFacesUtils = null;
        this.mockedInclusionService = null;
        this.mockedPatientService = null;
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
        Assert.assertNotNull(this.mockedFacesUtils);
        Assert.assertNotNull(this.mockedInclusionService);
        Assert.assertNotNull(this.mockedPatientService);
    }

    /**
     * Test de la méthode init().
     */
    @Test
    public void testInitMethod()
    {
        this.manager.setEssaiSelected(new Essai());
        this.manager.setPatientSelected(new Patient());
        this.manager.setValid(true);
        this.manager.init();
        Assert.assertNull(this.manager.getEssaiSelected());
        Assert.assertNull(this.manager.getPatientSelected());
        Assert.assertFalse(this.manager.getValid());
    }

    /**
     * Test de la méthode handleSelectPatient.
     */
    @Test
    public void testHandleSelectPatientOk()
    {
        final SelectEvent event = Mockito.mock(SelectEvent.class);
        final Patient p = new Patient();
        Mockito.when(event.getObject()).thenReturn(p);

        this.manager.handleSelectPatient(event);

        Mockito.verify(this.mockedFacesUtils,
                       Mockito.never()).addMessage(Matchers.any(Severity.class),
                                                   Matchers.anyString());
        Assert.assertTrue(this.manager.getValid());
    }

    /**
     * Test de la méthode handleSelectPatient.
     */
    @Test
    public void testHandleSelectPatientKo()
    {
        final SelectEvent event = Mockito.mock(SelectEvent.class);
        final Patient p = new Patient();
        final Inclusion i = new Inclusion();
        i.setActif(true);
        p.getInclusions().add(i);

        Mockito.when(event.getObject()).thenReturn(p);
        Mockito
                .when(this.mockedPatientService.getInclusionCourante(Matchers.any(Patient.class)))
                .thenReturn(i);

        this.manager.handleSelectPatient(event);

        Mockito.verify(this.mockedFacesUtils).addMessage(FacesMessage.SEVERITY_ERROR,
                                                         "patient.inclu.error");
        Assert.assertFalse(this.manager.getValid());
    }

    /**
     * Test de la méthode confirmInclusion().
     */
    @Test
    public void testConfirmInclusion()
    {
        this.manager.confirmInclusion();
        Mockito.verify(this.mockedFacesUtils).addMessage(FacesMessage.SEVERITY_INFO,
                                                         "patient.inclu.ok");
        Assert.assertFalse(this.manager.getValid());
        Assert.assertNull(this.manager.getEssaiSelected());
        Assert.assertNull(this.manager.getPatientSelected());
    }
}
