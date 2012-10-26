package fr.pharma.eclipse.comparator.suivi;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Classe de comparator de suivi de modifcation.
 
 * @version $Revision$ $Date$
 */
public class SuiviComparator
    implements Comparator<Suivi>, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4892759319895507998L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final Suivi o1,
                       final Suivi o2)
    {
        // Tri par ordre de date de mise à jour décroissant
        return o2.getDateMaj().compareTo(o1.getDateMaj());
    }
}
