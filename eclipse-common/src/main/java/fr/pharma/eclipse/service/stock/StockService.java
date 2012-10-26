package fr.pharma.eclipse.service.stock;

import java.util.List;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Interface de service de gestion de stock.
 
 * @version $Revision$ $Date$
 */
public interface StockService
{
    /**
     * Méthode en charge de retourner les lignes de stock concernant une pharmacie, un produit et
     * un conditionnement.
     * @param essai Essai.
     * @param pharmacie Pharmacie.
     * @param produit Produit.
     * @param conditionnement Conditionnement.
     * @param dotations Comptabilise-t-on les dotations ?
     * @return Lignes de stock.
     */
    List<LigneStock> getLinesStock(final Essai essai,
                                   final Pharmacie pharmacie,
                                   final Produit produit,
                                   final Conditionnement conditionnement,
                                   final Boolean dotations);

}
