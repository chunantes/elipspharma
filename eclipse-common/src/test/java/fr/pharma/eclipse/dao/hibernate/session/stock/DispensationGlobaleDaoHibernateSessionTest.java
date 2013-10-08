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
import fr.pharma.eclipse.domain.model.stock.DispensationGlobale;
import fr.pharma.eclipse.service.acteur.PersonneService;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.dotation.DotationService;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester la DAO DispensationGlobale avec une session
 * réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext.xml" })
@Transactional
public class DispensationGlobaleDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de DispensationGlobale.
     */
    private GenericDaoHibernate<DispensationGlobale> dispensationGlobaleDao;

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
     * Service de gestion des dotations.
     */
    @Resource(name = "dotationService")
    private DotationService dotationService;

    /**
     * Initialisation du test.
     */
    @Before
    public void init() {
        this.dispensationGlobaleDao = new GenericDaoHibernate<DispensationGlobale>(DispensationGlobale.class);
        this.dispensationGlobaleDao.setEntityManager(this.entityManager);
        this.dispensationGlobaleDao.setCriteriaDictionary(this.dictionary);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.dispensationGlobaleDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO DispensationGlobale.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de DispensationGlobale
        DispensationGlobale dispensationGlobale = new DispensationGlobale();
        dispensationGlobale.setEssai(this.essaiService.get(1L));
        dispensationGlobale.setPersonne(this.personneService.get(1L));
        dispensationGlobale.setQuantite(Integer.valueOf(1));
        dispensationGlobale.setProduit(this.produitService.get(1L));
        dispensationGlobale.setDateCreation(Calendar.getInstance(EclipseConstants.LOCALE));
        dispensationGlobale.setNumLot("numLot");
        dispensationGlobale.setConditionnement(this.conditionnementService.get(1L));
        dispensationGlobale.setPharmacie(this.pharmacieService.get(1L));
        dispensationGlobale.setNumTraitement("numTraitement");
        dispensationGlobale.setApproApprouve(Boolean.TRUE);
        dispensationGlobale.setDotation(this.dotationService.get(1L));

        dispensationGlobale.setType(TypeMvtStock.DOTATION);
        dispensationGlobale = this.dispensationGlobaleDao.save(dispensationGlobale);

        // Récupération de DispensationGlobale
        final DispensationGlobale dispensationGlobaleReturn = this.dispensationGlobaleDao.get(dispensationGlobale.getId());

        Assert.assertEquals(dispensationGlobale.getType().getLibelle(), dispensationGlobaleReturn.getType().getLibelle());
        Assert.assertEquals(dispensationGlobale.getDateCreation(), dispensationGlobaleReturn.getDateCreation());
        Assert.assertEquals(dispensationGlobale.getNumLot(), dispensationGlobaleReturn.getNumLot());
        Assert.assertEquals(dispensationGlobale.getEssai(), dispensationGlobaleReturn.getEssai());
        Assert.assertEquals(dispensationGlobale.getProduit(), dispensationGlobaleReturn.getProduit());
        Assert.assertEquals(dispensationGlobale.getPersonne(), dispensationGlobaleReturn.getPersonne());
        Assert.assertEquals(dispensationGlobale.getQuantite(), dispensationGlobaleReturn.getQuantite());
        Assert.assertEquals(dispensationGlobale.getConditionnement(), dispensationGlobaleReturn.getConditionnement());
        Assert.assertEquals(dispensationGlobale.getPharmacie(), dispensationGlobaleReturn.getPharmacie());
        Assert.assertEquals(dispensationGlobale.getApproApprouve(), dispensationGlobaleReturn.getApproApprouve());
        Assert.assertEquals(dispensationGlobale.getNumTraitement(), dispensationGlobaleReturn.getNumTraitement());
        Assert.assertEquals(dispensationGlobale.getDotation(), dispensationGlobaleReturn.getDotation());

        // Suppression de DispensationGlobale
        this.dispensationGlobaleDao.remove(dispensationGlobale);
    }

    /**
     * Test de la récupération de tous les DispensationGlobales.
     */
    @Test
    public void getAll() {
        // Récupération de tous les DispensationGlobale
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        final Collection<DispensationGlobale> dispensationGlobales = this.dispensationGlobaleDao.getAll(criteria);
        Assert.assertNotNull(dispensationGlobales);
        for (final DispensationGlobale dispensationGlobale : dispensationGlobales) {
            Assert.assertNotNull(dispensationGlobale.getId());
        }
    }

}
