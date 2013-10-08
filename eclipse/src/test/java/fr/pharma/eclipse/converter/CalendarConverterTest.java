package fr.pharma.eclipse.converter;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Test du converter CalendarConverter.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class CalendarConverterTest {
    /**
     * Converter testé.
     */
    private CalendarConverter converter;

    /**
     * Contexte mocké.
     */
    private FacesContext mockedContext;

    /**
     * Composant mocké.
     */
    private UIComponent mockedUiComponent;

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void setUp() {
        this.converter = new CalendarConverter();
        this.mockedContext = Mockito.mock(FacesContext.class);
        this.mockedUiComponent = Mockito.mock(UIComponent.class);
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown() {
        this.converter = null;
        this.mockedContext = null;
        this.mockedUiComponent = null;
    }

    /**
     * Test de la méthode getAsObject - mauvaise valeur.
     */
    @Test
    public void testGetAsObjectNull() {
        Assert.assertNull(this.converter.getAsObject(this.mockedContext, this.mockedUiComponent, ""));
        Assert.assertNull(this.converter.getAsObject(this.mockedContext, this.mockedUiComponent, "1/1"));
    }

    /**
     * Test de la méthode getAsObject.
     */
    @Test
    public void testGetAsObject() {
        final String expectedValue = "01/01/2010";
        final Object res = this.converter.getAsObject(this.mockedContext, this.mockedUiComponent, expectedValue);
        Assert.assertTrue(res instanceof Calendar);
        Assert.assertEquals(expectedValue, Utils.formatDate(((Calendar) res).getTime(), EclipseConstants.PATTERN_SIMPLE));
    }

    /**
     * Test de la méthode getAsString - mauvais objet.
     */
    @Test
    public void testGetAsStringNull() {
        Assert.assertNull(this.converter.getAsString(this.mockedContext, this.mockedUiComponent, null));
        Assert.assertNull(this.converter.getAsString(this.mockedContext, this.mockedUiComponent, new Date()));
    }

    /**
     * Test de la méthode getAsString.
     * @throws ParseException Erreur de création des jeux de données de tests.
     */
    @Test
    public void testGetAsString() throws ParseException {
        final String expectedValue = "01/01/2010";
        Assert.assertEquals(expectedValue, this.converter.getAsString(this.mockedContext, this.mockedUiComponent, Utils.parseDate(expectedValue, EclipseConstants.PATTERN_SIMPLE)));

    }

}
