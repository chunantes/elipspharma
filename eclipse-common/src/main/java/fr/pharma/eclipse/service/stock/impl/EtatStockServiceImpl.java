package fr.pharma.eclipse.service.stock.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import fr.pharma.eclipse.comparator.stock.EtatStockComparator;
import fr.pharma.eclipse.domain.criteria.stock.StockSearchCriteria;
import fr.pharma.eclipse.domain.model.stock.EtatLigneStock;
import fr.pharma.eclipse.domain.model.stock.EtatStock;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.service.stock.EtatStockService;
import fr.pharma.eclipse.service.stock.StockService;

/**
 * Classe d'implémentation du service d'état de stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EtatStockServiceImpl implements EtatStockService {

    /**
     * Service de gestion des mouvements de stock.
     */
    @Resource(name = "stockService")
    private StockService stockService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EtatStock> getLinesEtatStock(final StockSearchCriteria criteria) {
        return this.getLinesEtatStock(criteria, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EtatStock> getLinesEtatStock(final StockSearchCriteria criteria,
                                             final boolean datesPeremptionFusionnees) {
        final List<LigneStock> lignes = this.stockService.getAll(criteria);
        final List<EtatStock> result = new ArrayList<EtatStock>(this.convert(lignes, datesPeremptionFusionnees));

        // Tri par essai + pharmacie + produit + conditionnement
        Collections.sort(result, new EtatStockComparator());
        return result;
    }

    private List<EtatStock> convert(final List<LigneStock> lignesStock,
                                    final boolean datesPeremptionFusionnees) {
        final Map<String, EtatStock> results = new HashMap<String, EtatStock>();
        for (final LigneStock ligneStock : lignesStock) {

            ligneStock.setDotation(false);
            final EtatStock etat;

            final String keyStock = ligneStock.getKeyStock();

            if (results.containsKey(keyStock)) {
                etat = results.get(keyStock);
                etat.setQteEnStock(etat.getQteEnStock() + ligneStock.getQteEnStock());
            } else {
                etat = new EtatStock(ligneStock.getEssai(), ligneStock.getPharmacie(), ligneStock.getProduit(), ligneStock.getConditionnement(), !ligneStock.getApproApprouve());
                etat.setQteEnStock(ligneStock.getQteEnStock());
                etat.setStockage(ligneStock.getStockage());
                results.put(keyStock, etat);
            }

            if (etat.getEtatsLignesStock().containsKey(ligneStock.getKeyLigneStock(datesPeremptionFusionnees))) {
                final EtatLigneStock etatLigneSotck = etat.getEtatsLignesStock().get(ligneStock.getKeyLigneStock(datesPeremptionFusionnees));
                etatLigneSotck.setQteEnStock(etatLigneSotck.getQteEnStock() + ligneStock.getQteEnStock());
                ligneStock.setQtePharmacie(Integer.SIZE);
            } else {
                etat.getEtatsLignesStock()
                        .put(ligneStock.getKeyLigneStock(datesPeremptionFusionnees),
                             new EtatLigneStock(ligneStock.getNumLot(), ligneStock.getNumTraitement(), ligneStock.getDatePeremption(), ligneStock.getQteEnStock()));
            }
        }
        return new ArrayList<EtatStock>(results.values());
    }

    /**
     * Setter pour stockService.
     * @param mvtStockService Le mvtStockService à écrire.
     */
    public void setStockService(final StockService stockService) {
        this.stockService = stockService;
    }

}
