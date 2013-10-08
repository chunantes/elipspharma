package fr.pharma.eclipse.service.stock.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.dates.DetailDates;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stock.NumTraitement;
import fr.pharma.eclipse.domain.model.stock.ReceptionLot;
import fr.pharma.eclipse.domain.model.stock.ResultApprovisionnement;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.factory.stock.ApproFactory;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stock.StockService;
import fr.pharma.eclipse.service.user.UserService;

/**
 * Méthode en charge de tester le service de gestion des approvisionnements.
 * @TODO Fusionner avec ApprovisionnementServiceImplTest (aucune idée pourquoi
 * il y a deux classes)
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ApprovisionnementServiceImplTestII {
    /**
     * ApprovisionnementServiceImpl à tester.
     */
    private ApprovisionnementServiceImpl<Approvisionnement> service;

    /**
     * Mock de la dao de gestion des approvisionnements.
     */
    private GenericDao<Approvisionnement> mockApproDao;

    /**
     * Mock de la factory de mouvement.
     */
    private ApproFactory<Approvisionnement> mockApproFactory;

    /**
     * Mock du service de gestion de utilisateur.
     */
    private UserService mockUserService;

    /**
     * Mock du service de gestion des produits.
     */
    private ProduitService<Produit> mockProduitService;

    /**
     * Service essai.
     */
    private EssaiService essaiService;

    /**
     * Service essai.
     */
    private StockService stockService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.mockApproDao = Mockito.mock(GenericDao.class);
        this.service = new ApprovisionnementServiceImpl<Approvisionnement>(this.mockApproDao);
        this.mockApproFactory = Mockito.mock(ApproFactory.class);
        this.essaiService = Mockito.mock(EssaiService.class);
        final Map<TypeMvtStock, ApproFactory<Approvisionnement>> factories = new HashMap<TypeMvtStock, ApproFactory<Approvisionnement>>();
        factories.put(TypeMvtStock.APPROVISIONNEMENT, this.mockApproFactory);
        this.service.setApproFactories(factories);
        this.mockUserService = Mockito.mock(UserService.class);
        this.service.setUserService(this.mockUserService);
        this.service.setEssaiService(this.essaiService);
        this.mockProduitService = Mockito.mock(ProduitService.class);
        this.service.setProduitService(this.mockProduitService);

        this.stockService = Mockito.mock(StockService.class);
        this.service.setStockService(this.stockService);
    }
    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.mockApproDao = null;
        this.service = null;
        this.mockApproFactory = null;
        this.mockUserService = null;
        this.essaiService = null;
        this.mockProduitService = null;
    }

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.mockApproDao);
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.mockApproFactory);
        Assert.assertNotNull(this.essaiService);
        Assert.assertNotNull(this.mockUserService);
        Assert.assertNotNull(this.mockProduitService);
    }

    /**
     * Méthode en charge de tester la sauvegarde d'un approvisionnement.
     */
    @Test
    public void testSaveApprovisionnementListOfNumTraitement() {
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setModePrescription(ModePrescription.NUM_TRAITEMENT);
        final Approvisionnement approvisionnement = new Approvisionnement();
        approvisionnement.setType(TypeMvtStock.APPROVISIONNEMENT);
        approvisionnement.setDateReception(Calendar.getInstance());
        final Essai essai = Mockito.mock(Essai.class);
        Mockito.when(essai.getEtat()).thenReturn(EtatEssai.EN_ATTENTE_CLOTURE);
        Mockito.when(this.essaiService.get(Matchers.anyLong())).thenReturn(essai);
        Mockito.when(essai.getDetailDates()).thenReturn(new DetailDates());
        final Produit produit = new Medicament();
        approvisionnement.setEssai(essai);
        approvisionnement.setConditionnement(conditionnement);
        approvisionnement.setProduit(produit);
        final List<NumTraitement> numsTraitements = new ArrayList<NumTraitement>();
        final NumTraitement numTraitement1 = new NumTraitement();
        numTraitement1.setNumTraitement("deihd");
        numTraitement1.setQuantite(1);
        numsTraitements.add(numTraitement1);
        final NumTraitement numTraitement2 = new NumTraitement();
        numTraitement2.setNumTraitement("deihd");
        numTraitement2.setQuantite(null);
        numsTraitements.add(numTraitement2);

        Mockito.when(this.mockProduitService.get(1L)).thenReturn(produit);
        Mockito.when(this.mockApproFactory.getInitializedObject()).thenReturn(approvisionnement);
        Mockito.when(this.essaiService.get(Matchers.anyLong())).thenReturn(essai);
        final ReceptionLot receptionLot = new ReceptionLot();
        receptionLot.setAppro(approvisionnement);
        receptionLot.setNumsTraitements(numsTraitements);
        final List<ReceptionLot> receptionLots = new ArrayList<ReceptionLot>();
        receptionLots.add(receptionLot);

        final Stockage stockage = new Stockage();

        Mockito.when(this.mockProduitService.getStockageProduitPharma(Matchers.any(Produit.class), Matchers.any(Pharmacie.class))).thenReturn(stockage);

        this.service.save(receptionLots);
        Mockito.verify(this.mockApproFactory, Mockito.times(1)).getInitializedObject();
    }

    /**
     * Méthode en charge de tester la sauvegarde d'un approvisionnement.
     */
    @Test
    public void testSaveApprovisionnement() {
        final Conditionnement conditionnement = new Conditionnement();
        final Essai essai = Mockito.mock(Essai.class);
        Mockito.when(this.essaiService.get(Matchers.anyLong())).thenReturn(essai);
        Mockito.when(essai.getDetailDates()).thenReturn(new DetailDates());
        final Produit produit = new Medicament();
        produit.setId(1L);
        conditionnement.setModePrescription(ModePrescription.DOSE);
        final Approvisionnement approvisionnement = Mockito.mock(Approvisionnement.class);
        Mockito.when(approvisionnement.getConditionnement()).thenReturn(conditionnement);
        approvisionnement.setDateReception(Calendar.getInstance());
        Mockito.when(approvisionnement.getEssai()).thenReturn(essai);
        Mockito.when(essai.getEtat()).thenReturn(EtatEssai.EN_ATTENTE_CLOTURE);
        Mockito.when(approvisionnement.getProduit()).thenReturn(produit);
        Mockito.when(this.mockApproDao.save(approvisionnement)).thenReturn(approvisionnement);

        final ReceptionLot receptionLot = new ReceptionLot();
        receptionLot.setAppro(approvisionnement);
        final List<ReceptionLot> receptionLots = new ArrayList<ReceptionLot>();
        receptionLots.add(receptionLot);

        final Stockage stockage = new Stockage();
        Mockito.when(this.mockProduitService.getStockageProduitPharma(Matchers.any(Produit.class), Matchers.any(Pharmacie.class))).thenReturn(stockage);

        this.service.save(receptionLots);
        Mockito.verify(this.mockApproDao).save(approvisionnement);
    }
    /**
     * Méthode en charge de tester la méthode de validation d'un numéro de
     * traitement.
     */
    @Test
    public void testIsNumTraitementValide() {
        final NumTraitement numTraitement = new NumTraitement();
        numTraitement.setNumTraitement("ede");
        numTraitement.setQuantite(1);
        Assert.assertTrue(this.service.isNumTraitementValide(numTraitement));
    }

    /**
     * Méthode en charge de tester la méthode de validation d'un numéro de
     * traitement.
     */
    @Test
    public void testIsNumTraitementNotValide1() {
        final NumTraitement numTraitement = new NumTraitement();
        numTraitement.setNumTraitement("ede");
        Assert.assertFalse(this.service.isNumTraitementValide(numTraitement));
    }

    /**
     * Méthode en charge de tester la méthode de validation d'un numéro de
     * traitement.
     */
    @Test
    public void testIsNumTraitementNotValide2() {
        final NumTraitement numTraitement = new NumTraitement();
        numTraitement.setQuantite(1);
        Assert.assertFalse(this.service.isNumTraitementValide(numTraitement));
    }

    /**
     * Méthode en charge de tester la méthode de complétion de résultats.
     */
    @Test
    public void testCompleteResultNothing() {
        final ResultApprovisionnement resultAppro = new ResultApprovisionnement();
        this.service.completeResult(resultAppro);
        Assert.assertNull(resultAppro.getEssai());
        Assert.assertNull(resultAppro.getPromoteur());
        Assert.assertNull(resultAppro.getPharmacie());
        Assert.assertNull(resultAppro.getDateAppro());
        Assert.assertNull(resultAppro.getPersonne());
    }

}
