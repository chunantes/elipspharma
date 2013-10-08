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
import fr.pharma.eclipse.domain.criteria.localisation.SiteSearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Site;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.utils.TestConstants;

/**
 * Classe de test du service pour le bean métier Site.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class SiteServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<Site> siteService;

    /**
     * Mock de la DAO.
     */
    private GenericDao<Site> mockedDao;

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
        this.siteService = new GenericServiceImpl<Site>(this.mockedDao);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.siteService = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.siteService);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un site pour le test.
     * @return Editeur initialisé.
     */
    private Site makeSiteTest() {
        final Site site = new Site();
        site.setId(1L);
        return site;
    }

    /**
     * Crée une liste de sites pour le test.
     * @return La liste de Site initialisés.
     */
    private List<Site> makeListSiteTest() {
        final List<Site> sites = new ArrayList<Site>();
        for (int i = 0; i < TestConstants.TROIS; i++) {
            sites.add(this.makeSiteTest());
        }
        return sites;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final Site siteExpected = this.makeSiteTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(siteExpected);

        final Site site = this.siteService.get(idTest);

        Assert.assertNotNull(site);
        Assert.assertEquals(siteExpected, site);
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
            this.siteService.get(idTest);
            Assert.fail(SiteServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final Site siteToSave = this.makeSiteTest();
        Mockito.when(this.mockedDao.save(siteToSave)).thenReturn(siteToSave);
        Site siteSaved = null;
        siteSaved = this.siteService.save(siteToSave);
        Assert.assertEquals(siteSaved, siteToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final Site siteToSave = this.makeSiteTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(siteToSave)).thenThrow(exception);
        try {
            this.siteService.save(siteToSave);
            Assert.fail(SiteServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de liste de tous les Site avec un critère de recherche
     * OK.
     */
    @Test
    public void testGetAllOK() {
        final SiteSearchCriteria criteria = new SiteSearchCriteria();
        criteria.setNom("NOM");
        final List<Site> sitesExp = this.makeListSiteTest();
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(sitesExp);

        final List<Site> sitesRetrieved = this.siteService.getAll(criteria);

        Assert.assertEquals(sitesExp.size(), sitesRetrieved.size());
        for (final Site site : sitesExp) {
            Assert.assertTrue(sitesRetrieved.contains(site));
        }
    }

    /**
     * Test de l'appel de liste de tous les Site avec un critère de recherche
     * KO.
     */
    @Test
    public void testGetAllKo() {
        final SiteSearchCriteria criteria = new SiteSearchCriteria();
        final MockitoException exception = new MockitoException("GetAllParamKO");
        Mockito.when(this.mockedDao.getAll(criteria)).thenThrow(exception);
        try {
            this.siteService.getAll(criteria);
            Assert.fail(SiteServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final Site bean = Mockito.mock(Site.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.siteService.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de sites.
     */
    @Test
    public void testRemoveListe() {
        final List<Site> beans = new ArrayList<Site>();
        final Site bean1 = Mockito.mock(Site.class);
        final Site bean2 = Mockito.mock(Site.class);
        beans.add(bean1);
        beans.add(bean2);
        this.siteService.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((Site) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((Site) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final Site bean = Mockito.mock(Site.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.siteService.remove(bean);
            Assert.fail(SiteServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}
