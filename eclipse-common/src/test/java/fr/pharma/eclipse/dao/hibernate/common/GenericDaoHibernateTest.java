package fr.pharma.eclipse.dao.hibernate.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TransactionRequiredException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.impl.CriteriaImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.comparator.stockage.StockageComparator;
import fr.pharma.eclipse.domain.criteria.stockage.PharmacieSearchCriteria;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;

/**
 * Classe en charge de tester la DAO Hibernate.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GenericDaoHibernateTest {
    /**
     * GenericDaoHibernate à tester.
     */
    private GenericDaoHibernate<BeanObject> genericDao;

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

        this.genericDao = new GenericDaoHibernate<BeanObject>(BeanObject.class);
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
        final Long id = 1L;
        final IllegalArgumentException e = new IllegalArgumentException();
        Mockito.doThrow(e).when(this.entityManager).find(BeanObject.class, id);
        this.genericDao.get(id);
        Mockito.verify(this.entityManager).find(BeanObject.class, id);
    }

    /**
     * Méthode en charge de tester le get avec une exception
     * IllegalStateException.
     */
    @Test
    public void getKOIllegalStateException() {
        final Long id = 1L;
        final IllegalStateException e = new IllegalStateException();
        Mockito.doThrow(e).when(this.entityManager).find(BeanObject.class, id);
        this.genericDao.get(id);
        Mockito.verify(this.entityManager).find(BeanObject.class, id);
    }

    /**
     * Méthode en charge de tester le save avec une exception
     * IllegalStateException.
     */
    @Test
    public void saveKOIllegalStateException() {
        final BeanObject bean = Mockito.mock(BeanObject.class);
        final IllegalStateException e = new IllegalStateException();
        Mockito.doThrow(e).when(this.entityManager).merge(bean);
        this.genericDao.save(bean);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(bean);
    }

    /**
     * Méthode en charge de tester le save avec une exception
     * IllegalArgumentException.
     */
    @Test
    public void saveKOIllegalArgumentException() {
        final BeanObject bean = Mockito.mock(BeanObject.class);
        final IllegalArgumentException e = new IllegalArgumentException();
        Mockito.doThrow(e).when(this.entityManager).merge(bean);
        this.genericDao.save(bean);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(bean);
    }

    /**
     * Méthode en charge de tester le save avec une exception
     * TransactionRequiredException.
     */
    @Test
    public void saveKOTransactionRequiredException() {
        final BeanObject bean = Mockito.mock(BeanObject.class);
        final TransactionRequiredException e = new TransactionRequiredException();
        Mockito.doThrow(e).when(this.entityManager).merge(bean);
        this.genericDao.save(bean);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(bean);
    }

    /**
     * Méthode en charge de tester le save avec une exception
     * EntityExistsException.
     */
    @Test
    public void saveKOEntityExistsException() {
        final BeanObject bean = Mockito.mock(BeanObject.class);
        final EntityExistsException e = new EntityExistsException();
        Mockito.doThrow(e).when(this.entityManager).merge(bean);
        this.genericDao.save(bean);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(bean);
    }

    /**
     * Méthode en charge de tester le remove avec une exception
     * IllegalStateException.
     */
    @Test
    public void removeKOIllegalStateException() {
        final BeanObject beanObject = Mockito.mock(BeanObject.class);
        final IllegalStateException e = new IllegalStateException();
        Mockito.doThrow(e).when(this.entityManager).remove(Matchers.anyObject());
        this.genericDao.remove(beanObject);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.anyObject());
    }

    /**
     * Méthode en charge de tester le remove avec une exception
     * IllegalArgumentException.
     */
    @Test
    public void removeKOIllegalArgumentException() {
        final BeanObject beanObject = Mockito.mock(BeanObject.class);
        final IllegalArgumentException e = new IllegalArgumentException();
        Mockito.doThrow(e).when(this.entityManager).remove(Matchers.anyObject());
        this.genericDao.remove(beanObject);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.anyObject());
    }

    /**
     * Méthode en charge de tester le remove avec une exception
     * TransactionRequiredException.
     */
    @Test
    public void removeKOTransactionRequiredException() {
        final BeanObject beanObject = Mockito.mock(BeanObject.class);
        final TransactionRequiredException e = new TransactionRequiredException();
        Mockito.doThrow(e).when(this.entityManager).remove(Matchers.anyObject());
        this.genericDao.remove(beanObject);
        Mockito.verify(this.entityManager, Mockito.times(1)).remove(Matchers.anyObject());
    }

    /**
     * Méthode en charge de tester le reattach avec un objet null.
     */
    @Test
    public void reattachWithNull() {
        final BeanObject result = this.genericDao.reattach(null);
        Assert.assertNull(result);
    }

    /**
     * Méthode en charge de tester le reattach avec un objet dont l'identifiant
     * est null.
     */
    @Test
    public void reattachWithIdNull() {
        final BeanObject bean = new Pharmacie();
        final BeanObject result = this.genericDao.reattach(bean);
        Assert.assertNull(bean.getId());
        Assert.assertNull(result.getId());
    }

    /**
     * Méthode en charge de tester le reattach avec un objet comprenant un
     * identifiant (pas d'exception).
     */
    @Test
    public void reattachWithId() {
        final BeanObject bean = new Pharmacie();
        bean.setId(1L);
        Mockito.when(this.entityManager.merge(bean)).thenReturn(bean);
        final BeanObject result = this.genericDao.reattach(bean);
        Assert.assertEquals(1L, bean.getId().longValue());
        Assert.assertEquals(1L, result.getId().longValue());
    }

    /**
     * Méthode en charge de tester le reattach avec une exception
     * IllegalStateException.
     */
    @Test
    public void reattachKOIllegalStateException() {
        final BeanObject bean = Mockito.mock(BeanObject.class);
        final IllegalStateException e = new IllegalStateException();
        Mockito.doThrow(e).when(this.entityManager).merge(bean);
        this.genericDao.reattach(bean);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(bean);
    }

    /**
     * Méthode en charge de tester le reattach avec une exception
     * IllegalArgumentException.
     */
    @Test
    public void reattachKOIllegalArgumentException() {
        final BeanObject bean = Mockito.mock(BeanObject.class);
        final IllegalArgumentException e = new IllegalArgumentException();
        Mockito.doThrow(e).when(this.entityManager).merge(bean);
        this.genericDao.reattach(bean);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(bean);
    }

    /**
     * Méthode en charge de tester le reattach avec une exception
     * TransactionRequiredException.
     */
    @Test
    public void reattachKOTransactionRequiredException() {
        final BeanObject bean = Mockito.mock(BeanObject.class);
        final TransactionRequiredException e = new TransactionRequiredException();
        Mockito.doThrow(e).when(this.entityManager).merge(bean);
        this.genericDao.reattach(bean);
        Mockito.verify(this.entityManager, Mockito.times(1)).merge(bean);
    }

    /**
     * Méthode en charge de tester le dettach sans exception.
     */
    @Test
    public void dettachOK() {
        final BeanObject bean = Mockito.mock(BeanObject.class);
        this.genericDao.dettach(bean);
        Mockito.verify(this.entityManager, Mockito.times(1)).refresh(bean);
    }

    /**
     * Méthode en charge de tester le dettach avec la purge d'une liste qui ont
     * les id null.
     */
    @Test
    public void dettachOKWithPurge() {
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        final Stockage stockage1 = new Stockage();
        stockage1.setNom("nom1");
        final Stockage stockage2 = new Stockage();
        stockage2.setNom("nom2");
        stockage2.setId(1L);
        final SortedSet<Stockage> stockages = new TreeSet<Stockage>(new StockageComparator());
        stockages.add(stockage1);
        stockages.add(stockage2);
        this.genericDao.setNameBeanListsForDetach(Arrays.asList("stockages"));
        Mockito.when(pharmacie.getStockages()).thenReturn(stockages);
        this.genericDao.dettach(pharmacie);
        Mockito.verify(this.entityManager, Mockito.times(1)).refresh(pharmacie);
    }

    /**
     * Méthode en charge de tester le dettach avec une exception
     * IllegalStateException.
     */
    @Test
    public void dettachKOIllegalStateException() {
        final BeanObject bean = Mockito.mock(BeanObject.class);
        final IllegalStateException e = new IllegalStateException();
        Mockito.doThrow(e).when(this.entityManager).refresh(bean);
        this.genericDao.dettach(bean);
        Mockito.verify(this.entityManager, Mockito.times(1)).refresh(bean);
    }

    /**
     * Méthode en charge de tester le dettach avec une exception
     * IllegalArgumentException.
     */
    @Test
    public void dettachKOIllegalArgumentException() {
        final BeanObject bean = Mockito.mock(BeanObject.class);
        final IllegalArgumentException e = new IllegalArgumentException();
        Mockito.doThrow(e).when(this.entityManager).refresh(bean);
        this.genericDao.dettach(bean);
        Mockito.verify(this.entityManager, Mockito.times(1)).refresh(bean);
    }

    /**
     * Méthode en charge de tester le dettach avec une exception
     * TransactionRequiredException.
     */
    @Test
    public void dettachKOTransactionRequiredException() {
        final BeanObject bean = Mockito.mock(BeanObject.class);
        final TransactionRequiredException e = new TransactionRequiredException();
        Mockito.doThrow(e).when(this.entityManager).refresh(bean);
        this.genericDao.dettach(bean);
        Mockito.verify(this.entityManager, Mockito.times(1)).refresh(bean);
    }

    /**
     * Méthode en charge de tester le dettach avec une exception
     * EntityNotFoundException.
     */
    @Test
    public void dettachKOEntityNotFoundException() {
        final BeanObject bean = Mockito.mock(BeanObject.class);
        final EntityNotFoundException e = new EntityNotFoundException();
        Mockito.doThrow(e).when(this.entityManager).refresh(bean);
        this.genericDao.dettach(bean);
        Mockito.verify(this.entityManager, Mockito.times(1)).refresh(bean);
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
        final List<BeanObject> result = this.genericDao.getAll();
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
