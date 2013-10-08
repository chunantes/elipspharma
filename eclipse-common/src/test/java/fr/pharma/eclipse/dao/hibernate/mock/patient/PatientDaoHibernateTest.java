package fr.pharma.eclipse.dao.hibernate.mock.patient;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.patient.Patient;

/**
 * Classe en charge de tester la DAO des patients.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PatientDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<Patient> patientDao;

    /**
     * EntityManager.
     */
    private EntityManager entityManager;

    /**
     * Méthode d'initialisation du test.
     */
    @Before
    public void init() {
        this.entityManager = Mockito.mock(EntityManager.class);
        this.patientDao = new GenericDaoHibernate<Patient>(Patient.class);
        this.patientDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.patientDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final Patient patient = new Patient();
        patient.setId(1L);

        Mockito.when(this.entityManager.find(Patient.class, patient.getId())).thenReturn(patient);

        final Patient patientReturned = this.patientDao.get(patient.getId());
        Assert.assertNotNull(patientReturned);
        Assert.assertNotNull(patientReturned.getId());
        Assert.assertEquals(1L, patientReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final Patient patient = new Patient();
        patient.setId(1L);
        Mockito.when(this.entityManager.merge(patient)).thenReturn(patient);
        final Patient patientSaved = this.patientDao.save(patient);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(patientSaved);
        Assert.assertEquals(patient, patientSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final Patient patient = new Patient();
        Mockito.when(this.patientDao.get(patient.getId())).thenReturn(patient);
        this.patientDao.remove(patient);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
