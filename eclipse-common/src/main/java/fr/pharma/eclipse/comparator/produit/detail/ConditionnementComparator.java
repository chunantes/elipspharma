package fr.pharma.eclipse.comparator.produit.detail;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Comparator de Conditionnement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ConditionnementComparator implements Comparator<Conditionnement>, Serializable {
    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 5408090284384020973L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final Conditionnement p1,
                       final Conditionnement p2) {
        final String key1 = this.buildKey(p1);
        final String key2 = this.buildKey(p2);

        return key1.compareTo(key2);
    }

    /**
     * Méthode en charge de construire la clé relative à un Conditionnement.
     * @param p Conditionnement dont on veut construire la clé.
     * @return La clé du Conditionnement.
     */
    private String buildKey(final Conditionnement p) {
        final StringBuilder builder = new StringBuilder();
        builder.append(p.getLibelle()).append(EclipseConstants.COMMA);
        return builder.toString();
    }
}
