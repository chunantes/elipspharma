package fr.pharma.eclipse.dao.hibernate.session.prescription;

import java.util.Collection;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.prescription.PrescriptionSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.utils.ContextSecurityHelper;

/**
 * Classe en charge de tester la DAO Prescription avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class PrescriptionDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Prescription.
     */
    @Resource
    private GenericDao<Prescription> prescriptionDao;

    /**
     * Initialisation des données de test.
     */
    @Before
    public void setUp() {
        ContextSecurityHelper.createSecurityContextMock();
    }

    /**
     * Purge des données de test.
     */
    @After
    public void tearDown() {
        SecurityContextHolder.clearContext();
    }

    /**
     * Test de la récupération de tous les Prescriptions.
     */
    @Test
    public void getAllWithCriteria() {
        // Récupération de tous les Prescription
        final PrescriptionSearchCriteria criteria = new PrescriptionSearchCriteria();
        criteria.setActiveOrder("id");
        criteria.setDispense(false);
        final Essai essai = this.entityManager.find(Essai.class, 1L);
        final Inclusion inclusion = this.entityManager.find(Inclusion.class, 1L);
        criteria.setEssai(essai);
        criteria.setInclusion(inclusion);
        final Patient patient = this.entityManager.find(Patient.class, 1L);
        criteria.setPatient(patient);
        criteria.setAscending(true);
        criteria.setCaseSensitiveOrder(true);
        final Collection<Prescription> prescriptions = this.prescriptionDao.getAll(criteria);

        Assert.assertNotNull(prescriptions);

        for (final Prescription prescription : prescriptions) {
            Assert.assertTrue(prescription.getEssai().equals(essai));
            Assert.assertTrue(prescription.getInclusion().equals(inclusion));
        }
    }

    /**
     * Test de la récupération de tous les Prescriptions.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Prescription
        final PrescriptionSearchCriteria criteria = new PrescriptionSearchCriteria();
        final Collection<Prescription> prescriptions = this.prescriptionDao.getAll(criteria);

        Assert.assertNotNull(prescriptions);

        for (final Prescription prescription : prescriptions) {
            Assert.assertNotNull(prescription.getId());
        }
    }

}
