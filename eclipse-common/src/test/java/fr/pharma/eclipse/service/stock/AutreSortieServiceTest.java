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
import fr.pharma.eclipse.domain.model.stock.AutreSortie;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier AutreSortie.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class AutreSortieServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<AutreSortie> autreSortieService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<AutreSortie> mockedDao;

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
        this.autreSortieService = new GenericServiceImpl<AutreSortie>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.autreSortieService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.autreSortieService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un autreSortie pour le test.
     * @return Editeur initialisé.
     */
    private AutreSortie makeAutreSortieTest() {
        final AutreSortie autreSortie = new AutreSortie();
        autreSortie.setId(1L);
        return autreSortie;
    }

    /**
     * Crée une liste de autreSorties pour le test.
     * @return La liste de AutreSortie initialisés.
     */
    private List<AutreSortie> makeListAutreSortieTest() {
        final List<AutreSortie> autreSorties = new ArrayList<AutreSortie>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            autreSorties.add(this.makeAutreSortieTest());
        }
        return autreSorties;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final AutreSortie autreSortieExpected = this.makeAutreSortieTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(autreSortieExpected);

        final AutreSortie autreSortie = this.autreSortieService.get(idTest);

        Assert.assertNotNull(autreSortie);
        Assert.assertEquals(autreSortieExpected, autreSortie);
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
            this.autreSortieService.get(idTest);
            Assert.fail(AutreSortieServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final AutreSortie autreSortieToSave = this.makeAutreSortieTest();
        Mockito.when(this.mockedDao.save(autreSortieToSave)).thenReturn(autreSortieToSave);
        AutreSortie autreSortieSaved = null;
        autreSortieSaved = this.autreSortieService.save(autreSortieToSave);
        Assert.assertEquals(autreSortieSaved, autreSortieToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final AutreSortie autreSortieToSave = this.makeAutreSortieTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(autreSortieToSave)).thenThrow(exception);
        try {
            this.autreSortieService.save(autreSortieToSave);
            Assert.fail(AutreSortieServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les AutreSortie avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        criteria.setTypeMouvement(TypeMvtStock.APPROVISIONNEMENT);
        final List<AutreSortie> autreSortiesExp = this.makeListAutreSortieTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(autreSortiesExp);

        final List<AutreSortie> autreSortiesRetrieved = this.autreSortieService.getAll(criteria);

        Assert.assertEquals(autreSortiesExp.size(), autreSortiesRetrieved.size());
        for (final AutreSortie autreSortie : autreSortiesExp) {
            Assert.assertTrue(autreSortiesRetrieved.contains(autreSortie));
        }
    }

    /**
     * Test de l'appel de liste de tous les AutreSortie avec un critère de
     * recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.autreSortieService.getAll(criteria);
            Assert.fail(AutreSortieServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final AutreSortie bean = Mockito.mock(AutreSortie.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.autreSortieService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de autreSorties.
     */
    @Test
    public void testRemoveListe() {
        final List<AutreSortie> beans = new ArrayList<AutreSortie>();
        final AutreSortie bean1 = Mockito.mock(AutreSortie.class);
        final AutreSortie bean2 = Mockito.mock(AutreSortie.class);
        beans.add(bean1);
        beans.add(bean2);
        this.autreSortieService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((AutreSortie) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((AutreSortie) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final AutreSortie bean = Mockito.mock(AutreSortie.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.autreSortieService.remove(bean);
            Assert.fail(AutreSortieServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
