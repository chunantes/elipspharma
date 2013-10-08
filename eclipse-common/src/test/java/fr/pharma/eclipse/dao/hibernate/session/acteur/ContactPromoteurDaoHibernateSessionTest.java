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
import fr.pharma.eclipse.domain.criteria.acteur.ContactPromoteurSearchCriteria;
import fr.pharma.eclipse.domain.model.acteur.ContactPromoteur;
import fr.pharma.eclipse.service.acteur.PromoteurService;

/**
 * Classe en charge de tester la DAO ContactPromoteur avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class ContactPromoteurDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de ContactPromoteur.
     */
    private GenericDaoHibernate<ContactPromoteur> contactPromoteurDao;

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
        this.contactPromoteurDao = new GenericDaoHibernate<ContactPromoteur>(ContactPromoteur.class);
        this.contactPromoteurDao.setEntityManager(this.entityManager);
        this.contactPromoteurDao.setCriteriaDictionary(this.dictionary);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.contactPromoteurDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO ContactPromoteur.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de ContactPromoteur
        ContactPromoteur contactPromoteur = new ContactPromoteur();
        contactPromoteur.setNom("nom");
        contactPromoteur.setPrenom("prenom");
        contactPromoteur.setTelephone("0987654345");
        contactPromoteur.setMail("prenom.nom@eclipse.fr");
        contactPromoteur.setFax("0987654345");
        contactPromoteur.setPromoteur(this.promoteurService.get(1L));
        contactPromoteur.setLogin("login");
        contactPromoteur.setIsAdmin(Boolean.FALSE);
        contactPromoteur.setAdresse("adresse");
        contactPromoteur.setCodePostal("codePostal");
        contactPromoteur.setVille("ville");
        contactPromoteur.setPassword("password");

        contactPromoteur = this.contactPromoteurDao.save(contactPromoteur);

        // Récupération de ContactPromoteur
        final ContactPromoteur contactPromoteurReturn = this.contactPromoteurDao.get(contactPromoteur.getId());

        Assert.assertEquals(contactPromoteur.getNom(), contactPromoteurReturn.getNom());
        Assert.assertEquals(contactPromoteur.getPrenom(), contactPromoteurReturn.getPrenom());
        Assert.assertEquals(contactPromoteur.getPromoteur(), contactPromoteurReturn.getPromoteur());
        Assert.assertEquals(contactPromoteur.getTelephone(), contactPromoteurReturn.getTelephone());
        Assert.assertEquals(contactPromoteur.getMail(), contactPromoteurReturn.getMail());
        Assert.assertEquals(contactPromoteur.getFax(), contactPromoteurReturn.getFax());
        Assert.assertEquals(contactPromoteur.getLogin(), contactPromoteurReturn.getLogin());
        Assert.assertEquals(contactPromoteur.getIsAdmin(), contactPromoteurReturn.getIsAdmin());
        Assert.assertEquals(contactPromoteur.getAdresse(), contactPromoteurReturn.getAdresse());
        Assert.assertEquals(contactPromoteur.getCodePostal(), contactPromoteurReturn.getCodePostal());
        Assert.assertEquals(contactPromoteur.getVille(), contactPromoteurReturn.getVille());
        Assert.assertEquals(contactPromoteur.getPassword(), contactPromoteurReturn.getPassword());
        Assert.assertNotNull(contactPromoteurReturn.getModifs());
        Assert.assertNotNull(contactPromoteurReturn.getHabilitations());

        // Suppression de ContactPromoteur
        this.contactPromoteurDao.remove(contactPromoteur);
    }

    /**
     * Test de la récupération de tous les ContactPromoteurs.
     */
    @Test
    public void getAll() {
        // Récupération de tous les ContactPromoteur
        final ContactPromoteurSearchCriteria criteria = new ContactPromoteurSearchCriteria();
        final Collection<ContactPromoteur> contactPromoteurs = this.contactPromoteurDao.getAll(criteria);
        Assert.assertNotNull(contactPromoteurs);
        for (final ContactPromoteur contactPromoteur : contactPromoteurs) {
            Assert.assertNotNull(contactPromoteur.getId());
            Assert.assertNotNull(contactPromoteur.getNom());
        }
    }

}
