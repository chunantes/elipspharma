package fr.pharma.eclipse.dao.hibernate.session.localisation;

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
import fr.pharma.eclipse.domain.criteria.localisation.EtablissementSearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Etablissement;

/**
 * Classe en charge de tester la DAO Etablissement avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class EtablissementDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Etablissement.
     */
    private GenericDaoHibernate<Etablissement> etablissementDao;

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
        this.etablissementDao = new GenericDaoHibernate<Etablissement>(Etablissement.class);
        this.etablissementDao.setEntityManager(this.entityManager);
        this.etablissementDao.setCriteriaDictionary(this.dictionary);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.etablissementDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO Etablissement.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de Etablissement
        Etablissement etablissement = new Etablissement();
        etablissement.setNom("nom");
        etablissement.setMail("mail");
        etablissement.setFax("fax");
        etablissement.setTelephone("telephone");
        etablissement.setAdresseDirection("adresseDirection");
        etablissement.setVille("ville");
        etablissement.setCodePostal("codePostal");
        etablissement.setPays("pays");
        etablissement = this.etablissementDao.save(etablissement);

        // Récupération de Etablissement
        final Etablissement etablissementReturn = this.etablissementDao.get(etablissement.getId());

        Assert.assertEquals(etablissement.getNom(), etablissementReturn.getNom());
        Assert.assertEquals(etablissement.getMail(), etablissementReturn.getMail());
        Assert.assertEquals(etablissement.getFax(), etablissementReturn.getFax());
        Assert.assertEquals(etablissement.getTelephone(), etablissementReturn.getTelephone());
        Assert.assertEquals(etablissement.getAdresseDirection(), etablissementReturn.getAdresseDirection());
        Assert.assertEquals(etablissement.getVille(), etablissementReturn.getVille());
        Assert.assertEquals(etablissement.getCodePostal(), etablissementReturn.getCodePostal());
        Assert.assertEquals(etablissement.getPays(), etablissementReturn.getPays());
        Assert.assertNotNull(etablissementReturn.getModifs());

        // Suppression de Etablissement
        this.etablissementDao.remove(etablissement);
    }

    /**
     * Test de la récupération de tous les Etablissements.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Etablissements
        final EtablissementSearchCriteria criteria = new EtablissementSearchCriteria();
        final Collection<Etablissement> etablissements = this.etablissementDao.getAll(criteria);

        Assert.assertNotNull(etablissements);

        for (final Etablissement etablissement : etablissements) {
            Assert.assertNotNull(etablissement.getId());
            Assert.assertNotNull(etablissement.getNom());
        }
    }

}
