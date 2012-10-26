package fr.pharma.eclipse.filler.impl.sigrec.essai;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.NatureRecherche;
import fr.pharma.eclipse.domain.model.sigrec.GeneralInformationType;
import fr.pharma.eclipse.domain.model.sigrec.TrialScopeType;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.DetailRechercheSigrec;
import fr.pharma.eclipse.filler.helper.BooleanFillerHelper;

/**
 * Test du filler : NatureRechercheFiller.
 
 * @version $Revision$ $Date$
 */
public class NatureRechercheFillerTest
{
    /**
     * Filler à tester.
     */
    private NatureRechercheFiller filler;

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
        this.filler = new NatureRechercheFiller();
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
     * Test de la méthode support().
     */
    @Test
    public void testSupport()
    {
        final TrialType essai = new TrialType();
        Assert.assertFalse(this.filler.support(essai));
        essai.setGeneralInformation(new GeneralInformationType());
        Assert.assertFalse(this.filler.support(essai));
        essai.getGeneralInformation().setTrialScope(new TrialScopeType());
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

        Assert.assertNull(essai.getDetailRecherche().getNatureRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillPharmaco()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTrialScope().setPharmacologie("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(true,
                                                                             false);

        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(NatureRecherche.PHARMACOLOGIE,
                            essai.getDetailRecherche().getNatureRecherche());
    }
    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillTherapeutique()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTrialScope().setTherapeutique("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             true);
        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(NatureRecherche.THERAPEUTIQUE,
                            essai.getDetailRecherche().getNatureRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillPronostique()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTrialScope().setPronostique("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             false,
                                                                             true);
        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(NatureRecherche.PRONOSTIQUE,
                            essai.getDetailRecherche().getNatureRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillDiagnostique()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTrialScope().setDiagnostique("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             false,
                                                                             false,
                                                                             true);
        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(NatureRecherche.DIAGNOSTIQUE,
                            essai.getDetailRecherche().getNatureRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillPhysiologique()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTrialScope().setPhysiologie("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             true);
        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(NatureRecherche.PHYSIOLOGIE,
                            essai.getDetailRecherche().getNatureRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillPhysioPatho()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTrialScope().setPhysiopathologie("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             true);
        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(NatureRecherche.PHYSIOPATHOLOGIE,
                            essai.getDetailRecherche().getNatureRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillPrevention()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTrialScope().setPrevention("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             true);
        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(NatureRecherche.PREVENTION,
                            essai.getDetailRecherche().getNatureRecherche());
    }
    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillDepistage()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTrialScope().setDepistage("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             true);
        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(NatureRecherche.DEPISTAGE,
                            essai.getDetailRecherche().getNatureRecherche());
    }
    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillGenetique()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTrialScope().setGenetique("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             true);
        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(NatureRecherche.GENETIQUE,
                            essai.getDetailRecherche().getNatureRecherche());
    }
    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillEpidemio()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTrialScope().setEpidemiologie("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             true);

        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(NatureRecherche.EPIDEMIOLOGIE,
                            essai.getDetailRecherche().getNatureRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillAutre()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTrialScope().setOther("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             true);
        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(NatureRecherche.AUTRE,
                            essai.getDetailRecherche().getNatureRecherche());
    }

    /**
     * Initialisation du TrialType.
     * @return le trialtype.
     */
    private TrialType initTrial()
    {
        final GeneralInformationType gen = new GeneralInformationType();
        final TrialScopeType nature = new TrialScopeType();

        gen.setTrialScope(nature);
        final TrialType trial = new TrialType();
        trial.setGeneralInformation(gen);
        return trial;
    }
}
