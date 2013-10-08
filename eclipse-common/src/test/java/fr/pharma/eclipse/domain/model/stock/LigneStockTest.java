package fr.pharma.eclipse.domain.model.stock;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

public class LigneStockTest {

    /**
     * Méthode en charge de tester la génération de clé pour une ligne de stock.
     */
    @Test
    public void testGetKeyLigneStock() {
        final LigneStock ligneStock = new LigneStock(this.getEssai(), this.getPharmacie(), this.getProduit(), this.getConditionnement(), Boolean.TRUE);
        ligneStock.setNumLot("numLot1");
        ligneStock.setNumTraitement("numTraitement");
        Assert.assertEquals("1111numLot1numTraitementtrue", ligneStock.getKeyLigneStock(false));
    }

    /**
     * Construction d'un objet Essai de test.
     * @return Essai.
     */
    private Essai getEssai() {
        final Essai essai = new Essai();
        essai.setId(1L);
        return essai;
    }

    /**
     * Construction d'un objet Pharmacie de test.
     * @return Pharmacie.
     */
    private Pharmacie getPharmacie() {
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);
        return pharmacie;
    }

    /**
     * Construction d'un objet Produit de test.
     * @return Produit.
     */
    private Produit getProduit() {
        final Produit produit = new Medicament();
        produit.setId(1L);
        return produit;
    }

    /**
     * Construction d'un objet Conditionnement de test.
     * @return Conditionnement.
     */
    private Conditionnement getConditionnement() {
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setId(1L);
        return conditionnement;
    }

}
