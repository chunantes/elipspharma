package fr.pharma.eclipse.service.acteur;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.acteur.PersonneSearchCriteria;
import fr.pharma.eclipse.domain.model.acteur.Cro;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier Cro.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class CroServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<Cro> croService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<Cro> mockedDao;

    /**
     * String indiquant un point de passage normalement non atteint.
     */
    private static final String NOT_TO_BE_REACHED = "Passage à un point normalement non atteint";

    /**
     * Méthode d'initialisation.
     * @throws Exception Exception.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception {
        this.mockedDao = Mockito.mock(GenericDao.class);
        this.croService = new GenericServiceImpl<Cro>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.croService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.croService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un cro pour le test.
     * @return Editeur initialisé.
     */
    private Cro makeCroTest() {
        final Cro cro = new Cro();
        cro.setId(1L);
        return cro;
    }

    /**
     * Crée une liste de cros pour le test.
     * @return La liste de Cro initialisés.
     */
    private List<Cro> makeListCroTest() {
        final List<Cro> cros = new ArrayList<Cro>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            cros.add(this.makeCroTest());
        }
        return cros;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final Cro croExpected = this.makeCroTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(croExpected);

        final Cro cro = this.croService.get(idTest);

        Assert.assertNotNull(cro);
        Assert.assertEquals(croExpected, cro);
    }

    /**
     * Test de la méthode get(int) KO.
     */
    @Test
    public void testGetIntKo() {
        final Long idTest = 1L;
        final MockitoException exception = new MockitoException("testGetIntKo");
        Mockito.when(this.mockedDao.get(idTest)).thenThrow(exception);
        try {
            this.croService.get(idTest);
            Assert.fail(CroServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final Cro croToSave = this.makeCroTest();
        Mockito.when(this.mockedDao.save(croToSave)).thenReturn(croToSave);
        Cro croSaved = null;
        croSaved = this.croService.save(croToSave);
        Assert.assertEquals(croSaved, croToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final Cro croToSave = this.makeCroTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(croToSave)).thenThrow(exception);
        try {
            this.croService.save(croToSave);
            Assert.fail(CroServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les Cro avec un critère de recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        criteria.setNom("nom");
        final List<Cro> crosExp = this.makeListCroTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(crosExp);

        final List<Cro> crosRetrieved = this.croService.getAll(criteria);

        Assert.assertEquals(crosExp.size(), crosRetrieved.size());
        for (final Cro cro : crosExp) {
            Assert.assertTrue(crosRetrieved.contains(cro));
        }
    }

    /**
     * Test de l'appel de liste de tous les Cro avec un critère de recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.croService.getAll(criteria);
            Assert.fail(CroServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final Cro bean = Mockito.mock(Cro.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.croService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de cros.
     */
    @Test
    public void testRemoveListe() {
        final List<Cro> beans = new ArrayList<Cro>();
        final Cro bean1 = Mockito.mock(Cro.class);
        final Cro bean2 = Mockito.mock(Cro.class);
        beans.add(bean1);
        beans.add(bean2);
        this.croService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((Cro) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((Cro) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final Cro bean = Mockito.mock(Cro.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.croService.remove(bean);
            Assert.fail(CroServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
