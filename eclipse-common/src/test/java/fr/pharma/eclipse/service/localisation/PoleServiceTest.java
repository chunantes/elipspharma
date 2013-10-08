package fr.pharma.eclipse.service.localisation;

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
import fr.pharma.eclipse.domain.criteria.localisation.PoleSearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Pole;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier Pole.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class PoleServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<Pole> poleService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<Pole> mockedDao;

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
        this.poleService = new GenericServiceImpl<Pole>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.poleService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.poleService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un pole pour le test.
     * @return Editeur initialisé.
     */
    private Pole makePoleTest() {
        final Pole pole = new Pole();
        pole.setId(1L);
        return pole;
    }

    /**
     * Crée une liste de poles pour le test.
     * @return La liste de Pole initialisés.
     */
    private List<Pole> makeListPoleTest() {
        final List<Pole> poles = new ArrayList<Pole>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            poles.add(this.makePoleTest());
        }
        return poles;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final Pole poleExpected = this.makePoleTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(poleExpected);

        final Pole pole = this.poleService.get(idTest);

        Assert.assertNotNull(pole);
        Assert.assertEquals(poleExpected, pole);
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
            this.poleService.get(idTest);
            Assert.fail(PoleServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final Pole poleToSave = this.makePoleTest();
        Mockito.when(this.mockedDao.save(poleToSave)).thenReturn(poleToSave);
        Pole poleSaved = null;
        poleSaved = this.poleService.save(poleToSave);
        Assert.assertEquals(poleSaved, poleToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final Pole poleToSave = this.makePoleTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(poleToSave)).thenThrow(exception);
        try {
            this.poleService.save(poleToSave);
            Assert.fail(PoleServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les Pole avec un critère de recherche
     * OK.
     */
    @Test
    public void testGetAllOK() {
        final PoleSearchCriteria criteria = new PoleSearchCriteria();
        criteria.setNom("NOM");
        final List<Pole> polesExp = this.makeListPoleTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(polesExp);

        final List<Pole> polesRetrieved = this.poleService.getAll(criteria);

        Assert.assertEquals(polesExp.size(), polesRetrieved.size());
        for (final Pole pole : polesExp) {
            Assert.assertTrue(polesRetrieved.contains(pole));
        }
    }

    /**
     * Test de l'appel de liste de tous les Pole avec un critère de recherche
     * KO.
     */
    @Test
    public void testGetAllKo() {
        final PoleSearchCriteria criteria = new PoleSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.poleService.getAll(criteria);
            Assert.fail(PoleServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final Pole bean = Mockito.mock(Pole.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.poleService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de poles.
     */
    @Test
    public void testRemoveListe() {
        final List<Pole> beans = new ArrayList<Pole>();
        final Pole bean1 = Mockito.mock(Pole.class);
        final Pole bean2 = Mockito.mock(Pole.class);
        beans.add(bean1);
        beans.add(bean2);
        this.poleService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((Pole) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((Pole) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final Pole bean = Mockito.mock(Pole.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.poleService.remove(bean);
            Assert.fail(PoleServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
