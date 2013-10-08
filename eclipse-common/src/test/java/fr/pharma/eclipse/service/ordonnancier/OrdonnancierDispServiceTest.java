package fr.pharma.eclipse.service.ordonnancier;

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
import fr.pharma.eclipse.domain.criteria.ordonnancier.OrdonnancierSearchCriteria;
import fr.pharma.eclipse.domain.model.ordonnancier.OrdonnancierDisp;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier OrdonnancierDisp.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class OrdonnancierDispServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<OrdonnancierDisp> ordonnancierDispService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<OrdonnancierDisp> mockedDao;

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
        this.ordonnancierDispService = new GenericServiceImpl<OrdonnancierDisp>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.ordonnancierDispService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.ordonnancierDispService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un ordonnancierDisp pour le test.
     * @return Editeur initialisé.
     */
    private OrdonnancierDisp makeOrdonnancierDispTest() {
        final OrdonnancierDisp ordonnancierDisp = new OrdonnancierDisp();
        ordonnancierDisp.setId(1L);
        return ordonnancierDisp;
    }

    /**
     * Crée une liste de ordonnancierDisps pour le test.
     * @return La liste de OrdonnancierDisp initialisés.
     */
    private List<OrdonnancierDisp> makeListOrdonnancierDispTest() {
        final List<OrdonnancierDisp> ordonnancierDisps = new ArrayList<OrdonnancierDisp>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            ordonnancierDisps.add(this.makeOrdonnancierDispTest());
        }
        return ordonnancierDisps;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final OrdonnancierDisp ordonnancierDispExpected = this.makeOrdonnancierDispTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(ordonnancierDispExpected);

        final OrdonnancierDisp ordonnancierDisp = this.ordonnancierDispService.get(idTest);

        Assert.assertNotNull(ordonnancierDisp);
        Assert.assertEquals(ordonnancierDispExpected, ordonnancierDisp);
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
            this.ordonnancierDispService.get(idTest);
            Assert.fail(OrdonnancierDispServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final OrdonnancierDisp ordonnancierDispToSave = this.makeOrdonnancierDispTest();
        Mockito.when(this.mockedDao.save(ordonnancierDispToSave)).thenReturn(ordonnancierDispToSave);
        OrdonnancierDisp ordonnancierDispSaved = null;
        ordonnancierDispSaved = this.ordonnancierDispService.save(ordonnancierDispToSave);
        Assert.assertEquals(ordonnancierDispSaved, ordonnancierDispToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final OrdonnancierDisp ordonnancierDispToSave = this.makeOrdonnancierDispTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(ordonnancierDispToSave)).thenThrow(exception);
        try {
            this.ordonnancierDispService.save(ordonnancierDispToSave);
            Assert.fail(OrdonnancierDispServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les OrdonnancierDisp avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final OrdonnancierSearchCriteria criteria = new OrdonnancierSearchCriteria();
        final List<OrdonnancierDisp> ordonnancierDispsExp = this.makeListOrdonnancierDispTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(ordonnancierDispsExp);

        final List<OrdonnancierDisp> ordonnancierDispsRetrieved = this.ordonnancierDispService.getAll(criteria);

        Assert.assertEquals(ordonnancierDispsExp.size(), ordonnancierDispsRetrieved.size());
        for (final OrdonnancierDisp ordonnancierDisp : ordonnancierDispsExp) {
            Assert.assertTrue(ordonnancierDispsRetrieved.contains(ordonnancierDisp));
        }
    }

    /**
     * Test de l'appel de liste de tous les OrdonnancierDisp avec un critère de
     * recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final OrdonnancierSearchCriteria criteria = new OrdonnancierSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.ordonnancierDispService.getAll(criteria);
            Assert.fail(OrdonnancierDispServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final OrdonnancierDisp bean = Mockito.mock(OrdonnancierDisp.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.ordonnancierDispService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de ordonnancierDisps.
     */
    @Test
    public void testRemoveListe() {
        final List<OrdonnancierDisp> beans = new ArrayList<OrdonnancierDisp>();
        final OrdonnancierDisp bean1 = Mockito.mock(OrdonnancierDisp.class);
        final OrdonnancierDisp bean2 = Mockito.mock(OrdonnancierDisp.class);
        beans.add(bean1);
        beans.add(bean2);
        this.ordonnancierDispService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((OrdonnancierDisp) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((OrdonnancierDisp) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final OrdonnancierDisp bean = Mockito.mock(OrdonnancierDisp.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.ordonnancierDispService.remove(bean);
            Assert.fail(OrdonnancierDispServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
