package fr.pharma.eclipse.dao.hibernate.mock.acteur;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;

/**
 * Classe en charge de tester la DAO des investigateurs.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class InvestigateurDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<Investigateur> investigateurDao;

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
        this.investigateurDao = new GenericDaoHibernate<Investigateur>(Investigateur.class);
        this.investigateurDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.investigateurDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final Investigateur investigateur = new Investigateur();
        investigateur.setId(1L);

        Mockito.when(this.entityManager.find(Investigateur.class, investigateur.getId())).thenReturn(investigateur);

        final Investigateur investigateurReturned = this.investigateurDao.get(investigateur.getId());
        Assert.assertNotNull(investigateurReturned);
        Assert.assertNotNull(investigateurReturned.getId());
        Assert.assertEquals(1L, investigateurReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final Investigateur investigateur = new Investigateur();
        investigateur.setId(1L);
        Mockito.when(this.entityManager.merge(investigateur)).thenReturn(investigateur);
        final Investigateur investigateurSaved = this.investigateurDao.save(investigateur);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(investigateurSaved);
        Assert.assertEquals(investigateur, investigateurSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final Investigateur investigateur = new Investigateur();
        Mockito.when(this.investigateurDao.get(investigateur.getId())).thenReturn(investigateur);
        this.investigateurDao.remove(investigateur);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
