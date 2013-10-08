package fr.pharma.eclipse.json;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe BrasToJSONConverter.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BrasToJSONConverterTest extends AbstractEclipseJUnitTest {

    /**
     * Converter à tester.
     */
    private BrasToJSONConverter converter;

    /**
     * COnverter de sequence.
     */
    private SequenceToJSONConverter sequenceConverter;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.sequenceConverter = Mockito.mock(SequenceToJSONConverter.class);
        this.converter = new BrasToJSONConverter();
        this.converter.setSequenceConverter(this.sequenceConverter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.sequenceConverter = null;
        this.converter = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.sequenceConverter);
        Assert.assertNotNull(this.converter);
    }

    /**
     * Test de la méthode convert().
     */
    @Test
    public void testConvertNothing() {
        final Bras bras = new Bras();
        final Sequence seq1 = new Sequence();
        bras.getSequences().add(seq1);
        final Calendar cal = Calendar.getInstance();
        Mockito.when(this.sequenceConverter.support(Matchers.any(Sequence.class))).thenReturn(false);
        Mockito.when(this.sequenceConverter.convert(Matchers.any(Sequence.class), Matchers.any(Calendar.class))).thenReturn(new JSONObject());
        final JSONArray array = this.converter.convert(bras, cal);
        Mockito.verify(this.sequenceConverter, Mockito.never()).convert(Matchers.any(Sequence.class), Matchers.any(Calendar.class));
        Assert.assertEquals(1, array.length());

    }

    /**
     * Test de la méthode convert().
     */
    @Test
    public void testConvert() {
        final Bras bras = new Bras();
        bras.setNom("nom");
        bras.setId(2L);
        final Sequence seq1 = new Sequence();
        bras.getSequences().add(seq1);
        final Calendar cal = Calendar.getInstance();
        Mockito.when(this.sequenceConverter.support(Matchers.any(Sequence.class))).thenReturn(true);
        Mockito.when(this.sequenceConverter.convert(Matchers.any(Sequence.class), Matchers.any(Calendar.class))).thenReturn(new JSONObject());
        final JSONArray array = this.converter.convert(bras, cal);
        Mockito.verify(this.sequenceConverter).convert(Matchers.any(Sequence.class), Matchers.any(Calendar.class));
        Assert.assertEquals(1, array.length());
        try {

            for (int i = 0; i < array.length(); i++) {
                Assert.assertNotNull(((JSONObject) array.get(i)).get("niveau"));
                Assert.assertNotNull(((JSONObject) array.get(i)).get("series"));
                Assert.assertNotNull(((JSONObject) array.get(i)).get("itemName"));
                Assert.assertNotNull(((JSONObject) array.get(i)).get("id"));
            }

        } catch (final JSONException e) {
            Assert.fail("Aucune exception attendue");
        }
    }

    /**
     * Test de la méthode convert() avec sous bras.
     */
    @Test
    public void testConvertSousBras() {
        final Bras bras = new Bras();
        bras.setNom("nom");
        bras.setId(2L);
        final Sequence seq1 = new Sequence();
        seq1.setDebut(new TempsPrescription());
        seq1.setFin(new TempsPrescription());
        bras.getSequences().add(seq1);

        final Bras ssBras = new Bras();
        ssBras.getSequences().add(seq1);
        ssBras.setParent(bras);
        ssBras.setNom("nom");
        ssBras.setId(2L);
        final Calendar cal = Calendar.getInstance();
        bras.getSousBras().add(ssBras);

        Mockito.when(this.sequenceConverter.support(Matchers.any(Sequence.class))).thenReturn(true);
        Mockito.when(this.sequenceConverter.convert(Matchers.any(Sequence.class), Matchers.any(Calendar.class))).thenReturn(new JSONObject());
        final JSONArray array = this.converter.convert(bras, cal);
        Mockito.verify(this.sequenceConverter, Mockito.times(2)).convert(Matchers.any(Sequence.class), Matchers.any(Calendar.class));
        Assert.assertEquals(2, array.length());
        try {

            for (int i = 0; i < array.length(); i++) {
                Assert.assertNotNull(((JSONObject) array.get(i)).get("niveau"));
                Assert.assertNotNull(((JSONObject) array.get(i)).get("series"));
                Assert.assertNotNull(((JSONObject) array.get(i)).get("itemName"));
                Assert.assertNotNull(((JSONObject) array.get(i)).get("id"));
            }

        } catch (final JSONException e) {
            Assert.fail("Aucune exception attendue");
        }
    }
}
