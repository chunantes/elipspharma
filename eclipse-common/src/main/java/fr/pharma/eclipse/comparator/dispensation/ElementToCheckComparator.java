package fr.pharma.eclipse.comparator.dispensation;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.dispensation.ElementToCheck;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Description de la classe.
 
 * @version $Revision$ $Date$
 */
public class ElementToCheckComparator
    implements Comparator<ElementToCheck>, Serializable
{
    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 5408090284384020973L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final ElementToCheck p1,
                       final ElementToCheck p2)
    {
        final String key1 = this.buildKey(p1);
        final String key2 = this.buildKey(p2);

        return key1.compareTo(key2);
    }

    /**
     * Méthode en charge de construire la clé relative à un ElementToCheck.
     * @param p ElementToCheck dont on veut construire la clé.
     * @return La clé du ElementToCheck.
     */
    private String buildKey(final ElementToCheck p)
    {
        final StringBuilder builder = new StringBuilder();
        builder.append(p.getNom()).append(EclipseConstants.COMMA);
        builder.append(p.getProduitPrescrit().getId()).append(EclipseConstants.COMMA);
        return builder.toString();
    }
}
