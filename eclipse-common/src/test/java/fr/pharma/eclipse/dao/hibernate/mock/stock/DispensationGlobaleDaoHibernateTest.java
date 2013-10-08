package fr.pharma.eclipse.dao.hibernate.mock.stock;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.stock.DispensationGlobale;

/**
 * Classe en charge de tester la DAO des dispensationGlobales.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationGlobaleDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<DispensationGlobale> dispensationGlobaleDao;

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
        this.dispensationGlobaleDao = new GenericDaoHibernate<DispensationGlobale>(DispensationGlobale.class);
        this.dispensationGlobaleDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.dispensationGlobaleDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final DispensationGlobale dispensationGlobale = new DispensationGlobale();
        dispensationGlobale.setId(1L);

        Mockito.when(this.entityManager.find(DispensationGlobale.class, dispensationGlobale.getId())).thenReturn(dispensationGlobale);

        final DispensationGlobale dispensationGlobaleReturned = this.dispensationGlobaleDao.get(dispensationGlobale.getId());
        Assert.assertNotNull(dispensationGlobaleReturned);
        Assert.assertNotNull(dispensationGlobaleReturned.getId());
        Assert.assertEquals(1L, dispensationGlobaleReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final DispensationGlobale dispensationGlobale = new DispensationGlobale();
        dispensationGlobale.setId(1L);
        Mockito.when(this.entityManager.merge(dispensationGlobale)).thenReturn(dispensationGlobale);
        final DispensationGlobale dispensationGlobaleSaved = this.dispensationGlobaleDao.save(dispensationGlobale);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(dispensationGlobaleSaved);
        Assert.assertEquals(dispensationGlobale, dispensationGlobaleSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final DispensationGlobale dispensationGlobale = new DispensationGlobale();
        Mockito.when(this.dispensationGlobaleDao.get(dispensationGlobale.getId())).thenReturn(dispensationGlobale);
        this.dispensationGlobaleDao.remove(dispensationGlobale);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
