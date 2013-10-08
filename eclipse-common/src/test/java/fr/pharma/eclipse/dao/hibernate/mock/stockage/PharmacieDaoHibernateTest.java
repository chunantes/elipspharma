package fr.pharma.eclipse.dao.hibernate.mock.stockage;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Classe en charge de tester la DAO des pharmacies.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PharmacieDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<Pharmacie> pharmacieDao;

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
        this.pharmacieDao = new GenericDaoHibernate<Pharmacie>(Pharmacie.class);
        this.pharmacieDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.pharmacieDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);

        Mockito.when(this.entityManager.find(Pharmacie.class, pharmacie.getId())).thenReturn(pharmacie);

        final Pharmacie pharmacieReturned = this.pharmacieDao.get(pharmacie.getId());
        Assert.assertNotNull(pharmacieReturned);
        Assert.assertNotNull(pharmacieReturned.getId());
        Assert.assertEquals(1L, pharmacieReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);
        Mockito.when(this.entityManager.merge(pharmacie)).thenReturn(pharmacie);
        final Pharmacie pharmacieSaved = this.pharmacieDao.save(pharmacie);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(pharmacieSaved);
        Assert.assertEquals(pharmacie, pharmacieSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final Pharmacie pharmacie = new Pharmacie();
        Mockito.when(this.pharmacieDao.get(pharmacie.getId())).thenReturn(pharmacie);
        this.pharmacieDao.remove(pharmacie);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
