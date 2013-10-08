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
import fr.pharma.eclipse.domain.model.acteur.ArcInvestigateur;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.service.localisation.ServiceService;

/**
 * Classe en charge de tester la DAO ArcInvestigateur avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class ArcInvestigateurDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de ArcInvestigateur.
     */
    private GenericDaoHibernate<ArcInvestigateur> arcInvestigateurDao;

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
        this.arcInvestigateurDao = new GenericDaoHibernate<ArcInvestigateur>(ArcInvestigateur.class);
        this.arcInvestigateurDao.setEntityManager(this.entityManager);
        this.arcInvestigateurDao.setCriteriaDictionary(this.dictionary);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.arcInvestigateurDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO ArcInvestigateur.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de ArcInvestigateur
        ArcInvestigateur arcInvestigateur = new ArcInvestigateur();
        arcInvestigateur.setNom("nom");
        arcInvestigateur.setPrenom("prenom");
        arcInvestigateur.setTelephone("0987654345");
        arcInvestigateur.setMail("prenom.nom@eclipse.fr");
        arcInvestigateur.setFax("0987654345");
        arcInvestigateur.setLogin("login");
        arcInvestigateur.setIsAdmin(Boolean.FALSE);

        final SortedSet<Service> services = new TreeSet<Service>(new BeanWithNomComparator());
        services.add(this.serviceService.get(1L));
        services.add(this.serviceService.get(2L));
        arcInvestigateur.setServices(services);

        arcInvestigateur = this.arcInvestigateurDao.save(arcInvestigateur);

        // Récupération de ArcInvestigateur
        final ArcInvestigateur arcInvestigateurReturn = this.arcInvestigateurDao.get(arcInvestigateur.getId());

        Assert.assertEquals(arcInvestigateur.getNom(), arcInvestigateurReturn.getNom());
        Assert.assertEquals(arcInvestigateur.getPrenom(), arcInvestigateurReturn.getPrenom());
        Assert.assertEquals(arcInvestigateur.getTelephone(), arcInvestigateurReturn.getTelephone());
        Assert.assertEquals(arcInvestigateur.getMail(), arcInvestigateurReturn.getMail());
        Assert.assertEquals(arcInvestigateur.getFax(), arcInvestigateurReturn.getFax());
        Assert.assertEquals(arcInvestigateur.getLogin(), arcInvestigateurReturn.getLogin());
        Assert.assertEquals(arcInvestigateur.getIsAdmin(), arcInvestigateurReturn.getIsAdmin());
        Assert.assertNotNull(arcInvestigateurReturn.getModifs());
        Assert.assertNotNull(arcInvestigateurReturn.getServices());

        // Suppression de ArcInvestigateur
        this.arcInvestigateurDao.remove(arcInvestigateur);
    }

    /**
     * Test de la récupération de tous les ArcInvestigateurs.
     */
    @Test
    public void getAll() {
        // Preparer
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();

        // Tester
        final Collection<ArcInvestigateur> arcInvestigateurs = this.arcInvestigateurDao.getAll(criteria);

        // Vérifier
        Assert.assertNotNull(arcInvestigateurs);
        Assert.assertEquals(1, arcInvestigateurs.size());
        Assert.assertEquals("Ronald", arcInvestigateurs.iterator().next().getNom());
    }

}
