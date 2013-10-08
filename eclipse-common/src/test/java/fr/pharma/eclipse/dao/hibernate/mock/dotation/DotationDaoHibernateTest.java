package fr.pharma.eclipse.dao.hibernate.mock.dotation;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.dotation.Dotation;

/**
 * Classe en charge de tester la DAO des dotations.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DotationDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<Dotation> dotationDao;

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
        this.dotationDao = new GenericDaoHibernate<Dotation>(Dotation.class);
        this.dotationDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.dotationDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final Dotation dotation = new Dotation();
        dotation.setId(1L);

        Mockito.when(this.entityManager.find(Dotation.class, dotation.getId())).thenReturn(dotation);

        final Dotation dotationReturned = this.dotationDao.get(dotation.getId());
        Assert.assertNotNull(dotationReturned);
        Assert.assertNotNull(dotationReturned.getId());
        Assert.assertEquals(1L, dotationReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final Dotation dotation = new Dotation();
        dotation.setId(1L);
        Mockito.when(this.entityManager.merge(dotation)).thenReturn(dotation);
        final Dotation dotationSaved = this.dotationDao.save(dotation);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(dotationSaved);
        Assert.assertEquals(dotation, dotationSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final Dotation dotation = new Dotation();
        Mockito.when(this.dotationDao.get(dotation.getId())).thenReturn(dotation);
        this.dotationDao.remove(dotation);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
