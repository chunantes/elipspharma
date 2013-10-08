package fr.pharma.eclipse.dao.hibernate.mock.surcout;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.surcout.Grille;

/**
 * Classe en charge de tester la DAO des grilles.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GrilleDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<Grille> grilleDao;

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
        this.grilleDao = new GenericDaoHibernate<Grille>(Grille.class);
        this.grilleDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.grilleDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final Grille grille = new Grille();
        grille.setId(1L);

        Mockito.when(this.entityManager.find(Grille.class, grille.getId())).thenReturn(grille);

        final Grille grilleReturned = this.grilleDao.get(grille.getId());
        Assert.assertNotNull(grilleReturned);
        Assert.assertNotNull(grilleReturned.getId());
        Assert.assertEquals(1L, grilleReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final Grille grille = new Grille();
        grille.setId(1L);
        Mockito.when(this.entityManager.merge(grille)).thenReturn(grille);
        final Grille grilleSaved = this.grilleDao.save(grille);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(grilleSaved);
        Assert.assertEquals(grille, grilleSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final Grille grille = new Grille();
        Mockito.when(this.grilleDao.get(grille.getId())).thenReturn(grille);
        this.grilleDao.remove(grille);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
