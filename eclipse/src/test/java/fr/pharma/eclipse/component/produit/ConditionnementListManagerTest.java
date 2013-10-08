package fr.pharma.eclipse.component.produit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.component.helper.BeanManagerHelper;
import fr.pharma.eclipse.component.produit.validator.ConditionnementRemoveValidator;
import fr.pharma.eclipse.domain.criteria.produit.ConditionnementSearchCriteria;
import fr.pharma.eclipse.domain.enums.produit.TypeProduit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.redirect.RedirectHandler;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Description de la classe.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ConditionnementListManagerTest extends AbstractEclipseJUnitTest {

    /**
     * Manager à tester.
     */
    private ConditionnementListManager conditionnementsManager;

    /**
     * Criteria.
     */
    private ConditionnementSearchCriteria criteria;

    /**
     * Validateur de suppression.
     */
    private ConditionnementRemoveValidator mockedRemoveValidator;

    /**
     * Manager de conditionnement.
     */
    private ConditionnementManager conditionnementManager;

    /**
     * Helper.
     */
    private BeanManagerHelper<Conditionnement> mockedHelper;

    /**
     * Redirect handler.
     */
    private RedirectHandler mcokedRedirectHandler;

    /**
     * FacesUtils.
     */
    private FacesUtils facesUtils;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.facesUtils = Mockito.mock(FacesUtils.class);
        this.criteria = new ConditionnementSearchCriteria();
        this.mockedRemoveValidator = Mockito.mock(ConditionnementRemoveValidator.class);
        this.conditionnementManager = Mockito.mock(ConditionnementManager.class);
        this.mcokedRedirectHandler = Mockito.mock(RedirectHandler.class);
        this.mockedHelper = Mockito.mock(BeanManagerHelper.class);
        this.conditionnementsManager = new ConditionnementListManager(this.criteria);
        this.conditionnementsManager.setConditionnementManager(this.conditionnementManager);
        this.conditionnementsManager.setRemoveValidator(this.mockedRemoveValidator);
        this.conditionnementsManager.setHelper(this.mockedHelper);
        this.conditionnementsManager.setRedirectHandler(this.mcokedRedirectHandler);
        this.conditionnementsManager.setFacesUtils(this.facesUtils);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.criteria = null;
        this.mockedRemoveValidator = null;
        this.conditionnementsManager = null;
        this.conditionnementManager = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.conditionnementsManager);
        Assert.assertEquals(this.criteria, this.conditionnementsManager.getSearchCriteria());
        Assert.assertNotNull(this.conditionnementManager);
    }

    /**
     * Test de la méthode add.
     */
    @Test
    public void testAddWithoutId() {
        final Produit produit = this.createProduit();
        final Conditionnement conditionnement = this.createConditionnement();
        conditionnement.setId(1L);
        this.conditionnementsManager.add(produit, conditionnement);
        Assert.assertTrue(produit.getConditionnements().first().getId().equals(conditionnement.getId()));
        Assert.assertNull(this.conditionnementsManager.getBeanSelected());
    }
    /**
     * Test de la méthode add.
     */
    @Test
    public void testAddWithId() {
        final Produit produit = this.createProduit();
        final Conditionnement conditionnement = this.createConditionnement();
        conditionnement.setId(1L);
        this.conditionnementsManager.setAction("");
        produit.getConditionnements().add(conditionnement);
        this.conditionnementsManager.add(produit, conditionnement);
        Assert.assertTrue(produit.getConditionnements().first().getId().equals(conditionnement.getId()));
        Assert.assertNull(this.conditionnementsManager.getBeanSelected());
    }

    /**
     * Test de la méthode remove.
     */
    @Test
    public void testRemove() {
        final Produit produit = this.createProduit();
        final Conditionnement conditionnement1 = this.createConditionnement();
        conditionnement1.setLibelle("i1");
        conditionnement1.setId(1L);
        conditionnement1.setSelected(true);
        final Conditionnement conditionnement2 = this.createConditionnement();
        conditionnement2.setSelected(true);
        conditionnement2.setLibelle("2");
        produit.getConditionnements().add(conditionnement1);
        produit.getConditionnements().add(conditionnement2);

        final List<Conditionnement> result = new ArrayList<Conditionnement>();
        result.add(conditionnement2);

        Mockito.when(this.mockedRemoveValidator.validate(Matchers.any(Conditionnement.class))).thenReturn(true);

        Mockito.when(this.mockedHelper.getBeansSelected(Matchers.anyCollection())).thenReturn(result);

        this.conditionnementsManager.remove(produit);

        Mockito.verify(this.mockedRemoveValidator).validate(Matchers.any(Conditionnement.class));
        Assert.assertEquals(1, produit.getConditionnements().size());
    }

    /**
     * Test de la méthode reinit().
     */
    @Test
    public void testReinit() {
        this.conditionnementsManager.reinit();
        Assert.assertNull(this.conditionnementsManager.getBeanSelected());
    }

    /**
     * Méthode en charge de constuire le produit de test.
     * @return le produit de test.
     */
    private Produit createProduit() {
        final Produit p = new Medicament();
        p.setId(1L);
        p.setType(TypeProduit.DISPOSITIF_MEDICAL);
        return p;
    }

    /**
     * Méthode en charge de construire le conditionnement de tesT.
     * @return le conditionnement de tesT.
     */
    private Conditionnement createConditionnement() {
        final Conditionnement c = new Conditionnement();

        return c;
    }
}
