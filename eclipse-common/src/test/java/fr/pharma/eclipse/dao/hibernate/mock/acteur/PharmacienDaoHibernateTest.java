package fr.pharma.eclipse.dao.hibernate.mock.acteur;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;

/**
 * Classe en charge de tester la DAO des pharmaciens.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PharmacienDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<Pharmacien> pharmacienDao;

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
        this.pharmacienDao = new GenericDaoHibernate<Pharmacien>(Pharmacien.class);
        this.pharmacienDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.pharmacienDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final Pharmacien pharmacien = new Pharmacien();
        pharmacien.setId(1L);

        Mockito.when(this.entityManager.find(Pharmacien.class, pharmacien.getId())).thenReturn(pharmacien);

        final Pharmacien pharmacienReturned = this.pharmacienDao.get(pharmacien.getId());
        Assert.assertNotNull(pharmacienReturned);
        Assert.assertNotNull(pharmacienReturned.getId());
        Assert.assertEquals(1L, pharmacienReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final Pharmacien pharmacien = new Pharmacien();
        pharmacien.setId(1L);
        Mockito.when(this.entityManager.merge(pharmacien)).thenReturn(pharmacien);
        final Pharmacien pharmacienSaved = this.pharmacienDao.save(pharmacien);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(pharmacienSaved);
        Assert.assertEquals(pharmacien, pharmacienSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final Pharmacien pharmacien = new Pharmacien();
        Mockito.when(this.pharmacienDao.get(pharmacien.getId())).thenReturn(pharmacien);
        this.pharmacienDao.remove(pharmacien);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
