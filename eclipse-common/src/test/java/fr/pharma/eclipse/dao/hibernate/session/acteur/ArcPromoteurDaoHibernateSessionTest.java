package fr.pharma.eclipse.dao.hibernate.session.acteur;

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
import fr.pharma.eclipse.domain.criteria.acteur.PersonneSearchCriteria;
import fr.pharma.eclipse.domain.model.acteur.ArcPromoteur;
import fr.pharma.eclipse.service.acteur.PromoteurService;

/**
 * Classe en charge de tester la DAO ArcPromoteur avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class ArcPromoteurDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de ArcPromoteur.
     */
    private GenericDaoHibernate<ArcPromoteur> arcPromoteurDao;

    /**
     * Dictionnaire de critère de recherche.
     */
    @Resource(name = "criteriaDictionary")
    private CriteriaDictionaryImpl dictionary;

    /**
     * Service de gestion des promoteurs.
     */
    @Resource(name = "promoteurService")
    private PromoteurService promoteurService;

    /**
     * Initialisation du test.
     */
    @Before
    public void init() {
        this.arcPromoteurDao = new GenericDaoHibernate<ArcPromoteur>(ArcPromoteur.class);
        this.arcPromoteurDao.setEntityManager(this.entityManager);
        this.arcPromoteurDao.setCriteriaDictionary(this.dictionary);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.arcPromoteurDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO ArcPromoteur.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de ArcPromoteur
        ArcPromoteur arcPromoteur = new ArcPromoteur();
        arcPromoteur.setNom("nom");
        arcPromoteur.setPrenom("prenom");
        arcPromoteur.setTelephone("0987654345");
        arcPromoteur.setMail("prenom.nom@eclipse.fr");
        arcPromoteur.setFax("0987654345");
        arcPromoteur.setPromoteur(this.promoteurService.get(1L));
        arcPromoteur.setLogin("login");
        arcPromoteur.setPassword("password");
        arcPromoteur.setIsAdmin(Boolean.FALSE);

        arcPromoteur = this.arcPromoteurDao.save(arcPromoteur);

        // Récupération de ArcPromoteur
        final ArcPromoteur arcPromoteurReturn = this.arcPromoteurDao.get(arcPromoteur.getId());

        Assert.assertEquals(arcPromoteur.getNom(), arcPromoteurReturn.getNom());
        Assert.assertEquals(arcPromoteur.getPrenom(), arcPromoteurReturn.getPrenom());
        Assert.assertEquals(arcPromoteur.getPromoteur(), arcPromoteurReturn.getPromoteur());
        Assert.assertEquals(arcPromoteur.getTelephone(), arcPromoteurReturn.getTelephone());
        Assert.assertEquals(arcPromoteur.getMail(), arcPromoteurReturn.getMail());
        Assert.assertEquals(arcPromoteur.getFax(), arcPromoteurReturn.getFax());
        Assert.assertEquals(arcPromoteur.getLogin(), arcPromoteurReturn.getLogin());
        Assert.assertEquals(arcPromoteur.getPassword(), arcPromoteurReturn.getPassword());
        Assert.assertEquals(arcPromoteur.getIsAdmin(), arcPromoteurReturn.getIsAdmin());
        Assert.assertNotNull(arcPromoteurReturn.getModifs());

        // Suppression de ArcPromoteur
        this.arcPromoteurDao.remove(arcPromoteur);
    }

    /**
     * Test de la récupération de tous les ArcPromoteurs.
     */
    @Test
    public void getAll() {
        // Récupération de tous les ArcPromoteur
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        final Collection<ArcPromoteur> arcPromoteurs = this.arcPromoteurDao.getAll(criteria);
        Assert.assertNotNull(arcPromoteurs);
        for (final ArcPromoteur arcPromoteur : arcPromoteurs) {
            Assert.assertNotNull(arcPromoteur.getId());
            Assert.assertNotNull(arcPromoteur.getNom());
        }
    }

}
