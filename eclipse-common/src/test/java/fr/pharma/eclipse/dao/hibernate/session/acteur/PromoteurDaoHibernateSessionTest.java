package fr.pharma.eclipse.dao.hibernate.session.acteur;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

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

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.dictionary.impl.CriteriaDictionaryImpl;
import fr.pharma.eclipse.domain.criteria.acteur.PromoteurSearchCriteria;
import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.model.acteur.ArcPromoteur;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.service.acteur.PersonneService;

/**
 * Classe en charge de tester la DAO Promoteur avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class PromoteurDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Promoteur.
     */
    private GenericDaoHibernate<Promoteur> promoteurDao;

    /**
     * Dictionnaire de critère de recherche.
     */
    @Resource(name = "criteriaDictionary")
    private CriteriaDictionaryImpl dictionary;

    /**
     * Service de gestion des ARC promoteur.
     */
    @Resource(name = "arcPromoteurService")
    private PersonneService<ArcPromoteur> arcPromoteurService;

    /**
     * Initialisation du test.
     */
    @Before
    public void init() {
        this.promoteurDao = new GenericDaoHibernate<Promoteur>(Promoteur.class);
        this.promoteurDao.setEntityManager(this.entityManager);
        this.promoteurDao.setCriteriaDictionary(this.dictionary);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.promoteurDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO Promoteur.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de Promoteur
        Promoteur promoteur = new Promoteur();
        promoteur.setIdentifiant("identifiant");
        promoteur.setRaisonSociale("raisonSociale");
        promoteur.setType(TypePromoteur.ACADEMIQUE);

        final SortedSet<ArcPromoteur> arcPromoteurs = new TreeSet<ArcPromoteur>(new BeanWithNomComparator());
        arcPromoteurs.add(this.arcPromoteurService.get(2L));
        promoteur.setArcPromoteurs(arcPromoteurs);

        promoteur = this.promoteurDao.save(promoteur);

        // Récupération de Promoteur
        final Promoteur promoteurReturn = this.promoteurDao.get(promoteur.getId());

        Assert.assertEquals(promoteur.getIdentifiant(), promoteurReturn.getIdentifiant());
        Assert.assertEquals(promoteur.getRaisonSociale(), promoteurReturn.getRaisonSociale());
        Assert.assertEquals(promoteur.getType().getLibelle(), promoteurReturn.getType().getLibelle());
        Assert.assertNotNull(promoteurReturn.getModifs());
        Assert.assertNotNull(promoteurReturn.getArcPromoteurs());

        // Suppression de Promoteur
        this.promoteurDao.remove(promoteur);
    }

    /**
     * Test de la récupération de tous les Promoteurs.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Promoteur
        final PromoteurSearchCriteria criteria = new PromoteurSearchCriteria();
        final Collection<Promoteur> promoteurs = this.promoteurDao.getAll(criteria);
        Assert.assertNotNull(promoteurs);
        for (final Promoteur promoteur : promoteurs) {
            Assert.assertNotNull(promoteur.getId());
            Assert.assertNotNull(promoteur.getRaisonSociale());
        }
    }

}
