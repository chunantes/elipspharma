package fr.pharma.eclipse.predicate.stock;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;

/**
 * Predicat sur les mouvements de stock (Récupération des mouvements de type
 * SORTIE / DISPENSATION / DOTATION).
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class MvtStockSortiePredicate implements Predicate, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 4423458596656880712L;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate(final Object object) {
        final MvtStock mvt = (MvtStock) object;
        return Arrays.asList(TypeMvtStock.ALL_SORTIES).contains(mvt.getType());
    }
}
