package fr.pharma.eclipse.utils;

import fr.pharma.eclipse.domain.model.stockage.Stockage;

/**
 * Classe utilitaire pour les jeux de tests de type Stockage.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class StockageUtils {
    /**
     * Constructeur.
     */
    private StockageUtils() {
        super();
    }

    /**
     * Méthode en charge de créer un stockage de test.
     * @param id Identifiant.
     * @param nom Nom du stockage.
     * @return Un nouveau Stockage.
     */
    public static Stockage makeStockage(final long id,
                                        final String nom) {
        return StockageUtils.makeStockage(id, nom, null);
    }

    /**
     * Méthode en charge de créer un stockage de test.
     * @param id Identifiant.
     * @param nom Nom du stockage.
     * @param parent Stockage parent.
     * @return Un nouveau Stockage.
     */
    public static Stockage makeStockage(final long id,
                                        final String nom,
                                        final Stockage parent) {
        final Stockage stockage = new Stockage();
        stockage.setId(id);
        stockage.setNom(nom);
        stockage.setParent(parent);
        return stockage;
    }
}
