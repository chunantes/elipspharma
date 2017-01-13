package fr.pharma.eclipse.comparator.produit.detail;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.produit.Produit;

/**
 * Comparator sur produit.
 *
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ProduitComparator implements Comparator<Produit>, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -1848011066883422471L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final Produit p1,
            final Produit p2) {
        final String key1 = this.buildKey(p1);
        final String key2 = this.buildKey(p2);
        if (key1==null) {
            return -1;
        } else {
            return key1.compareTo(key2);
        }
    }

    /**
     * Méthode en charge de construire la clé relative à un Produit.
     *
     * @param p Produit dont on veut construire la clé.
     * @return La clé du Produit.
     */
    private String buildKey(final Produit p) {
        final StringBuilder builder = new StringBuilder();
        if (p == null) {
            return null;
        } else {
            builder.append(p.getDenomination());
            builder.append(p.getType());
            return builder.toString();
        }
    }
}
