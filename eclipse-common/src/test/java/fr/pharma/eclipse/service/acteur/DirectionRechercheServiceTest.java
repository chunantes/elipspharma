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
import fr.pharma.eclipse.domain.model.acteur.DirectionRecherche;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier DirectionRecherche.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class DirectionRechercheServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<DirectionRecherche> directionRechercheService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<DirectionRecherche> mockedDao;

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
        this.directionRechercheService = new GenericServiceImpl<DirectionRecherche>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.directionRechercheService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.directionRechercheService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un directionRecherche pour le test.
     * @return Editeur initialisé.
     */
    private DirectionRecherche makeDirectionRechercheTest() {
        final DirectionRecherche directionRecherche = new DirectionRecherche();
        directionRecherche.setId(1L);
        return directionRecherche;
    }

    /**
     * Crée une liste de directionRecherches pour le test.
     * @return La liste de DirectionRecherche initialisés.
     */
    private List<DirectionRecherche> makeListDirectionRechercheTest() {
        final List<DirectionRecherche> directionRecherches = new ArrayList<DirectionRecherche>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            directionRecherches.add(this.makeDirectionRechercheTest());
        }
        return directionRecherches;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final DirectionRecherche directionRechercheExpected = this.makeDirectionRechercheTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(directionRechercheExpected);

        final DirectionRecherche directionRecherche = this.directionRechercheService.get(idTest);

        Assert.assertNotNull(directionRecherche);
        Assert.assertEquals(directionRechercheExpected, directionRecherche);
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
            this.directionRechercheService.get(idTest);
            Assert.fail(DirectionRechercheServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final DirectionRecherche directionRechercheToSave = this.makeDirectionRechercheTest();
        Mockito.when(this.mockedDao.save(directionRechercheToSave)).thenReturn(directionRechercheToSave);
        DirectionRecherche directionRechercheSaved = null;
        directionRechercheSaved = this.directionRechercheService.save(directionRechercheToSave);
        Assert.assertEquals(directionRechercheSaved, directionRechercheToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final DirectionRecherche directionRechercheToSave = this.makeDirectionRechercheTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(directionRechercheToSave)).thenThrow(exception);
        try {
            this.directionRechercheService.save(directionRechercheToSave);
            Assert.fail(DirectionRechercheServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les DirectionRecherche avec un critère
     * de recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        criteria.setNom("nom");
        final List<DirectionRecherche> directionRecherchesExp = this.makeListDirectionRechercheTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(directionRecherchesExp);

        final List<DirectionRecherche> directionRecherchesRetrieved = this.directionRechercheService.getAll(criteria);

        Assert.assertEquals(directionRecherchesExp.size(), directionRecherchesRetrieved.size());
        for (final DirectionRecherche directionRecherche : directionRecherchesExp) {
            Assert.assertTrue(directionRecherchesRetrieved.contains(directionRecherche));
        }
    }

    /**
     * Test de l'appel de liste de tous les DirectionRecherche avec un critère
     * de recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.directionRechercheService.getAll(criteria);
            Assert.fail(DirectionRechercheServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final DirectionRecherche bean = Mockito.mock(DirectionRecherche.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.directionRechercheService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de directionRecherches.
     */
    @Test
    public void testRemoveListe() {
        final List<DirectionRecherche> beans = new ArrayList<DirectionRecherche>();
        final DirectionRecherche bean1 = Mockito.mock(DirectionRecherche.class);
        final DirectionRecherche bean2 = Mockito.mock(DirectionRecherche.class);
        beans.add(bean1);
        beans.add(bean2);
        this.directionRechercheService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((DirectionRecherche) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((DirectionRecherche) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final DirectionRecherche bean = Mockito.mock(DirectionRecherche.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.directionRechercheService.remove(bean);
            Assert.fail(DirectionRechercheServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
