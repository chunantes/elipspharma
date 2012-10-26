package fr.pharma.eclipse.component.stockage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.primefaces.component.tabview.Tab;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.TreeNode;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.component.helper.BeanManagerHelper;
import fr.pharma.eclipse.component.stockage.helper.TreeStockageHelper;
import fr.pharma.eclipse.component.stockage.validator.StockageValidator;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Etablissement;
import fr.pharma.eclipse.domain.model.localisation.Site;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.service.localisation.SiteService;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.service.stockage.StockageService;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Classe en charge de tester le manager de Pharmacie.
 
 * @version $Revision$ $Date$
 */
public class PharmacieManagerTest
{
    /**
     * PharmacieManager à tester.
     */
    private PharmacieManager manager;

    /**
     * Mock du service de gestion de Pharmacie.
     */
    private PharmacieService mockPharmacieService;

    /**
     * Mock du service de gestion de sites.
     */
    private SiteService mockSiteService;

    /**
     * Mock du helper de Managed Bean.
     */
    private BeanManagerHelper<Pharmacie> mockHelper;

    /**
     * Mock du helper de gestion de stockage sous forme d'arbre.
     */
    private TreeStockageHelper mockTreeStockageHelper;

    /**
     * Validator de stockage mocké.
     */
    private StockageValidator mockStockagevalidator;

