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
import fr.pharma.eclipse.domain.model.stock.RetourPromoteur;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier RetourPromoteur.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class RetourPromoteurServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<RetourPromoteur> retourPromoteurService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<RetourPromoteur> mockedDao;

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
        this.retourPromoteurService = new GenericServiceImpl<RetourPromoteur>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.retourPromoteurService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.retourPromoteurService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un retourPromoteur pour le test.
     * @return Editeur initialisé.
     */
    private RetourPromoteur makeRetourPromoteurTest() {
        final RetourPromoteur retourPromoteur = new RetourPromoteur();
        retourPromoteur.setId(1L);
        return retourPromoteur;
    }

    /**
     * Crée une liste de retourPromoteurs pour le test.
     * @return La liste de RetourPromoteur initialisés.
     */
    private List<RetourPromoteur> makeListRetourPromoteurTest() {
        final List<RetourPromoteur> retourPromoteurs = new ArrayList<RetourPromoteur>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            retourPromoteurs.add(this.makeRetourPromoteurTest());
        }
        return retourPromoteurs;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final RetourPromoteur retourPromoteurExpected = this.makeRetourPromoteurTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(retourPromoteurExpected);

        final RetourPromoteur retourPromoteur = this.retourPromoteurService.get(idTest);

        Assert.assertNotNull(retourPromoteur);
        Assert.assertEquals(retourPromoteurExpected, retourPromoteur);
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
            this.retourPromoteurService.get(idTest);
            Assert.fail(RetourPromoteurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final RetourPromoteur retourPromoteurToSave = this.makeRetourPromoteurTest();
        Mockito.when(this.mockedDao.save(retourPromoteurToSave)).thenReturn(retourPromoteurToSave);
        RetourPromoteur retourPromoteurSaved = null;
        retourPromoteurSaved = this.retourPromoteurService.save(retourPromoteurToSave);
        Assert.assertEquals(retourPromoteurSaved, retourPromoteurToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final RetourPromoteur retourPromoteurToSave = this.makeRetourPromoteurTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(retourPromoteurToSave)).thenThrow(exception);
        try {
            this.retourPromoteurService.save(retourPromoteurToSave);
            Assert.fail(RetourPromoteurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les RetourPromoteur avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        criteria.setTypeMouvement(TypeMvtStock.APPROVISIONNEMENT);
        final List<RetourPromoteur> retourPromoteursExp = this.makeListRetourPromoteurTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(retourPromoteursExp);

        final List<RetourPromoteur> retourPromoteursRetrieved = this.retourPromoteurService.getAll(criteria);

        Assert.assertEquals(retourPromoteursExp.size(), retourPromoteursRetrieved.size());
        for (final RetourPromoteur retourPromoteur : retourPromoteursExp) {
            Assert.assertTrue(retourPromoteursRetrieved.contains(retourPromoteur));
        }
    }

    /**
     * Test de l'appel de liste de tous les RetourPromoteur avec un critère de
     * recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.retourPromoteurService.getAll(criteria);
            Assert.fail(RetourPromoteurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final RetourPromoteur bean = Mockito.mock(RetourPromoteur.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.retourPromoteurService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de retourPromoteurs.
     */
    @Test
    public void testRemoveListe() {
        final List<RetourPromoteur> beans = new ArrayList<RetourPromoteur>();
        final RetourPromoteur bean1 = Mockito.mock(RetourPromoteur.class);
        final RetourPromoteur bean2 = Mockito.mock(RetourPromoteur.class);
        beans.add(bean1);
        beans.add(bean2);
        this.retourPromoteurService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((RetourPromoteur) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((RetourPromoteur) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final RetourPromoteur bean = Mockito.mock(RetourPromoteur.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.retourPromoteurService.remove(bean);
            Assert.fail(RetourPromoteurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
