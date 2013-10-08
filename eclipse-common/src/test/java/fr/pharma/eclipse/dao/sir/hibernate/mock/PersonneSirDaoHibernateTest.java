package fr.pharma.eclipse.dao.sir.hibernate.mock;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.sir.hibernate.GenericSirDaoHibernate;
import fr.pharma.eclipse.domain.model.sir.PersonneSir;

/**
 * Classe en charge de tester la DAO des personnes SIR.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PersonneSirDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericSirDaoHibernate<PersonneSir> personneSirDao;

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
        this.personneSirDao = new GenericSirDaoHibernate<PersonneSir>(PersonneSir.class);
        this.personneSirDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.personneSirDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final PersonneSir personneSir = new PersonneSir();
        personneSir.setId(1);

        Mockito.when(this.entityManager.find(PersonneSir.class, personneSir.getId())).thenReturn(personneSir);

        final PersonneSir personneSirReturned = this.personneSirDao.get(personneSir.getId());
        Assert.assertNotNull(personneSirReturned);
        Assert.assertNotNull(personneSirReturned.getId());
        Assert.assertEquals(1L, personneSirReturned.getId().longValue());
    }

}
