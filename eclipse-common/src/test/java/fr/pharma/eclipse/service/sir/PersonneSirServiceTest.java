package fr.pharma.eclipse.service.sir;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;

import fr.pharma.eclipse.dao.sir.GenericSirDao;
import fr.pharma.eclipse.domain.criteria.sir.PersonneSirSearchCriteria;
import fr.pharma.eclipse.domain.model.sir.PersonneSir;
import fr.pharma.eclipse.service.sir.impl.GenericSirServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier PersonneSir.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class PersonneSirServiceTest {
    /**
     * Service.
     */
    private GenericSirServiceImpl<PersonneSir> personneSirService;

    /**
     * Mock de la DAO.
     */
    private GenericSirDao<PersonneSir> mockedDao;

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
        this.mockedDao = Mockito.mock(GenericSirDao.class);
        this.personneSirService = new GenericSirServiceImpl<PersonneSir>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.personneSirService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.personneSirService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un personneSir pour le test.
     * @return Editeur initialisé.
     */
    private PersonneSir makePersonneSirTest() {
        final PersonneSir personneSir = new PersonneSir();
        personneSir.setId(1);
        return personneSir;
    }

    /**
     * Crée une liste de personneSirs pour le test.
     * @return La liste de PersonneSir initialisés.
     */
    private List<PersonneSir> makeListPersonneSirTest() {
        final List<PersonneSir> personneSirs = new ArrayList<PersonneSir>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            personneSirs.add(this.makePersonneSirTest());
        }
        return personneSirs;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final PersonneSir personneSirExpected = this.makePersonneSirTest();
        final Integer idTest = 1;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(personneSirExpected);

        final PersonneSir personneSir = this.personneSirService.get(idTest);

        Assert.assertNotNull(personneSir);
        Assert.assertEquals(personneSirExpected, personneSir);
    }

    /**
     * Test de la méthode get(int) KO.
     */
    @Test
    public void testGetIntKo() {
        final Integer idTest = 1;
        final MockitoException exception = new MockitoException("testGetIntKo");
        Mockito.when(this.mockedDao.get(idTest)).thenThrow(exception);
        try {
            this.personneSirService.get(idTest);
            Assert.fail(PersonneSirServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les PersonneSir avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllOK() {
        final List<PersonneSir> personneSirsExp = this.makeListPersonneSirTest();
        Mockito.when(this.mockedDao.getAll()).thenReturn(personneSirsExp);

        final List<PersonneSir> personneSirsRetrieved = this.personneSirService.getAll();

        Assert.assertEquals(personneSirsExp.size(), personneSirsRetrieved.size());
        for (final PersonneSir personneSir : personneSirsExp) {
            Assert.assertTrue(personneSirsRetrieved.contains(personneSir));
        }
    }

    /**
     * Test de l'appel de liste de tous les PersonneSir avec un critère de
     * recherche OK.
     */
    @Test
    public void testGetAllWithCriteria() {
        final PersonneSirSearchCriteria criteria = new PersonneSirSearchCriteria();
        criteria.setNom("nom");
        criteria.setLogin("login");
        criteria.setPrenom("prenom");
        final List<PersonneSir> personneSirsExp = this.makeListPersonneSirTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(personneSirsExp);

        final List<PersonneSir> personneSirsRetrieved = this.personneSirService.getAll(criteria);

        Assert.assertEquals(personneSirsExp.size(), personneSirsRetrieved.size());
        for (final PersonneSir personneSir : personneSirsExp) {
            Assert.assertTrue(personneSirsRetrieved.contains(personneSir));
        }
    }

    /**
     * Test de l'appel de liste de tous les PersonneSir avec un critère de
     * recherche KO.
     */
    @Test
    public void testGetAllKO() {
        final PersonneSirSearchCriteria criteria = new PersonneSirSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.personneSirService.getAll(criteria);
            Assert.fail(PersonneSirServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

}
