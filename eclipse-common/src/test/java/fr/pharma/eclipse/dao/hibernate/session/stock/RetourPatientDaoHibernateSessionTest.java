package fr.pharma.eclipse.dao.hibernate.session.stock;

import java.util.Calendar;
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
import fr.pharma.eclipse.domain.criteria.stock.RetourPatientSearchCriteria;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.detail.DetailStockage;
import fr.pharma.eclipse.domain.model.stock.RetourPatient;
import fr.pharma.eclipse.service.acteur.PersonneService;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.patient.PatientService;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.utils.ContextSecurityHelper;

/**
 * Classe en charge de tester la DAO Retour patient avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class RetourPatientDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de RetourPatient.
     */
    private GenericDaoHibernate<RetourPatient> retourPatientDao;

    /**
     * Dictionnaire de critère de recherche.
     */
    @Resource(name = "criteriaDictionary")
    private CriteriaDictionaryImpl dictionary;

    /**
     * Service de gestion des essais.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * Service de gestion des produits.
     */
    @Resource(name = "medicamentService")
    private ProduitService<Medicament> produitService;

    /**
     * Service de gestion des personnes.
     */
    @Resource(name = "investigateurService")
    private PersonneService<Investigateur> personneService;

    /**
     * Service de gestion des conditionnements.
     */
    @Resource(name = "conditionnementService")
    private GenericService<Conditionnement> conditionnementService;

    /**
     * Service de gestion des patients.
     */
    @Resource(name = "patientService")
    private PatientService patientService;

    /**
     * Initialisation du test.
     */
    @Before
    public void init() {
        this.retourPatientDao = new GenericDaoHibernate<RetourPatient>(RetourPatient.class);
        this.retourPatientDao.setEntityManager(this.entityManager);
        this.retourPatientDao.setCriteriaDictionary(this.dictionary);
        ContextSecurityHelper.createSecurityContextMock();
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.retourPatientDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO RetourPatient.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de RetourPatient
        RetourPatient retourPatient = new RetourPatient();
        retourPatient.setEssai(this.essaiService.get(1L));
        retourPatient.setPersonne(this.personneService.get(1L));
        final Produit produit = this.produitService.get(1L);
        retourPatient.setProduit(produit);
        retourPatient.setConditionnement(this.conditionnementService.get(1L));
        retourPatient.setPatient(this.patientService.get(1L));
        final DetailStockage detail = produit.getDetailLogistique().getDetailsStockages().first();
        retourPatient.setDate(Calendar.getInstance());
        retourPatient.setQuantite(5);
        retourPatient.setDetailStockage(detail);

        retourPatient = this.retourPatientDao.save(retourPatient);

        // Récupération de RetourPatient
        final RetourPatient retourPatientReturn = this.retourPatientDao.get(retourPatient.getId());

        Assert.assertEquals(retourPatient.getEssai(), retourPatientReturn.getEssai());
        Assert.assertEquals(retourPatient.getProduit(), retourPatientReturn.getProduit());
        Assert.assertEquals(retourPatient.getPersonne(), retourPatientReturn.getPersonne());
        Assert.assertEquals(retourPatient.getConditionnement(), retourPatientReturn.getConditionnement());
        Assert.assertEquals(retourPatient.getPatient(), retourPatientReturn.getPatient());

        // Suppression de RetourPatient
        this.retourPatientDao.remove(retourPatient);
    }

    /**
     * Test de la récupération de tous les RetourPatients.
     */
    @Test
    public void getAllWithCriteria() {
        // Récupération de tous les RetourPatient
        final RetourPatientSearchCriteria criteria = new RetourPatientSearchCriteria();
        criteria.setActiveOrder("id");
        criteria.setEssai(this.essaiService.get(1L));
        criteria.setProduit(this.produitService.get(1L));
        criteria.setAscending(true);
        criteria.setCaseSensitiveOrder(true);
        final Collection<RetourPatient> retourPatients = this.retourPatientDao.getAll(criteria);

        Assert.assertNotNull(retourPatients);

        for (final RetourPatient retourPatient : retourPatients) {
            Assert.assertNotNull(retourPatient.getId());
        }
    }

    /**
     * Test de la récupération de tous les RetourPatients.
     */
    @Test
    public void getAll() {
        // Récupération de tous les RetourPatient
        final RetourPatientSearchCriteria criteria = new RetourPatientSearchCriteria();
        final Collection<RetourPatient> retourPatients = this.retourPatientDao.getAll(criteria);
        Assert.assertNotNull(retourPatients);
        for (final RetourPatient retourPatient : retourPatients) {
            Assert.assertNotNull(retourPatient.getId());
        }
    }

}
