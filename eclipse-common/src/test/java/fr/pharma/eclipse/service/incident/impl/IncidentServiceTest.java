package fr.pharma.eclipse.service.incident.impl;

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
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.incident.IncidentSearchCriteria;
import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.model.incident.Incident;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier Incident.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class IncidentServiceTest {
    /**
     * Service.
     */
    private IncidentServiceImpl incidentService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<Incident> mockedDao;

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
        this.incidentService = new IncidentServiceImpl(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.incidentService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.incidentService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un evenement pour le test.
     * @return Editeur initialisé.
     */
    private Incident makeIncidentTest() {
        final Incident incident = new Incident();
        incident.setId(1L);
        return incident;
    }

    /**
     * Crée une liste d'incidents pour le test.
     * @return La liste de Incident initialisés.
     */
    private List<Incident> makeListIncidentTest() {
        final List<Incident> incidents = new ArrayList<Incident>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            incidents.add(this.makeIncidentTest());
        }
        return incidents;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final Incident incidentExpected = this.makeIncidentTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(incidentExpected);

        final Incident evenement = this.incidentService.get(idTest);

        Assert.assertNotNull(evenement);
        Assert.assertEquals(incidentExpected, evenement);
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
            this.incidentService.get(idTest);
            Assert.fail(IncidentServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final Incident incidentToSave = this.makeIncidentTest();
        Mockito.when(this.mockedDao.save(incidentToSave)).thenReturn(incidentToSave);
        Incident evenementSaved = null;
        evenementSaved = this.incidentService.save(incidentToSave);
        Assert.assertEquals(evenementSaved, incidentToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final Incident incidentToSave = this.makeIncidentTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(incidentToSave)).thenThrow(exception);
        try {
            this.incidentService.save(incidentToSave);
            Assert.fail(IncidentServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les Incident avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllOK() {

        final IncidentSearchCriteria criteria = new IncidentSearchCriteria();
        final List<Incident> evenementsExp = this.makeListIncidentTest();

        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(evenementsExp);

        final List<Incident> evenementsRetrieved = this.incidentService.getAll(criteria);

        Assert.assertEquals(evenementsExp.size(), evenementsRetrieved.size());
        for (final Incident evenement : evenementsExp) {
            Assert.assertTrue(evenementsRetrieved.contains(evenement));
        }
    }

    /**
     * Test de l'appel de liste de tous les Incident avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllOK2() {
        final List<Incident> evenementsExp = this.makeListIncidentTest();
        Mockito.when(this.mockedDao.getAll(Matchers.any(SearchCriteria.class))).thenReturn(evenementsExp);
        final List<Incident> evenementsRetrieved = this.incidentService.getAll();
        Assert.assertEquals(evenementsExp.size(), evenementsRetrieved.size());
        for (final Incident evenement : evenementsExp) {
            Assert.assertTrue(evenementsRetrieved.contains(evenement));
        }
    }

    /**
     * Test de l'appel de liste de tous les Incident avec un critère de
     * recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.incidentService.getAll(criteria);
            Assert.fail(IncidentServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final Incident bean = Mockito.mock(Incident.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.incidentService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de evenements.
     */
    @Test
    public void testRemoveListe() {
        final List<Incident> beans = new ArrayList<Incident>();
        final Incident bean1 = Mockito.mock(Incident.class);
        final Incident bean2 = Mockito.mock(Incident.class);
        beans.add(bean1);
        beans.add(bean2);
        this.incidentService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((Incident) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((Incident) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final Incident bean = Mockito.mock(Incident.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.incidentService.remove(bean);
            Assert.fail(IncidentServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

}
