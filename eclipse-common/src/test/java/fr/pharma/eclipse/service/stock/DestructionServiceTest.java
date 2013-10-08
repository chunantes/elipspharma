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
import fr.pharma.eclipse.domain.model.stock.Destruction;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier Destruction.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class DestructionServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<Destruction> destructionService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<Destruction> mockedDao;

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
        this.destructionService = new GenericServiceImpl<Destruction>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.destructionService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.destructionService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un destruction pour le test.
     * @return Editeur initialisé.
     */
    private Destruction makeDestructionTest() {
        final Destruction destruction = new Destruction();
        destruction.setId(1L);
        return destruction;
    }

    /**
     * Crée une liste de destructions pour le test.
     * @return La liste de Destruction initialisés.
     */
    private List<Destruction> makeListDestructionTest() {
        final List<Destruction> destructions = new ArrayList<Destruction>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            destructions.add(this.makeDestructionTest());
        }
        return destructions;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final Destruction destructionExpected = this.makeDestructionTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(destructionExpected);

        final Destruction destruction = this.destructionService.get(idTest);

        Assert.assertNotNull(destruction);
        Assert.assertEquals(destructionExpected, destruction);
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
            this.destructionService.get(idTest);
            Assert.fail(DestructionServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final Destruction destructionToSave = this.makeDestructionTest();
        Mockito.when(this.mockedDao.save(destructionToSave)).thenReturn(destructionToSave);
        Destruction destructionSaved = null;
        destructionSaved = this.destructionService.save(destructionToSave);
        Assert.assertEquals(destructionSaved, destructionToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final Destruction destructionToSave = this.makeDestructionTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(destructionToSave)).thenThrow(exception);
        try {
            this.destructionService.save(destructionToSave);
            Assert.fail(DestructionServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les Destruction avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        criteria.setTypeMouvement(TypeMvtStock.APPROVISIONNEMENT);
        final List<Destruction> destructionsExp = this.makeListDestructionTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(destructionsExp);

        final List<Destruction> destructionsRetrieved = this.destructionService.getAll(criteria);

        Assert.assertEquals(destructionsExp.size(), destructionsRetrieved.size());
        for (final Destruction destruction : destructionsExp) {
            Assert.assertTrue(destructionsRetrieved.contains(destruction));
        }
    }

    /**
     * Test de l'appel de liste de tous les Destruction avec un critère de
     * recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.destructionService.getAll(criteria);
            Assert.fail(DestructionServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final Destruction bean = Mockito.mock(Destruction.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.destructionService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de destructions.
     */
    @Test
    public void testRemoveListe() {
        final List<Destruction> beans = new ArrayList<Destruction>();
        final Destruction bean1 = Mockito.mock(Destruction.class);
        final Destruction bean2 = Mockito.mock(Destruction.class);
        beans.add(bean1);
        beans.add(bean2);
        this.destructionService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((Destruction) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((Destruction) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final Destruction bean = Mockito.mock(Destruction.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.destructionService.remove(bean);
            Assert.fail(DestructionServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
