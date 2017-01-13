package fr.pharma.eclipse.validator.remove.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.prescription.ProduitPrescritSearchCriteria;
import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.stock.MvtStockService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe ProduitRemoveValidator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ProduitRemoveValidatorTest extends AbstractEclipseJUnitTest {

    /**
     * Validator à tester.
     */
    private ProduitRemoveValidator validator;

    /**
     * Service mvtStock.
     */
    private MvtStockService<MvtStock> mvtStockService;

    /**
     * Service produitPrescrit.
     */
    private GenericService<ProduitPrescrit> produitPrescritService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.produitPrescritService = Mockito.mock(GenericService.class);
        this.mvtStockService = Mockito.mock(MvtStockService.class);
        this.validator = new ProduitRemoveValidator();
        this.validator.setMvtStockService(this.mvtStockService);
        this.validator.setProduitPrescritService(this.produitPrescritService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.mvtStockService = null;
        this.produitPrescritService = null;
        this.validator = null;
    }

    /**
     * {@inheritDoc}
     */
    @Test
    @Override
    public void testInit() {
        Assert.assertNotNull(this.produitPrescritService);
        Assert.assertNotNull(this.validator);
        Assert.assertNotNull(this.mvtStockService);
    }

    /**
     * Test de la méthode validate.
     */
    @Test
    public void testValidateOk() {
        Mockito.when(this.mvtStockService.count(Matchers.any(MvtStockSearchCriteria.class))).thenReturn(0L);
        Mockito.when(this.produitPrescritService.count(Matchers.any(ProduitPrescritSearchCriteria.class))).thenReturn(0L);
        this.validator.validate(new Medicament());
    }

    /**
     * Test de la méthode validate.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKo1() {
        final List<MvtStock> mvts = new ArrayList<MvtStock>();
        mvts.add(new DispensationProduit());
        Mockito.when(this.mvtStockService.count(Matchers.any(MvtStockSearchCriteria.class))).thenReturn(1L);
        Mockito.when(this.produitPrescritService.count(Matchers.any(ProduitPrescritSearchCriteria.class))).thenReturn(0L);
        this.validator.validate(new Medicament());
    }

    /**
     * Test de la méthode validate.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKo2() {
        Mockito.when(this.mvtStockService.count(Matchers.any(MvtStockSearchCriteria.class))).thenReturn(0L);
        Mockito.when(this.produitPrescritService.count(Matchers.any(ProduitPrescritSearchCriteria.class))).thenReturn(1L);
        this.validator.validate(new Medicament());
    }
}
