package fr.pharma.eclipse.service.stock.impl;

import java.util.Calendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.service.user.UserService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester le service de gestion des mouvements de stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class MvtStockServiceImplTest {
    /**
     * MvtStockServiceImpl à tester.
     */
    private MvtStockServiceImpl<Approvisionnement> service;

    /**
     * Dao de gestion des mouvements de stock.
     */
    private GenericDao<Approvisionnement> mockDao;

    /**
     * Mock du service de gestion des utilisateurs.
     */
    private UserService mockUserService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.mockDao = Mockito.mock(GenericDao.class);
        this.service = new MvtStockServiceImpl<Approvisionnement>(this.mockDao);
        this.mockUserService = Mockito.mock(UserService.class);
        this.service.setUserService(this.mockUserService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.mockDao = null;
        this.service = null;
        this.mockUserService = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.mockDao);
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.mockUserService);
    }

    /**
     * Méthode en charge de tester la méthode de sauvegarde.
     */
    @Test
    public void testSaveMVT() {
        final Approvisionnement approvisionnement = Mockito.mock(Approvisionnement.class);
        approvisionnement.setDateCreation(Calendar.getInstance(EclipseConstants.LOCALE));
        Mockito.when(this.mockDao.save(approvisionnement)).thenReturn(approvisionnement);
        final Approvisionnement result = this.service.save(approvisionnement);
        Assert.assertNotNull(result);
        Mockito.verify(this.mockUserService).getPersonne();
    }

    /**
     * Méthode en charge de tester la méthode getAll.
     */
    @Test
    public void testGetAll() {
        this.service.getAll();
        Mockito.verify(this.mockDao).getAll(Matchers.any(SearchCriteria.class));
    }

    /**
     * Méthode en charge de tester la méthode getAll.
     */
    @Test
    public void testGetAllWithCriteria() {
        this.service.getAll((SearchCriteria) Matchers.any());
        Mockito.verify(this.mockDao).getAll(Matchers.any(SearchCriteria.class));
    }

}