    /**
     * Faces utils.
     */
    private FacesUtils facesUtils;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init()
    {
        this.mockPharmacieService = Mockito.mock(PharmacieService.class);
        this.manager = new PharmacieManager(this.mockPharmacieService);
        this.mockSiteService = Mockito.mock(SiteService.class);
        this.facesUtils = Mockito.mock(FacesUtils.class);
        this.manager.setSiteService(this.mockSiteService);
        this.mockHelper = Mockito.mock(BeanManagerHelper.class);
        this.manager.setHelper(this.mockHelper);
        this.mockTreeStockageHelper = Mockito.mock(TreeStockageHelper.class);
        this.manager.setStockageHelper(this.mockTreeStockageHelper);
        this.mockStockagevalidator = Mockito.mock(StockageValidator.class);
        this.manager.setStockageValidator(this.mockStockagevalidator);
        this.manager.setFacesUtils(this.facesUtils);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end()
    {
        this.mockPharmacieService = null;
        this.manager = null;
        this.mockSiteService = null;
        this.mockHelper = null;
        this.mockTreeStockageHelper = null;
        this.mockStockagevalidator = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.mockPharmacieService);
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.mockSiteService);
        Assert.assertNotNull(this.mockHelper);
        Assert.assertNotNull(this.mockTreeStockageHelper);
        Assert.assertNotNull(this.mockStockagevalidator);
    }

    /**
     * Méthode en charge de récupérer les sites sélectables.
     */
    @Test
    public void testGetSitesSelectableSansEtablissement()
    {
        final Pharmacie pharmacie = new Pharmacie();
        this.manager.setBean(pharmacie);
        final List<Site> sitesExpected = new ArrayList<Site>();
        Mockito.when(this.mockSiteService.getAll((SearchCriteria) Matchers.any()))
                .thenReturn(sitesExpected);
        final List<Site> sites = this.manager.getSitesSelectable();
        Assert.assertNotNull(sites);
        Mockito.verify(this.mockHelper).updateSelectable(pharmacie.getSites(),
                                                         sitesExpected);
    }

    /**
     * Méthode en charge de récupérer les sites sélectables.
     */
    @Test
    public void testGetSitesSelectableAvecEtablissement()
    {
        final Pharmacie pharmacie = new Pharmacie();
        final Etablissement etablissement = new Etablissement();
        pharmacie.setEtablissement(etablissement);
        this.manager.setBean(pharmacie);
        final List<Site> sitesExpected = new ArrayList<Site>();
        Mockito.when(this.mockSiteService.getAll((SearchCriteria) Matchers.any()))
                .thenReturn(sitesExpected);
        final List<Site> sites = this.manager.getSitesSelectable();
        Assert.assertNotNull(sites);
        Mockito.verify(this.mockHelper).updateSelectable(pharmacie.getSites(),
                                                         sitesExpected);
    }

    /**
     * Méthode en charge de tester la mise à jour des sites d'une pharmacie.
     */
    @Test
    public void testUpdateSites()
    {
        final Pharmacie mockPharmacie = Mockito.mock(Pharmacie.class);
        final SortedSet<Site> sites = Mockito.mock(TreeSet.class);
        final List<Site> selectables = Mockito.mock(List.class);
        this.manager.setBean(mockPharmacie);
        this.manager.setSitesSelectable(selectables);
        Mockito.when(mockPharmacie.getSites()).thenReturn(sites);
        this.manager.updateSites();
        Mockito.verify(this.mockHelper).updateSelected(sites,
                                                       selectables);
    }

    /**
     * Méthode en charge de tester la méthode d'effacement des sites d'une pharmacie.
     */
    @Test
    public void testClearSites()
    {
        final SortedSet<Site> sites = new TreeSet<Site>(new BeanWithNomComparator());
        final Site site = new Site();
        site.setNom("site");
        sites.add(site);

        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setSites(sites);

        Assert.assertEquals(1,
                            pharmacie.getSites().size());
        this.manager.setBean(pharmacie);
        this.manager.clearSites();
        Assert.assertEquals(0,
                            pharmacie.getSites().size());
    }

    /**
     * Méthode en charge de tester la construction de l'arbre de stockage.
     */
    @Test
    public void testGetRootSansCalculIdsNodesToExpand()
    {
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        this.manager.setBean(pharmacie);
        final TreeNode rootExpected = Mockito.mock(TreeNode.class);
        Mockito.when(this.mockTreeStockageHelper.buildTree(pharmacie)).thenReturn(rootExpected);
        final TreeNode root = this.manager.getRoot();
        Mockito.verify(this.mockTreeStockageHelper).buildTree(pharmacie);
        Assert.assertEquals(rootExpected,
                            root);
        Assert.assertNull(this.manager.getIdsNodesToExpand());
    }

    /**
     * Méthode en charge de tester la construction de l'arbre de stockage.
     */
    @Test
    public void testGetRootAvecCalculIdsNodesToExpand()
    {
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        final Stockage stockageDisplay = Mockito.mock(Stockage.class);
        final String idsNodesToExpand = "idsNodesToExpand";
        this.manager.setStockageDisplay(stockageDisplay);
        this.manager.setBean(pharmacie);
        this.manager.setIdsNodesToExpand(StringUtils.EMPTY);
        final TreeNode rootExpected = Mockito.mock(TreeNode.class);
        Mockito.when(this.mockTreeStockageHelper.buildTree(pharmacie)).thenReturn(rootExpected);
        Mockito.when(this.mockTreeStockageHelper.calculateNodesToExpand(rootExpected,
                                                                        stockageDisplay))
                .thenReturn(idsNodesToExpand);
        final TreeNode root = this.manager.getRoot();
        Mockito.verify(this.mockTreeStockageHelper).buildTree(pharmacie);
        Mockito.verify(this.mockTreeStockageHelper).calculateNodesToExpand(rootExpected,
                                                                           stockageDisplay);
        Assert.assertEquals(rootExpected,
                            root);
        Assert.assertEquals(idsNodesToExpand,
                            this.manager.getIdsNodesToExpand());
    }

    /**
     * Méthode en charge de tester le changement d'onglet.
     */
    @Test
    public void testOnOngletChange()
    {
        final TabChangeEvent event = Mockito.mock(TabChangeEvent.class);
        final Tab tab = Mockito.mock(Tab.class);
        Mockito.when(event.getTab()).thenReturn(tab);
        Mockito.when(tab.getId()).thenReturn(PharmacieManager.INFOS_ONGLETS
                .keySet()
                .iterator()
                .next());
        this.manager.onOngletChange(event);
        Assert.assertEquals(0,
                            this.manager.getIndexOngletCourant());
    }

    /**
     * Méthode en charge de tester l'ajout de stockage.
     */
    @Test
    public void addStockageAvecParent()
    {
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final UIComponent component = Mockito.mock(UIComponent.class);
        Mockito.when(event.getComponent()).thenReturn(component);
        final Map<String, Object> attributes = Mockito.mock(Map.class);
        Mockito.when(component.getAttributes()).thenReturn(attributes);
        final Stockage stockageParent = Mockito.mock(Stockage.class);
        final String nameAttribute = "stockageParent";
        Mockito.when(attributes.get(nameAttribute)).thenReturn(stockageParent);
        this.manager.addStockage(event);
        Assert.assertNotNull(this.manager.getStockageCurrent());
    }

    /**
     * Méthode en charge de tester l'ajout de stockage.
     */
    @Test
    public void addStockageSansParent()
    {
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final UIComponent component = Mockito.mock(UIComponent.class);
        Mockito.when(event.getComponent()).thenReturn(component);
        final Map<String, Object> attributes = Mockito.mock(Map.class);
        Mockito.when(component.getAttributes()).thenReturn(attributes);
        final String nameAttribute = "stockageParent";
        Mockito.when(attributes.get(nameAttribute)).thenReturn(null);
        this.manager.addStockage(event);
        Assert.assertNotNull(this.manager.getStockageCurrent());
    }

    /**
     * Méthode en charge de tester l'édition de stockage.
     */
    @Test
    public void testEditStockage()
    {
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final UIComponent component = Mockito.mock(UIComponent.class);
        Mockito.when(event.getComponent()).thenReturn(component);
        final Map<String, Object> attributes = Mockito.mock(Map.class);
        Mockito.when(component.getAttributes()).thenReturn(attributes);
        final Stockage stockageCurrent = Mockito.mock(Stockage.class);
        final String nameAttribute = "stockageCurrent";
        Mockito.when(attributes.get(nameAttribute)).thenReturn(stockageCurrent);
        this.manager.editStockage(event);
        Assert.assertNotNull(this.manager.getStockageCurrent());
    }

    /**
     * Méthode en charge de tester la suppression de stockage.
     */
    @Test
    public void testDelStockageAvecParent()
    {
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final UIComponent component = Mockito.mock(UIComponent.class);
        Mockito.when(event.getComponent()).thenReturn(component);
        final Map<String, Object> attributes = Mockito.mock(Map.class);
        Mockito.when(component.getAttributes()).thenReturn(attributes);
        final Stockage stockageToDelete = Mockito.mock(Stockage.class);
        final Stockage stockageParent = Mockito.mock(Stockage.class);
        final String nameAttribute = "stockageToDelete";
        Mockito.when(attributes.get(nameAttribute)).thenReturn(stockageToDelete);
        Mockito.when(stockageToDelete.getParent()).thenReturn(stockageParent);
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        this.manager.setBean(pharmacie);
        this.manager.delStockage(event);
        Mockito.verify(this.mockPharmacieService).removeStockage(pharmacie,
                                                                 stockageToDelete);
    }

    /**
     * Méthode en charge de tester la suppression de stockage.
     */
    @Test
    public void testDelStockageSansParent()
    {
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final UIComponent component = Mockito.mock(UIComponent.class);
        Mockito.when(event.getComponent()).thenReturn(component);
        final Map<String, Object> attributes = Mockito.mock(Map.class);
        Mockito.when(component.getAttributes()).thenReturn(attributes);
        final Stockage stockageToDelete = Mockito.mock(Stockage.class);
        final String nameAttribute = "stockageToDelete";
        Mockito.when(attributes.get(nameAttribute)).thenReturn(stockageToDelete);
        Mockito.when(stockageToDelete.getParent()).thenReturn(null);
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        this.manager.setBean(pharmacie);
        this.manager.delStockage(event);
        Mockito.verify(this.mockPharmacieService).removeStockage(pharmacie,
                                                                 stockageToDelete);
    }

    /**
     * Méthode en charge de tester l'ajout de stockage.
     */
    @Test
    public void testAddsWithValidKO()
    {
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        final SortedSet<Stockage> stockages = Mockito.mock(TreeSet.class);
        Mockito.when(pharmacie.getStockages()).thenReturn(stockages);
        final Stockage stockageCurrent = Mockito.mock(Stockage.class);
        this.manager.setStockageCurrent(stockageCurrent);
        this.manager.setBean(pharmacie);
        this.manager.setActionStockageCurrent("ADD");
        Mockito.when(this.mockStockagevalidator.validateStockage(stockageCurrent))
                .thenReturn(Boolean.FALSE);
        this.manager.updateStockages();
        Mockito.verify(this.mockStockagevalidator).validateStockage(stockageCurrent);
    }

    /**
     * Méthode en charge de tester l'ajout de stockage.
     */
    @Test
    public void testAddsWithValidOK()
    {
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        final SortedSet<Stockage> stockages = Mockito.mock(TreeSet.class);
        Mockito.when(pharmacie.getStockages()).thenReturn(stockages);
        final Stockage stockageCurrent = Mockito.mock(Stockage.class);
        this.manager.setStockageCurrent(stockageCurrent);
        this.manager.setBean(pharmacie);
        this.manager.setActionStockageCurrent("ADD");
        final StockageService stockageService = Mockito.mock(StockageService.class);
        this.manager.setStockageService(stockageService);
        Mockito.when(this.mockStockagevalidator.validateStockage(stockageCurrent)).thenReturn(Boolean.TRUE);
        this.manager.updateStockages();
        Mockito.verify(this.mockStockagevalidator).validateStockage(stockageCurrent);
    }

    /**
     * Méthode en charge de tester l'ajout de stockage.
     */
    @Test
    public void testAddsWithValidOKAndParent()
    {
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        final SortedSet<Stockage> stockages = Mockito.mock(TreeSet.class);
        Mockito.when(pharmacie.getStockages()).thenReturn(stockages);
        final Stockage stockageCurrent = Mockito.mock(Stockage.class);
        final Stockage stockageParent = Mockito.mock(Stockage.class);
        Mockito.when(stockageCurrent.getParent()).thenReturn(stockageParent);
        this.manager.setStockageCurrent(stockageCurrent);
        this.manager.setBean(pharmacie);
        this.manager.setActionStockageCurrent("ADD");
        final StockageService stockageService = Mockito.mock(StockageService.class);
        this.manager.setStockageService(stockageService);
        Mockito.when(this.mockStockagevalidator.validateStockage(stockageCurrent)).thenReturn(Boolean.TRUE);
        this.manager.updateStockages();
        Mockito.verify(this.mockStockagevalidator).validateStockage(stockageCurrent);
    }

    /**
     * Méthode en charge de tester la mise à jour de stockage.
     */
    @Test
    public void testUpdatesWithValidKO()
    {
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        final SortedSet<Stockage> stockages = Mockito.mock(TreeSet.class);
        Mockito.when(pharmacie.getStockages()).thenReturn(stockages);
        final Stockage stockageCurrent = Mockito.mock(Stockage.class);
        this.manager.setStockageCurrent(stockageCurrent);
        this.manager.setBean(pharmacie);
        this.manager.setActionStockageCurrent("EDIT");
        Mockito.when(this.mockStockagevalidator.validateStockage(stockageCurrent))
                .thenReturn(Boolean.FALSE);
        this.manager.updateStockages();
        Mockito.verify(this.mockStockagevalidator).validateStockage(stockageCurrent);
    }

    /**
     * Méthode en charge de tester la mise à jour de stockage.
     */
    @Test
    public void testUpdatesWithValidOK()
    {
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        final SortedSet<Stockage> stockages = new TreeSet<Stockage>();
        final Stockage stockage1 = Mockito.mock(Stockage.class);
        stockages.add(stockage1);
        Mockito.when(pharmacie.getStockages()).thenReturn(stockages);
        final Stockage stockageCurrent = Mockito.mock(Stockage.class);
        this.manager.setStockageCurrent(stockageCurrent);
        this.manager.setBean(pharmacie);
        this.manager.setActionStockageCurrent("EDIT");
        final StockageService stockageService = Mockito.mock(StockageService.class);
        this.manager.setStockageService(stockageService);
        Mockito.when(this.mockStockagevalidator.validateStockage(stockageCurrent))
                .thenReturn(Boolean.TRUE);
        Mockito.when(stockageService.save(stockageCurrent)).thenReturn(stockageCurrent);

        this.manager.updateStockages();
        Mockito.verify(this.mockStockagevalidator).validateStockage(stockageCurrent);

    }

    /**
     * Méthode en charge de tester la mise à jour de stockage.
     */
    @Test
    public void testUpdatesWithValidOKAndParent()
    {
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        final SortedSet<Stockage> stockages = new TreeSet<Stockage>();
        final Stockage stockage1 = Mockito.mock(Stockage.class);
        stockages.add(stockage1);
        Mockito.when(pharmacie.getStockages()).thenReturn(stockages);
        final Stockage stockageCurrent = Mockito.mock(Stockage.class);
        final Stockage stockageParent = Mockito.mock(Stockage.class);
        Mockito.when(stockageCurrent.getParent()).thenReturn(stockageParent);
        this.manager.setStockageCurrent(stockageCurrent);
        this.manager.setBean(pharmacie);
        this.manager.setActionStockageCurrent("EDIT");
        final StockageService stockageService = Mockito.mock(StockageService.class);
        this.manager.setStockageService(stockageService);
        Mockito.when(this.mockStockagevalidator.validateStockage(stockageCurrent))
                .thenReturn(Boolean.TRUE);
        Mockito.when(stockageService.save(stockageCurrent)).thenReturn(stockageCurrent);
        this.manager.updateStockages();
        Mockito.verify(this.mockStockagevalidator).validateStockage(stockageCurrent);
    }

    /**
     * Test de la méthode confirmEnregistrement.
     */
    @Test
    public void testConfirmEnregistrement()
    {
        this.manager.confirmEnregistrement();
        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_INFO,
                                                   "pharmacie.enregistrement.ok");
    }

}
