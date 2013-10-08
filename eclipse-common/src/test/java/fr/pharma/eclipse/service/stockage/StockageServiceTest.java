package fr.pharma.eclipse.service.stockage;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.stockage.StockageSearchCriteria;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier Stockage.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class StockageServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<Stockage> stockageService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<Stockage> mockedDao;

    /**
     * String indiquant un point de passage normalement non atteint.
     */
    private static final String NOT_TO_BE_REACHED = "Passage à un point normalement non atteint";

    /**
     * Méthode d'initialisation.
     * @throws Exception Exception.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception {
        this.mockedDao = Mockito.mock(GenericDao.class);
        this.stockageService = new GenericServiceImpl<Stockage>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.stockageService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.stockageService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un stockage pour le test.
     * @return Editeur initialisé.
     */
    private Stockage makeStockageTest() {
        final Stockage stockage = new Stockage();
        stockage.setId(1L);
        stockage.setNom("nom");
        return stockage;
    }

    /**
     * Crée une liste de stockages pour le test.
     * @return La liste de Stockage initialisés.
     */
    private List<Stockage> makeListStockageTest() {
        final List<Stockage> stockages = new ArrayList<Stockage>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            stockages.add(this.makeStockageTest());
        }
        return stockages;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final Stockage stockageExpected = this.makeStockageTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(stockageExpected);

        final Stockage stockage = this.stockageService.get(idTest);

        Assert.assertNotNull(stockage);
        Assert.assertEquals(stockageExpected, stockage);
    }

    /**
     * Test de la méthode get(int) KO.
     */
    @Test
    public void testGetIntKo() {
        final Long idTest = 1L;
        final MockitoException exception = new MockitoException("testGetIntKo");
        Mockito.when(this.mockedDao.get(idTest)).thenThrow(exception);
        try {
            this.stockageService.get(idTest);
            Assert.fail(StockageServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final Stockage stockageToSave = this.makeStockageTest();
        Mockito.when(this.mockedDao.save(stockageToSave)).thenReturn(stockageToSave);
        Stockage stockageSaved = null;
        stockageSaved = this.stockageService.save(stockageToSave);
        Assert.assertEquals(stockageSaved, stockageToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final Stockage stockageToSave = this.makeStockageTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(stockageToSave)).thenThrow(exception);
        try {
            this.stockageService.save(stockageToSave);
            Assert.fail(StockageServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les Stockage avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final StockageSearchCriteria criteria = new StockageSearchCriteria();
        criteria.setNom("NOM");
        final List<Stockage> stockagesExp = this.makeListStockageTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(stockagesExp);

        final List<Stockage> stockagesRetrieved = this.stockageService.getAll(criteria);

        Assert.assertEquals(stockagesExp.size(), stockagesRetrieved.size());
        for (final Stockage stockage : stockagesExp) {
            Assert.assertTrue(stockagesRetrieved.contains(stockage));
        }
    }

    /**
     * Test de l'appel de liste de tous les Stockage avec un critère de
     * recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final StockageSearchCriteria criteria = new StockageSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.stockageService.getAll(criteria);
            Assert.fail(StockageServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final Stockage bean = Mockito.mock(Stockage.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.stockageService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de stockages.
     */
    @Test
    public void testRemoveListe() {
        final List<Stockage> beans = new ArrayList<Stockage>();
        final Stockage bean1 = Mockito.mock(Stockage.class);
        final Stockage bean2 = Mockito.mock(Stockage.class);
        beans.add(bean1);
        beans.add(bean2);
        this.stockageService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((Stockage) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((Stockage) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final Stockage bean = Mockito.mock(Stockage.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.stockageService.remove(bean);
            Assert.fail(StockageServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
