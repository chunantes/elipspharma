package fr.pharma.eclipse.dao.hibernate.mock.evenement;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.evenement.Evenement;

/**
 * Classe en charge de tester la DAO des evenements.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EvenementDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<Evenement> evenementDao;

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
        this.evenementDao = new GenericDaoHibernate<Evenement>(Evenement.class);
        this.evenementDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.evenementDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final Evenement evenement = new Evenement();
        evenement.setId(1L);

        Mockito.when(this.entityManager.find(Evenement.class, evenement.getId())).thenReturn(evenement);

        final Evenement evenementReturned = this.evenementDao.get(evenement.getId());
        Assert.assertNotNull(evenementReturned);
        Assert.assertNotNull(evenementReturned.getId());
        Assert.assertEquals(1L, evenementReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final Evenement evenement = new Evenement();
        evenement.setId(1L);
        Mockito.when(this.entityManager.merge(evenement)).thenReturn(evenement);
        final Evenement evenementSaved = this.evenementDao.save(evenement);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(evenementSaved);
        Assert.assertEquals(evenement, evenementSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final Evenement evenement = new Evenement();
        Mockito.when(this.evenementDao.get(evenement.getId())).thenReturn(evenement);
        this.evenementDao.remove(evenement);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
