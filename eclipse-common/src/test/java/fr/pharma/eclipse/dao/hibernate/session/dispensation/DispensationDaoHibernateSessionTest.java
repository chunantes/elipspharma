package fr.pharma.eclipse.dao.hibernate.session.dispensation;

import java.util.Calendar;
import java.util.Collection;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.dispensation.DispensationForOrdoSearchCriteria;
import fr.pharma.eclipse.domain.criteria.dispensation.DispensationSearchCriteria;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.utils.ContextSecurityHelper;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester la DAO Dispensation avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext.xml" })
@Transactional
public class DispensationDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Dispensation.
     */
    @Resource(name = "dispensationDao")
    private GenericDao<Dispensation> dispensationDao;

    /**
     * Service de gestion des pharmacies.
     */
    @Autowired
    private PharmacieService pharmacieService;

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
     * Test de la récupération de tous les Dispensations.
     */
    @Test
    public void getAllWithCriteria() {
        // Récupération de tous les Dispensation
        final DispensationSearchCriteria criteria = new DispensationSearchCriteria();
        criteria.setActiveOrder("id");

        final Patient patient = this.entityManager.find(Patient.class, 1L);
        criteria.setPatient(patient);
        criteria.setAscending(true);
        criteria.setCaseSensitiveOrder(true);

        criteria.setEssai(this.entityManager.find(Essai.class, 1L));
        criteria.setPharmacie(this.entityManager.find(Pharmacie.class, 1L));

        final Collection<Dispensation> dispensations = this.dispensationDao.getAll(criteria);
        Assert.assertNotNull(dispensations);

        for (final Dispensation dispensation : dispensations) {
            Assert.assertTrue(dispensation.getPrescription().getInclusion().getPatient().equals(patient));
        }
    }

    /**
     * Test de la récupération de tous les Dispensations pour l'ordonnancier.
     */
    @Test
    public void getAllWithCriteriaForOrdo() {
        // Récupération de tous les Dispensation
        final DispensationForOrdoSearchCriteria criteria = new DispensationForOrdoSearchCriteria();
        criteria.setActiveOrder("dateDispensation");
        criteria.setAscending(true);
        criteria.setCaseSensitiveOrder(true);
        criteria.setDateDebut(Calendar.getInstance(EclipseConstants.LOCALE));
        criteria.setDateFin(Calendar.getInstance(EclipseConstants.LOCALE));
        criteria.setPharmacie(this.pharmacieService.get(1L));

        final Collection<Dispensation> dispensations = this.dispensationDao.getAll(criteria);

        Assert.assertNotNull(dispensations);

        for (final Dispensation dispensation : dispensations) {
            Assert.assertNotNull(dispensation.getId());
        }
    }

    /**
     * Test de la récupération de tous les Dispensations.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Dispensation
        final DispensationSearchCriteria criteria = new DispensationSearchCriteria();
        final Collection<Dispensation> dispensations = this.dispensationDao.getAll(criteria);

        Assert.assertNotNull(dispensations);

        for (final Dispensation dispensation : dispensations) {
            Assert.assertNotNull(dispensation.getId());
        }
    }

}
