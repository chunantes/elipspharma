package fr.pharma.eclipse.service.stock.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stock.DispensationGlobale;
import fr.pharma.eclipse.domain.model.stock.EtatLigneStock;
import fr.pharma.eclipse.domain.model.stock.EtatStock;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stock.MvtStockService;
import fr.pharma.eclipse.service.stock.StockService;

/**
 * Classe d'implémentation du service de gestion de stock.
 
 * @version $Revision$ $Date$
 */
public class StockServiceImpl
    implements StockService
{

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
     * Constante de stockage en quarantaine.
     */
    private static final String EN_QUARANTAINE = "En quarantaine";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LigneStock> getLinesStock(final Essai essai,
                                          final Pharmacie pharmacie,
                                          final Produit produit,
                                          final Conditionnement conditionnement,
                                          final Boolean dotations)
    {
        final Map<String, LigneStock> mapLines = new HashMap<String, LigneStock>();

        // Construction des lignes de stock à partir des entrées
        this.buildFromMvtTypeEntree(essai,
                                    pharmacie,
                                    produit,
                                    conditionnement,
                                    mapLines);

        // Mise à jour des lignes de stock à partir des sorties
        this.updateFromMvtTypeSortie(essai,
                                     pharmacie,
                                     produit,
                                     conditionnement,
                                     mapLines);

        // Traitement des dispensations globales (non sorties du stock mais non visible sauf à la
        // dispensation de ces produits).
        if (dotations)
        {
            this.updateFromDispensationGlobales(essai,
                                                pharmacie,
                                                produit,
                                                conditionnement,
                                                mapLines);
        }

        // Récupération des lignes dont la quantité est différente de 0
        final Map<String, LigneStock> result = this.getLignesStockQteNotZero(mapLines);

        return new ArrayList<LigneStock>(result.values());
    }

    /**
     * Méthode en charge d'initialiser les lignes de stock à partir des mouvements de stock de
     * type Dispensation globales.
     * @param essai L'essai .
     * @param pharmacie La pharmacie.
     * @param produit Le produit.
     * @param conditionnement Le conditionnement.
     * @param mapLines Les lignes contenants les résultats.
     */
    private void updateFromDispensationGlobales(final Essai essai,
                                                final Pharmacie pharmacie,
                                                final Produit produit,
                                                final Conditionnement conditionnement,
                                                final Map<String, LigneStock> mapLines)
    {
        final MvtStockSearchCriteria critEntree = new MvtStockSearchCriteria();
        critEntree.setEssai(essai);
        critEntree.setPharmacie(pharmacie);
        critEntree.setProduit(produit);
        critEntree.setConditionnement(conditionnement);
        critEntree.setTypeMouvement(TypeMvtStock.DOTATION);

        final List<MvtStock> dispGlobales = this.mvtStockService.getAll(critEntree);

        for (final MvtStock disp : dispGlobales)
        {
            final DispensationGlobale dotation = (DispensationGlobale) disp;
            final String key = this.getKeyMvtStock(dotation);
            final LigneStock ligneStock = mapLines.get(key);
            // Mise à jour de la quantité réelle en stock
            ligneStock
                    .setQteEnStock(ligneStock.getQteEnStock()
                                   - (dotation.getQuantite() - dotation.getQuantiteDispensee()));

        }

    }
    /**
     * Méthode en charge de retourner dans la map des lignes de stock uniquement les lignes qui
     * ont une quantité différente de 0.
     * @param mapLines Map des lignes à traiter.
     * @return Map des lignes de stock dont la quantité est différente de 0.
     */
    public Map<String, LigneStock> getLignesStockQteNotZero(final Map<String, LigneStock> mapLines)
    {
        final Map<String, LigneStock> result = new TreeMap<String, LigneStock>();

        for (final Map.Entry<String, LigneStock> entry : mapLines.entrySet())
        {
            if (entry.getValue().getQteEnStock() > 0)
            {
                result.put(entry.getKey(),
                           entry.getValue());
            }
        }

        return result;
    }

    /**
     * Méthode en charge d'initialiser les lignes de stock à partir des mouvements de stock de
     * type Entree.
     * @param essai Essai.
     * @param pharmacie Pharmacie.
     * @param produit Produit.
     * @param conditionnement Conditionnement.
     * @param mapLines Map des lignes de stock à construire.
     */
    private void buildFromMvtTypeEntree(final Essai essai,
                                        final Pharmacie pharmacie,
                                        final Produit produit,
                                        final Conditionnement conditionnement,
                                        final Map<String, LigneStock> mapLines)
    {
        // Récupération des mouvements de type Entree qui concernent l'essai, la
        // pharmacie, le produit et le conditionnement
        final MvtStockSearchCriteria critEntree = new MvtStockSearchCriteria();
        critEntree.setEssai(essai);
        critEntree.setPharmacie(pharmacie);
        critEntree.setProduit(produit);
        critEntree.setConditionnement(conditionnement);
        critEntree.setTypesMouvement(TypeMvtStock.valuesEntree());

        final List<MvtStock> appros = this.mvtStockService.getAll(critEntree);

        for (final MvtStock mouvementStock : appros)
        {
            final Approvisionnement appro = (Approvisionnement) mouvementStock;
            final LigneStock ligneStock = new LigneStock(essai,
                                                         pharmacie,
                                                         produit,
                                                         conditionnement,
                                                         appro.getApproApprouve());
            ligneStock.setNumLot(appro.getNumLot());
            ligneStock.setNumTraitement(appro.getNumTraitement());
            ligneStock.setDatePeremption(appro.getDatePeremption());
            ligneStock.setQteEnStock(appro.getQuantite());

            if (appro.getApproApprouve())
            {
                final Produit prod = this.produitService.reattach(produit);
                final Stockage stockage = this.produitService.getStockageProduitPharma(prod,
                                                                                       pharmacie);
                ligneStock.setStockage(stockage.getNomComplet());
            }
            else
            {
                ligneStock.setStockage(StockServiceImpl.EN_QUARANTAINE);
            }

            final String key = this.getKeyLigneStock(ligneStock);

            // Une entrée existe déjà => cumul des quantités saisies
            if (mapLines.get(key) != null)
            {
                final LigneStock l = mapLines.get(key);
                l.setQteEnStock(l.getQteEnStock()
                                + ligneStock.getQteEnStock());
            }
            else
            {
                mapLines.put(key,
                             ligneStock);
            }
        }
    }

    /**
     * Méthode en charge de mettre à jour les lignes de stock à partir des mouvements de stock de
     * type Sortie. La mise à jour concerne la quantité.
     * @param essai Essai.
     * @param pharmacie Pharmacie.
     * @param produit Produit.
     * @param conditionnement Conditionnement.
     * @param mapLines Map des lignes de stock à mettre à jour.
     */
    private void updateFromMvtTypeSortie(final Essai essai,
                                         final Pharmacie pharmacie,
                                         final Produit produit,
                                         final Conditionnement conditionnement,
                                         final Map<String, LigneStock> mapLines)
    {
        // Récupération des mouvements de type Sortie qui concernant l'essai, la pharmacie, le
        // produit et le conditionnement
        final MvtStockSearchCriteria critSortie = new MvtStockSearchCriteria();
        critSortie.setTypesMouvement(TypeMvtStock.valuesAllSortie());
        critSortie.setEssai(essai);
        critSortie.setPharmacie(pharmacie);
        critSortie.setProduit(produit);
        critSortie.setConditionnement(conditionnement);

        final List<MvtStock> sorties = this.mvtStockService.getAll(critSortie);

        // on ne traite pas ici les dispensations globales car elles ne sortent pas du stocks.
        CollectionUtils.filter(sorties,
                               new Predicate() {

                                   @Override
                                   public boolean evaluate(final Object object)
                                   {
                                       return !((MvtStock) object)
                                               .getType()
                                               .equals(TypeMvtStock.DOTATION);
                                   }
                               });

        for (final MvtStock mvtStock : sorties)
        {
            final String key = this.getKeyMvtStock(mvtStock);
            final LigneStock ligneStock = mapLines.get(key);
            // Mise à jour de la quantité réelle en stock
            ligneStock.setQteEnStock(ligneStock.getQteEnStock()
                                     - mvtStock.getQuantite());
        }
    }

    /**
     * Méthode en charge de retourner la clé d'un mouvement de stock. <br />
     * La clé est la concaténation de essai + pharmacie + produit + conditionnement + numLot +
     * numTraitement.
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
                .append(mvtStock.getNumLot())
                .append(mvtStock.getNumTraitement())
                .append(mvtStock.getApproApprouve());
        return sb.toString();
    }
    /**
     * Méthode en charge de retourner la clé d'une ligne de stock. <br />
     * La clé est la concaténation de essai + pharmacie + produit + conditionnement + numLot +
     * numTraitement.
     * @param ligneStock Ligne de stock.
     * @return Clé.
     */
    public String getKeyLigneStock(final LigneStock ligneStock)
    {
        final StringBuilder sb = new StringBuilder();
        sb.append(ligneStock.getEssai().getId())
                .append(ligneStock.getPharmacie().getId())
                .append(ligneStock.getProduit().getId())
                .append(ligneStock.getConditionnement().getId())
                .append(ligneStock.getNumLot())
                .append(ligneStock.getNumTraitement())
                .append(ligneStock.getApproApprouve());
        return sb.toString();
    }

    public void addQuarantaineToStock(final EtatStock etat,
                                      final EtatLigneStock ligne)
    {
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        criteria.setConditionnement(etat.getConditionnement());
        criteria.setProduit(etat.getProduit());
        criteria.setEssai(etat.getEssai());
        criteria.setNumLot(ligne.getNumLot());

        for (final MvtStock mvt : this.mvtStockService.getAll(criteria))
        {
            mvt.setApproApprouve(true);
            this.mvtStockService.save(mvt);
        }

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
