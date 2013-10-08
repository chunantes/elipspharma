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
import fr.pharma.eclipse.domain.model.ordonnancier.OrdonnancierFabReconst;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier OrdonnancierFabReconst.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class OrdonnancierFabReconstServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<OrdonnancierFabReconst> ordonnancierFabReconstService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<OrdonnancierFabReconst> mockedDao;

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
        this.ordonnancierFabReconstService = new GenericServiceImpl<OrdonnancierFabReconst>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.ordonnancierFabReconstService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.ordonnancierFabReconstService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un ordonnancierFabReconst pour le test.
     * @return Editeur initialisé.
     */
    private OrdonnancierFabReconst makeOrdonnancierFabReconstTest() {
        final OrdonnancierFabReconst ordonnancierFabReconst = new OrdonnancierFabReconst();
        ordonnancierFabReconst.setId(1L);
        return ordonnancierFabReconst;
    }

    /**
     * Crée une liste de ordonnancierFabReconsts pour le test.
     * @return La liste de OrdonnancierFabReconst initialisés.
     */
    private List<OrdonnancierFabReconst> makeListOrdonnancierFabReconstTest() {
        final List<OrdonnancierFabReconst> ordonnancierFabReconsts = new ArrayList<OrdonnancierFabReconst>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            ordonnancierFabReconsts.add(this.makeOrdonnancierFabReconstTest());
        }
        return ordonnancierFabReconsts;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final OrdonnancierFabReconst ordonnancierFabReconstExpected = this.makeOrdonnancierFabReconstTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(ordonnancierFabReconstExpected);

        final OrdonnancierFabReconst ordonnancierFabReconst = this.ordonnancierFabReconstService.get(idTest);

        Assert.assertNotNull(ordonnancierFabReconst);
        Assert.assertEquals(ordonnancierFabReconstExpected, ordonnancierFabReconst);
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
            this.ordonnancierFabReconstService.get(idTest);
            Assert.fail(OrdonnancierFabReconstServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final OrdonnancierFabReconst ordonnancierFabReconstToSave = this.makeOrdonnancierFabReconstTest();
        Mockito.when(this.mockedDao.save(ordonnancierFabReconstToSave)).thenReturn(ordonnancierFabReconstToSave);
        OrdonnancierFabReconst ordonnancierFabReconstSaved = null;
        ordonnancierFabReconstSaved = this.ordonnancierFabReconstService.save(ordonnancierFabReconstToSave);
        Assert.assertEquals(ordonnancierFabReconstSaved, ordonnancierFabReconstToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final OrdonnancierFabReconst ordonnancierFabReconstToSave = this.makeOrdonnancierFabReconstTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(ordonnancierFabReconstToSave)).thenThrow(exception);
        try {
            this.ordonnancierFabReconstService.save(ordonnancierFabReconstToSave);
            Assert.fail(OrdonnancierFabReconstServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les OrdonnancierFabReconst avec un
     * critère de recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final OrdonnancierSearchCriteria criteria = new OrdonnancierSearchCriteria();
        final List<OrdonnancierFabReconst> ordonnancierFabReconstsExp = this.makeListOrdonnancierFabReconstTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(ordonnancierFabReconstsExp);

        final List<OrdonnancierFabReconst> ordonnancierFabReconstsRetrieved = this.ordonnancierFabReconstService.getAll(criteria);

        Assert.assertEquals(ordonnancierFabReconstsExp.size(), ordonnancierFabReconstsRetrieved.size());
        for (final OrdonnancierFabReconst ordonnancierFabReconst : ordonnancierFabReconstsExp) {
            Assert.assertTrue(ordonnancierFabReconstsRetrieved.contains(ordonnancierFabReconst));
        }
    }

    /**
     * Test de l'appel de liste de tous les OrdonnancierFabReconst avec un
     * critère de recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final OrdonnancierSearchCriteria criteria = new OrdonnancierSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.ordonnancierFabReconstService.getAll(criteria);
            Assert.fail(OrdonnancierFabReconstServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final OrdonnancierFabReconst bean = Mockito.mock(OrdonnancierFabReconst.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.ordonnancierFabReconstService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de ordonnancierFabReconsts.
     */
    @Test
    public void testRemoveListe() {
        final List<OrdonnancierFabReconst> beans = new ArrayList<OrdonnancierFabReconst>();
        final OrdonnancierFabReconst bean1 = Mockito.mock(OrdonnancierFabReconst.class);
        final OrdonnancierFabReconst bean2 = Mockito.mock(OrdonnancierFabReconst.class);
        beans.add(bean1);
        beans.add(bean2);
        this.ordonnancierFabReconstService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((OrdonnancierFabReconst) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((OrdonnancierFabReconst) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final OrdonnancierFabReconst bean = Mockito.mock(OrdonnancierFabReconst.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.ordonnancierFabReconstService.remove(bean);
            Assert.fail(OrdonnancierFabReconstServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
