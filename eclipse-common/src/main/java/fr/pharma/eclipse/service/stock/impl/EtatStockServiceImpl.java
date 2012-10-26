package fr.pharma.eclipse.service.stock.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.comparator.stock.EtatStockComparator;
import fr.pharma.eclipse.domain.criteria.stock.StockSearchCriteria;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stock.DispensationGlobale;
import fr.pharma.eclipse.domain.model.stock.EtatLigneStock;
import fr.pharma.eclipse.domain.model.stock.EtatStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.predicate.stock.MvtStockEntreePredicate;
import fr.pharma.eclipse.predicate.stock.MvtStockSortiePredicate;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stock.EtatStockService;
import fr.pharma.eclipse.service.stock.MvtStockService;

/**
 * Classe d'implémentation du service d'état de stock.
 
 * @version $Revision$ $Date$
 */
public class EtatStockServiceImpl
    implements EtatStockService
{
    private final Logger log = LoggerFactory.getLogger(EtatStockServiceImpl.class);

    /**
     * Service de gestion des mouvements de stock.
     */
    @Resource(name = "mouvementStockService")
    private MvtStockService<MvtStock> mvtStockService;

    /**
     * Service de gestion des produits.
     */
    @Resource(name = "produitService")
    private ProduitService<Produit> produitService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EtatStock> getLinesEtatStock(final StockSearchCriteria criteria)
    {
        // Récupération des mouvements par rapport au critère de recherche
        final List<MvtStock> mvts = this.mvtStockService.getAll(criteria);

        final Map<String, EtatStock> mapLines = new TreeMap<String, EtatStock>();

        // Initialisation des données de la Map avec les entrées
        this.buildFromMvtTypeEntree(mvts,
                                    mapLines);

        // Mise à jour des données de la Map avec les sorties
        this.updateFromMvtTypeSortie(mvts,
                                     mapLines);

        this.updateFromDispensationGlobales(mvts,
                                            mapLines);

        final List<EtatStock> result = new ArrayList<EtatStock>(mapLines.values());

        // Tri par essai + pharmacie + produit + conditionnement
        Collections.sort(result,
                         new EtatStockComparator());

        return result;
    }
    /**
     * Méthode en charge d'initialiser les lignes de stock à partir des mouvements de stock de
     * type Dispensation globales.
     * @param essai L'essai .
     * @param pharmacie La pharmacie.
     * @param mapLines Les lignes contenants les résultats.
     */
    private void updateFromDispensationGlobales(final List<MvtStock> mvts,
                                                final Map<String, EtatStock> mapLines)
    {

        CollectionUtils.filter(mvts,
                               new Predicate() {

                                   @Override
                                   public boolean evaluate(final Object object)
                                   {
                                       return ((MvtStock) object)
                                               .getType()
                                               .equals(TypeMvtStock.DOTATION);
                                   }
                               });

        for (final MvtStock disp : mvts)
        {
            final DispensationGlobale dotation = (DispensationGlobale) disp;
            final String key = this.getKeyMvtStock(dotation);

            final EtatStock etatStock = mapLines.get(key);
            etatStock.setQteEnStock(etatStock.getQteEnStock()
                                    - (dotation.getQuantite() - dotation.getQuantiteDispensee()));

            // Mise à jour de la ligne correspondante.
            final EtatLigneStock ligne =
                etatStock.getEtatsLignesStock().get(this.getKeyEtatStock(dotation));
            ligne.setQteEnStock(ligne.getQteEnStock()
                                - (dotation.getQuantite() - dotation.getQuantiteDispensee()));

        }

    }

    /**
     * Méthode en charge de mettre à jour les données de la Map à partir des mouvements de type
     * sortie.
     * @param mvts Liste des mouvements issus de la recherche.
     * @param mapLines Map de lignes d'état de stock à construire.
     */
    private void updateFromMvtTypeSortie(final List<MvtStock> mvts,
                                         final Map<String, EtatStock> mapLines)
    {
        // Récupération des mouvements de type Sortie
        final List<MvtStock> mvtsSortie = new ArrayList<MvtStock>(mvts);
        CollectionUtils.filter(mvtsSortie,
                               new MvtStockSortiePredicate());

        // on ne traite pas ici les dispensations globales car elles ne sortent pas du stocks.
        CollectionUtils.filter(mvtsSortie,
                               new Predicate() {

                                   @Override
                                   public boolean evaluate(final Object object)
                                   {
                                       return !((MvtStock) object)
                                               .getType()
                                               .equals(TypeMvtStock.DOTATION);
                                   }
                               });

        for (final MvtStock mvtSortie : mvtsSortie)
        {

            final String key = this.getKeyMvtStock(mvtSortie);
            final EtatStock etatStock = mapLines.get(key);
            etatStock.setQteEnStock(etatStock.getQteEnStock()
                                    - mvtSortie.getQuantite());

            // Mise à jour de la ligne correspondante.
            final EtatLigneStock ligne =
                etatStock.getEtatsLignesStock().get(this.getKeyEtatStock(mvtSortie));
            ligne.setQteEnStock(ligne.getQteEnStock()
                                - mvtSortie.getQuantite());
        }

    }

    /**
     * Méthode en charge de construire la Map des données à partir des mouvements de type entrée.
     * @param mvts Liste des mouvements issus de la recherche.
     * @param mapLines Map de lignes d'état de stock à construire.
     */
    private void buildFromMvtTypeEntree(final List<MvtStock> mvts,
                                        final Map<String, EtatStock> mapLines)
    {
        // Récupération des mouvements de type Entree
        final List<MvtStock> mvtsEntree = new ArrayList<MvtStock>(mvts);
        CollectionUtils.filter(mvtsEntree,
                               new MvtStockEntreePredicate());

        for (final MvtStock mvtEntree : mvtsEntree)
        {
            // Prise en compte de l'entrée si l'approvisionnement a été approuvé (pas de
            // quarantaine)

            final String key = this.getKeyMvtStock(mvtEntree);
            final EtatStock etatStock = mapLines.get(key);

            // Une entrée existe déjà dans la Map
            if (etatStock != null)
            {
                // Mise à jour de la quantité
                etatStock.setQteEnStock(etatStock.getQteEnStock()
                                        + mvtEntree.getQuantite());

                // Si aucun etat de ligne n'existe on l'ajoute.
                if (!etatStock.getEtatsLignesStock().containsKey(this.getKeyEtatStock(mvtEntree)))
                {
                    etatStock
                            .getEtatsLignesStock()
                            .put(this.getKeyEtatStock(mvtEntree),
                                 new EtatLigneStock(mvtEntree.getNumLot(),
                                                    mvtEntree.getNumTraitement(),
                                                    ((Approvisionnement) mvtEntree)
                                                            .getDatePeremption(),
                                                    mvtEntree.getQuantite()));
                }

                // sinon on le met à jours.
                else
                {
                    final EtatLigneStock ligne =
                        etatStock.getEtatsLignesStock().get(this.getKeyEtatStock(mvtEntree));
                    etatStock
                            .getEtatsLignesStock()
                            .get(this.getKeyEtatStock(mvtEntree))
                            .setQteEnStock(ligne.getQteEnStock()
                                           + mvtEntree.getQuantite());
                }
            }
            else
            {
                final EtatStock newEtatStock = new EtatStock(mvtEntree.getEssai(),
                                                             mvtEntree.getPharmacie(),
                                                             mvtEntree.getProduit(),
                                                             mvtEntree.getConditionnement(),
                                                             !mvtEntree.getApproApprouve());
                newEtatStock.setQteEnStock(mvtEntree.getQuantite());
                final Produit prod = mvtEntree.getProduit();
                final Stockage stockage =
                    this.produitService.getStockageProduitPharma(prod,
                                                                 mvtEntree.getPharmacie());
                newEtatStock.setStockage(stockage.getNomComplet());
                mapLines.put(key,
                             newEtatStock);
                newEtatStock
                        .getEtatsLignesStock()
                        .put(this.getKeyEtatStock(mvtEntree),
                             new EtatLigneStock(mvtEntree.getNumLot(),
                                                mvtEntree.getNumTraitement(),
                                                ((Approvisionnement) mvtEntree)
                                                        .getDatePeremption(),
                                                mvtEntree.getQuantite()));
            }
        }
    }
    /**
     * Méthode en charge de retourner la clé d'un mouvement de stock côté état. <br />
     * La clé est la concaténation de essai + pharmacie + produit + conditionnement.
     * @param mvtStock Mouvement de stock.
     * @return Clé.
     */
    public String getKeyMvtStock(final MvtStock mvtStock)
    {
        final StringBuilder sb = new StringBuilder();
        sb.append(mvtStock.getEssai().getId())
                .append(mvtStock.getPharmacie().getId())
                .append(mvtStock.getProduit().getId())
                .append(mvtStock.getConditionnement().getId())
                .append(mvtStock.getApproApprouve());
        return sb.toString();
    }

    /**
     * Méthode en charge de retourner la clé d'une ligne d'état de stock. <br />
     * La clé est la concaténation de essai + pharmacie + produit + conditionnement.
     * @param mvt Ligne de stock.
     * @return Clé.
     */
    public String getKeyEtatStock(final MvtStock mvt)
    {
        final StringBuilder sb = new StringBuilder();
        sb.append(mvt.getNumLot()).append(mvt.getNumTraitement());
        return sb.toString();
    }
    /**
     * Setter pour mvtStockService.
     * @param mvtStockService Le mvtStockService à écrire.
     */
    public void setMvtStockService(final MvtStockService<MvtStock> mvtStockService)
    {
        this.mvtStockService = mvtStockService;
    }

    /**
     * Setter pour produitService.
     * @param produitService Le produitService à écrire.
     */
    public void setProduitService(final ProduitService<Produit> produitService)
    {
        this.produitService = produitService;
    }
}
