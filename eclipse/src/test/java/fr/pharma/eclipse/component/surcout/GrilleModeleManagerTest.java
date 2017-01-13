package fr.pharma.eclipse.component.surcout;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.component.surcout.validator.CategorieValidator;
import fr.pharma.eclipse.domain.model.surcout.Categorie;
import fr.pharma.eclipse.domain.model.surcout.Grille;
import fr.pharma.eclipse.domain.model.surcout.GrilleModele;
import fr.pharma.eclipse.domain.model.surcout.Theme;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du GrilleModeleManager.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GrilleModeleManagerTest extends AbstractEclipseJUnitTest {

    /**
     * Manager à tester.
     */
    private GrilleModeleManager manager;

    /**
     * Factory de theme.
     */
    private BeanObjectFactory<Theme> themeFactory;

    /**
     * Factory de catégorie.
     */
    private BeanObjectFactory<Categorie> categorieFactory;

    /**
     * Service grille modele.
     */
    private GenericService<GrilleModele> service;
    

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.service = Mockito.mock(GenericService.class);
        this.categorieFactory = Mockito.mock(BeanObjectFactory.class);
        this.themeFactory = Mockito.mock(BeanObjectFactory.class);
        this.manager = new GrilleModeleManager(this.service);
        this.manager.setThemeFactory(this.themeFactory);
        this.manager.setCategorieFactory(this.categorieFactory);
        this.manager.setCategorieValidator(Mockito.mock(CategorieValidator.class));
       
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.themeFactory = null;
        this.categorieFactory = null;
        this.manager = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.themeFactory);
        Assert.assertNotNull(this.categorieFactory);
    }

    /**
     * Test de la méthode removeTheme().
     */
    @Test
    public void testRemoveTheme() {

        final GrilleModele bean = new GrilleModele();
        bean.setNom("nom");
        final Theme theme = new Theme();
        theme.setLibelle("nom");
        bean.getThemes().add(theme);
        this.manager.setBean(bean);

        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final UIComponent component = Mockito.mock(UIComponent.class);
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("theme", theme);

        Mockito.when(event.getComponent()).thenReturn(component);
        Mockito.when(component.getAttributes()).thenReturn(map);
        this.manager.removeTheme(event);
        Assert.assertEquals(0, this.manager.getBean().getThemes().size());
    }

    /**
     * Test de la méthode removeCategorie().
     */
    @Test
    public void testRemoveCategorie() {

        final GrilleModele bean = new GrilleModele();
        bean.setNom("nom");
        final Theme theme = new Theme();
        theme.setLibelle("nom");
        bean.getThemes().add(theme);
        final Categorie categorie = new Categorie();
        categorie.setLibelle("nom");
        theme.getCategories().add(categorie);
        categorie.setTheme(theme);

        this.manager.setBean(bean);
        this.manager.setTheme(theme);

        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final UIComponent component = Mockito.mock(UIComponent.class);
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("categorie", categorie);

        Mockito.when(event.getComponent()).thenReturn(component);
        Mockito.when(component.getAttributes()).thenReturn(map);
        this.manager.removeCategorie(event);
        Assert.assertEquals(0, categorie.getTheme().getCategories().size());
    }

    /**
     * Test de la méthode initTheme().
     */
    @Test
    public void testInitTheme() {
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final UIComponent component = Mockito.mock(UIComponent.class);
        final Map<String, Object> map = new HashMap<String, Object>();
        Mockito.when(event.getComponent()).thenReturn(component);
        Mockito.when(component.getAttributes()).thenReturn(map);
        Mockito.when(this.themeFactory.getInitializedObject()).thenReturn(new Theme());
        this.manager.initTheme(event);
        Mockito.verify(this.themeFactory).getInitializedObject();
        Assert.assertNotNull(this.manager.getTheme());
    }

    /**
     * Test de la méthode initTheme().
     */
    @Test
    public void testInitThemeEdit() {
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final UIComponent component = Mockito.mock(UIComponent.class);
        final Map<String, Object> map = new HashMap<String, Object>();
        Mockito.when(event.getComponent()).thenReturn(component);
        Mockito.when(component.getAttributes()).thenReturn(map);
        map.put("theme", new Theme());
        this.manager.initTheme(event);
        Mockito.verify(this.themeFactory, Mockito.never()).getInitializedObject();
        Assert.assertNotNull(this.manager.getTheme());
    }

    /**
     * Test de la méthode initCategorie().
     */
    @Test
    public void testInitCategorie() {
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final UIComponent component = Mockito.mock(UIComponent.class);
        final Map<String, Object> map = new HashMap<String, Object>();
        Mockito.when(event.getComponent()).thenReturn(component);
        Mockito.when(component.getAttributes()).thenReturn(map);
        Mockito.when(this.categorieFactory.getInitializedObject()).thenReturn(new Categorie());
        this.manager.initCategorie(event);
        Mockito.verify(this.categorieFactory).getInitializedObject();
        Assert.assertNotNull(this.manager.getCategorie());
    }

    /**
     * Test de la méthode initCategorie().
     */
    @Test
    public void testInitCategorieEdit() {
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final UIComponent component = Mockito.mock(UIComponent.class);
        final Map<String, Object> map = new HashMap<String, Object>();
        Mockito.when(event.getComponent()).thenReturn(component);
        Mockito.when(component.getAttributes()).thenReturn(map);
        map.put("categorie", new Categorie());
        this.manager.initCategorie(event);
        Mockito.verify(this.categorieFactory, Mockito.never()).getInitializedObject();
        Assert.assertNotNull(this.manager.getCategorie());
    }

    /**
     * Test de la méthode addTheme.
     */
    @Test
    public void testAddTheme() {
        final Theme theme = new Theme();
        theme.setId(1L);
        theme.setLibelle("theme");
        this.manager.setTheme(theme);

        this.manager.setBean(new GrilleModele());
        this.manager.addTheme();

        Assert.assertEquals(1, this.manager.getBean().getThemes().size());
        Assert.assertNull(this.manager.getTheme());
    }

    /**
     * Test de la méthode addTheme.
     */
    @Test
    public void testAddThemeDejaPresent() {
        final Theme theme = new Theme();
        theme.setId(1L);
        theme.setLibelle("theme");
        this.manager.setTheme(theme);

        this.manager.setBean(new GrilleModele());
        this.manager.getBean().getThemes().add(theme);
        this.manager.addTheme();

        Assert.assertEquals(1, this.manager.getBean().getThemes().size());
        Assert.assertNull(this.manager.getTheme());
    }

    /**
     * Test de la méthode addCategorie.
     */
    @Test
    public void testAddCategorie() {
        final Theme theme = new Theme();
        theme.setId(1L);
        theme.setLibelle("theme");
        this.manager.setTheme(theme);

        this.manager.setBean(new GrilleModele());
        this.manager.getBean().getThemes().add(theme);

        final Categorie categorie = new Categorie();
        categorie.setId(1L);
        categorie.setLibelle("nom");
        this.manager.setCategorie(categorie);
        categorie.setTheme(theme);
        
        Mockito.when(this.manager.getCategorieValidator().validate(this.manager.getCategorie())).thenReturn(true);
        
        this.manager.addCategorie();

        Assert.assertEquals(1, this.manager.getBean().getThemes().first().getCategories().size());
    }

    /**
     * Test de la méthode addTheme.
     */
    @Test
    public void testAddCategorieDejaPresent() {
        final Theme theme = new Theme();
        theme.setId(1L);
        theme.setLibelle("theme");
        this.manager.setTheme(theme);

        this.manager.setBean(new GrilleModele());
        this.manager.getBean().getThemes().add(theme);
        final Categorie categorie = new Categorie();
        categorie.setId(1L);
        categorie.setLibelle("nom");
        this.manager.getTheme().getCategories().add(categorie);
        this.manager.setCategorie(categorie);
        CategorieValidator validator = this.manager.getCategorieValidator();
        Mockito.when(validator.validate(categorie)).thenReturn(true);
        this.manager.addCategorie();

        Assert.assertEquals(1, this.manager.getBean().getThemes().first().getCategories().size());
    }

    /**
     * Test de la méthode processEditable.
     */
    @Test
    public void testProcessEditableTrue() {
        this.manager.setBean(new GrilleModele());
        Mockito.when(this.service.reattach(Matchers.any(GrilleModele.class))).thenReturn(new GrilleModele());
        this.manager.processEditable();
        Assert.assertTrue(this.manager.isEditable());
    }

    /**
     * Test de la méthode processEditable.
     */
    @Test
    public void testProcessEditableTrue2() {
        this.manager.setBean(new GrilleModele());
        final GrilleModele g = new GrilleModele();
        g.setId(1L);
        Mockito.when(this.service.reattach(Matchers.any(GrilleModele.class))).thenReturn(g);
        this.manager.processEditable();
        Assert.assertTrue(this.manager.isEditable());
    }

    /**
     * Test de la méthode processEditable.
     */
    @Test
    public void testProcessEditableFalse() {
        this.manager.setBean(new GrilleModele());
        final GrilleModele g = new GrilleModele();
        g.setId(1L);
        g.getGrilles().add(new Grille());
        Mockito.when(this.service.reattach(Matchers.any(GrilleModele.class))).thenReturn(g);
        this.manager.processEditable();
        Assert.assertFalse(this.manager.isEditable());
    }
}
