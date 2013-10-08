package fr.pharma.eclipse.component.produit.helper;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.produit.FormeConditionnement;
import fr.pharma.eclipse.domain.enums.produit.UniteDosage;
import fr.pharma.eclipse.domain.enums.produit.UniteGestion;
import fr.pharma.eclipse.domain.enums.produit.VoieAdministration;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du helper ConditionnementHelper.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ConditionnementHelperTest extends AbstractEclipseJUnitTest {

    /**
     * Helper.
     */
    private ConditionnementHelper helper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.helper = new ConditionnementHelper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.helper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.helper);
    }

    /**
     * Test de la méthode buildResume.
     */
    @Test
    public void testBuildResumeEmpty() {
        final Conditionnement conditionnement = new Conditionnement();
        Assert.assertEquals("", this.helper.buildResume(conditionnement));
    }

    /**
     * Test de la méthode buildResume().
     */
    @Test
    public void testBuildResume() {
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setUniteGestion(UniteGestion.BOITE);
        conditionnement.setForme(FormeConditionnement.CONDITIONNEMENT_PRIMAIRE);
        conditionnement.setNbUnitePrescription(new BigDecimal(5));
        conditionnement.setDosage(new BigDecimal(20));
        conditionnement.setUniteDosage(UniteDosage.COMPRIME);
        conditionnement.setVoieAdministration(VoieAdministration.BOLUS);
        Assert.assertEquals("Boîte 5 Unités de 20 cp sous forme de Conditionnement primaire", this.helper.buildResume(conditionnement));
    }

    /**
     * Testé une valeur avec des zeros à la fin du nobmre décimal
     */
    @Test
    public void testBuildResumeAvecTrailingZeros() {
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setUniteGestion(UniteGestion.BOITE);
        conditionnement.setForme(FormeConditionnement.CONDITIONNEMENT_PRIMAIRE);
        conditionnement.setNbUnitePrescription(new BigDecimal(5));
        conditionnement.setDosage(new BigDecimal("0.100"));
        conditionnement.setUniteDosage(UniteDosage.POURCENTAGE);
        conditionnement.setVoieAdministration(VoieAdministration.BOLUS);
        Assert.assertEquals("Boîte 5 Unités de 0.1 % sous forme de Conditionnement primaire", this.helper.buildResume(conditionnement));
    }

    /**
     * Test de la méthode buildResume().
     */
    @Test
    public void testBuildResumeFlaconEmpty() {
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setUniteGestion(UniteGestion.FLACON);
        conditionnement.setForme(FormeConditionnement.CONDITIONNEMENT_PRIMAIRE);
        conditionnement.setNbUnitePrescription(new BigDecimal(5));
        conditionnement.setDosage(new BigDecimal(20));
        conditionnement.setUniteDosage(UniteDosage.COMPRIME);
        conditionnement.setVoieAdministration(VoieAdministration.BOLUS);
        Assert.assertEquals("", this.helper.buildResume(conditionnement));
    }

    /**
     * Test de la méthode buildResume().
     */
    @Test
    public void testBuildResumeFlacon() {
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setUniteGestion(UniteGestion.FLACON);
        conditionnement.setForme(FormeConditionnement.CONDITIONNEMENT_PRIMAIRE);
        conditionnement.setNbUnitePrescription(new BigDecimal(5));
        conditionnement.setDosage(new BigDecimal(20));
        conditionnement.setContenance(new BigDecimal(5));
        conditionnement.setUniteContenance(UniteDosage.COMPRIME);
        conditionnement.setUniteDosage(UniteDosage.COMPRIME);
        conditionnement.setVoieAdministration(VoieAdministration.BOLUS);
        Assert.assertEquals("Flacon contenant 5 cp", this.helper.buildResume(conditionnement));
    }

}
