package fr.pharma.eclipse.dao.hibernate.session.incident;

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
import fr.pharma.eclipse.domain.criteria.incident.IncidentSearchCriteria;
import fr.pharma.eclipse.domain.model.incident.Incident;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.utils.ContextSecurityHelper;

/**
 * Classe en charge de tester la DAO Incident avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class IncidentDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Incident.
     */
    private GenericDaoHibernate<Incident> evenementDao;

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
        this.evenementDao = new GenericDaoHibernate<Incident>(Incident.class);
        this.evenementDao.setEntityManager(this.entityManager);
        this.evenementDao.setCriteriaDictionary(this.dictionary);
        ContextSecurityHelper.createSecurityContextMock();
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.evenementDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO Incident.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de Incident
        Incident evenement = new Incident();
        evenement.setCommentaire("commentaire");
        evenement.setDate(Calendar.getInstance());
        evenement.setEssai(this.essaiService.get(1L));
        evenement.setLibelle("libelle");

        evenement = this.evenementDao.save(evenement);

        // Récupération de Incident
        final Incident evenementReturn = this.evenementDao.get(evenement.getId());

        Assert.assertEquals(evenement.getCommentaire(), evenementReturn.getCommentaire());
        Assert.assertEquals(evenement.getLibelle(), evenementReturn.getLibelle());
        Assert.assertEquals(evenement.getDate(), evenementReturn.getDate());
        Assert.assertEquals(evenement.getEssai(), evenementReturn.getEssai());
        Assert.assertNotNull(evenementReturn.getModifs());

        // Suppression de Incident
        this.evenementDao.remove(evenement);
    }

    /**
     * Test de la récupération de tous les Incidents.
     */
    @Test
    public void getAllWithCriteria() {
        // Récupération de tous les Incident
        final fr.pharma.eclipse.domain.criteria.incident.IncidentSearchCriteria criteria = new IncidentSearchCriteria();
        criteria.setActiveOrder("date");
        criteria.setDateDebut(Calendar.getInstance());
        criteria.setDateFin(Calendar.getInstance());
        criteria.setEssai(this.essaiService.get(1L));

        criteria.setAscending(false);
        final Collection<Incident> evts = this.evenementDao.getAll(criteria);

        Assert.assertNotNull(evts);

        for (final Incident evenement : evts) {
            Assert.assertNotNull(evenement.getId());
        }
    }

    /**
     * Test de la récupération de tous les Incidents.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Incident
        final IncidentSearchCriteria criteria = new IncidentSearchCriteria();
        final Collection<Incident> evts = this.evenementDao.getAll(criteria);
        Assert.assertNotNull(evts);
        for (final Incident evt : evts) {
            Assert.assertNotNull(evt.getId());
        }
    }

}
