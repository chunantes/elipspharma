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
import fr.pharma.eclipse.domain.criteria.localisation.ServiceSearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Pole;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Classe en charge de tester la DAO Service avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class ServiceDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Service.
     */
    private GenericDaoHibernate<Service> serviceDao;

    /**
     * Dictionnaire de critère de recherche.
     */
    @Resource(name = "criteriaDictionary")
    private CriteriaDictionaryImpl dictionary;

    /**
     * Service de gestion des poles.
     */
    @Resource(name = "poleService")
    private GenericService<Pole> poleService;

    /**
     * Initialisation du test.
     */
    @Before
    public void init() {
        this.serviceDao = new GenericDaoHibernate<Service>(Service.class);
        this.serviceDao.setEntityManager(this.entityManager);
        this.serviceDao.setCriteriaDictionary(this.dictionary);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.serviceDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO Service.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de Service
        Service service = new Service();
        service.setNom("nom");
        service.setPole(this.poleService.get(1L));
        service = this.serviceDao.save(service);

        // Récupération de Service
        final Service serviceReturn = this.serviceDao.get(service.getId());

        Assert.assertEquals(service.getNom(), serviceReturn.getNom());
        Assert.assertEquals(service.getPole(), serviceReturn.getPole());
        Assert.assertNotNull(serviceReturn.getModifs());

        // Suppression de Service
        this.serviceDao.remove(service);
    }

    /**
     * Test de la récupération de tous les Services.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Service
        final ServiceSearchCriteria criteria = new ServiceSearchCriteria();
        final Collection<Service> services = this.serviceDao.getAll(criteria);

        Assert.assertNotNull(services);

        for (final Service service : services) {
            Assert.assertNotNull(service.getId());
            Assert.assertNotNull(service.getNom());
        }
    }

}
