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
import fr.pharma.eclipse.domain.model.acteur.ArcInvestigateur;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier ArcInvestigateur.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class ArcInvestigateurServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<ArcInvestigateur> arcInvestigateurService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<ArcInvestigateur> mockedDao;

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
        this.arcInvestigateurService = new GenericServiceImpl<ArcInvestigateur>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.arcInvestigateurService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.arcInvestigateurService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un arcInvestigateur pour le test.
     * @return Editeur initialisé.
     */
    private ArcInvestigateur makeArcInvestigateurTest() {
        final ArcInvestigateur arcInvestigateur = new ArcInvestigateur();
        arcInvestigateur.setId(1L);
        return arcInvestigateur;
    }

    /**
     * Crée une liste de arcInvestigateurs pour le test.
     * @return La liste de ArcInvestigateur initialisés.
     */
    private List<ArcInvestigateur> makeListArcInvestigateurTest() {
        final List<ArcInvestigateur> arcInvestigateurs = new ArrayList<ArcInvestigateur>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            arcInvestigateurs.add(this.makeArcInvestigateurTest());
        }
        return arcInvestigateurs;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final ArcInvestigateur arcInvestigateurExpected = this.makeArcInvestigateurTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(arcInvestigateurExpected);

        final ArcInvestigateur arcInvestigateur = this.arcInvestigateurService.get(idTest);

        Assert.assertNotNull(arcInvestigateur);
        Assert.assertEquals(arcInvestigateurExpected, arcInvestigateur);
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
            this.arcInvestigateurService.get(idTest);
            Assert.fail(ArcInvestigateurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final ArcInvestigateur arcInvestigateurToSave = this.makeArcInvestigateurTest();
        Mockito.when(this.mockedDao.save(arcInvestigateurToSave)).thenReturn(arcInvestigateurToSave);
        ArcInvestigateur arcInvestigateurSaved = null;
        arcInvestigateurSaved = this.arcInvestigateurService.save(arcInvestigateurToSave);
        Assert.assertEquals(arcInvestigateurSaved, arcInvestigateurToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final ArcInvestigateur arcInvestigateurToSave = this.makeArcInvestigateurTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(arcInvestigateurToSave)).thenThrow(exception);
        try {
            this.arcInvestigateurService.save(arcInvestigateurToSave);
            Assert.fail(ArcInvestigateurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les ArcInvestigateur avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        criteria.setNom("nom");
        final List<ArcInvestigateur> arcInvestigateursExp = this.makeListArcInvestigateurTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(arcInvestigateursExp);

        final List<ArcInvestigateur> arcInvestigateursRetrieved = this.arcInvestigateurService.getAll(criteria);

        Assert.assertEquals(arcInvestigateursExp.size(), arcInvestigateursRetrieved.size());
        for (final ArcInvestigateur arcInvestigateur : arcInvestigateursExp) {
            Assert.assertTrue(arcInvestigateursRetrieved.contains(arcInvestigateur));
        }
    }

    /**
     * Test de l'appel de liste de tous les ArcInvestigateur avec un critère de
     * recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.arcInvestigateurService.getAll(criteria);
            Assert.fail(ArcInvestigateurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final ArcInvestigateur bean = Mockito.mock(ArcInvestigateur.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.arcInvestigateurService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de arcInvestigateurs.
     */
    @Test
    public void testRemoveListe() {
        final List<ArcInvestigateur> beans = new ArrayList<ArcInvestigateur>();
        final ArcInvestigateur bean1 = Mockito.mock(ArcInvestigateur.class);
        final ArcInvestigateur bean2 = Mockito.mock(ArcInvestigateur.class);
        beans.add(bean1);
        beans.add(bean2);
        this.arcInvestigateurService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((ArcInvestigateur) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((ArcInvestigateur) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final ArcInvestigateur bean = Mockito.mock(ArcInvestigateur.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.arcInvestigateurService.remove(bean);
            Assert.fail(ArcInvestigateurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
