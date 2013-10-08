package fr.pharma.eclipse.service.localisation.impl;

import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.suivi.localisation.ServiceSuivi;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;

/**
 * Classe en charge de tester le service de gestion des services.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ServiceServiceImplTest {

    /**
     * Service de gestion de services à tester.
     */
    private ServiceServiceImpl service;

    /**
     * Dao mocké.
     */
    private GenericDao<Service> mockDao;

    /**
     * Suivi Factory mocké.
     */
    private SuiviFactory<ServiceSuivi> mockSuiviFactory;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.mockDao = Mockito.mock(GenericDao.class);
        this.service = new ServiceServiceImpl(this.mockDao);
        this.mockSuiviFactory = Mockito.mock(SuiviFactory.class);
        this.service.setServiceSuiviFactory(this.mockSuiviFactory);
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
     * Méthode en charge de tester la sauvegarde des services.
     */
    @Test
    public void testSaveService() {
        final Service serv = Mockito.mock(Service.class);
        final ServiceSuivi suivi = new ServiceSuivi();
        Mockito.when(this.mockDao.reattach(serv)).thenReturn(serv);
        Mockito.when(this.mockSuiviFactory.getInitializedObject()).thenReturn(suivi);
        Mockito.when(serv.getModifs()).thenReturn(new TreeSet<ServiceSuivi>());
        Mockito.when(this.mockDao.save(serv)).thenReturn(serv);
        final Service result = this.service.save(serv);
        Mockito.verify(this.mockDao).reattach(serv);
        Mockito.verify(this.mockSuiviFactory).getInitializedObject();
        Assert.assertEquals(1, result.getModifs().size());
    }

}
