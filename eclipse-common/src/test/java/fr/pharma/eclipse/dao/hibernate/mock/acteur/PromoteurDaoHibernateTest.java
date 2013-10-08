package fr.pharma.eclipse.dao.hibernate.mock.acteur;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;

/**
 * Classe en charge de tester la DAO des promoteurs.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PromoteurDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<Promoteur> promoteurDao;

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
        this.promoteurDao = new GenericDaoHibernate<Promoteur>(Promoteur.class);
        this.promoteurDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.promoteurDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final Promoteur promoteur = new Promoteur();
        promoteur.setId(1L);

        Mockito.when(this.entityManager.find(Promoteur.class, promoteur.getId())).thenReturn(promoteur);

        final Promoteur promoteurReturned = this.promoteurDao.get(promoteur.getId());
        Assert.assertNotNull(promoteurReturned);
        Assert.assertNotNull(promoteurReturned.getId());
        Assert.assertEquals(1L, promoteurReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final Promoteur promoteur = new Promoteur();
        promoteur.setId(1L);
        Mockito.when(this.entityManager.merge(promoteur)).thenReturn(promoteur);
        final Promoteur promoteurSaved = this.promoteurDao.save(promoteur);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(promoteurSaved);
        Assert.assertEquals(promoteur, promoteurSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final Promoteur promoteur = new Promoteur();
        Mockito.when(this.promoteurDao.get(promoteur.getId())).thenReturn(promoteur);
        this.promoteurDao.remove(promoteur);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
