package fr.pharma.eclipse.predicate.stock;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stock.RetourPromoteur;
import fr.pharma.eclipse.domain.model.stock.Sortie;

/**
 * Classe en charge de tester le prédicat de sortie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SortiePredicateTest {
    /**
     * Méthode en charge de tester la prédicat KO.
     */
    @Test
    public void testEvaluateKOCond() {
        final Sortie sortie = new Sortie();
        final MvtStock mvt = new RetourPromoteur();
        sortie.setMvtSortie(mvt);
        final Produit produit = new Medicament();
        produit.setId(1L);
        mvt.setProduit(produit);
        final Conditionnement cond = new Conditionnement();
        cond.setId(1L);
        mvt.setConditionnement(cond);

        final SortiePredicate predicate = new SortiePredicate(sortie);

        final Sortie s = new Sortie();
        final MvtStock m = new RetourPromoteur();
        s.setMvtSortie(m);
        final Produit prod = new Medicament();
        prod.setId(1L);
        m.setProduit(prod);
        final Conditionnement c = new Conditionnement();
        c.setId(2L);
        m.setConditionnement(c);

        Assert.assertFalse(predicate.evaluate(s));
    }

    /**
     * Méthode en charge de tester la prédicat KO.
     */
    @Test
    public void testEvaluateKOProd() {
        final Sortie sortie = new Sortie();
        final MvtStock mvt = new RetourPromoteur();
        sortie.setMvtSortie(mvt);
        final Produit produit = new Medicament();
        produit.setId(1L);
        mvt.setProduit(produit);
        final Conditionnement cond = new Conditionnement();
        cond.setId(1L);
        mvt.setConditionnement(cond);

        final SortiePredicate predicate = new SortiePredicate(sortie);

        final Sortie s = new Sortie();
        final MvtStock m = new RetourPromoteur();
        s.setMvtSortie(m);
        final Produit prod = new Medicament();
        prod.setId(2L);
        m.setProduit(prod);
        final Conditionnement c = new Conditionnement();
        c.setId(1L);
        m.setConditionnement(c);

        Assert.assertFalse(predicate.evaluate(s));
    }

    /**
     * Méthode en charge de tester la prédicat OK.
     */
    @Test
    public void testEvaluateOK() {
        final Sortie sortie = new Sortie();
        final MvtStock mvt = new RetourPromoteur();
        sortie.setMvtSortie(mvt);
        final Produit produit = new Medicament();
        produit.setId(1L);
        mvt.setProduit(produit);
        final Conditionnement cond = new Conditionnement();
        cond.setId(1L);
        mvt.setConditionnement(cond);

        final SortiePredicate predicate = new SortiePredicate(sortie);

        final Sortie s = new Sortie();
        final MvtStock m = new RetourPromoteur();
        s.setMvtSortie(m);
        final Produit prod = new Medicament();
        prod.setId(1L);
        m.setProduit(prod);
        final Conditionnement c = new Conditionnement();
        c.setId(1L);
        m.setConditionnement(c);

        Assert.assertTrue(predicate.evaluate(s));
    }

}
