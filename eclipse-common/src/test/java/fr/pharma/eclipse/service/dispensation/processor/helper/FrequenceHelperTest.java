package fr.pharma.eclipse.service.dispensation.processor.helper;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.design.TypeRegularite;
import fr.pharma.eclipse.domain.enums.design.UniteTemps;
import fr.pharma.eclipse.domain.model.design.embedded.Frequence;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe FrequenceHelper.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class FrequenceHelperTest extends AbstractEclipseJUnitTest {
    /**
     * Helper à tester.
     */
    private FrequenceHelper helper;

    /**
     * Converter en jours mocké.
     */
    private UniteTempsConverter joursConverter;

    /**
     * Converter en semaine mocké.
     */
    private UniteTempsConverter semaineConverter;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.joursConverter = Mockito.mock(UniteTempsConverter.class);
        this.semaineConverter = Mockito.mock(UniteTempsConverter.class);
        this.helper = new FrequenceHelper();
        final Map<UniteTemps, UniteTempsConverter> conversion = new HashMap<UniteTemps, UniteTempsConverter>();
        conversion.put(UniteTemps.JOUR, this.joursConverter);
        conversion.put(UniteTemps.SEMAINE, this.semaineConverter);
        this.helper.setConversionStrategy(conversion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.helper = null;
        this.joursConverter = null;
        this.semaineConverter = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.helper);
        Assert.assertNotNull(this.joursConverter);
        Assert.assertNotNull(this.semaineConverter);
    }

    /**
     * Test de la méthode convertToInt.
     */
    @Test
    public void testConvertToIntParOk() {
        final TempsPrescription temps = new TempsPrescription();
        temps.setNb(5);
        temps.setUnite(UniteTemps.SEMAINE);
        final Frequence frequence = new Frequence();
        frequence.setNbFrequence(5);
        frequence.setUniteFrequence(UniteTemps.JOUR);
        frequence.setTypeRegularite(TypeRegularite.PAR);
        Mockito.when(this.joursConverter.support(UniteTemps.SEMAINE)).thenReturn(true);
        Mockito.when(this.joursConverter.convert(5, UniteTemps.SEMAINE)).thenReturn(35);
        Assert.assertTrue(175 == this.helper.convertToInt(temps, frequence));
        Mockito.verify(this.joursConverter).support(UniteTemps.SEMAINE);
        Mockito.verify(this.joursConverter).convert(5, UniteTemps.SEMAINE);
    }

    /**
     * Test de la méthode convertToInt.
     */
    @Test
    public void testConvertToIntParKo() {
        final TempsPrescription temps = new TempsPrescription();
        temps.setNb(5);
        temps.setUnite(UniteTemps.SEMAINE);
        final Frequence frequence = new Frequence();
        frequence.setNbFrequence(5);
        frequence.setUniteFrequence(UniteTemps.JOUR);
        frequence.setTypeRegularite(TypeRegularite.PAR);
        Mockito.when(this.joursConverter.support(UniteTemps.SEMAINE)).thenReturn(false);
        Mockito.when(this.joursConverter.convert(5, UniteTemps.SEMAINE)).thenReturn(35);
        Assert.assertNull(this.helper.convertToInt(temps, frequence));
        Mockito.verify(this.joursConverter).support(UniteTemps.SEMAINE);
        Mockito.verify(this.joursConverter, Mockito.never()).convert(5, UniteTemps.SEMAINE);
    }

    /**
     * Test de la méthode convertToInt.
     */
    @Test
    public void testConvertToIntTousLesKo() {
        final TempsPrescription temps = new TempsPrescription();
        temps.setNb(5);
        temps.setUnite(UniteTemps.SEMAINE);
        final Frequence frequence = new Frequence();
        frequence.setNbFrequence(5);
        frequence.setUniteFrequence(UniteTemps.JOUR);
        frequence.setTypeRegularite(TypeRegularite.TOUS_LES);
        frequence.setNbUniteTempsFrequence(5);
        Mockito.when(this.joursConverter.support(UniteTemps.SEMAINE)).thenReturn(false);
        Mockito.when(this.joursConverter.convert(5, UniteTemps.SEMAINE)).thenReturn(35);
        Assert.assertNull(this.helper.convertToInt(temps, frequence));
        Mockito.verify(this.joursConverter).support(UniteTemps.SEMAINE);
        Mockito.verify(this.joursConverter, Mockito.never()).convert(5, UniteTemps.SEMAINE);
    }

    /**
     * Test de la méthode convertToInt.
     */
    @Test
    public void testConvertToIntTousLesKo2() {
        final TempsPrescription temps = new TempsPrescription();
        temps.setNb(5);
        temps.setUnite(UniteTemps.SEMAINE);
        final Frequence frequence = new Frequence();
        frequence.setNbFrequence(5);
        frequence.setUniteFrequence(UniteTemps.JOUR);
        frequence.setTypeRegularite(TypeRegularite.TOUS_LES);
        Mockito.when(this.joursConverter.support(UniteTemps.SEMAINE)).thenReturn(false);
        Mockito.when(this.joursConverter.convert(5, UniteTemps.SEMAINE)).thenReturn(35);
        Assert.assertNull(this.helper.convertToInt(temps, frequence));
        Mockito.verify(this.joursConverter, Mockito.never()).support(UniteTemps.SEMAINE);
        Mockito.verify(this.joursConverter, Mockito.never()).convert(5, UniteTemps.SEMAINE);
    }

    /**
     * Test de la méthode convertToInt.
     */
    @Test
    public void testConvertToIntTousLesOk() {
        final TempsPrescription temps = new TempsPrescription();
        temps.setNb(5);
        temps.setUnite(UniteTemps.SEMAINE);
        final Frequence frequence = new Frequence();
        frequence.setNbFrequence(5);
        frequence.setTypeRegularite(TypeRegularite.TOUS_LES);
        frequence.setNbUniteTempsFrequence(2);
        frequence.setUniteFrequence(UniteTemps.JOUR);
        Mockito.when(this.joursConverter.support(UniteTemps.SEMAINE)).thenReturn(true);
        Mockito.when(this.joursConverter.convert(5, UniteTemps.SEMAINE)).thenReturn(35);
        Assert.assertTrue(85 == this.helper.convertToInt(temps, frequence));
        Mockito.verify(this.joursConverter).support(UniteTemps.SEMAINE);
        Mockito.verify(this.joursConverter).convert(5, UniteTemps.SEMAINE);
    }
}
