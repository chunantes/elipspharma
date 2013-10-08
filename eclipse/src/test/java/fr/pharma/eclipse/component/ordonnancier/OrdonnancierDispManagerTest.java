package fr.pharma.eclipse.component.ordonnancier;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.ordonnancier.OrdonnancierSearchCriteria;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.ordonnancier.OrdonnancierDisp;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.ordonnancier.OrdonnancierService;
import fr.pharma.eclipse.service.stockage.PharmacieService;

/**
 * Classe en charge de tester le manager de l'ordonnancier des dispensations.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class OrdonnancierDispManagerTest {
    /**
     * OrdonnancierDispManager à tester.
     */
    private OrdonnancierDispManager manager;

    /**
     * Critère de recherche sur Ordonnancier.
     */
    private OrdonnancierSearchCriteria criteria;

    /**
     * Service de gestion des pharmacies mocké.
     */
    private PharmacieService mockPharmacieService;

    /**
     * Service de gestion des ordonnanciers de dispensation mocké.
     */
    private OrdonnancierService<OrdonnancierDisp> mockOrdonnancierDispService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.criteria = Mockito.mock(OrdonnancierSearchCriteria.class);
        this.manager = new OrdonnancierDispManager(this.criteria);
        this.mockPharmacieService = Mockito.mock(PharmacieService.class);
        this.mockOrdonnancierDispService = Mockito.mock(OrdonnancierService.class);
        this.manager.setPharmacieService(this.mockPharmacieService);
        this.manager.setOrdoService(this.mockOrdonnancierDispService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.criteria = null;
        this.manager = null;
        this.mockPharmacieService = null;
        this.mockOrdonnancierDispService = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInitData() {
        Assert.assertNotNull(this.criteria);
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.mockPharmacieService);
        Assert.assertNotNull(this.mockOrdonnancierDispService);
    }

    /**
     * Méthode en charge de tester la méthode init.
     */
    @Test
    public void testInitWithPharma() {
        final List<Pharmacie> pharmacies = new ArrayList<Pharmacie>();
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        pharmacies.add(pharmacie);
        Mockito.when(this.mockPharmacieService.getAll((SearchCriteria) Matchers.any())).thenReturn(pharmacies);

        this.manager.init();
        Mockito.verify(this.mockOrdonnancierDispService).getDateDebut(pharmacie);
        Mockito.verify(this.mockOrdonnancierDispService).getDateFin();
    }

    /**
     * Méthode en charge de tester la méthode init.
     */
    @Test
    public void testInitWithEmptyPharma() {
        final List<Pharmacie> pharmacies = new ArrayList<Pharmacie>();
        Mockito.when(this.mockPharmacieService.getAll((SearchCriteria) Matchers.any())).thenReturn(pharmacies);

        this.manager.init();
        Mockito.verify(this.mockOrdonnancierDispService, Mockito.times(0)).getDateDebut((Pharmacie) Matchers.any());
        Mockito.verify(this.mockOrdonnancierDispService).getDateFin();
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM suite à la
     * sélection d'une pharmacie.
     */
    @Test
    public void testHandleSelectPharmacie() {
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        Mockito.when(select.getLocalValue()).thenReturn(pharmacie);

        this.manager.handleSelectPharmacie(event);
        Mockito.verify(this.mockOrdonnancierDispService).getDateDebut(pharmacie);
    }

    /**
     * Méthode en charge de tester la récupération des initiales patient.
     */
    @Test
    public void testGetInitialesPatient() {
        final Dispensation dispensation = new Dispensation();
        final Prescription prescription = new Prescription();
        final Inclusion inclusion = new Inclusion();
        final Patient patient = new Patient();
        patient.setPrenom("celine");
        patient.setNom("gilet");
        inclusion.setPatient(patient);
        prescription.setInclusion(inclusion);
        dispensation.setPrescription(prescription);
        final String initiales = this.manager.getInitialesPatient(dispensation);
        Assert.assertEquals("C G", initiales);
    }

    /**
     * Méthode en charge de tester le set sur Pharmacies.
     */
    @Test
    public void testSetPharmacies() {
        final List<Pharmacie> pharmacies = new ArrayList<Pharmacie>();
        this.manager.setPharmacies(pharmacies);
        Assert.assertNotNull(this.manager.getPharmacies());
    }

    /**
     * Méthode en charge de tester la récupération des dispensations d'un
     * ordonnancier.
     */
    @Test
    public void testGetListDispensationsWithOrdoNull() {
        this.manager.setOrdonnancier(null);
        Assert.assertEquals(0, this.manager.getListDispensations().size());
    }

    /**
     * Méthode en charge de tester la récupération des dispensations d'un
     * ordonnancier.
     */
    @Test
    public void testGetListDispensationsWithOrdo() {
        final OrdonnancierDisp ordonnancier = new OrdonnancierDisp();
        this.manager.setOrdonnancier(ordonnancier);
        Assert.assertEquals(0, this.manager.getListDispensations().size());
    }

    /**
     * Méthode en charge de tester la récupération des dispensations de produit
     * d'une dispensation.
     */
    @Test
    public void testGetListDispensationsProduit() {
        final Dispensation dispensation = new Dispensation();
        Assert.assertEquals(0, this.manager.getListDispensationsProduit(dispensation).size());
    }

}
