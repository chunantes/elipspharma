package fr.pharma.eclipse.filler.impl.sigrec.essai;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.ObjetRecherche;
import fr.pharma.eclipse.domain.model.sigrec.GeneralInformationType;
import fr.pharma.eclipse.domain.model.sigrec.ObjetRechercheType;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.DetailRechercheSigrec;
import fr.pharma.eclipse.filler.helper.BooleanFillerHelper;

/**
 * Test du filler : ObjetRechercheFiller.
 
 * @version $Revision$ $Date$
 */
public class ObjetRechercheFillerTest
{
    /**
     * Filler à tester.
     */
    private ObjetRechercheFiller filler;

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
        this.filler = new ObjetRechercheFiller();
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
        essai.getGeneralInformation().setObjetRecherche(new ObjetRechercheType());
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

        Assert.assertNull(essai.getDetailRecherche().getObjetRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillAlicament()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getObjetRecherche().setAlicament("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(true);
        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(ObjetRecherche.ALICAMENT,
                            essai.getDetailRecherche().getObjetRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillDmAutres()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getObjetRecherche().setDmAutres("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             true);

        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(ObjetRecherche.DM_AUTRE,
                            essai.getDetailRecherche().getObjetRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillDiagnosticInVitro()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getObjetRecherche().setDmDiagnosticInvitro("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             false,
                                                                             true);

        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(ObjetRecherche.DM_DIAGNOSTIC_IN_VITRO,
                            essai.getDetailRecherche().getObjetRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillDmImplantableActif()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getObjetRecherche().setDmImplantableActif("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             false,
                                                                             false,
                                                                             true);

        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(ObjetRecherche.DM_IMPLANTABLE_ACTIF,
                            essai.getDetailRecherche().getObjetRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillImagerie()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getObjetRecherche().setImagerie("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             true);
        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(ObjetRecherche.IMAGERIE,
                            essai.getDetailRecherche().getObjetRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillMedicament()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getObjetRecherche().setMedicament("Y");
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

        Assert.assertEquals(ObjetRecherche.MEDICAMENT,
                            essai.getDetailRecherche().getObjetRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillNutrition()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getObjetRecherche().setNutrition("Y");
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

        Assert.assertEquals(ObjetRecherche.NUTRITION,
                            essai.getDetailRecherche().getObjetRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillOther()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getObjetRecherche().setOther("Y");
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

        Assert.assertEquals(ObjetRecherche.AUTRE,
                            essai.getDetailRecherche().getObjetRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillRadio()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getObjetRecherche().setRadiotherapie("Y");
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

        Assert.assertEquals(ObjetRecherche.RADIOTHERAPIE,
                            essai.getDetailRecherche().getObjetRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillTherapieCell()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getObjetRecherche().setTherapieCellulaire("Y");
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

        Assert.assertEquals(ObjetRecherche.THERAPIE_CELLULAIRE,
                            essai.getDetailRecherche().getObjetRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillTherapieChir()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getObjetRecherche().setTherapieChirurgicale("Y");
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

        Assert.assertEquals(ObjetRecherche.THERAPEUTIQUES_CHIR,
                            essai.getDetailRecherche().getObjetRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillTherapieGen()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getObjetRecherche().setTherapieGenetique("Y");
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
                                                                             false,
                                                                             true);

        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(ObjetRecherche.THERAPIE_GENIQUE,
                            essai.getDetailRecherche().getObjetRecherche());
    }
    /**
     * Initialisation du TrialType.
     * @return le trialtype.
     */
    private TrialType initTrial()
    {
        final GeneralInformationType gen = new GeneralInformationType();
        final ObjetRechercheType objet = new ObjetRechercheType();
        gen.setObjetRecherche(objet);
        final TrialType trial = new TrialType();
        trial.setGeneralInformation(gen);
        return trial;
    }
}
