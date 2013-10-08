package fr.pharma.eclipse.json;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.primefaces.json.JSONArray;

import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.essai.detail.design.DetailDesign;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe DesignConverter.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DesignConverterTest extends AbstractEclipseJUnitTest {

    /**
     * Converter à tester.
     */
    private DesignConverter converter;

    /**
     * COnverter de bras.
     */
    private BrasToJSONConverter brasConverter;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.brasConverter = Mockito.mock(BrasToJSONConverter.class);
        this.converter = new DesignConverter();
        this.converter.setBrasConverter(this.brasConverter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.brasConverter = null;
        this.converter = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.brasConverter);
        Assert.assertNotNull(this.converter);
    }

    /**
     * Test de la méthode convert().
     */
    @Test
    public void testConvertNothing() {
        final DetailDesign detailDesign = new DetailDesign();
        final Bras bras1 = new Bras();
        detailDesign.getBras().add(bras1);
        final Calendar cal = Calendar.getInstance();
        Mockito.when(this.brasConverter.support(bras1)).thenReturn(false);
        Mockito.when(this.brasConverter.convert(bras1, cal)).thenReturn(new JSONArray());
        final JSONArray array = this.converter.convert(detailDesign, cal);
        Mockito.verify(this.brasConverter, Mockito.never()).convert(Matchers.any(Bras.class), Matchers.any(Calendar.class));
        Assert.assertEquals(0, array.length());

    }

    /**
     * Test de la méthode convert().
     */
    @Test
    public void testConvert() {
        final DetailDesign detailDesign = new DetailDesign();
        final Bras bras1 = new Bras();
        detailDesign.getBras().add(bras1);
        final Calendar cal = Calendar.getInstance();
        Mockito.when(this.brasConverter.support(Matchers.any(Bras.class))).thenReturn(true);
        Mockito.when(this.brasConverter.convert(Matchers.any(Bras.class), Matchers.any(Calendar.class))).thenReturn(new JSONArray());
        final JSONArray array = this.converter.convert(detailDesign, cal);
        Mockito.verify(this.brasConverter).convert(Matchers.any(Bras.class), Matchers.any(Calendar.class));

    }

}
