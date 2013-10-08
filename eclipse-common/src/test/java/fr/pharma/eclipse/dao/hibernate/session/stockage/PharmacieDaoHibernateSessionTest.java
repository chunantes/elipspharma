package fr.pharma.eclipse.dao.hibernate.session.stockage;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.comparator.stockage.StockageComparator;
import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.dictionary.impl.CriteriaDictionaryImpl;
import fr.pharma.eclipse.domain.criteria.stockage.PharmacieSearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Site;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.service.localisation.EtablissementService;
import fr.pharma.eclipse.service.localisation.SiteService;
import fr.pharma.eclipse.service.stockage.StockageService;
import fr.pharma.eclipse.utils.ContextSecurityHelper;

/**
 * Classe en charge de tester la DAO Pharmacie avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class PharmacieDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Pharmacie.
     */
    private GenericDaoHibernate<Pharmacie> pharmacieDao;

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
     * Service de gestion des sites.
     */
    @Resource(name = "siteService")
    private SiteService siteService;

    /**
     * Service de gestion des stockages.
     */
    @Resource(name = "stockageService")
    private StockageService stockageService;

    /**
     * Initialisation du test.
     */
    @Before
    public void init() {
        this.pharmacieDao = new GenericDaoHibernate<Pharmacie>(Pharmacie.class);
        this.pharmacieDao.setEntityManager(this.entityManager);
        this.pharmacieDao.setCriteriaDictionary(this.dictionary);
        ContextSecurityHelper.createSecurityContextMock();
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.pharmacieDao = null;
        this.entityManager = null;
        this.dictionary = null;
        SecurityContextHolder.clearContext();
    }

    /**
     * Test des méthodes de DAO Pharmacie.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de Pharmacie
        Pharmacie pharmacie = new Pharmacie();
        pharmacie.setNom("nom");
        pharmacie.setAdresse("adresse");
        pharmacie.setAdresseLivraison("adresseLivraison");
        pharmacie.setTelephone("telephone");
        pharmacie.setFax("fax");
        pharmacie.setResponsablePrincipal("responsablePrincipal");
        pharmacie.setEtablissement(this.etablissementService.get(1L));
        pharmacie.setNumOrdonnancierDisp(0);
        pharmacie.setNumOrdonnancierFab(0);

        final SortedSet<Site> sites = new TreeSet<Site>(new BeanWithNomComparator());
        sites.add(this.siteService.get(1L));
        sites.add(this.siteService.get(2L));
        pharmacie.setSites(sites);

        final SortedSet<Stockage> stockages = new TreeSet<Stockage>(new StockageComparator());
        stockages.add(this.stockageService.get(1L));
        pharmacie.setStockages(stockages);

        pharmacie = this.pharmacieDao.save(pharmacie);

        // Récupération de Pharmacie
        final Pharmacie pharmacieReturn = this.pharmacieDao.get(pharmacie.getId());

        Assert.assertEquals(pharmacie.getNom(), pharmacieReturn.getNom());
        Assert.assertEquals(pharmacie.getAdresse(), pharmacieReturn.getAdresse());
        Assert.assertEquals(pharmacie.getAdresseLivraison(), pharmacieReturn.getAdresseLivraison());
        Assert.assertEquals(pharmacie.getTelephone(), pharmacieReturn.getTelephone());
        Assert.assertEquals(pharmacie.getFax(), pharmacieReturn.getFax());
        Assert.assertEquals(pharmacie.getEtablissement(), pharmacieReturn.getEtablissement());
        Assert.assertEquals(pharmacie.getResponsablePrincipal(), pharmacieReturn.getResponsablePrincipal());
        Assert.assertEquals(pharmacie.getNumOrdonnancierDisp(), pharmacieReturn.getNumOrdonnancierDisp());
        Assert.assertEquals(pharmacie.getNumOrdonnancierFab(), pharmacieReturn.getNumOrdonnancierFab());
        Assert.assertNotNull(pharmacieReturn.getModifs());
        Assert.assertNotNull(pharmacieReturn.getSites());
        Assert.assertNotNull(pharmacieReturn.getDetailsDonneesPharma());
        Assert.assertNotNull(pharmacieReturn.getPharmaciens());
        Assert.assertNotNull(pharmacieReturn.getStockages());

        // Suppression de Pharmacie
        this.pharmacieDao.remove(pharmacie);
    }

    /**
     * Test de la récupération de tous les Pharmacies.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Pharmacie
        final PharmacieSearchCriteria criteria = new PharmacieSearchCriteria();
        criteria.setActiveOrder("nom");
        final Collection<Pharmacie> pharmacies = this.pharmacieDao.getAll(criteria);

        Assert.assertNotNull(pharmacies);

        for (final Pharmacie pharmacie : pharmacies) {
            Assert.assertNotNull(pharmacie.getId());
            Assert.assertNotNull(pharmacie.getNom());
        }
    }

    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final Pharmacie pharmacie = this.pharmacieDao.get(1L);
        Assert.assertNotNull(pharmacie.toString());
    }

}
