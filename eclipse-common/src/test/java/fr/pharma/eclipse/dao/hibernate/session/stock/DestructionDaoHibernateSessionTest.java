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
import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.stock.Destruction;
import fr.pharma.eclipse.service.acteur.PersonneService;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.utils.ContextSecurityHelper;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester la DAO Destruction avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class DestructionDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Destruction.
     */
    private GenericDaoHibernate<Destruction> destructionDao;

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
     * Service de gestion des pharmacies.
     */
    @Resource(name = "pharmacieService")
    private PharmacieService pharmacieService;

    /**
     * Initialisation du test.
     */
    @Before
    public void init() {
        this.destructionDao = new GenericDaoHibernate<Destruction>(Destruction.class);
        this.destructionDao.setEntityManager(this.entityManager);
        this.destructionDao.setCriteriaDictionary(this.dictionary);
        ContextSecurityHelper.createSecurityContextMock();
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.destructionDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO Destruction.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de Destruction
        Destruction destruction = new Destruction();
        destruction.setEssai(this.essaiService.get(1L));
        destruction.setPersonne(this.personneService.get(1L));
        destruction.setQuantite(Integer.valueOf(1));
        destruction.setProduit(this.produitService.get(1L));
        destruction.setDateCreation(Calendar.getInstance(EclipseConstants.LOCALE));
        destruction.setNumLot("numLot");
        destruction.setConditionnement(this.conditionnementService.get(1L));
        destruction.setPharmacie(this.pharmacieService.get(1L));
        destruction.setNumTraitement("numTraitement");
        destruction.setCommentaire("commentaire");
        destruction.setApproApprouve(Boolean.TRUE);

        destruction.setType(TypeMvtStock.APPROVISIONNEMENT);
        destruction = this.destructionDao.save(destruction);

        // Récupération de Destruction
        final Destruction destructionReturn = this.destructionDao.get(destruction.getId());

        Assert.assertEquals(destruction.getType().getLibelle(), destructionReturn.getType().getLibelle());
        Assert.assertEquals(destruction.getDateCreation(), destructionReturn.getDateCreation());
        Assert.assertEquals(destruction.getNumLot(), destructionReturn.getNumLot());
        Assert.assertEquals(destruction.getEssai(), destructionReturn.getEssai());
        Assert.assertEquals(destruction.getProduit(), destructionReturn.getProduit());
        Assert.assertEquals(destruction.getPersonne(), destructionReturn.getPersonne());
        Assert.assertEquals(destruction.getQuantite(), destructionReturn.getQuantite());
        Assert.assertEquals(destruction.getConditionnement(), destructionReturn.getConditionnement());
        Assert.assertEquals(destruction.getPharmacie(), destructionReturn.getPharmacie());
        Assert.assertEquals(destruction.getNumTraitement(), destructionReturn.getNumTraitement());
        Assert.assertEquals(destruction.getCommentaire(), destructionReturn.getCommentaire());

        // Suppression de Destruction
        this.destructionDao.remove(destruction);
    }

    /**
     * Test de la récupération de tous les Destructions.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Destruction
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        final Collection<Destruction> destructions = this.destructionDao.getAll(criteria);
        Assert.assertNotNull(destructions);
        for (final Destruction destruction : destructions) {
            Assert.assertNotNull(destruction.getId());
        }
    }

}
