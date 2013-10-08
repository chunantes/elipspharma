package fr.pharma.eclipse.predicate.prescription;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du predicat ProduitPrescritPredicate.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ProduitPrescritPredicateTest extends AbstractEclipseJUnitTest {

    /**
     * Prédicat à tester.
     */
    private ProduitPrescritPredicate predicate;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        final ProduitPrescrit produitPrescrit = new ProduitPrescrit();
        final Produit produit = new Medicament();
        produit.setId(1L);
        produitPrescrit.setProduit(produit);
        final Conditionnement c = new Conditionnement();
        c.setModePrescription(ModePrescription.NUM_TRAITEMENT);
        produitPrescrit.setConditionnement(c);
        this.predicate = new ProduitPrescritPredicate(produitPrescrit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.predicate = null;
    }

    /**
     * {@inheritDoc}
     */
    @Test
    @Override
    public void testInit() {
        Assert.assertNotNull(this.predicate);
    }

    /**
     * Test de la méthode evaluate.
     */
    @Test
    public void testEvaluateKo() {
        final ProduitPrescrit p = new ProduitPrescrit();
        final Conditionnement c = new Conditionnement();
        c.setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        p.setConditionnement(c);
        final Produit produit = new Medicament();
        produit.setId(2L);
        p.setProduit(produit);
        Assert.assertFalse(this.predicate.evaluate(p));
    }

    /**
     * Test de la méthode evaluate.
     */
    @Test
    public void testEvaluateOk() {
        final ProduitPrescrit p = new ProduitPrescrit();
        final Conditionnement c = new Conditionnement();
        c.setModePrescription(ModePrescription.NUM_TRAITEMENT);
        p.setConditionnement(c);
        final Produit produit = new Medicament();
        produit.setId(1L);
        p.setProduit(produit);
        Assert.assertTrue(this.predicate.evaluate(p));
    }
}
