package fr.pharma.eclipse.component.surcout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.SortedSet;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.component.essai.EssaiManager;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.surcout.DetailSurcout;
import fr.pharma.eclipse.domain.model.surcout.Grille;
import fr.pharma.eclipse.domain.model.surcout.GrilleModele;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.factory.surcout.GrilleFactory;
import fr.pharma.eclipse.service.surcout.GrilleModeleService;
import fr.pharma.eclipse.service.surcout.GrilleService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Description de la classe.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GrilleManagerTest extends AbstractEclipseJUnitTest {

    /**
     * Manager de grille.
     */
    private GrilleManager manager;

    /**
     * Service grille.
     */
    private GrilleService service;

    /**
     * Manager d'essai.
     */
    private EssaiManager essaiManager;

    /**
     * Factory de grille.
     */
    private GrilleFactory grilleFactory;

    /**
     * Grille model service.
     */
    private GrilleModeleService grilleModeleService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.grilleModeleService = Mockito.mock(GrilleModeleService.class);
        this.grilleFactory = Mockito.mock(GrilleFactory.class);
        this.service = Mockito.mock(GrilleService.class);
        this.essaiManager = Mockito.mock(EssaiManager.class);
        this.manager = new GrilleManager(this.service);
        this.manager.setEssaiManager(this.essaiManager);
        this.manager.setGrilleFactory(this.grilleFactory);
        this.manager.setGrilleModeleService(this.grilleModeleService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.essaiManager = null;
        this.grilleFactory = null;
        this.grilleModeleService = null;
        this.manager = null;
        this.service = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.essaiManager);
        Assert.assertNotNull(this.grilleFactory);
        Assert.assertNotNull(this.grilleModeleService);
        Assert.assertNotNull(this.service);
    }

    /**
     * Test dela méthode init().
     */
    @Test
    public void testInitMethodNull() {
        final DetailSurcout detail = Mockito.mock(DetailSurcout.class);
        final Essai essai = Mockito.mock(Essai.class);
        Mockito.when(this.essaiManager.getBean()).thenReturn(essai);
        Mockito.when(essai.getDetailSurcout()).thenReturn(detail);
        Mockito.when(detail.getGrille()).thenReturn(null);
        this.manager.init();
        Assert.assertNull(this.manager.getBean());
        Assert.assertNull(this.manager.getGrilleModeleSelected());
    }

    /**
     * Test dela méthode init().
     */
    @Test
    public void testInitMethodIdNull() {
        final Grille grille = new Grille();
        final DetailSurcout detail = Mockito.mock(DetailSurcout.class);
        final Essai essai = Mockito.mock(Essai.class);
        Mockito.when(this.essaiManager.getBean()).thenReturn(essai);
        Mockito.when(essai.getDetailSurcout()).thenReturn(detail);
        Mockito.when(detail.getGrille()).thenReturn(grille);
        this.manager.init();
        Assert.assertNotNull(this.manager.getBean());
        Assert.assertNull(this.manager.getGrilleModeleSelected());
    }

    /**
     * Test dela méthode init().
     */
    @Test
    public void testInitMethodIdNotNull() {
        final Grille grille = new Grille();
        grille.setId(1L);
        grille.setGrilleModele(new GrilleModele());
        final DetailSurcout detail = Mockito.mock(DetailSurcout.class);
        final Essai essai = Mockito.mock(Essai.class);
        Mockito.when(this.essaiManager.getBean()).thenReturn(essai);
        Mockito.when(essai.getDetailSurcout()).thenReturn(detail);
        Mockito.when(detail.getGrille()).thenReturn(grille);
        this.manager.init();
        Assert.assertNotNull(this.manager.getBean());
        Assert.assertNotNull(this.manager.getGrilleModeleSelected());
    }

    /**
     * Test de la méthode handleSelectGrilleModele.
     */
    @Test
    public void testHandleSelectGrilleModeleNull() {
        final DetailSurcout detail = Mockito.mock(DetailSurcout.class);
        final Essai essai = Mockito.mock(Essai.class);
        Mockito.when(this.essaiManager.getBean()).thenReturn(essai);
        Mockito.when(essai.getDetailSurcout()).thenReturn(detail);
        Mockito.when(detail.getGrille()).thenReturn(null);

        final HtmlSelectOneMenu htmlSelectOneMenu = Mockito.mock(HtmlSelectOneMenu.class);
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        Mockito.when(event.getSource()).thenReturn(htmlSelectOneMenu);
        Mockito.when(htmlSelectOneMenu.getLocalValue()).thenReturn(null);

        this.manager.handleSelectGrilleModele(event);

        Assert.assertNull(this.manager.getBean());
    }

    /**
     * Test de la méthode handleSelectGrilleModele.
     */
    @Test
    public void testHandleSelectGrilleModeleNotNull() {
        final Grille grille = new Grille();
        final DetailSurcout detail = Mockito.mock(DetailSurcout.class);
        final Essai essai = Mockito.mock(Essai.class);
        Mockito.when(this.essaiManager.getBean()).thenReturn(essai);
        Mockito.when(essai.getDetailSurcout()).thenReturn(detail);
        Mockito.when(detail.getGrille()).thenReturn(grille);

        final GrilleModele modele = new GrilleModele();
        final HtmlSelectOneMenu htmlSelectOneMenu = Mockito.mock(HtmlSelectOneMenu.class);
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        Mockito.when(event.getSource()).thenReturn(htmlSelectOneMenu);
        Mockito.when(htmlSelectOneMenu.getLocalValue()).thenReturn(modele);
        Mockito.when(this.grilleFactory.getInitializedObject(Matchers.any(GrilleModele.class))).thenReturn(grille);
        this.manager.handleSelectGrilleModele(event);

        Assert.assertNotNull(this.manager.getBean());
        Assert.assertNotNull(this.manager.getBean().getDetailSurcout());
        Mockito.verify(this.grilleFactory).getInitializedObject(Matchers.any(GrilleModele.class));
    }

    /**
     * TEst de la méthode removeGrille.
     */
    @Test
    public void testRemoveGrille() {
        final Grille grille = new Grille();
        final DetailSurcout detail = Mockito.mock(DetailSurcout.class);
        final Essai essai = Mockito.mock(Essai.class);
        Mockito.when(this.essaiManager.getBean()).thenReturn(essai);
        Mockito.when(essai.getDetailSurcout()).thenReturn(detail);
        Mockito.when(detail.getGrille()).thenReturn(grille).thenReturn(null);
        this.manager.removeGrille();

        Mockito.verify(detail, Mockito.times(2)).setGrille(null);
    }

    /**
     * Test de la méthode findItemForTheme.
     */
    @Test
    public void testFindItemForTheme() {
        final Item i1 = new Item();
        final Item i2 = new Item();
        final Item i3 = new Item();
        final String theme = "theme";
        i1.setTheme(theme);
        i2.setTheme("");
        i3.setTheme("autre");
        final Grille grille = new Grille();
        grille.getItems().add(i1);
        grille.getItems().add(i2);
        grille.getItems().add(i3);
        final Essai essai = new Essai();
        essai.setDetailSurcout(new DetailSurcout());
        Mockito.when(this.essaiManager.getBean()).thenReturn(essai);
        this.manager.setBean(grille);
        Assert.assertEquals(1, this.manager.findItemForTheme(theme).size());
    }

    /**
     * Test de la méthode findTheme.
     */
    @Test
    public void testFindThemeNull() {
        final Essai essai = new Essai();
        final DetailSurcout detailSurcout = new DetailSurcout();
        essai.setDetailSurcout(detailSurcout);
        Mockito.when(this.essaiManager.getBean()).thenReturn(essai);
        Assert.assertNull(this.manager.findThemes());
    }

    /**
     * Test de la méthode findTheme.
     */
    @Test
    public void testFindThemeNotNullEmpty() {
        final Grille grille = new Grille();
        final Essai essai = new Essai();
        final DetailSurcout detailSurcout = new DetailSurcout();
        essai.setDetailSurcout(detailSurcout);
        detailSurcout.setGrille(grille);
        Mockito.when(this.essaiManager.getBean()).thenReturn(essai);
        final SortedSet<String> themes = this.manager.findThemes();
        Assert.assertNotNull(themes);
        Assert.assertEquals(0, themes.size());
    }

    /**
     * Test de la méthode findTheme.
     */
    @Test
    public void testFindThemeNotNullNotEmpty() {
        final Grille grille = new Grille();
        final Item i1 = new Item();
        i1.setTheme("Theme 1");
        i1.setCategorie("cat1");
        final Item i2 = new Item();
        i2.setTheme("Theme 1");
        i2.setCategorie("cat2");
        final Item i3 = new Item();
        i3.setTheme("Theme 2");
        i3.setCategorie("cat1");
        grille.getItems().add(i1);
        grille.getItems().add(i2);
        grille.getItems().add(i3);
        final Essai essai = new Essai();
        final DetailSurcout detailSurcout = new DetailSurcout();
        essai.setDetailSurcout(detailSurcout);
        detailSurcout.setGrille(grille);
        Mockito.when(this.essaiManager.getBean()).thenReturn(essai);
        final SortedSet<String> themes = this.manager.findThemes();
        Assert.assertNotNull(themes);
        Assert.assertEquals(2, themes.size());
    }

    /**
     * Test de la méthode getGrillesModeles.
     */
    @Test
    public void testGetGrillesModeles() {
        Mockito.when(this.grilleModeleService.getAll()).thenReturn(new ArrayList<GrilleModele>());
        Assert.assertNotNull(this.manager.getGrillesModeles());
    }

    /**
     * Test dateDebut.
     */
    @Test
    public void testGetSetDateDebut() {
        final Calendar cal = Calendar.getInstance();
        this.manager.setDateDebut(cal);
        Assert.assertEquals(cal, this.manager.getDateDebut());
    }

    /**
     * Test dateFin.
     */
    @Test
    public void testGetSetDateFin() {
        final Calendar cal = Calendar.getInstance();
        this.manager.setDateFin(cal);
        Assert.assertEquals(cal, this.manager.getDateFin());
    }
}
