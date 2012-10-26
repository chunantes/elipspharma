package fr.pharma.eclipse.predicate.stock;

import java.io.Serializable;

import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.enums.stock.CategorieMouvement;
import fr.pharma.eclipse.domain.model.stock.MvtStock;

/**
 * Predicat sur les mouvements de stock (Récupération des mouvements de type ENTREE).
 
 * @version $Revision$ $Date$
 */
public class MvtStockEntreePredicate
    implements Predicate, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7706076609784007948L;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate(final Object object)
    {
        final MvtStock mvt = (MvtStock) object;
        return CategorieMouvement.ENTREE.equals(mvt.getType().getCategorie());
    }
}
