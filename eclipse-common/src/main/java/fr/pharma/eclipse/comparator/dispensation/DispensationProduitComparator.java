package fr.pharma.eclipse.comparator.dispensation;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.stock.DispensationProduit;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Comparateur de Dispensation Produit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationProduitComparator implements Comparator<DispensationProduit>, Serializable {
    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 5408090284384020973L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final DispensationProduit p1,
                       final DispensationProduit p2) {
        final String key1 = this.buildKey(p1);
        final String key2 = this.buildKey(p2);

        return key1.compareTo(key2);
    }

    /**
     * Méthode en charge de construire la clé relative à un DispensationProduit.
     * @param p DispensationProduit dont on veut construire la clé.
     * @return La clé du DispensationProduit.
     */
    private String buildKey(final DispensationProduit p) {
        final StringBuilder builder = new StringBuilder();
        builder.append(p.getNumLot()).append(EclipseConstants.COMMA);
        if (p.getNumTraitement() != null) {
            builder.append(p.getNumTraitement()).append(EclipseConstants.COMMA);
        }
        return builder.toString();
    }
}
