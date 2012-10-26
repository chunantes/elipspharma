package fr.pharma.eclipse.component.produit;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.component.helper.BeanManagerHelper;
import fr.pharma.eclipse.component.produit.validator.DetailStockageRemoveValidator;
import fr.pharma.eclipse.component.produit.validator.DetailStockageValidator;
import fr.pharma.eclipse.domain.criteria.produit.DetailStockageSearchCriteria;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.detail.DetailStockage;
import fr.pharma.eclipse.redirect.RedirectHandler;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.ProduitUtils;

/**
 * Test de la classe DetailsStockagesManager.
 
 * @version $Revision$ $Date$
 */
public class DetailsStockagesManagerTest
    extends AbstractEclipseJUnitTest
{

    /**
     * Manager à tester.
     */
    private DetailsStockagesManager detailsStockagesManager;

    /**
     * DetailStockageManager mocké.
     */
    private DetailStockageManager mockedDetailManager;

    /**
     * Criteria.
     */
    private DetailStockageSearchCriteria criteria;

    /**
     * Validateur de suppression.
     */
    private DetailStockageRemoveValidator mockedRemoveValidator;

    /**
     * Validateur d'ajout.
     */
    private DetailStockageValidator mockedAddValidator;

    /**
     * Helper.
     */
    private BeanManagerHelper<DetailStockage> mockedHelper;

    /**
     * Redirect handler.
     */
    private RedirectHandler mcokedRedirectHandler;

    /**
     * {@inheri@SuppressWarnings("unchecked") tDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setUp()
    {
        this.criteria = new DetailStockageSearchCriteria();
        this.mockedDetailManager = Mockito.mock(DetailStockageManager.class);
        this.mockedRemoveValidator = Mockito.mock(DetailStockageRemoveValidator.class);
        this.mockedAddValidator = Mockito.mock(DetailStockageValidator.class);
        this.mcokedRedirectHandler = Mockito.mock(RedirectHandler.class);
        this.mockedHelper = Mockito.mock(BeanManagerHelper.class);
        this.detailsStockagesManager = new DetailsStockagesManager(this.criteria);
        this.detailsStockagesManager.setRemoveValidator(this.mockedRemoveValidator);
        this.detailsStockagesManager.setAddValidator(this.mockedAddValidator);
        this.detailsStockagesManager.setHelper(this.mockedHelper);
        this.detailsStockagesManager.setRedirectHandler(this.mcokedRedirectHandler);
        this.detailsStockagesManager.setManager(this.mockedDetailManager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown()
    {
        this.criteria = null;
        this.mockedDetailManager = null;
        this.mockedRemoveValidator = null;
        this.mockedAddValidator = null;
        this.detailsStockagesManager = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit()
    {
        Assert.assertNotNull(this.detailsStockagesManager);
        Assert.assertEquals(this.criteria,
                            this.detailsStockagesManager.getSearchCriteria());
        Assert.assertEquals(this.mockedAddValidator,
                            this.detailsStockagesManager.getAddValidator());
    }

    /**
     * Test de la méthode reinit().
     */
    @Test
    public void testReinit()
    {
        this.detailsStockagesManager.reinit();
        Assert.assertNull(this.detailsStockagesManager.getBeanSelected());
        Mockito.verify(this.mockedDetailManager).setBean(null);
    }

    /**
     * Test de la méthode addStockage - ok.
     */
    @Test
    public void testAddStockageOk()
    {
        long id = 1;
        final Produit p = ProduitUtils.makeMedicamentTest(id++);
        final DetailStockage detailStockage = new DetailStockage();
        detailStockage.setId(id++);
        final boolean expectedValidity = true;

        this.detailsStockagesManager.setBeanSelected(detailStockage);
        Mockito.when(this.mockedAddValidator.validate(detailStockage,
                                                      p.getDetailLogistique().getDetailsStockages()))
                .thenReturn(expectedValidity);

        this.detailsStockagesManager.addStockage(p,
                                                 detailStockage);

        Mockito.verify(this.mockedAddValidator).validate(detailStockage,
                                                         p.getDetailLogistique().getDetailsStockages());
        Assert.assertTrue(p.getDetailLogistique().getDetailsStockages().contains(detailStockage));
        Assert.assertNull(this.detailsStockagesManager.getBeanSelected());
    }

    /**
     * Test de la méthode addStockage - ko.
     */
    @Test
    public void testAddStockageKo()
    {
        long id = 1;
        final Produit p = ProduitUtils.makeMedicamentTest(id++);
        final DetailStockage detailStockage = new DetailStockage();
        detailStockage.setId(id++);
        final boolean expectedValidity = false;

        this.detailsStockagesManager.setBeanSelected(detailStockage);
        Mockito.when(this.mockedAddValidator.validate(detailStockage,
                                                      p.getDetailLogistique().getDetailsStockages()))
                .thenReturn(expectedValidity);

        this.detailsStockagesManager.addStockage(p,
                                                 detailStockage);

        Mockito.verify(this.mockedAddValidator).validate(detailStockage,
                                                         p.getDetailLogistique().getDetailsStockages());
        Assert.assertFalse(p.getDetailLogistique().getDetailsStockages().contains(detailStockage));
        Assert.assertEquals(detailStockage,
                            this.detailsStockagesManager.getBeanSelected());
    }

    /**
     * Test de la méthode addStockageRetour - ok.
     */
    @Test
    public void testAddStockageRetourOk()
    {
        long id = 1;
        final Produit p = ProduitUtils.makeMedicamentTest(id++);
        final DetailStockage detailStockage = new DetailStockage();
        detailStockage.setId(id++);
        final boolean expectedValidity = true;

        this.detailsStockagesManager.setBeanSelected(detailStockage);
        Mockito.when(this.mockedAddValidator.validate(detailStockage,
                                                      p.getDetailLogistique()
                                                              .getStockagesRetours()))
                .thenReturn(expectedValidity);

        this.detailsStockagesManager.addStockageRetour(p,
                                                       detailStockage);

        Mockito.verify(this.mockedAddValidator).validate(detailStockage,
                                                         p.getDetailLogistique()
                                                                 .getStockagesRetours());
        Assert.assertTrue(p.getDetailLogistique().getStockagesRetours().contains(detailStockage));
        Assert.assertNull(this.detailsStockagesManager.getBeanSelected());
    }

    /**
     * Test de la méthode addStockageRetour - ko.
     */
    @Test
    public void testAddStockageRetourKo()
    {
        long id = 1;
        final Produit p = ProduitUtils.makeMedicamentTest(id++);
        final DetailStockage detailStockage = new DetailStockage();
        detailStockage.setId(id++);
        final boolean expectedValidity = false;

        this.detailsStockagesManager.setBeanSelected(detailStockage);
        Mockito.when(this.mockedAddValidator.validate(detailStockage,
                                                      p.getDetailLogistique()
                                                              .getStockagesRetours()))
                .thenReturn(expectedValidity);

        this.detailsStockagesManager.addStockageRetour(p,
                                                       detailStockage);

        Mockito.verify(this.mockedAddValidator).validate(detailStockage,
                                                         p.getDetailLogistique()
                                                                 .getStockagesRetours());
        Assert
                .assertFalse(p
                        .getDetailLogistique()
                        .getStockagesRetours()
                        .contains(detailStockage));
        Assert.assertEquals(detailStockage,
                            this.detailsStockagesManager.getBeanSelected());
    }

}
