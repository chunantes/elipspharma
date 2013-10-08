package fr.pharma.eclipse.component.dispensation.helper.finder;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import fr.pharma.eclipse.component.dispensation.helper.LigneStockFinder;
import fr.pharma.eclipse.component.stock.SortieManager;
import fr.pharma.eclipse.domain.criteria.stock.StockSearchCriteria;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.service.stock.StockService;

/**
 * Implé"mentation pour les essais en Type de dispensation globales.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GlobaleFinder implements LigneStockFinder, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -1567569242320372638L;

    @Resource(name = "stockService")
    private StockService stockService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initLignesStocks(final SortieManager sortieManager) {

        final MvtStock mvt = sortieManager.getSortieCurrent().getMvtSortie();
        final StockSearchCriteria criteria = new StockSearchCriteria();
        criteria.setProduit(mvt.getProduit());
        criteria.setPharmacie(mvt.getPharmacie());
        criteria.setEssai(mvt.getEssai());
        criteria.setConditionnement(mvt.getConditionnement());
        criteria.setNotNullQteDispensationGlobal(Boolean.TRUE);

        final List<LigneStock> lignesStockBDD = this.stockService.getAll(criteria);
        for (final LigneStock ligneStock : lignesStockBDD) {
            ligneStock.setDotation(Boolean.TRUE);
        }
        sortieManager.getSortieCurrent().getLignesStock().addAll(lignesStockBDD);
    }

    /**
     * Méthode en charge de retourner la clé d'un mouvement de stock. <br />
     * La clé est la concaténation de essai + pharmacie + produit +
     * conditionnement + numLot + numTraitement.
     * @param mvtStock Mouvement de stock.
     * @return Clé.
     */
    public String getKeyMvtStock(final MvtStock mvtStock) {
        final StringBuilder sb = new StringBuilder();
        sb.append(mvtStock.getEssai().getId()).append(mvtStock.getPharmacie().getId()).append(mvtStock.getProduit().getId()).append(mvtStock.getConditionnement().getId())
                .append(mvtStock.getNumLot()).append(mvtStock.getNumTraitement()).append(mvtStock.getApproApprouve());
        return sb.toString();
    }

    /**
     * Setter pour stockService.
     * @param stockService Le stockService à écrire.
     */
    public void setStockService(final StockService stockService) {
        this.stockService = stockService;
    }
}
