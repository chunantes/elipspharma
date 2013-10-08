package fr.pharma.eclipse.dao.hibernate.mock.stock;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.stock.Destruction;

/**
 * Classe en charge de tester la DAO des destructions.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DestructionDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<Destruction> destructionDao;

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
        this.destructionDao = new GenericDaoHibernate<Destruction>(Destruction.class);
        this.destructionDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.destructionDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final Destruction destruction = new Destruction();
        destruction.setId(1L);

        Mockito.when(this.entityManager.find(Destruction.class, destruction.getId())).thenReturn(destruction);

        final Destruction destructionReturned = this.destructionDao.get(destruction.getId());
        Assert.assertNotNull(destructionReturned);
        Assert.assertNotNull(destructionReturned.getId());
        Assert.assertEquals(1L, destructionReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final Destruction destruction = new Destruction();
        destruction.setId(1L);
        Mockito.when(this.entityManager.merge(destruction)).thenReturn(destruction);
        final Destruction destructionSaved = this.destructionDao.save(destruction);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(destructionSaved);
        Assert.assertEquals(destruction, destructionSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final Destruction destruction = new Destruction();
        Mockito.when(this.destructionDao.get(destruction.getId())).thenReturn(destruction);
        this.destructionDao.remove(destruction);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
