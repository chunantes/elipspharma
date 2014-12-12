package fr.pharma.eclipse.service.stock.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.user.UserService;
import fr.pharma.eclipse.utils.TestConstants;
import fr.pharma.eclipse.utils.message.MessageBuilder;

/**
 * Classe de test du service pour le bean métier Approvisionnement.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class ApprovisionnementServiceImplTest {
    /** Test classe */
    private ApprovisionnementServiceImpl<Approvisionnement> approvisionnementService;

    /** Services */
    private GenericServiceImpl<Approvisionnement> genericService;
    private GenericDao<Approvisionnement> mockedDao;
    private UserService mockedUserService;
    private MessageBuilder messageBuilder;

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
        this.approvisionnementService = new ApprovisionnementServiceImpl<Approvisionnement>(this.mockedDao);

        this.mockedUserService = Mockito.mock(UserService.class);
        this.approvisionnementService.setUserService(this.mockedUserService);

        this.messageBuilder = Mockito.mock(MessageBuilder.class);
        this.approvisionnementService.setMessageBuilder(this.messageBuilder);

        this.genericService = new GenericServiceImpl<Approvisionnement>(this.mockedDao);

    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.approvisionnementService = null;
        this.genericService = null;
        this.mockedUserService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.approvisionnementService);
        Assert.assertNotNull(this.genericService);
        Assert.assertNotNull(this.mockedDao);
        Assert.assertNotNull(this.mockedUserService);
    }

    /**
     * Crée un approvisionnement pour le test.
     * @return Editeur initialisé.
     */
    private Approvisionnement makeApprovisionnementTest() {
        final Approvisionnement approvisionnement = new Approvisionnement();
        approvisionnement.setId(1L);
        return approvisionnement;
    }

    /**
     * Crée une liste de approvisionnements pour le test.
     * @return La liste de Approvisionnement initialisés.
     */
    private List<Approvisionnement> makeListApprovisionnementTest() {
        final List<Approvisionnement> approvisionnements = new ArrayList<Approvisionnement>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            approvisionnements.add(this.makeApprovisionnementTest());
        }
        return approvisionnements;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final Approvisionnement approvisionnementExpected = this.makeApprovisionnementTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(approvisionnementExpected);

        final Approvisionnement approvisionnement = this.approvisionnementService.get(idTest);

        Assert.assertNotNull(approvisionnement);
        Assert.assertEquals(approvisionnementExpected, approvisionnement);
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
            this.approvisionnementService.get(idTest);
            Assert.fail(ApprovisionnementServiceImplTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final Approvisionnement approvisionnementToSave = this.makeApprovisionnementTest();
        Mockito.when(this.mockedDao.save(approvisionnementToSave)).thenReturn(approvisionnementToSave);

        Mockito.when(this.mockedUserService.getPersonne()).thenReturn(new Pharmacien());

        Approvisionnement approvisionnementSaved = null;
        approvisionnementSaved = this.approvisionnementService.save(approvisionnementToSave);
        Assert.assertEquals(approvisionnementSaved, approvisionnementToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final Approvisionnement approvisionnementToSave = this.makeApprovisionnementTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(approvisionnementToSave)).thenThrow(exception);
        try {
            this.approvisionnementService.save(approvisionnementToSave);
            Assert.fail(ApprovisionnementServiceImplTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
    /**
     * Test de l'appel de liste de tous les Approvisionnement avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        criteria.setTypeMouvement(TypeMvtStock.APPROVISIONNEMENT);
        final List<Approvisionnement> approvisionnementsExp = this.makeListApprovisionnementTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(approvisionnementsExp);

        final List<Approvisionnement> approvisionnementsRetrieved = this.genericService.getAll(criteria);

        Assert.assertEquals(approvisionnementsExp.size(), approvisionnementsRetrieved.size());
        for (final Approvisionnement approvisionnement : approvisionnementsExp) {
            Assert.assertTrue(approvisionnementsRetrieved.contains(approvisionnement));
        }
    }

    /**
     * Test de l'appel de liste de tous les Approvisionnement avec un critère de
     * recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.approvisionnementService.getAll(criteria);
            Assert.fail(ApprovisionnementServiceImplTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final Approvisionnement bean = Mockito.mock(Approvisionnement.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.approvisionnementService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de approvisionnements.
     */
    @Test
    public void testRemoveListe() {
        final List<Approvisionnement> beans = new ArrayList<Approvisionnement>();
        final Approvisionnement bean1 = Mockito.mock(Approvisionnement.class);
        final Approvisionnement bean2 = Mockito.mock(Approvisionnement.class);
        beans.add(bean1);
        beans.add(bean2);
        this.approvisionnementService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((Approvisionnement) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((Approvisionnement) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final Approvisionnement bean = Mockito.mock(Approvisionnement.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.approvisionnementService.remove(bean);
            Assert.fail(ApprovisionnementServiceImplTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /** Cas nominal */
    @Test
    public void testUpdateDatePeremption() throws InterruptedException {
        // Prepare
        final Approvisionnement appro = new Approvisionnement();
        appro.setExtensionPeremption(false);
        appro.setDatePeremption(Calendar.getInstance());
        Thread.sleep(1);
        final Calendar newDatePeremption = Calendar.getInstance();

        Mockito.when(this.messageBuilder.getMessage(Matchers.anyString())).thenReturn("Test");

        // Test
        this.approvisionnementService.updateDatePeremption(appro, newDatePeremption, "TEST");

        // Verify
        Assert.assertEquals(newDatePeremption, appro.getDatePeremption());
        Assert.assertTrue(appro.getExtensionPeremption());
        Assert.assertFalse(StringUtils.isEmpty(appro.getHistoriqueExtensionPeremption()));
        Assert.assertEquals("TEST", appro.getCommentaireExtensionPeremption());
    }

    /** Nouvelle date peremption avant date actuelle */
    @Test
    public void testUpdateDatePeremptionNoExtenstion() throws InterruptedException {
        // Prepare
        final Approvisionnement appro = new Approvisionnement();
        appro.setExtensionPeremption(false);
        final Calendar newDatePeremption = Calendar.getInstance();
        Thread.sleep(1);
        appro.setDatePeremption(Calendar.getInstance());

        Mockito.when(this.messageBuilder.getMessage(Matchers.anyString())).thenReturn("Test");

        // Test
        this.approvisionnementService.updateDatePeremption(appro, newDatePeremption, "TEST2");

        // Verify
        Assert.assertEquals(newDatePeremption, appro.getDatePeremption());
        Assert.assertFalse(appro.getExtensionPeremption());
        Assert.assertFalse(StringUtils.isEmpty(appro.getHistoriqueExtensionPeremption()));
        Assert.assertEquals("TEST2", appro.getCommentaireExtensionPeremption());
    }

}
