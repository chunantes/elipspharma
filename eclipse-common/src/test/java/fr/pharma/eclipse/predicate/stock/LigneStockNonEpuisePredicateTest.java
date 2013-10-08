package fr.pharma.eclipse.predicate.stock;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Classe en charge de tester le prédicat LigneStockEnQuarantainePredicate.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class LigneStockNonEpuisePredicateTest {

    /**
     * Méthode en charge de tester la récupération des lignes de stock dont la
     * quantité n'est pas à 0.
     */
    @Test
    public void testGetLignesStockQteNotZero() {
        LigneStockNonEpuisePredicate predicate = new LigneStockNonEpuisePredicate();
        final LigneStock ligneStock = new LigneStock(new Essai(), new Pharmacie(), new Medicament(), new Conditionnement(), Boolean.TRUE);

        ligneStock.setQteGlobalStock(1);
        Assert.assertTrue("Test avec ligneStock.qteGlobalStock == 1", predicate.evaluate(ligneStock));

        ligneStock.setQteGlobalStock(Integer.MAX_VALUE);
        Assert.assertTrue("Test avec ligneStock.qteGlobalStock == Integer.MAX_VALUE", predicate.evaluate(ligneStock));

        ligneStock.setQteGlobalStock(0);
        Assert.assertFalse("Test avec ligneStock.qteGlobalStock == 0", predicate.evaluate(ligneStock));

        ligneStock.setQteGlobalStock(-1);
        Assert.assertFalse("Test avec ligneStock.qteGlobalStock == -1", predicate.evaluate(ligneStock));

        ligneStock.setQteGlobalStock(Integer.MIN_VALUE);
        Assert.assertFalse("Test avec ligneStock.qteGlobalStock == Integer.MIN_VALUE)", predicate.evaluate(ligneStock));

        ligneStock.setQteGlobalStock(null);
        Assert.assertFalse("Test avec ligneStock.qteGlobalStock == null)", predicate.evaluate(ligneStock));

        Assert.assertFalse("Test avec ligneStock == null)", predicate.evaluate(null));

    }
}
