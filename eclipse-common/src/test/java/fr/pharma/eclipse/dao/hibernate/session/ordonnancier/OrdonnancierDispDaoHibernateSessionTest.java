package fr.pharma.eclipse.dao.hibernate.session.ordonnancier;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.dictionary.impl.CriteriaDictionaryImpl;
import fr.pharma.eclipse.domain.criteria.ordonnancier.OrdonnancierSearchCriteria;
import fr.pharma.eclipse.domain.model.ordonnancier.OrdonnancierDisp;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester la DAO OrdonnancierDisp avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class OrdonnancierDispDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de OrdonnancierDisp.
     */
    private GenericDaoHibernate<OrdonnancierDisp> ordonnancierDispDao;

    /**
     * Dictionnaire de critère de recherche.
     */
    @Resource(name = "criteriaDictionary")
    private CriteriaDictionaryImpl dictionary;

    /**
     * Service de gestion des pharmacies.
     */
    @Resource(name = "pharmacieService")
    private PharmacieService pharmacieService;

    /**
     * Initialisation du test.
     */
    @Before
    public void init() {
        this.ordonnancierDispDao = new GenericDaoHibernate<OrdonnancierDisp>(OrdonnancierDisp.class);
        this.ordonnancierDispDao.setEntityManager(this.entityManager);
        this.ordonnancierDispDao.setCriteriaDictionary(this.dictionary);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.ordonnancierDispDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO OrdonnancierDisp.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de OrdonnancierDisp
        OrdonnancierDisp ordonnancierDisp = new OrdonnancierDisp();
        ordonnancierDisp.setDateDebut(Calendar.getInstance(EclipseConstants.LOCALE));
        ordonnancierDisp.setDateFin(Calendar.getInstance(EclipseConstants.LOCALE));
        ordonnancierDisp.setDateMaj(Calendar.getInstance(EclipseConstants.LOCALE));
        ordonnancierDisp.setMajPar("CEL");
        ordonnancierDisp.setPharmacie(this.pharmacieService.get(1L));
        ordonnancierDisp = this.ordonnancierDispDao.save(ordonnancierDisp);

        // Récupération de OrdonnancierDisp
        final OrdonnancierDisp ordonnancierDispReturn = this.ordonnancierDispDao.get(ordonnancierDisp.getId());

        Assert.assertEquals(ordonnancierDisp.getDateDebut(), ordonnancierDispReturn.getDateDebut());
        Assert.assertEquals(ordonnancierDisp.getDateFin(), ordonnancierDispReturn.getDateFin());
        Assert.assertEquals(ordonnancierDisp.getDateMaj(), ordonnancierDispReturn.getDateMaj());
        Assert.assertEquals(ordonnancierDisp.getMajPar(), ordonnancierDispReturn.getMajPar());
        Assert.assertEquals(ordonnancierDisp.getPharmacie(), ordonnancierDispReturn.getPharmacie());
        Assert.assertNotNull(ordonnancierDispReturn.getDispensations());

        // Suppression de OrdonnancierDisp
        this.ordonnancierDispDao.remove(ordonnancierDisp);
    }

    /**
     * Test de la récupération de tous les OrdonnancierDisps.
     */
    @Test
    public void getAllWithCriteria() {
        // Récupération de tous les OrdonnancierDisp
        final OrdonnancierSearchCriteria criteria = new OrdonnancierSearchCriteria();
        criteria.setActiveOrder("dateDebut");
        criteria.setPharmacie(this.pharmacieService.get(1L));
        criteria.setAscending(false);
        final Collection<OrdonnancierDisp> ordonnancierDisps = this.ordonnancierDispDao.getAll(criteria);

        Assert.assertNotNull(ordonnancierDisps);

        for (final OrdonnancierDisp ordonnancierDisp : ordonnancierDisps) {
            Assert.assertNotNull(ordonnancierDisp.getId());
        }
    }

    /**
     * Test de la récupération de tous les OrdonnancierDisps.
     */
    @Test
    public void getAll() {
        // Récupération de tous les OrdonnancierDisp
        final OrdonnancierSearchCriteria criteria = new OrdonnancierSearchCriteria();
        final Collection<OrdonnancierDisp> ordonnancierDisps = this.ordonnancierDispDao.getAll(criteria);
        Assert.assertNotNull(ordonnancierDisps);
        for (final OrdonnancierDisp ordonnancierDisp : ordonnancierDisps) {
            Assert.assertNotNull(ordonnancierDisp.getId());
        }
    }

}
