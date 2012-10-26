package fr.pharma.eclipse.filler.impl.sigrec.essai;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.TypeRecherche;
import fr.pharma.eclipse.domain.model.sigrec.GeneralInformationType;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.TypeRechercheType;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.DetailRechercheSigrec;
import fr.pharma.eclipse.filler.helper.BooleanFillerHelper;

/**
 * Test du filler : TypeRechercheFiller.
 
 * @version $Revision$ $Date$
 */
public class TypeRechercheFillerTest
{
    /**
     * Filler à tester.
     */
    private TypeRechercheFiller filler;

    /**
     * Helper.
     */
    private BooleanFillerHelper helper;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp()
    {
        this.helper = Mockito.mock(BooleanFillerHelper.class);
        this.filler = new TypeRechercheFiller();
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
        essai.getGeneralInformation().setTypeRecherche(new TypeRechercheType());
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

        Assert.assertNull(essai.getDetailRecherche().getTypeRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillRechercheBio()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTypeRecherche().setRechercheBiomedicale("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(true);

        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(TypeRecherche.RECHERCHE_BIOMEDICALE,
                            essai.getDetailRecherche().getTypeRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillSoinsCourant()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTypeRecherche().setSoinsCourants("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             true);

        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(TypeRecherche.SOINS_COURANTS,
                            essai.getDetailRecherche().getTypeRecherche());
    }
    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillObservationel()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTypeRecherche().setObservationnelle("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             false,
                                                                             true);

        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(TypeRecherche.OBSERVATIONNELLE,
                            essai.getDetailRecherche().getTypeRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillSurDonnes()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTypeRecherche().setSurDonnees("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             false,
                                                                             false,
                                                                             true);

        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(TypeRecherche.SUR_DONNEES,
                            essai.getDetailRecherche().getTypeRecherche());
    }

    /**
     * Test dela méthode fill().
     */
    @Test
    public void testFillCollectionsBio()
    {
        final TrialType trial = this.initTrial();
        trial.getGeneralInformation().getTypeRecherche().setCollectionsBiologiques("Y");
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setDetailRecherche(new DetailRechercheSigrec());

        Mockito.when(this.helper.checkTrue(Matchers.anyString())).thenReturn(false,
                                                                             false,
                                                                             false,
                                                                             false,
                                                                             true);

        this.filler.fill(trial,
                         essai);

        Assert.assertEquals(TypeRecherche.COLLECTIONS_BIOLOGIQUES,
                            essai.getDetailRecherche().getTypeRecherche());
    }

    /**
     * Initialisation du TrialType.
     * @return le trialtype.
     */
    private TrialType initTrial()
    {
        final GeneralInformationType gen = new GeneralInformationType();
        final TypeRechercheType type = new TypeRechercheType();
        gen.setTypeRecherche(type);
        final TrialType trial = new TrialType();
        trial.setGeneralInformation(gen);
        return trial;
    }

}
