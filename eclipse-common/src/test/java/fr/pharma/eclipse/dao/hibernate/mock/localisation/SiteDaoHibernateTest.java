package fr.pharma.eclipse.dao.hibernate.mock.localisation;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.localisation.Site;

/**
 * Classe en charge de tester la DAO des sites.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SiteDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<Site> siteDao;

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
        this.siteDao = new GenericDaoHibernate<Site>(Site.class);
        this.siteDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.siteDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final Site site = new Site();
        site.setId(1L);

        Mockito.when(this.entityManager.find(Site.class, site.getId())).thenReturn(site);

        final Site siteReturned = this.siteDao.get(site.getId());
        Assert.assertNotNull(siteReturned);
        Assert.assertNotNull(siteReturned.getId());
        Assert.assertEquals(1L, siteReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final Site site = new Site();
        site.setId(1L);
        Mockito.when(this.entityManager.merge(site)).thenReturn(site);
        final Site siteSaved = this.siteDao.save(site);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(siteSaved);
        Assert.assertEquals(site, siteSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final Site site = new Site();
        Mockito.when(this.siteDao.get(site.getId())).thenReturn(site);
        this.siteDao.remove(site);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
