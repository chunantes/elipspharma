package fr.pharma.eclipse.service.surcout.checker.impl;

import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.produit.DetailProduit;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.ProduitTherapeutique;

/**
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TracabiliteCheckerTest {

    @Test
    public void testMedicamentStupefiant() {
        // Prepare
        final Medicament m = new Medicament();
        m.setStupefiant(Boolean.TRUE);

        final Essai e = new Essai();
        e.setDetailProduit(new DetailProduit());
        e.getDetailProduit().setProduits(new TreeSet<Produit>());
        e.getDetailProduit().getProduits().add(m);

        // Test
        final TracabiliteChecker tc = new TracabiliteChecker();
        final boolean result = tc.check(e);

        // Verify
        Assert.assertTrue(result);
    }

    /**
     * MDS(Médicament dérivé du sang).
     */
    @Test
    public void testProduitTherapeutiqueMDS() {
        // Prepare
        final ProduitTherapeutique pt = new ProduitTherapeutique();
        pt.setMds(Boolean.TRUE);

        final Essai e = new Essai();
        e.setDetailProduit(new DetailProduit());
        e.getDetailProduit().setProduits(new TreeSet<Produit>());
        e.getDetailProduit().getProduits().add(pt);

        // Test
        final TracabiliteChecker tc = new TracabiliteChecker();
        final boolean result = tc.check(e);

        // Verify
        Assert.assertTrue(result);
    }

    @Test
    public void testProduitTherapeutiqueMDSNull() {
        // Prepare
        final ProduitTherapeutique pt = new ProduitTherapeutique();

        final Essai e = new Essai();
        e.setDetailProduit(new DetailProduit());
        e.getDetailProduit().setProduits(new TreeSet<Produit>());
        e.getDetailProduit().getProduits().add(pt);

        // Test
        final TracabiliteChecker tc = new TracabiliteChecker();
        final boolean result = tc.check(e);

        // Verify
        Assert.assertFalse(result);
    }

}
