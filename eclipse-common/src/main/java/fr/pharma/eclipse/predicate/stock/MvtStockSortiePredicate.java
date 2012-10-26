package fr.pharma.eclipse.predicate.stock;

import java.io.Serializable;

import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.enums.stock.CategorieMouvement;
import fr.pharma.eclipse.domain.model.stock.MvtStock;

/**
 * Predicat sur les mouvements de stock (Récupération des mouvements de type SORTIE / DISPENSATION
 * / DOTATION).
 
 * @version $Revision$ $Date$
 */
public class MvtStockSortiePredicate
    implements Predicate, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 4423458596656880712L;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate(final Object object)
    {
        final MvtStock mvt = (MvtStock) object;
        final CategorieMouvement categ = mvt.getType().getCategorie();
        return CategorieMouvement.SORTIE.equals(categ)
               || CategorieMouvement.DISPENSATION.equals(categ)
               || CategorieMouvement.DOTATION.equals(categ);
    }
}
