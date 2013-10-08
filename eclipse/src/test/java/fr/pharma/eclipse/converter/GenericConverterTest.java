package fr.pharma.eclipse.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.stockage.PharmacieService;

/**
 * Classe en charge de tester le converter générique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GenericConverterTest {

    /**
     * GenericConverter à tester.
     */
    private GenericConverter<Pharmacie> converter;

    /**
     * Mock du service de pharmacie.
     */
    private PharmacieService mockService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.mockService = Mockito.mock(PharmacieService.class);
        this.converter = new GenericConverter<Pharmacie>(this.mockService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.mockService = null;
        this.converter = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.mockService);
        Assert.assertNotNull(this.converter);
    }

    /**
     * Test de la méthode permettant de récupérer l'objet (bean métier).
     */
    @Test
    public void testGetAsObjectValueNull() {
        final UIComponent component = Mockito.mock(UIComponent.class);
        final FacesContext facesContext = Mockito.mock(FacesContext.class);
        final Object result = this.converter.getAsObject(facesContext, component, null);
        Assert.assertNull(result);
    }

    /**
     * Test de la méthode permettant de récupérer l'objet (bean métier).
     */
    @Test
    public void testGetAsObjectValueEmpty() {
        final UIComponent component = Mockito.mock(UIComponent.class);
        final FacesContext facesContext = Mockito.mock(FacesContext.class);
        final Object result = this.converter.getAsObject(facesContext, component, "");
        Assert.assertNull(result);
    }

    /**
     * Test de la méthode permettant de récupérer l'objet (bean métier).
     */
    @Test
    public void testGetAsObjectValue() {
        final UIComponent component = Mockito.mock(UIComponent.class);
        final FacesContext facesContext = Mockito.mock(FacesContext.class);
        final String value = "1";
        final Pharmacie pharmacieExpected = new Pharmacie();
        pharmacieExpected.setId(1L);
        Mockito.when(this.mockService.get(Long.valueOf(value))).thenReturn(pharmacieExpected);
        final Object result = this.converter.getAsObject(facesContext, component, value);
        Assert.assertEquals(pharmacieExpected, result);
    }

    /**
     * Test de la méthode permettant de récupérer l'objet (bean métier).
     */
    @Test
    public void testGetAsObjectValueException() {
        final UIComponent component = Mockito.mock(UIComponent.class);
        final FacesContext facesContext = Mockito.mock(FacesContext.class);
        final String value = "1";
        final Pharmacie pharmacieExpected = new Pharmacie();
        pharmacieExpected.setId(1L);
        Mockito.when(this.mockService.get(Long.valueOf(value))).thenThrow(new NumberFormatException());
        final Object result = this.converter.getAsObject(facesContext, component, value);
        Assert.assertNull(result);
    }

    /**
     * Test de la méthode permettant de récupérant l'id technique de l'objet
     * (bean métier).
     */
    @Test
    public void testGetAsStringNull() {
        final UIComponent component = Mockito.mock(UIComponent.class);
        final FacesContext facesContext = Mockito.mock(FacesContext.class);
        final String result = this.converter.getAsString(facesContext, component, null);
        Assert.assertNull(result);
    }

    /**
     * Test de la méthode permettant de récupérant l'id technique de l'objet
     * (bean métier).
     */
    @Test
    public void testGetAsStringNotNull() {
        final UIComponent component = Mockito.mock(UIComponent.class);
        final FacesContext facesContext = Mockito.mock(FacesContext.class);
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);
        final String result = this.converter.getAsString(facesContext, component, pharmacie);
        Assert.assertEquals("1", result);
    }

    /**
     * Test de la méthode permettant de récupérant l'id technique de l'objet
     * (bean métier).
     */
    @Test
    public void testGetAsStringException() {
        final UIComponent component = Mockito.mock(UIComponent.class);
        final FacesContext facesContext = Mockito.mock(FacesContext.class);
        final String result = this.converter.getAsString(facesContext, component, "1");
        Assert.assertNull(result);
    }

}
