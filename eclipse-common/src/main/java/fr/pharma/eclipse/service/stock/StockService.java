package fr.pharma.eclipse.service.stock;

import java.util.List;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.enums.TypeDispensation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stock.EtatLigneStock;
import fr.pharma.eclipse.domain.model.stock.EtatStock;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stock.Sortie;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.exception.common.CommonException;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Interface de service de gestion de stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface StockService extends GenericService<LigneStock> {

    /**
     * Recalcul les stock à partir des mouvements si table LigneStock Vide.<br>
     * <br>
     * LigneStock est une nouvelle table qui contient les produits en stock.
     * Cette table résume l'ensemble de flux. Avant l'application calculait à la
     * volée le stock à partir des flux (MvtStock).<br>
     * <br>
     * Calcul est censé être fait une fois au démarrage de l'application.
     */
    List<LigneStock> initialiseTableLigneStock();

    /**
     * Méthode en charge de retourner les lignes de stock concernant une
     * pharmacie, un produit et un conditionnement.
     * @param essai Essai.
     * @param pharmacie Pharmacie.
     * @param produit Produit.
     * @param conditionnement Conditionnement.
     * @param dotations Comptabilise-t-on les dotations ?
     * @return Lignes de stock.
     */
    List<LigneStock> getAllLignesStock(final Essai essai,
                                   final Pharmacie pharmacie,
                                   final Produit produit,
                                   final Conditionnement conditionnement,
                                   final Boolean dotations);

    @Override
    List<LigneStock> getAll(final SearchCriteria criteria);

    void addQuarantaineToStock(final EtatStock etat,
                               final EtatLigneStock ligne);

    /**
     * Mettre à jour la date de péremption partout dans le modèle.
     * <p>
     * La date de péremption (ainsi qu'une historique de changment) est
     * principalement sur l'approvisionnement. Mais la valeur est denormalisé :
     * <ul>
     * <li>dans d'autres MvtStock associés à l'appro
     * <li>dans la LigneStock de l'appro
     * </ul>
     * @param approDto approvisionnement qui contient les données à mettre à
     * jour
     */
    void etendrePeremption(final Approvisionnement approDto);

    /**
     * Méthode en charge de mettre à jour le stock associé à une liste de
     * mouvements.
     * @param mvtStocks Liste de mouvements de stock.
     */
    <MVT extends MvtStock> void update(final List<MVT> mvtStocks);

    /**
     * Met à jour les lignes Stock de la sortie spécifiée en fonction du type de
     * dispensation spécifié.
     * @param typeDispensation
     * @param sortie sortie à mettre à jour
     */
    void initLignesStock(TypeDispensation typeDispensation,
                         Sortie sortie);

    /**
     * Méthode en charge de retourner la clé d'un mouvement de stock. <br />
     * La clé est la concaténation de essai + pharmacie + produit +
     * conditionnement + numLot + numTraitement + approApprouve (est en
     * quarantaine ?) + datePeremption
     * @param mvtStock Mouvement de stock.
     * @return Clé.
     */
    public String getKeyMvtStock(final MvtStock mvtStock,
                                 final boolean datesPeremptionFusionnees);

    /**
     * Récupérer une ligne de stock répondant à la clé fonctionnelle du mvt.
     * Créer une nouvelle ligne si elle n'existe pas.
     * @param mvt mouvement de stock
     * @return ligne de stock associée au mvt
     * @throws CommonException si plusieurs lignes existent pour le mvt
     */
    public LigneStock getLigneStock(final MvtStock mvt);
}
