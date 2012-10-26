package fr.pharma.eclipse.filler.impl.sigrec.essai;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.sigrec.GeneralInformationType;
import fr.pharma.eclipse.domain.model.sigrec.TrialDesignType;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.filler.helper.BooleanFillerHelper;

/**
 * Test du filler : ReelFiller.
 
 * @version $Revision$ $Date$
 */
public class ReelFillerTest
{
    /**
     * Filler à tester.
     */
    private ReelFiller filler;

    /**
     * Helper mocké.
     */
    private BooleanFillerHelper helper;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp()
    {
        this.helper = Mockito.mock(BooleanFillerHelper.class);
        this.filler = new ReelFiller();
        this.filler.setHelper(this.helper);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown()
    {
        this.helper = null;
        this.filler = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.filler);
    }
    /**
     * Test de la méthode fill().
     */
    @Test
    public void testFillOk()
    {
        final TrialType source = this.initTrial();
        final EssaiSigrec destination = this.initEssai();

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(true);
        this.filler.fill(source,
                         destination);

        Assert.assertEquals(new Integer(10),
                            destination.getNbCentres());
        Assert.assertTrue(destination.getMulticentrique().booleanValue());
    }

    /**
     * Test de la méthode fill().
     */
    @Test
    public void testFillFalse()
    {
        final TrialType source = this.initTrial();
        final EssaiSigrec destination = this.initEssai();
        source.getGeneralInformation().getTrialDesign().setNumberOfSites("df");
        source.getGeneralInformation().getTrialDesign().setMultipleSite("N");

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false);

        this.filler.fill(source,
                         destination);

        Assert.assertEquals(null,
                            destination.getNbCentres());
        Assert.assertFalse(destination.getMulticentrique().booleanValue());
    }

    /**
     * Test de la méthode fill().
     */
    @Test
    public void testFillNull()
    {
        final TrialType source = this.initTrial();
        final EssaiSigrec destination = this.initEssai();
        source.getGeneralInformation().getTrialDesign().setNumberOfSites("df");
        source.getGeneralInformation().getTrialDesign().setMultipleSite("sdf");

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false);

        this.filler.fill(source,
                         destination);

        Assert.assertNull(destination.getNbCentres());
        Assert.assertFalse(destination.getMulticentrique());
    }
    /**
     * Test de la méthode support().
     */
    @Test
    public void testSupport()
    {
        final TrialType essai = new TrialType();
        Assert.assertFalse(this.filler.support(essai));
        essai.setGeneralInformation(new GeneralInformationType());
        Assert.assertFalse(this.filler.support(essai));
        essai.getGeneralInformation().setTrialDesign(new TrialDesignType());
        Assert.assertTrue(this.filler.support(essai));
    }
    /**
     * Initialisation du TrialType.
     * @return le trialtype.
     */
    private TrialType initTrial()
    {
        final TrialType trial = new TrialType();
        trial.setGeneralInformation(new GeneralInformationType());
        final TrialDesignType design = new TrialDesignType();
        design.setMultipleSite("Y");
        design.setNumberOfSites("10");
        trial.getGeneralInformation().setTrialDesign(design);
        return trial;

    }
    private EssaiSigrec initEssai()
    {
        final EssaiSigrec essai = new EssaiSigrec();
        return essai;
    }

}
