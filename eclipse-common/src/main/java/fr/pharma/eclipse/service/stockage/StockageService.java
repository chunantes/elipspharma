package fr.pharma.eclipse.service.stockage;

import java.util.SortedSet;

import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Interface de service de gestion de stockage.
 
 * @version $Revision$ $Date$
 */
public interface StockageService
    extends GenericService<Stockage>
{

    /**
     * Méthode en charge de retourner le nom complet d'un stockage (inclusion du nom de ses
     * parents).
     * @param stockage Stockage.
     * @return Nom du stockage.
     */
    String getNomComplet(final Stockage stockage);

    /**
     * Méthode en charge de tester la présence d'un stockage dans une liste de stockages.
     * @param stockage Stockage.
     * @param stockages Liste de stockages.
     * @return Résultat du test.
     */
    Boolean isStockageAlreadyPresent(final Stockage stockage,
                                     final SortedSet<Stockage> stockages);

    /**
     * Verifie si le nom de stockage est utilise par un autre stockage de même niveau, de la pharmacie.
     * @param stockage
     * @param pharmacie
     * @return booleen
     */
    Boolean isNomStockageUtiliseParAutreStockageDeMemeNiveau(final Stockage stockage,
                                                 Pharmacie pharmacie);
}
