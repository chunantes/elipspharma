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
import fr.pharma.eclipse.domain.model.ordonnancier.OrdonnancierFabReconst;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester la DAO OrdonnancierFabReconst avec une session
 * réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class OrdonnancierFabReconstDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de OrdonnancierFabReconst.
     */
    private GenericDaoHibernate<OrdonnancierFabReconst> ordonnancierFabReconstDao;

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
        this.ordonnancierFabReconstDao = new GenericDaoHibernate<OrdonnancierFabReconst>(OrdonnancierFabReconst.class);
        this.ordonnancierFabReconstDao.setEntityManager(this.entityManager);
        this.ordonnancierFabReconstDao.setCriteriaDictionary(this.dictionary);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.ordonnancierFabReconstDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO OrdonnancierFabReconst.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de OrdonnancierFabReconst
        OrdonnancierFabReconst ordonnancierFabReconst = new OrdonnancierFabReconst();
        ordonnancierFabReconst.setDateDebut(Calendar.getInstance(EclipseConstants.LOCALE));
        ordonnancierFabReconst.setDateFin(Calendar.getInstance(EclipseConstants.LOCALE));
        ordonnancierFabReconst.setDateMaj(Calendar.getInstance(EclipseConstants.LOCALE));
        ordonnancierFabReconst.setMajPar("CEL");
        ordonnancierFabReconst.setPharmacie(this.pharmacieService.get(1L));
        ordonnancierFabReconst = this.ordonnancierFabReconstDao.save(ordonnancierFabReconst);

        // Récupération de OrdonnancierFabReconst
        final OrdonnancierFabReconst ordonnancierFabReconstReturn = this.ordonnancierFabReconstDao.get(ordonnancierFabReconst.getId());

        Assert.assertEquals(ordonnancierFabReconst.getDateDebut(), ordonnancierFabReconstReturn.getDateDebut());
        Assert.assertEquals(ordonnancierFabReconst.getDateFin(), ordonnancierFabReconstReturn.getDateFin());
        Assert.assertEquals(ordonnancierFabReconst.getDateMaj(), ordonnancierFabReconstReturn.getDateMaj());
        Assert.assertEquals(ordonnancierFabReconst.getMajPar(), ordonnancierFabReconstReturn.getMajPar());
        Assert.assertEquals(ordonnancierFabReconst.getPharmacie(), ordonnancierFabReconstReturn.getPharmacie());
        Assert.assertNotNull(ordonnancierFabReconstReturn.getElementsToCheck());

        // Suppression de OrdonnancierFabReconst
        this.ordonnancierFabReconstDao.remove(ordonnancierFabReconst);
    }

    /**
     * Test de la récupération de tous les OrdonnancierFabReconsts.
     */
    @Test
    public void getAllWithCriteria() {
        // Récupération de tous les OrdonnancierFabReconst
        final OrdonnancierSearchCriteria criteria = new OrdonnancierSearchCriteria();
        criteria.setActiveOrder("dateDebut");
        criteria.setPharmacie(this.pharmacieService.get(1L));
        criteria.setAscending(false);
        final Collection<OrdonnancierFabReconst> ordonnancierFabReconsts = this.ordonnancierFabReconstDao.getAll(criteria);

        Assert.assertNotNull(ordonnancierFabReconsts);

        for (final OrdonnancierFabReconst ordonnancierFabReconst : ordonnancierFabReconsts) {
            Assert.assertNotNull(ordonnancierFabReconst.getId());
        }
    }

    /**
     * Test de la récupération de tous les OrdonnancierFabReconsts.
     */
    @Test
    public void getAll() {
        // Récupération de tous les OrdonnancierFabReconst
        final OrdonnancierSearchCriteria criteria = new OrdonnancierSearchCriteria();
        final Collection<OrdonnancierFabReconst> ordonnancierFabReconsts = this.ordonnancierFabReconstDao.getAll(criteria);
        Assert.assertNotNull(ordonnancierFabReconsts);
        for (final OrdonnancierFabReconst ordonnancierFabReconst : ordonnancierFabReconsts) {
            Assert.assertNotNull(ordonnancierFabReconst.getId());
        }
    }

}
