package fr.pharma.eclipse.component.surcout.validator;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.surcout.PerimetreCout;
import fr.pharma.eclipse.domain.enums.surcout.TypeCout;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Test du validateur RegleValidator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class RegleValidatorTest extends AbstractEclipseJUnitTest {

    /**
     * Validator à tester.
     */
    private RegleValidator validator;

    /**
     * FacesUtils.
     */
    private FacesUtils facesUtils;

    /**
     * Validateur de cout variable.
     */
    private CoutVariableValidator coutVariableValidator;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.facesUtils = Mockito.mock(FacesUtils.class);
        this.coutVariableValidator = Mockito.mock(CoutVariableValidator.class);
        this.validator = new RegleValidator();
        this.validator.setCoutVariableValidator(this.coutVariableValidator);
        this.validator.setFacesUtils(this.facesUtils);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.facesUtils = null;
        this.coutVariableValidator = null;
        this.validator = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.validator);
        Assert.assertNotNull(this.coutVariableValidator);
        Assert.assertNotNull(this.facesUtils);
    }

    /**
     * Test de la méthode validate.
     */
    @Test
    public void testValidateFail1() {
        final Regle regle = new Regle();
        Assert.assertFalse(this.validator.validate(regle));
        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR, "regle.type.notEmpty");

    }

    /**
     * Test de la méthode validate.
     */
    @Test
    public void testValidateFixeFail1() {
        final Regle regle = new Regle();
        regle.setType(TypeCout.FIXE);
        Assert.assertFalse(this.validator.validate(regle));
        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR, "regle.valeurs.notEmpty");
        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR, "regle.perimetre.notEmpty");

    }

    /**
     * Test de la méthode validate.
     */
    @Test
    public void testValidateFixeFail2() {
        final Regle regle = new Regle();
        regle.setType(TypeCout.FIXE);
        regle.setPremiereAnnee(new BigDecimal(0.0));
        Assert.assertFalse(this.validator.validate(regle));
        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR, "regle.valeurs.notEmpty");
        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR, "regle.perimetre.notEmpty");

    }

    /**
     * Test de la méthode validate.
     */
    @Test
    public void testValidateFixeFail3() {
        final Regle regle = new Regle();
        regle.setType(TypeCout.FIXE);
        regle.setAnneesSuivantes(new BigDecimal(0.0));
        Assert.assertFalse(this.validator.validate(regle));
        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR, "regle.valeurs.notEmpty");
        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR, "regle.perimetre.notEmpty");

    }

    /**
     * Test de la méthode validate.
     */
    @Test
    public void testValidateFixeFail4() {
        final Regle regle = new Regle();
        regle.setType(TypeCout.FIXE);
        regle.setPremiereAnnee(new BigDecimal(0.0));
        regle.setAnneesSuivantes(new BigDecimal(0.0));
        Assert.assertFalse(this.validator.validate(regle));
        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR, "regle.perimetre.notEmpty");

    }

    /**
     * Test de la méthode validate.
     */
    @Test
    public void testValidateFixeOk() {
        final Regle regle = new Regle();
        regle.setType(TypeCout.FIXE);
        regle.setPremiereAnnee(new BigDecimal(0.0));
        regle.setAnneesSuivantes(new BigDecimal(0.0));
        regle.setPerimetre(PerimetreCout.ESSAI);
        Assert.assertTrue(this.validator.validate(regle));

    }

    /**
     * Test de la méthode validate.
     */
    @Test
    public void testValidateVariableFail1() {
        final Regle regle = new Regle();
        regle.setType(TypeCout.VARIABLE);
        regle.setPerimetre(PerimetreCout.ESSAI);
        Mockito.when(this.coutVariableValidator.validate(Matchers.any(Regle.class))).thenReturn(false);
        Assert.assertFalse(this.validator.validate(regle));

    }

    /**
     * Test de la méthode validate.
     */
    @Test
    public void testValidateVariableFail2() {
        final Regle regle = new Regle();
        regle.setType(TypeCout.VARIABLE);
        Mockito.when(this.coutVariableValidator.validate(Matchers.any(Regle.class))).thenReturn(true);
        Assert.assertFalse(this.validator.validate(regle));
        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR, "regle.perimetre.notEmpty");

    }

    /**
     * Test de la méthode validate.
     */
    @Test
    public void testValidateVariableOk() {
        final Regle regle = new Regle();
        regle.setType(TypeCout.VARIABLE);
        regle.setPerimetre(PerimetreCout.ESSAI);
        Mockito.when(this.coutVariableValidator.validate(Matchers.any(Regle.class))).thenReturn(true);
        Assert.assertTrue(this.validator.validate(regle));

    }
}
