package fr.pharma.eclipse.dao.hibernate.mock.stock;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.stock.CessionPui;

/**
 * Classe en charge de tester la DAO des cessionPuis.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class CessionPuiDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<CessionPui> cessionPuiDao;

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
        this.cessionPuiDao = new GenericDaoHibernate<CessionPui>(CessionPui.class);
        this.cessionPuiDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.cessionPuiDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final CessionPui cessionPui = new CessionPui();
        cessionPui.setId(1L);

        Mockito.when(this.entityManager.find(CessionPui.class, cessionPui.getId())).thenReturn(cessionPui);

        final CessionPui cessionPuiReturned = this.cessionPuiDao.get(cessionPui.getId());
        Assert.assertNotNull(cessionPuiReturned);
        Assert.assertNotNull(cessionPuiReturned.getId());
        Assert.assertEquals(1L, cessionPuiReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final CessionPui cessionPui = new CessionPui();
        cessionPui.setId(1L);
        Mockito.when(this.entityManager.merge(cessionPui)).thenReturn(cessionPui);
        final CessionPui cessionPuiSaved = this.cessionPuiDao.save(cessionPui);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(cessionPuiSaved);
        Assert.assertEquals(cessionPui, cessionPuiSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final CessionPui cessionPui = new CessionPui();
        Mockito.when(this.cessionPuiDao.get(cessionPui.getId())).thenReturn(cessionPui);
        this.cessionPuiDao.remove(cessionPui);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
