package fr.pharma.eclipse.component.stock;

import java.util.ArrayList;
import java.util.Calendar;
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

import fr.pharma.eclipse.comparator.common.EclipseListComparator;
import fr.pharma.eclipse.component.stock.helper.AutoSaisieNumTraitement;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stock.NumTraitement;
import fr.pharma.eclipse.domain.model.stock.ReceptionLot;
import fr.pharma.eclipse.domain.model.stock.ResultApprovisionnement;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.factory.stock.ApproFactory;
import fr.pharma.eclipse.factory.stock.ApprovisionnementFactory;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stock.MvtStockService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester le manager de Approvisionnement.
 
 * @version $Revision$ $Date$
 */
public class ApprovisionnementManagerTest
{
    /**
     * ApprovisionnementManager à tester.
     */
    private ApprovisionnementManager manager;

    /**
     * Mock du service d'approvisionnement.
     */
    private MvtStockService<Approvisionnement> mockAppoService;

    /**
     * Mock du service de gestion des produits.
     */
    private ProduitService<Produit> mockProduitService;

    /**
     * Mock du service de gestion des essais.
     */
    private EssaiService mockEssaiService;

    /**
     * Mock de la factory d'approvisionnment.
     */
    private ApprovisionnementFactory mockApproFactory;

    /**
     * Mock de la saisie automatique des numéros de traitements.
     */
    private AutoSaisieNumTraitement mockAutoSaisieNumTraitement;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init()
    {
        this.mockAppoService = Mockito.mock(MvtStockService.class);
        this.manager = new ApprovisionnementManager();
        this.mockProduitService = Mockito.mock(ProduitService.class);
        this.manager.setProduitService(this.mockProduitService);
        this.mockApproFactory = Mockito.mock(ApprovisionnementFactory.class);
        this.mockAutoSaisieNumTraitement = Mockito.mock(AutoSaisieNumTraitement.class);
        this.manager.setAutoSaisieNumTraitement(this.mockAutoSaisieNumTraitement);
        final Map<TypeMvtStock, ApproFactory<Approvisionnement>> factories =
            new HashMap<TypeMvtStock, ApproFactory<Approvisionnement>>();
        factories.put(TypeMvtStock.APPROVISIONNEMENT,
                      this.mockApproFactory);
        this.manager.setApproFactorys(factories);
        this.mockEssaiService = Mockito.mock(EssaiService.class);
        this.manager.setEssaiService(this.mockEssaiService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end()
    {
        this.mockAppoService = null;
        this.manager = null;
        this.mockProduitService = null;
        this.mockAutoSaisieNumTraitement = null;
        this.mockEssaiService = null;
        this.mockApproFactory = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInitData()
    {
        Assert.assertNotNull(this.mockAppoService);
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.mockProduitService);
        Assert.assertNotNull(this.mockAutoSaisieNumTraitement);
        Assert.assertNotNull(this.mockEssaiService);
        Assert.assertNotNull(this.mockApproFactory);
    }

    /**
     * Méthode en charge de tester la méthode init.
     */
    @Test
    public void testInit()
    {
        this.manager.init();
        Assert.assertNull(this.manager.getEssaiSelected());
        Assert.assertNull(this.manager.getPharmacieSelected());
        Assert.assertNull(this.manager.getProduits());
        Assert.assertNull(this.manager.getReceptionCurrent());
        Assert.assertEquals(0,
                            this.manager.getReceptionLots().size());
        Assert.assertEquals(0,
                            this.manager.getPharmacies().size());
    }

    /**
     * Méthode en charge de tester la maj des pharmacies et produits suite à la sélection d'un
     * essai.
     */
    @Test
    public void testHandleSelectEssaiPharmaSize1()
    {
        final SelectEvent mockSelectEvent = Mockito.mock(SelectEvent.class);
        final Essai essai = Mockito.mock(Essai.class);
        Mockito.when(mockSelectEvent.getObject()).thenReturn(essai);
        this.manager.setEssaiSelected(essai);
        final List<Pharmacie> pharmacies = new ArrayList<Pharmacie>();
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);
        pharmacies.add(pharmacie);
        Mockito.when(this.mockEssaiService.getAllPharmaciesOfUser(essai)).thenReturn(pharmacies);
        this.manager.handleSelectEssai(mockSelectEvent);
        Mockito.verify(this.mockEssaiService).getAllPharmaciesOfUser(essai);
        Assert.assertNotNull(this.manager.getPharmacieSelected());
        Mockito.verify(this.mockProduitService).getProduits(essai,
                                                            pharmacie);
    }

    /**
     * Méthode en charge de tester la maj des pharmacies et produits suite à la sélection d'un
     * essai.
     */
    @Test
    public void testHandleSelectEssaiPharmaSizeNot1()
    {
        final SelectEvent mockSelectEvent = Mockito.mock(SelectEvent.class);
        final Essai essai = Mockito.mock(Essai.class);
        Mockito.when(mockSelectEvent.getObject()).thenReturn(essai);
        this.manager.setEssaiSelected(essai);
        final List<Pharmacie> pharmacies = new ArrayList<Pharmacie>();
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);
        final Pharmacie pharmacie2 = new Pharmacie();
        pharmacie2.setId(2L);
        pharmacies.add(pharmacie);
        pharmacies.add(pharmacie2);
        Mockito.when(this.mockEssaiService.getAllPharmaciesOfUser(essai)).thenReturn(pharmacies);
        this.manager.handleSelectEssai(mockSelectEvent);
        Mockito.verify(this.mockEssaiService).getAllPharmaciesOfUser(essai);
        Assert.assertNull(this.manager.getPharmacieSelected());
        Mockito.verify(this.mockProduitService).getProduits(essai,
                                                            null);
    }

