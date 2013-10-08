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
public class LigneStockEnQuarantainePredicateTest {
    /**
     * Méthode en charge de tester l'évaluation du prédicat.
     */
    @Test
    public void testEvaluateOK() {
        LigneStockEnQuarantainePredicate predicate = new LigneStockEnQuarantainePredicate();
        final LigneStock ligneStock = new LigneStock(new Essai(), new Pharmacie(), new Medicament(), new Conditionnement(), Boolean.TRUE);

        ligneStock.setStockage(LigneStock.EN_QUARANTAINE);
        Assert.assertTrue("Test avec ligneStock.stockage == 'En quarantaine'", predicate.evaluate(ligneStock));

        ligneStock.setStockage("bla bla");
        Assert.assertFalse("Test avec ligneStock.stockage != 'En quarantaine'", predicate.evaluate(ligneStock));

        ligneStock.setStockage("");
        Assert.assertFalse("Test avec ligneStock.stockage == ''", predicate.evaluate(ligneStock));

        ligneStock.setStockage(null);
        Assert.assertFalse("Test avec ligneStock.stockage == null", predicate.evaluate(ligneStock));

        Assert.assertFalse("Test avec ligneStock == null", predicate.evaluate(null));
    }
}
