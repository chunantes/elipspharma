package fr.pharma.eclipse.dao.hibernate.mock.acteur;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.acteur.ContactPromoteur;

/**
 * Classe en charge de tester la DAO des contacts Promoteur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ContactPromoteurDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<ContactPromoteur> contactPromoteurDao;

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
        this.contactPromoteurDao = new GenericDaoHibernate<ContactPromoteur>(ContactPromoteur.class);
        this.contactPromoteurDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.contactPromoteurDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final ContactPromoteur contactPromoteur = new ContactPromoteur();
        contactPromoteur.setId(1L);

        Mockito.when(this.entityManager.find(ContactPromoteur.class, contactPromoteur.getId())).thenReturn(contactPromoteur);

        final ContactPromoteur contactPromoteurReturned = this.contactPromoteurDao.get(contactPromoteur.getId());
        Assert.assertNotNull(contactPromoteurReturned);
        Assert.assertNotNull(contactPromoteurReturned.getId());
        Assert.assertEquals(1L, contactPromoteurReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final ContactPromoteur contactPromoteur = new ContactPromoteur();
        contactPromoteur.setId(1L);
        Mockito.when(this.entityManager.merge(contactPromoteur)).thenReturn(contactPromoteur);
        final ContactPromoteur contactPromoteurSaved = this.contactPromoteurDao.save(contactPromoteur);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(contactPromoteurSaved);
        Assert.assertEquals(contactPromoteur, contactPromoteurSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final ContactPromoteur contactPromoteur = new ContactPromoteur();
        Mockito.when(this.contactPromoteurDao.get(contactPromoteur.getId())).thenReturn(contactPromoteur);
        this.contactPromoteurDao.remove(contactPromoteur);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
