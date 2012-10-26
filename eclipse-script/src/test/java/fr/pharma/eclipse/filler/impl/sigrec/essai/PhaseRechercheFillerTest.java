package fr.pharma.eclipse.filler.impl.sigrec.essai;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.PhaseRecherche;
import fr.pharma.eclipse.domain.model.sigrec.GeneralInformationType;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.TrialTypeAndPhaseType;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.DetailRechercheSigrec;
import fr.pharma.eclipse.filler.helper.BooleanFillerHelper;

/**
 * Test du filler : PhaseRechercheFiller.
 
 * @version $Revision$ $Date$
 */
public class PhaseRechercheFillerTest
{
    /**
     * Filler à tester.
     */
    private PhaseRechercheFiller filler;

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
        this.filler = new PhaseRechercheFiller();
        this.filler.setHelper(this.helper);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown()
    {
        this.filler = null;
    }

    @Test
    public void testSupport()
    {
        final TrialType essai = new TrialType();
        Assert.assertFalse(this.filler.support(essai));
        essai.setGeneralInformation(new GeneralInformationType());
        Assert.assertFalse(this.filler.support(essai));
        essai.getGeneralInformation().setTrialTypeAndPhase(new TrialTypeAndPhaseType());
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

        Assert.assertNull(essai.getDetailRecherche().getPhaseRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillPhaseUn()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTrialTypeAndPhase().setPhaseOne("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(true);

        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(PhaseRecherche.I,
                            essai.getDetailRecherche().getPhaseRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillPhaseTwoA()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTrialTypeAndPhase().setPhaseTwoA("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             true);

        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(PhaseRecherche.IIa,
                            essai.getDetailRecherche().getPhaseRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillPhaseTwoB()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTrialTypeAndPhase().setPhaseTwoB("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             false,
                                                                             true);

        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(PhaseRecherche.IIb,
                            essai.getDetailRecherche().getPhaseRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillPhaseThree()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTrialTypeAndPhase().setPhaseThree("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             false,
                                                                             false,
                                                                             true);

        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(PhaseRecherche.III,
                            essai.getDetailRecherche().getPhaseRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillPhaseFour()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTrialTypeAndPhase().setPhaseFour("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             true);

        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(PhaseRecherche.IV,
                            essai.getDetailRecherche().getPhaseRecherche());
    }

    /**
     * Initialisation du TrialType.
     * @return le trialtype.
     */
    private TrialType initTrial()
    {
        final GeneralInformationType gen = new GeneralInformationType();
        final TrialTypeAndPhaseType type = new TrialTypeAndPhaseType();
        gen.setTrialTypeAndPhase(type);
        final TrialType trial = new TrialType();
        trial.setGeneralInformation(gen);
        return trial;
    }

}
