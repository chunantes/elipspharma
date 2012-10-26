package fr.pharma.eclipse.component.produit.validator;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Test de la classe ConditionnementRemoveValidator.
 
 * @version $Revision$ $Date$
 */
public class ConditionnementRemoveValidatorTest
    extends AbstractEclipseJUnitTest
{

    /**
     * Validator.
     */
    private ConditionnementRemoveValidator validator;

    /**
     * Service prescription Type.
     */
    private GenericService<PrescriptionType> prescriptionTypeService;

    /**
     * Service prescription Type.
     */
    private GenericService<ProduitPrescrit> produitPrescritService;

    /**
     * Faces utils.
     */
    private FacesUtils facesUtils;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp()
    {
        this.facesUtils = Mockito.mock(FacesUtils.class);
        this.produitPrescritService = Mockito.mock(GenericService.class);
        this.prescriptionTypeService = Mockito.mock(GenericService.class);
        this.validator = new ConditionnementRemoveValidator();
        this.validator.setFacesUtils(this.facesUtils);
        this.validator.setPrescriptionTypeService(this.prescriptionTypeService);
        this.validator.setProduitPrescritTypeService(this.produitPrescritService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown()
    {
        this.facesUtils = null;
        this.prescriptionTypeService = null;
        this.produitPrescritService = null;
        this.validator = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.facesUtils);
        Assert.assertNotNull(this.prescriptionTypeService);
        Assert.assertNotNull(this.produitPrescritService);
        Assert.assertNotNull(this.validator);
    }

    /**
     * Tes de la méthode validate.
     */
    @Test
    public void validateIdNull()
    {
        final Conditionnement conditionnement = new Conditionnement();
        Assert.assertTrue(this.validator.validate(conditionnement));
        Mockito.verify(this.facesUtils,
                       Mockito.never()).addMessage(FacesMessage.SEVERITY_ERROR,
                                                   "remove.impossible");
    }

    /**
     * Tes de la méthode validate.
     */
    @Test
    public void validatePrescriptionPrescritEmpty()
    {
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setId(1L);
        final List<PrescriptionType> prescriptions = new ArrayList<PrescriptionType>();
        final List<ProduitPrescrit> produitPrescrits = new ArrayList<ProduitPrescrit>();
        Mockito.when(this.prescriptionTypeService.getAll(Matchers.any(SearchCriteria.class)))
                .thenReturn(prescriptions);
        Mockito.when(this.produitPrescritService.getAll(Matchers.any(SearchCriteria.class)))
                .thenReturn(produitPrescrits);
        Assert.assertTrue(this.validator.validate(conditionnement));
        Mockito.verify(this.facesUtils,
                       Mockito.never()).addMessage(FacesMessage.SEVERITY_ERROR,
                                                   "remove.impossible");
    }

    /**
     * Tes de la méthode validate.
     */
    @Test
    public void validatePrescriptionEmpty()
    {
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setId(1L);
        final List<PrescriptionType> prescriptions = new ArrayList<PrescriptionType>();
        final List<ProduitPrescrit> produitPrescrits = new ArrayList<ProduitPrescrit>();
        produitPrescrits.add(new ProduitPrescrit());
        Mockito.when(this.prescriptionTypeService.getAll(Matchers.any(SearchCriteria.class)))
                .thenReturn(prescriptions);
        Mockito.when(this.produitPrescritService.getAll(Matchers.any(SearchCriteria.class)))
                .thenReturn(produitPrescrits);
        Assert.assertFalse(this.validator.validate(conditionnement));
        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR,
                                                   "remove.impossible");
    }

    /**
     * Tes de la méthode validate.
     */
    @Test
    public void validatePrescritEmpty()
    {
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setId(1L);
        final List<PrescriptionType> prescriptions = new ArrayList<PrescriptionType>();
        prescriptions.add(new PrescriptionType());
        final List<ProduitPrescrit> produitPrescrits = new ArrayList<ProduitPrescrit>();
        Mockito.when(this.prescriptionTypeService.getAll(Matchers.any(SearchCriteria.class)))
                .thenReturn(prescriptions);
        Mockito.when(this.produitPrescritService.getAll(Matchers.any(SearchCriteria.class)))
                .thenReturn(produitPrescrits);
        Assert.assertFalse(this.validator.validate(conditionnement));
        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR,
                                                   "remove.impossible");
    }

}
