package fr.pharma.eclipse.component.design.helper;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.design.TypeRegularite;
import fr.pharma.eclipse.domain.enums.design.UniteTemps;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.domain.model.design.embedded.Frequence;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du helper PrescriptionTypeHelper.
 
 * @version $Revision$ $Date$
 */
public class PrescriptionTypeHelperTest
    extends AbstractEclipseJUnitTest
{

    /**
     * Helper à tester.
     */
    private PrescriptionTypeHelper helper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp()
    {
        this.helper = new PrescriptionTypeHelper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown()
    {
        this.helper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.helper);
    }

    /**
     * Test de la méthode buildResume avec un prescriptionType vide.
     */
    @Test
    public void testNotSet()
    {
        Assert.assertEquals("",
                            this.helper.buildResume(new PrescriptionType()));
    }

    @Test
    public void testNumTraitement()
    {
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setModePrescription(ModePrescription.NUM_TRAITEMENT);
        final PrescriptionType prescription = new PrescriptionType();
        prescription.setConditionnement(conditionnement);
        final Frequence frequence = new Frequence();
        frequence.setNbFrequence(1);
        frequence.setTypeRegularite(TypeRegularite.PAR);
        frequence.setUniteFrequence(UniteTemps.JOUR);
        prescription.setFrequence(frequence);
        final TempsPrescription debut = new TempsPrescription();
        debut.setNb(0);
        debut.setUnite(UniteTemps.JOUR);
        prescription.setDebut(debut);
        final TempsPrescription duree = new TempsPrescription();
        duree.setNb(10);
        duree.setUnite(UniteTemps.JOUR);
        prescription.setDuree(duree);

        Assert
                .assertEquals("Prescription : Par numéro de traitement, 1 fois par jour(s) à partir de j0 pendant 10 jour(s)",
                              this.helper.buildResume(prescription));
    }

    @Test
    public void testCondPrimaire()
    {
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setUnitePrescription("cp");
        conditionnement.setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        final PrescriptionType prescription = new PrescriptionType();
        prescription.setConditionnement(conditionnement);
        prescription.setNbUniteDosage(new BigDecimal(5));
        final Frequence frequence = new Frequence();
        frequence.setNbFrequence(1);
        frequence.setTypeRegularite(TypeRegularite.PAR);
        frequence.setUniteFrequence(UniteTemps.JOUR);
        prescription.setFrequence(frequence);
        final TempsPrescription debut = new TempsPrescription();
        debut.setNb(0);
        debut.setUnite(UniteTemps.JOUR);
        prescription.setDebut(debut);
        final TempsPrescription duree = new TempsPrescription();
        duree.setNb(10);
        duree.setUnite(UniteTemps.JOUR);
        prescription.setDuree(duree);

        Assert
                .assertEquals("Prescription : 5 cp, 1 fois par jour(s) à partir de j0 pendant 10 jour(s)",
                              this.helper.buildResume(prescription));
    }

    @Test
    public void testGanelique()
    {
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setUnitePrescription("cp");
        conditionnement.setModePrescription(ModePrescription.FORME_GALENIQUE);
        final PrescriptionType prescription = new PrescriptionType();
        prescription.setConditionnement(conditionnement);
        prescription.setNbUniteDosage(new BigDecimal(5));
        final Frequence frequence = new Frequence();
        frequence.setNbFrequence(1);
        frequence.setTypeRegularite(TypeRegularite.PAR);
        frequence.setUniteFrequence(UniteTemps.JOUR);
        prescription.setFrequence(frequence);
        final TempsPrescription debut = new TempsPrescription();
        debut.setNb(0);
        debut.setUnite(UniteTemps.JOUR);
        prescription.setDebut(debut);
        final TempsPrescription duree = new TempsPrescription();
        duree.setNb(10);
        duree.setUnite(UniteTemps.JOUR);
        prescription.setDuree(duree);

        Assert
                .assertEquals("Prescription : 5 comprimés/gélules, 1 fois par jour(s) à partir de j0 pendant 10 jour(s)",
                              this.helper.buildResume(prescription));
    }

    @Test
    public void testDose()
    {
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setModePrescription(ModePrescription.DOSE);
        final PrescriptionType prescription = new PrescriptionType();
        conditionnement.setUnitePrescription("mg");
        prescription.setConditionnement(conditionnement);
        prescription.setDosage(new BigDecimal(10));
        prescription.setNbUniteDosage(new BigDecimal(5));
        final Frequence frequence = new Frequence();
        frequence.setNbFrequence(1);
        frequence.setTypeRegularite(TypeRegularite.PAR);
        frequence.setUniteFrequence(UniteTemps.JOUR);
        prescription.setFrequence(frequence);
        final TempsPrescription debut = new TempsPrescription();
        debut.setNb(0);
        debut.setUnite(UniteTemps.JOUR);
        prescription.setDebut(debut);
        final TempsPrescription duree = new TempsPrescription();
        duree.setNb(10);
        duree.setUnite(UniteTemps.JOUR);
        prescription.setDuree(duree);

        Assert
                .assertEquals("Prescription : 5 fois 10 mg, 1 fois par jour(s) à partir de j0 pendant 10 jour(s)",
                              this.helper.buildResume(prescription));
    }
}
