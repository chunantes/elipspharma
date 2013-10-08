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
import fr.pharma.eclipse.domain.model.ordonnancier.OrdonnancierFabReconst;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.ordonnancier.OrdonnancierService;
import fr.pharma.eclipse.service.stockage.PharmacieService;

/**
 * Classe en charge de tester le manager de duplicata d'ordonnancier de
 * fabrication/reconstitution.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DuplicataOrdonnancierFabReconstManagerTest {
    /**
     * DuplicataOrdonnancierFabReconstManager à tester.
     */
    private DuplicataOrdonnancierFabReconstManager manager;

    /**
     * Critère de recherche sur Ordonnancier.
     */
    private OrdonnancierSearchCriteria criteria;

    /**
     * Service de gestion des pharmacies mocké.
     */
    private PharmacieService mockPharmacieService;

    /**
     * Service de gestion des ordonnanciers de fabrication/reconstitution mocké.
     */
    private OrdonnancierService<OrdonnancierFabReconst> mockOrdonnancierFabReconstService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.criteria = Mockito.mock(OrdonnancierSearchCriteria.class);
        this.manager = new DuplicataOrdonnancierFabReconstManager(this.criteria);
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
        Assert.assertNotNull(this.manager.getCriteria());
        Assert.assertNotNull(this.mockPharmacieService);
        Assert.assertNotNull(this.mockOrdonnancierFabReconstService);
    }

    /**
     * Méthode en charge de tester la méthode d'init du manager.
     */
    @Test
    public void testInitWithPharmaSizeOne() {
        final List<Pharmacie> pharmacies = new ArrayList<Pharmacie>();
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        pharmacies.add(pharmacie);
        Mockito.when(this.mockPharmacieService.getAll((SearchCriteria) Matchers.any())).thenReturn(pharmacies);
        this.manager.init();
        Mockito.verify(this.mockOrdonnancierFabReconstService).getAll((SearchCriteria) Matchers.any());
        Assert.assertNull(this.manager.getOrdonnancierSelected());
    }

    /**
     * Méthode en charge de tester la méthode d'init du manager.
     */
    @Test
    public void testInitWithPharmaSizeNotOne() {
        final List<Pharmacie> pharmacies = new ArrayList<Pharmacie>();
        Mockito.when(this.mockPharmacieService.getAll((SearchCriteria) Matchers.any())).thenReturn(pharmacies);
        this.manager.init();
        Mockito.verify(this.mockOrdonnancierFabReconstService, Mockito.times(0)).getAll((SearchCriteria) Matchers.any());
        Assert.assertNull(this.manager.getOrdonnancierSelected());
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM suite à la
     * sélection d'une pharmacie.
     */
    @Test
    public void testHandleSelectPharmacieNotNull() {
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        Mockito.when(select.getLocalValue()).thenReturn(pharmacie);

        this.manager.handleSelectPharmacie(event);
        Mockito.verify(this.mockOrdonnancierFabReconstService).getAll((SearchCriteria) Matchers.any());
        Assert.assertNull(this.manager.getOrdonnancierSelected());
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM suite à la
     * sélection d'une pharmacie.
     */
    @Test
    public void testHandleSelectPharmacieNull() {
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        Mockito.when(select.getLocalValue()).thenReturn(null);

        this.manager.handleSelectPharmacie(event);
        Assert.assertNull(this.manager.getBeans());
        Assert.assertNull(this.manager.getOrdonnancierSelected());
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
        this.manager.setOrdonnancierSelected(null);
        Assert.assertEquals(0, this.manager.getListElementsToCheck().size());
    }

    /**
     * Méthode en charge de tester la récupération des elementsToCheck d'un
     * ordonnancier.
     */
    @Test
    public void testGetListElementsToCheckWithOrdo() {
        final OrdonnancierFabReconst ordonnancier = new OrdonnancierFabReconst();
        this.manager.setOrdonnancierSelected(ordonnancier);
        Assert.assertEquals(0, this.manager.getListElementsToCheck().size());
    }

}
