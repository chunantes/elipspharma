package fr.pharma.eclipse.dao.hibernate.mock.habilitation;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;

/**
 * Classe en charge de tester la DAO des établissements.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class HabilitationDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<Habilitation> habilitationDao;

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
        this.habilitationDao = new GenericDaoHibernate<Habilitation>(Habilitation.class);
        this.habilitationDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.habilitationDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final Habilitation habilitation = new Habilitation();
        habilitation.setId(1L);

        Mockito.when(this.entityManager.find(Habilitation.class, habilitation.getId())).thenReturn(habilitation);

        final Habilitation habilitationReturned = this.habilitationDao.get(habilitation.getId());
        Assert.assertNotNull(habilitationReturned);
        Assert.assertNotNull(habilitationReturned.getId());
        Assert.assertEquals(1L, habilitationReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final Habilitation habilitation = new Habilitation();
        habilitation.setId(1L);
        Mockito.when(this.entityManager.merge(habilitation)).thenReturn(habilitation);
        final Habilitation habilitationSaved = this.habilitationDao.save(habilitation);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(habilitationSaved);
        Assert.assertEquals(habilitation, habilitationSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final Habilitation habilitation = new Habilitation();
        Mockito.when(this.habilitationDao.get(habilitation.getId())).thenReturn(habilitation);
        this.habilitationDao.remove(habilitation);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
