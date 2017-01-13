package fr.pharma.eclipse.component.produit.validator;

import javax.faces.application.FacesMessage;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.stock.MvtStockService;
import fr.pharma.eclipse.service.stock.StockService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Test de la classe ConditionnementRemoveValidator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ConditionnementRemoveValidatorTest extends AbstractEclipseJUnitTest {

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
     * Service ligne stock.
     */
    private StockService ligneStockService;
    
    /**
     * Service ligne stock.
     */
    private MvtStockService<MvtStock> mvtStockService;
    
	/**
     * Faces utils.
     */
    private FacesUtils facesUtils;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.facesUtils = Mockito.mock(FacesUtils.class);
        this.produitPrescritService = Mockito.mock(GenericService.class);
        this.prescriptionTypeService = Mockito.mock(GenericService.class);
        this.ligneStockService = Mockito.mock(StockService.class);
        this.mvtStockService = Mockito.mock(MvtStockService.class);
        this.validator = new ConditionnementRemoveValidator();
        this.validator.setFacesUtils(this.facesUtils);
        this.validator.setPrescriptionTypeService(this.prescriptionTypeService);
        this.validator.setProduitPrescritTypeService(this.produitPrescritService);
        this.validator.setLigneStockService(this.ligneStockService);
        this.validator.setMvtStockService(this.mvtStockService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.facesUtils = null;
        this.prescriptionTypeService = null;
        this.produitPrescritService = null;
        this.ligneStockService = null;
        this.validator = null;
        this.mvtStockService = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.facesUtils);
        Assert.assertNotNull(this.prescriptionTypeService);
        Assert.assertNotNull(this.produitPrescritService);
        Assert.assertNotNull(this.ligneStockService);
        Assert.assertNotNull(this.validator);
        Assert.assertNotNull(this.mvtStockService);
    }

    /**
     * Tes de la méthode validate.
     */
    @Test
    public void validateIdNull() {
        final Conditionnement conditionnement = new Conditionnement();
        Assert.assertTrue(this.validator.validate(conditionnement));
        Mockito.verify(this.facesUtils, Mockito.never()).addMessage(FacesMessage.SEVERITY_ERROR, "remove.impossible");
    }

    /**
     * Tes de la méthode validate.
     */
    @Test
    public void validatePrescriptionPrescritLigneEmpty() {
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setId(1L);
        Mockito.when(this.prescriptionTypeService.count(Matchers.any(SearchCriteria.class))).thenReturn(0L);
        Mockito.when(this.produitPrescritService.count(Matchers.any(SearchCriteria.class))).thenReturn(0L);
        Mockito.when(this.ligneStockService.count(Matchers.any(SearchCriteria.class))).thenReturn(0L);
        Mockito.when(this.mvtStockService.count(Matchers.any(SearchCriteria.class))).thenReturn(0L);
        Assert.assertTrue(this.validator.validate(conditionnement));
        Mockito.verify(this.facesUtils, Mockito.never()).addMessage(FacesMessage.SEVERITY_ERROR, "remove.impossible");
    }
    
    /**
     * Tes de la méthode validate.
     */
    @Test
    public void validatePrescriptionPrescritEmpty() {
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setId(1L);
        Mockito.when(this.prescriptionTypeService.count(Matchers.any(SearchCriteria.class))).thenReturn(0L);
        Mockito.when(this.produitPrescritService.count(Matchers.any(SearchCriteria.class))).thenReturn(0L);
        Mockito.when(this.ligneStockService.count(Matchers.any(SearchCriteria.class))).thenReturn(1L);
        Mockito.when(this.mvtStockService.count(Matchers.any(SearchCriteria.class))).thenReturn(0L);
        Assert.assertFalse(this.validator.validate(conditionnement));
        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR, "remove.impossible");
    }

    /**
     * Tes de la méthode validate.
     */
    @Test
    public void validatePrescriptionEmpty() {
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setId(1L);
        Mockito.when(this.prescriptionTypeService.count(Matchers.any(SearchCriteria.class))).thenReturn(0L);
        Mockito.when(this.produitPrescritService.count(Matchers.any(SearchCriteria.class))).thenReturn(1L);
        Mockito.when(this.ligneStockService.count(Matchers.any(SearchCriteria.class))).thenReturn(0L);
        Assert.assertFalse(this.validator.validate(conditionnement));
        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR, "remove.impossible");
    }

    /**
     * Tes de la méthode validate.
     */
    @Test
    public void validatePrescritEmpty() {
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setId(1L);
        Mockito.when(this.prescriptionTypeService.count(Matchers.any(SearchCriteria.class))).thenReturn(1L);
        Mockito.when(this.produitPrescritService.count(Matchers.any(SearchCriteria.class))).thenReturn(1L);
        Mockito.when(this.ligneStockService.count(Matchers.any(SearchCriteria.class))).thenReturn(0L);
        Assert.assertFalse(this.validator.validate(conditionnement));
        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR, "remove.impossible");
    }

	/**
	 * @return the ligneStockService
	 */
	public StockService getLigneStockService() {
		return ligneStockService;
	}

	/**
	 * @param ligneStockService the ligneStockService to set
	 */
	public void setLigneStockService(StockService ligneStockService) {
		this.ligneStockService = ligneStockService;
	}
	


    /**
	 * @return the mvtStockService
	 */
	public MvtStockService<MvtStock> getMvtStockService() {
		return mvtStockService;
	}

	/**
	 * @param mvtStockService the mvtStockService to set
	 */
	public void setMvtStockService(MvtStockService<MvtStock> mvtStockService) {
		this.mvtStockService = mvtStockService;
	}


}
