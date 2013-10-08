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
import fr.pharma.eclipse.domain.model.stock.CessionPui;
import fr.pharma.eclipse.service.acteur.PersonneService;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.utils.ContextSecurityHelper;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester la DAO CessionPui avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class CessionPuiDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de CessionPui.
     */
    private GenericDaoHibernate<CessionPui> cessionPuiDao;

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
        this.cessionPuiDao = new GenericDaoHibernate<CessionPui>(CessionPui.class);
        this.cessionPuiDao.setEntityManager(this.entityManager);
        this.cessionPuiDao.setCriteriaDictionary(this.dictionary);
        ContextSecurityHelper.createSecurityContextMock();
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.cessionPuiDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO CessionPui.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de CessionPui
        CessionPui cessionPui = new CessionPui();
        cessionPui.setEssai(this.essaiService.get(1L));
        cessionPui.setPersonne(this.personneService.get(1L));
        cessionPui.setQuantite(Integer.valueOf(1));
        cessionPui.setProduit(this.produitService.get(1L));
        cessionPui.setDateCreation(Calendar.getInstance(EclipseConstants.LOCALE));
        cessionPui.setNumLot("numLot");
        cessionPui.setConditionnement(this.conditionnementService.get(1L));
        cessionPui.setPharmacie(this.pharmacieService.get(1L));
        cessionPui.setNumTraitement("numTraitement");
        cessionPui.setCommentaire("commentaire");
        cessionPui.setApproApprouve(Boolean.TRUE);

        cessionPui.setType(TypeMvtStock.APPROVISIONNEMENT);
        cessionPui = this.cessionPuiDao.save(cessionPui);

        // Récupération de CessionPui
        final CessionPui cessionPuiReturn = this.cessionPuiDao.get(cessionPui.getId());

        Assert.assertEquals(cessionPui.getType().getLibelle(), cessionPuiReturn.getType().getLibelle());
        Assert.assertEquals(cessionPui.getDateCreation(), cessionPuiReturn.getDateCreation());
        Assert.assertEquals(cessionPui.getNumLot(), cessionPuiReturn.getNumLot());
        Assert.assertEquals(cessionPui.getEssai(), cessionPuiReturn.getEssai());
        Assert.assertEquals(cessionPui.getProduit(), cessionPuiReturn.getProduit());
        Assert.assertEquals(cessionPui.getPersonne(), cessionPuiReturn.getPersonne());
        Assert.assertEquals(cessionPui.getQuantite(), cessionPuiReturn.getQuantite());
        Assert.assertEquals(cessionPui.getConditionnement(), cessionPuiReturn.getConditionnement());
        Assert.assertEquals(cessionPui.getPharmacie(), cessionPuiReturn.getPharmacie());
        Assert.assertEquals(cessionPui.getNumTraitement(), cessionPuiReturn.getNumTraitement());
        Assert.assertEquals(cessionPui.getCommentaire(), cessionPuiReturn.getCommentaire());

        // Suppression de CessionPui
        this.cessionPuiDao.remove(cessionPui);
    }

    /**
     * Test de la récupération de tous les CessionPuis.
     */
    @Test
    public void getAll() {
        // Récupération de tous les CessionPui
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        final Collection<CessionPui> cessionPuis = this.cessionPuiDao.getAll(criteria);
        Assert.assertNotNull(cessionPuis);
        for (final CessionPui cessionPui : cessionPuis) {
            Assert.assertNotNull(cessionPui.getId());
        }
    }

}
