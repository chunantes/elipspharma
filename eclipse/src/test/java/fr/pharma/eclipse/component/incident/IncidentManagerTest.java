package fr.pharma.eclipse.component.incident;

import java.util.ArrayList;

import javax.faces.model.DataModel;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.faces.model.SerializableListDataModel;

import fr.pharma.eclipse.component.helper.BeanManagerHelper;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.incident.Incident;
import fr.pharma.eclipse.factory.incident.IncidentFactory;
import fr.pharma.eclipse.service.incident.IncidentService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe IncidentManager
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class IncidentManagerTest extends AbstractEclipseJUnitTest {

    /**
     * Manager en test.
     */
    private IncidentManager incidentManager;

    /**
     * Factory d'incident.
     */
    private IncidentFactory incidentFactory;

    /**
     * Incident service.
     */
    private IncidentService incidentService;

    /**
     * Helper.
     */
    private BeanManagerHelper<Incident> helper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.helper = Mockito.mock(BeanManagerHelper.class);
        this.incidentService = Mockito.mock(IncidentService.class);
        this.incidentFactory = Mockito.mock(IncidentFactory.class);
        this.incidentManager = new IncidentManager(this.incidentService);
        this.incidentManager.setIncidentFactory(this.incidentFactory);
        this.incidentManager.setHelper(this.helper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.incidentManager = null;
        this.incidentFactory = null;
        this.incidentService = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.incidentFactory);
        Assert.assertNotNull(this.incidentService);
        Assert.assertNotNull(this.incidentManager);
    }

    /**
     * Test de la méthode init().
     */
    @Test
    public void testInitMethodEssaiNull() {
        Mockito.when(this.incidentFactory.getInitializedObject()).thenReturn(new Incident());
        this.incidentManager.init(null);
        Assert.assertNotNull(this.incidentManager.getBean());
        Assert.assertNull(this.incidentManager.getBean().getEssai());
    }
    /**
     * Test de la méthode init().
     */
    @Test
    public void testInitMethodEssaiNotNull() {
        final Essai essai = Mockito.mock(Essai.class);
        final Incident incident = new Incident();
        incident.setEssai(essai);
        Mockito.when(this.incidentFactory.getInitializedObject(essai)).thenReturn(incident);
        this.incidentManager.init(essai);
        Assert.assertNotNull(this.incidentManager.getBean());
        Assert.assertEquals(this.incidentManager.getBean().getEssai(), essai);
    }

    /**
     * Test de la méthode findIncidentsForEssai.
     */
    @Test
    public void testFindIncidentsForEssai() {
        final Essai essai = Mockito.mock(Essai.class);
        Mockito.when(essai.getId()).thenReturn(null);
        Mockito.when(this.incidentService.getAll(Matchers.any(SearchCriteria.class))).thenReturn(new ArrayList<Incident>());
        Mockito.when(this.helper.returnAsDataModel(Matchers.anyCollection())).thenReturn(new SerializableListDataModel());
        final DataModel<Incident> incidents = this.incidentManager.findIncidentsForEssai(essai);
        Mockito.verify(this.incidentService, Mockito.never()).getAll(Matchers.any(SearchCriteria.class));
    }

    /**
     * Test de la méthode findIncidentsForEssai.
     */
    @Test
    public void testFindIncidentsForEssaiok() {
        final Essai essai = Mockito.mock(Essai.class);
        Mockito.when(essai.getId()).thenReturn(1L);
        Mockito.when(this.incidentService.getAll(Matchers.any(SearchCriteria.class))).thenReturn(new ArrayList<Incident>());
        Mockito.when(this.helper.returnAsDataModel(Matchers.anyCollection())).thenReturn(new SerializableListDataModel());
        final DataModel<Incident> incidents = this.incidentManager.findIncidentsForEssai(essai);
        Mockito.verify(this.incidentService, Mockito.times(1)).getAll(Matchers.any(SearchCriteria.class));
    }

    /**
     * Test de la méthode ajouterIncident.
     */
    @Test
    public void testAjouterIncident() {
        final Incident incident = Mockito.mock(Incident.class);
        this.incidentManager.setBean(incident);
        this.incidentManager.ajouterIncident();
        Mockito.verify(this.incidentService).save(incident);
    }

    /**
     * Test de la méthode reinit().
     */
    @Test
    public void testReinit() {
        this.incidentManager.setBean(new Incident());
        this.incidentManager.reinit();
        Assert.assertNull(this.incidentManager.getBean());
    }
}
