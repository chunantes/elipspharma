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
import fr.pharma.eclipse.domain.model.acteur.Cro;

/**
 * Classe en charge de tester la DAO Cro avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class CroDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Cro.
     */
    private GenericDaoHibernate<Cro> croDao;

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
        this.croDao = new GenericDaoHibernate<Cro>(Cro.class);
        this.croDao.setEntityManager(this.entityManager);
        this.croDao.setCriteriaDictionary(this.dictionary);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.croDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO Cro.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de Cro
        Cro cro = new Cro();
        cro.setNom("nom");
        cro.setPrenom("prenom");
        cro.setTelephone("0987654345");
        cro.setMail("prenom.nom@eclipse.fr");
        cro.setFax("0987654345");
        cro.setNomSociete("nomSociete");
        cro.setLogin("login");
        cro.setPassword("password");
        cro.setIsAdmin(Boolean.FALSE);

        cro = this.croDao.save(cro);

        // Récupération de Cro
        final Cro croReturn = this.croDao.get(cro.getId());

        Assert.assertEquals(cro.getNom(), croReturn.getNom());
        Assert.assertEquals(cro.getPrenom(), croReturn.getPrenom());
        Assert.assertEquals(cro.getTelephone(), croReturn.getTelephone());
        Assert.assertEquals(cro.getMail(), croReturn.getMail());
        Assert.assertEquals(cro.getFax(), croReturn.getFax());
        Assert.assertEquals(cro.getNomSociete(), croReturn.getNomSociete());
        Assert.assertEquals(cro.getLogin(), croReturn.getLogin());
        Assert.assertEquals(cro.getPassword(), croReturn.getPassword());
        Assert.assertEquals(cro.getIsAdmin(), croReturn.getIsAdmin());
        Assert.assertNotNull(croReturn.getModifs());

        // Suppression de Cro
        this.croDao.remove(cro);
    }

    /**
     * Test de la récupération de tous les Cros.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Cro
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        final Collection<Cro> cros = this.croDao.getAll(criteria);
        Assert.assertNotNull(cros);
        for (final Cro cro : cros) {
            Assert.assertNotNull(cro.getId());
            Assert.assertNotNull(cro.getNom());
        }
    }

}
