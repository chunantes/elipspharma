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
import fr.pharma.eclipse.domain.criteria.acteur.PromoteurSearchCriteria;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier Promoteur.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class PromoteurServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<Promoteur> promoteurService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<Promoteur> mockedDao;

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
        this.promoteurService = new GenericServiceImpl<Promoteur>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.promoteurService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.promoteurService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un promoteur pour le test.
     * @return Editeur initialisé.
     */
    private Promoteur makePromoteurTest() {
        final Promoteur promoteur = new Promoteur();
        promoteur.setId(1L);
        return promoteur;
    }

    /**
     * Crée une liste de promoteurs pour le test.
     * @return La liste de Promoteur initialisés.
     */
    private List<Promoteur> makeListPromoteurTest() {
        final List<Promoteur> promoteurs = new ArrayList<Promoteur>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            promoteurs.add(this.makePromoteurTest());
        }
        return promoteurs;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final Promoteur promoteurExpected = this.makePromoteurTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(promoteurExpected);

        final Promoteur promoteur = this.promoteurService.get(idTest);

        Assert.assertNotNull(promoteur);
        Assert.assertEquals(promoteurExpected, promoteur);
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
            this.promoteurService.get(idTest);
            Assert.fail(PromoteurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final Promoteur promoteurToSave = this.makePromoteurTest();
        Mockito.when(this.mockedDao.save(promoteurToSave)).thenReturn(promoteurToSave);
        Promoteur promoteurSaved = null;
        promoteurSaved = this.promoteurService.save(promoteurToSave);
        Assert.assertEquals(promoteurSaved, promoteurToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final Promoteur promoteurToSave = this.makePromoteurTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(promoteurToSave)).thenThrow(exception);
        try {
            this.promoteurService.save(promoteurToSave);
            Assert.fail(PromoteurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les Promoteur avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final PromoteurSearchCriteria criteria = new PromoteurSearchCriteria();
        criteria.setRaisonSociale("raisonSociale");
        final List<Promoteur> promoteursExp = this.makeListPromoteurTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(promoteursExp);

        final List<Promoteur> promoteursRetrieved = this.promoteurService.getAll(criteria);

        Assert.assertEquals(promoteursExp.size(), promoteursRetrieved.size());
        for (final Promoteur promoteur : promoteursExp) {
            Assert.assertTrue(promoteursRetrieved.contains(promoteur));
        }
    }

    /**
     * Test de l'appel de liste de tous les Promoteur avec un critère de
     * recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final PromoteurSearchCriteria criteria = new PromoteurSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.promoteurService.getAll(criteria);
            Assert.fail(PromoteurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final Promoteur bean = Mockito.mock(Promoteur.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.promoteurService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de promoteurs.
     */
    @Test
    public void testRemoveListe() {
        final List<Promoteur> beans = new ArrayList<Promoteur>();
        final Promoteur bean1 = Mockito.mock(Promoteur.class);
        final Promoteur bean2 = Mockito.mock(Promoteur.class);
        beans.add(bean1);
        beans.add(bean2);
        this.promoteurService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((Promoteur) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((Promoteur) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final Promoteur bean = Mockito.mock(Promoteur.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.promoteurService.remove(bean);
            Assert.fail(PromoteurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
