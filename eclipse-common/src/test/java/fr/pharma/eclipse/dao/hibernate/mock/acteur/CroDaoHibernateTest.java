package fr.pharma.eclipse.dao.hibernate.mock.acteur;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.acteur.Cro;

/**
 * Classe en charge de tester la DAO des cros.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class CroDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<Cro> croDao;

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
        this.croDao = new GenericDaoHibernate<Cro>(Cro.class);
        this.croDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.croDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final Cro cro = new Cro();
        cro.setId(1L);

        Mockito.when(this.entityManager.find(Cro.class, cro.getId())).thenReturn(cro);

        final Cro croReturned = this.croDao.get(cro.getId());
        Assert.assertNotNull(croReturned);
        Assert.assertNotNull(croReturned.getId());
        Assert.assertEquals(1L, croReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final Cro cro = new Cro();
        cro.setId(1L);
        Mockito.when(this.entityManager.merge(cro)).thenReturn(cro);
        final Cro croSaved = this.croDao.save(cro);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(croSaved);
        Assert.assertEquals(cro, croSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final Cro cro = new Cro();
        Mockito.when(this.croDao.get(cro.getId())).thenReturn(cro);
        this.croDao.remove(cro);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
