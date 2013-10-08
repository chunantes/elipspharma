package fr.pharma.eclipse.service.habilitation;

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
import fr.pharma.eclipse.domain.criteria.habilitation.HabilitationSearchCriteria;
import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier Habilitation.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class HabilitationServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<Habilitation> habilitationService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<Habilitation> mockedDao;

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
        this.habilitationService = new GenericServiceImpl<Habilitation>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.habilitationService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.habilitationService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un habilitation pour le test.
     * @return Editeur initialisé.
     */
    private Habilitation makeHabilitationTest() {
        final Habilitation habilitation = new Habilitation();
        habilitation.setId(1L);
        return habilitation;
    }

    /**
     * Crée une liste de habilitations pour le test.
     * @return La liste de Habilitation initialisés.
     */
    private List<Habilitation> makeListHabilitationTest() {
        final List<Habilitation> habilitations = new ArrayList<Habilitation>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            habilitations.add(this.makeHabilitationTest());
        }
        return habilitations;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final Habilitation habilitationExpected = this.makeHabilitationTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(habilitationExpected);

        final Habilitation habilitation = this.habilitationService.get(idTest);

        Assert.assertNotNull(habilitation);
        Assert.assertEquals(habilitationExpected, habilitation);
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
            this.habilitationService.get(idTest);
            Assert.fail(HabilitationServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final Habilitation habilitationToSave = this.makeHabilitationTest();
        Mockito.when(this.mockedDao.save(habilitationToSave)).thenReturn(habilitationToSave);
        Habilitation habilitationSaved = null;
        habilitationSaved = this.habilitationService.save(habilitationToSave);
        Assert.assertEquals(habilitationSaved, habilitationToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final Habilitation habilitationToSave = this.makeHabilitationTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(habilitationToSave)).thenThrow(exception);
        try {
            this.habilitationService.save(habilitationToSave);
            Assert.fail(HabilitationServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les Habilitation avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final HabilitationSearchCriteria criteria = new HabilitationSearchCriteria();
        criteria.setDroit(Droit.INVESTIGATEUR_PRINCIPAL);
        final List<Habilitation> habilitationsExp = this.makeListHabilitationTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(habilitationsExp);

        final List<Habilitation> habilitationsRetrieved = this.habilitationService.getAll(criteria);

        Assert.assertEquals(habilitationsExp.size(), habilitationsRetrieved.size());
        for (final Habilitation habilitation : habilitationsExp) {
            Assert.assertTrue(habilitationsRetrieved.contains(habilitation));
        }
    }

    /**
     * Test de l'appel de liste de tous les Habilitation avec un critère de
     * recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final HabilitationSearchCriteria criteria = new HabilitationSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.habilitationService.getAll(criteria);
            Assert.fail(HabilitationServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final Habilitation bean = Mockito.mock(Habilitation.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.habilitationService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de habilitations.
     */
    @Test
    public void testRemoveListe() {
        final List<Habilitation> beans = new ArrayList<Habilitation>();
        final Habilitation bean1 = Mockito.mock(Habilitation.class);
        final Habilitation bean2 = Mockito.mock(Habilitation.class);
        beans.add(bean1);
        beans.add(bean2);
        this.habilitationService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((Habilitation) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((Habilitation) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final Habilitation bean = Mockito.mock(Habilitation.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.habilitationService.remove(bean);
            Assert.fail(HabilitationServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
