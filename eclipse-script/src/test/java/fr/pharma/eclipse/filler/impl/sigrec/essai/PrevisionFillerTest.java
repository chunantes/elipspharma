package fr.pharma.eclipse.filler.impl.sigrec.essai;

import java.util.Calendar;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.sigrec.GeneralInformationType;
import fr.pharma.eclipse.domain.model.sigrec.OptionalType;
import fr.pharma.eclipse.domain.model.sigrec.TrialDesignType;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.PrevisionSigrec;

/**
 * Test du filler : PrevisionFiller.
 
 * @version $Revision$ $Date$
 */
public class PrevisionFillerTest
{
    /**
     * Filler à tester.
     */
    private PrevisionFiller filler;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp()
    {
        this.filler = new PrevisionFiller();
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown()
    {
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

    @Test
    public void testSupport()
    {
        final TrialType essai = new TrialType();
        Assert.assertFalse(this.filler.support(essai));
        essai.setGeneralInformation(new GeneralInformationType());
        Assert.assertTrue(this.filler.support(essai));
    }

    /**
     * Test de la méthode fill().
     */
    @Test
    public void testFill()
    {
        final TrialType source = this.initTrial();
        final EssaiSigrec destination = this.initEssai();

        this.filler.fill(source,
                         destination);

        Assert.assertEquals(Integer.valueOf(3),
                            destination.getPrevision().getNbCentres());
        Assert.assertEquals(Integer.valueOf(5),
                            destination.getPrevision().getDureeTotale());
        Assert.assertEquals(2008,
                            destination.getPrevision().getDateDebut().get(Calendar.YEAR));
        Assert.assertEquals(9,
                            destination.getPrevision().getDateDebut().get(Calendar.MONTH));
        Assert.assertEquals(10,
                            destination.getPrevision().getDateDebut().get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals(2008,
                            destination.getPrevision().getDateFin().get(Calendar.YEAR));
        Assert.assertEquals(9,
                            destination.getPrevision().getDateFin().get(Calendar.MONTH));
        Assert.assertEquals(10,
                            destination.getPrevision().getDateFin().get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Test de la méthode fill().
     */
    @Test
    public void testFillKoDateDebut()
    {
        final TrialType source = this.initTrial();
        source.getGeneralInformation().setPlannedStartDate(null);
        final EssaiSigrec destination = this.initEssai();
        final OptionalType option = new OptionalType();
        option.setValue("");
        source.getGeneralInformation().setPlannedStartDate(option);

        this.filler.fill(source,
                         destination);

        Assert.assertEquals(Integer.valueOf(3),
                            destination.getPrevision().getNbCentres());
        Assert.assertEquals(Integer.valueOf(5),
                            destination.getPrevision().getDureeTotale());
        Assert.assertNull(destination.getPrevision().getDateDebut());
        Assert.assertEquals(2008,
                            destination.getPrevision().getDateFin().get(Calendar.YEAR));
        Assert.assertEquals(9,
                            destination.getPrevision().getDateFin().get(Calendar.MONTH));
        Assert.assertEquals(10,
                            destination.getPrevision().getDateFin().get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Test de la méthode fill().
     */
    @Test
    public void testFillKoDateFin()
    {
        final TrialType source = this.initTrial();
        final EssaiSigrec destination = this.initEssai();
        final OptionalType option = new OptionalType();
        option.setValue("");
        source.getGeneralInformation().setPlannedEndDate(option);
        this.filler.fill(source,
                         destination);

        Assert.assertEquals(Integer.valueOf(3),
                            destination.getPrevision().getNbCentres());
        Assert.assertEquals(Integer.valueOf(5),
                            destination.getPrevision().getDureeTotale());
        Assert.assertNull(destination.getPrevision().getDateFin());
        Assert.assertEquals(2008,
                            destination.getPrevision().getDateDebut().get(Calendar.YEAR));
        Assert.assertEquals(9,
                            destination.getPrevision().getDateDebut().get(Calendar.MONTH));
        Assert.assertEquals(10,
                            destination.getPrevision().getDateDebut().get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Test de la méthode fill().
     */
    @Test
    public void testFillKoNbCentres()
    {
        final TrialType source = this.initTrial();
        final EssaiSigrec destination = this.initEssai();
        source.getGeneralInformation().getTrialDesign().setNumberOfSites("dfd");
        this.filler.fill(source,
                         destination);

        Assert.assertNull(destination.getPrevision().getNbCentres());
        Assert.assertEquals(Integer.valueOf(5),
                            destination.getPrevision().getDureeTotale());
        Assert.assertEquals(2008,
                            destination.getPrevision().getDateDebut().get(Calendar.YEAR));
        Assert.assertEquals(9,
                            destination.getPrevision().getDateDebut().get(Calendar.MONTH));
        Assert.assertEquals(10,
                            destination.getPrevision().getDateDebut().get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals(2008,
                            destination.getPrevision().getDateFin().get(Calendar.YEAR));
        Assert.assertEquals(9,
                            destination.getPrevision().getDateFin().get(Calendar.MONTH));
        Assert.assertEquals(10,
                            destination.getPrevision().getDateFin().get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Test de la méthode fill().
     */
    @Test
    public void testFillKoDuree()
    {
        final TrialType source = this.initTrial();
        final EssaiSigrec destination = this.initEssai();
        source.getGeneralInformation().getTotalTrialDurationMsMonths().setValue("dfd");
        this.filler.fill(source,
                         destination);

        Assert.assertNull(destination.getPrevision().getDureeTotale());
        Assert.assertEquals(Integer.valueOf(3),
                            destination.getPrevision().getNbCentres());
        Assert.assertEquals(2008,
                            destination.getPrevision().getDateDebut().get(Calendar.YEAR));
        Assert.assertEquals(9,
                            destination.getPrevision().getDateDebut().get(Calendar.MONTH));
        Assert.assertEquals(10,
                            destination.getPrevision().getDateDebut().get(Calendar.DAY_OF_MONTH));
        Assert.assertEquals(2008,
                            destination.getPrevision().getDateFin().get(Calendar.YEAR));
        Assert.assertEquals(9,
                            destination.getPrevision().getDateFin().get(Calendar.MONTH));
        Assert.assertEquals(10,
                            destination.getPrevision().getDateFin().get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Initialisation du TrialType.
     * @return le trialtype.
     */
    private TrialType initTrial()
    {
        final GeneralInformationType gen = new GeneralInformationType();
        gen.setTrialDesign(new TrialDesignType());
        gen.getTrialDesign().setNumberOfSites("3");
        final OptionalType date = new OptionalType();
        date.setValue("2008-10-10");
        gen.setPlannedStartDate(date);
        gen.setPlannedEndDate(date);
        final OptionalType duree = new OptionalType();
        duree.setValue("5");
        gen.setTotalTrialDurationMsMonths(duree);
        final TrialType trial = new TrialType();
        trial.setGeneralInformation(gen);
        return trial;
    }

    private EssaiSigrec initEssai()
    {
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setPrevision(new PrevisionSigrec());
        return essai;
    }

}
