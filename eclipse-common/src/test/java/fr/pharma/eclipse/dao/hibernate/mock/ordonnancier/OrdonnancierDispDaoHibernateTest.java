package fr.pharma.eclipse.dao.hibernate.mock.ordonnancier;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.ordonnancier.OrdonnancierDisp;

/**
 * Classe en charge de tester la DAO des ordonnancierDisps.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class OrdonnancierDispDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<OrdonnancierDisp> ordonnancierDispDao;

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
        this.ordonnancierDispDao = new GenericDaoHibernate<OrdonnancierDisp>(OrdonnancierDisp.class);
        this.ordonnancierDispDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.ordonnancierDispDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final OrdonnancierDisp ordonnancierDisp = new OrdonnancierDisp();
        ordonnancierDisp.setId(1L);

        Mockito.when(this.entityManager.find(OrdonnancierDisp.class, ordonnancierDisp.getId())).thenReturn(ordonnancierDisp);

        final OrdonnancierDisp ordonnancierDispReturned = this.ordonnancierDispDao.get(ordonnancierDisp.getId());
        Assert.assertNotNull(ordonnancierDispReturned);
        Assert.assertNotNull(ordonnancierDispReturned.getId());
        Assert.assertEquals(1L, ordonnancierDispReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final OrdonnancierDisp ordonnancierDisp = new OrdonnancierDisp();
        ordonnancierDisp.setId(1L);
        Mockito.when(this.entityManager.merge(ordonnancierDisp)).thenReturn(ordonnancierDisp);
        final OrdonnancierDisp ordonnancierDispSaved = this.ordonnancierDispDao.save(ordonnancierDisp);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(ordonnancierDispSaved);
        Assert.assertEquals(ordonnancierDisp, ordonnancierDispSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final OrdonnancierDisp ordonnancierDisp = new OrdonnancierDisp();
        Mockito.when(this.ordonnancierDispDao.get(ordonnancierDisp.getId())).thenReturn(ordonnancierDisp);
        this.ordonnancierDispDao.remove(ordonnancierDisp);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
