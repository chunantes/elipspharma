package fr.pharma.eclipse.dao.hibernate.mock.acteur;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.acteur.ArcPromoteur;

/**
 * Classe en charge de tester la DAO des ARC Promoteur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ArcPromoteurDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<ArcPromoteur> arcPromoteurDao;

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
        this.arcPromoteurDao = new GenericDaoHibernate<ArcPromoteur>(ArcPromoteur.class);
        this.arcPromoteurDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.arcPromoteurDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final ArcPromoteur arcPromoteur = new ArcPromoteur();
        arcPromoteur.setId(1L);

        Mockito.when(this.entityManager.find(ArcPromoteur.class, arcPromoteur.getId())).thenReturn(arcPromoteur);

        final ArcPromoteur arcPromoteurReturned = this.arcPromoteurDao.get(arcPromoteur.getId());
        Assert.assertNotNull(arcPromoteurReturned);
        Assert.assertNotNull(arcPromoteurReturned.getId());
        Assert.assertEquals(1L, arcPromoteurReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final ArcPromoteur arcPromoteur = new ArcPromoteur();
        arcPromoteur.setId(1L);
        Mockito.when(this.entityManager.merge(arcPromoteur)).thenReturn(arcPromoteur);
        final ArcPromoteur arcPromoteurSaved = this.arcPromoteurDao.save(arcPromoteur);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(arcPromoteurSaved);
        Assert.assertEquals(arcPromoteur, arcPromoteurSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final ArcPromoteur arcPromoteur = new ArcPromoteur();
        Mockito.when(this.arcPromoteurDao.get(arcPromoteur.getId())).thenReturn(arcPromoteur);
        this.arcPromoteurDao.remove(arcPromoteur);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
