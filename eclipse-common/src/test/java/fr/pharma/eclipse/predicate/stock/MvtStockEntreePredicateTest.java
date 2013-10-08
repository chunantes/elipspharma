package fr.pharma.eclipse.predicate.stock;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stock.RetourPromoteur;

/**
 * Classe en charge de tester le Predicat de Mouvement de Stock de type Entree.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class MvtStockEntreePredicateTest {
    /**
     * Prédicat à tester.
     */
    private MvtStockEntreePredicate predicat;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.predicat = new MvtStockEntreePredicate();
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
        final MvtStock mvt = new Approvisionnement();
        mvt.setType(TypeMvtStock.APPROVISIONNEMENT);
        Assert.assertTrue(this.predicat.evaluate(mvt));
    }

    /**
     * Méthode en charge de tester l'évaluation KO.
     */
    @Test
    public void testEvaluateKO() {
        final MvtStock mvt = new RetourPromoteur();
        mvt.setType(TypeMvtStock.RETOUR_PROMOTEUR);
        Assert.assertFalse(this.predicat.evaluate(mvt));
    }

}
