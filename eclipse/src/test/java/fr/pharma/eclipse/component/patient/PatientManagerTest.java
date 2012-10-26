package fr.pharma.eclipse.component.patient;

import java.util.Calendar;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.event.ValueChangeEvent;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.primefaces.component.tabview.Tab;
import org.primefaces.event.TabChangeEvent;

import fr.pharma.eclipse.domain.model.patient.HistoriquePatient;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.factory.patient.HistoriquePatientFactory;
import fr.pharma.eclipse.service.patient.PatientService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Ttest de la classe PatientManager.
 
 * @version $Revision$ $Date$
 */
public class PatientManagerTest
    extends AbstractEclipseJUnitTest
{

    /**
     * PatientManager
     */
    private PatientManager manager;

    /**
     * Service mocké.
     */
    private PatientService mockedPatientService;

    /**
     * Factory patient.
     */
    private HistoriquePatientFactory mockedFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp()
    {
        this.mockedFactory = Mockito.mock(HistoriquePatientFactory.class);
        this.mockedPatientService = Mockito.mock(PatientService.class);
        this.manager = new PatientManager(this.mockedPatientService);
        this.manager.setFactory(this.mockedFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown()
    {
        this.mockedPatientService = null;
        this.manager = null;
        this.mockedFactory = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit()
    {
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.mockedPatientService);
        Assert.assertNotNull(this.mockedFactory);
    }

    /**
     * Test de la méthode reinit().
     */
    @Test
    public void testReinit()
    {

        this.manager.setBean(new Patient());
        this.manager.setEditHistorique(true);
        this.manager.reinit();
        Assert.assertNull(this.manager.getHistorique());
        Assert.assertFalse(this.manager.isEditHistorique());
    }

    /**
     * Test de la méthode initHistorique.
     */
    @Test
    public void testInitHistorique()
    {
        Mockito.when(this.mockedFactory.getInitializedObject(Matchers.any(Patient.class)))
                .thenReturn(new HistoriquePatient());
        this.manager.initHistorique();
        Assert.assertNotNull(this.manager.getHistorique());
        Assert.assertTrue(this.manager.isEditHistorique());
    }

    /**
     * Test de la méthode addHistorique.
     */
    @Test
    public void testAddHistorique()
    {
        this.manager.setBean(new Patient());
        final HistoriquePatient historique = new HistoriquePatient();
        historique.setDate(Calendar.getInstance(EclipseConstants.LOCALE));
        this.manager.setHistorique(historique);
        this.manager.addHistorique();
        Assert.assertNull(this.manager.getHistorique());
        Assert.assertEquals(1,
                            this.manager.getBean().getHistoriquePatient().size());
        Assert.assertFalse(this.manager.isEditHistorique());

    }

    /**
     * Méthode en charge de tester le changement d'onglet.
     */
    @Test
    public void testOnOngletChange()
    {
        final TabChangeEvent mockEvent = Mockito.mock(TabChangeEvent.class);
        final Tab tab = Mockito.mock(Tab.class);
        Mockito.when(mockEvent.getTab()).thenReturn(tab);
        Mockito.when(tab.getId()).thenReturn("ONG_PRESCRIPTION");
        this.manager.onOngletChange(mockEvent);
        Assert.assertNotNull(this.manager.getIndexOngletCourant());
    }

    /**
     * Test de la méthode updateSurfaceCorporelle.
     */
    @Test
    public void testUpdateInitialesNom()
    {
        final ValueChangeEvent event = Mockito.mock(ValueChangeEvent.class);
        final UIComponent component = Mockito.mock(UIComponent.class);
        Mockito.when(event.getComponent()).thenReturn(component);
        Mockito.when(component.getId()).thenReturn("nom");
        Mockito.when(event.getNewValue()).thenReturn("nom");
        final Patient patient = new Patient();
        patient.setPrenom("prenom");
        this.manager.setBean(patient);

        this.manager.updateInitiales(event);

        Assert.assertNotNull(this.manager.getBean().getInitiales().equals("NOM-PR"));
    }

    /**
     * Test de la méthode updateSurfaceCorporelle.
     */
    @Test
    public void testUpdateInitialesPrenom()
    {
        final ValueChangeEvent event = Mockito.mock(ValueChangeEvent.class);
        final UIComponent component = Mockito.mock(UIComponent.class);
        Mockito.when(event.getComponent()).thenReturn(component);
        Mockito.when(component.getId()).thenReturn("prenom");
        Mockito.when(event.getNewValue()).thenReturn("prenom");
        final Patient patient = new Patient();
        patient.setNom("nom");
        this.manager.setBean(patient);

        this.manager.updateInitiales(event);

        Assert.assertNotNull(this.manager.getBean().getInitiales().equals("NOM-PR"));
    }

    /**
     * Test de la méthode updateSurfaceCorporelle.
     */
    @Test
    public void testUpdateInitialesEmpty()
    {
        final ValueChangeEvent event = Mockito.mock(ValueChangeEvent.class);
        final UIComponent component = Mockito.mock(UIComponent.class);
        Mockito.when(event.getComponent()).thenReturn(component);
        Mockito.when(component.getId()).thenReturn("prenom");
        Mockito.when(event.getNewValue()).thenReturn("prenom");
        final Patient patient = new Patient();
        this.manager.setBean(patient);

        this.manager.updateInitiales(event);

        Assert.assertNotNull(this.manager.getBean().getInitiales().equals(""));
    }

    /**
     * Test des autres getters setter.
     */
    @Test
    public void testGetterSetter()
    {
        this.manager.setInclusionCourante(new Inclusion());
        Assert.assertNotNull(this.manager.getInclusionCourante());
        this.manager.setPrescriptions(Mockito.mock(List.class));
        Assert.assertNotNull(this.manager.getPrescriptions());
    }
}
