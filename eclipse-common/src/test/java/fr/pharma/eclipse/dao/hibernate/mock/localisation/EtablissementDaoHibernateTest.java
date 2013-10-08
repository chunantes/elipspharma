package fr.pharma.eclipse.dao.hibernate.mock.localisation;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.model.localisation.Etablissement;

/**
 * Classe en charge de tester la DAO des établissements.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EtablissementDaoHibernateTest {
    /**
     * Dao.
     */
    private GenericDaoHibernate<Etablissement> etablissementDao;

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
        this.etablissementDao = new GenericDaoHibernate<Etablissement>(Etablissement.class);
        this.etablissementDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.etablissementDao = null;
        this.entityManager = null;
    }

    /**
     * Méthode de test Get.
     */
    @Test
    public void get() {
        final Etablissement etablissement = new Etablissement();
        etablissement.setId(1L);

        Mockito.when(this.entityManager.find(Etablissement.class, etablissement.getId())).thenReturn(etablissement);

        final Etablissement etablissementReturned = this.etablissementDao.get(etablissement.getId());
        Assert.assertNotNull(etablissementReturned);
        Assert.assertNotNull(etablissementReturned.getId());
        Assert.assertEquals(1L, etablissementReturned.getId().longValue());
    }

    /**
     * Méthode de test Save.
     */
    @Test
    public void save() {
        final Etablissement etablissement = new Etablissement();
        etablissement.setId(1L);
        Mockito.when(this.entityManager.merge(etablissement)).thenReturn(etablissement);
        final Etablissement etablissementSaved = this.etablissementDao.save(etablissement);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(Matchers.any());
        Assert.assertNotNull(etablissementSaved);
        Assert.assertEquals(etablissement, etablissementSaved);
    }

    /**
     * Méthode de test Delete.
     */
    @Test
    public void delete() {
        final Etablissement etablissement = new Etablissement();
        Mockito.when(this.etablissementDao.get(etablissement.getId())).thenReturn(etablissement);
        this.etablissementDao.remove(etablissement);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.any());
    }

}
