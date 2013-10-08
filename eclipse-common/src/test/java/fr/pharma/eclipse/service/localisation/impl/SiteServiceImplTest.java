package fr.pharma.eclipse.service.localisation.impl;

import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.localisation.Site;
import fr.pharma.eclipse.domain.model.suivi.localisation.SiteSuivi;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;

/**
 * Classe en charge de tester le service de gestion des sites.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SiteServiceImplTest {

    /**
     * Service de gestion de sites à tester.
     */
    private SiteServiceImpl service;

    /**
     * Dao mocké.
     */
    private GenericDao<Site> mockDao;

    /**
     * Suivi Factory mocké.
     */
    private SuiviFactory<SiteSuivi> mockSuiviFactory;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.mockDao = Mockito.mock(GenericDao.class);
        this.service = new SiteServiceImpl(this.mockDao);
        this.mockSuiviFactory = Mockito.mock(SuiviFactory.class);
        this.service.setSiteSuiviFactory(this.mockSuiviFactory);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.mockDao = null;
        this.service = null;
        this.mockSuiviFactory = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.mockDao);
        Assert.assertNotNull(this.mockSuiviFactory);
    }

    /**
     * Méthode en charge de tester la sauvegarde des sites.
     */
    @Test
    public void testSaveSite() {
        final Site site = Mockito.mock(Site.class);
        final SiteSuivi suivi = new SiteSuivi();
        Mockito.when(this.mockDao.reattach(site)).thenReturn(site);
        Mockito.when(this.mockSuiviFactory.getInitializedObject()).thenReturn(suivi);
        Mockito.when(site.getModifs()).thenReturn(new TreeSet<SiteSuivi>());
        Mockito.when(this.mockDao.save(site)).thenReturn(site);
        final Site result = this.service.save(site);
        Mockito.verify(this.mockDao).reattach(site);
        Mockito.verify(this.mockSuiviFactory).getInitializedObject();
        Assert.assertEquals(1, result.getModifs().size());
    }

}
