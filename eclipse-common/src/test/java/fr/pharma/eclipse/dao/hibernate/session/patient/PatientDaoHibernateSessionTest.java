package fr.pharma.eclipse.dao.hibernate.session.patient;

import java.util.Collection;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.dictionary.impl.CriteriaDictionaryImpl;
import fr.pharma.eclipse.domain.criteria.patient.PatientSearchCriteria;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.service.essai.EssaiService;

/**
 * Classe en charge de tester la DAO Patient avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class PatientDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Patient.
     */
    private GenericDaoHibernate<Patient> patientDao;

    /**
     * Dictionnaire de critère de recherche.
     */
    @Resource(name = "criteriaDictionary")
    private CriteriaDictionaryImpl dictionary;

    /**
     * Service de gestion des essais.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * Initialisation du test.
     */
    @Before
    public void init() {
        this.patientDao = new GenericDaoHibernate<Patient>(Patient.class);
        this.patientDao.setEntityManager(this.entityManager);
        this.patientDao.setCriteriaDictionary(this.dictionary);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.patientDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO Patient.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de Patient
        Patient patient = new Patient();
        patient.setNom("nom");
        patient.setPrenom("prenom");
        patient.setNumeroIpp("5456");
        patient = this.patientDao.save(patient);

        // Récupération de Patient
        final Patient patientReturn = this.patientDao.get(patient.getId());

        Assert.assertEquals(patient.getNom(), patientReturn.getNom());
        Assert.assertEquals(patient.getPrenom(), patientReturn.getPrenom());
        Assert.assertEquals(patient.getNumeroIpp(), patientReturn.getNumeroIpp());
        Assert.assertNotNull(patientReturn.getModifs());

        // Suppression de Patient
        this.patientDao.remove(patient);
    }

    /**
     * Test de la récupération de tous les Patients.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Patient
        final PatientSearchCriteria criteria = new PatientSearchCriteria();
        final Collection<Patient> patients = this.patientDao.getAll(criteria);

        Assert.assertNotNull(patients);

        for (final Patient patient : patients) {
            Assert.assertNotNull(patient.getId());
            Assert.assertNotNull(patient.getNom());
        }
    }

}
