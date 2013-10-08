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
import fr.pharma.eclipse.domain.criteria.localisation.ServiceSearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier Service.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class ServiceServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<Service> serviceService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<Service> mockedDao;

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
        this.serviceService = new GenericServiceImpl<Service>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.serviceService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.serviceService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un service pour le test.
     * @return Editeur initialisé.
     */
    private Service makeServiceTest() {
        final Service service = new Service();
        service.setId(1L);
        return service;
    }

    /**
     * Crée une liste de services pour le test.
     * @return La liste de Service initialisés.
     */
    private List<Service> makeListServiceTest() {
        final List<Service> services = new ArrayList<Service>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            services.add(this.makeServiceTest());
        }
        return services;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final Service serviceExpected = this.makeServiceTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(serviceExpected);

        final Service service = this.serviceService.get(idTest);

        Assert.assertNotNull(service);
        Assert.assertEquals(serviceExpected, service);
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
            this.serviceService.get(idTest);
            Assert.fail(ServiceServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final Service serviceToSave = this.makeServiceTest();
        Mockito.when(this.mockedDao.save(serviceToSave)).thenReturn(serviceToSave);
        Service serviceSaved = null;
        serviceSaved = this.serviceService.save(serviceToSave);
        Assert.assertEquals(serviceSaved, serviceToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final Service serviceToSave = this.makeServiceTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(serviceToSave)).thenThrow(exception);
        try {
            this.serviceService.save(serviceToSave);
            Assert.fail(ServiceServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les Service avec un critère de recherche
     * OK.
     */
    @Test
    public void testGetAllOK() {
        final ServiceSearchCriteria criteria = new ServiceSearchCriteria();
        criteria.setNom("NOM");
        final List<Service> servicesExp = this.makeListServiceTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(servicesExp);

        final List<Service> servicesRetrieved = this.serviceService.getAll(criteria);

        Assert.assertEquals(servicesExp.size(), servicesRetrieved.size());
        for (final Service service : servicesExp) {
            Assert.assertTrue(servicesRetrieved.contains(service));
        }
    }

    /**
     * Test de l'appel de liste de tous les Service avec un critère de recherche
     * KO.
     */
    @Test
    public void testGetAllKo() {
        final ServiceSearchCriteria criteria = new ServiceSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.serviceService.getAll(criteria);
            Assert.fail(ServiceServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final Service bean = Mockito.mock(Service.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.serviceService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de services.
     */
    @Test
    public void testRemoveListe() {
        final List<Service> beans = new ArrayList<Service>();
        final Service bean1 = Mockito.mock(Service.class);
        final Service bean2 = Mockito.mock(Service.class);
        beans.add(bean1);
        beans.add(bean2);
        this.serviceService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((Service) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((Service) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final Service bean = Mockito.mock(Service.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.serviceService.remove(bean);
            Assert.fail(ServiceServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
