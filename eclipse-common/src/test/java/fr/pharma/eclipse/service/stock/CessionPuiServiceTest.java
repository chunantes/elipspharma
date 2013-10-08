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
import fr.pharma.eclipse.domain.model.stock.CessionPui;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier CessionPui.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class CessionPuiServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<CessionPui> cessionPuiService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<CessionPui> mockedDao;

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
        this.cessionPuiService = new GenericServiceImpl<CessionPui>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.cessionPuiService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.cessionPuiService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un cessionPui pour le test.
     * @return Editeur initialisé.
     */
    private CessionPui makeCessionPuiTest() {
        final CessionPui cessionPui = new CessionPui();
        cessionPui.setId(1L);
        return cessionPui;
    }

    /**
     * Crée une liste de cessionPuis pour le test.
     * @return La liste de CessionPui initialisés.
     */
    private List<CessionPui> makeListCessionPuiTest() {
        final List<CessionPui> cessionPuis = new ArrayList<CessionPui>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            cessionPuis.add(this.makeCessionPuiTest());
        }
        return cessionPuis;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final CessionPui cessionPuiExpected = this.makeCessionPuiTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(cessionPuiExpected);

        final CessionPui cessionPui = this.cessionPuiService.get(idTest);

        Assert.assertNotNull(cessionPui);
        Assert.assertEquals(cessionPuiExpected, cessionPui);
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
            this.cessionPuiService.get(idTest);
            Assert.fail(CessionPuiServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final CessionPui cessionPuiToSave = this.makeCessionPuiTest();
        Mockito.when(this.mockedDao.save(cessionPuiToSave)).thenReturn(cessionPuiToSave);
        CessionPui cessionPuiSaved = null;
        cessionPuiSaved = this.cessionPuiService.save(cessionPuiToSave);
        Assert.assertEquals(cessionPuiSaved, cessionPuiToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final CessionPui cessionPuiToSave = this.makeCessionPuiTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(cessionPuiToSave)).thenThrow(exception);
        try {
            this.cessionPuiService.save(cessionPuiToSave);
            Assert.fail(CessionPuiServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les CessionPui avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        criteria.setTypeMouvement(TypeMvtStock.APPROVISIONNEMENT);
        final List<CessionPui> cessionPuisExp = this.makeListCessionPuiTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(cessionPuisExp);

        final List<CessionPui> cessionPuisRetrieved = this.cessionPuiService.getAll(criteria);

        Assert.assertEquals(cessionPuisExp.size(), cessionPuisRetrieved.size());
        for (final CessionPui cessionPui : cessionPuisExp) {
            Assert.assertTrue(cessionPuisRetrieved.contains(cessionPui));
        }
    }

    /**
     * Test de l'appel de liste de tous les CessionPui avec un critère de
     * recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.cessionPuiService.getAll(criteria);
            Assert.fail(CessionPuiServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final CessionPui bean = Mockito.mock(CessionPui.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.cessionPuiService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de cessionPuis.
     */
    @Test
    public void testRemoveListe() {
        final List<CessionPui> beans = new ArrayList<CessionPui>();
        final CessionPui bean1 = Mockito.mock(CessionPui.class);
        final CessionPui bean2 = Mockito.mock(CessionPui.class);
        beans.add(bean1);
        beans.add(bean2);
        this.cessionPuiService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((CessionPui) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((CessionPui) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final CessionPui bean = Mockito.mock(CessionPui.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.cessionPuiService.remove(bean);
            Assert.fail(CessionPuiServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
