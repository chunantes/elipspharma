package fr.pharma.eclipse.service.stock.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.stock.StockSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.EtatLigneStock;
import fr.pharma.eclipse.domain.model.stock.EtatStock;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stock.StockService;

/**
 * Classe en charge de tester le service d'état de stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EtatStockServiceImplTest {
    /**
     * Service d'état de stock à tester.
     */
    private EtatStockServiceImpl service;

    /**
     * Service de gestion des mouvements de stock mocké.
     */
    private StockService mockStockService;

    /**
     * Service de gestion des produits mocké.
     */
    private ProduitService<Produit> mockProduitService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.service = new EtatStockServiceImpl();
        this.mockStockService = Mockito.mock(StockService.class);
        this.mockProduitService = Mockito.mock(ProduitService.class);
        this.service.setStockService(this.mockStockService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.service = null;
        this.mockStockService = null;
        this.mockProduitService = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.mockStockService);
    }

    /**
     * Méthode en charge de tester la récupération des lignes d'Etat de Stock.
     */
    @Test
    public void testGetLinesEtatStock() {
        final LigneStock appro = new LigneStock();
        appro.setEssai(this.getEssai());
        appro.setPharmacie(this.getPharmacie());
        appro.setProduit(this.getProduit());
        appro.setConditionnement(this.getConditionnement());
        appro.setApproApprouve(Boolean.TRUE);
        appro.setQteGlobalStock(2);
        appro.setStockage("nom");
        final List<LigneStock> lignes = new ArrayList<LigneStock>();
        lignes.add(appro);

        final Stockage stockage = new Stockage();
        stockage.setNom("nom");

        final StockSearchCriteria criteria = Mockito.mock(StockSearchCriteria.class);
        Mockito.when(this.mockStockService.getAll(criteria)).thenReturn(lignes);
        final List<EtatStock> result = this.service.getLinesEtatStock(criteria);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(2, result.get(0).getQteEnStock().intValue());
        Assert.assertEquals("nom", result.get(0).getStockage());

    }

    /**
     * Méthode en charge de tester la récupération des lignes d'Etat de Stock.
     */
    @Test
    public void testGetLinesEtatStockMultiEntree() {
        final Stockage stockage = new Stockage();
        stockage.setNom("nom");

        final LigneStock appro = new LigneStock();
        appro.setEssai(this.getEssai());
        appro.setPharmacie(this.getPharmacie());
        appro.setProduit(this.getProduit());
        appro.setConditionnement(this.getConditionnement());
        appro.setApproApprouve(Boolean.TRUE);
        appro.setQteGlobalStock(2);
        appro.setStockage("nom");

        final List<LigneStock> mvts = new ArrayList<LigneStock>();
        mvts.add(appro);

        final StockSearchCriteria criteria = Mockito.mock(StockSearchCriteria.class);
        Mockito.when(this.mockStockService.getAll(criteria)).thenReturn(mvts);

        final List<EtatStock> result = this.service.getLinesEtatStock(criteria);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(2, result.get(0).getQteEnStock().intValue());
        Assert.assertEquals("nom", result.get(0).getStockage());

    }

    /**
     * Méthode en charge de tester la récupération des lignes d'Etat de Stock.
     */
    @Test
    public void testGetLinesEtatStockNotApprouve() {
        final LigneStock appro = new LigneStock();
        appro.setEssai(this.getEssai());
        appro.setPharmacie(this.getPharmacie());
        appro.setProduit(this.getProduit());
        appro.setConditionnement(this.getConditionnement());
        appro.setApproApprouve(Boolean.FALSE);
        appro.setStockage("nom");
        appro.setQteGlobalStock(2);

        final LigneStock retourPromoteur = new LigneStock();
        retourPromoteur.setEssai(this.getEssai());
        retourPromoteur.setPharmacie(this.getPharmacie());
        retourPromoteur.setProduit(this.getProduit());
        retourPromoteur.setConditionnement(this.getConditionnement());
        retourPromoteur.setApproApprouve(Boolean.TRUE);
        retourPromoteur.setQteGlobalStock(1);

        final List<LigneStock> mvts = new ArrayList<LigneStock>();
        mvts.add(appro);
        mvts.add(retourPromoteur);

        final Stockage stockage = new Stockage();
        stockage.setNom("nom");

        final StockSearchCriteria criteria = Mockito.mock(StockSearchCriteria.class);
        Mockito.when(this.mockStockService.getAll(criteria)).thenReturn(mvts);
        final List<EtatStock> result = this.service.getLinesEtatStock(criteria);
        Assert.assertEquals(2, result.size());
        Assert.assertEquals(2, result.get(0).getQteEnStock().intValue());
        Assert.assertEquals(1, result.get(1).getQteEnStock().intValue());
        Assert.assertEquals("nom", result.get(0).getStockage());

    }

    /**
     * Méthode en charge de tester la méthode de construction de clé à partir
     * d'un mouvement.
     */
    @Test
    public void testGetKeyMvtStock() {
        final EtatStock mvt = new EtatStock(this.getEssai(), this.getPharmacie(), this.getProduit(), this.getConditionnement(), Boolean.TRUE);

        mvt.setEssai(this.getEssai());
        mvt.setPharmacie(this.getPharmacie());
        mvt.setEnQuarantaine(false);
        mvt.setProduit(this.getProduit());
        mvt.setConditionnement(this.getConditionnement());
        Assert.assertEquals("1111true", mvt.getKeyStock());
    }

    /**
     * Méthode en charge de tester la méthode de construction de clé à partir
     * d'un état de stock.
     */
    @Test
    public void testGetKeyEtatStock() {
        // final EtatStock stock = new EtatStock(this.getEssai(),
        // this.getPharmacie(), this.getProduit(), this.getConditionnement(),
        // Boolean.TRUE);
        final EtatLigneStock mvt = new EtatLigneStock("lot", "f", null, Integer.SIZE);
        // mvt.setParent(stock);

        Assert.assertEquals("lotf", mvt.getKeyLigneStock(false));
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
