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
import fr.pharma.eclipse.domain.model.acteur.DirectionRecherche;

/**
 * Classe en charge de tester la DAO DirectionRecherche avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class DirectionRechercheDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de DirectionRecherche.
     */
    private GenericDaoHibernate<DirectionRecherche> directionRechercheDao;

    /**
     * Dictionnaire de critère de recherche.
     */
    @Resource(name = "criteriaDictionary")
    private CriteriaDictionaryImpl dictionary;

    /**
     * Initialisation du test.
     */
    @Before
    public void init() {
        this.directionRechercheDao = new GenericDaoHibernate<DirectionRecherche>(DirectionRecherche.class);
        this.directionRechercheDao.setEntityManager(this.entityManager);
        this.directionRechercheDao.setCriteriaDictionary(this.dictionary);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.directionRechercheDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO DirectionRecherche.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de DirectionRecherche
        DirectionRecherche directionRecherche = new DirectionRecherche();
        directionRecherche.setNom("nom");
        directionRecherche.setPrenom("prenom");
        directionRecherche.setTelephone("0987654345");
        directionRecherche.setMail("prenom.nom@eclipse.fr");
        directionRecherche.setFax("0987654345");
        directionRecherche.setLogin("login");
        directionRecherche.setIsAdmin(Boolean.FALSE);

        directionRecherche = this.directionRechercheDao.save(directionRecherche);

        // Récupération de DirectionRecherche
        final DirectionRecherche directionRechercheReturn = this.directionRechercheDao.get(directionRecherche.getId());

        Assert.assertEquals(directionRecherche.getNom(), directionRechercheReturn.getNom());
        Assert.assertEquals(directionRecherche.getPrenom(), directionRechercheReturn.getPrenom());
        Assert.assertEquals(directionRecherche.getTelephone(), directionRechercheReturn.getTelephone());
        Assert.assertEquals(directionRecherche.getMail(), directionRechercheReturn.getMail());
        Assert.assertEquals(directionRecherche.getFax(), directionRechercheReturn.getFax());
        Assert.assertEquals(directionRecherche.getLogin(), directionRechercheReturn.getLogin());
        Assert.assertEquals(directionRecherche.getIsAdmin(), directionRechercheReturn.getIsAdmin());
        Assert.assertNotNull(directionRechercheReturn.getModifs());

        // Suppression de DirectionRecherche
        this.directionRechercheDao.remove(directionRecherche);
    }

    /**
     * Test de la récupération de tous les DirectionRecherches.
     */
    @Test
    public void getAll() {
        // Récupération de tous les DirectionRecherche
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        final Collection<DirectionRecherche> directionRecherches = this.directionRechercheDao.getAll(criteria);
        Assert.assertNotNull(directionRecherches);
        for (final DirectionRecherche directionRecherche : directionRecherches) {
            Assert.assertNotNull(directionRecherche.getId());
            Assert.assertNotNull(directionRecherche.getNom());
        }
    }

}
