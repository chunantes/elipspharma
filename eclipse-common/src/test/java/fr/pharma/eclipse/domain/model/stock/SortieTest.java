package fr.pharma.eclipse.domain.model.stock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Classe en charge de tester les méthodes présentes dans le bean Sortie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SortieTest {
    /**
     * Essai mocké.
     */
    private final Essai essai = Mockito.mock(Essai.class);

    /**
     * Pharmacie mockée.
     */
    private final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);

    /**
     * Produit mocké.
     */
    private final Produit produit = Mockito.mock(Produit.class);

    /**
     * Conditionnement mocké.
     */
    private final Conditionnement conditionnement = Mockito.mock(Conditionnement.class);

    /**
     * Méthode en charge de tester le cumul de la quantité de sortie.
     */
    @Test
    public void testGetQteCumulSortie() {
        final List<LigneStock> lignes = new ArrayList<LigneStock>();
        final LigneStock ligneStock1 = new LigneStock(this.essai, this.pharmacie, this.produit, this.conditionnement, Boolean.TRUE);
        ligneStock1.setQteASortir(2);
        final LigneStock ligneStock2 = new LigneStock(this.essai, this.pharmacie, this.produit, this.conditionnement, Boolean.TRUE);
        final LigneStock ligneStock3 = new LigneStock(this.essai, this.pharmacie, this.produit, this.conditionnement, Boolean.TRUE);
        ligneStock3.setQteASortir(1);
        lignes.add(ligneStock1);
        lignes.add(ligneStock2);
        lignes.add(ligneStock3);
        final Sortie sortie = new Sortie();
        sortie.setLignesStock(lignes);
        Assert.assertEquals(2 + 1, sortie.getQteCumulSortie().intValue());

    }

    /**
     * Méthode en charge de tester la récupération des lignes de stock
     * complétées.
     */
    @Test
    public void testGetLignesStockCompletees() {
        final List<LigneStock> lignes = new ArrayList<LigneStock>();
        final LigneStock ligneStock1 = new LigneStock(this.essai, this.pharmacie, this.produit, this.conditionnement, Boolean.TRUE);
        ligneStock1.setQteASortir(2);
        final LigneStock ligneStock2 = new LigneStock(this.essai, this.pharmacie, this.produit, this.conditionnement, Boolean.TRUE);
        final LigneStock ligneStock3 = new LigneStock(this.essai, this.pharmacie, this.produit, this.conditionnement, Boolean.TRUE);
        ligneStock3.setQteASortir(1);
        lignes.add(ligneStock1);
        lignes.add(ligneStock2);
        lignes.add(ligneStock3);
        final Sortie sortie = new Sortie();
        sortie.setLignesStock(lignes);
        Assert.assertEquals(2, sortie.getLignesStockCompletees().size());
    }

    /**
     * Test de la méthode filterByNumTraitement.
     */
    @Test
    public void testFiltrerLignesStockParNumeroTraitement() {
        // Prepare
        final String numTraitement = "num";

        final LigneStock l1 = new LigneStock(null, null, null, null, null);
        l1.setNumTraitement(numTraitement);

        final LigneStock l2 = new LigneStock(null, null, null, null, null);
        l2.setNumTraitement(numTraitement + "2");

        final List<LigneStock> lignes = new ArrayList<LigneStock>();
        lignes.add(l1);
        lignes.add(l2);

        final Sortie sortie = new Sortie();
        sortie.setLignesStock(lignes);

        // Test
        sortie.filtrerLignesStockParNumeroTraitement(numTraitement);

        // Verifier
        Assert.assertEquals(1, sortie.getLignesStock().size());
    }

}
