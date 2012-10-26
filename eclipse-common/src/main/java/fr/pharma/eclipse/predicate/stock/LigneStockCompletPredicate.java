package fr.pharma.eclipse.predicate.stock;

import java.io.Serializable;

import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.model.stock.LigneStock;

/**
 * Prédicat sur les lignes de stock complète (c'est à dire avec une quantité positive définie).
 
 * @version $Revision$ $Date$
 */
public class LigneStockCompletPredicate
    implements Predicate, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1974470263421738418L;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate(final Object object)
    {
        final LigneStock ligne = (LigneStock) object;
        return ligne.getQteASortir() != null
               && ligne.getQteASortir() > 0;
    }
}
