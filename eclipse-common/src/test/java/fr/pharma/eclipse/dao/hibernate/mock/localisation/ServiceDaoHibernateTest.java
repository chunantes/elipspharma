package fr.pharma.eclipse.dao.hibernate.mock.localisation;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.localisation.Service;

/**
 * Classe en charge de tester la DAO des services.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ServiceDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<Service> serviceDao;

    /**
     * EntityManager.
     */
    private EntityManager entityManager;

    /**
     * Méthode d'initialisation du test.
     */
    @Before
    public void init() {
        this.entityManager = Mockito.mock(EntityManager.class);
        this.serviceDao = new GenericDaoHibernate<Service>(Service.class);
        this.serviceDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.serviceDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final Service service = new Service();
        service.setId(1L);

        Mockito.when(this.entityManager.find(Service.class, service.getId())).thenReturn(service);

        final Service serviceReturned = this.serviceDao.get(service.getId());
        Assert.assertNotNull(serviceReturned);
        Assert.assertNotNull(serviceReturned.getId());
        Assert.assertEquals(1L, serviceReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final Service service = new Service();
        service.setId(1L);
        Mockito.when(this.entityManager.merge(service)).thenReturn(service);
        final Service serviceSaved = this.serviceDao.save(service);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(serviceSaved);
        Assert.assertEquals(service, serviceSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final Service service = new Service();
        Mockito.when(this.serviceDao.get(service.getId())).thenReturn(service);
        this.serviceDao.remove(service);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
