package fr.pharma.eclipse.service.patient;

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
import fr.pharma.eclipse.domain.criteria.patient.PatientSearchCriteria;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier Patient.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class PatientServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<Patient> patientService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<Patient> mockedDao;

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
        this.patientService = new GenericServiceImpl<Patient>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.patientService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.patientService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un patient pour le test.
     * @return Editeur initialisé.
     */
    private Patient makePatientTest() {
        final Patient patient = new Patient();
        patient.setId(1L);
        return patient;
    }

    /**
     * Crée une liste de patients pour le test.
     * @return La liste de Patient initialisés.
     */
    private List<Patient> makeListPatientTest() {
        final List<Patient> patients = new ArrayList<Patient>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            patients.add(this.makePatientTest());
        }
        return patients;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final Patient patientExpected = this.makePatientTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(patientExpected);

        final Patient patient = this.patientService.get(idTest);

        Assert.assertNotNull(patient);
        Assert.assertEquals(patientExpected, patient);
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
            this.patientService.get(idTest);
            Assert.fail(PatientServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final Patient patientToSave = this.makePatientTest();
        Mockito.when(this.mockedDao.save(patientToSave)).thenReturn(patientToSave);
        Patient patientSaved = null;
        patientSaved = this.patientService.save(patientToSave);
        Assert.assertEquals(patientSaved, patientToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final Patient patientToSave = this.makePatientTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(patientToSave)).thenThrow(exception);
        try {
            this.patientService.save(patientToSave);
            Assert.fail(PatientServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les Patient avec un critère de recherche
     * OK.
     */
    @Test
    public void testGetAllOK() {
        final PatientSearchCriteria criteria = new PatientSearchCriteria();
        criteria.setNom("NOM");
        final List<Patient> patientsExp = this.makeListPatientTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(patientsExp);

        final List<Patient> patientsRetrieved = this.patientService.getAll(criteria);

        Assert.assertEquals(patientsExp.size(), patientsRetrieved.size());
        for (final Patient patient : patientsExp) {
            Assert.assertTrue(patientsRetrieved.contains(patient));
        }
    }

    /**
     * Test de l'appel de liste de tous les Patient avec un critère de recherche
     * KO.
     */
    @Test
    public void testGetAllKo() {
        final PatientSearchCriteria criteria = new PatientSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.patientService.getAll(criteria);
            Assert.fail(PatientServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final Patient bean = Mockito.mock(Patient.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.patientService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de patients.
     */
    @Test
    public void testRemoveListe() {
        final List<Patient> beans = new ArrayList<Patient>();
        final Patient bean1 = Mockito.mock(Patient.class);
        final Patient bean2 = Mockito.mock(Patient.class);
        beans.add(bean1);
        beans.add(bean2);
        this.patientService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((Patient) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((Patient) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final Patient bean = Mockito.mock(Patient.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.patientService.remove(bean);
            Assert.fail(PatientServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
