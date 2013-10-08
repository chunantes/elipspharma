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
import fr.pharma.eclipse.domain.model.dispensation.ElementToCheck;
import fr.pharma.eclipse.domain.model.ordonnancier.OrdonnancierFabReconst;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.ordonnancier.OrdonnancierService;
import fr.pharma.eclipse.service.stockage.PharmacieService;

/**
 * Classe en charge de tester le manager des ordonnanciers de
 * fabrication/reconstitution.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class OrdonnancierFabReconstManagerTest {

    /**
     * OrdonnancierFabReconstManager à tester.
     */
    private OrdonnancierFabReconstManager manager;

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
    private OrdonnancierService<OrdonnancierFabReconst> mockOrdonnancierFabReconstService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.criteria = Mockito.mock(OrdonnancierSearchCriteria.class);
        this.manager = new OrdonnancierFabReconstManager(this.criteria);
        this.mockPharmacieService = Mockito.mock(PharmacieService.class);
        this.mockOrdonnancierFabReconstService = Mockito.mock(OrdonnancierService.class);
        this.manager.setPharmacieService(this.mockPharmacieService);
        this.manager.setOrdoService(this.mockOrdonnancierFabReconstService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.criteria = null;
        this.manager = null;
        this.mockPharmacieService = null;
        this.mockOrdonnancierFabReconstService = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInitData() {
        Assert.assertNotNull(this.criteria);
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.mockPharmacieService);
        Assert.assertNotNull(this.mockOrdonnancierFabReconstService);
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
        Mockito.verify(this.mockOrdonnancierFabReconstService).getDateDebut(pharmacie);
        Mockito.verify(this.mockOrdonnancierFabReconstService).getDateFin();
        Assert.assertNull(this.manager.getOrdonnancier());
    }

    /**
     * Méthode en charge de tester la méthode init.
     */
    @Test
    public void testInitWithEmptyPharma() {
        final List<Pharmacie> pharmacies = new ArrayList<Pharmacie>();
        Mockito.when(this.mockPharmacieService.getAll((SearchCriteria) Matchers.any())).thenReturn(pharmacies);

        this.manager.init();
        Mockito.verify(this.mockOrdonnancierFabReconstService, Mockito.times(0)).getDateDebut((Pharmacie) Matchers.any());
        Mockito.verify(this.mockOrdonnancierFabReconstService).getDateFin();
        Assert.assertNull(this.manager.getOrdonnancier());
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
        Mockito.verify(this.mockOrdonnancierFabReconstService).getDateDebut(pharmacie);
    }

    /**
     * Méthode en charge de tester la récupération des initiales patient.
     */
    @Test
    public void testGetInitialesPatient() {
        final ElementToCheck elementToCheck = new ElementToCheck();
        final Dispensation dispensation = new Dispensation();
        final Prescription prescription = new Prescription();
        final Inclusion inclusion = new Inclusion();
        final Patient patient = new Patient();
        patient.setPrenom("celine");
        patient.setNom("gilet");
        inclusion.setPatient(patient);
        prescription.setInclusion(inclusion);
        dispensation.setPrescription(prescription);
        elementToCheck.setDispensation(dispensation);
        final String initiales = this.manager.getInitialesPatient(elementToCheck);
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
     * Méthode en charge de tester la récupération des elementsToCheck d'un
     * ordonnancier.
     */
    @Test
    public void testGetListElementsToCheckWithOrdoNull() {
        this.manager.setOrdonnancier(null);
        Assert.assertEquals(0, this.manager.getListElementsToCheck().size());
    }

    /**
     * Méthode en charge de tester la récupération des elementsToCheck d'un
     * ordonnancier.
     */
    @Test
    public void testGetListDispensationsWithOrdo() {
        final OrdonnancierFabReconst ordonnancier = new OrdonnancierFabReconst();
        this.manager.setOrdonnancier(ordonnancier);
        Mockito.when(this.mockOrdonnancierFabReconstService.get(Matchers.anyLong())).thenReturn(ordonnancier);
        Assert.assertEquals(0, this.manager.getListElementsToCheck().size());
    }

}
