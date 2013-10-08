package fr.pharma.eclipse.service.stockage;

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
import fr.pharma.eclipse.dao.search.AclSearchDao;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.stockage.PharmacieSearchCriteria;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.suivi.stockage.PharmacieSuivi;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.service.stockage.impl.PharmacieServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;
import fr.pharma.eclipse.validator.save.impl.PharmacieSaveValidator;

/**
 * Classe de test du service pour le bean métier Pharmacie.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class PharmacieServiceTest {
    /**
     * Service.
     */
    private PharmacieServiceImpl pharmacieService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<Pharmacie> mockedDao;

    /**
     * Suivi Factory mocké.
     */
    private SuiviFactory<PharmacieSuivi> mockSuiviFactory;

    /**
     * Validator de sauvegarde mocké.
     */
    private PharmacieSaveValidator mockSaveValidator;

    /**
     * Mock de recherche d'acl.
     */
    private AclSearchDao mockAclSearchDao;

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
        this.mockSuiviFactory = Mockito.mock(SuiviFactory.class);
        this.mockSaveValidator = Mockito.mock(PharmacieSaveValidator.class);
        this.mockAclSearchDao = Mockito.mock(AclSearchDao.class);
        this.pharmacieService = new PharmacieServiceImpl(this.mockedDao);
        this.pharmacieService.setPharmacieSuiviFactory(this.mockSuiviFactory);
        this.pharmacieService.setPharmacieSaveValidator(this.mockSaveValidator);
        this.pharmacieService.setAclSearchDao(this.mockAclSearchDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.pharmacieService = null;
        this.mockedDao = null;
        this.mockSuiviFactory = null;
        this.mockSaveValidator = null;
        this.mockAclSearchDao = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.pharmacieService);
        Assert.assertNotNull(this.mockedDao);
        Assert.assertNotNull(this.mockSuiviFactory);
        Assert.assertNotNull(this.mockSaveValidator);
        Assert.assertNotNull(this.mockAclSearchDao);
    }

    /**
     * Crée un pharmacie pour le test.
     * @return Editeur initialisé.
     */
    private Pharmacie makePharmacieTest() {
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);
        pharmacie.setNom("nom");
        pharmacie.setAdresse("adresse");
        pharmacie.setAdresseLivraison("adresseLivraison");
        return pharmacie;
    }

    /**
     * Crée une liste de pharmacies pour le test.
     * @return La liste de Pharmacie initialisés.
     */
    private List<Pharmacie> makeListPharmacieTest() {
        final List<Pharmacie> pharmacies = new ArrayList<Pharmacie>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            pharmacies.add(this.makePharmacieTest());
        }
        return pharmacies;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final Pharmacie pharmacieExpected = this.makePharmacieTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(pharmacieExpected);

        final Pharmacie pharmacie = this.pharmacieService.get(idTest);

        Assert.assertNotNull(pharmacie);
        Assert.assertEquals(pharmacieExpected, pharmacie);
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
            this.pharmacieService.get(idTest);
            Assert.fail(PharmacieServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final Pharmacie pharmacieToSave = this.makePharmacieTest();
        final PharmacieSuivi expectedSuivi = Mockito.mock(PharmacieSuivi.class);
        Mockito.when(this.mockedDao.reattach(pharmacieToSave)).thenReturn(pharmacieToSave);
        Mockito.when(this.mockSuiviFactory.getInitializedObject()).thenReturn(expectedSuivi);
        Mockito.when(this.mockedDao.save(pharmacieToSave)).thenReturn(pharmacieToSave);
        Pharmacie pharmacieSaved = null;
        pharmacieSaved = this.pharmacieService.save(pharmacieToSave);
        Assert.assertEquals(pharmacieSaved, pharmacieToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final Pharmacie pharmacieToSave = this.makePharmacieTest();
        final PharmacieSuivi expectedSuivi = Mockito.mock(PharmacieSuivi.class);
        Mockito.when(this.mockedDao.reattach(pharmacieToSave)).thenReturn(pharmacieToSave);
        Mockito.when(this.mockSuiviFactory.getInitializedObject()).thenReturn(expectedSuivi);
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(pharmacieToSave)).thenThrow(exception);
        try {
            this.pharmacieService.save(pharmacieToSave);
            Assert.fail(PharmacieServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les Pharmacie OK.
     */
    @Test
    public void testGetAllOK() {
        final List<Pharmacie> pharmaciesExp = this.makeListPharmacieTest();
        Mockito.when(this.mockedDao.getAll(Matchers.any(SearchCriteria.class))).thenReturn(pharmaciesExp);
        final List<Pharmacie> pharmaciesRetrieved = this.pharmacieService.getAll();
        Assert.assertEquals(pharmaciesExp.size(), pharmaciesRetrieved.size());
        for (final Pharmacie pharmacie : pharmaciesExp) {
            Assert.assertTrue(pharmaciesRetrieved.contains(pharmacie));
        }
    }

    /**
     * Test de l'appel de liste de tous les Pharmacie avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllWithCriteriaOK() {
        final PharmacieSearchCriteria criteria = new PharmacieSearchCriteria();
        criteria.setNom("NOM");
        final List<Pharmacie> pharmaciesExp = this.makeListPharmacieTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(pharmaciesExp);
        final List<Pharmacie> pharmaciesRetrieved = this.pharmacieService.getAll(criteria);
        Assert.assertEquals(pharmaciesExp.size(), pharmaciesRetrieved.size());
        for (final Pharmacie pharmacie : pharmaciesExp) {
            Assert.assertTrue(pharmaciesRetrieved.contains(pharmacie));
        }
    }

    /**
     * Test de l'appel de liste de tous les Pharmacie KO.
     */
    @Test
    public void testGetAllKo() {
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(Matchers.any(SearchCriteria.class))).thenThrow(exception);
        try {
            this.pharmacieService.getAll();
            Assert.fail(PharmacieServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les Pharmacie avec un critère de
     * recherche KO.
     */
    @Test
    public void testGetAllWithCriteriaKo() {
        final PharmacieSearchCriteria criteria = new PharmacieSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.pharmacieService.getAll(criteria);
            Assert.fail(PharmacieServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final Pharmacie bean = Mockito.mock(Pharmacie.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.pharmacieService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de pharmacies.
     */
    @Test
    public void testRemoveListe() {
        final List<Pharmacie> beans = new ArrayList<Pharmacie>();
        final Pharmacie bean1 = Mockito.mock(Pharmacie.class);
        final Pharmacie bean2 = Mockito.mock(Pharmacie.class);
        beans.add(bean1);
        beans.add(bean2);
        this.pharmacieService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((Pharmacie) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((Pharmacie) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final Pharmacie bean = Mockito.mock(Pharmacie.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.pharmacieService.remove(bean);
            Assert.fail(PharmacieServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
