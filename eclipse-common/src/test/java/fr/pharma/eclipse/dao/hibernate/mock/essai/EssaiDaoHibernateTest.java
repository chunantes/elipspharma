package fr.pharma.eclipse.dao.hibernate.mock.essai;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Classe en charge de tester la DAO des essais.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<Essai> essaiDao;

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
        this.essaiDao = new GenericDaoHibernate<Essai>(Essai.class);
        this.essaiDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.essaiDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final Essai essai = new Essai();
        essai.setId(1L);

        Mockito.when(this.entityManager.find(Essai.class, essai.getId())).thenReturn(essai);

        final Essai essaiReturned = this.essaiDao.get(essai.getId());
        Assert.assertNotNull(essaiReturned);
        Assert.assertNotNull(essaiReturned.getId());
        Assert.assertEquals(1L, essaiReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final Essai essai = new Essai();
        essai.setId(1L);
        Mockito.when(this.entityManager.merge(essai)).thenReturn(essai);
        final Essai essaiSaved = this.essaiDao.save(essai);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(essaiSaved);
        Assert.assertEquals(essai, essaiSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final Essai essai = new Essai();
        Mockito.when(this.essaiDao.get(essai.getId())).thenReturn(essai);
        this.essaiDao.remove(essai);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
