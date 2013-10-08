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
import fr.pharma.eclipse.domain.model.acteur.ArcPromoteur;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier ArcPromoteur.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class ArcPromoteurServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<ArcPromoteur> arcPromoteurService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<ArcPromoteur> mockedDao;

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
        this.arcPromoteurService = new GenericServiceImpl<ArcPromoteur>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.arcPromoteurService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.arcPromoteurService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un arcPromoteur pour le test.
     * @return Editeur initialisé.
     */
    private ArcPromoteur makeArcPromoteurTest() {
        final ArcPromoteur arcPromoteur = new ArcPromoteur();
        arcPromoteur.setId(1L);
        return arcPromoteur;
    }

    /**
     * Crée une liste de arcPromoteurs pour le test.
     * @return La liste de ArcPromoteur initialisés.
     */
    private List<ArcPromoteur> makeListArcPromoteurTest() {
        final List<ArcPromoteur> arcPromoteurs = new ArrayList<ArcPromoteur>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            arcPromoteurs.add(this.makeArcPromoteurTest());
        }
        return arcPromoteurs;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final ArcPromoteur arcPromoteurExpected = this.makeArcPromoteurTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(arcPromoteurExpected);

        final ArcPromoteur arcPromoteur = this.arcPromoteurService.get(idTest);

        Assert.assertNotNull(arcPromoteur);
        Assert.assertEquals(arcPromoteurExpected, arcPromoteur);
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
            this.arcPromoteurService.get(idTest);
            Assert.fail(ArcPromoteurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final ArcPromoteur arcPromoteurToSave = this.makeArcPromoteurTest();
        Mockito.when(this.mockedDao.save(arcPromoteurToSave)).thenReturn(arcPromoteurToSave);
        ArcPromoteur arcPromoteurSaved = null;
        arcPromoteurSaved = this.arcPromoteurService.save(arcPromoteurToSave);
        Assert.assertEquals(arcPromoteurSaved, arcPromoteurToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final ArcPromoteur arcPromoteurToSave = this.makeArcPromoteurTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(arcPromoteurToSave)).thenThrow(exception);
        try {
            this.arcPromoteurService.save(arcPromoteurToSave);
            Assert.fail(ArcPromoteurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les ArcPromoteur avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        criteria.setNom("nom");
        final List<ArcPromoteur> arcPromoteursExp = this.makeListArcPromoteurTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(arcPromoteursExp);

        final List<ArcPromoteur> arcPromoteursRetrieved = this.arcPromoteurService.getAll(criteria);

        Assert.assertEquals(arcPromoteursExp.size(), arcPromoteursRetrieved.size());
        for (final ArcPromoteur arcPromoteur : arcPromoteursExp) {
            Assert.assertTrue(arcPromoteursRetrieved.contains(arcPromoteur));
        }
    }

    /**
     * Test de l'appel de liste de tous les ArcPromoteur avec un critère de
     * recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.arcPromoteurService.getAll(criteria);
            Assert.fail(ArcPromoteurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final ArcPromoteur bean = Mockito.mock(ArcPromoteur.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.arcPromoteurService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de arcPromoteurs.
     */
    @Test
    public void testRemoveListe() {
        final List<ArcPromoteur> beans = new ArrayList<ArcPromoteur>();
        final ArcPromoteur bean1 = Mockito.mock(ArcPromoteur.class);
        final ArcPromoteur bean2 = Mockito.mock(ArcPromoteur.class);
        beans.add(bean1);
        beans.add(bean2);
        this.arcPromoteurService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((ArcPromoteur) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((ArcPromoteur) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final ArcPromoteur bean = Mockito.mock(ArcPromoteur.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.arcPromoteurService.remove(bean);
            Assert.fail(ArcPromoteurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
