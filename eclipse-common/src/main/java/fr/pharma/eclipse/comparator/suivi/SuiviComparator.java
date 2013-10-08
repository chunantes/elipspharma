package fr.pharma.eclipse.comparator.suivi;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Classe de comparator de suivi de modifcation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SuiviComparator implements Comparator<Suivi>, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4892759319895507998L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final Suivi o1,
                       final Suivi o2) {
        final Calendar d1 = o1 == null ? null : o1.getDateMaj();
        final Calendar d2 = o2 == null ? null : o2.getDateMaj();
        if ((d1 == null) && (d2 == null)) {
            return 0;
        } else if (d2 == null) {
            return -1;
        } else if (d1 == null) {
            return 1;
        } else {
            // Tri par ordre de date de mise à jour décroissant
            return d2.compareTo(d1);
        }
    }
}
