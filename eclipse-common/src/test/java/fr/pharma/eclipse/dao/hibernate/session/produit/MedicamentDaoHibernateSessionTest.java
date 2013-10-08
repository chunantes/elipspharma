package fr.pharma.eclipse.dao.hibernate.session.produit;

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
import fr.pharma.eclipse.domain.criteria.produit.ProduitSearchCriteria;
import fr.pharma.eclipse.domain.enums.produit.NatureProduit;
import fr.pharma.eclipse.domain.enums.produit.TypeProduit;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.service.produit.ProduitService;

/**
 * Classe en charge de tester la DAO Medicament avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class MedicamentDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Medicament.
     */
    private GenericDaoHibernate<Medicament> medicamentDao;

    /**
     * Dictionnaire de critère de recherche.
     */
    @Resource(name = "criteriaDictionary")
    private CriteriaDictionaryImpl dictionary;

    /**
     * Service de gestion des médicaments.
     */
    @Resource(name = "medicamentService")
    private ProduitService<Medicament> produitService;

    /**
     * Initialisation du test.
     */
    @Before
    public void init() {
        this.medicamentDao = new GenericDaoHibernate<Medicament>(Medicament.class);
        this.medicamentDao.setEntityManager(this.entityManager);
        this.medicamentDao.setCriteriaDictionary(this.dictionary);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.medicamentDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO Medicament.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de Medicament
        Medicament medicament = this.produitService.get(1L);
        medicament.setType(TypeProduit.DISPOSITIF_MEDICAL);
        medicament.setClasseTherapeutique("class");
        medicament.setCode("code");
        medicament.setDenomination("denomination");
        medicament.setMds(true);
        medicament.setNature(NatureProduit.PRODUIT_ASSOCIE);
        medicament.setStupefiant(true);
        medicament.setAlerteActive(Boolean.TRUE);
        medicament = this.medicamentDao.save(medicament);

        // Récupération de Medicament
        final Medicament mediReturn = this.medicamentDao.get(medicament.getId());

        Assert.assertEquals(medicament.getClasseTherapeutique(), mediReturn.getClasseTherapeutique());
        Assert.assertEquals(medicament.getCode(), mediReturn.getCode());
        Assert.assertEquals(medicament.getDenomination(), mediReturn.getDenomination());
        Assert.assertEquals(medicament.getMds(), mediReturn.getMds());
        Assert.assertEquals(medicament.getNature(), mediReturn.getNature());
        Assert.assertEquals(medicament.getStupefiant(), mediReturn.getStupefiant());
        Assert.assertEquals(medicament.getType(), mediReturn.getType());
        Assert.assertNotNull(mediReturn.getModifs());

        // Suppression de Medicament
        this.medicamentDao.remove(medicament);
    }

    /**
     * Test de la récupération de tous les Medicaments.
     */
    @Test
    public void getAllWithCriteria() {
        // Récupération de tous les Medicament
        final ProduitSearchCriteria criteria = new ProduitSearchCriteria();
        criteria.setActiveOrder("denomination");
        criteria.setAscending(true);
        criteria.setTypeProduit(TypeProduit.DISPOSITIF_MEDICAL);
        criteria.setCaseSensitiveOrder(true);
        final Collection<Medicament> medicaments = this.medicamentDao.getAll(criteria);

        Assert.assertNotNull(medicaments);

        for (final Medicament medocs : medicaments) {
            Assert.assertNotNull(medocs.getId());
        }
    }

    /**
     * Test de la récupération de tous les Medicaments.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Medicament
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        final Collection<Medicament> medicaments = this.medicamentDao.getAll(criteria);
        Assert.assertNotNull(medicaments);
        for (final Medicament medoc : medicaments) {
            Assert.assertNotNull(medoc.getId());
        }
    }

}
