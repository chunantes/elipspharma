package fr.pharma.eclipse.dao.hibernate.mock.dispensation;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.dispensation.ElementToCheck;

/**
 * Classe en charge de tester la DAO des elementToChecks.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ElementToCheckDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<ElementToCheck> elementToCheckDao;

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
        this.elementToCheckDao = new GenericDaoHibernate<ElementToCheck>(ElementToCheck.class);
        this.elementToCheckDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.elementToCheckDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final ElementToCheck elementToCheck = new ElementToCheck();
        elementToCheck.setId(1L);

        Mockito.when(this.entityManager.find(ElementToCheck.class, elementToCheck.getId())).thenReturn(elementToCheck);

        final ElementToCheck elementToCheckReturned = this.elementToCheckDao.get(elementToCheck.getId());
        Assert.assertNotNull(elementToCheckReturned);
        Assert.assertNotNull(elementToCheckReturned.getId());
        Assert.assertEquals(1L, elementToCheckReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final ElementToCheck elementToCheck = new ElementToCheck();
        elementToCheck.setId(1L);
        Mockito.when(this.entityManager.merge(elementToCheck)).thenReturn(elementToCheck);
        final ElementToCheck elementToCheckSaved = this.elementToCheckDao.save(elementToCheck);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(elementToCheckSaved);
        Assert.assertEquals(elementToCheck, elementToCheckSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final ElementToCheck elementToCheck = new ElementToCheck();
        Mockito.when(this.elementToCheckDao.get(elementToCheck.getId())).thenReturn(elementToCheck);
        this.elementToCheckDao.remove(elementToCheck);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
