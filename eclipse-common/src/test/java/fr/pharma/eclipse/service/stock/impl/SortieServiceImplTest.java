package fr.pharma.eclipse.service.stock.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.stock.RaisonSortie;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.domain.model.localisation.Etablissement;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.AutreSortie;
import fr.pharma.eclipse.domain.model.stock.CessionPui;
import fr.pharma.eclipse.domain.model.stock.Destruction;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stock.ResultSortie;
import fr.pharma.eclipse.domain.model.stock.RetourPromoteur;
import fr.pharma.eclipse.domain.model.stock.Sortie;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.factory.evenement.EvenementFactory;
import fr.pharma.eclipse.factory.stock.DispensationProduitFactory;
import fr.pharma.eclipse.factory.stock.MvtStockFactory;
import fr.pharma.eclipse.jasper.document.DocumentMakerDictionary;
import fr.pharma.eclipse.service.evenement.EvenementService;
import fr.pharma.eclipse.service.stock.MvtStockService;
import fr.pharma.eclipse.service.stock.StockService;
import fr.pharma.eclipse.service.user.UserService;
import fr.pharma.eclipse.utils.CacheUtils;

/**
 * Classe en charge de tester le service de sortie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SortieServiceImplTest {
    /**
     * Service de sortie à tester.
     */
    private SortieServiceImpl sortieService;

    /**
     * Service de gestion des retours promoteurs mocké.
     */
    private MvtStockService<RetourPromoteur> mockRetourService;

    /**
     * Factory des retours promoteurs mocké.
     */
    private MvtStockFactory<RetourPromoteur> mockRetourFactory;

    /**
     * Service de gestion des cessions Pui.
     */
    private MvtStockService<CessionPui> mockCessionPuiService;

    /**
     * Factory des cessions Pui.
     */
    private MvtStockFactory<CessionPui> mockCessionPuiFactory;

    /**
     * Service de gestion des destructions.
     */
    private MvtStockService<Destruction> mockDestructionService;

    /**
     * Factory des destructions.
     */
    private MvtStockFactory<Destruction> mockDestructionFactory;

    /**
     * Service de gestion des autres sorties.
     */
    private MvtStockService<AutreSortie> mockAutreSortieService;

    /**
     * Factory des autres sorties.
     */
    private MvtStockFactory<AutreSortie> mockAutreSortieFactory;

    /**
     * Factory des autres sorties.
     */
    private StockService stockService;

    /**
     * Factory de dispensationproduit.
     */
    private DispensationProduitFactory mockDispensationProduitFactory;

    /**
     * Service evement.
     */
    private EvenementService evenementService;

    /**
     * Factory d'évènement.
     */
    private EvenementFactory evenementFactory;

    /**
     * Service de gestion des utilisateurs.
     */
    private UserService mockUserService;

    /**
     * Essai mocké.
     */
    private Essai essai;

    /**
     * Pharmacie mockée.
     */
    private Pharmacie pharmacie;

    /**
     * Produit mocké.
     */
    private Produit produit;

    /**
     * Conditionnement mocké.
     */
    private Conditionnement conditionnement;

    /**
     * Dictionary Mocké.
     */
    private DocumentMakerDictionary dictionary;
    
    /**
     * CacheUtils
     */
    private CacheUtils cacheUtils;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.sortieService = new SortieServiceImpl();
        this.dictionary = Mockito.mock(DocumentMakerDictionary.class);
        this.mockRetourService = Mockito.mock(MvtStockService.class);
        this.mockRetourFactory = Mockito.mock(MvtStockFactory.class);
        this.mockCessionPuiService = Mockito.mock(MvtStockService.class);
        this.evenementFactory = Mockito.mock(EvenementFactory.class);
        this.evenementService = Mockito.mock(EvenementService.class);
        this.mockCessionPuiFactory = Mockito.mock(MvtStockFactory.class);
        this.mockDestructionService = Mockito.mock(MvtStockService.class);
        this.mockDestructionFactory = Mockito.mock(MvtStockFactory.class);
        this.mockAutreSortieService = Mockito.mock(MvtStockService.class);
        this.mockAutreSortieFactory = Mockito.mock(MvtStockFactory.class);
        this.mockAutreSortieFactory = Mockito.mock(MvtStockFactory.class);
        this.stockService = Mockito.mock(StockService.class);
        this.mockDispensationProduitFactory = Mockito.mock(DispensationProduitFactory.class);
        this.mockUserService = Mockito.mock(UserService.class);
        this.cacheUtils = Mockito.mock(CacheUtils.class);
        this.sortieService.setRetourService(this.mockRetourService);
        this.sortieService.setRetourFactory(this.mockRetourFactory);
        this.sortieService.setCessionPuiService(this.mockCessionPuiService);
        this.sortieService.setCessionPuiFactory(this.mockCessionPuiFactory);
        this.sortieService.setDocumentMakerDictionary(this.dictionary);
        this.sortieService.setDestructionService(this.mockDestructionService);
        this.sortieService.setEvenementFactory(this.evenementFactory);
        this.sortieService.setEvenementService(this.evenementService);
        this.sortieService.setDestructionFactory(this.mockDestructionFactory);
        this.sortieService.setAutreSortieService(this.mockAutreSortieService);
        this.sortieService.setAutreSortieFactory(this.mockAutreSortieFactory);
        this.sortieService.setDispensationProduitFactory(this.mockDispensationProduitFactory);
        this.sortieService.setUserService(this.mockUserService);
        this.sortieService.setStockService(this.stockService);
        this.sortieService.setCacheUtils(this.cacheUtils);
        this.essai = Mockito.mock(Essai.class);
        this.pharmacie = Mockito.mock(Pharmacie.class);
        this.produit = Mockito.mock(Produit.class);
        this.conditionnement = Mockito.mock(Conditionnement.class);
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.sortieService);
        Assert.assertNotNull(this.mockRetourService);
        Assert.assertNotNull(this.mockRetourFactory);
        Assert.assertNotNull(this.mockCessionPuiService);
        Assert.assertNotNull(this.mockCessionPuiFactory);
        Assert.assertNotNull(this.mockDestructionService);
        Assert.assertNotNull(this.mockDestructionFactory);
        Assert.assertNotNull(this.mockAutreSortieService);
        Assert.assertNotNull(this.mockAutreSortieFactory);
        Assert.assertNotNull(this.mockUserService);
        Assert.assertNotNull(this.essai);
        Assert.assertNotNull(this.pharmacie);
        Assert.assertNotNull(this.produit);
        Assert.assertNotNull(this.conditionnement);
    }

    /**
     * Méthode en charge de tester la sauvegarde de sortie de type
     * RetourPromoteur.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testSaveRetourPromoteur() {
        final String nomSocieteTransport = "nomSocieteTransport";
        final String referenceEnvoi = "referenceEnvoi";
        final String commentaire = "commentaire";

        final List<Sortie> sorties = new ArrayList<Sortie>();
        final Sortie sortie = new Sortie();
        final MvtStock mvt = new RetourPromoteur();
        this.completeMvt(mvt);
        sortie.setMvtSortie(mvt);
        sorties.add(sortie);
        final List<LigneStock> lignesStock = new ArrayList<LigneStock>();
        final LigneStock ligneStock = new LigneStock(this.essai, this.pharmacie, this.produit, this.conditionnement, Boolean.TRUE);
        ligneStock.setQteASortir(2);
        lignesStock.add(ligneStock);
        sortie.setLignesStock(lignesStock);

        final RetourPromoteur retourPromoteur = new RetourPromoteur();
        Mockito.when(this.mockRetourFactory.getInitializedObject()).thenReturn(retourPromoteur);

        final ResultSortie result =
            this.sortieService.save(RaisonSortie.FIN_ETUDE, null, TypeMvtStock.RETOUR_PROMOTEUR, nomSocieteTransport, referenceEnvoi, commentaire, null, sorties, null);

        Mockito.verify(this.mockRetourService, Mockito.times(1)).saveAll(Matchers.anyList());

        Assert.assertNotNull(result);
    }

    /**
     * Méthode en charge de tester la sauvegarde de sortie de type CessionPui.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testSaveCessionPui() {
        final String commentaire = "commentaire";

        final List<Sortie> sorties = new ArrayList<Sortie>();
        final Sortie sortie = new Sortie();
        final MvtStock mvt = new CessionPui();
        this.completeMvt(mvt);
        sortie.setMvtSortie(mvt);
        sorties.add(sortie);
        final List<LigneStock> lignesStock = new ArrayList<LigneStock>();
        final LigneStock ligneStock = new LigneStock(this.essai, this.pharmacie, this.produit, this.conditionnement, Boolean.TRUE);
        ligneStock.setQteASortir(2);
        lignesStock.add(ligneStock);
        sortie.setLignesStock(lignesStock);

        final CessionPui cessionPui = new CessionPui();
        Mockito.when(this.mockCessionPuiFactory.getInitializedObject()).thenReturn(cessionPui);

        Mockito.when(this.evenementFactory.getInitializedObject()).thenReturn(new Evenement());

        final Pharmacie pharmacieDest = Mockito.mock(Pharmacie.class);
        Mockito.when(pharmacieDest.getEtablissement()).thenReturn(new Etablissement());

        final ResultSortie result = this.sortieService.save(RaisonSortie.FIN_ETUDE, null, TypeMvtStock.CESSION_PUI, null, null, commentaire, pharmacieDest, sorties, null);

        Mockito.verify(this.mockCessionPuiService, Mockito.times(1)).saveAll(Matchers.anyList());

        Assert.assertNotNull(result);
    }

    /**
     * Méthode en charge de tester la sauvegarde de sortie de type Destruction.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testSaveDestruction() {
        final String commentaire = "commentaire";

        final List<Sortie> sorties = new ArrayList<Sortie>();
        final Sortie sortie = new Sortie();
        final MvtStock mvt = new Destruction();
        this.completeMvt(mvt);
        sortie.setMvtSortie(mvt);
        sorties.add(sortie);
        final List<LigneStock> lignesStock = new ArrayList<LigneStock>();
        final LigneStock ligneStock = new LigneStock(this.essai, this.pharmacie, this.produit, this.conditionnement, Boolean.TRUE);
        ligneStock.setQteASortir(2);
        lignesStock.add(ligneStock);
        sortie.setLignesStock(lignesStock);

        final Destruction destruction = new Destruction();
        Mockito.when(this.mockDestructionFactory.getInitializedObject()).thenReturn(destruction);

        final ResultSortie result = this.sortieService.save(RaisonSortie.FIN_ETUDE, null, TypeMvtStock.DESTRUCTION, null, null, commentaire, null, sorties, null);

        Mockito.verify(this.mockDestructionService, Mockito.times(1)).saveAll(Matchers.anyList());

        Assert.assertNotNull(result);
    }

    /**
     * Méthode en charge de tester la sauvegarde de sortie de type AutreSortie.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testSaveAutreSortie() {
        final String commentaire = "commentaire";

        final List<Sortie> sorties = new ArrayList<Sortie>();
        final Sortie sortie = new Sortie();
        final MvtStock mvt = new AutreSortie();
        this.completeMvt(mvt);
        sortie.setMvtSortie(mvt);
        sorties.add(sortie);
        final List<LigneStock> lignesStock = new ArrayList<LigneStock>();
        final LigneStock ligneStock = new LigneStock(this.essai, this.pharmacie, this.produit, this.conditionnement, Boolean.TRUE);
        ligneStock.setQteASortir(2);
        lignesStock.add(ligneStock);
        sortie.setLignesStock(lignesStock);

        final AutreSortie autreSortie = new AutreSortie();
        Mockito.when(this.mockAutreSortieFactory.getInitializedObject()).thenReturn(autreSortie);

        final ResultSortie result = this.sortieService.save(RaisonSortie.FIN_ETUDE, null, TypeMvtStock.AUTRE_SORTIE, null, null, commentaire, null, sorties, null);

        Mockito.verify(this.mockAutreSortieService, Mockito.times(1)).saveAll(Matchers.anyList());

        Assert.assertNotNull(result);
    }

    /**
     * Méthode en charge de tester la sauvegarde de sortie de type Dispensation.
     */
    @Test
    public void testSaveDispensation() {
        final String commentaire = "commentaire";

        final List<Sortie> sorties = new ArrayList<Sortie>();
        final Sortie sortie = new Sortie();
        final MvtStock mvt = new DispensationProduit();
        this.completeMvt(mvt);
        sortie.setMvtSortie(mvt);
        sorties.add(sortie);
        final List<LigneStock> lignesStock = new ArrayList<LigneStock>();
        final LigneStock ligneStock = new LigneStock(this.essai, this.pharmacie, this.produit, this.conditionnement, Boolean.TRUE);
        ligneStock.setQteASortir(2);
        lignesStock.add(ligneStock);
        sortie.setLignesStock(lignesStock);

        final Dispensation dispensation = new Dispensation();
        dispensation.setPrescription(new Prescription());

        final DispensationProduit disp = new DispensationProduit();
        Mockito.when(this.mockDispensationProduitFactory.getInitializedObject()).thenReturn(disp);

        final ResultSortie result = this.sortieService.save(RaisonSortie.FIN_ETUDE, null, TypeMvtStock.DISPENSATION, null, null, commentaire, null, sorties, dispensation);

        Assert.assertNotNull(result);
    }

    /**
     * Méthode en charge de compléter un mouvement de sortie.
     * @param mvt Mouvement à compléter.
     */
    private void completeMvt(final MvtStock mvt) {
        mvt.setEssai(this.essai);
        mvt.setPharmacie(this.pharmacie);
        mvt.setProduit(this.produit);
        mvt.setConditionnement(this.conditionnement);
    }

}
