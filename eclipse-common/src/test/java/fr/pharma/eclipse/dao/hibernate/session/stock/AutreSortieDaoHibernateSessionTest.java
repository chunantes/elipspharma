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
import fr.pharma.eclipse.domain.model.stock.AutreSortie;
import fr.pharma.eclipse.service.acteur.PersonneService;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.utils.ContextSecurityHelper;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester la DAO AutreSortie avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class AutreSortieDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de AutreSortie.
     */
    private GenericDaoHibernate<AutreSortie> autreSortieDao;

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
        this.autreSortieDao = new GenericDaoHibernate<AutreSortie>(AutreSortie.class);
        this.autreSortieDao.setEntityManager(this.entityManager);
        this.autreSortieDao.setCriteriaDictionary(this.dictionary);
        ContextSecurityHelper.createSecurityContextMock();
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.autreSortieDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO AutreSortie.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de AutreSortie
        AutreSortie autreSortie = new AutreSortie();
        autreSortie.setEssai(this.essaiService.get(1L));
        autreSortie.setPersonne(this.personneService.get(1L));
        autreSortie.setQuantite(Integer.valueOf(1));
        autreSortie.setProduit(this.produitService.get(1L));
        autreSortie.setDateCreation(Calendar.getInstance(EclipseConstants.LOCALE));
        autreSortie.setNumLot("numLot");
        autreSortie.setConditionnement(this.conditionnementService.get(1L));
        autreSortie.setPharmacie(this.pharmacieService.get(1L));
        autreSortie.setNumTraitement("numTraitement");
        autreSortie.setCommentaire("commentaire");
        autreSortie.setApproApprouve(Boolean.FALSE);

        autreSortie.setType(TypeMvtStock.APPROVISIONNEMENT);
        autreSortie = this.autreSortieDao.save(autreSortie);

        // Récupération de AutreSortie
        final AutreSortie autreSortieReturn = this.autreSortieDao.get(autreSortie.getId());

        Assert.assertEquals(autreSortie.getType().getLibelle(), autreSortieReturn.getType().getLibelle());
        Assert.assertEquals(autreSortie.getDateCreation(), autreSortieReturn.getDateCreation());
        Assert.assertEquals(autreSortie.getNumLot(), autreSortieReturn.getNumLot());
        Assert.assertEquals(autreSortie.getEssai(), autreSortieReturn.getEssai());
        Assert.assertEquals(autreSortie.getProduit(), autreSortieReturn.getProduit());
        Assert.assertEquals(autreSortie.getPersonne(), autreSortieReturn.getPersonne());
        Assert.assertEquals(autreSortie.getQuantite(), autreSortieReturn.getQuantite());
        Assert.assertEquals(autreSortie.getConditionnement(), autreSortieReturn.getConditionnement());
        Assert.assertEquals(autreSortie.getPharmacie(), autreSortieReturn.getPharmacie());
        Assert.assertEquals(autreSortie.getNumTraitement(), autreSortieReturn.getNumTraitement());
        Assert.assertEquals(autreSortie.getCommentaire(), autreSortieReturn.getCommentaire());

        // Suppression de AutreSortie
        this.autreSortieDao.remove(autreSortie);
    }

    /**
     * Test de la récupération de tous les AutreSorties.
     */
    @Test
    public void getAll() {
        // Récupération de tous les AutreSortie
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        final Collection<AutreSortie> autreSorties = this.autreSortieDao.getAll(criteria);
        Assert.assertNotNull(autreSorties);
        for (final AutreSortie autreSortie : autreSorties) {
            Assert.assertNotNull(autreSortie.getId());
        }
    }

}
