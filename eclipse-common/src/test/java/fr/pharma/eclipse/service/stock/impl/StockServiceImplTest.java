package fr.pharma.eclipse.service.stock.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.stock.LigneStockSearchCriteria;
import fr.pharma.eclipse.domain.enums.TypeDispensation;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stock.Sortie;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stock.MvtStockService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester le service de gestion des stocks.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class StockServiceImplTest {
    /**
     * StockService à tester.
     */
    @InjectMocks
    private StockServiceImpl service;

    /**
     * Mocks
     */
    @Mock
    private GenericDao<LigneStock> mockDao;
    @Mock
    private GenericServiceImpl<LigneStock> mockService;
    @Mock
    private MvtStockService<MvtStock> mvtStockServiceMock;
    @Mock
    private ProduitService<Produit> produitServiceMock;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.mockDao = Mockito.mock(GenericDao.class);
        this.mockService = Mockito.mock(GenericServiceImpl.class);
        this.service = new StockServiceImpl(this.mockDao);
        MockitoAnnotations.initMocks(this);
    }
    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.service = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.mvtStockServiceMock);
        Assert.assertNotNull(this.produitServiceMock);
        Assert.assertNotNull(this.mockService);
    }

    @Test
    public void testInitialiseTableLigneStock() {
        // Prepare
        final List<MvtStock> appros = new ArrayList<MvtStock>();
        appros.add(this.getMvtStock(TypeMvtStock.APPROVISIONNEMENT, "numLot1", 2, true));
        appros.add(this.getMvtStock(TypeMvtStock.APPROVISIONNEMENT, "numLot2", 1, false));

        final List<MvtStock> sorties = new ArrayList<MvtStock>();
        sorties.add(this.getMvtStock(TypeMvtStock.RETOUR_PROMOTEUR, "numLot1", 1, true));
        sorties.add(this.getMvtStock(TypeMvtStock.CESSION_PUI, "numLot2", 1, false));

        final Stockage stockage = new Stockage();
        stockage.setNom("stockage");

        Mockito.when(this.produitServiceMock.reattach(this.getProduit())).thenReturn(this.getProduit());
        Mockito.when(this.produitServiceMock.getStockageProduitPharma(this.getProduit(), this.getPharmacie())).thenReturn(stockage);

        // Ignorer le warning : devoir remplacer thenReturn(a,b,c) avec
        // thenReturn(a), thenReturn(b), ... mais le test ne marche plus !
        Mockito.when(this.mvtStockServiceMock.getAll((SearchCriteria) Matchers.any())).thenReturn(appros, sorties, new ArrayList<MvtStock>());

        Mockito.when(this.mockDao.save(Matchers.any(LigneStock.class))).thenAnswer(new Answer<LigneStock>() {

            @Override
            public LigneStock answer(final InvocationOnMock invocation) throws Throwable {
                return (LigneStock) invocation.getArguments()[0];

            }
        });

        // Test
        final List<LigneStock> lignesBdd = this.service.initialiseTableLigneStock();

        Mockito.when(this.mockDao.getAll((SearchCriteria) Matchers.any())).thenReturn(lignesBdd);
        final List<LigneStock> result = this.service.getLignesStockPharmacie(this.getEssai(), this.getPharmacie(), this.getProduit(), this.getConditionnement());

        // Verifier
        Assert.assertEquals(1, result.size());
        final LigneStock ligne = result.get(0);
        Assert.assertEquals(1, ligne.getQteEnStock().intValue());
    }

    @Test
    public void testInitialiseTableLigneStockWithApproFoncIdentiques() {
        // Prepare
        final List<MvtStock> appros = new ArrayList<MvtStock>();
        appros.add(this.getMvtStock(TypeMvtStock.APPROVISIONNEMENT, "numLot1", 2, true));
        appros.add(this.getMvtStock(TypeMvtStock.APPROVISIONNEMENT, "numLot1", 1, true));

        final List<MvtStock> sorties = new ArrayList<MvtStock>();
        sorties.add(this.getMvtStock(TypeMvtStock.RETOUR_PROMOTEUR, "numLot1", 1, true));

        final Stockage stockage = new Stockage();
        stockage.setNom("stockage");
        Mockito.when(this.produitServiceMock.reattach(this.getProduit())).thenReturn(this.getProduit());
        Mockito.when(this.produitServiceMock.getStockageProduitPharma(this.getProduit(), this.getPharmacie())).thenReturn(stockage);

        // Ignorer le warning : devoir remplacer thenReturn(a,b,c) avec
        // thenReturn(a), thenReturn(b), ... mais le test ne marche plus !
        Mockito.when(this.mvtStockServiceMock.getAll((SearchCriteria) Matchers.any())).thenReturn(appros, sorties, new ArrayList<MvtStock>());
        Mockito.when(this.mockDao.save(Matchers.any(LigneStock.class))).thenAnswer(new Answer<LigneStock>() {

            @Override
            public LigneStock answer(final InvocationOnMock invocation) throws Throwable {
                return (LigneStock) invocation.getArguments()[0];

            }
        });

        // Test
        final List<LigneStock> lignesBdd = this.service.initialiseTableLigneStock();

        Mockito.when(this.mockDao.getAll((SearchCriteria) Matchers.any())).thenReturn(lignesBdd);
        final List<LigneStock> result = this.service.getLignesStockPharmacie(this.getEssai(), this.getPharmacie(), this.getProduit(), this.getConditionnement());

        // Verifier
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
        ligne1.setQtePharmacie(12);

        final LigneStock ligne2 = new LigneStock(this.getEssai(), this.getPharmacie(), this.getProduit(), this.getConditionnement(), true);
        ligne2.setQtePharmacie(24);
        ligne2.setStockage(LigneStock.EN_QUARANTAINE);

        final LigneStock ligne3 = new LigneStock(this.getEssai(), this.getPharmacie(), this.getProduit(), this.getConditionnement(), true);
        ligne3.setQtePharmacie(0);

        Mockito.when(this.mockDao.getAll(Matchers.any(SearchCriteria.class))).thenReturn(Arrays.asList(ligne1, ligne2, ligne3));

        this.service.initLignesStock(TypeDispensation.NOMINATIVE, sortie);
        Assert.assertNotNull(sortie.getLignesStock());

        // Seules les lignes stock qui ont une quantité > 0 et qui ne sont pas
        // "En Quarantaine" doivent être affectées
        Assert.assertEquals(1, sortie.getLignesStock().size());
        Assert.assertEquals(ligne1, sortie.getLignesStock().get(0));
    }

    @Test
    public void testSaveNoStock() {
        final LigneStock l1 = new LigneStock();
        l1.setId(1L);
        Mockito.when(this.mockDao.save(l1)).thenReturn(l1);
        Mockito.when(this.mockDao.reattach(l1)).thenReturn(l1);
        this.service.save(l1);
        Mockito.verify(this.mockDao).save(l1);
        Mockito.verify(this.mockDao).remove(l1);
    }

    @Test
    public void testSaveWithPharmacieStock() {
        final LigneStock l2 = new LigneStock();
        l2.setId(2L);
        l2.setQtePharmacie(2);
        Mockito.when(this.mockDao.save(l2)).thenReturn(l2);
        Mockito.when(this.mockDao.reattach(l2)).thenReturn(l2);
        this.service.save(l2);
        Mockito.verify(this.mockDao).save(l2);
        Mockito.verify(this.mockDao, Mockito.never()).remove(l2);
    }

    @Test
    public void testSaveWithDispGlobal() {
        final LigneStock l3 = new LigneStock();
        l3.setId(3L);
        l3.setQteDispensationGlobal(2);
        Mockito.when(this.mockDao.save(l3)).thenReturn(l3);
        Mockito.when(this.mockDao.reattach(l3)).thenReturn(l3);
        this.service.save(l3);
        Mockito.verify(this.mockDao).save(l3);
        Mockito.verify(this.mockDao, Mockito.never()).remove(l3);
    }

    @Test
    public void testSaveWithStockPharmacieNegative() {
        final LigneStock l4 = new LigneStock();
        l4.setId(4L);
        l4.setQtePharmacie(-1);

        Mockito.when(this.mockDao.save(l4)).thenReturn(l4);
        Mockito.when(this.mockDao.reattach(l4)).thenReturn(l4);
        this.service.save(l4);
        Mockito.verify(this.mockDao).save(l4);
        Mockito.verify(this.mockDao).remove(l4);
    }

    @Test
    public void testSaveWithMAJStockage() {
        // Prepare
        final LigneStock l4 = new LigneStock();
        l4.setId(4L);
        l4.setQtePharmacie(10);
        l4.setStockage("Lieu 1");
        l4.setProduit(this.getProduit());
        l4.setPharmacie(this.getPharmacie());

        final Stockage stockage = new Stockage();
        stockage.setNom("Lieu 2");
        Mockito.when(this.produitServiceMock.getStockageProduitPharma(Matchers.any(Produit.class), Matchers.any(Pharmacie.class))).thenReturn(stockage);

        Mockito.when(this.mockDao.save(l4)).thenReturn(l4);
        Mockito.when(this.mockDao.reattach(l4)).thenReturn(l4);

        // Test
        this.service.save(l4);

        // Verify
        final ArgumentCaptor<LigneStock> argument = ArgumentCaptor.forClass(LigneStock.class);
        Mockito.verify(this.mockDao).save(argument.capture());
        Assert.assertEquals("Lieu 2", argument.getValue().getStockage());

        Mockito.verify(this.mockDao, Mockito.never()).remove(l4);
    }

    @Test
    public void testGetLigneStockEmpty() {
        // Prepare
        final List<LigneStock> lignes = new ArrayList<LigneStock>();
        Mockito.when(this.mockDao.getAll(Matchers.any(SearchCriteria.class))).thenReturn(lignes);

        // Test
        final LigneStock result = this.service.getLigneStock(Mockito.mock(MvtStock.class));

        // Verify
        Assert.assertEquals(StockServiceImpl.NO_LIGNESTOCK, result);
    }

    @Test
    public void testGetLigneStock() {
        // Prepare
        final LigneStock ligne = Mockito.mock(LigneStock.class);
        final List<LigneStock> lignes = new ArrayList<LigneStock>();
        lignes.add(ligne);
        Mockito.when(this.mockDao.getAll(Matchers.any(SearchCriteria.class))).thenReturn(lignes);

        // Test
        final LigneStock result = this.service.getLigneStock(Mockito.mock(MvtStock.class));

        // Verify
        Assert.assertEquals(ligne, result);
    }

    @Test
    public void testGetLigneStockFusion() {
        // Prepare
        final LigneStock ligne1 = new LigneStock();
        ligne1.setQtePharmacie(3);
        ligne1.setQteDispensationGlobal(5);

        final LigneStock ligne2 = new LigneStock();
        ligne2.setQtePharmacie(7);
        ligne2.setQteDispensationGlobal(11);

        final List<LigneStock> lignes = new ArrayList<LigneStock>();
        lignes.add(ligne1);
        lignes.add(ligne2);
        Mockito.when(this.mockDao.getAll(Matchers.any(SearchCriteria.class))).thenReturn(lignes);

        // Test
        final LigneStock result = this.service.getLigneStock(Mockito.mock(MvtStock.class));

        // Verify
        Assert.assertEquals(new Integer(10), result.getQtePharmacie());
        Assert.assertEquals(new Integer(16), result.getQteDispensationGlobal());
    }

    @Test
    public void testRetrieveLigneStockNoMatch() {
        // Prepare
        final List<LigneStock> lignes = new ArrayList<LigneStock>();
        Mockito.when(this.mockDao.getAll(Matchers.any(SearchCriteria.class))).thenReturn(lignes);

        final MvtStock mvt = Mockito.mock(MvtStock.class);
        Mockito.when(mvt.getEssai()).thenReturn(this.getEssai());
        Mockito.when(mvt.getPharmacie()).thenReturn(this.getPharmacie());
        Mockito.when(mvt.getProduit()).thenReturn(this.getProduit());

        // Test
        final LigneStock result = this.service.getOrCreateLigneStock(mvt);

        // Verify
        Assert.assertEquals(mvt.getEssai(), result.getEssai());
        Assert.assertEquals(mvt.getPharmacie(), result.getPharmacie());
        Assert.assertEquals(mvt.getProduit(), result.getProduit());
        Assert.assertNull(result.getId());
    }

    @Test
    public void testUpdateAppro() {
        final int APPRO = 5;
        final int STOCK = 3;

        // Prepare
        final List<MvtStock> mvts = new ArrayList<MvtStock>();
        mvts.add(this.getMvtStock(TypeMvtStock.APPROVISIONNEMENT, "numLot", APPRO, true));

        final Stockage stockage = new Stockage();
        stockage.setNom("Lieu 2");
        Mockito.when(this.produitServiceMock.getStockageProduitPharma(Matchers.any(Produit.class), Matchers.any(Pharmacie.class))).thenReturn(stockage);

        final LigneStock ligne = new LigneStock();
        ligne.setProduit(this.getProduit());
        ligne.setPharmacie(this.getPharmacie());
        ligne.setQtePharmacie(STOCK);
        ligne.setStockage("Lieu 1");

        final List<LigneStock> lignes = new ArrayList<LigneStock>();
        lignes.add(ligne);
        Mockito.when(this.mockDao.getAll(Matchers.any(SearchCriteria.class))).thenReturn(lignes);

        // Test
        this.service.update(mvts);

        // Verify
        Assert.assertEquals(new Integer(APPRO + STOCK), ligne.getQteEnStock());
        Assert.assertEquals("Lieu 2", ligne.getStockage());
    }

    @Test
    public void testUpdateApproNewLigneStock() {
        final Integer APPRO = 5;

        // Prepare
        final List<MvtStock> mvts = new ArrayList<MvtStock>();
        mvts.add(this.getMvtStock(TypeMvtStock.APPROVISIONNEMENT, "numLot", APPRO, false));

        final Stockage stockage = new Stockage();
        stockage.setIdentifiantStockage("STOCK");
        Mockito.when(this.produitServiceMock.getStockageProduitPharma(Matchers.any(Produit.class), Matchers.any(Pharmacie.class))).thenReturn(stockage);

        // Test
        this.service.update(mvts);

        // Verify
        final ArgumentCaptor<LigneStock> argument = ArgumentCaptor.forClass(LigneStock.class);
        Mockito.verify(this.mockDao).save(argument.capture());
        Assert.assertEquals(APPRO, argument.getValue().getQteEnStock());
    }

    @Test
    public void testUpdateSortie() {
        final int SORTIE = 5;
        final int STOCK = 7;

        // Prepare
        final List<MvtStock> mvts = new ArrayList<MvtStock>();
        mvts.add(this.getMvtStock(TypeMvtStock.PREPARATION_SORTIE, "numLot", SORTIE, false));

        final Stockage stockage = new Stockage();
        stockage.setIdentifiantStockage("STOCK");
        Mockito.when(this.produitServiceMock.getStockageProduitPharma(Matchers.any(Produit.class), Matchers.any(Pharmacie.class))).thenReturn(stockage);

        final LigneStock ligne = new LigneStock();
        ligne.setQtePharmacie(STOCK);
        final List<LigneStock> lignes = new ArrayList<LigneStock>();
        lignes.add(ligne);
        Mockito.when(this.mockDao.getAll(Matchers.any(SearchCriteria.class))).thenReturn(lignes);

        // Test
        this.service.update(mvts);

        // Verify
        Assert.assertEquals(new Integer(STOCK - SORTIE), ligne.getQteEnStock());
    }

    @Test
    public void testUpdateSortieNoLigneStock() {
        final Integer SORTIE = 5;

        // Prepare
        final List<MvtStock> mvts = new ArrayList<MvtStock>();
        mvts.add(this.getMvtStock(TypeMvtStock.PREPARATION_SORTIE, "numLot", SORTIE, false));

        final Stockage stockage = new Stockage();
        stockage.setIdentifiantStockage("STOCK");
        Mockito.when(this.produitServiceMock.getStockageProduitPharma(Matchers.any(Produit.class), Matchers.any(Pharmacie.class))).thenReturn(stockage);

        // Test
        this.service.update(mvts);

        // Verify
        Mockito.verify(this.mockDao, Mockito.never()).save((LigneStock) Matchers.any());
    }
    
    @Test
    /**
     * Test de la méthode updateLigneStock dans le cas 
     * indiqué par le ticket PHARMA-670 :
     * Modification de la date de péremption pour que deux lignes de stock 
     * du même numéro de lot ait la même date de péremption
     * 
     */
	public void testUpdateLigneStockAvecLigneStockNewDate() {
    	
    	//Prepare
    	Essai essai = new Essai();
    	Pharmacie pharmacie = new Pharmacie();
    	Produit produit = new Medicament();
    	Conditionnement conditionnement = new Conditionnement();
    	String numLot = "numLot";
    	String numTraitement = "numTraitement";
    	DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
    	DateTime oldDt = fmt.parseDateTime("01/01/2015");
    	Calendar oldDatePeremption = oldDt.toCalendar(EclipseConstants.LOCALE);
    	DateTime newDt = fmt.parseDateTime("15/05/2015");
    	Calendar newDatePeremption = newDt.toCalendar(EclipseConstants.LOCALE);
    	
    	
    	Approvisionnement mvtstockOldDate = new Approvisionnement();
    	mvtstockOldDate.setId(new Long(1));
    	mvtstockOldDate.setEssai(essai);
    	mvtstockOldDate.setPharmacie(pharmacie);
    	mvtstockOldDate.setProduit(produit);
    	mvtstockOldDate.setConditionnement(conditionnement);
    	mvtstockOldDate.setNumLot(numLot);
    	mvtstockOldDate.setNumTraitement(numTraitement);
    	mvtstockOldDate.setDatePeremption(oldDatePeremption);
    	mvtstockOldDate.setApproApprouve(true);
    	
    	Approvisionnement mvtstockNewDate = new Approvisionnement();
    	mvtstockNewDate.setId(new Long(2));
    	mvtstockNewDate.setEssai(essai);
    	mvtstockNewDate.setPharmacie(pharmacie);
    	mvtstockNewDate.setProduit(produit);
    	mvtstockNewDate.setConditionnement(conditionnement);
    	mvtstockNewDate.setNumLot(numLot);
    	mvtstockNewDate.setNumTraitement(numTraitement);
    	mvtstockNewDate.setDatePeremption(newDatePeremption);
    	mvtstockNewDate.setApproApprouve(true);
    	
    	LigneStock ligneStockOldDate = new LigneStock();
    	ligneStockOldDate.setId(new Long(1));
    	ligneStockOldDate.setEssai(essai);
    	ligneStockOldDate.setPharmacie(pharmacie);
    	ligneStockOldDate.setProduit(produit);
    	ligneStockOldDate.setConditionnement(conditionnement);
    	ligneStockOldDate.setNumLot(numLot);
    	ligneStockOldDate.setNumTraitement(numTraitement);
    	ligneStockOldDate.setDatePeremption(oldDatePeremption);
    	ligneStockOldDate.setQtePharmacie(20);
    	
    	List<LigneStock> listeLigneStockOldDate = new ArrayList<LigneStock>();
    	listeLigneStockOldDate.add(ligneStockOldDate);
    	
    	LigneStock ligneStockNewDate = new LigneStock();
    	ligneStockNewDate.setId(new Long(2));
    	ligneStockNewDate.setEssai(essai);
    	ligneStockNewDate.setPharmacie(pharmacie);
    	ligneStockNewDate.setProduit(produit);
    	ligneStockNewDate.setConditionnement(conditionnement);
    	ligneStockNewDate.setNumLot(numLot);
    	ligneStockNewDate.setNumTraitement(numTraitement);
    	ligneStockNewDate.setDatePeremption(newDatePeremption);
    	ligneStockNewDate.setQtePharmacie(8);
    	
    	List<LigneStock> listeLigneStockNewDate = new ArrayList<LigneStock>();
    	listeLigneStockNewDate.add(ligneStockNewDate);
    	
    	final LigneStockSearchCriteria criteriaOldDate = new LigneStockSearchCriteria();
    	criteriaOldDate.setEssai(essai);
    	criteriaOldDate.setPharmacie(pharmacie);
    	criteriaOldDate.setProduit(produit);
    	criteriaOldDate.setConditionnement(conditionnement);
    	criteriaOldDate.setNumLot(numLot);
    	criteriaOldDate.setNumTraitement(numTraitement);
    	criteriaOldDate.setApproApprouve(true);
    	criteriaOldDate.setDatePeremption(oldDatePeremption);
    	
    	final LigneStockSearchCriteria criteriaNewDate = new LigneStockSearchCriteria();
    	criteriaNewDate.setEssai(essai);
    	criteriaNewDate.setPharmacie(pharmacie);
    	criteriaNewDate.setProduit(produit);
    	criteriaNewDate.setConditionnement(conditionnement);
    	criteriaNewDate.setNumLot(numLot);
    	criteriaNewDate.setNumTraitement(numTraitement);
    	criteriaNewDate.setApproApprouve(true);
    	criteriaNewDate.setDatePeremption(newDatePeremption);
    	
    	Mockito.when(this.mockDao.getAll((LigneStockSearchCriteria)Mockito.argThat(new IsOldDateMatcher()))).thenReturn(listeLigneStockOldDate);
    	Mockito.when(this.mockDao.getAll((LigneStockSearchCriteria)Mockito.argThat(new IsNewDateMatcher()))).thenReturn(listeLigneStockNewDate);
    	
    	Mockito.when(this.mockDao.save(ligneStockOldDate)).thenReturn(ligneStockOldDate);
    	Mockito.when(this.mockDao.save(ligneStockNewDate)).thenReturn(ligneStockNewDate);
    	
    	
    	Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {

                return null;
            }
        }).when(this.mockDao).remove(Matchers.any(LigneStock.class));
  	
    	
    	//Appel de la méthode testée
    	this.service.updateLigneStock(mvtstockOldDate, mvtstockNewDate);
    	
    	//Vérifications
    	Assert.assertEquals(new Integer(28), ligneStockOldDate.getQtePharmacie());
    	Assert.assertEquals(newDatePeremption, ligneStockOldDate.getDatePeremption());
    	Mockito.verify(this.mockDao).remove(Matchers.any(LigneStock.class));
    	Mockito.verify(this.mockDao).save(Matchers.any(LigneStock.class));
    	
	}
    
    /**
     * Matcher utilisé dans la méthode testUpdateLigneStockAvecLigneStockNewDate
     *
     */
    private class IsOldDateMatcher extends ArgumentMatcher {
        @Override
        public boolean matches(Object argument) {
        	DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
        	DateTime oldDt = fmt.parseDateTime("01/01/2015");
        	Calendar oldDatePeremption = oldDt.toCalendar(EclipseConstants.LOCALE);
            LigneStockSearchCriteria l = (LigneStockSearchCriteria) argument;
            if(l!=null && l.getDatePeremption().getTimeInMillis()==oldDatePeremption.getTimeInMillis()){
            	return true;
            }else{
            	return false;
            }            	
        }
    }
    
    /**
     * Matcher utilisé dans la méthode testUpdateLigneStockAvecLigneStockNewDate
     *
     */
    private class IsNewDateMatcher extends ArgumentMatcher {
        @Override
        public boolean matches(Object argument) {
        	DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
        	DateTime Dt = fmt.parseDateTime("15/05/2015");
        	Calendar datePeremption = Dt.toCalendar(EclipseConstants.LOCALE);
            LigneStockSearchCriteria l = (LigneStockSearchCriteria) argument;
            if(l!=null && l.getDatePeremption().getTimeInMillis()==datePeremption.getTimeInMillis()){
            	return true;
            }else{
            	return false;
            }            	
        }
    }

    /**
     * @return Essai de test
     */
    private Essai getEssai() {
        final Essai essai = new Essai();
        essai.setId(1L);
        return essai;
    }

    /**
     * @return Pharmacie de test
     */
    private Pharmacie getPharmacie() {
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);
        return pharmacie;
    }

    /**
     * @return Produit de test
     */
    private Produit getProduit() {
        final Produit produit = new Medicament();
        produit.setId(1L);
        return produit;
    }

    /**
     * @return Conditionnement de test
     */
    private Conditionnement getConditionnement() {
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setId(1L);
        return conditionnement;
    }

    /**
     * @return MvtStock de test avec les attributes donnés
     */
    private MvtStock getMvtStock(final TypeMvtStock type,
                                 final String numLot,
                                 final Integer quantite,
                                 final Boolean isApprouve) {
        final Approvisionnement mvt = new Approvisionnement();
        mvt.setEssai(this.getEssai());
        mvt.setPharmacie(this.getPharmacie());
        mvt.setProduit(this.getProduit());
        mvt.setConditionnement(this.getConditionnement());
        mvt.setType(type);
        mvt.setNumLot(numLot);
        mvt.setQuantite(quantite);
        mvt.setApproApprouve(isApprouve);

        return mvt;
    }
    
    

}
