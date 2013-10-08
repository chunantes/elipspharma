package fr.pharma.eclipse.dao.hibernate.mock.dispensation;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;

/**
 * Classe en charge de tester la DAO des dispensations.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<Dispensation> dispensationDao;

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
        this.dispensationDao = new GenericDaoHibernate<Dispensation>(Dispensation.class);
        this.dispensationDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.dispensationDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final Dispensation dispensation = new Dispensation();
        dispensation.setId(1L);

        Mockito.when(this.entityManager.find(Dispensation.class, dispensation.getId())).thenReturn(dispensation);

        final Dispensation dispensationReturned = this.dispensationDao.get(dispensation.getId());
        Assert.assertNotNull(dispensationReturned);
        Assert.assertNotNull(dispensationReturned.getId());
        Assert.assertEquals(1L, dispensationReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final Dispensation dispensation = new Dispensation();
        dispensation.setId(1L);
        Mockito.when(this.entityManager.merge(dispensation)).thenReturn(dispensation);
        final Dispensation dispensationSaved = this.dispensationDao.save(dispensation);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(dispensationSaved);
        Assert.assertEquals(dispensation, dispensationSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final Dispensation dispensation = new Dispensation();
        Mockito.when(this.dispensationDao.get(dispensation.getId())).thenReturn(dispensation);
        this.dispensationDao.remove(dispensation);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
