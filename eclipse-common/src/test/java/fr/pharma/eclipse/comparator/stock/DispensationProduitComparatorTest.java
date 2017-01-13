/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.pharma.eclipse.comparator.stock;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.comparator.dispensation.DispensationProduitComparator;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;

/**
 *
 * @author sgl
 */
public class DispensationProduitComparatorTest {
    /**
     * Comparator à tester.
     */
    private DispensationProduitComparator comparator;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.comparator = new DispensationProduitComparator();
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.comparator = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.comparator);
    }

    /**
     * Méthode en charge de tester la méthode compare.
     */
    @Test
    public void testCompareOK() {
        final Produit produit = new Medicament();
        produit.setDenomination("denomination");
        final ProduitPrescrit prescrit = new ProduitPrescrit();
        final Dispensation dispensation = new Dispensation();
        dispensation.setCommentaire("libDispensation");
        final DispensationProduit val1 = new DispensationProduit(prescrit, dispensation);
        val1.setNumLot("lot1");
        val1.setNumTraitement("trait1");
        val1.setProduit(produit);
        final DispensationProduit val2 = new DispensationProduit(prescrit, dispensation);
        val2.setNumLot("lot1");
        val2.setNumTraitement("trait1");
        val2.setProduit(produit);
        Assert.assertEquals(0, this.comparator.compare(val1, val2));
    }

        /**
     * Méthode en charge de tester la méthode compare.
     */
    @Test
    public void testCompareAvecMemeNumLotProduitDiff() {
        final Produit produitA = new Medicament();
        produitA.setDenomination("denomination A");
        final Produit produitB = new Medicament();
        produitB.setDenomination("denomination B");
        final ProduitPrescrit prescrit = new ProduitPrescrit();
        final Dispensation dispensation = new Dispensation();
        dispensation.setCommentaire("libDispensation");
        final DispensationProduit val1 = new DispensationProduit(prescrit, dispensation);
        val1.setNumLot("lot1");
        val1.setNumTraitement("trait1");
        val1.setProduit(produitA);
        final DispensationProduit val2 = new DispensationProduit(prescrit, dispensation);
        val2.setNumLot("lot1");
        val2.setNumTraitement("trait1");
        val2.setProduit(produitB);
        Assert.assertEquals(-1, this.comparator.compare(val1, val2));
    }
    
    /**
     * Méthode en charge de tester la méthode compare.
     */
    @Test
    public void testCompareInferieur() {
        final Produit produit = new Medicament();
        produit.setDenomination("denomination");
        final ProduitPrescrit prescrit = new ProduitPrescrit();
        prescrit.setProduit(produit);
        final Dispensation dispensation = new Dispensation();
        dispensation.setCommentaire("libDispensation");

        final DispensationProduit val1 = new DispensationProduit(prescrit, dispensation);
        val1.setNumLot("0");
        val1.setProduit(produit);
        final DispensationProduit val2 = new DispensationProduit(prescrit, dispensation);
        val2.setNumLot("1");
        val2.setProduit(produit);
        Assert.assertTrue(this.comparator.compare(val1, val2) < 0);
    }

   
}
