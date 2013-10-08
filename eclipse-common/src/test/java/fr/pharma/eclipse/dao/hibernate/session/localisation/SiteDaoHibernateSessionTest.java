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
import fr.pharma.eclipse.domain.criteria.localisation.SiteSearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Site;
import fr.pharma.eclipse.service.localisation.EtablissementService;

/**
 * Classe en charge de tester la DAO Site avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class SiteDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Site.
     */
    private GenericDaoHibernate<Site> siteDao;

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
        this.siteDao = new GenericDaoHibernate<Site>(Site.class);
        this.siteDao.setEntityManager(this.entityManager);
        this.siteDao.setCriteriaDictionary(this.dictionary);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.siteDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO Site.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de Site
        Site site = new Site();
        site.setNom("nom");
        site.setCode("code");
        site.setEtablissement(this.etablissementService.get(1L));
        site.setAdresse("adresse");
        site.setCodePostal("codePostal");
        site.setVille("ville");
        site = this.siteDao.save(site);

        // Récupération de Site
        final Site siteReturn = this.siteDao.get(site.getId());

        Assert.assertEquals(site.getNom(), siteReturn.getNom());
        Assert.assertEquals(site.getCode(), siteReturn.getCode());
        Assert.assertEquals(site.getEtablissement(), siteReturn.getEtablissement());
        Assert.assertEquals(site.getAdresse(), siteReturn.getAdresse());
        Assert.assertEquals(site.getCodePostal(), siteReturn.getCodePostal());
        Assert.assertEquals(site.getVille(), siteReturn.getVille());
        Assert.assertNotNull(siteReturn.getModifs());
        Assert.assertNotNull(siteReturn.getPharmacies());

        // Suppression de Site
        this.siteDao.remove(site);
    }

    /**
     * Test de la récupération de tous les Sites.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Site
        final SiteSearchCriteria criteria = new SiteSearchCriteria();
        final Collection<Site> sites = this.siteDao.getAll(criteria);

        Assert.assertNotNull(sites);

        for (final Site site : sites) {
            Assert.assertNotNull(site.getId());
            Assert.assertNotNull(site.getNom());
        }
    }

}
