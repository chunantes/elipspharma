package fr.pharma.eclipse.service.produit;

import java.util.List;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Interface de service de gestion de produit.
 * @author Netapsys
 * @version $Revision$ $Date$
 * @param <PRODUIT> Bean Object Produit.
 */
public interface ProduitService<PRODUIT extends Produit> extends GenericService<PRODUIT> {

    /**
     * Méthode en charge de retourner les produits d'un essai ayant un stockage
     * de défini pour une pharmacie.
     * @param essai Essai.
     * @param pharmacie Pharmacie.
     * @return Liste des produits concernant l'essai et la pharmacie.
     */
    List<Produit> getProduits(final Essai essai,
                              final Pharmacie pharmacie);

    /**
     * Méthode en charge de retourner le stockage associé à un produit et une
     * pharmacie.
     * @param produit Produit.
     * @param pharmacie Pharmacie.
     * @return Stockage.
     */
    Stockage getStockageProduitPharma(final Produit produit,
                                      final Pharmacie pharmacie);

    /**
     * Méthode en charge de retourner les produits d'un essai.
     * @param essai L'essai.
     * @return La liste des produits.
     */
    List<Produit> getProduits(final Essai essai);

    /**
     * Méthode en charge de retourner les preparations d'un essai.
     * @param essai L'essai.
     * @return La liste des produits.
     */
    List<Produit> getPreparations(final Essai essai,
                                  final Pharmacie pharmacie);

    /**
     * Méthode en charge de retourner les produits d'un essai + les
     * préparations.
     * @param essai L'essai.
     * @return La liste des produits.
     */
    List<Produit> getProduitsWithPreparations(final Essai essai,
                                              final Pharmacie pharmacie);

    /**
     * Méthode en charge d'ajouter un élément d'historique de maj sur un
     * produit.
     * @param p Le produit.
     */
    public void addMaj(final Produit p);
}
