package fr.pharma.eclipse.validator.save.impl;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.DetailDonneesPharma;
import fr.pharma.eclipse.domain.model.essai.detail.surcout.DetailSurcout;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du validator PrevisionValidator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PrevisionValidatorTest extends AbstractEclipseJUnitTest {

    /**
     * Validator.
     */
    private PrevisionValidator validator;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.validator = new PrevisionValidator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.validator = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.validator);
    }

    /**
     * Test de la méthode validate.
     */
    @Test(expected = ValidationException.class)
    public void testValidateNbPatients() {
        final DonneesPrevision donnees = new DonneesPrevision();
        donnees.setDetailSurcout(new DetailSurcout());
        donnees.getDetailSurcout().setEssai(new Essai());
        donnees.getDetailSurcout().getEssai().setDetailDonneesPharma(new DetailDonneesPharma());
        this.validator.validate(donnees);
    }

    /**
     * Test de la méthode validate.
     */
    @Test(expected = ValidationException.class)
    public void testValidateNbAnnees() {
        final DonneesPrevision donnees = new DonneesPrevision();
        donnees.setDetailSurcout(new DetailSurcout());
        donnees.getDetailSurcout().setEssai(new Essai());
        donnees.getDetailSurcout().getEssai().setDetailDonneesPharma(new DetailDonneesPharma());
        donnees.getDetailSurcout().getEssai().getDetailDonneesPharma().getInfosGenerales().setNbPatientsPrevus(10);
        this.validator.validate(donnees);
    }

    /**
     * Test de la méthode validate.
     */
    @Test(expected = ValidationException.class)
    public void testValidateNbDestructions() {
        final DonneesPrevision donnees = new DonneesPrevision();
        donnees.setDetailSurcout(new DetailSurcout());
        donnees.getDetailSurcout().setEssai(new Essai());
        donnees.getDetailSurcout().getEssai().setDetailDonneesPharma(new DetailDonneesPharma());
        donnees.getDetailSurcout().getEssai().getDetailDonneesPharma().getInfosGenerales().setNbPatientsPrevus(10);
        donnees.setNbAnnees(5);
        this.validator.validate(donnees);
    }

    /**
     * Test de la méthode validate.
     */
    @Test(expected = ValidationException.class)
    public void testValidateNbReconstitutions() {
        final DonneesPrevision donnees = new DonneesPrevision();
        donnees.setDetailSurcout(new DetailSurcout());
        donnees.getDetailSurcout().setEssai(new Essai());
        donnees.getDetailSurcout().getEssai().setDetailDonneesPharma(new DetailDonneesPharma());
        donnees.getDetailSurcout().getEssai().getDetailDonneesPharma().getInfosGenerales().setNbPatientsPrevus(10);
        donnees.setNbAnnees(5);
        donnees.setNbDestructions(10);
        this.validator.validate(donnees);
    }

    /**
     * Test de la méthode validate.
     */
    @Test(expected = ValidationException.class)
    public void testValidateNbReetiquetages() {
        final DonneesPrevision donnees = new DonneesPrevision();
        donnees.setDetailSurcout(new DetailSurcout());
        donnees.getDetailSurcout().setEssai(new Essai());
        donnees.getDetailSurcout().getEssai().setDetailDonneesPharma(new DetailDonneesPharma());
        donnees.getDetailSurcout().getEssai().getDetailDonneesPharma().getInfosGenerales().setNbPatientsPrevus(10);
        donnees.setNbAnnees(5);
        donnees.setNbDestructions(10);
        this.validator.validate(donnees);
    }

    /**
     * Test de la méthode validate.
     */
    @Test(expected = ValidationException.class)
    public void testValidateNbDispensations() {
        final DonneesPrevision donnees = new DonneesPrevision();
        donnees.setDetailSurcout(new DetailSurcout());
        donnees.getDetailSurcout().setEssai(new Essai());
        donnees.getDetailSurcout().getEssai().setDetailDonneesPharma(new DetailDonneesPharma());
        donnees.getDetailSurcout().getEssai().getDetailDonneesPharma().getInfosGenerales().setNbPatientsPrevus(10);
        donnees.setNbAnnees(5);
        donnees.setNbDestructions(5);
        donnees.setNbReetiquetages(10);
        donnees.setNbReetiquetages(10);
        this.validator.validate(donnees);
    }

    /**
     * Test de la méthode validate.
     */
    @Test
    public void testValidateOk() {
        final DonneesPrevision donnees = new DonneesPrevision();
        donnees.setDetailSurcout(new DetailSurcout());
        donnees.getDetailSurcout().setEssai(new Essai());
        donnees.getDetailSurcout().getEssai().setDetailDonneesPharma(new DetailDonneesPharma());
        donnees.getDetailSurcout().getEssai().getDetailDonneesPharma().getInfosGenerales().setNbPatientsPrevus(10);
        donnees.setNbAnnees(5);
        donnees.setNbDestructions(5);
        donnees.setNbReetiquetages(10);
        donnees.setNbDispensationsRenouvellement(2);
        donnees.setNbVisiteMonitoring(1);
        donnees.setNbDispensations(10);
        donnees.setNbPrescriptions(1);
        donnees.setNbApprovisionnements(1);
        donnees.setNbAudits(1);
        donnees.setNbPreparationsNonSteriles(1);
        donnees.setNbPreparationsSteriles(1);

        this.validator.validate(donnees);
    }
}