    /**
     * Méthode en charge de tester la maj des produits suite à la sélection d'une pharmacie.
     */
    @Test
    public void testHandleSelectPharmacie()
    {
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
        Mockito.verify(this.mockProduitService).getProduits(essai,
                                                            pharmacie);
    }

    /**
     * Méthode en charge de tester la maj des conditionnements suite à la sélection d'un produit.
     */
    @Test
    public void testHandleSelectProduit()
    {
        final Produit produit = Mockito.mock(Produit.class);
        final SortedSet<Conditionnement> conditionnements =
            new TreeSet<Conditionnement>(new EclipseListComparator());
        produit.getConditionnements().addAll(conditionnements);
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        Mockito.when(select.getLocalValue()).thenReturn(produit);
        Mockito.when(this.mockProduitService.reattach(produit)).thenReturn(produit);
        final ReceptionLot receptionLot = new ReceptionLot();
        final Approvisionnement approvisionnement = new Approvisionnement();
        receptionLot.setAppro(approvisionnement);
        this.manager.setReceptionCurrent(receptionLot);
        this.manager.handleSelectProduit(event);
        Assert.assertNotNull(this.manager.getReceptionCurrent().getConditionnements());
        Assert.assertNull(approvisionnement.getConditionnement());
        Assert.assertNull(receptionLot.getNbNumerosTraitement());
    }

    /**
     * Méthode en charge de tester la gestion de la saisie automatique des numéros de traitement.
     */
    @Test
    public void testHandleSaisieAutoNumsTraitements()
    {
        final List<NumTraitement> numsTraitements = new ArrayList<NumTraitement>();
        final ReceptionLot receptionLot = new ReceptionLot();
        receptionLot.setNumsTraitements(numsTraitements);
        this.manager.setReceptionCurrent(receptionLot);
        this.manager.handleSaisieAutoNumsTraitements();
        Mockito.verify(this.mockAutoSaisieNumTraitement).handle(numsTraitements);
    }

    /**
     * Méthode en charge de tester l'ajout d'une réception de lot.
     */
    @Test
    public void testAddReceptionLot()
    {
        final Approvisionnement appro = new Approvisionnement();
        final Essai essai = new Essai();
        essai.setId(1L);
        this.manager.setTypeAppro(TypeMvtStock.APPROVISIONNEMENT);
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);
        appro.setEssai(essai);
        appro.setType(TypeMvtStock.APPROVISIONNEMENT);
        appro.setPharmacie(pharmacie);
        final Calendar dateReception = Calendar.getInstance(EclipseConstants.LOCALE);
        appro.setDateReception(dateReception);
        final Calendar dateArriveeColis = Calendar.getInstance(EclipseConstants.LOCALE);
        appro.setDateArriveeColis(dateArriveeColis);
        Mockito.when(this.mockApproFactory.getInitializedObject(essai,
                                                                pharmacie,
                                                                dateReception,
                                                                dateArriveeColis))
                .thenReturn(appro);
        this.manager.setEssaiSelected(essai);
        this.manager.setPharmacieSelected(pharmacie);
        this.manager.addReceptionLot();
        Assert.assertEquals("ADD",
                            this.manager.getActionReceptionCurrent());
    }

    /**
     * Méthode en charge de tester la modification d'une réception de lot.
     */
    @Test
    public void testModifyReceptionLot()
    {
        this.manager.modifyReceptionLot();
        Assert.assertEquals("EDIT",
                            this.manager.getActionReceptionCurrent());
    }

    /**
     * Méthode en charge de tester l'ajout d'une réception de lot à la liste des réceptions de
     * lot.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testAddReceptionLotToListAdd()
    {
        this.manager.setActionReceptionCurrent("ADD");
        final List<ReceptionLot> receptionLots = Mockito.mock(List.class);
        this.manager.setReceptionLots(receptionLots);
        this.manager.addReceptionToReceptions();
        Mockito.verify(receptionLots).add((ReceptionLot) Matchers.any());
    }

    /**
     * Méthode en charge de tester l'ajout d'une réception de lot à la liste des réceptions de
     * lot.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testAddReceptionLotToListEdit()
    {
        this.manager.setActionReceptionCurrent("EDIT");
        final List<ReceptionLot> receptionLots = Mockito.mock(List.class);
        this.manager.setReceptionLots(receptionLots);
        this.manager.addReceptionToReceptions();
        Mockito.verify(receptionLots,
                       Mockito.times(0)).add((ReceptionLot) Matchers.any());
    }

    /**
     * Méthode en charge de tester la suppression d'une réception de lot.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testDelReception()
    {
        final List<ReceptionLot> receptionLots = Mockito.mock(List.class);
        final ReceptionLot receptionToDelete = Mockito.mock(ReceptionLot.class);
        this.manager.setReceptionToDelete(receptionToDelete);
        this.manager.setReceptionLots(receptionLots);
        this.manager.delReception();
        Mockito.verify(receptionLots).remove(receptionToDelete);
    }

    /**
     * Méthode en charge de tester le résultat de l'enregistrement de Approvisionnement.
     */
    @Test
    public void setResultApprovisionnement()
    {
        final ResultApprovisionnement result = new ResultApprovisionnement();
        this.manager.setResult(result);
        Assert.assertNotNull(this.manager.getResult());
    }

}
