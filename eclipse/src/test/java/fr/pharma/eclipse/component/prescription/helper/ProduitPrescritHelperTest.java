package fr.pharma.eclipse.component.prescription.helper;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.design.TypeRegularite;
import fr.pharma.eclipse.domain.enums.design.UniteTemps;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.enums.produit.UniteDosage;
import fr.pharma.eclipse.domain.model.design.embedded.Frequence;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe ProduitPrescritHelper.
 
 * @version $Revision$ $Date$
 */
public class ProduitPrescritHelperTest
    extends AbstractEclipseJUnitTest
{

    /**
     * Helper à tester.
     */
    private ProduitPrescritHelper helper;

    @Override
    public void setUp()
    {
        this.helper = new ProduitPrescritHelper();
    }

    @Override
    public void tearDown()
    {
        this.helper = null;
    }

    @Override
    public void testInit()
    {
        Assert.assertNotNull(this.helper);
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.component.prescription.helper.ProduitPrescritHelper#buildResume(fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit)}
     * .
     */
    @Test
    public void testBuildResumePrimaire()
    {
        final ProduitPrescrit prescription = new ProduitPrescrit();
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        prescription.setConditionnement(conditionnement);
        final TempsPrescription temps = new TempsPrescription();
        temps.setNb(1);
        temps.setUnite(UniteTemps.JOUR);
        conditionnement.setUnitePrescription("mg");
        prescription.setDebut(temps);
        prescription.setNbUniteDosage(new BigDecimal(5));
        prescription.setDuree(temps);
        final Frequence frequence = new Frequence();
        frequence.setNbFrequence(1);
        frequence.setNbUniteTempsFrequence(5);
        frequence.setTypeRegularite(TypeRegularite.TOUS_LES);
        frequence.setUniteFrequence(UniteTemps.HEURE);
        prescription.setFrequence(frequence);

        Assert
                .assertEquals("Prescription : 5 mg, 1 fois tous les 5 heure(s) à partir de j1 pendant 1 jour(s)",
                              this.helper.buildResume(prescription));
    }
    /**
     * Test method for
     * {@link fr.pharma.eclipse.component.prescription.helper.ProduitPrescritHelper#buildResume(fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit)}
     * .
     */
    @Test
    public void testBuildResumeDose()
    {
        final ProduitPrescrit prescription = new ProduitPrescrit();
        prescription.setDosage(new BigDecimal(5));
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setModePrescription(ModePrescription.DOSE);
        conditionnement.setUnitePrescription("mg");
        conditionnement.setUniteDosage(UniteDosage.COMPRIME);
        prescription.setNbUniteDosage(new BigDecimal(2));
        prescription.setConditionnement(conditionnement);
        final TempsPrescription temps = new TempsPrescription();
        temps.setNb(1);
        temps.setUnite(UniteTemps.JOUR);
        prescription.setDebut(temps);
        prescription.setDuree(temps);
        final Frequence frequence = new Frequence();
        frequence.setNbFrequence(1);
        frequence.setNbUniteTempsFrequence(5);
        frequence.setTypeRegularite(TypeRegularite.TOUS_LES);
        frequence.setUniteFrequence(UniteTemps.HEURE);
        prescription.setFrequence(frequence);

        Assert
                .assertEquals("Prescription : 2 fois 5 mg, 1 fois tous les 5 heure(s) à partir de j1 pendant 1 jour(s)",
                              this.helper.buildResume(prescription));
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.component.prescription.helper.ProduitPrescritHelper#buildResume(fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit)}
     * .
     */
    @Test
    public void testBuildResumeGalenique()
    {
        final ProduitPrescrit prescription = new ProduitPrescrit();
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setModePrescription(ModePrescription.FORME_GALENIQUE);
        prescription.setConditionnement(conditionnement);
        final TempsPrescription temps = new TempsPrescription();
        temps.setNb(1);
        temps.setUnite(UniteTemps.JOUR);
        prescription.setDebut(temps);
        prescription.setNbUniteDosage(new BigDecimal(5));
        prescription.setDuree(temps);
        final Frequence frequence = new Frequence();
        frequence.setNbFrequence(1);
        frequence.setNbUniteTempsFrequence(5);
        frequence.setTypeRegularite(TypeRegularite.TOUS_LES);
        frequence.setUniteFrequence(UniteTemps.HEURE);
        prescription.setFrequence(frequence);

        Assert
                .assertEquals("Prescription : 5 comprimés/gélules, 1 fois tous les 5 heure(s) à partir de j1 pendant 1 jour(s)",
                              this.helper.buildResume(prescription));
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.component.prescription.helper.ProduitPrescritHelper#buildResume(fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit)}
     * .
     */
    @Test
    public void testBuildResumeNumTraitement()
    {
        final ProduitPrescrit prescription = new ProduitPrescrit();
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setModePrescription(ModePrescription.NUM_TRAITEMENT);
        prescription.setConditionnement(conditionnement);
        final TempsPrescription temps = new TempsPrescription();
        temps.setNb(1);
        temps.setUnite(UniteTemps.JOUR);
        prescription.setDebut(temps);
        prescription.setDuree(temps);
        final Frequence frequence = new Frequence();
        frequence.setNbFrequence(1);
        frequence.setNbUniteTempsFrequence(5);
        frequence.setTypeRegularite(TypeRegularite.TOUS_LES);
        frequence.setUniteFrequence(UniteTemps.HEURE);
        prescription.setFrequence(frequence);

        Assert
                .assertEquals("Prescription : Par numéro de traitement, 1 fois tous les 5 heure(s) à partir de j1 pendant 1 jour(s)",
                              this.helper.buildResume(prescription));
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.component.prescription.helper.ProduitPrescritHelper#buildResume(fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit)}
     * .
     */
    @Test
    public void testBuildResumeEmpty()
    {
        Assert.assertEquals("",
                            this.helper.buildResume(new ProduitPrescrit()));
    }
}
