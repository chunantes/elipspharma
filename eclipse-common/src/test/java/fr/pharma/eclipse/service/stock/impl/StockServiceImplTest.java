package fr.pharma.eclipse.service.stock.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.enums.TypeDispensation;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stock.CessionPui;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stock.PreparationEntree;
import fr.pharma.eclipse.domain.model.stock.RetourPromoteur;
import fr.pharma.eclipse.domain.model.stock.Sortie;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stock.ApprovisionnementService;
import fr.pharma.eclipse.service.stock.MvtStockService;

/**
 * Classe en charge de tester le service de gestion des stocks.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class StockServiceImplTest {
    /**
     * StockService à tester.
     */
    private StockServiceImpl service;

    /**
     * Dao de gestion des mouvements de stock.
     */
    private GenericDao<LigneStock> mockDao;

    /**
     * Service de gestion des mouvements de stock mocké.
     */
    private MvtStockService<MvtStock> mvtStockServiceMock;

    /**
     * Service de gestion des produits mocké.
     */
    private ProduitService<Produit> produitServiceMock;

    private ApprovisionnementService<Approvisionnement> approServiceMock;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.mockDao = Mockito.mock(GenericDao.class);
        this.service = new StockServiceImpl(this.mockDao);

        this.mvtStockServiceMock = Mockito.mock(MvtStockService.class);
        this.service.setMvtStockService(this.mvtStockServiceMock);

        this.produitServiceMock = Mockito.mock(ProduitService.class);
        this.service.setProduitService(this.produitServiceMock);

        this.approServiceMock = Mockito.mock(ApprovisionnementService.class);
        this.service.setApproService(this.approServiceMock);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.service = null;
        this.mvtStockServiceMock = null;
        this.produitServiceMock = null;
        this.approServiceMock = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.mvtStockServiceMock);
        Assert.assertNotNull(this.produitServiceMock);
        Assert.assertNotNull(this.approServiceMock);
    }

    /**
     * Méthode en charge de tester la récupération des lignes de stock.
     */
    @Test
    public void testGetAllLignesStock() {
        final List<MvtStock> appros = new ArrayList<MvtStock>();
        final Approvisionnement appro1 = new Approvisionnement();
        appro1.setEssai(this.getEssai());
        appro1.setPharmacie(this.getPharmacie());
        appro1.setProduit(this.getProduit());
        appro1.setConditionnement(this.getConditionnement());
        appro1.setApproApprouve(Boolean.TRUE);
        appro1.setNumLot("numLot1");
        appro1.setQuantite(2);
        appros.add(appro1);

        final Approvisionnement appro2 = new Approvisionnement();
        appro2.setEssai(this.getEssai());
        appro2.setPharmacie(this.getPharmacie());
        appro2.setProduit(this.getProduit());
        appro2.setConditionnement(this.getConditionnement());
        appro2.setNumLot("numLot2");
        appro2.setQuantite(1);
        appro2.setApproApprouve(Boolean.FALSE);
        appros.add(appro2);

        final List<MvtStock> sorties = new ArrayList<MvtStock>();
        final RetourPromoteur retourPromoteur = new RetourPromoteur();
        retourPromoteur.setEssai(this.getEssai());
        retourPromoteur.setPharmacie(this.getPharmacie());
        retourPromoteur.setProduit(this.getProduit());
        retourPromoteur.setConditionnement(this.getConditionnement());
        retourPromoteur.setNumLot("numLot1");
        retourPromoteur.setType(TypeMvtStock.RETOUR_PROMOTEUR);
        retourPromoteur.setQuantite(1);
        retourPromoteur.setApproApprouve(true);
        sorties.add(retourPromoteur);

        final CessionPui cessionPui = new CessionPui();
        cessionPui.setEssai(this.getEssai());
        cessionPui.setPharmacie(this.getPharmacie());
        cessionPui.setProduit(this.getProduit());
        cessionPui.setConditionnement(this.getConditionnement());
        cessionPui.setNumLot("numLot2");
        cessionPui.setQuantite(1);
        cessionPui.setType(TypeMvtStock.CESSION_PUI);
        cessionPui.setApproApprouve(false);
        sorties.add(cessionPui);

        final Stockage stockage = new Stockage();
        stockage.setNom("stockage");
        Mockito.when(this.produitServiceMock.reattach(this.getProduit())).thenReturn(this.getProduit());
        Mockito.when(this.produitServiceMock.getStockageProduitPharma(this.getProduit(), this.getPharmacie())).thenReturn(stockage);

        Mockito.when(this.mvtStockServiceMock.getAll((SearchCriteria) Matchers.any())).thenReturn(appros, sorties, new ArrayList<MvtStock>());

        Mockito.when(this.mockDao.save(Matchers.any(LigneStock.class))).thenAnswer(new Answer<LigneStock>() {

            @Override
            public LigneStock answer(final InvocationOnMock invocation) throws Throwable {
                return (LigneStock) invocation.getArguments()[0];

            }
        });

        final List<LigneStock> lignesBdd = this.service.initialiseTableLigneStock();

        Mockito.when(this.mockDao.getAll((SearchCriteria) Matchers.any())).thenReturn(lignesBdd);
        final List<LigneStock> result = this.service.getAllLignesStock(this.getEssai(), this.getPharmacie(), this.getProduit(), this.getConditionnement(), false);
        Assert.assertEquals(1, result.size());
        final LigneStock ligneResult = result.get(0);
        Assert.assertEquals(1, ligneResult.getQteEnStock().intValue());

    }

    /**
     * Méthode en charge de tester la récupération des lignes de stock.
     */
    @Test
    public void testGetAllLignesStockWithApproFoncIdentiques() {
        final List<MvtStock> appros = new ArrayList<MvtStock>();
        final Approvisionnement appro1 = new Approvisionnement();
        appro1.setEssai(this.getEssai());
        appro1.setPharmacie(this.getPharmacie());
        appro1.setProduit(this.getProduit());
        appro1.setConditionnement(this.getConditionnement());
        appro1.setApproApprouve(Boolean.TRUE);
        appro1.setType(TypeMvtStock.APPROVISIONNEMENT);
        appro1.setNumLot("numLot1");
        appro1.setQuantite(2);
        appros.add(appro1);

        final Approvisionnement appro2 = new Approvisionnement();
        appro2.setEssai(this.getEssai());
        appro2.setPharmacie(this.getPharmacie());
        appro2.setProduit(this.getProduit());
        appro2.setConditionnement(this.getConditionnement());
        appro2.setNumLot("numLot1");
        appro2.setType(TypeMvtStock.APPROVISIONNEMENT);
        appro2.setQuantite(1);
        appro2.setApproApprouve(Boolean.TRUE);
        appros.add(appro2);

        final List<MvtStock> sorties = new ArrayList<MvtStock>();
        final RetourPromoteur retourPromoteur = new RetourPromoteur();
        retourPromoteur.setEssai(this.getEssai());
        retourPromoteur.setPharmacie(this.getPharmacie());
        retourPromoteur.setProduit(this.getProduit());
        retourPromoteur.setConditionnement(this.getConditionnement());
        retourPromoteur.setNumLot("numLot1");
        retourPromoteur.setType(TypeMvtStock.RETOUR_PROMOTEUR);
        retourPromoteur.setQuantite(1);
        retourPromoteur.setApproApprouve(true);
        sorties.add(retourPromoteur);

        final Stockage stockage = new Stockage();
        stockage.setNom("stockage");
        Mockito.when(this.produitServiceMock.reattach(this.getProduit())).thenReturn(this.getProduit());
        Mockito.when(this.produitServiceMock.getStockageProduitPharma(this.getProduit(), this.getPharmacie())).thenReturn(stockage);

        Mockito.when(this.mvtStockServiceMock.getAll((SearchCriteria) Matchers.any())).thenReturn(appros, sorties, new ArrayList<MvtStock>());
        Mockito.when(this.mockDao.save(Matchers.any(LigneStock.class))).thenAnswer(new Answer<LigneStock>() {

            @Override
            public LigneStock answer(final InvocationOnMock invocation) throws Throwable {
                return (LigneStock) invocation.getArguments()[0];

            }
        });

        final List<LigneStock> lignesBdd = this.service.initialiseTableLigneStock();

        Mockito.when(this.mockDao.getAll((SearchCriteria) Matchers.any())).thenReturn(lignesBdd);

        final List<LigneStock> result = this.service.getAllLignesStock(this.getEssai(), this.getPharmacie(), this.getProduit(), this.getConditionnement(), false);
        Assert.assertEquals(1, result.size());
        final LigneStock ligneResult = result.get(0);
        Assert.assertEquals(2, ligneResult.getQteEnStock().intValue());

    }

    /**
     * Méthode en charge de tester la génération de clé pour un mouvement de
     * stock.
     */
    @Test
    public void testInitLignesStock_TypeDispensation_NOMINATIVE() {
        final MvtStock mvtStock = new Approvisionnement();
        mvtStock.setEssai(this.getEssai());
        mvtStock.setPharmacie(this.getPharmacie());
        mvtStock.setProduit(this.getProduit());
        mvtStock.setConditionnement(this.getConditionnement());
        mvtStock.setNumLot("numLot1");
        mvtStock.setNumTraitement("numTraitement");
        mvtStock.setApproApprouve(true);
        final Sortie sortie = new Sortie();
        sortie.setMvtSortie(mvtStock);

        final LigneStock ligne1 = new LigneStock(this.getEssai(), this.getPharmacie(), this.getProduit(), this.getConditionnement(), true);
        ligne1.setQteGlobalStock(12);

        final LigneStock ligne2 = new LigneStock(this.getEssai(), this.getPharmacie(), this.getProduit(), this.getConditionnement(), true);
        ligne2.setQteGlobalStock(24);
        ligne2.setStockage(LigneStock.EN_QUARANTAINE);

        final LigneStock ligne3 = new LigneStock(this.getEssai(), this.getPharmacie(), this.getProduit(), this.getConditionnement(), true);
        ligne3.setQteGlobalStock(0);

        Mockito.when(this.mockDao.getAll(Matchers.any(SearchCriteria.class))).thenReturn(Arrays.asList(ligne1, ligne2, ligne3));

        this.service.initLignesStock(TypeDispensation.NOMINATIVE, sortie);
        Assert.assertNotNull(sortie.getLignesStock());

        // Seules les lignes stock qui ont une quantité > 0 et qui ne sont pas
        // "En Quarantaine" doivent être affectées
        Assert.assertEquals(1, sortie.getLignesStock().size());
        Assert.assertEquals(ligne1, sortie.getLignesStock().get(0));
    }

    /**
     * Classe en charge de tester la méthode de sauvegarde de ligne de stock
     */
    @Test
    public void testSave() {
        final LigneStock l1 = new LigneStock();
        l1.setId(1L);
        Mockito.when(this.mockDao.save(l1)).thenReturn(l1);
        Mockito.when(this.mockDao.reattach(l1)).thenReturn(l1);
        this.service.save(l1);
        Mockito.verify(this.mockDao).save(l1);
        Mockito.verify(this.mockDao).remove(l1);

        final LigneStock l2 = new LigneStock();
        l2.setId(2L);
        l2.setQteGlobalStock(2);
        Mockito.when(this.mockDao.save(l2)).thenReturn(l2);
        Mockito.when(this.mockDao.reattach(l2)).thenReturn(l2);
        this.service.save(l2);
        Mockito.verify(this.mockDao).save(l2);
        Mockito.verify(this.mockDao, Mockito.times(0)).remove(l2);

        final LigneStock l3 = new LigneStock();
        l3.setId(3L);
        l3.setQteDispensationGlobal(2);
        Mockito.when(this.mockDao.save(l3)).thenReturn(l3);
        Mockito.when(this.mockDao.reattach(l3)).thenReturn(l3);
        this.service.save(l3);
        Mockito.verify(this.mockDao).save(l3);
        Mockito.verify(this.mockDao, Mockito.times(0)).remove(l3);

        final LigneStock l4 = new LigneStock();
        l4.setId(4L);
        l4.setQteGlobalStock(-1);
        Mockito.when(this.mockDao.save(l4)).thenReturn(l4);
        Mockito.when(this.mockDao.reattach(l4)).thenReturn(l4);
        this.service.save(l4);
        Mockito.verify(this.mockDao).save(l4);
        Mockito.verify(this.mockDao, Mockito.times(0)).remove(l4);
    }

    @Test
    public void testEtendrePeremptionBasic() {
        // Prepare
        final Calendar newDatePeremption = Calendar.getInstance();
        final List<MvtStock> mvts = this.prepareTestEtendrePeremption(newDatePeremption);

        final Approvisionnement approTmp = new Approvisionnement();
        approTmp.setId(1L);
        approTmp.setDatePeremption(newDatePeremption);

        // Test
        this.service.etendrePeremption(approTmp);

        // Verify
        for (final MvtStock mvt : mvts) {
            Assert.assertEquals(newDatePeremption, mvt.getDatePeremption());
        }
    }

    /**
     * Création d'une liste de MvtStock ; preparer les réponses des mocks
     * @return la liste de MvtStock
     */
    private List<MvtStock> prepareTestEtendrePeremption(final Calendar newDatePeremption) {
        final Approvisionnement approBase = new Approvisionnement();
        approBase.setId(1L);
        final Calendar origDatePeremption = Calendar.getInstance();
        approBase.setDatePeremption(origDatePeremption);

        final Approvisionnement entreeCorrective = new Approvisionnement();
        entreeCorrective.setType(TypeMvtStock.ENTREE_CORRECTIVE);
        entreeCorrective.setId(2L);
        entreeCorrective.setDatePeremption(origDatePeremption);

        final PreparationEntree entree = new PreparationEntree();
        entree.setId(3L);
        entree.setDatePeremption(Calendar.getInstance());

        final DispensationProduit mvtStockBase = new DispensationProduit();

        final List<MvtStock> mvts = new ArrayList<MvtStock>();
        mvts.add(approBase);
        mvts.add(entreeCorrective);
        mvts.add(entree);
        mvts.add(mvtStockBase);

        Mockito.when(this.approServiceMock.get(1L)).thenReturn(approBase);
        Mockito.when(this.mvtStockServiceMock.getAll((SearchCriteria) Matchers.any())).thenReturn(mvts);

        return mvts;
    }

    @Test
    public void testUpdateDatePeremptionDesMvtStocks() throws InterruptedException {
        // Prepare
        final Calendar newDatePeremption = Calendar.getInstance();
        Thread.sleep(1);
        final List<MvtStock> mvts = this.prepareTestEtendrePeremption(newDatePeremption);

        final Approvisionnement approTmp = new Approvisionnement();
        approTmp.setId(1L);
        approTmp.setDatePeremption(newDatePeremption);

        final String commentaire = "TEST";

        // Test
        this.service.updateDatePeremptionDesMvtStocks(approTmp, newDatePeremption, commentaire);

        // Verify
        // Les mvts de type "ENTREE" n'ont pas de date à jour parce que cette
        // manipulation est fait dans une autre méthode
        for (final MvtStock mvt : mvts) {
            if (Arrays.asList(TypeMvtStock.ENTREES).contains(mvt.getType())) {
                Assert.assertEquals(commentaire, ((Approvisionnement) mvt).getCommentaireExtensionPeremption());
            } else {
                Assert.assertEquals(newDatePeremption, mvt.getDatePeremption());
            }
        }

        Mockito.verify(this.approServiceMock, Mockito.times(3)).updateDatePeremption((Approvisionnement) Matchers.any(), (Calendar) Matchers.any());
    }

    /**
     * Update lignestock; pas de fusion avec une autre ligne
     */
    @Test
    public void testUpdateLigneStockNonFusion() {
        // Prepare
        final Approvisionnement appro = new Approvisionnement();
        appro.setDatePeremption(Calendar.getInstance());
        final LigneStock oldLigneStock = new LigneStock();

        Mockito.when(this.mockDao.getAll((SearchCriteria) Matchers.any())).thenReturn(new ArrayList<LigneStock>());

        // Test
        this.service.updateLigneStock(appro, oldLigneStock);

        // Verify
        Assert.assertEquals(appro.getDatePeremption(), oldLigneStock.getDatePeremption());
    }

    /**
     * Fusionner lignestock avec stock déjà en base
     * @throws InterruptedException
     */
    @Test
    public void testUpdateLigneStockFusion() throws InterruptedException {
        // Prepare
        final Approvisionnement appro = new Approvisionnement();
        appro.setDatePeremption(Calendar.getInstance());

        final LigneStock newLigneStock = new LigneStock();
        newLigneStock.setId(1L);
        Thread.sleep(1);
        newLigneStock.setDatePeremption(Calendar.getInstance());

        final List<LigneStock> lignesStock = new ArrayList<LigneStock>();
        lignesStock.add(newLigneStock);

        Mockito.when(this.mockDao.getAll((SearchCriteria) Matchers.any())).thenReturn(lignesStock);

        final LigneStock oldLigneStock = new LigneStock();

        // Test
        this.service.updateLigneStock(appro, oldLigneStock);

        // Verify
        Assert.assertNotEquals(appro.getDatePeremption(), oldLigneStock.getDatePeremption());
    }

    /**
     * Construction d'un objet Essai de test.
     * @return Essai.
     */
    private Essai getEssai() {
        final Essai essai = new Essai();
        essai.setId(1L);
        return essai;
    }

    /**
     * Construction d'un objet Pharmacie de test.
     * @return Pharmacie.
     */
    private Pharmacie getPharmacie() {
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);
        return pharmacie;
    }

    /**
     * Construction d'un objet Produit de test.
     * @return Produit.
     */
    private Produit getProduit() {
        final Produit produit = new Medicament();
        produit.setId(1L);
        return produit;
    }

    /**
     * Construction d'un objet Conditionnement de test.
     * @return Conditionnement.
     */
    private Conditionnement getConditionnement() {
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setId(1L);
        return conditionnement;
    }
}
