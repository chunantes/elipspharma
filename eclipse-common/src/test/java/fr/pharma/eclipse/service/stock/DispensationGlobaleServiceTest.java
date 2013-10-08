package fr.pharma.eclipse.service.stock;

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
import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.stock.DispensationGlobale;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier DispensationGlobale.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class DispensationGlobaleServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<DispensationGlobale> dispensationGlobaleService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<DispensationGlobale> mockedDao;

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
        this.dispensationGlobaleService = new GenericServiceImpl<DispensationGlobale>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.dispensationGlobaleService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.dispensationGlobaleService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un dispensationGlobale pour le test.
     * @return Editeur initialisé.
     */
    private DispensationGlobale makeDispensationGlobaleTest() {
        final DispensationGlobale dispensationGlobale = new DispensationGlobale();
        dispensationGlobale.setId(1L);
        return dispensationGlobale;
    }

    /**
     * Crée une liste de dispensationGlobales pour le test.
     * @return La liste de DispensationGlobale initialisés.
     */
    private List<DispensationGlobale> makeListDispensationGlobaleTest() {
        final List<DispensationGlobale> dispensationGlobales = new ArrayList<DispensationGlobale>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            dispensationGlobales.add(this.makeDispensationGlobaleTest());
        }
        return dispensationGlobales;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final DispensationGlobale dispensationGlobaleExpected = this.makeDispensationGlobaleTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(dispensationGlobaleExpected);

        final DispensationGlobale dispensationGlobale = this.dispensationGlobaleService.get(idTest);

        Assert.assertNotNull(dispensationGlobale);
        Assert.assertEquals(dispensationGlobaleExpected, dispensationGlobale);
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
            this.dispensationGlobaleService.get(idTest);
            Assert.fail(DispensationGlobaleServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final DispensationGlobale dispensationGlobaleToSave = this.makeDispensationGlobaleTest();
        Mockito.when(this.mockedDao.save(dispensationGlobaleToSave)).thenReturn(dispensationGlobaleToSave);
        DispensationGlobale dispensationGlobaleSaved = null;
        dispensationGlobaleSaved = this.dispensationGlobaleService.save(dispensationGlobaleToSave);
        Assert.assertEquals(dispensationGlobaleSaved, dispensationGlobaleToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final DispensationGlobale dispensationGlobaleToSave = this.makeDispensationGlobaleTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(dispensationGlobaleToSave)).thenThrow(exception);
        try {
            this.dispensationGlobaleService.save(dispensationGlobaleToSave);
            Assert.fail(DispensationGlobaleServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les DispensationGlobale avec un critère
     * de recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        criteria.setTypeMouvement(TypeMvtStock.APPROVISIONNEMENT);
        final List<DispensationGlobale> dispensationGlobalesExp = this.makeListDispensationGlobaleTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(dispensationGlobalesExp);

        final List<DispensationGlobale> dispensationGlobalesRetrieved = this.dispensationGlobaleService.getAll(criteria);

        Assert.assertEquals(dispensationGlobalesExp.size(), dispensationGlobalesRetrieved.size());
        for (final DispensationGlobale dispensationGlobale : dispensationGlobalesExp) {
            Assert.assertTrue(dispensationGlobalesRetrieved.contains(dispensationGlobale));
        }
    }

    /**
     * Test de l'appel de liste de tous les DispensationGlobale avec un critère
     * de recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.dispensationGlobaleService.getAll(criteria);
            Assert.fail(DispensationGlobaleServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final DispensationGlobale bean = Mockito.mock(DispensationGlobale.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.dispensationGlobaleService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de dispensationGlobales.
     */
    @Test
    public void testRemoveListe() {
        final List<DispensationGlobale> beans = new ArrayList<DispensationGlobale>();
        final DispensationGlobale bean1 = Mockito.mock(DispensationGlobale.class);
        final DispensationGlobale bean2 = Mockito.mock(DispensationGlobale.class);
        beans.add(bean1);
        beans.add(bean2);
        this.dispensationGlobaleService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((DispensationGlobale) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((DispensationGlobale) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final DispensationGlobale bean = Mockito.mock(DispensationGlobale.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.dispensationGlobaleService.remove(bean);
            Assert.fail(DispensationGlobaleServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
