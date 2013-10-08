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
import fr.pharma.eclipse.domain.criteria.acteur.PersonneSearchCriteria;
import fr.pharma.eclipse.domain.criteria.acteur.PharmacienSearchCriteria;
import fr.pharma.eclipse.domain.enums.TypePharmacien;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.stockage.PharmacieService;

/**
 * Classe en charge de tester la DAO Pharmacien avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class PharmacienDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Pharmacien.
     */
    private GenericDaoHibernate<Pharmacien> pharmacienDao;

    /**
     * Dictionnaire de critère de recherche.
     */
    @Resource(name = "criteriaDictionary")
    private CriteriaDictionaryImpl dictionary;

    /**
     * Service de gestion des pharmacies.
     */
    @Resource(name = "pharmacieService")
    private PharmacieService pharmacieService;

    /**
     * Initialisation du test.
     */
    @Before
    public void init() {
        this.pharmacienDao = new GenericDaoHibernate<Pharmacien>(Pharmacien.class);
        this.pharmacienDao.setEntityManager(this.entityManager);
        this.pharmacienDao.setCriteriaDictionary(this.dictionary);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.pharmacienDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO Pharmacien.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de Pharmacien
        Pharmacien pharmacien = new Pharmacien();
        pharmacien.setNom("nom");
        pharmacien.setPrenom("prenom");
        pharmacien.setTelephone("0987654345");
        pharmacien.setMail("prenom.nom@eclipse.fr");
        pharmacien.setFax("0987654345");
        pharmacien.setLogin("login");
        pharmacien.setIsAdmin(Boolean.TRUE);
        pharmacien.setTypePharmacien(TypePharmacien.TITULAIRE);

        final SortedSet<Pharmacie> pharmacies = new TreeSet<Pharmacie>(new BeanWithNomComparator());
        pharmacies.add(this.pharmacieService.get(1L));
        pharmacies.add(this.pharmacieService.get(2L));
        pharmacien.setPharmacies(pharmacies);

        pharmacien = this.pharmacienDao.save(pharmacien);

        // Récupération de Pharmacien
        final Pharmacien pharmacienReturn = this.pharmacienDao.get(pharmacien.getId());

        Assert.assertEquals(pharmacien.getNom(), pharmacienReturn.getNom());
        Assert.assertEquals(pharmacien.getPrenom(), pharmacienReturn.getPrenom());
        Assert.assertEquals(pharmacien.getTelephone(), pharmacienReturn.getTelephone());
        Assert.assertEquals(pharmacien.getMail(), pharmacienReturn.getMail());
        Assert.assertEquals(pharmacien.getFax(), pharmacienReturn.getFax());
        Assert.assertEquals(pharmacien.getLogin(), pharmacienReturn.getLogin());
        Assert.assertEquals(pharmacien.getIsAdmin(), pharmacienReturn.getIsAdmin());
        Assert.assertEquals(pharmacien.getTypePharmacien(), pharmacienReturn.getTypePharmacien());
        Assert.assertNotNull(pharmacienReturn.getModifs());
        Assert.assertNotNull(pharmacienReturn.getPharmacies());

        // Suppression de Pharmacien
        this.pharmacienDao.remove(pharmacien);
    }

    /**
     * Test de la récupération des pharmaciens avec critère.
     */
    @Test
    public void getAllWithCriteria() {
        // Récupération de tous les Pharmacien
        final PharmacienSearchCriteria criteria = new PharmacienSearchCriteria();
        criteria.setActiveOrder("nom");
        criteria.setTypePharmacien(TypePharmacien.TITULAIRE);
        final Pharmacie pharmacie = this.pharmacieService.get(1L);
        criteria.setPharmacie(pharmacie);
        criteria.setAscending(true);
        criteria.setCaseSensitiveOrder(true);
        final Collection<Pharmacien> pharmaciens = this.pharmacienDao.getAll(criteria);

        Assert.assertNotNull(pharmaciens);

        for (final Pharmacien pharmacien : pharmaciens) {
            Assert.assertNotNull(pharmacien.getId());
            Assert.assertNotNull(pharmacien.getNom());
            Assert.assertEquals(criteria.getTypePharmacien(), pharmacien.getTypePharmacien());
        }
    }

    /**
     * Test de la récupération de tous les Pharmaciens.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Pharmacien
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        final Collection<Pharmacien> pharmaciens = this.pharmacienDao.getAll(criteria);
        Assert.assertNotNull(pharmaciens);
        for (final Pharmacien pharmacien : pharmaciens) {
            Assert.assertNotNull(pharmacien.getId());
            Assert.assertNotNull(pharmacien.getNom());
        }
    }

}
