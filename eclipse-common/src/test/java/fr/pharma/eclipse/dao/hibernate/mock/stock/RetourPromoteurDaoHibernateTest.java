package fr.pharma.eclipse.dao.hibernate.mock.stock;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.stock.RetourPromoteur;

/**
 * Classe en charge de tester la DAO des retourPromoteurs.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class RetourPromoteurDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<RetourPromoteur> retourPromoteurDao;

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
        this.retourPromoteurDao = new GenericDaoHibernate<RetourPromoteur>(RetourPromoteur.class);
        this.retourPromoteurDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.retourPromoteurDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final RetourPromoteur retourPromoteur = new RetourPromoteur();
        retourPromoteur.setId(1L);

        Mockito.when(this.entityManager.find(RetourPromoteur.class, retourPromoteur.getId())).thenReturn(retourPromoteur);

        final RetourPromoteur retourPromoteurReturned = this.retourPromoteurDao.get(retourPromoteur.getId());
        Assert.assertNotNull(retourPromoteurReturned);
        Assert.assertNotNull(retourPromoteurReturned.getId());
        Assert.assertEquals(1L, retourPromoteurReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final RetourPromoteur retourPromoteur = new RetourPromoteur();
        retourPromoteur.setId(1L);
        Mockito.when(this.entityManager.merge(retourPromoteur)).thenReturn(retourPromoteur);
        final RetourPromoteur retourPromoteurSaved = this.retourPromoteurDao.save(retourPromoteur);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(retourPromoteurSaved);
        Assert.assertEquals(retourPromoteur, retourPromoteurSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final RetourPromoteur retourPromoteur = new RetourPromoteur();
        Mockito.when(this.retourPromoteurDao.get(retourPromoteur.getId())).thenReturn(retourPromoteur);
        this.retourPromoteurDao.remove(retourPromoteur);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
