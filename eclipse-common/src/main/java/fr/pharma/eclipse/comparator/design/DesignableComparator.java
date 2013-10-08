package fr.pharma.eclipse.comparator.design;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.design.Designable;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe de comparator sur Designable.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DesignableComparator implements Comparator<Designable>, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3398493522606374717L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final Designable p1,
                       final Designable p2) {
        final String key1 = this.buildKey(p1);
        final String key2 = this.buildKey(p2);

        return key1.compareTo(key2);
    }

    /**
     * Méthode en charge de construire la clé relative à un Designable
     * @param p Designable dont on veut construire la clé.
     * @return La clé du Designable.
     */
    private String buildKey(final Designable p) {
        // Tri par date + nom du produit + dosage + mode de prescription
        final StringBuilder builder = new StringBuilder();
        if (p.getDebut() != null) {
            builder.append(String.valueOf(p.getDebut())).append(EclipseConstants.COMMA);
        }
        builder.append(p.getNomComplet()).append(EclipseConstants.COMMA);
        return builder.toString();
    }

}
