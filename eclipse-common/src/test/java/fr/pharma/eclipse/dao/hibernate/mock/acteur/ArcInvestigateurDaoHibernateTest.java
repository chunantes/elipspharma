package fr.pharma.eclipse.dao.hibernate.mock.acteur;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.acteur.ArcInvestigateur;

/**
 * Classe en charge de tester la DAO des ARC Investigateurs.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ArcInvestigateurDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<ArcInvestigateur> arcInvestigateurDao;

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
        this.arcInvestigateurDao = new GenericDaoHibernate<ArcInvestigateur>(ArcInvestigateur.class);
        this.arcInvestigateurDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.arcInvestigateurDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final ArcInvestigateur arcInvestigateur = new ArcInvestigateur();
        arcInvestigateur.setId(1L);

        Mockito.when(this.entityManager.find(ArcInvestigateur.class, arcInvestigateur.getId())).thenReturn(arcInvestigateur);

        final ArcInvestigateur arcInvestigateurReturned = this.arcInvestigateurDao.get(arcInvestigateur.getId());
        Assert.assertNotNull(arcInvestigateurReturned);
        Assert.assertNotNull(arcInvestigateurReturned.getId());
        Assert.assertEquals(1L, arcInvestigateurReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final ArcInvestigateur arcInvestigateur = new ArcInvestigateur();
        arcInvestigateur.setId(1L);
        Mockito.when(this.entityManager.merge(arcInvestigateur)).thenReturn(arcInvestigateur);
        final ArcInvestigateur arcInvestigateurSaved = this.arcInvestigateurDao.save(arcInvestigateur);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(arcInvestigateurSaved);
        Assert.assertEquals(arcInvestigateur, arcInvestigateurSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final ArcInvestigateur arcInvestigateur = new ArcInvestigateur();
        Mockito.when(this.arcInvestigateurDao.get(arcInvestigateur.getId())).thenReturn(arcInvestigateur);
        this.arcInvestigateurDao.remove(arcInvestigateur);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
