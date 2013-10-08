package fr.pharma.eclipse.dao.sir.hibernate.session;

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

import fr.pharma.eclipse.dao.sir.hibernate.GenericSirDaoHibernate;
import fr.pharma.eclipse.dictionary.impl.CriteriaDictionaryImpl;
import fr.pharma.eclipse.domain.criteria.sir.PersonneSirSearchCriteria;
import fr.pharma.eclipse.domain.model.sir.PersonneSir;

/**
 * Classe en charge de tester la DAO Personne Sir avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext.xml" })
@Transactional
public class PersonneSirDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "sir")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Personne Sir.
     */
    private GenericSirDaoHibernate<PersonneSir> personneSirDao;

    /**
     * Identifiant technique d'une personne présente dans SIR.
     */
    private final Integer ID_USER_SIR = 1068;

    /**
     * Dictionnaire de critère de recherche.
     */
    @Resource(name = "sirCriteriaDictionary")
    private CriteriaDictionaryImpl dictionary;

    /**
     * Initialisation du test.
     */
    @Before
    public void init() {
        this.personneSirDao = new GenericSirDaoHibernate<PersonneSir>(PersonneSir.class);
        this.personneSirDao.setEntityManager(this.entityManager);
        this.personneSirDao.setCriteriaDictionary(this.dictionary);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.personneSirDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO Personne Sir.
     */
    @Test
    public void get() {
        final PersonneSir personneSir = this.personneSirDao.get(this.ID_USER_SIR);
        Assert.assertNotNull(personneSir.getId());
        Assert.assertNotNull(personneSir.getNom());
        Assert.assertNotNull(personneSir.getPrenom());
    }

    /**
     * Test de la récupération de tous les PersonneSir avec une défintion de
     * critère de recherche.
     */
    @Test
    public void getAllWithCriteria() {
        // Récupération de tous les PersonneSir
        final PersonneSirSearchCriteria criteria = new PersonneSirSearchCriteria();
        criteria.setActiveOrder("nom");
        criteria.setLogin("helene.lefranc");
        criteria.setNom("LEFRANC");
        criteria.setPrenom("Hélène");
        criteria.setAscending(true);
        criteria.setCaseSensitiveOrder(true);
        final Collection<PersonneSir> personnesSir = this.personneSirDao.getAll(criteria);

        Assert.assertNotNull(personnesSir);

        for (final PersonneSir personneSir : personnesSir) {
            Assert.assertNotNull(personneSir.getId());
            Assert.assertNotNull(personneSir.getNom());
            Assert.assertNotNull(personneSir.getPrenom());
        }
    }

    /**
     * Test de la récupération de tous les PersonneSir avec une défintion de
     * critère de recherche.
     */
    @Test
    public void getAllWithCriteriaLoginNotStrict() {
        // Récupération de tous les PersonneSir
        final PersonneSirSearchCriteria criteria = new PersonneSirSearchCriteria();
        criteria.setActiveOrder("nom");
        criteria.setLogin("helene.lefranc");
        criteria.setStrictSearchLogin(Boolean.FALSE);
        criteria.setNom("LEFRANC");
        criteria.setPrenom("Hélène");
        criteria.setAscending(true);
        criteria.setCaseSensitiveOrder(true);
        final Collection<PersonneSir> personnesSir = this.personneSirDao.getAll(criteria);

        Assert.assertNotNull(personnesSir);

        for (final PersonneSir personneSir : personnesSir) {
            Assert.assertNotNull(personneSir.getId());
            Assert.assertNotNull(personneSir.getNom());
            Assert.assertNotNull(personneSir.getPrenom());
        }
    }

    /**
     * Test de la récupération de tous les PersonneSir avec une défintion de
     * critère de recherche.
     */
    @Test
    public void getAllWithCriteriaLoginStrict() {
        // Récupération de tous les PersonneSir
        final PersonneSirSearchCriteria criteria = new PersonneSirSearchCriteria();
        criteria.setActiveOrder("nom");
        criteria.setLogin("helene.lefranc");
        criteria.setStrictSearchLogin(Boolean.TRUE);
        criteria.setNom("LEFRANC");
        criteria.setPrenom("Hélène");
        criteria.setAscending(true);
        criteria.setCaseSensitiveOrder(true);
        final Collection<PersonneSir> personnesSir = this.personneSirDao.getAll(criteria);

        Assert.assertNotNull(personnesSir);

        for (final PersonneSir personneSir : personnesSir) {
            Assert.assertNotNull(personneSir.getId());
            Assert.assertNotNull(personneSir.getNom());
            Assert.assertNotNull(personneSir.getPrenom());
        }
    }

    /**
     * Test de la récupération de tous les PersonneSir.
     */
    @Test
    public void getAll() {
        // Récupération de tous les PersonneSir
        final PersonneSirSearchCriteria criteria = new PersonneSirSearchCriteria();
        criteria.setActiveOrder("nom");
        final Collection<PersonneSir> personnesSir = this.personneSirDao.getAll(criteria);

        Assert.assertNotNull(personnesSir);

        for (final PersonneSir personneSir : personnesSir) {
            Assert.assertNotNull(personneSir.getId());
            Assert.assertNotNull(personneSir.getNom());
            Assert.assertNotNull(personneSir.getPrenom());
        }
    }

}
