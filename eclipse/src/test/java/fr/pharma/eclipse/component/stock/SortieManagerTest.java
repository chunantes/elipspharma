package fr.pharma.eclipse.component.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.primefaces.event.SelectEvent;

import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.DetailDonneesPharma;
import fr.pharma.eclipse.domain.model.localisation.Etablissement;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stock.ResultSortie;
import fr.pharma.eclipse.domain.model.stock.RetourPromoteur;
import fr.pharma.eclipse.domain.model.stock.Sortie;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.factory.stock.MvtStockFactory;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stock.StockService;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.utils.CacheUtils;

/**
 * Classe en charge de tester le manager de sortie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SortieManagerTest {
    /**
     * SortieManager à tester.
     */
    private SortieManager manager;

    /**
     * Service de gestion des essais mocké.
     */
    private EssaiService mockEssaiService;

    /**
     * Service de gestion des produits mocké.
     */
    private ProduitService<Produit> mockProduitService;

    /**
     * Service de gestion des stocks mocké.
     */
    private StockService mockStockService;

    /**
     * Service pharmacie.
     */
    private PharmacieService pharmacieService;

    /**
     * Map des factories par type de mouvement.
     */
    private final Map<TypeMvtStock, MvtStockFactory<MvtStock>> mapFactories = new HashMap<TypeMvtStock, MvtStockFactory<MvtStock>>();

    /**
     * Factory de retour de promoteur mocké.
     */
    private MvtStockFactory<MvtStock> mockRetourPromoteurFactory;
    
    /**
     * CacheUtils
     */
    private CacheUtils cacheUtils;
    
    /**
     * Essai
     */
    private Essai essai;
    
    /**
     * DetailDonneesPharma
     */
    private DetailDonneesPharma detailDonneesPharma;
    
    /**
     * Etablissement
     */
    private Etablissement etablissement;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
    	//Création d'un sortedSet d'établissement pour le tests testHandleSelectTypeSortieCessionPUI
    	SortedSet<Etablissement> etablissements = new TreeSet<Etablissement>();
    	etablissements.add(etablissement);
        this.manager = new SortieManager();
        this.pharmacieService = Mockito.mock(PharmacieService.class);
        this.mockEssaiService = Mockito.mock(EssaiService.class);
        this.mockProduitService = Mockito.mock(ProduitService.class);
        this.mockStockService = Mockito.mock(StockService.class);
        this.cacheUtils = Mockito.mock(CacheUtils.class);
        this.essai = Mockito.mock(Essai.class);
        this.detailDonneesPharma = Mockito.mock(DetailDonneesPharma.class);
        this.etablissement = Mockito.mock(Etablissement.class);
        this.detailDonneesPharma.setEtablissement(etablissements);
        this.manager.setEssaiService(this.mockEssaiService);
        this.manager.setProduitService(this.mockProduitService);
        this.manager.setStockService(this.mockStockService);
        this.manager.setCacheUtils(this.cacheUtils);
        this.manager.setEssaiSelected(this.essai);
        this.mockRetourPromoteurFactory = Mockito.mock(MvtStockFactory.class);
        this.mapFactories.put(TypeMvtStock.RETOUR_PROMOTEUR, this.mockRetourPromoteurFactory);
        this.manager.setMapFactories(this.mapFactories);
        Mockito.when(mockEssaiService.reattach(this.essai)).thenReturn(this.essai);
        Mockito.when(this.essai.getDetailDonneesPharma()).thenReturn(this.detailDonneesPharma);
        Mockito.when(this.detailDonneesPharma.getEtablissements()).thenReturn(etablissements);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.manager = null;
        this.mockEssaiService = null;
        this.mockProduitService = null;
        this.mockStockService = null;
        this.mockRetourPromoteurFactory = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInitData() {
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.mockEssaiService);
        Assert.assertNotNull(this.mockProduitService);
        Assert.assertNotNull(this.mockStockService);
        Assert.assertNotNull(this.mockRetourPromoteurFactory);
        Assert.assertNotNull(this.manager.getStockService());
        Assert.assertNotNull(this.manager.getProduitService());
    }

    /**
     * Méthode en charge de tester la méthode init.
     */
    @Test
    public void testInit() {
        this.manager.init();
        Assert.assertNull(this.manager.getProduits());
        Assert.assertNull(this.manager.getEssaiSelected());
        Assert.assertEquals(0, this.manager.getPharmacies().size());
        Assert.assertNull(this.manager.getPharmacieSelected());
        Assert.assertNull(this.manager.getTypeSortie());
        Assert.assertNull(this.manager.getSortieCurrent());
        Assert.assertEquals(0, this.manager.getSorties().size());
        Assert.assertNull(this.manager.getCommentaire());
        Assert.assertNull(this.manager.getReferenceEnvoi());
        Assert.assertNull(this.manager.getNomSocieteTransport());
        Assert.assertNull(this.manager.getFileDestruction());
        Assert.assertNull(this.manager.getFileRetourPromoteur());
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM quand un essai
     * est sélectionné.
     */
    @Test
    public void testHandleSelectEssaiWithPharmaEmpty() {
        final SelectEvent mockSelectEvent = Mockito.mock(SelectEvent.class);
        final Essai essai = Mockito.mock(Essai.class);
        Mockito.when(mockSelectEvent.getObject()).thenReturn(essai);

        final List<Pharmacie> pharmacies = new ArrayList<Pharmacie>();
        Mockito.when(this.mockEssaiService.getAllPharmaciesOfUser(essai)).thenReturn(pharmacies);
        this.manager.setEssaiSelected(essai);
        this.manager.handleSelectEssai(mockSelectEvent);

        Assert.assertNull(this.manager.getPharmacieSelected());
        Mockito.verify(this.mockProduitService).getProduitsWithPreparations(essai, null);
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM quand un essai
     * est sélectionné.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testHandleSelectEssaiWithPharmaSize1() {
        final SelectEvent mockSelectEvent = Mockito.mock(SelectEvent.class);
        final Essai essai = Mockito.mock(Essai.class);
        this.manager.setEssaiSelected(essai);
        Mockito.when(mockSelectEvent.getObject()).thenReturn(essai);

        final List<Pharmacie> pharmacies = Mockito.mock(List.class);
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        Mockito.when(pharmacies.size()).thenReturn(1);
        Mockito.when(pharmacies.get(0)).thenReturn(pharmacie);

        Mockito.when(this.mockEssaiService.getAllPharmaciesOfUser(essai)).thenReturn(pharmacies);
        this.manager.handleSelectEssai(mockSelectEvent);

        Assert.assertNotNull(this.manager.getPharmacieSelected());
        Mockito.verify(this.mockProduitService).getProduitsWithPreparations(essai, pharmacie);
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
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);
        Mockito.when(select.getLocalValue()).thenReturn(pharmacie);
        final Essai essai = Mockito.mock(Essai.class);
        this.manager.setEssaiSelected(essai);
        this.manager.handleSelectPharmacie(event);
        Assert.assertNotNull(this.manager.getPharmacieSelected());
        Mockito.verify(this.mockProduitService).getProduitsWithPreparations(essai, pharmacie);
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM suite à la
     * sélection d'un type de sortie.
     */
    @Test
    public void testHandleSelectTypeSortieCessionPUI() {
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        this.manager.setPharmaciesDest(new ArrayList<Pharmacie>());
        final TypeMvtStock typeSortie = TypeMvtStock.CESSION_PUI;
        Mockito.when(select.getLocalValue()).thenReturn(typeSortie);
        Mockito.when(this.pharmacieService.getAll()).thenReturn(new ArrayList<Pharmacie>());
        this.manager.handleSelectTypeSortie(event);
        Assert.assertEquals(TypeMvtStock.CESSION_PUI, this.manager.getTypeSortie());
        Assert.assertNull(this.manager.getReferenceEnvoi());
        Assert.assertNull(this.manager.getNomSocieteTransport());
        Assert.assertNull(this.manager.getFileRetourPromoteur());
        Assert.assertNull(this.manager.getFileDestruction());
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM suite à la
     * sélection d'un type de sortie.
     */
    @Test
    public void testHandleSelectTypeSortieRetourPromoteur() {
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        final TypeMvtStock typeSortie = TypeMvtStock.RETOUR_PROMOTEUR;
        Mockito.when(select.getLocalValue()).thenReturn(typeSortie);
        this.manager.setReferenceEnvoi("referenceEnvoi");
        this.manager.setNomSocieteTransport("nomSocieteTransport");
        this.manager.handleSelectTypeSortie(event);
        Assert.assertEquals(TypeMvtStock.RETOUR_PROMOTEUR, this.manager.getTypeSortie());
        Assert.assertNotNull(this.manager.getReferenceEnvoi());
        Assert.assertNotNull(this.manager.getNomSocieteTransport());
        Assert.assertNull(this.manager.getFileDestruction());
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM lors de la
     * sélection d'un produit.
     */
    @Test
    public void testHandleSelectProduitNull() {
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        final Produit produit = Mockito.mock(Produit.class);
        Mockito.when(select.getLocalValue()).thenReturn(produit);
        Mockito.when(this.mockProduitService.reattach(produit)).thenReturn(null);
        final Sortie sortie = new Sortie();
        final MvtStock mvtSortie = new RetourPromoteur();
        sortie.setMvtSortie(mvtSortie);
        this.manager.setSortieCurrent(sortie);
        this.manager.handleSelectProduit(event);
        Assert.assertEquals(0, this.manager.getSortieCurrent().getConditionnements().size());
        Assert.assertNull(this.manager.getSortieCurrent().getMvtSortie().getConditionnement());
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM lors de la
     * sélection d'un produit.
     */
    @Test
    public void testHandleSelectProduit() {
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        final Produit produit = Mockito.mock(Produit.class);
        Mockito.when(select.getLocalValue()).thenReturn(produit);
        Mockito.when(this.mockProduitService.reattach(produit)).thenReturn(produit);
        final Sortie sortie = new Sortie();
        final MvtStock mvtSortie = new RetourPromoteur();
        sortie.setMvtSortie(mvtSortie);
        this.manager.setSortieCurrent(sortie);
        this.manager.handleSelectProduit(event);
        Assert.assertEquals(0, this.manager.getSortieCurrent().getConditionnements().size());
        Assert.assertNull(this.manager.getSortieCurrent().getMvtSortie().getConditionnement());
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM suite à la
     * sélection d'un conditionnement.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testHandleSelectConditionnement() {
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        final Conditionnement conditionnement = Mockito.mock(Conditionnement.class);
        Mockito.when(select.getLocalValue()).thenReturn(conditionnement);

        final List<LigneStock> lignesStock = Matchers.anyList();

        Mockito.when(this.mockStockService.getLignesStockPharmacie((Essai) Matchers.any(), (Pharmacie) Matchers.any(), (Produit) Matchers.any(), conditionnement))
                .thenReturn(lignesStock);

        final Sortie sortie = new Sortie();
        final MvtStock mvtSortie = new RetourPromoteur();
        sortie.setMvtSortie(mvtSortie);
        this.manager.setSortieCurrent(sortie);
        this.manager.handleSelectConditionnement(event);

        Assert.assertEquals(lignesStock, sortie.getLignesStock());
    }

    /**
     * Méthode en charge de tester l'ajout d'une sortie dans la liste des
     * sorties.
     */
    @Test
    public void testAddSortieWithTypeSortieNull() {
        this.manager.setTypeSortie(null);
        this.manager.addSortie();
    }

    /**
     * Méthode en charge de tester l'ajout d'une sortie dans la liste des
     * sorties.
     */
    @Test
    public void testAddSortieWithTypeSortieNotNull() {
        final TypeMvtStock typeSortie = TypeMvtStock.RETOUR_PROMOTEUR;
        this.manager.setTypeSortie(typeSortie);

        final MvtStock retourPromoteur = new RetourPromoteur();
        Mockito.when(this.mockRetourPromoteurFactory.getInitializedObject()).thenReturn(retourPromoteur);

        this.manager.addSortie();
        Assert.assertNotNull(this.manager.getSortieCurrent());
        Assert.assertEquals("ADD", this.manager.getActionSortieCurrent());
    }

    /**
     * Méthode en charge de tester la modification d'une sortie.
     */
    @Test
    public void testModifySortie() {
        this.manager.modifySortie();
        Assert.assertEquals("EDIT", this.manager.getActionSortieCurrent());
    }

    /**
     * Méthode en charge de tester la suppression de sortie.
     */
    @Test
    public void testDelSortie() {
        final Sortie sortieToDelete = new Sortie();
        this.manager.setSortieToDelete(sortieToDelete);

        final List<Sortie> sorties = new ArrayList<Sortie>();
        sorties.add(sortieToDelete);
        this.manager.setSorties(sorties);

        this.manager.delSortie();
        Assert.assertEquals(0, this.manager.getSorties().size());
    }

    /**
     * Méthode en charge de tester l'ajout d'une sortie.
     */
    @Test
    public void testAddSortieToSortiesEdit() {
        final List<Sortie> sorties = new ArrayList<Sortie>();
        this.manager.setActionSortieCurrent("EDIT");
        this.manager.setSorties(sorties);
        this.manager.addSortieToSorties();
        Assert.assertEquals(0, this.manager.getSorties().size());
    }

    /**
     * Méthode en charge de tester l'ajout d'une sortie.
     */
    @Test
    public void testAddSortieToSortiesAdd() {
        final List<Sortie> sorties = new ArrayList<Sortie>();
        this.manager.setActionSortieCurrent("ADD");

        final Sortie sortie = new Sortie();
        this.manager.setSortieCurrent(sortie);
        this.manager.setSorties(sorties);

        this.manager.addSortieToSorties();
        Assert.assertEquals(1, this.manager.getSorties().size());
    }

    /**
     * Méthode en charge de tester l'affectation de résultat.
     */
    @Test
    public void testSetResult() {
        final ResultSortie resultSortie = new ResultSortie();
        this.manager.setResult(resultSortie);
        Assert.assertNotNull(this.manager.getResult());
    }

}
