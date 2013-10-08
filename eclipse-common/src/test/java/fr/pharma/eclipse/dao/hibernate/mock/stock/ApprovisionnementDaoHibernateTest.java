package fr.pharma.eclipse.dao.hibernate.mock.stock;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;

/**
 * Classe en charge de tester la DAO des approvisionnements.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ApprovisionnementDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<Approvisionnement> approvisionnementDao;

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
        this.approvisionnementDao = new GenericDaoHibernate<Approvisionnement>(Approvisionnement.class);
        this.approvisionnementDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.approvisionnementDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final Approvisionnement approvisionnement = new Approvisionnement();
        approvisionnement.setId(1L);

        Mockito.when(this.entityManager.find(Approvisionnement.class, approvisionnement.getId())).thenReturn(approvisionnement);

        final Approvisionnement approvisionnementReturned = this.approvisionnementDao.get(approvisionnement.getId());
        Assert.assertNotNull(approvisionnementReturned);
        Assert.assertNotNull(approvisionnementReturned.getId());
        Assert.assertEquals(1L, approvisionnementReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final Approvisionnement approvisionnement = new Approvisionnement();
        approvisionnement.setId(1L);
        Mockito.when(this.entityManager.merge(approvisionnement)).thenReturn(approvisionnement);
        final Approvisionnement approvisionnementSaved = this.approvisionnementDao.save(approvisionnement);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(approvisionnementSaved);
        Assert.assertEquals(approvisionnement, approvisionnementSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final Approvisionnement approvisionnement = new Approvisionnement();
        Mockito.when(this.approvisionnementDao.get(approvisionnement.getId())).thenReturn(approvisionnement);
        this.approvisionnementDao.remove(approvisionnement);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
