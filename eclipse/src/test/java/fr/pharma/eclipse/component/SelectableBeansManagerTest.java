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

import fr.pharma.eclipse.component.wrapper.SelectableBean;
import fr.pharma.eclipse.domain.criteria.stockage.PharmacieSearchCriteria;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.redirect.RedirectHandler;

/**
 * Test de la classe SelectableBeansManager.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SelectableBeansManagerTest {
    /**
     * BeansManager à tester.
     */
    private SelectableBeansManager<Pharmacie> pharmaciesManager;

    /**
     * Critère de recherche de pharmacie.
     */
    private PharmacieSearchCriteria criteria;

    /**
     * Mock du handler de redirection.
     */
    private RedirectHandler mockRedirect;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.criteria = new PharmacieSearchCriteria();
        this.pharmaciesManager = new SelectableBeansManager<Pharmacie>(this.criteria);
        final Pharmacie pharmacie1 = new Pharmacie();
        pharmacie1.setId(1L);
        pharmacie1.setNom("pharma1");
        final Pharmacie pharmacie2 = new Pharmacie();
        pharmacie2.setId(2L);
        pharmacie2.setNom("pharma2");
        final SelectableBean<Pharmacie> wrapper1 = new SelectableBean<Pharmacie>(pharmacie1);
        final SelectableBean<Pharmacie> wrapper2 = new SelectableBean<Pharmacie>(pharmacie2);
        wrapper2.setSelected(Boolean.TRUE);
        final List<SelectableBean<Pharmacie>> pharmacies = new ArrayList<SelectableBean<Pharmacie>>();
        pharmacies.add(wrapper1);
        pharmacies.add(wrapper2);
        this.pharmaciesManager.setBeans(pharmacies);
        this.mockRedirect = Mockito.mock(RedirectHandler.class);
        this.pharmaciesManager.setRedirectHandler(this.mockRedirect);
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
        final List<SelectableBean<Pharmacie>> beansSelected = this.pharmaciesManager.getBeansSelected();
        Assert.assertNotNull(beansSelected);
        Assert.assertEquals(1, beansSelected.size());
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
        this.pharmaciesManager.setBeanSelected(new SelectableBean<Pharmacie>(Mockito.mock(Pharmacie.class)));
        Assert.assertNotNull(this.pharmaciesManager.getBeanSelected());
        this.pharmaciesManager.resetBeanSelected();
        Assert.assertNull(this.pharmaciesManager.getBeanSelected());
    }
}
