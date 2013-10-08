package fr.pharma.eclipse.comparator.inclusion;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.patient.Inclusion;

/**
 * Comparateur d'historique d'inclusions.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class InclusionComparator implements Comparator<Inclusion>, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -7005207240770696341L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final Inclusion o1,
                       final Inclusion o2) {
        return o2.getDateInclusion().compareTo(o1.getDateInclusion());
    }

}
