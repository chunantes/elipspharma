package fr.pharma.eclipse.component.dispensation.helper.finder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import fr.pharma.eclipse.component.dispensation.helper.LigneStockFinder;
import fr.pharma.eclipse.component.stock.SortieManager;
import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.stock.DispensationGlobale;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.service.stock.MvtStockService;

/**
 * Implé"mentation pour les essais en Type de dispensation globales.
 
 * @version $Revision$ $Date$
 */
public class GlobaleFinder
    implements LigneStockFinder, Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -1567569242320372638L;

    /**
     * Service de mouvement de stock.
     */
    @Resource(name = "mouvementStockService")
    private MvtStockService<DispensationGlobale> mvtStockService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initLignesStocks(final SortieManager sortieManager)
    {

        final MvtStock mvt = sortieManager.getSortieCurrent().getMvtSortie();

        final MvtStockSearchCriteria crit = new MvtStockSearchCriteria();
        crit.setEssai(mvt.getEssai());
        crit.setTypeMouvement(TypeMvtStock.DOTATION);
        crit.setDispenseNominativement(false);
        crit.setProduit(mvt.getProduit());
        crit.setConditionnement(mvt.getConditionnement());

        final List<DispensationGlobale> dispensations = this.mvtStockService.getAll(crit);
        final Map<String, LigneStock> mapLines = new HashMap<String, LigneStock>();

        for (final DispensationGlobale d : dispensations)
        {
            final LigneStock l = new LigneStock(mvt.getEssai(),
                                                mvt.getPharmacie(),
                                                mvt.getProduit(),
                                                mvt.getConditionnement(),
                                                true);
            l.setNumLot(d.getNumLot());
            l.setNumTraitement(d.getNumTraitement());
            l.setDatePeremption(d.getDatePeremption());
            l.setQteEnStock(d.getQuantite()
                            - d.getQuantiteDispensee());
            l.setDispensationGlobale(d);

            final String key = this.getKeyMvtStock(d);

            final LigneStock ligneStock = mapLines.get(key);
            if (ligneStock != null)
            {
                // Mise à jour de la quantité réelle en stock
                ligneStock.setQteEnStock(ligneStock.getQteEnStock()
                                         + (d.getQuantite() - d.getQuantiteDispensee()));
            }
            else
            {
                mapLines.put(key,
                             l);
            }

        }
        for (final LigneStock l : mapLines.values())
        {
            sortieManager.getSortieCurrent().getLignesStock().add(l);
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
     * Setter pour mvtStockService.
     * @param mvtStockService Le mvtStockService à écrire.
     */
    public void setMvtStockService(final MvtStockService<DispensationGlobale> mvtStockService)
    {
        this.mvtStockService = mvtStockService;
    }
}
