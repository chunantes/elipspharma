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
import fr.pharma.eclipse.domain.model.stock.RetourPatient;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier RetourPatient.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class RetourPatientServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<RetourPatient> retourPatientService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<RetourPatient> mockedDao;

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
        this.retourPatientService = new GenericServiceImpl<RetourPatient>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.retourPatientService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.retourPatientService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un retourPatient pour le test.
     * @return Editeur initialisé.
     */
    private RetourPatient makeRetourPatientTest() {
        final RetourPatient retourPatient = new RetourPatient();
        retourPatient.setId(1L);
        return retourPatient;
    }

    /**
     * Crée une liste de retourPatients pour le test.
     * @return La liste de RetourPatient initialisés.
     */
    private List<RetourPatient> makeListRetourPatientTest() {
        final List<RetourPatient> retourPatients = new ArrayList<RetourPatient>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            retourPatients.add(this.makeRetourPatientTest());
        }
        return retourPatients;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final RetourPatient retourPatientExpected = this.makeRetourPatientTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(retourPatientExpected);

        final RetourPatient retourPatient = this.retourPatientService.get(idTest);

        Assert.assertNotNull(retourPatient);
        Assert.assertEquals(retourPatientExpected, retourPatient);
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
            this.retourPatientService.get(idTest);
            Assert.fail(RetourPatientServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final RetourPatient retourPatientToSave = this.makeRetourPatientTest();
        Mockito.when(this.mockedDao.save(retourPatientToSave)).thenReturn(retourPatientToSave);
        RetourPatient retourPatientSaved = null;
        retourPatientSaved = this.retourPatientService.save(retourPatientToSave);
        Assert.assertEquals(retourPatientSaved, retourPatientToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final RetourPatient retourPatientToSave = this.makeRetourPatientTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(retourPatientToSave)).thenThrow(exception);
        try {
            this.retourPatientService.save(retourPatientToSave);
            Assert.fail(RetourPatientServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les RetourPatient avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        criteria.setTypeMouvement(TypeMvtStock.APPROVISIONNEMENT);
        final List<RetourPatient> retourPatientsExp = this.makeListRetourPatientTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(retourPatientsExp);

        final List<RetourPatient> retourPatientsRetrieved = this.retourPatientService.getAll(criteria);

        Assert.assertEquals(retourPatientsExp.size(), retourPatientsRetrieved.size());
        for (final RetourPatient retourPatient : retourPatientsExp) {
            Assert.assertTrue(retourPatientsRetrieved.contains(retourPatient));
        }
    }

    /**
     * Test de l'appel de liste de tous les RetourPatient avec un critère de
     * recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.retourPatientService.getAll(criteria);
            Assert.fail(RetourPatientServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final RetourPatient bean = Mockito.mock(RetourPatient.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.retourPatientService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de retourPatients.
     */
    @Test
    public void testRemoveListe() {
        final List<RetourPatient> beans = new ArrayList<RetourPatient>();
        final RetourPatient bean1 = Mockito.mock(RetourPatient.class);
        final RetourPatient bean2 = Mockito.mock(RetourPatient.class);
        beans.add(bean1);
        beans.add(bean2);
        this.retourPatientService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((RetourPatient) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((RetourPatient) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final RetourPatient bean = Mockito.mock(RetourPatient.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.retourPatientService.remove(bean);
            Assert.fail(RetourPatientServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
