package fr.pharma.eclipse.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.validator.remove.RemoveValidator;

/**
 * Classe en charge de tester la méthode de service générique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GenericServiceImplTest {
    /**
     * Service.
     */
    private GenericServiceImpl<Pharmacie> service;

    /**
     * Dao.
     */
    private GenericDao<Pharmacie> dao;

    /**
     * Mock du validator de suppression.
     */
    private RemoveValidator<Pharmacie> removeValidator;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.dao = Mockito.mock(GenericDaoHibernate.class);
        this.service = new GenericServiceImpl<Pharmacie>(this.dao);
        this.removeValidator = Mockito.mock(RemoveValidator.class);
    }
    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown() {
        this.dao = null;
        this.service = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.dao);
        Assert.assertNotNull(this.service);
    }

    /**
     * Méthode en charge de tester la méthode de dettach d'un bean.
     */
    @Test
    public void dettach() {
        final Pharmacie bean = Mockito.mock(Pharmacie.class);
        this.service.dettach(bean);
        Mockito.verify(this.dao, Mockito.times(1)).dettach(bean);
    }

    /**
     * Méthode en charge de tester la récupération d'un bean.
     */
    @Test
    public void get() {
        final Pharmacie bean = new Pharmacie();
        bean.setId(1L);
        Mockito.when(this.dao.get(1L)).thenReturn(bean);
        final BeanObject result = this.service.get(1L);
        Mockito.verify(this.dao, Mockito.times(1)).get(1L);
        Assert.assertEquals(1L, bean.getId().longValue());
        Assert.assertEquals(1L, result.getId().longValue());
    }

    /**
     * Méthode en charge de tester la récupération des beans à partir d'un
     * SearchCriteria.
     */
    @Test
    public void getAllWithCriteria() {
        final SearchCriteria criteria = Mockito.mock(SearchCriteria.class);
        final List<Pharmacie> beans = new ArrayList<Pharmacie>();
        final Pharmacie bean1 = new Pharmacie();
        bean1.setId(1L);
        beans.add(bean1);
        final Pharmacie bean2 = new Pharmacie();
        bean2.setId(1L);
        beans.add(bean2);
        Mockito.when(this.dao.getAll(criteria)).thenReturn(beans);
        final List<Pharmacie> result = this.service.getAll(criteria);
        Mockito.verify(this.dao, Mockito.times(1)).getAll(criteria);
        Assert.assertNotNull(result);
        Assert.assertEquals(beans.size(), result.size());
    }

    /**
     * Méthode en charge de tester la recherche ou non de résultats à partir
     * d'un SearchCriteria.
     */
    @Test
    public void hasResultTrue() {
        final SearchCriteria criteria = Mockito.mock(SearchCriteria.class);
        final List<Pharmacie> beans = new ArrayList<Pharmacie>();
        final Pharmacie bean1 = new Pharmacie();
        bean1.setId(1L);
        beans.add(bean1);
        final Pharmacie bean2 = new Pharmacie();
        bean2.setId(1L);
        beans.add(bean2);
        Mockito.when(this.dao.getAll(criteria)).thenReturn(beans);
        final Boolean result = this.service.hasResult(criteria);
        Assert.assertTrue(result);
    }

    /**
     * Méthode en charge de tester la recherche ou non de résultats à partir
     * d'un SearchCriteria.
     */
    @Test
    public void hasResultFalse() {
        final SearchCriteria criteria = Mockito.mock(SearchCriteria.class);
        final List<Pharmacie> beans = new ArrayList<Pharmacie>();
        final Pharmacie bean1 = new Pharmacie();
        bean1.setId(1L);
        beans.add(bean1);
        final Pharmacie bean2 = new Pharmacie();
        bean2.setId(1L);
        beans.add(bean2);
        Mockito.when(this.dao.getAll(criteria)).thenReturn(new ArrayList<Pharmacie>());
        final Boolean result = this.service.hasResult(criteria);
        Assert.assertFalse(result);
    }

    /**
     * Méthode en charge de tester la récupération des beans.
     */
    @Test
    public void getAll() {
        final List<Pharmacie> beans = new ArrayList<Pharmacie>();
        final Pharmacie bean1 = new Pharmacie();
        bean1.setId(1L);
        beans.add(bean1);
        final Pharmacie bean2 = new Pharmacie();
        bean2.setId(1L);
        beans.add(bean2);
        Mockito.when(this.dao.getAll()).thenReturn(beans);
        final List<Pharmacie> result = this.service.getAll();
        Mockito.verify(this.dao, Mockito.times(1)).getAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(beans.size(), result.size());
    }

    /**
     * Méthode en charge de tester la méthode de reattach.
     */
    @Test
    public void reattach() {
        final Pharmacie bean = Mockito.mock(Pharmacie.class);
        this.service.reattach(bean);
        Mockito.verify(this.dao, Mockito.times(1)).reattach(bean);
    }

    /**
     * Méthode en charge de tester la méthode de reattachAll.
     */
    @Test
    public void reattachAll() {
        Assert.assertNull(this.service.reattachAll(null));

        final Pharmacie bean1 = Mockito.mock(Pharmacie.class);
        final Pharmacie bean2 = Mockito.mock(Pharmacie.class);
        final List<Pharmacie> beans = new ArrayList<Pharmacie>();
        beans.add(bean1);
        beans.add(bean2);
        this.service.reattachAll(beans);
        Mockito.verify(this.dao, Mockito.times(1)).reattach(bean1);
        Mockito.verify(this.dao, Mockito.times(1)).reattach(bean2);
    }

    /**
     * Méthode en charge de tester la méthode de suppression sans application du
     * validator.
     */
    @Test
    public void removeWithoutValidator() {
        final Pharmacie bean = Mockito.mock(Pharmacie.class);
        Mockito.when(this.dao.reattach(bean)).thenReturn(bean);
        this.service.setRemoveValidator(null);
        this.service.remove(bean);
        Mockito.verify(this.dao, Mockito.times(1)).reattach(bean);
        Mockito.verify(this.dao, Mockito.times(1)).remove(bean);
    }

    /**
     * Méthode en charge de tester la méthode de suppression avec application du
     * validator.
     */
    @Test
    public void removeWithValidator() {
        final Pharmacie bean = Mockito.mock(Pharmacie.class);
        Mockito.when(this.dao.reattach(bean)).thenReturn(bean);
        this.service.setRemoveValidator(this.removeValidator);
        this.service.remove(bean);
        Mockito.verify(this.dao, Mockito.times(1)).reattach(bean);
        Mockito.verify(this.dao, Mockito.times(1)).remove(bean);
    }

    /**
     * Méthode en charge de tester la méthode de suppression d'une liste.
     */
    @Test
    public void removeListe() {
        final List<Pharmacie> beans = new ArrayList<Pharmacie>();
        final Pharmacie bean1 = Mockito.mock(Pharmacie.class);
        final Pharmacie bean2 = Mockito.mock(Pharmacie.class);
        beans.add(bean1);
        beans.add(bean2);
        this.service.remove(beans);
        Mockito.verify(this.dao, Mockito.times(2)).reattach((Pharmacie) Matchers.any());
        Mockito.verify(this.dao, Mockito.times(2)).remove((Pharmacie) Matchers.any());
    }

    /**
     * Méthode en charge de tester la méthode de suppression d'une liste.
     */
    @Test(expected = ValidationException.class)
    public void removeListeWithException() {
        final List<Pharmacie> beans = new ArrayList<Pharmacie>();
        final Pharmacie bean1 = Mockito.mock(Pharmacie.class);
        final Pharmacie bean2 = Mockito.mock(Pharmacie.class);
        beans.add(bean1);
        beans.add(bean2);
        this.service.setRemoveValidator(this.removeValidator);
        Mockito.when(this.dao.reattach(bean1)).thenReturn(bean1);
        final ValidationException validationException = new ValidationException("remove", new String[]{"impossible" });
        Mockito.doThrow(validationException).when(this.removeValidator).validate(bean1);
        this.service.remove(beans);
        Mockito.verify(this.dao, Mockito.times(2)).reattach((Pharmacie) Matchers.any());
        Mockito.verify(this.dao, Mockito.times(2)).remove((Pharmacie) Matchers.any());
    }

    /**
     * Méthode en charge de tester l'enregistrement OK (pas de levée de
     * ValidationException).
     */
    @Test
    public void saveOK() {
        final Pharmacie bean = new Pharmacie();
        bean.setId(1L);
        Mockito.when(this.dao.save(bean)).thenReturn(bean);
        final BeanObject result = this.service.save(bean);
        Mockito.verify(this.dao, Mockito.times(1)).save(bean);
        Assert.assertNotNull(result);
        Assert.assertEquals(1L, result.getId().longValue());
    }

    /**
     * Méthode en charge de tester l'enregistrement en lot.
     */
    @Test
    public void saveAll() {
        final List<Pharmacie> beans = new ArrayList<Pharmacie>();
        final Pharmacie bean1 = Mockito.mock(Pharmacie.class);
        final Pharmacie bean2 = Mockito.mock(Pharmacie.class);
        bean1.setId(1L);
        bean2.setId(2L);
        beans.add(bean1);
        beans.add(bean2);

        Mockito.when(this.dao.save(bean1)).thenReturn(bean1);
        Mockito.when(this.dao.save(bean2)).thenReturn(bean2);

        final List<Pharmacie> result = this.service.saveAll(beans);

        Mockito.verify(this.dao, Mockito.times(1)).save(bean1);
        Mockito.verify(this.dao, Mockito.times(1)).save(bean2);
        Assert.assertNotNull(result);
        Assert.assertEquals(beans.size(), result.size());
        for (final BeanObject bean : beans) {
            Assert.assertTrue(result.contains(bean));
        }
    }

    /**
     * Méthode en charge de tester l'enregistrement en lot.
     */
    @Test
    public void saveAllNull() {
        final List<Pharmacie> result = this.service.saveAll(null);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }

}
