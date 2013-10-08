package fr.pharma.eclipse.component.produit;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.component.produit.builder.UnitePrescriptionBuilder;
import fr.pharma.eclipse.component.produit.helper.ConditionnementHelper;
import fr.pharma.eclipse.domain.enums.produit.FormeConditionnement;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.enums.produit.UniteGestion;
import fr.pharma.eclipse.domain.enums.produit.VoieAdministration;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Classe en charge de tester le manager de Conditionnement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ConditionnementManagerTest {
    /**
     * ConditionnementManager à tester.
     */
    private ConditionnementManager conditionnementManager;

    /**
     * Mock du service de gestion des produits.
     */
    private GenericService<Conditionnement> mockConditionnementService;

    /**
     * Helper.
     */
    private ConditionnementHelper helper;

    /**
     * Builder.
     */
    private UnitePrescriptionBuilder builder;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.builder = Mockito.mock(UnitePrescriptionBuilder.class);
        this.helper = Mockito.mock(ConditionnementHelper.class);
        this.mockConditionnementService = Mockito.mock(GenericService.class);
        this.conditionnementManager = new ConditionnementManager(this.mockConditionnementService);
        this.conditionnementManager.setConditionnementHelper(this.helper);
        final Map<ModePrescription, UnitePrescriptionBuilder> map = new HashMap<ModePrescription, UnitePrescriptionBuilder>();
        map.put(ModePrescription.CONDITIONNEMENT_PRIMAIRE, this.builder);
        this.conditionnementManager.setBuilders(map);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.mockConditionnementService = null;
        this.helper = null;
        this.builder = null;
        this.conditionnementManager = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInitOk() {
        Assert.assertNotNull(this.builder);
        Assert.assertNotNull(this.helper);
        Assert.assertNotNull(this.conditionnementManager);
        Assert.assertNotNull(this.mockConditionnementService);
    }

    /**
     * Test de la méthode init().
     */
    @Test
    public void testInit() {
        this.conditionnementManager.setBean(new Conditionnement());
        this.conditionnementManager.setResume("te");
        this.conditionnementManager.init();
        Assert.assertNull(this.conditionnementManager.getBean());
        Assert.assertEquals("", this.conditionnementManager.getResume());
    }

    /**
     * Test de la méthode editCOnditionnement.
     */
    @Test
    public void testEditConditionnement() {
        final Conditionnement conditionnement = Mockito.mock(Conditionnement.class);
        Mockito.when(this.helper.buildResume(Matchers.any(Conditionnement.class))).thenReturn("resume");
        this.conditionnementManager.editConditionnement(conditionnement);
        Assert.assertEquals(this.conditionnementManager.getBean(), conditionnement);
        Assert.assertEquals("resume", this.conditionnementManager.getResume());
    }

    /**
     * Test de la méthode handleForme.
     */
    @Test
    public void testHandleForme() {
        final HtmlSelectOneMenu menu = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(menu.getLocalValue()).thenReturn(FormeConditionnement.CONDITIONNEMENT_PRIMAIRE);
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        Mockito.when(event.getSource()).thenReturn(menu);

        Mockito.when(this.helper.buildResume(Matchers.any(Conditionnement.class))).thenReturn("resume");
        this.conditionnementManager.setBean(new Conditionnement());
        this.conditionnementManager.handleForme(event);

        Assert.assertEquals(FormeConditionnement.CONDITIONNEMENT_PRIMAIRE, this.conditionnementManager.getBean().getForme());
    }

    /**
     * Test de la méthode handleForme.
     */
    @Test
    public void testHandleVoieAdministration() {
        final HtmlSelectOneMenu menu = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(menu.getLocalValue()).thenReturn(VoieAdministration.BOLUS);
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        Mockito.when(event.getSource()).thenReturn(menu);

        Mockito.when(this.helper.buildResume(Matchers.any(Conditionnement.class))).thenReturn("resume");
        this.conditionnementManager.setBean(new Conditionnement());
        this.conditionnementManager.handleVoieAdministration(event);

        Assert.assertEquals(VoieAdministration.BOLUS, this.conditionnementManager.getBean().getVoieAdministration());
    }

    /**
     * Test de la méthode handleUniteGestion.
     */
    @Test
    public void testHandleUniteGestion() {
        final HtmlSelectOneMenu menu = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(menu.getLocalValue()).thenReturn(UniteGestion.BOITE);
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);

        Mockito.when(event.getSource()).thenReturn(menu);

        if (menu.getLocalValue() != null) {
            System.out.println("not null");
        } else {
            System.out.println("null");
        }

        Mockito.when(this.helper.buildResume(Matchers.any(Conditionnement.class))).thenReturn("resume");
        this.conditionnementManager.setBean(new Conditionnement());
        this.conditionnementManager.handleUniteGestion(event);

        Assert.assertEquals(UniteGestion.BOITE, this.conditionnementManager.getBean().getUniteGestion());
    }

    /**
     * Test de la méthode handleUniteGestion.
     */
    @Test
    public void testHandleUniteGestionNullMenu() {
        final HtmlSelectOneMenu menu = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(menu.getLocalValue()).thenReturn(null);
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);

        Mockito.when(event.getSource()).thenReturn(menu);

        Mockito.when(this.helper.buildResume(Matchers.any(Conditionnement.class))).thenReturn("resume");
        this.conditionnementManager.setBean(new Conditionnement());
        this.conditionnementManager.handleUniteGestion(event);

        Assert.assertNull(this.conditionnementManager.getBean().getUniteGestion());

    }
    /**
     * Test de la méthode handleUniteGestion.
     */
    @Test
    public void testHandleUniteGestionKit() {
        final HtmlSelectOneMenu menu = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(menu.getLocalValue()).thenReturn(UniteGestion.KIT);
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        Mockito.when(event.getSource()).thenReturn(menu);

        Mockito.when(this.helper.buildResume(Matchers.any(Conditionnement.class))).thenReturn("resume");
        this.conditionnementManager.setBean(new Conditionnement());
        this.conditionnementManager.handleUniteGestion(event);

        Assert.assertEquals(UniteGestion.KIT, this.conditionnementManager.getBean().getUniteGestion());
        Assert.assertEquals(FormeConditionnement.CONDITIONNEMENT_PRIMAIRE, this.conditionnementManager.getBean().getForme());
    }

    /**
     * Test de la méthode buildUnitePrescription.
     */
    @Test
    public void testBuildUnitePrescriptionNull() {
        this.conditionnementManager.setBean(new Conditionnement());
        this.conditionnementManager.buildUnitePrescription();
        Mockito.verify(this.builder, Mockito.never()).build(Matchers.any(Conditionnement.class));
    }

    /**
     * Test de la méthode buildUnitePrescription.
     */
    @Test
    public void testBuildUnitePrescriptionNull2() {
        final Conditionnement conditionnement = new Conditionnement();
        this.conditionnementManager.setBean(conditionnement);
        conditionnement.setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        this.conditionnementManager.buildUnitePrescription();
        Mockito.verify(this.builder, Mockito.never()).build(Matchers.any(Conditionnement.class));
    }

    /**
     * Test de la méthode buildUnitePrescription.
     */
    @Test
    public void testBuildUnitePrescriptionOk() {
        final Conditionnement conditionnement = new Conditionnement();
        this.conditionnementManager.setBean(conditionnement);
        conditionnement.setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        conditionnement.setUniteGestion(UniteGestion.BOITE);
        this.conditionnementManager.buildUnitePrescription();
        Mockito.verify(this.builder, Mockito.times(1)).build(Matchers.any(Conditionnement.class));
    }
}
