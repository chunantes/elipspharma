package fr.pharma.eclipse.dao.hibernate.session.sigrec.essai;

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
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.dictionary.impl.CriteriaDictionaryImpl;
import fr.pharma.eclipse.domain.criteria.sigrec.essai.EssaiSigrecSearchCriteria;
import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.model.sigrec.acteur.InvestigateurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.PromoteurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.DetailRechercheSigrec;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Classe en charge de tester la DAO Essai avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class EssaiSigrecDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Essai.
     */
    private GenericDaoHibernate<EssaiSigrec> essaiSigrecDao;

    /**
     * Dictionnaire de critère de recherche.
     */
    @Resource(name = "criteriaDictionary")
    private CriteriaDictionaryImpl dictionary;

    /**
     * Service de gestion des promoteurs.
     */
    @Resource(name = "promoteurSigrecService")
    private GenericService<PromoteurSigrec> promoteurSigrecService;

    /**
     * Initialisation du test.
     */
    @Before
    public void init() {
        this.essaiSigrecDao = new GenericDaoHibernate<EssaiSigrec>(EssaiSigrec.class);
        this.essaiSigrecDao.setEntityManager(this.entityManager);
        this.essaiSigrecDao.setCriteriaDictionary(this.dictionary);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.essaiSigrecDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO Essai.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de Essai
        EssaiSigrec essai = new EssaiSigrec();
        essai.setCodePromoteur("codePromoteur");
        essai.setCodePromoteur("prom-20101102");
        essai.setNom("nom");
        essai.setTypePromoteur(TypePromoteur.AUTRE);
        essai.setPromoteur(new PromoteurSigrec());
        essai.setInvestigateurPrincipal(new InvestigateurSigrec());

        final DetailRechercheSigrec detailRecherche = new DetailRechercheSigrec();
        essai.setDetailRecherche(detailRecherche);

        essai = this.essaiSigrecDao.save(essai);

        // Récupération de Essai
        final EssaiSigrec essaiReturn = this.essaiSigrecDao.get(essai.getId());

        Assert.assertEquals(essai.getCodePromoteur(), essaiReturn.getCodePromoteur());
        Assert.assertEquals(essai.getCodePromoteur(), essaiReturn.getCodePromoteur());
        Assert.assertEquals(essai.getNom(), essaiReturn.getNom());
        Assert.assertEquals(essai.getTypePromoteur(), essaiReturn.getTypePromoteur());
        Assert.assertEquals(essai.getPromoteur(), essaiReturn.getPromoteur());
        Assert.assertEquals(essai.getInvestigateurPrincipal(), essaiReturn.getInvestigateurPrincipal());

        // Suppression de Essai
        this.essaiSigrecDao.remove(essai);
    }

    /**
     * Test de la récupération de tous les Essais.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Essai
        final EssaiSigrecSearchCriteria criteria = new EssaiSigrecSearchCriteria();
        final Collection<EssaiSigrec> essais = this.essaiSigrecDao.getAll(criteria);
        Assert.assertNotNull(essais);
        for (final EssaiSigrec essai : essais) {
            Assert.assertNotNull(essai.getId());
            Assert.assertNotNull(essai.getNom());
        }
    }

}
