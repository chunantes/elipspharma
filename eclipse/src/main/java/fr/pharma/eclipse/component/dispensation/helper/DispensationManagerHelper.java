package fr.pharma.eclipse.component.dispensation.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.component.stock.SortieManager;
import fr.pharma.eclipse.domain.enums.TypeDispensation;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stock.Sortie;
import fr.pharma.eclipse.predicate.GenericPredicate;

/**
 * Helper du manager gérant les dispensations.
 
 * @version $Revision$ $Date$
 */
public class DispensationManagerHelper
    implements Serializable
{
    /**
     * Serial Version ID.
     */
    private static final long serialVersionUID = -2249510007647982404L;

    /**
     * Stratégie de récupération des lignes de stocks en fonction du type de dispensation.
     */
    private Map<TypeDispensation, LigneStockFinder> strategieGetLignesStock;

    /**
     * Méthode en charge d'intialiser les conditionnements en fonction du mode de prescription, du
     * produit prescrit.
     * @param sortieManager Manager de sortie.
     * @param produitPrescrit Produit prescrit.
     */
    public void initConditionnements(final SortieManager sortieManager,
                                     final ProduitPrescrit produitPrescrit)
    {
        final Produit produit =
            sortieManager.getProduitService().reattach(produitPrescrit.getProduit());

        final TypeDispensation type =
            produitPrescrit
                    .getPrescription()
                    .getEssai()
                    .getDetailDonneesPharma()
                    .getInfosDispensations()
                    .getTypeDispensation();

        final Sortie sortieCurrent = sortieManager.getSortieCurrent();

        // on initialise le mouvement.
        final MvtStock mvt = sortieCurrent.getMvtSortie();
        mvt.setProduit(produit);
        mvt.setEssai(produitPrescrit.getPrescription().getEssai());
        mvt.setConditionnement(produitPrescrit.getConditionnement());

        // les sorties.
        sortieCurrent.setLignesStock(new ArrayList<LigneStock>());

        this.strategieGetLignesStock.get(type).initLignesStocks(sortieManager);

        // on filtre les lignes selon le numéro de traitement (s'il est setté).
        if (!StringUtils.isEmpty(produitPrescrit.getNumTraitement()))
        {
            sortieCurrent.filtrerLignesStockParNumeroTraitement(produitPrescrit
                    .getNumTraitement());
        }
    }

    /**
     * Méthode en charge d'initialiser la map de DispensationProduits pour l'affichage de la liste
     * des sorties pour un produit.
     * @param dispensation La dispensation courante.
     * @param map La map de DispensationProduit
     */
    public void initProduitsDispensesForConsult(final Dispensation dispensation,
                                                final Map<Long, List<Sortie>> map)
    {
        for (final DispensationProduit d : dispensation.getDispensationsProduit())
        {
            final Long idProduitPrescrit = d.getProduitPrescrit().getId();

            if (!map.containsKey(idProduitPrescrit))
            {
                map.put(idProduitPrescrit,
                        new ArrayList<Sortie>());
            }

            this.prepareSortie(map.get(idProduitPrescrit),
                               d);
        }
    }

    /**
     * Methode en charge de mettre à jour ou de créer une sortie en fonction de la dispensation.
     * @param liste La liste de sortie.
     * @param dispensation La dispensation courante.
     */
    private void prepareSortie(final List<Sortie> liste,
                               final DispensationProduit dispensation)
    {

        Sortie sortie =
            (Sortie) CollectionUtils.find(liste,
                                          new GenericPredicate("mvtSortie.conditionnement.id",
                                                               dispensation
                                                                       .getConditionnement()
                                                                       .getId()));

        if (null == sortie)
        {
            sortie = new Sortie();
            sortie.setLignesStock(new ArrayList<LigneStock>());
            sortie.setMvtSortie(dispensation);
            liste.add(sortie);
        }
        final LigneStock l = new LigneStock(dispensation.getEssai(),
                                            dispensation.getPharmacie(),
                                            dispensation.getProduit(),
                                            dispensation.getConditionnement(),
                                            dispensation.getApproApprouve());
        l.setNumLot(dispensation.getNumLot());
        l.setNumTraitement(dispensation.getNumTraitement());
        l.setQteASortir(dispensation.getQuantite());
        sortie.getLignesStock().add(l);
    }

    /**
     * Setter pour strategy.
     * @param strategy Le strategy à écrire.
     */
    public void setStrategy(final Map<TypeDispensation, LigneStockFinder> strategy)
    {
        this.strategieGetLignesStock = strategy;
    }
}
