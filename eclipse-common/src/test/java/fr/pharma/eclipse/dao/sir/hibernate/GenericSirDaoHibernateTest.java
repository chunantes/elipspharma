package fr.pharma.eclipse.dao.sir.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.impl.CriteriaImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.stockage.PharmacieSearchCriteria;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.sir.common.BeanSirObject;

/**
 * Classe en charge de tester la DAO Hibernate dédié aux objets SIR.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GenericSirDaoHibernateTest {
    /**
     * GenericSirDaoHibernate à tester.
     */
    private GenericSirDaoHibernate<BeanSirObject> genericDao;

    /**
     * EntityManager.
     */
    private EntityManager entityManager;

    /**
     * Session.
     */
    private Session session;

    /**
     * Méthode d'initialisation du test.
     */
    @Before
    public void init() {
        this.entityManager = Mockito.mock(EntityManager.class);
        this.session = Mockito.mock(Session.class);

        Mockito.when(this.entityManager.getDelegate()).thenReturn(this.session);

        this.genericDao = new GenericSirDaoHibernate<BeanSirObject>(BeanSirObject.class);
        this.genericDao.setEntityManager(this.entityManager);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.genericDao = null;
        this.entityManager = null;
        this.session = null;
    }

    /**
     * Méthode en charge de tester les données d'init du test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.genericDao.getEntityManager());
        Assert.assertNotNull(this.genericDao.getPersistentClass());
    }

    /**
     * Méthode en charge de tester le get avec une exception
     * IllegalArgumentException.
     */
    @Test
    public void getKOIllegalArgumentException() {
        final Integer id = 1;
        final IllegalArgumentException e = new IllegalArgumentException();
        Mockito.doThrow(e).when(this.entityManager).find(BeanSirObject.class, id);
        this.genericDao.get(id);
        Mockito.verify(this.entityManager).find(BeanSirObject.class, id);
    }

    /**
     * Méthode en charge de tester le get avec une exception
     * IllegalStateException.
     */
    @Test
    public void getKOIllegalStateException() {
        final Integer id = 1;
        final IllegalStateException e = new IllegalStateException();
        Mockito.doThrow(e).when(this.entityManager).find(BeanSirObject.class, id);
        this.genericDao.get(id);
        Mockito.verify(this.entityManager).find(BeanSirObject.class, id);
    }

    /**
     * Méthode en charge de tester la récupération de la session sans exception.
     */
    @Test
    public void getCurrentSessionOK() {
        Mockito.when(this.entityManager.getDelegate()).thenReturn(null);
        final Session result = this.genericDao.getCurrentSession();
        Assert.assertNull(result);
    }

    /**
     * Méthode en charge de tester la récupération de la session avec exception.
     */
    @Test
    public void getCurrentSessionKO() {
        final IllegalStateException e = new IllegalStateException();
        Mockito.when(this.entityManager.getDelegate()).thenThrow(e);
        final Session result = this.genericDao.getCurrentSession();
        Assert.assertNull(result);
    }

    /**
     * Méthode en charge de tester la gestion des exceptions.
     */
    @Test
    public void handleException() {
        final IllegalStateException e = new IllegalStateException();
        this.genericDao.handleException("handleException", e, "detailMessage");
    }

    /**
     * Méthode en charge de tester la méthode getAll.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void getAll() {
        final Criteria criteria = Mockito.mock(Criteria.class);
        final List<BeanObject> expected = new ArrayList<BeanObject>();
        Mockito.when(this.session.createCriteria((Class) Matchers.anyObject())).thenReturn(criteria);
        Mockito.when(criteria.list()).thenReturn(expected);
        final List<BeanSirObject> result = this.genericDao.getAll();
        Mockito.verify(this.session).createCriteria((Class) Matchers.anyObject());
        Mockito.verify(criteria).list();
        Assert.assertNotNull(result);
        Assert.assertEquals(expected.size(), result.size());
    }

    /**
     * Méthode en charge de tester l'application des critères au niveau tri.
     */
    @Test
    public void testApplySortCriteriaActivieOrderNull() {
        final PharmacieSearchCriteria criteria = new PharmacieSearchCriteria();
        final Criteria crit = new CriteriaImpl("", null);
        this.genericDao.applySortCriteria(crit, criteria);
    }

    /**
     * Méthode en charge de tester l'application des critères au niveau tri.
     */
    @Test
    public void testApplySortCriteriaActiveOrderNotNullAndAscending() {
        final PharmacieSearchCriteria criteria = new PharmacieSearchCriteria();
        criteria.setActiveOrder("nom");
        final Criteria crit = new CriteriaImpl("", null);
        this.genericDao.applySortCriteria(crit, criteria);
    }

    /**
     * Méthode en charge de tester l'application des critères au niveau tri.
     */
    @Test
    public void testApplySortCriteriaActiveOrderNotNullAndDescending() {
        final PharmacieSearchCriteria criteria = new PharmacieSearchCriteria();
        criteria.setActiveOrder("nom");
        criteria.setAscending(false);
        final Criteria crit = new CriteriaImpl("", null);
        this.genericDao.applySortCriteria(crit, criteria);
    }

    /**
     * Méthode en charge de tester l'application des critères au niveau tri.
     */
    @Test
    public void testApplySortCriteriaCaseSensitive() {
        final PharmacieSearchCriteria criteria = new PharmacieSearchCriteria();
        criteria.setActiveOrder("nom");
        criteria.setAscending(false);
        criteria.setCaseSensitiveOrder(true);
        final Criteria crit = new CriteriaImpl("", null);
        this.genericDao.applySortCriteria(crit, criteria);
    }

    /**
     * Méthode en charge de tester l'application des critères au niveau tri.
     */
    @Test
    public void testApplySortCriteriaCaseNotSensitive() {
        final PharmacieSearchCriteria criteria = new PharmacieSearchCriteria();
        criteria.setActiveOrder("nom");
        criteria.setAscending(false);
        criteria.setCaseSensitiveOrder(false);
        final Criteria crit = new CriteriaImpl("", null);
        this.genericDao.applySortCriteria(crit, criteria);
    }
}
