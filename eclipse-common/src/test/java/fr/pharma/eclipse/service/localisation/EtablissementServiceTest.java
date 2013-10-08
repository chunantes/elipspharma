package fr.pharma.eclipse.service.localisation;

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
import fr.pharma.eclipse.domain.criteria.localisation.EtablissementSearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Etablissement;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier Etablissement.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class EtablissementServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<Etablissement> etablissementService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<Etablissement> mockedDao;

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
        this.etablissementService = new GenericServiceImpl<Etablissement>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.etablissementService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.etablissementService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un etablissement pour le test.
     * @return Editeur initialisé.
     */
    private Etablissement makeEtablissementTest() {
        final Etablissement etablissement = new Etablissement();
        etablissement.setId(1L);
        return etablissement;
    }

    /**
     * Crée une liste de etablissements pour le test.
     * @return La liste de Etablissement initialisés.
     */
    private List<Etablissement> makeListEtablissementTest() {
        final List<Etablissement> etablissements = new ArrayList<Etablissement>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            etablissements.add(this.makeEtablissementTest());
        }
        return etablissements;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final Etablissement etablissementExpected = this.makeEtablissementTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(etablissementExpected);

        final Etablissement etablissement = this.etablissementService.get(idTest);

        Assert.assertNotNull(etablissement);
        Assert.assertEquals(etablissementExpected, etablissement);
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
            this.etablissementService.get(idTest);
            Assert.fail(EtablissementServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final Etablissement etablissementToSave = this.makeEtablissementTest();
        Mockito.when(this.mockedDao.save(etablissementToSave)).thenReturn(etablissementToSave);
        Etablissement etablissementSaved = null;
        etablissementSaved = this.etablissementService.save(etablissementToSave);
        Assert.assertEquals(etablissementSaved, etablissementToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final Etablissement etablissementToSave = this.makeEtablissementTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(etablissementToSave)).thenThrow(exception);
        try {
            this.etablissementService.save(etablissementToSave);
            Assert.fail(EtablissementServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les Etablissement avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final EtablissementSearchCriteria criteria = new EtablissementSearchCriteria();
        criteria.setNom("NOM");
        final List<Etablissement> etablissementsExp = this.makeListEtablissementTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(etablissementsExp);

        final List<Etablissement> etablissementsRetrieved = this.etablissementService.getAll(criteria);

        Assert.assertEquals(etablissementsExp.size(), etablissementsRetrieved.size());
        for (final Etablissement etablissement : etablissementsExp) {
            Assert.assertTrue(etablissementsRetrieved.contains(etablissement));
        }
    }

    /**
     * Test de l'appel de liste de tous les Etablissement avec un critère de
     * recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final EtablissementSearchCriteria criteria = new EtablissementSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.etablissementService.getAll(criteria);
            Assert.fail(EtablissementServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final Etablissement bean = Mockito.mock(Etablissement.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.etablissementService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de etablissements.
     */
    @Test
    public void testRemoveListe() {
        final List<Etablissement> beans = new ArrayList<Etablissement>();
        final Etablissement bean1 = Mockito.mock(Etablissement.class);
        final Etablissement bean2 = Mockito.mock(Etablissement.class);
        beans.add(bean1);
        beans.add(bean2);
        this.etablissementService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((Etablissement) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((Etablissement) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final Etablissement bean = Mockito.mock(Etablissement.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.etablissementService.remove(bean);
            Assert.fail(EtablissementServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
