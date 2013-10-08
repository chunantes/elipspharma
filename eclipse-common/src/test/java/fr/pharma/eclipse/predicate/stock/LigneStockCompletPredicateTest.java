package fr.pharma.eclipse.predicate.stock;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Classe en charge de tester le Predicat de ligne de stock pour savoir si une
 * ligne de stock est complète.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class LigneStockCompletPredicateTest {
    /**
     * Prédicat à tester.
     */
    private LigneStockCompletPredicate predicat;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.predicat = new LigneStockCompletPredicate();
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.predicat = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.predicat);
    }

    /**
     * Méthode en charge de tester l'évaluation OK.
     */
    @Test
    public void testEvaluateOK() {
        final Essai essai = new Essai();
        final Pharmacie pharmacie = new Pharmacie();
        final Produit produit = new Medicament();
        final Conditionnement conditionnement = new Conditionnement();

        final LigneStock ligne = new LigneStock(essai, pharmacie, produit, conditionnement, Boolean.TRUE);
        ligne.setQteASortir(1);
    }

    /**
     * Méthode en charge de tester l'évaluation KO.
     */
    @Test
    public void testEvaluateKOQteNull() {
        final Essai essai = new Essai();
        final Pharmacie pharmacie = new Pharmacie();
        final Produit produit = new Medicament();
        final Conditionnement conditionnement = new Conditionnement();

        final LigneStock ligne = new LigneStock(essai, pharmacie, produit, conditionnement, Boolean.TRUE);
        ligne.setQteASortir(null);
        Assert.assertFalse(this.predicat.evaluate(ligne));
    }

    /**
     * Méthode en charge de tester l'évaluation KO.
     */
    @Test
    public void testEvaluateKOQteNegatif() {
        final Essai essai = new Essai();
        final Pharmacie pharmacie = new Pharmacie();
        final Produit produit = new Medicament();
        final Conditionnement conditionnement = new Conditionnement();

        final LigneStock ligne = new LigneStock(essai, pharmacie, produit, conditionnement, Boolean.TRUE);
        ligne.setQteASortir(-1);
        Assert.assertFalse(this.predicat.evaluate(ligne));
    }

    /**
     * Méthode en charge de tester l'évaluation KO.
     */
    @Test
    public void testEvaluateKOQteZero() {
        final Essai essai = new Essai();
        final Pharmacie pharmacie = new Pharmacie();
        final Produit produit = new Medicament();
        final Conditionnement conditionnement = new Conditionnement();

        final LigneStock ligne = new LigneStock(essai, pharmacie, produit, conditionnement, Boolean.TRUE);
        ligne.setQteASortir(0);
        Assert.assertFalse(this.predicat.evaluate(ligne));
    }

}
