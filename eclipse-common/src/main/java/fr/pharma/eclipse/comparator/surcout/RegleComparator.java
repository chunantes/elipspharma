package fr.pharma.eclipse.comparator.surcout;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.enums.surcout.TypeCout;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Comparator sur les Regles de calculs.
 
 * @version $Revision$ $Date$
 */
public class RegleComparator
    implements Comparator<Regle>, Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -4091012880439608882L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final Regle o1,
                       final Regle o2)
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
    private String buildKey(final Regle p)
    {
        final StringBuilder builder = new StringBuilder();
        builder.append(p.getType()).append(EclipseConstants.COMMA);
        builder.append(p.getPerimetre()).append(EclipseConstants.COMMA);

        if (p.getType().equals(TypeCout.FIXE))
        {
            builder.append(p.getPremiereAnnee()).append(EclipseConstants.COMMA);
            builder.append(p.getAnneesSuivantes()).append(EclipseConstants.COMMA);
        }
        else if (p.getType().equals(TypeCout.VARIABLE))
        {
            builder.append(p.getMode()).append(EclipseConstants.COMMA);
            builder.append(p.getMin()).append(EclipseConstants.COMMA);
            builder.append(p.getMax()).append(EclipseConstants.COMMA);
            builder.append(p.getMontant()).append(EclipseConstants.COMMA);
        }
        return builder.toString();
    }

}
