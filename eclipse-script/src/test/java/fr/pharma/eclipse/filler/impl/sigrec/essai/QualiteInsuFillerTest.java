package fr.pharma.eclipse.filler.impl.sigrec.essai;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.QualiteInsu;
import fr.pharma.eclipse.domain.model.sigrec.GeneralInformationType;
import fr.pharma.eclipse.domain.model.sigrec.TrialDesignType;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.DetailRechercheSigrec;
import fr.pharma.eclipse.filler.helper.BooleanFillerHelper;

/**
 * Test du filler : QualiteInsuFiller.
 
 * @version $Revision$ $Date$
 */
public class QualiteInsuFillerTest
{
    /**
     * Filler à tester.
     */
    private QualiteInsuFiller filler;

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
        this.filler = new QualiteInsuFiller();
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
     * Test dela méthode fill().
     */
    @Test
    public void testFillNull()
    {
        final TrialType trial = this.initTrial();

        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false);

        this.filler.fill(trial,
                         essai);

        Assert.assertNull(essai.getDetailRecherche().getQualiteInsu());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillOpen()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTrialDesign().setOpen("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(true);

        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(QualiteInsu.ESSAI_OUVERT,
                            essai.getDetailRecherche().getQualiteInsu());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillSingleBlind()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTrialDesign().setSingleBlind("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             true);

        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(QualiteInsu.ESSAI_SIMPLE_AVEUGLE,
                            essai.getDetailRecherche().getQualiteInsu());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillDoubleBlind()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTrialDesign().setDoubleBlind("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             false,
                                                                             true);

        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(QualiteInsu.ESSAI_DOUBLE_AVEUGLE,
                            essai.getDetailRecherche().getQualiteInsu());
    }
    /**
     * Initialisation du TrialType.
     * @return le trialtype.
     */
    private TrialType initTrial()
    {
        final TrialType trial = new TrialType();
        final GeneralInformationType gen = new GeneralInformationType();
        final TrialDesignType type = new TrialDesignType();
        gen.setTrialDesign(type);
        trial.setGeneralInformation(gen);
        return trial;
    }
}
