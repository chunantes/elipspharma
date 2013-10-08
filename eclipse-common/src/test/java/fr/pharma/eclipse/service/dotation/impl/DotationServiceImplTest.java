package fr.pharma.eclipse.service.dotation.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.dotation.Dotation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.DispensationGlobale;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.factory.stock.MvtStockFactory;
import fr.pharma.eclipse.service.stock.MvtStockService;
import fr.pharma.eclipse.service.stock.StockService;
import fr.pharma.eclipse.service.user.UserService;
import fr.pharma.eclipse.validator.stock.StockValidator;

/**
 * Classe en charge de tester le service de gestion des dotations.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DotationServiceImplTest {
    /**
     * DotationServiceImpl à tester.
     */
    private DotationServiceImpl service;

    /**
     * Dao de gestion des dotations.
     */
    private GenericDao<Dotation> mockDao;

    /**
     * Mock du service de gestion des utilisateurs.
     */
    private UserService mockUserService;

    /**
     * Mock du validator des lignes de stock.
     */
    private StockValidator mockStockValidator;

    /**
     * Mock du service des dispensations globales.
     */
    private MvtStockService<DispensationGlobale> mockDispGlobService;

    /**
     * Mock de la factory des dispensations globales.
     */
    private MvtStockFactory<DispensationGlobale> mockDispGlobFactory;

    private StockService mockStockService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.mockDao = Mockito.mock(GenericDao.class);
        this.service = new DotationServiceImpl(this.mockDao);
        this.mockUserService = Mockito.mock(UserService.class);
        this.service.setUserService(this.mockUserService);
        this.mockStockValidator = Mockito.mock(StockValidator.class);
        this.service.setStockValidator(this.mockStockValidator);
        this.mockDispGlobService = Mockito.mock(MvtStockService.class);
        this.service.setDispGlobService(this.mockDispGlobService);
        this.mockDispGlobFactory = Mockito.mock(MvtStockFactory.class);
        this.service.setDispGlobFactory(this.mockDispGlobFactory);
        this.mockStockService = Mockito.mock(StockService.class);
        this.service.setStockService(this.mockStockService);
    }

    /**
     * Méthode en charge de tester la sauvegarde d'une dotation.
     */
    @Test
    public void testSaveDotation() {
        final Dotation dotation = Mockito.mock(Dotation.class);
        Mockito.when(this.mockDao.save(dotation)).thenReturn(dotation);
        final Dotation result = this.service.save(dotation);
        Assert.assertNotNull(result);
        Mockito.verify(this.mockUserService).getPersonne();
    }

    /**
     * Méthode en charge de tester la sauvegarde de traitement d'une demande de
     * dotation.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testSaveTraitementDotation() {
        final Dotation dotation = Mockito.mock(Dotation.class);
        final List<LigneStock> lignesStock = new ArrayList<LigneStock>();

        final Essai essai = new Essai();
        final Pharmacie pharmacie = new Pharmacie();
        final Produit produit = new Medicament();
        final Conditionnement conditionnement = new Conditionnement();

        final LigneStock ligneStock = new LigneStock(essai, pharmacie, produit, conditionnement, Boolean.TRUE);
        ligneStock.setQteASortir(1);
        lignesStock.add(ligneStock);

        final DispensationGlobale mvt = new DispensationGlobale();
        Mockito.when(this.mockDispGlobFactory.getInitializedObject()).thenReturn(mvt);

        this.service.saveTraitementDotation(dotation, lignesStock);

        Mockito.verify(this.mockStockValidator).validateQteLignesStock(Matchers.anyList());

        Mockito.verify(dotation).setTraitee(Boolean.TRUE);
        Mockito.verify(this.mockDao).save(dotation);
    }

}
