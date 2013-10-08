package fr.pharma.eclipse.dao.hibernate.mock.stock;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.stock.AutreSortie;

/**
 * Classe en charge de tester la DAO des autreSorties.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AutreSortieDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<AutreSortie> autreSortieDao;

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
        this.autreSortieDao = new GenericDaoHibernate<AutreSortie>(AutreSortie.class);
        this.autreSortieDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.autreSortieDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final AutreSortie autreSortie = new AutreSortie();
        autreSortie.setId(1L);

        Mockito.when(this.entityManager.find(AutreSortie.class, autreSortie.getId())).thenReturn(autreSortie);

        final AutreSortie autreSortieReturned = this.autreSortieDao.get(autreSortie.getId());
        Assert.assertNotNull(autreSortieReturned);
        Assert.assertNotNull(autreSortieReturned.getId());
        Assert.assertEquals(1L, autreSortieReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final AutreSortie autreSortie = new AutreSortie();
        autreSortie.setId(1L);
        Mockito.when(this.entityManager.merge(autreSortie)).thenReturn(autreSortie);
        final AutreSortie autreSortieSaved = this.autreSortieDao.save(autreSortie);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(autreSortieSaved);
        Assert.assertEquals(autreSortie, autreSortieSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final AutreSortie autreSortie = new AutreSortie();
        Mockito.when(this.autreSortieDao.get(autreSortie.getId())).thenReturn(autreSortie);
        this.autreSortieDao.remove(autreSortie);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
