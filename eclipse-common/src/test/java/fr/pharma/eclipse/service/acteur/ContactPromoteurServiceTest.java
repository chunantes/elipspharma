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
import fr.pharma.eclipse.domain.model.acteur.ContactPromoteur;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier ContactPromoteur.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class ContactPromoteurServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<ContactPromoteur> contactPromoteurService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<ContactPromoteur> mockedDao;

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
        this.contactPromoteurService = new GenericServiceImpl<ContactPromoteur>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.contactPromoteurService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.contactPromoteurService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un contactPromoteur pour le test.
     * @return Editeur initialisé.
     */
    private ContactPromoteur makeContactPromoteurTest() {
        final ContactPromoteur contactPromoteur = new ContactPromoteur();
        contactPromoteur.setId(1L);
        return contactPromoteur;
    }

    /**
     * Crée une liste de contactPromoteurs pour le test.
     * @return La liste de ContactPromoteur initialisés.
     */
    private List<ContactPromoteur> makeListContactPromoteurTest() {
        final List<ContactPromoteur> contactPromoteurs = new ArrayList<ContactPromoteur>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            contactPromoteurs.add(this.makeContactPromoteurTest());
        }
        return contactPromoteurs;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final ContactPromoteur contactPromoteurExpected = this.makeContactPromoteurTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(contactPromoteurExpected);

        final ContactPromoteur contactPromoteur = this.contactPromoteurService.get(idTest);

        Assert.assertNotNull(contactPromoteur);
        Assert.assertEquals(contactPromoteurExpected, contactPromoteur);
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
            this.contactPromoteurService.get(idTest);
            Assert.fail(ContactPromoteurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final ContactPromoteur contactPromoteurToSave = this.makeContactPromoteurTest();
        Mockito.when(this.mockedDao.save(contactPromoteurToSave)).thenReturn(contactPromoteurToSave);
        ContactPromoteur contactPromoteurSaved = null;
        contactPromoteurSaved = this.contactPromoteurService.save(contactPromoteurToSave);
        Assert.assertEquals(contactPromoteurSaved, contactPromoteurToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final ContactPromoteur contactPromoteurToSave = this.makeContactPromoteurTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(contactPromoteurToSave)).thenThrow(exception);
        try {
            this.contactPromoteurService.save(contactPromoteurToSave);
            Assert.fail(ContactPromoteurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les ContactPromoteur avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        criteria.setNom("nom");
        final List<ContactPromoteur> contactPromoteursExp = this.makeListContactPromoteurTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(contactPromoteursExp);

        final List<ContactPromoteur> contactPromoteursRetrieved = this.contactPromoteurService.getAll(criteria);

        Assert.assertEquals(contactPromoteursExp.size(), contactPromoteursRetrieved.size());
        for (final ContactPromoteur contactPromoteur : contactPromoteursExp) {
            Assert.assertTrue(contactPromoteursRetrieved.contains(contactPromoteur));
        }
    }

    /**
     * Test de l'appel de liste de tous les ContactPromoteur avec un critère de
     * recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.contactPromoteurService.getAll(criteria);
            Assert.fail(ContactPromoteurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final ContactPromoteur bean = Mockito.mock(ContactPromoteur.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.contactPromoteurService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de contactPromoteurs.
     */
    @Test
    public void testRemoveListe() {
        final List<ContactPromoteur> beans = new ArrayList<ContactPromoteur>();
        final ContactPromoteur bean1 = Mockito.mock(ContactPromoteur.class);
        final ContactPromoteur bean2 = Mockito.mock(ContactPromoteur.class);
        beans.add(bean1);
        beans.add(bean2);
        this.contactPromoteurService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((ContactPromoteur) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((ContactPromoteur) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final ContactPromoteur bean = Mockito.mock(ContactPromoteur.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.contactPromoteurService.remove(bean);
            Assert.fail(ContactPromoteurServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
