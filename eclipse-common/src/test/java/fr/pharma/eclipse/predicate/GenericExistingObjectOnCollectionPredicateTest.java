package fr.pharma.eclipse.predicate;

import org.apache.commons.collections.Predicate;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe {@link GenericExistingObjectOnCollectionPredicate}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GenericExistingObjectOnCollectionPredicateTest extends AbstractEclipseJUnitTest {
    /**
     * Prédicat testé.
     */
    private GenericExistingObjectOnCollectionPredicate predicate;

    /**
     * Prédicat secondaire, mocké.
     */
    private Predicate mockedSubPredicate;

    /**
     * Propriété à inspecter (collection).
     */
    private static final String COLLECTION_PROPERTY = "conditionnements";

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedSubPredicate = Mockito.mock(Predicate.class);
        this.predicate = new GenericExistingObjectOnCollectionPredicate(GenericExistingObjectOnCollectionPredicateTest.COLLECTION_PROPERTY, this.mockedSubPredicate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.predicate = null;
        this.mockedSubPredicate = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.predicate);
        Assert.assertEquals(GenericExistingObjectOnCollectionPredicateTest.COLLECTION_PROPERTY, this.predicate.getCollectionProperty());
        Assert.assertEquals(this.mockedSubPredicate, this.predicate.getCollectionObjectsPredicate());
    }

    /**
     * Test de la méthode evaluate(java.lang.Object) - cas nominal TRUE.
     */
    @Test
    public void testEvaluateNominalTrue() {
        long id = 1;
        final Produit produit = new Medicament();
        produit.setId(id++);
        final Conditionnement cond1 = new Conditionnement();
        cond1.setId(id++);
        cond1.setLibelle("lib1");
        final Conditionnement cond2 = new Conditionnement();
        cond2.setId(id++);
        cond2.setLibelle("lib2");
        produit.getConditionnements().add(cond1);
        produit.getConditionnements().add(cond2);
        Assert.assertEquals(2, produit.getConditionnements().size());

        Mockito.when(this.mockedSubPredicate.evaluate(cond1)).thenReturn(false);
        Mockito.when(this.mockedSubPredicate.evaluate(cond2)).thenReturn(true);

        final boolean res = this.predicate.evaluate(produit);
        Mockito.verify(this.mockedSubPredicate, Mockito.atLeastOnce()).evaluate(Matchers.any(Conditionnement.class));
        Assert.assertTrue(res);
    }

    /**
     * Test de la méthode evaluate(java.lang.Object) - cas nominal FALSE.
     */
    @Test
    public void testEvaluateNominalFalse() {
        long id = 1;
        final Produit produit = new Medicament();
        produit.setId(id++);
        final Conditionnement cond1 = new Conditionnement();
        cond1.setId(id++);
        cond1.setLibelle("lib1");
        final Conditionnement cond2 = new Conditionnement();
        cond2.setId(id++);
        cond2.setLibelle("lib2");
        produit.getConditionnements().add(cond1);
        produit.getConditionnements().add(cond2);
        Assert.assertEquals(2, produit.getConditionnements().size());

        Mockito.when(this.mockedSubPredicate.evaluate(cond1)).thenReturn(false);
        Mockito.when(this.mockedSubPredicate.evaluate(cond2)).thenReturn(false);

        final boolean res = this.predicate.evaluate(produit);
        Mockito.verify(this.mockedSubPredicate, Mockito.atLeastOnce()).evaluate(Matchers.any(Conditionnement.class));
        Assert.assertFalse(res);
    }

    /**
     * Test de la méthode evaluate(java.lang.Object) - la propriété est nulle.
     */
    @Test
    public void testEvaluateNullProperty() {
        long id = 1;
        final Produit produit = new Medicament();
        produit.setId(id++);
        produit.setConditionnements(null);

        final boolean res = this.predicate.evaluate(produit);
        Mockito.verify(this.mockedSubPredicate, Mockito.never()).evaluate(Matchers.any(Conditionnement.class));
        Assert.assertFalse(res);
    }

    /**
     * Test de la méthode evaluate(java.lang.Object) - la propriété ne désigne
     * pas une collection.
     */
    @Test
    public void testEvaluateInvalidProperty() {
        long id = 1;
        final Produit produit = new Medicament();
        produit.setId(id++);
        produit.setDenomination("denomination");

        this.predicate.setCollectionProperty("denomination");
        final boolean res = this.predicate.evaluate(produit);
        Mockito.verify(this.mockedSubPredicate, Mockito.never()).evaluate(Matchers.any(Conditionnement.class));
        Assert.assertFalse(res);
    }
}
