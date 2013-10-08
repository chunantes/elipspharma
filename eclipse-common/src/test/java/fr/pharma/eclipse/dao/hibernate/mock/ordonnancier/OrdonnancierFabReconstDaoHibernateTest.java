package fr.pharma.eclipse.dao.hibernate.mock.ordonnancier;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.ordonnancier.OrdonnancierFabReconst;

/**
 * Classe en charge de tester la DAO des ordonnancierFabReconsts.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class OrdonnancierFabReconstDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<OrdonnancierFabReconst> ordonnancierFabReconstDao;

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
        this.ordonnancierFabReconstDao = new GenericDaoHibernate<OrdonnancierFabReconst>(OrdonnancierFabReconst.class);
        this.ordonnancierFabReconstDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.ordonnancierFabReconstDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final OrdonnancierFabReconst ordonnancierFabReconst = new OrdonnancierFabReconst();
        ordonnancierFabReconst.setId(1L);

        Mockito.when(this.entityManager.find(OrdonnancierFabReconst.class, ordonnancierFabReconst.getId())).thenReturn(ordonnancierFabReconst);

        final OrdonnancierFabReconst ordonnancierFabReconstReturned = this.ordonnancierFabReconstDao.get(ordonnancierFabReconst.getId());
        Assert.assertNotNull(ordonnancierFabReconstReturned);
        Assert.assertNotNull(ordonnancierFabReconstReturned.getId());
        Assert.assertEquals(1L, ordonnancierFabReconstReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final OrdonnancierFabReconst ordonnancierFabReconst = new OrdonnancierFabReconst();
        ordonnancierFabReconst.setId(1L);
        Mockito.when(this.entityManager.merge(ordonnancierFabReconst)).thenReturn(ordonnancierFabReconst);
        final OrdonnancierFabReconst ordonnancierFabReconstSaved = this.ordonnancierFabReconstDao.save(ordonnancierFabReconst);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(ordonnancierFabReconstSaved);
        Assert.assertEquals(ordonnancierFabReconst, ordonnancierFabReconstSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final OrdonnancierFabReconst ordonnancierFabReconst = new OrdonnancierFabReconst();
        Mockito.when(this.ordonnancierFabReconstDao.get(ordonnancierFabReconst.getId())).thenReturn(ordonnancierFabReconst);
        this.ordonnancierFabReconstDao.remove(ordonnancierFabReconst);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
