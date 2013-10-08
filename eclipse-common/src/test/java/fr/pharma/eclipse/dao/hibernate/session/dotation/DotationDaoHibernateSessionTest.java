package fr.pharma.eclipse.dao.hibernate.session.dotation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

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
import fr.pharma.eclipse.domain.criteria.dotation.DotationSearchCriteria;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.dotation.Dotation;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.service.acteur.PersonneService;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.localisation.ServiceService;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.utils.ContextSecurityHelper;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester la DAO Dotation avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class DotationDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Dotation.
     */
    private GenericDaoHibernate<Dotation> dotationDao;

    /**
     * Dictionnaire de critère de recherche.
     */
    @Resource(name = "criteriaDictionary")
    private CriteriaDictionaryImpl dictionary;

    /**
     * Service de gestion des conditionnements.
     */
    @Resource(name = "conditionnementService")
    private GenericService<Conditionnement> conditionnementService;

    /**
     * Service de gestion des produits.
     */
    @Resource(name = "produitService")
    private ProduitService<Produit> produitService;

    /**
     * Service de gestion des services.
     */
    @Resource(name = "serviceService")
    private ServiceService serviceService;

    /**
     * Service de gestion des personnes.
     */
    @Resource(name = "personneService")
    private PersonneService<Personne> personneService;

    /**
     * Service de gestion des essais.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

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
        this.dotationDao = new GenericDaoHibernate<Dotation>(Dotation.class);
        this.dotationDao.setEntityManager(this.entityManager);
        this.dotationDao.setCriteriaDictionary(this.dictionary);
        ContextSecurityHelper.createSecurityContextMock();
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.dotationDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO Dotation.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de Dotation
        Dotation dotation = new Dotation();
        dotation.setEssai(this.essaiService.get(1L));
        dotation.setPharmacie(this.pharmacieService.get(1L));
        dotation.setService(this.serviceService.get(1L));
        dotation.setProduit(this.produitService.get(1L));
        dotation.setConditionnement(this.conditionnementService.get(1L));
        dotation.setPersonne(this.personneService.get(1L));
        dotation.setDateDemande(Calendar.getInstance(EclipseConstants.LOCALE));
        dotation.setQuantite(2);
        dotation.setTraitee(Boolean.FALSE);
        dotation.setCommentaire("commentaire");

        dotation = this.dotationDao.save(dotation);

        // Récupération de Dotation
        final Dotation dotationReturn = this.dotationDao.get(dotation.getId());

        Assert.assertEquals(dotation.getEssai(), dotationReturn.getEssai());
        Assert.assertEquals(dotation.getPharmacie(), dotationReturn.getPharmacie());
        Assert.assertEquals(dotation.getService(), dotationReturn.getService());
        Assert.assertEquals(dotation.getProduit(), dotationReturn.getProduit());
        Assert.assertEquals(dotation.getConditionnement(), dotationReturn.getConditionnement());
        Assert.assertEquals(dotation.getPersonne(), dotationReturn.getPersonne());
        Assert.assertEquals(dotation.getDateDemande(), dotationReturn.getDateDemande());
        Assert.assertEquals(dotation.getQuantite(), dotationReturn.getQuantite());
        Assert.assertEquals(dotation.getTraitee(), dotationReturn.getTraitee());
        Assert.assertEquals(dotation.getCommentaire(), dotationReturn.getCommentaire());

        // Suppression de Dotation
        this.dotationDao.remove(dotation);
    }

    /**
     *
     */
    // private void createSecurityContextMock()
    // {
    // final SecurityContext contextMocked =
    // Mockito.mock(SecurityContext.class);
    // final Authentication authMocked = Mockito.mock(Authentication.class);
    // SecurityContextHolder.setContext(contextMocked);
    // SecurityContextHolder.getContext().setAuthentication(authMocked);
    //
    // Mockito.when(contextMocked.getAuthentication()).thenReturn(authMocked);
    //
    // final List<GrantedAuthority> authorities = new
    // ArrayList<GrantedAuthority>();
    // authorities.add(new GrantedAuthorityImpl(EclipseConstants.ROLE
    // + RolePersonne.PHARMACIEN));
    //
    // Mockito.when(contextMocked.getAuthentication().getPrincipal())
    // .thenReturn(new UserSecurity("admin",
    // "password",
    // "salt",
    // authorities,
    // 3L,
    // RolePersonne.PHARMACIEN,
    // true));
    // }

    /**
     * Test de la récupération de tous les Dotations avec une défintion de
     * critère de recherche.
     */
    @Test
    public void getAllWithCriteria() {
        // Récupération de tous les Dotation
        final DotationSearchCriteria criteria = new DotationSearchCriteria();
        criteria.setActiveOrder("dateDemande");
        criteria.setEssai(this.essaiService.get(1L));
        criteria.setPharmacie(this.pharmacieService.get(1L));
        criteria.setProduit(this.produitService.get(1L));
        criteria.setService(this.serviceService.get(1L));
        criteria.setTraitee(Boolean.FALSE);
        criteria.setAscending(true);
        criteria.setCaseSensitiveOrder(true);
        final Collection<Dotation> dotations = this.dotationDao.getAll(criteria);

        Assert.assertNotNull(dotations);

        for (final Dotation dotation : dotations) {
            Assert.assertNotNull(dotation.getId());
        }
    }

    /**
     * Test de la récupération de tous les Dotations.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Dotation
        final DotationSearchCriteria criteria = new DotationSearchCriteria();
        criteria.setActiveOrder("dateDemande");
        final Collection<Dotation> dotations = this.dotationDao.getAll(criteria);

        Assert.assertNotNull(dotations);

        for (final Dotation dotation : dotations) {
            Assert.assertNotNull(dotation.getId());
        }
    }

    /**
     * Test de la récupération des dotations pour les alertes.
     */
    @Test
    public void getAllForAlerte() {
        final DotationSearchCriteria crit = new DotationSearchCriteria();
        crit.setTraitee(Boolean.FALSE);
        crit.setAlerteActiveProduit(Boolean.TRUE);
        final List<Long> idsEssais = new ArrayList<Long>();
        idsEssais.add(1L);
        idsEssais.add(2L);
        final List<Long> idsPharmacies = new ArrayList<Long>();
        idsPharmacies.add(1L);
        idsPharmacies.add(2L);
        crit.setIdsEssais(idsEssais);
        crit.setIdsPharmacies(idsPharmacies);
        final Collection<Dotation> dotations = this.dotationDao.getAll(crit);
        Assert.assertNotNull(dotations);

    }

}
