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
import fr.pharma.eclipse.domain.model.stock.RetourPromoteur;
import fr.pharma.eclipse.service.acteur.PersonneService;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.utils.ContextSecurityHelper;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester la DAO RetourPromoteur avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class RetourPromoteurDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de RetourPromoteur.
     */
    private GenericDaoHibernate<RetourPromoteur> retourPromoteurDao;

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
        this.retourPromoteurDao = new GenericDaoHibernate<RetourPromoteur>(RetourPromoteur.class);
        this.retourPromoteurDao.setEntityManager(this.entityManager);
        this.retourPromoteurDao.setCriteriaDictionary(this.dictionary);
        ContextSecurityHelper.createSecurityContextMock();
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.retourPromoteurDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO RetourPromoteur.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de RetourPromoteur
        RetourPromoteur retourPromoteur = new RetourPromoteur();
        retourPromoteur.setEssai(this.essaiService.get(1L));
        retourPromoteur.setPersonne(this.personneService.get(1L));
        retourPromoteur.setQuantite(Integer.valueOf(1));
        retourPromoteur.setProduit(this.produitService.get(1L));
        retourPromoteur.setDateCreation(Calendar.getInstance(EclipseConstants.LOCALE));
        retourPromoteur.setNumLot("numLot");
        retourPromoteur.setConditionnement(this.conditionnementService.get(1L));
        retourPromoteur.setPharmacie(this.pharmacieService.get(1L));
        retourPromoteur.setNumTraitement("numTraitement");
        retourPromoteur.setCommentaire("commentaire");
        retourPromoteur.setNomSocieteTransport("nomSocieteTransport");
        retourPromoteur.setReferenceEnvoi("referenceEnvoi");
        retourPromoteur.setApproApprouve(Boolean.FALSE);

        retourPromoteur.setType(TypeMvtStock.APPROVISIONNEMENT);
        retourPromoteur = this.retourPromoteurDao.save(retourPromoteur);

        // Récupération de RetourPromoteur
        final RetourPromoteur retourPromoteurReturn = this.retourPromoteurDao.get(retourPromoteur.getId());

        Assert.assertEquals(retourPromoteur.getType().getLibelle(), retourPromoteurReturn.getType().getLibelle());
        Assert.assertEquals(retourPromoteur.getDateCreation(), retourPromoteurReturn.getDateCreation());
        Assert.assertEquals(retourPromoteur.getNumLot(), retourPromoteurReturn.getNumLot());
        Assert.assertEquals(retourPromoteur.getEssai(), retourPromoteurReturn.getEssai());
        Assert.assertEquals(retourPromoteur.getProduit(), retourPromoteurReturn.getProduit());
        Assert.assertEquals(retourPromoteur.getPersonne(), retourPromoteurReturn.getPersonne());
        Assert.assertEquals(retourPromoteur.getQuantite(), retourPromoteurReturn.getQuantite());
        Assert.assertEquals(retourPromoteur.getConditionnement(), retourPromoteurReturn.getConditionnement());
        Assert.assertEquals(retourPromoteur.getPharmacie(), retourPromoteurReturn.getPharmacie());
        Assert.assertEquals(retourPromoteur.getNumTraitement(), retourPromoteurReturn.getNumTraitement());
        Assert.assertEquals(retourPromoteur.getCommentaire(), retourPromoteurReturn.getCommentaire());
        Assert.assertEquals(retourPromoteur.getReferenceEnvoi(), retourPromoteurReturn.getReferenceEnvoi());
        Assert.assertEquals(retourPromoteur.getNomSocieteTransport(), retourPromoteurReturn.getNomSocieteTransport());

        // Suppression de RetourPromoteur
        this.retourPromoteurDao.remove(retourPromoteur);
    }

    /**
     * Test de la récupération de tous les RetourPromoteurs.
     */
    @Test
    public void getAll() {
        // Récupération de tous les RetourPromoteur
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        final Collection<RetourPromoteur> retourPromoteurs = this.retourPromoteurDao.getAll(criteria);
        Assert.assertNotNull(retourPromoteurs);
        for (final RetourPromoteur retourPromoteur : retourPromoteurs) {
            Assert.assertNotNull(retourPromoteur.getId());
        }
    }

}
