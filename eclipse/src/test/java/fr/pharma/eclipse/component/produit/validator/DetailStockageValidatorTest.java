package fr.pharma.eclipse.component.produit.validator;

import java.util.ArrayList;
import java.util.Arrays;

import javax.faces.application.FacesMessage;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.produit.detail.DetailStockage;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Test de la classe {@link DetailStockageValidator}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DetailStockageValidatorTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private DetailStockageValidator validator;

    /**
     * Utilitaire faces mocké.
     */
    private FacesUtils mockedFacesUtils;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedFacesUtils = Mockito.mock(FacesUtils.class);
        this.validator = new DetailStockageValidator();
        this.validator.setFacesUtils(this.mockedFacesUtils);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.validator = null;
        this.mockedFacesUtils = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.validator);
        Assert.assertEquals(this.mockedFacesUtils, this.validator.getFacesUtils());
    }

    /**
     * Test de la méthode validate - ok.
     */
    @Test
    public void testValidateOk() {
        long id = 1;
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        final DetailStockage detailStockage = new DetailStockage();
        detailStockage.setId(id++);
        detailStockage.setStockage(Mockito.mock(Stockage.class));
        detailStockage.setPharmacie(pharmacie);
        final boolean expected = true;
        Assert.assertEquals(expected, this.validator.validate(detailStockage, new ArrayList<DetailStockage>()));
        Mockito.verify(this.mockedFacesUtils).putCallbackValidityParam(expected);
    }

    /**
     * Test de la méthode validate - ko à cause de la pharmacie.
     */
    @Test
    public void testValidateKoPharmacie() {
        long id = 1;
        final Pharmacie pharmacie = null;
        final DetailStockage detailStockage = new DetailStockage();
        detailStockage.setId(id++);
        detailStockage.setPharmacie(pharmacie);
        final boolean expected = false;
        Assert.assertEquals(expected, this.validator.validate(detailStockage, new ArrayList<DetailStockage>()));
        Mockito.verify(this.mockedFacesUtils).addMessage(FacesMessage.SEVERITY_ERROR, "produit.stockage.pharmacie.notEmpty");
        Mockito.verify(this.mockedFacesUtils).putCallbackValidityParam(expected);
    }

    /**
     * Test de la méthode validate - ko à cause de l'unicité.
     */
    @Test
    public void testValidateKoUnicity() {
        long id = 1;
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        final DetailStockage detailStockage = new DetailStockage();
        detailStockage.setId(id++);
        detailStockage.setStockage(Mockito.mock(Stockage.class));
        detailStockage.setPharmacie(pharmacie);
        final boolean expected = false;
        Assert.assertEquals(expected, this.validator.validate(detailStockage, Arrays.asList(detailStockage)));
        Mockito.verify(this.mockedFacesUtils).addMessage(FacesMessage.SEVERITY_ERROR, "produit.stockage.unique");
        Mockito.verify(this.mockedFacesUtils).putCallbackValidityParam(expected);
    }

}
