package fr.pharma.eclipse.component.dispensation.helper.finder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.NotPredicate;

import fr.pharma.eclipse.component.dispensation.helper.LigneStockFinder;
import fr.pharma.eclipse.component.stock.SortieManager;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.predicate.GenericPredicate;

/**
 * Implé"mentation pour les essais en Type de dispensation Nominative.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class NominativeFinder implements Serializable, LigneStockFinder {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -6313141963755548237L;

    @Override
    public void initLignesStocks(final SortieManager sortieManager) {

        final MvtStock mvt = sortieManager.getSortieCurrent().getMvtSortie();

        // on recherche les conditionnements du produit correspondant au mode de
        // prescription du produit prescrit.

        @SuppressWarnings("unchecked")
        final List<Conditionnement> conditionnements =
            new ArrayList<Conditionnement>(CollectionUtils.select(mvt.getProduit().getConditionnements(), new GenericPredicate("modePrescription", mvt.getConditionnement()
                    .getModePrescription())));
        sortieManager.getSortieCurrent().setConditionnements(conditionnements);
        // on charge les lignes de stocks des conditionnements ayant le meme
        // mode de prescription
        for (final Conditionnement conditionnement : conditionnements) {

            // Valorisation des lignes de stock possibles pour une sortie
            // booleen sur les dotations déterminé par le contexte de la
            // dispensation.
            final List<LigneStock> lignesStock = sortieManager.getStockService().getLignesStockPharmacie(mvt.getEssai(), mvt.getPharmacie(), mvt.getProduit(), conditionnement);
            // Filtre des stocks en quarantaine.
            CollectionUtils.filter(lignesStock, new NotPredicate(new BeanPropertyValueEqualsPredicate("stockage", "En quarantaine")));

            sortieManager.getSortieCurrent().getLignesStock().addAll(lignesStock);

        }

    }
}
