package fr.pharma.eclipse.dao.hibernate.mock.stockage;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.stockage.Stockage;

/**
 * Classe en charge de tester la DAO des stockages.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class StockageDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<Stockage> stockageDao;

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
        this.stockageDao = new GenericDaoHibernate<Stockage>(Stockage.class);
        this.stockageDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.stockageDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final Stockage stockage = new Stockage();
        stockage.setId(1L);

        Mockito.when(this.entityManager.find(Stockage.class, stockage.getId())).thenReturn(stockage);

        final Stockage stockageReturned = this.stockageDao.get(stockage.getId());
        Assert.assertNotNull(stockageReturned);
        Assert.assertNotNull(stockageReturned.getId());
        Assert.assertEquals(1L, stockageReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final Stockage stockage = new Stockage();
        stockage.setId(1L);
        Mockito.when(this.entityManager.merge(stockage)).thenReturn(stockage);
        final Stockage stockageSaved = this.stockageDao.save(stockage);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(stockageSaved);
        Assert.assertEquals(stockage, stockageSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final Stockage stockage = new Stockage();
        Mockito.when(this.stockageDao.get(stockage.getId())).thenReturn(stockage);
        this.stockageDao.remove(stockage);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
