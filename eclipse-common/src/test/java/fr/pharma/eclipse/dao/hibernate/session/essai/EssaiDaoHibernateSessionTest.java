package fr.pharma.eclipse.dao.hibernate.session.essai;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.dictionary.impl.CriteriaDictionaryImpl;
import fr.pharma.eclipse.domain.criteria.essai.EssaiSearchCriteria;
import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.recherche.CommentaireEssaiRecherche;
import fr.pharma.eclipse.domain.model.essai.detail.recherche.DetailRecherche;
import fr.pharma.eclipse.service.acteur.PromoteurService;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.utils.ContextSecurityHelper;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester la DAO Essai avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class EssaiDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Essai.
     */
    private GenericDaoHibernate<Essai> essaiDao;

    /**
     * Dictionnaire de critère de recherche.
     */
    @Resource(name = "criteriaDictionary")
    private CriteriaDictionaryImpl dictionary;

    /**
     * Service de gestion des promoteurs.
     */
    @Resource(name = "promoteurService")
    private PromoteurService promoteurService;

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
        this.essaiDao = new GenericDaoHibernate<Essai>(Essai.class);
        this.essaiDao.setEntityManager(this.entityManager);
        this.essaiDao.setCriteriaDictionary(this.dictionary);
        ContextSecurityHelper.createSecurityContextMock();
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.essaiDao = null;
        this.entityManager = null;
        this.dictionary = null;
        SecurityContextHolder.clearContext();
    }

    /**
     * Test des méthodes de DAO Essai.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de Essai
        long pharmacieId = 1;
        Essai essai = new Essai();
        essai.setNumInterne("numInterne");
        essai.setCodePromoteur("prom-20101102");
        essai.setNom("nom");
        essai.setDci("dci");
        essai.setEtat(EtatEssai.EN_COURS);
        essai.setTypePromoteur(TypePromoteur.AUTRE);
        essai.setPromoteur(this.promoteurService.get(1L));
        essai.setPharmaciePrincipale(this.pharmacieService.get(pharmacieId++));

        final DetailRecherche detailRecherche = new DetailRecherche();
        final CommentaireEssaiRecherche commentaire = new CommentaireEssaiRecherche();
        commentaire.setDetailRecherche(detailRecherche);
        commentaire.setMajPar("SRM");
        commentaire.setDateMaj(Calendar.getInstance(EclipseConstants.LOCALE));
        commentaire.setLibelle("libelle");
        detailRecherche.getCommentaires().add(commentaire);
        essai.setDetailRecherche(detailRecherche);
        essai.setAlerteActive(Boolean.TRUE);

        essai = this.essaiDao.save(essai);

        // Récupération de Essai
        final Essai essaiReturn = this.essaiDao.get(essai.getId());

        Assert.assertEquals(essai.getNumInterne(), essaiReturn.getNumInterne());
        Assert.assertEquals(essai.getCodePromoteur(), essaiReturn.getCodePromoteur());
        Assert.assertEquals(essai.getNom(), essaiReturn.getNom());
        Assert.assertEquals(essai.getDci(), essaiReturn.getDci());
        Assert.assertEquals(essai.getEtat().getLibelle(), essaiReturn.getEtat().getLibelle());
        Assert.assertEquals(essai.getTypePromoteur(), essaiReturn.getTypePromoteur());
        Assert.assertEquals(essai.getPromoteur(), essaiReturn.getPromoteur());
        Assert.assertEquals(essai.getPharmaciePrincipale(), essaiReturn.getPharmaciePrincipale());
        Assert.assertNotNull(essaiReturn.getModifs());

        // Suppression de Essai
        this.essaiDao.remove(essai);
    }

    /**
     * Test de la récupération de tous les Essais.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Essai
        final EssaiSearchCriteria criteria = new EssaiSearchCriteria();
        final Collection<Essai> essais = this.essaiDao.getAll(criteria);
        Assert.assertNotNull(essais);
        for (final Essai essai : essais) {
            Assert.assertNotNull(essai.getId());
            Assert.assertNotNull(essai.getNom());
        }
    }
}
