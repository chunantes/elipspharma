package fr.pharma.eclipse.dao.hibernate.session.localisation;

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
import fr.pharma.eclipse.domain.criteria.localisation.PoleSearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Pole;
import fr.pharma.eclipse.service.localisation.EtablissementService;

/**
 * Classe en charge de tester la DAO Pole avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class PoleDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Pole.
     */
    private GenericDaoHibernate<Pole> poleDao;

    /**
     * Dictionnaire de critère de recherche.
     */
    @Resource(name = "criteriaDictionary")
    private CriteriaDictionaryImpl dictionary;

    /**
     * Service de gestion des établissements.
     */
    @Resource(name = "etablissementService")
    private EtablissementService etablissementService;

    /**
     * Initialisation du test.
     */
    @Before
    public void init() {
        this.poleDao = new GenericDaoHibernate<Pole>(Pole.class);
        this.poleDao.setEntityManager(this.entityManager);
        this.poleDao.setCriteriaDictionary(this.dictionary);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.poleDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO Pole.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de Pole
        Pole pole = new Pole();
        pole.setNom("nom");
        pole.setEtablissement(this.etablissementService.get(1L));
        pole = this.poleDao.save(pole);

        // Récupération de Pole
        final Pole poleReturn = this.poleDao.get(pole.getId());

        Assert.assertEquals(pole.getNom(), poleReturn.getNom());
        Assert.assertEquals(pole.getEtablissement(), poleReturn.getEtablissement());
        Assert.assertNotNull(poleReturn.getModifs());

        // Suppression de Pole
        this.poleDao.remove(pole);
    }

    /**
     * Test de la récupération de tous les Poles.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Pole
        final PoleSearchCriteria criteria = new PoleSearchCriteria();
        final Collection<Pole> poles = this.poleDao.getAll(criteria);

        Assert.assertNotNull(poles);

        for (final Pole pole : poles) {
            Assert.assertNotNull(pole.getId());
            Assert.assertNotNull(pole.getNom());
        }
    }

}
