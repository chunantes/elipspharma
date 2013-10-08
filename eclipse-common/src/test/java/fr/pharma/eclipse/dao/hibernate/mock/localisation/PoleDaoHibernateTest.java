package fr.pharma.eclipse.dao.hibernate.mock.localisation;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.localisation.Pole;

/**
 * Classe en charge de tester la DAO des poles.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PoleDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<Pole> poleDao;

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
        this.poleDao = new GenericDaoHibernate<Pole>(Pole.class);
        this.poleDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.poleDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final Pole pole = new Pole();
        pole.setId(1L);

        Mockito.when(this.entityManager.find(Pole.class, pole.getId())).thenReturn(pole);

        final Pole poleReturned = this.poleDao.get(pole.getId());
        Assert.assertNotNull(poleReturned);
        Assert.assertNotNull(poleReturned.getId());
        Assert.assertEquals(1L, poleReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final Pole pole = new Pole();
        pole.setId(1L);
        Mockito.when(this.entityManager.merge(pole)).thenReturn(pole);
        final Pole poleSaved = this.poleDao.save(pole);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(poleSaved);
        Assert.assertEquals(pole, poleSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final Pole pole = new Pole();
        Mockito.when(this.poleDao.get(pole.getId())).thenReturn(pole);
        this.poleDao.remove(pole);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
