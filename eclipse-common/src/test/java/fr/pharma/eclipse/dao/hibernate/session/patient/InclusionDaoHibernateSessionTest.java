package fr.pharma.eclipse.dao.hibernate.session.patient;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.patient.InclusionSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.utils.ContextSecurityHelper;

/**
 * Classe en charge de tester la DAO Inclusion avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class InclusionDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Inclusion.
     */
    @Resource
    private GenericDao<Inclusion> inclusionDao;

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
     * Test de la récupération de tous les Inclusions.
     */
    @Test
    public void getAllWithCriteria() {
        // Récupération de tous les Inclusion
        final InclusionSearchCriteria criteria = new InclusionSearchCriteria();
        criteria.setActiveOrder("id");
        final Essai essai = this.entityManager.find(Essai.class, 1L);
        criteria.setEssai(essai);
        criteria.setActif(true);
        criteria.setDateDebut(Calendar.getInstance());
        criteria.setDateFin(Calendar.getInstance());
        criteria.setAscending(true);
        criteria.setCaseSensitiveOrder(true);
        final Collection<Inclusion> inclusions = this.inclusionDao.getAll(criteria);

        Assert.assertNotNull(inclusions);

        for (final Inclusion inclusion : inclusions) {
            Assert.assertTrue(inclusion.getEssai().equals(essai));
        }
    }

    /**
     * Test de la récupération de tous les Inclusions.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Inclusion
        final InclusionSearchCriteria criteria = new InclusionSearchCriteria();
        final Collection<Inclusion> inclusions = this.inclusionDao.getAll(criteria);

        Assert.assertNotNull(inclusions);

        for (final Inclusion inclusion : inclusions) {
            Assert.assertNotNull(inclusion.getId());
        }
    }

}
