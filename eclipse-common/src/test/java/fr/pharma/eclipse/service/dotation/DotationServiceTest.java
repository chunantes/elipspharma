package fr.pharma.eclipse.service.dotation;

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
import fr.pharma.eclipse.domain.criteria.dotation.DotationSearchCriteria;
import fr.pharma.eclipse.domain.model.dotation.Dotation;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier Dotation.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class DotationServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<Dotation> dotationService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<Dotation> mockedDao;

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
        this.dotationService = new GenericServiceImpl<Dotation>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.dotationService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.dotationService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un dotation pour le test.
     * @return Editeur initialisé.
     */
    private Dotation makeDotationTest() {
        final Dotation dotation = new Dotation();
        dotation.setId(1L);
        return dotation;
    }

    /**
     * Crée une liste de dotations pour le test.
     * @return La liste de Dotation initialisés.
     */
    private List<Dotation> makeListDotationTest() {
        final List<Dotation> dotations = new ArrayList<Dotation>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            dotations.add(this.makeDotationTest());
        }
        return dotations;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final Dotation dotationExpected = this.makeDotationTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(dotationExpected);

        final Dotation dotation = this.dotationService.get(idTest);

        Assert.assertNotNull(dotation);
        Assert.assertEquals(dotationExpected, dotation);
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
            this.dotationService.get(idTest);
            Assert.fail(DotationServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final Dotation dotationToSave = this.makeDotationTest();
        Mockito.when(this.mockedDao.save(dotationToSave)).thenReturn(dotationToSave);
        Dotation dotationSaved = null;
        dotationSaved = this.dotationService.save(dotationToSave);
        Assert.assertEquals(dotationSaved, dotationToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final Dotation dotationToSave = this.makeDotationTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(dotationToSave)).thenThrow(exception);
        try {
            this.dotationService.save(dotationToSave);
            Assert.fail(DotationServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les Dotation avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final DotationSearchCriteria criteria = new DotationSearchCriteria();
        final List<Dotation> dotationsExp = this.makeListDotationTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(dotationsExp);

        final List<Dotation> dotationsRetrieved = this.dotationService.getAll(criteria);

        Assert.assertEquals(dotationsExp.size(), dotationsRetrieved.size());
        for (final Dotation dotation : dotationsExp) {
            Assert.assertTrue(dotationsRetrieved.contains(dotation));
        }
    }

    /**
     * Test de l'appel de liste de tous les Dotation avec un critère de
     * recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final DotationSearchCriteria criteria = new DotationSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.dotationService.getAll(criteria);
            Assert.fail(DotationServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final Dotation bean = Mockito.mock(Dotation.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.dotationService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de dotations.
     */
    @Test
    public void testRemoveListe() {
        final List<Dotation> beans = new ArrayList<Dotation>();
        final Dotation bean1 = Mockito.mock(Dotation.class);
        final Dotation bean2 = Mockito.mock(Dotation.class);
        beans.add(bean1);
        beans.add(bean2);
        this.dotationService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((Dotation) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((Dotation) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final Dotation bean = Mockito.mock(Dotation.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.dotationService.remove(bean);
            Assert.fail(DotationServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
