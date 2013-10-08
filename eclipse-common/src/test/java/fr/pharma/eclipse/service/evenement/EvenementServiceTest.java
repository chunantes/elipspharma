package fr.pharma.eclipse.service.evenement;

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
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier Evenement.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class EvenementServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<Evenement> evenementService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<Evenement> mockedDao;

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
        this.evenementService = new GenericServiceImpl<Evenement>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.evenementService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.evenementService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un evenement pour le test.
     * @return Editeur initialisé.
     */
    private Evenement makeEvenementTest() {
        final Evenement evenement = new Evenement();
        evenement.setId(1L);
        return evenement;
    }

    /**
     * Crée une liste de evenements pour le test.
     * @return La liste de Evenement initialisés.
     */
    private List<Evenement> makeListEvenementTest() {
        final List<Evenement> evenements = new ArrayList<Evenement>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            evenements.add(this.makeEvenementTest());
        }
        return evenements;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final Evenement evenementExpected = this.makeEvenementTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(evenementExpected);

        final Evenement evenement = this.evenementService.get(idTest);

        Assert.assertNotNull(evenement);
        Assert.assertEquals(evenementExpected, evenement);
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
            this.evenementService.get(idTest);
            Assert.fail(EvenementServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final Evenement evenementToSave = this.makeEvenementTest();
        Mockito.when(this.mockedDao.save(evenementToSave)).thenReturn(evenementToSave);
        Evenement evenementSaved = null;
        evenementSaved = this.evenementService.save(evenementToSave);
        Assert.assertEquals(evenementSaved, evenementToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final Evenement evenementToSave = this.makeEvenementTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(evenementToSave)).thenThrow(exception);
        try {
            this.evenementService.save(evenementToSave);
            Assert.fail(EvenementServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les Evenement avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        criteria.setTypeMouvement(TypeMvtStock.APPROVISIONNEMENT);
        final List<Evenement> evenementsExp = this.makeListEvenementTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(evenementsExp);

        final List<Evenement> evenementsRetrieved = this.evenementService.getAll(criteria);

        Assert.assertEquals(evenementsExp.size(), evenementsRetrieved.size());
        for (final Evenement evenement : evenementsExp) {
            Assert.assertTrue(evenementsRetrieved.contains(evenement));
        }
    }

    /**
     * Test de l'appel de liste de tous les Evenement avec un critère de
     * recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.evenementService.getAll(criteria);
            Assert.fail(EvenementServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final Evenement bean = Mockito.mock(Evenement.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.evenementService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de evenements.
     */
    @Test
    public void testRemoveListe() {
        final List<Evenement> beans = new ArrayList<Evenement>();
        final Evenement bean1 = Mockito.mock(Evenement.class);
        final Evenement bean2 = Mockito.mock(Evenement.class);
        beans.add(bean1);
        beans.add(bean2);
        this.evenementService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((Evenement) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((Evenement) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final Evenement bean = Mockito.mock(Evenement.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.evenementService.remove(bean);
            Assert.fail(EvenementServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
