package fr.pharma.eclipse.comparator.surcout;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Comparator sur les items.
 
 * @version $Revision$ $Date$
 */
public class ItemComparator
    implements Comparator<Item>, Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -2940432017683119246L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final Item o1,
                       final Item o2)
    {
        final String key1 = this.buildKey(o1);
        final String key2 = this.buildKey(o2);

        return key1.compareTo(key2);
    }

    /**
     * Méthode en charge de construire la clé relative à un Item.
     * @param p Item dont on veut construire la clé.
     * @return La clé de l'Item.
     */
    private String buildKey(final Item p)
    {
        final StringBuilder builder = new StringBuilder();
        builder.append(p.getTheme()).append(EclipseConstants.COMMA);
        if (p.getCategorie() != null)
        {
            builder.append(p.getCategorie());
        }
        return builder.toString();
    }

}
