package fr.pharma.eclipse.service.essai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.essai.EssaiSearchCriteria;
import fr.pharma.eclipse.domain.enums.TypeHistoriqueEssai;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.faisabilite.DetailFaisabilite;
import fr.pharma.eclipse.domain.model.suivi.essai.EssaiSuivi;
import fr.pharma.eclipse.exception.TechnicalException;
import fr.pharma.eclipse.factory.common.BeanObjectWithParentFactory;
import fr.pharma.eclipse.service.acl.AclService;
import fr.pharma.eclipse.service.essai.impl.EssaiServiceImpl;
import fr.pharma.eclipse.service.essai.updator.EssaiBeforeSaveUpdator;
import fr.pharma.eclipse.service.helper.DroitAccesHelper;
import fr.pharma.eclipse.service.helper.common.BeanHelper;
import fr.pharma.eclipse.service.user.UserService;
import fr.pharma.eclipse.utils.ContextSecurityHelper;
import fr.pharma.eclipse.utils.TestConstants;
import fr.pharma.eclipse.validator.save.SaveValidator;

/**
 * Classe de test du service pour le bean métier Essai.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class EssaiServiceTest {
    /**
     * Service.
     */
    private EssaiServiceImpl essaiService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<Essai> mockedDao;

    /**
     * Helper mocké.
     */
    private BeanHelper<Essai> mockedBeanHelper;

    /**
     * Suivi Factory mocké.
     */
    private BeanObjectWithParentFactory<EssaiSuivi, Essai> mockSuiviGeneralFactory;

    /**
     * Updator before save mocké.
     */
    private EssaiBeforeSaveUpdator mockedBeforeSaveUpdator;

    /**
     * Validateur de save mocké.
     */
    private SaveValidator<Essai> mockedSaveValidator;

    /**
     * String indiquant un point de passage normalement non atteint.
     */
    private static final String NOT_TO_BE_REACHED = "Passage à un point normalement non atteint";

    /**
     * Helper droit mocké.
     */
    private DroitAccesHelper droitAccesHelper;

    /**
     * Service de gestion des utilisateurs.
     */
    private UserService mockUserService;

    /**
     * Service de gestion des acls mocké.
     */
    private AclService mockAclService;

    /**
     * Méthode d'initialisation.
     * @throws Exception Exception.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception {
        this.mockedDao = Mockito.mock(GenericDao.class);
        this.mockedBeanHelper = Mockito.mock(BeanHelper.class);
        this.mockedBeforeSaveUpdator = Mockito.mock(EssaiBeforeSaveUpdator.class);
        this.droitAccesHelper = Mockito.mock(DroitAccesHelper.class);
        this.mockedSaveValidator = Mockito.mock(SaveValidator.class);
        this.mockSuiviGeneralFactory = Mockito.mock(BeanObjectWithParentFactory.class);
        this.mockUserService = Mockito.mock(UserService.class);
        final Map<String, BeanObjectWithParentFactory<? extends BeanObject, ? extends BeanObject>> mapFactories =
            new TreeMap<String, BeanObjectWithParentFactory<? extends BeanObject, ? extends BeanObject>>();
        mapFactories.put(TypeHistoriqueEssai.GENERAL.name(), this.mockSuiviGeneralFactory);
        this.essaiService = new EssaiServiceImpl(this.mockedDao);
        this.essaiService.setMapFactories(mapFactories);
        this.essaiService.setBeanHelper(this.mockedBeanHelper);
        this.essaiService.setBeforeSaveUpdators(Arrays.asList(this.mockedBeforeSaveUpdator));
        this.essaiService.setSaveValidators(Arrays.asList(this.mockedSaveValidator));
        this.essaiService.setDroitAccesHelper(this.droitAccesHelper);
        this.essaiService.setUserService(this.mockUserService);
        Mockito.when(this.droitAccesHelper.isEssaiLectureSeule()).thenReturn(false);
        this.mockAclService = Mockito.mock(AclService.class);
        this.essaiService.setAclService(this.mockAclService);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.essaiService = null;
        this.mockedDao = null;
        this.mockSuiviGeneralFactory = null;
        this.mockedBeanHelper = null;
        this.mockedSaveValidator = null;
        this.mockAclService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.essaiService);
        Assert.assertNotNull(this.mockedDao);
        Assert.assertNotNull(this.mockAclService);
    }

    /**
     * Crée un essai pour le test.
     * @return Editeur initialisé.
     */
    private Essai makeEssaiTest() {
        final Essai essai = new Essai();
        final DetailFaisabilite detailFaisabilite = new DetailFaisabilite();
        essai.setDetailFaisabilite(detailFaisabilite);
        essai.setId(1L);
        return essai;
    }

    /**
     * Crée une liste de essais pour le test.
     * @return La liste de Essai initialisés.
     */
    private List<Essai> makeListEssaiTest() {
        final List<Essai> essais = new ArrayList<Essai>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            essais.add(this.makeEssaiTest());
        }
        return essais;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final Essai essaiExpected = Mockito.mock(Essai.class);
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(essaiExpected);
        Mockito.when(essaiExpected.getDetailFaisabilite()).thenReturn(new DetailFaisabilite());
        Mockito.when(essaiExpected.getId()).thenReturn(null);
        final Essai essai = this.essaiService.get(idTest);

        Assert.assertNotNull(essai);
        Assert.assertEquals(essaiExpected, essai);
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
            this.essaiService.get(idTest);
            Assert.fail(EssaiServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final Essai essaiToSave = this.makeEssaiTest();
        Mockito.when(this.mockedDao.save(essaiToSave)).thenReturn(essaiToSave);
        Mockito.when(this.mockedDao.reattach(essaiToSave)).thenReturn(essaiToSave);
        final EssaiSuivi essaiSuivi = new EssaiSuivi();
        Mockito.when(this.mockSuiviGeneralFactory.getInitializedObject(essaiToSave)).thenReturn(essaiSuivi);
        Essai essaiSaved = null;
        essaiSaved = this.essaiService.save(essaiToSave);
        Mockito.verify(this.mockedSaveValidator).validate(essaiToSave, this.essaiService);
        Mockito.verify(this.mockAclService).updateAclsEssais(essaiSaved);
        Assert.assertEquals(essaiSaved, essaiToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final Essai essaiToSave = this.makeEssaiTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(essaiToSave)).thenThrow(exception);
        Mockito.when(this.mockedDao.reattach(essaiToSave)).thenReturn(essaiToSave);
        final EssaiSuivi essaiSuivi = new EssaiSuivi();
        Mockito.when(this.mockSuiviGeneralFactory.getInitializedObject(essaiToSave)).thenReturn(essaiSuivi);
        try {
            this.essaiService.save(essaiToSave);
            Assert.fail(EssaiServiceTest.NOT_TO_BE_REACHED);
        } catch (final TechnicalException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve.getCause());
        }
        Mockito.verify(this.mockedSaveValidator).validate(essaiToSave, this.essaiService);
    }
    
    /**
     * Test de l'appel de liste de tous les Essai avec un critère de recherche
     * OK.
     */
    @Test
    public void testGetAllOKWithCriteria() {
        final EssaiSearchCriteria criteria = new EssaiSearchCriteria();
        criteria.setNom("nom");
        final List<Essai> essaisExp = this.makeListEssaiTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(essaisExp);
        final List<Essai> essaisRetrieved = this.essaiService.getAll(criteria);
        Assert.assertEquals(essaisExp.size(), essaisRetrieved.size());
        for (final Essai essai : essaisExp) {
            Assert.assertTrue(essaisRetrieved.contains(essai));
        }
    }

    /**
     * Test de l'appel de liste de tous les Essai sans un critère de recherche
     * OK.
     */
    @Test
    public void testGetAllOKCriteria() {
        final List<Essai> essaisExp = this.makeListEssaiTest();

        ContextSecurityHelper.createSecurityContextMock();
        final Personne personne = new Pharmacien();
        personne.setType(TypePersonne.PHARMACIEN);
        personne.setIsAdmin(true);
        Mockito.when(this.mockUserService.getPersonne()).thenReturn(personne);

        Mockito.when(this.mockedDao.executeSQLQuery("select distinct e.* from essai e", null)).thenReturn(essaisExp);

        final List<Essai> essaisRetrieved = this.essaiService.getAll();

        Assert.assertEquals(essaisExp.size(), essaisRetrieved.size());
        for (final Essai essai : essaisExp) {
            Assert.assertTrue(essaisRetrieved.contains(essai));
        }
    }

    /**
     * Test de l'appel de liste de tous les Essai avec un critère de recherche
     * KO.
     */
    @Test
    public void testGetAllKo() {
        final EssaiSearchCriteria criteria = new EssaiSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.essaiService.getAll(criteria);
            Assert.fail(EssaiServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final Essai bean = Mockito.mock(Essai.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.essaiService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de essais.
     */
    @Test
    public void testRemoveListe() {
        final List<Essai> beans = new ArrayList<Essai>();
        final Essai bean1 = Mockito.mock(Essai.class);
        final Essai bean2 = Mockito.mock(Essai.class);
        beans.add(bean1);
        beans.add(bean2);
        this.essaiService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((Essai) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((Essai) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final Essai bean = Mockito.mock(Essai.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.essaiService.remove(bean);
            Assert.fail(EssaiServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
