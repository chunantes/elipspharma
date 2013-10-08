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
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier Pharmacien.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class PharmacienServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<Pharmacien> pharmacienService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<Pharmacien> mockedDao;

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
        this.pharmacienService = new GenericServiceImpl<Pharmacien>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.pharmacienService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.pharmacienService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un pharmacien pour le test.
     * @return Editeur initialisé.
     */
    private Pharmacien makePharmacienTest() {
        final Pharmacien pharmacien = new Pharmacien();
        pharmacien.setId(1L);
        return pharmacien;
    }

    /**
     * Crée une liste de pharmaciens pour le test.
     * @return La liste de Pharmacien initialisés.
     */
    private List<Pharmacien> makeListPharmacienTest() {
        final List<Pharmacien> pharmaciens = new ArrayList<Pharmacien>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            pharmaciens.add(this.makePharmacienTest());
        }
        return pharmaciens;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final Pharmacien pharmacienExpected = this.makePharmacienTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(pharmacienExpected);

        final Pharmacien pharmacien = this.pharmacienService.get(idTest);

        Assert.assertNotNull(pharmacien);
        Assert.assertEquals(pharmacienExpected, pharmacien);
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
            this.pharmacienService.get(idTest);
            Assert.fail(PharmacienServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final Pharmacien pharmacienToSave = this.makePharmacienTest();
        Mockito.when(this.mockedDao.save(pharmacienToSave)).thenReturn(pharmacienToSave);
        Pharmacien pharmacienSaved = null;
        pharmacienSaved = this.pharmacienService.save(pharmacienToSave);
        Assert.assertEquals(pharmacienSaved, pharmacienToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final Pharmacien pharmacienToSave = this.makePharmacienTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(pharmacienToSave)).thenThrow(exception);
        try {
            this.pharmacienService.save(pharmacienToSave);
            Assert.fail(PharmacienServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les Pharmacien avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        criteria.setNom("nom");
        final List<Pharmacien> pharmaciensExp = this.makeListPharmacienTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(pharmaciensExp);

        final List<Pharmacien> pharmaciensRetrieved = this.pharmacienService.getAll(criteria);

        Assert.assertEquals(pharmaciensExp.size(), pharmaciensRetrieved.size());
        for (final Pharmacien pharmacien : pharmaciensExp) {
            Assert.assertTrue(pharmaciensRetrieved.contains(pharmacien));
        }
    }

    /**
     * Test de l'appel de liste de tous les Pharmacien avec un critère de
     * recherche KO.
     */
    @Test
    public void testGetAllKo() {
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.pharmacienService.getAll(criteria);
            Assert.fail(PharmacienServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final Pharmacien bean = Mockito.mock(Pharmacien.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.pharmacienService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de pharmaciens.
     */
    @Test
    public void testRemoveListe() {
        final List<Pharmacien> beans = new ArrayList<Pharmacien>();
        final Pharmacien bean1 = Mockito.mock(Pharmacien.class);
        final Pharmacien bean2 = Mockito.mock(Pharmacien.class);
        beans.add(bean1);
        beans.add(bean2);
        this.pharmacienService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((Pharmacien) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((Pharmacien) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final Pharmacien bean = Mockito.mock(Pharmacien.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.pharmacienService.remove(bean);
            Assert.fail(PharmacienServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
