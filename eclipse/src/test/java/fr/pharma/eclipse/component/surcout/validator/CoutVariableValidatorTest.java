package fr.pharma.eclipse.component.surcout.validator;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.surcout.ModeCalcul;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Test de la classe CoutVariableValidator.
 
 * @version $Revision$ $Date$
 */
public class CoutVariableValidatorTest
    extends AbstractEclipseJUnitTest
{

    /**
     * Validateur.
     */
    private CoutVariableValidator validator;

    /**
     * FacesUtils.
     */
    private FacesUtils facesUtils;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp()
    {
        this.facesUtils = Mockito.mock(FacesUtils.class);
        this.validator = new CoutVariableValidator();
        this.validator.setFacesUtils(this.facesUtils);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown()
    {
        this.facesUtils = null;
        this.validator = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.validator);
        Assert.assertNotNull(this.facesUtils);
    }

    /**
     * TEst de la méthode validate.
     */
    @Test
    public void testValidateModeFail()
    {
        final Regle regle = new Regle();

        Assert.assertFalse(this.validator.validate(regle));
        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR,
                                                   "regle.mode.notEmpty");
    }

    /**
     * TEst de la méthode validate.
     */
    @Test
    public void testValidateUniteFail1()
    {
        final Regle regle = new Regle();
        regle.setMode(ModeCalcul.UNITE);

        Assert.assertFalse(this.validator.validate(regle));

        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR,
                                                   "regle.borne.notEmpty");;
        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR,
                                                   "regle.montant.notEmpty");
    }

    /**
     * TEst de la méthode validate.
     */
    @Test
    public void testValidateUniteFail2()
    {
        final Regle regle = new Regle();
        regle.setMode(ModeCalcul.UNITE);
        regle.setMontant(new BigDecimal(2));
        Assert.assertFalse(this.validator.validate(regle));

        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR,
                                                   "regle.borne.notEmpty");;
    }

    /**
     * TEst de la méthode validate.
     */
    @Test
    public void testValidateUniteFail3()
    {
        final Regle regle = new Regle();
        regle.setMode(ModeCalcul.UNITE);
        regle.setMontant(new BigDecimal(2));
        regle.setMax(2);
        regle.setMin(10);
        Assert.assertFalse(this.validator.validate(regle));

        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR,
                                                   "regle.borne.incoherent");
    }

    /**
     * TEst de la méthode validate.
     */
    @Test
    public void testValidateUniteOK()
    {
        final Regle regle = new Regle();
        regle.setMode(ModeCalcul.UNITE);
        regle.setMontant(new BigDecimal(2));
        regle.setMax(10);
        regle.setMin(0);
        Assert.assertTrue(this.validator.validate(regle));

    }

    /**
     * TEst de la méthode validate.
     */
    @Test
    public void testValidateForfaitFail1()
    {
        final Regle regle = new Regle();
        regle.setMode(ModeCalcul.LOT_FORFAITAIRE);

        Assert.assertFalse(this.validator.validate(regle));

        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR,
                                                   "regle.borne.notEmpty");;
        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR,
                                                   "regle.montant.notEmpty");
    }

    /**
     * TEst de la méthode validate.
     */
    @Test
    public void testValidateForfaitFail2()
    {
        final Regle regle = new Regle();
        regle.setMode(ModeCalcul.LOT_FORFAITAIRE);
        regle.setMontant(new BigDecimal(2));
        Assert.assertFalse(this.validator.validate(regle));

        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR,
                                                   "regle.borne.notEmpty");;
    }

    /**
     * TEst de la méthode validate.
     */
    @Test
    public void testValidateForfaitFail3()
    {
        final Regle regle = new Regle();
        regle.setMode(ModeCalcul.LOT_FORFAITAIRE);
        regle.setMontant(new BigDecimal(2));
        regle.setMax(10);
        regle.setMin(100);
        Assert.assertFalse(this.validator.validate(regle));

        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR,
                                                   "regle.borne.incoherent");
    }

    /**
     * TEst de la méthode validate.
     */
    @Test
    public void testValidateForfaitOK()
    {
        final Regle regle = new Regle();
        regle.setMode(ModeCalcul.LOT_FORFAITAIRE);
        regle.setMontant(new BigDecimal(2));
        regle.setMax(10);
        regle.setMin(0);
        Assert.assertTrue(this.validator.validate(regle));

    }
}
