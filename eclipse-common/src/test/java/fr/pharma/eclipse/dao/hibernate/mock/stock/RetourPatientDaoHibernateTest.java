package fr.pharma.eclipse.dao.hibernate.mock.stock;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.stock.RetourPatient;

/**
 * Classe en charge de tester la DAO des retours patients..
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class RetourPatientDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<RetourPatient> retourPatientDao;

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
        this.retourPatientDao = new GenericDaoHibernate<RetourPatient>(RetourPatient.class);
        this.retourPatientDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.retourPatientDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final RetourPatient retourPatient = new RetourPatient();
        retourPatient.setId(1L);

        Mockito.when(this.entityManager.find(RetourPatient.class, retourPatient.getId())).thenReturn(retourPatient);

        final RetourPatient retourPatientReturned = this.retourPatientDao.get(retourPatient.getId());
        Assert.assertNotNull(retourPatientReturned);
        Assert.assertNotNull(retourPatientReturned.getId());
        Assert.assertEquals(1L, retourPatientReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final RetourPatient retourPatient = new RetourPatient();
        retourPatient.setId(1L);
        Mockito.when(this.entityManager.merge(retourPatient)).thenReturn(retourPatient);
        final RetourPatient retourPatientSaved = this.retourPatientDao.save(retourPatient);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(retourPatientSaved);
        Assert.assertEquals(retourPatient, retourPatientSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final RetourPatient retourPatient = new RetourPatient();
        Mockito.when(this.retourPatientDao.get(retourPatient.getId())).thenReturn(retourPatient);
        this.retourPatientDao.remove(retourPatient);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
