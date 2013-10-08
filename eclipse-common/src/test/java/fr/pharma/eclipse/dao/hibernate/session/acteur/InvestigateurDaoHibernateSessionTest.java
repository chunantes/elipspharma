package fr.pharma.eclipse.dao.hibernate.session.acteur;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

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

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.dictionary.impl.CriteriaDictionaryImpl;
import fr.pharma.eclipse.domain.criteria.acteur.PersonneSearchCriteria;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.service.localisation.ServiceService;

/**
 * Classe en charge de tester la DAO Investigateur avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class InvestigateurDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Investigateur.
     */
    private GenericDaoHibernate<Investigateur> investigateurDao;

    /**
     * Dictionnaire de critère de recherche.
     */
    @Resource(name = "criteriaDictionary")
    private CriteriaDictionaryImpl dictionary;

    /**
     * Service de gestion des services.
     */
    @Resource(name = "serviceService")
    private ServiceService serviceService;

    /**
     * Initialisation du test.
     */
    @Before
    public void init() {
        this.investigateurDao = new GenericDaoHibernate<Investigateur>(Investigateur.class);
        this.investigateurDao.setEntityManager(this.entityManager);
        this.investigateurDao.setCriteriaDictionary(this.dictionary);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.investigateurDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO Investigateur.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de Investigateur
        Investigateur investigateur = new Investigateur();
        investigateur.setNom("nom");
        investigateur.setPrenom("prenom");
        investigateur.setTitre("titre");
        investigateur.setTelephone("0987654345");
        investigateur.setMail("prenom.nom@eclipse.fr");
        investigateur.setFax("0987654345");
        investigateur.setLogin("login");
        investigateur.setIsAdmin(Boolean.FALSE);

        final SortedSet<Service> services = new TreeSet<Service>(new BeanWithNomComparator());
        services.add(this.serviceService.get(1L));
        services.add(this.serviceService.get(2L));
        investigateur.setServices(services);

        investigateur = this.investigateurDao.save(investigateur);

        // Récupération de Investigateur
        final Investigateur investigateurReturn = this.investigateurDao.get(investigateur.getId());

        Assert.assertEquals(investigateur.getNom(), investigateurReturn.getNom());
        Assert.assertEquals(investigateur.getPrenom(), investigateurReturn.getPrenom());
        Assert.assertEquals(investigateur.getTitre(), investigateurReturn.getTitre());
        Assert.assertEquals(investigateur.getTelephone(), investigateurReturn.getTelephone());
        Assert.assertEquals(investigateur.getMail(), investigateurReturn.getMail());
        Assert.assertEquals(investigateur.getFax(), investigateurReturn.getFax());
        Assert.assertEquals(investigateur.getLogin(), investigateurReturn.getLogin());
        Assert.assertEquals(investigateur.getIsAdmin(), investigateurReturn.getIsAdmin());
        Assert.assertNotNull(investigateurReturn.getModifs());
        Assert.assertNotNull(investigateurReturn.getServices());

        // Suppression de Investigateur
        this.investigateurDao.remove(investigateur);
    }

    /**
     * Test de la récupération de tous les Investigateurs.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Investigateur
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        final Collection<Investigateur> investigateurs = this.investigateurDao.getAll(criteria);
        Assert.assertNotNull(investigateurs);
        for (final Investigateur investigateur : investigateurs) {
            Assert.assertNotNull(investigateur.getId());
            Assert.assertNotNull(investigateur.getNom());
        }
    }

}
