package fr.pharma.eclipse.dao.hibernate.session.sigrec.acteur;

import java.util.ArrayList;
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
import fr.pharma.eclipse.domain.criteria.sigrec.acteur.PromoteurSigrecSearchCriteria;
import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ARCPromoteurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.PromoteurSigrec;

/**
 * Classe en charge de tester la DAO Promoteur Sigrec avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class PromoteurDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Promoteur.
     */
    private GenericDaoHibernate<PromoteurSigrec> promoteurDao;

    /**
     * Dictionnaire de critère de recherche.
     */
    @Resource(name = "criteriaDictionary")
    private CriteriaDictionaryImpl dictionary;

    /**
     * Initialisation du test.
     */
    @Before
    public void init() {
        this.promoteurDao = new GenericDaoHibernate<PromoteurSigrec>(PromoteurSigrec.class);
        this.promoteurDao.setEntityManager(this.entityManager);
        this.promoteurDao.setCriteriaDictionary(this.dictionary);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.promoteurDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO Promoteur.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de Promoteur
        PromoteurSigrec promoteur = new PromoteurSigrec();
        promoteur.setIdentifiant("identifiant");
        promoteur.setType(TypePromoteur.ACADEMIQUE);

        final List<ARCPromoteurSigrec> arcPromoteurs = new ArrayList<ARCPromoteurSigrec>();
        arcPromoteurs.add(new ARCPromoteurSigrec());
        promoteur.setArcs(arcPromoteurs);

        promoteur = this.promoteurDao.save(promoteur);

        // Récupération de Promoteur
        final PromoteurSigrec promoteurReturn = this.promoteurDao.get(promoteur.getId());

        Assert.assertEquals(promoteur.getIdentifiant(), promoteurReturn.getIdentifiant());
        Assert.assertEquals(promoteur.getType().getLibelle(), promoteurReturn.getType().getLibelle());
        Assert.assertNotNull(promoteurReturn.getArcs());

        // Suppression de Promoteur
        this.promoteurDao.remove(promoteur);
    }
    /**
     * Test de la récupération de tous les Promoteurs.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Promoteur
        final PromoteurSigrecSearchCriteria criteria = new PromoteurSigrecSearchCriteria();
        final Collection<PromoteurSigrec> promoteurs = this.promoteurDao.getAll(criteria);
        Assert.assertNotNull(promoteurs);
        for (final PromoteurSigrec promoteur : promoteurs) {
            Assert.assertNotNull(promoteur.getId());
            Assert.assertNotNull(promoteur.getContact().getRaisonSociale());
        }
    }

}
