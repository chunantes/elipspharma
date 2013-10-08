package fr.pharma.eclipse.dao.hibernate.mock.produit;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.produit.Medicament;

/**
 * Classe en charge de tester la DAO des produit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class MedicamentDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<Medicament> medicamentDao;

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
        this.medicamentDao = new GenericDaoHibernate<Medicament>(Medicament.class);
        this.medicamentDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.medicamentDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final Medicament medicament = new Medicament();
        medicament.setId(1L);

        Mockito.when(this.entityManager.find(Medicament.class, medicament.getId())).thenReturn(medicament);

        final Medicament medicamentReturned = this.medicamentDao.get(medicament.getId());
        Assert.assertNotNull(medicamentReturned);
        Assert.assertNotNull(medicamentReturned.getId());
        Assert.assertEquals(1L, medicamentReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final Medicament medicament = new Medicament();
        medicament.setId(1L);
        Mockito.when(this.entityManager.merge(medicament)).thenReturn(medicament);
        final Medicament medicamentSaved = this.medicamentDao.save(medicament);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(medicamentSaved);
        Assert.assertEquals(medicament, medicamentSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final Medicament medicament = new Medicament();
        Mockito.when(this.medicamentDao.get(medicament.getId())).thenReturn(medicament);
        this.medicamentDao.remove(medicament);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
