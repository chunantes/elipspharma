package fr.pharma.eclipse.service.stockage.impl;

import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.domain.model.suivi.stockage.PharmacieSuivi;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.validator.remove.impl.StockageRemoveValidator;
import fr.pharma.eclipse.validator.save.impl.PharmacieSaveValidator;

/**
 * Classe en charge de tester le service de gestion des pharmacies.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PharmacieServiceImplTest {

    /**
     * Service de gestion de pharmacies à tester.
     */
    private PharmacieServiceImpl service;

    /**
     * Dao mocké.
     */
    private GenericDao<Pharmacie> mockDao;

    /**
     * Suivi Factory mocké.
     */
    private SuiviFactory<PharmacieSuivi> mockSuiviFactory;

    /**
     * Validator de sauvegarde mocké.
     */
    private PharmacieSaveValidator mockSaveValidator;

    /**
     * Validateur de suppression d'un stockage.
     */
    private StockageRemoveValidator removeValidator;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.mockDao = Mockito.mock(GenericDao.class);
        this.removeValidator = Mockito.mock(StockageRemoveValidator.class);
        this.service = new PharmacieServiceImpl(this.mockDao);
        this.mockSuiviFactory = Mockito.mock(SuiviFactory.class);
        this.service.setPharmacieSuiviFactory(this.mockSuiviFactory);
        this.service.setStockageRemoveValidator(this.removeValidator);
        this.mockSaveValidator = Mockito.mock(PharmacieSaveValidator.class);
        this.service.setPharmacieSaveValidator(this.mockSaveValidator);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.mockDao = null;
        this.service = null;
        this.mockSuiviFactory = null;
        this.removeValidator = null;
        this.mockSaveValidator = null;
    }
    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.mockDao);
        Assert.assertNotNull(this.mockSuiviFactory);
        Assert.assertNotNull(this.mockSaveValidator);
    }

    /**
     * Méthode en charge de tester la sauvegarde des pharmacies.
     */
    @Test
    public void testSavePharmacie() {
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        final PharmacieSuivi suivi = new PharmacieSuivi();
        Mockito.when(this.mockDao.reattach(pharmacie)).thenReturn(pharmacie);
        Mockito.when(this.mockSuiviFactory.getInitializedObject()).thenReturn(suivi);
        Mockito.when(pharmacie.getModifs()).thenReturn(new TreeSet<PharmacieSuivi>());
        Mockito.when(this.mockDao.save(pharmacie)).thenReturn(pharmacie);
        final Pharmacie result = this.service.save(pharmacie);
        Mockito.verify(this.mockSaveValidator).validate(pharmacie, this.service);
        Mockito.verify(this.mockDao).reattach(pharmacie);
        Mockito.verify(this.mockSuiviFactory).getInitializedObject();
        Assert.assertEquals(1, result.getModifs().size());
    }

    /**
     * Méthode en charge de tester la correspondance entre 2 beans de stockage.
     */
    @Test
    public void testIsMatchingIdTrue() {
        final Stockage stockage1 = new Stockage();
        stockage1.setId(1L);
        final Stockage stockage2 = new Stockage();
        stockage2.setId(1L);
        Assert.assertTrue(this.service.isMatching(stockage1, stockage2));
    }

    /**
     * Méthode en charge de tester la correspondance entre 2 beans de stockage.
     */
    @Test
    public void testIsMatchingIdFalse() {
        final Stockage stockage1 = new Stockage();
        stockage1.setId(1L);
        final Stockage stockage2 = new Stockage();
        stockage2.setId(2L);
        Assert.assertFalse(this.service.isMatching(stockage1, stockage2));
    }

    /**
     * Méthode en charge de tester la correspondance entre 2 beans de stockage.
     */
    @Test
    public void testIsMatchingRefTrue() {
        final Stockage stockage1 = new Stockage();
        final Stockage stockage2 = stockage1;
        Assert.assertTrue(this.service.isMatching(stockage1, stockage2));
    }

    /**
     * Méthode en charge de tester la correspondance entre 2 beans de stockage.
     */
    @Test
    public void testIsMatchingRefFalse() {
        final Stockage stockage1 = new Stockage();
        final Stockage stockage2 = new Stockage();
        Assert.assertFalse(this.service.isMatching(stockage1, stockage2));
    }

    /**
     * Méthode en charge de tester la suppression de stockage pour une
     * pharmacie.
     */
    @Test
    public void testRemoveStockageSansParent() {
        final Pharmacie pharmacie = new Pharmacie();
        final Stockage stockage = new Stockage();
        pharmacie.getStockages().add(stockage);
        this.service.removeStockage(pharmacie, stockage);
        Assert.assertTrue(pharmacie.getStockages().isEmpty());
    }

    /**
     * Méthode en charge de tester la suppression de stockage pour une
     * pharmacie.
     */
    @Test
    public void testRemoveStockageParent() {
        final Pharmacie pharmacie = new Pharmacie();

        final Stockage parent = new Stockage();
        parent.setNom("parent");
        final Stockage enfant = new Stockage();
        enfant.setNom("enfant");
        enfant.setParent(parent);
        parent.getEnfants().add(enfant);

        pharmacie.getStockages().add(parent);
        pharmacie.getStockages().add(enfant);

        this.service.removeStockage(pharmacie, enfant);

        Assert.assertEquals(1, pharmacie.getStockages().size());
    }

    /**
     * Méthode en charge de tester la suppression de stockage pour une
     * pharmacie.
     */
    @Test
    public void testRemoveStockageParent2() {
        final Pharmacie pharmacie = new Pharmacie();

        final Stockage parent = new Stockage();
        parent.setNom("parent");
        final Stockage enfant = new Stockage();
        enfant.setNom("enfant");
        enfant.setParent(parent);
        parent.getEnfants().add(enfant);

        final Stockage sousEnfant = new Stockage();
        sousEnfant.setNom("nom");
        sousEnfant.setParent(enfant);
        enfant.getEnfants().add(sousEnfant);

        pharmacie.getStockages().add(parent);
        pharmacie.getStockages().add(enfant);
        pharmacie.getStockages().add(sousEnfant);

        this.service.removeStockage(pharmacie, enfant);

        Assert.assertEquals(1, pharmacie.getStockages().size());
    }

}
