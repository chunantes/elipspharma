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
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier Investigateur.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class InvestigateurServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<Investigateur> investigateurService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<Investigateur> mockedDao;

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
        this.investigateurService = new GenericServiceImpl<Investigateur>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.investigateurService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.investigateurService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un investigateur pour le test.
     * @return Editeur initialisé.
     */
    private Investigateur makeInvestigateurTest() {
        final Investigateur investigateur = new Investigateur();
        investigateur.setId(1L);
        return investigateur;
    }

    /**
     * Crée une liste de investigateurs pour le test.
     * @return La liste de Investigateur initialisés.
     */
    private List<Investigateur> makeListInvestigateurTest() {
        final List<Investigateur> investigateurs = new ArrayList<Investigateur>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            investigateurs.add(this.makeInvestigateurTest());
        }
        return investigateurs;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final Investigateur investigateurExpected = this.makeInvestigateurTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(investigateurExpected);

        final Investigateur investigateur = this.investigateurService.get(idTest);

        Assert.assertNotNull(investigateur);
        Assert.assertEquals(investigateurExpected, investigateur);
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
            this.investigateurService.get(idTest);
            Assert.fail(InvestigateurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final Investigateur investigateurToSave = this.makeInvestigateurTest();
        Mockito.when(this.mockedDao.save(investigateurToSave)).thenReturn(investigateurToSave);
        Investigateur investigateurSaved = null;
        investigateurSaved = this.investigateurService.save(investigateurToSave);
        Assert.assertEquals(investigateurSaved, investigateurToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final Investigateur investigateurToSave = this.makeInvestigateurTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(investigateurToSave)).thenThrow(exception);
        try {
            this.investigateurService.save(investigateurToSave);
            Assert.fail(InvestigateurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les Investigateur avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        criteria.setNom("nom");
        final List<Investigateur> investigateursExp = this.makeListInvestigateurTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(investigateursExp);

        final List<Investigateur> investigateursRetrieved = this.investigateurService.getAll(criteria);

        Assert.assertEquals(investigateursExp.size(), investigateursRetrieved.size());
        for (final Investigateur investigateur : investigateursExp) {
            Assert.assertTrue(investigateursRetrieved.contains(investigateur));
        }
    }

    /**
     * Test de l'appel de liste de tous les Investigateur avec un critère de
     * recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.investigateurService.getAll(criteria);
            Assert.fail(InvestigateurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final Investigateur bean = Mockito.mock(Investigateur.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.investigateurService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de investigateurs.
     */
    @Test
    public void testRemoveListe() {
        final List<Investigateur> beans = new ArrayList<Investigateur>();
        final Investigateur bean1 = Mockito.mock(Investigateur.class);
        final Investigateur bean2 = Mockito.mock(Investigateur.class);
        beans.add(bean1);
        beans.add(bean2);
        this.investigateurService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((Investigateur) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((Investigateur) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final Investigateur bean = Mockito.mock(Investigateur.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.investigateurService.remove(bean);
            Assert.fail(InvestigateurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
