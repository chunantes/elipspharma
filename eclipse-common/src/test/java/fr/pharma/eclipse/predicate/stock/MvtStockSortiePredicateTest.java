package fr.pharma.eclipse.predicate.stock;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stock.RetourPromoteur;

/**
 * Classe en charge de tester le Predicat de Mouvement de Stock de type Sortie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class MvtStockSortiePredicateTest {
    /**
     * Prédicat à tester.
     */
    private MvtStockSortiePredicate predicat;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.predicat = new MvtStockSortiePredicate();
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
    public void testEvaluateOKSortie() {
        final MvtStock mvt = new RetourPromoteur();
        mvt.setType(TypeMvtStock.RETOUR_PROMOTEUR);
        Assert.assertTrue(this.predicat.evaluate(mvt));
    }

    /**
     * Méthode en charge de tester l'évaluation OK.
     */
    @Test
    public void testEvaluateOKDispensation() {
        final MvtStock mvt = new DispensationProduit();
        mvt.setType(TypeMvtStock.DISPENSATION);
        Assert.assertTrue(this.predicat.evaluate(mvt));
    }

    /**
     * Méthode en charge de tester l'évaluation OK.
     */
    @Test
    public void testEvaluateOKDispensationGlobale() {
        final MvtStock mvt = new DispensationProduit();
        mvt.setType(TypeMvtStock.DOTATION);
        Assert.assertTrue(this.predicat.evaluate(mvt));
    }

    /**
     * Méthode en charge de tester l'évaluation KO.
     */
    @Test
    public void testEvaluateKO() {
        final MvtStock mvt = new Approvisionnement();
        mvt.setType(TypeMvtStock.APPROVISIONNEMENT);
        Assert.assertFalse(this.predicat.evaluate(mvt));
    }
}
