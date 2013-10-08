package fr.pharma.eclipse.comparator.stock;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.EtatStock;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Classe en charge de tester le comparator de EtatStock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EtatStockComparatorTest {
    /**
     * Comparator à tester.
     */
    private EtatStockComparator comparator;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.comparator = new EtatStockComparator();
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
        final Essai essai = new Essai();
        essai.setNumInterne("numInterne");
        essai.setNom("nom");
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setNom("nom");
        final Produit produit = new Medicament();
        produit.setDenomination("denomination");
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setLibelle("libConditionnement");
        final EtatStock etatStock1 = new EtatStock(essai, pharmacie, produit, conditionnement, true);
        final EtatStock etatStock2 = new EtatStock(essai, pharmacie, produit, conditionnement, true);
        Assert.assertEquals(0, this.comparator.compare(etatStock1, etatStock2));
    }

    /**
     * Méthode en charge de tester la méthode compare.
     */
    @Test
    public void testCompareInferieur() {
        final Essai essai = new Essai();
        essai.setNumInterne("numInterne");
        essai.setNom("nom");
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setNom("nom");
        final Produit produit = new Medicament();
        produit.setDenomination("denomination");
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setLibelle("libConditionnement");
        final Conditionnement conditionnement2 = new Conditionnement();
        conditionnement2.setLibelle("zzz");
        final EtatStock etatStock1 = new EtatStock(essai, pharmacie, produit, conditionnement, true);
        final EtatStock etatStock2 = new EtatStock(essai, pharmacie, produit, conditionnement2, true);
        Assert.assertTrue(this.comparator.compare(etatStock1, etatStock2) < 0);
    }

    /**
     * Méthode en charge de tester la méthode compare.
     */
    @Test
    public void testCompareSuperieur() {
        final Essai essai = new Essai();
        essai.setNumInterne("numInterne");
        essai.setNom("nom");
        final Essai essai2 = new Essai();
        essai2.setNumInterne("anumInterne");
        essai2.setNom("nom");
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setNom("nom");
        final Produit produit = new Medicament();
        produit.setDenomination("denomination");
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setLibelle("libConditionnement");
        final EtatStock etatStock1 = new EtatStock(essai, pharmacie, produit, conditionnement, true);
        final EtatStock etatStock2 = new EtatStock(essai2, pharmacie, produit, conditionnement, true);
        Assert.assertTrue(this.comparator.compare(etatStock1, etatStock2) > 0);
    }

}
