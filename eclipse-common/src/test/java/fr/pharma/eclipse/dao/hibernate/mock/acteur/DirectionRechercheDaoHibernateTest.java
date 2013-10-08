package fr.pharma.eclipse.dao.hibernate.mock.acteur;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.acteur.DirectionRecherche;

/**
 * Classe en charge de tester la DAO des directionRecherches.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DirectionRechercheDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<DirectionRecherche> directionRechercheDao;

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
        this.directionRechercheDao = new GenericDaoHibernate<DirectionRecherche>(DirectionRecherche.class);
        this.directionRechercheDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.directionRechercheDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final DirectionRecherche directionRecherche = new DirectionRecherche();
        directionRecherche.setId(1L);

        Mockito.when(this.entityManager.find(DirectionRecherche.class, directionRecherche.getId())).thenReturn(directionRecherche);

        final DirectionRecherche directionRechercheReturned = this.directionRechercheDao.get(directionRecherche.getId());
        Assert.assertNotNull(directionRechercheReturned);
        Assert.assertNotNull(directionRechercheReturned.getId());
        Assert.assertEquals(1L, directionRechercheReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final DirectionRecherche directionRecherche = new DirectionRecherche();
        directionRecherche.setId(1L);
        Mockito.when(this.entityManager.merge(directionRecherche)).thenReturn(directionRecherche);
        final DirectionRecherche directionRechercheSaved = this.directionRechercheDao.save(directionRecherche);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(directionRechercheSaved);
        Assert.assertEquals(directionRecherche, directionRechercheSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final DirectionRecherche directionRecherche = new DirectionRecherche();
        Mockito.when(this.directionRechercheDao.get(directionRecherche.getId())).thenReturn(directionRecherche);
        this.directionRechercheDao.remove(directionRecherche);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
