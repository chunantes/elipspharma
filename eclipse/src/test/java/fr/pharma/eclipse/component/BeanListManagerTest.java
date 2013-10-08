package fr.pharma.eclipse.component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.DataModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.component.helper.BeanManagerHelper;
import fr.pharma.eclipse.domain.criteria.stockage.PharmacieSearchCriteria;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.redirect.RedirectHandler;
import fr.pharma.eclipse.utils.PharmacieUtils;

/**
 * Classe en charge de tester le manager de beans.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BeanListManagerTest {
    /**
     * BeansManager à tester.
     */
    private BeanListManager<Pharmacie> pharmaciesManager;

    /**
     * Critère de recherche de pharmacie.
     */
    private PharmacieSearchCriteria criteria;

    /**
     * Mock du handler de redirection.
     */
    private RedirectHandler mockRedirect;

    /**
     * Helper mocké.
     */
    private BeanManagerHelper<Pharmacie> mockedHelper;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @SuppressWarnings("unchecked")
    @Before
    public void init() {
        this.criteria = new PharmacieSearchCriteria();
        this.pharmaciesManager = new BeanListManager<Pharmacie>(this.criteria);
        final Pharmacie pharmacie1 = new Pharmacie();
        pharmacie1.setId(1L);
        pharmacie1.setNom("pharma1");
        final Pharmacie pharmacie2 = new Pharmacie();
        pharmacie2.setId(2L);
        pharmacie2.setNom("pharma2");
        pharmacie2.setSelected(Boolean.TRUE);
        final List<Pharmacie> pharmacies = new ArrayList<Pharmacie>();
        pharmacies.add(pharmacie1);
        pharmacies.add(pharmacie2);
        this.pharmaciesManager.setBeans(pharmacies);
        this.mockRedirect = Mockito.mock(RedirectHandler.class);
        this.pharmaciesManager.setRedirectHandler(this.mockRedirect);
        this.mockedHelper = Mockito.mock(BeanManagerHelper.class);
        this.pharmaciesManager.setHelper(this.mockedHelper);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.criteria = null;
        this.pharmaciesManager = null;
        this.mockRedirect = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.pharmaciesManager);
        Assert.assertNotNull(this.criteria);
        Assert.assertNotNull(this.pharmaciesManager.getSearchCriteria());
        Assert.assertNotNull(this.pharmaciesManager.getBeans());
        Assert.assertNotNull(this.pharmaciesManager.getRedirectHandler());
        Assert.assertNotNull(this.pharmaciesManager.getHelper());
        Assert.assertNotNull(this.mockRedirect);
    }

    /**
     * Méthode en charge de tester la récupération des beans sous forme de
     * Model.
     */
    @Test
    public void testGetModel() {
        final DataModel<Pharmacie> result = this.pharmaciesManager.getModel();
        Assert.assertEquals(result.getRowCount(), this.pharmaciesManager.getBeans().size());
    }

    /**
     * Méthode en charge de tester la récupération des beans sélectionnés dans
     * la liste (ceux dont le booléen checked est true).
     */
    @Test
    public void testGetBeansSelected() {
        long id = 1;
        final List<Pharmacie> expectedList = new ArrayList<Pharmacie>();
        expectedList.add(PharmacieUtils.makePharmacieTest(id++));
        expectedList.add(PharmacieUtils.makePharmacieTest(id++));
        Mockito.when(this.mockedHelper.getBeansSelected(this.pharmaciesManager.getBeans())).thenReturn(expectedList);
        final List<Pharmacie> beansSelected = this.pharmaciesManager.getBeansSelected();
        Assert.assertNotNull(beansSelected);
        Assert.assertEquals(expectedList, beansSelected);
    }

    /**
     * Méthode en charge de tester la méthode de redirection.
     * @throws IOException Exception Input/Output.
     */
    @Test
    public void testRedirectWithSelectedNotNull() throws IOException {
        final String viewName = "editPharmacie";
        final String idName = "idPharmacie";
        this.pharmaciesManager.setBeanSelected(this.pharmaciesManager.getBeans().get(1));
        Assert.assertNotNull(this.pharmaciesManager.getBeanSelected());
        this.pharmaciesManager.redirect(viewName, idName);
        final String urlRedirect = viewName + "?" + idName + "=2";
        Mockito.verify(this.mockRedirect).redirect(urlRedirect);
        Assert.assertNull(this.pharmaciesManager.getBeanSelected());
    }

    /**
     * Méthode en charge de tester la méthode de redirection.
     * @throws IOException Exception Input/Output.
     */
    @Test
    public void testRedirectWithSelectedNull() throws IOException {
        final String viewName = "editPharmacie";
        final String idName = "idPharmacie";
        this.pharmaciesManager.setBeanSelected(null);
        Assert.assertNull(this.pharmaciesManager.getBeanSelected());
        this.pharmaciesManager.redirect(viewName, idName);
        final String urlRedirect = viewName;
        Mockito.verify(this.mockRedirect).redirect(urlRedirect);
        Assert.assertNull(this.pharmaciesManager.getBeanSelected());
    }

    /**
     * Test de la méthode resetBeanSelected.
     */
    @Test
    public void testResetBeanSelected() {
        this.pharmaciesManager.setBeanSelected(Mockito.mock(Pharmacie.class));
        Assert.assertNotNull(this.pharmaciesManager.getBeanSelected());
        this.pharmaciesManager.resetBeanSelected();
        Assert.assertNull(this.pharmaciesManager.getBeanSelected());
    }

}
