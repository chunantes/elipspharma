package fr.pharma.eclipse.dao.hibernate.mock.incident;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.incident.Incident;

/**
 * Classe en charge de tester la DAO des incident.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class IncidentDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<Incident> incidentDao;

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
        this.incidentDao = new GenericDaoHibernate<Incident>(Incident.class);
        this.incidentDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.incidentDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final Incident evenement = new Incident();
        evenement.setId(1L);

        Mockito.when(this.entityManager.find(Incident.class, evenement.getId())).thenReturn(evenement);

        final Incident evenementReturned = this.incidentDao.get(evenement.getId());
        Assert.assertNotNull(evenementReturned);
        Assert.assertNotNull(evenementReturned.getId());
        Assert.assertEquals(1L, evenementReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final Incident evenement = new Incident();
        evenement.setId(1L);
        Mockito.when(this.entityManager.merge(evenement)).thenReturn(evenement);
        final Incident evenementSaved = this.incidentDao.save(evenement);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(evenementSaved);
        Assert.assertEquals(evenement, evenementSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final Incident evenement = new Incident();
        Mockito.when(this.incidentDao.get(evenement.getId())).thenReturn(evenement);
        this.incidentDao.remove(evenement);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
