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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.comparator.stockage.StockageComparator;
import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.dictionary.impl.CriteriaDictionaryImpl;
import fr.pharma.eclipse.domain.criteria.stockage.StockageSearchCriteria;
import fr.pharma.eclipse.domain.enums.ConditionConservation;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.service.stockage.PharmacieService;

/**
 * Classe en charge de tester la DAO Stockage avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class StockageDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Stockage.
     */
    private GenericDaoHibernate<Stockage> stockageDao;

    /**
     * Service de gestion des pharmacies.
     */
    @Resource(name = "pharmacieService")
    private PharmacieService pharmacieService;

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
        this.stockageDao = new GenericDaoHibernate<Stockage>(Stockage.class);
        this.stockageDao.setEntityManager(this.entityManager);
        this.stockageDao.setCriteriaDictionary(this.dictionary);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.stockageDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO Stockage.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de Stockage
        Stockage parent = new Stockage();
        parent.setNom("nom");
        parent.setPharmacie(this.pharmacieService.get(1L));
        parent.setIdentifiantStockage("identifiantStockage");
        parent.setIdentifiantSondeTemp("identifiantSondeTemp");
        parent.setIdentifiantEnregistreurTemp("identifiantEnregistreurTemp");
        parent.setConservation(ConditionConservation.INF_25);
        parent = this.stockageDao.save(parent);

        final SortedSet<Stockage> enfants = new TreeSet<Stockage>(new StockageComparator());
        final Stockage enfant1 = new Stockage();
        enfant1.setNom("enfant1");
        enfant1.setParent(parent);
        enfant1.setConservation(ConditionConservation.MOINS_80);
        enfants.add(enfant1);
        final Stockage enfant2 = new Stockage();
        enfant2.setNom("enfant2");
        enfant2.setParent(parent);
        enfants.add(enfant2);

        parent.setEnfants(enfants);

        // Récupération de Stockage
        final Stockage stockageReturn = this.stockageDao.get(parent.getId());

        Assert.assertEquals(parent.getNom(), stockageReturn.getNom());
        Assert.assertEquals(parent.getIdentifiantStockage(), stockageReturn.getIdentifiantStockage());
        Assert.assertEquals(parent.getConservation(), stockageReturn.getConservation());
        Assert.assertEquals(parent.getIdentifiantSondeTemp(), stockageReturn.getIdentifiantSondeTemp());
        Assert.assertEquals(parent.getIdentifiantEnregistreurTemp(), stockageReturn.getIdentifiantEnregistreurTemp());
        Assert.assertEquals(parent.getPharmacie(), stockageReturn.getPharmacie());
        Assert.assertEquals(enfants.size(), parent.getEnfants().size());
        Assert.assertEquals(parent, enfant1.getParent());
        Assert.assertEquals(parent, enfant2.getParent());

        // Suppression de Stockage
        this.stockageDao.remove(parent);
    }

    /**
     * Test de la récupération de tous les Stockages.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Stockage
        final StockageSearchCriteria criteria = new StockageSearchCriteria();
        criteria.setActiveOrder("nom");
        final Collection<Stockage> stockages = this.stockageDao.getAll(criteria);

        Assert.assertNotNull(stockages);

        for (final Stockage stockage : stockages) {
            Assert.assertNotNull(stockage.getId());
            Assert.assertNotNull(stockage.getNom());
        }
    }

    /**
     * Setter pour pharmacieService.
     * @param pharmacieService Le pharmacieService à écrire.
     */
    public void setPharmacieService(final PharmacieService pharmacieService) {
        this.pharmacieService = pharmacieService;
    }

}
