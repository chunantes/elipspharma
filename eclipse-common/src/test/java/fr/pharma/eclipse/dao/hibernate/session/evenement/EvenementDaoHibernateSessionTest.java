package fr.pharma.eclipse.dao.hibernate.session.evenement;

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
import fr.pharma.eclipse.domain.criteria.evenement.EvenementSearchCriteria;
import fr.pharma.eclipse.domain.enums.evenement.ResultatVisite;
import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.enums.evenement.TypeVisite;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.utils.ContextSecurityHelper;

/**
 * Classe en charge de tester la DAO Evenement avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class EvenementDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Evenement.
     */
    private GenericDaoHibernate<Evenement> evenementDao;

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
        this.evenementDao = new GenericDaoHibernate<Evenement>(Evenement.class);
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
     * Test des méthodes de DAO Evenement.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de Evenement
        Evenement evenement = new Evenement();
        evenement.setCommentaire("commentaire");
        evenement.setDateDebut(Calendar.getInstance());
        evenement.setHeureDebut("10:30");
        evenement.setEssai(this.essaiService.get(1L));
        evenement.setLibelle("libelle");
        evenement.setResultatVisite(ResultatVisite.EFFECTUE);
        evenement.setTypeEvenement(TypeEvenement.VISITE);
        evenement.setTypeVisite(TypeVisite.AUDIT_EXTERNE);
        evenement.setDateDebut(Calendar.getInstance());
        evenement.setDateFin(Calendar.getInstance());

        evenement = this.evenementDao.save(evenement);

        // Récupération de Evenement
        final Evenement evenementReturn = this.evenementDao.get(evenement.getId());

        Assert.assertEquals(evenement.getCommentaire(), evenementReturn.getCommentaire());
        Assert.assertEquals(evenement.getLibelle(), evenementReturn.getLibelle());
        Assert.assertEquals(evenement.getDateDebut(), evenementReturn.getDateDebut());
        Assert.assertEquals(evenement.getHeureDebut(), evenementReturn.getHeureDebut());
        Assert.assertEquals(evenement.getEssai(), evenementReturn.getEssai());
        Assert.assertEquals(evenement.getTypeEvenement().getLibelle(), evenementReturn.getTypeEvenement().getLibelle());
        Assert.assertEquals(evenement.getTypeVisite().getLibelle(), evenementReturn.getTypeVisite().getLibelle());
        Assert.assertEquals(evenement.getResultatVisite(), evenementReturn.getResultatVisite());
        Assert.assertNotNull(evenementReturn.getModifs());

        // Suppression de Evenement
        this.evenementDao.remove(evenement);
    }

    /**
     * Test de la récupération de tous les Evenements.
     */
    @Test
    public void getAllWithCriteria() {
        // Récupération de tous les Evenement
        final EvenementSearchCriteria criteria = new EvenementSearchCriteria();
        criteria.setActiveOrder("dateDebut");
        criteria.setTypeEvenement(TypeEvenement.VISITE);
        criteria.setTypeVisite(TypeVisite.AUDIT_EXTERNE);
        criteria.setResultatVisite(ResultatVisite.EFFECTUE);
        criteria.setDateDebut(Calendar.getInstance());
        criteria.setDateFin(Calendar.getInstance());
        criteria.setEssai(this.essaiService.get(1L));
        criteria.setResultVisiteVide(Boolean.TRUE);
        criteria.setAscending(false);

        final Collection<Evenement> evts = this.evenementDao.getAll(criteria);

        Assert.assertNotNull(evts);

        for (final Evenement evenement : evts) {
            Assert.assertNotNull(evenement.getId());
        }
    }

    /**
     * Test de la récupération de tous les Evenements.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Evenement
        final EvenementSearchCriteria criteria = new EvenementSearchCriteria();
        final Collection<Evenement> evts = this.evenementDao.getAll(criteria);
        Assert.assertNotNull(evts);
        for (final Evenement evt : evts) {
            Assert.assertNotNull(evt.getId());
        }
    }

}
