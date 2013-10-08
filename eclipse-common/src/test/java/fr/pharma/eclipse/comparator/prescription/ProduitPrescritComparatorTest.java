package fr.pharma.eclipse.comparator.prescription;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.design.UniteTemps;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du comparateur ProduitPrescritComparator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ProduitPrescritComparatorTest extends AbstractEclipseJUnitTest {
    /**
     * Comparateur à tester.
     */
    private ProduitPrescritComparator comparator;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.comparator = new ProduitPrescritComparator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.comparator = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.comparator);
    }

    /**
     * Test de la méthode compare.
     */
    @Test
    public void testCompareSup() {
        final Produit produit = new Medicament();
        produit.setDenomination("produit");
        final ProduitPrescrit p1 = new ProduitPrescrit();
        final ProduitPrescrit p2 = new ProduitPrescrit();
        p1.setProduit(produit);
        p2.setProduit(produit);
        final TempsPrescription t1 = new TempsPrescription();
        t1.setNb(1);
        t1.setUnite(UniteTemps.JOUR);
        p1.setDebut(t1);
        final TempsPrescription t2 = new TempsPrescription();
        t2.setNb(2);
        t2.setUnite(UniteTemps.JOUR);
        p2.setDebut(t2);
        final Conditionnement c = new Conditionnement();
        c.setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        final Conditionnement c2 = new Conditionnement();
        c2.setModePrescription(ModePrescription.DOSE);
        p1.setConditionnement(c);
        p2.setConditionnement(c2);

        Assert.assertTrue(this.comparator.compare(p1, p2) < 0);
    }

    /**
     * Test de la méthode compare.
     */
    @Test
    public void testCompareInf() {
        final Produit produit = new Medicament();
        produit.setDenomination("produit");
        final ProduitPrescrit p1 = new ProduitPrescrit();
        final ProduitPrescrit p2 = new ProduitPrescrit();
        p1.setProduit(produit);
        p2.setProduit(produit);
        final TempsPrescription t1 = new TempsPrescription();
        t1.setNb(2);
        t1.setUnite(UniteTemps.JOUR);
        p1.setDebut(t1);
        final TempsPrescription t2 = new TempsPrescription();
        t2.setNb(1);
        t2.setUnite(UniteTemps.JOUR);
        p2.setDebut(t2);
        final Conditionnement c = new Conditionnement();
        c.setModePrescription(ModePrescription.DOSE);
        final Conditionnement c2 = new Conditionnement();
        c2.setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        p1.setConditionnement(c);
        p2.setConditionnement(c2);

        Assert.assertTrue(this.comparator.compare(p1, p2) > 0);
    }

    /**
     * Test de la méthode compare.
     */
    @Test
    public void testCompareEq() {
        final Produit produit = new Medicament();
        produit.setDenomination("produit");
        final ProduitPrescrit p1 = new ProduitPrescrit();
        final ProduitPrescrit p2 = new ProduitPrescrit();
        p1.setProduit(produit);
        p2.setProduit(produit);
        final TempsPrescription t1 = new TempsPrescription();
        t1.setNb(1);
        t1.setUnite(UniteTemps.JOUR);
        p1.setDebut(t1);
        final TempsPrescription t2 = new TempsPrescription();
        t2.setNb(1);
        t2.setUnite(UniteTemps.JOUR);
        p2.setDebut(t2);
        final Conditionnement c = new Conditionnement();
        c.setModePrescription(ModePrescription.DOSE);
        p1.setConditionnement(c);
        p2.setConditionnement(c);

        Assert.assertTrue(this.comparator.compare(p1, p2) == 0);
    }
}
