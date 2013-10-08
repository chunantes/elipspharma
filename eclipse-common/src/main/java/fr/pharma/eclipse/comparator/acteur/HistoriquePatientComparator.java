package fr.pharma.eclipse.comparator.acteur;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.patient.HistoriquePatient;

/**
 * Comparateur d'historique de patient.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class HistoriquePatientComparator implements Comparator<HistoriquePatient>, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -7005207240770696341L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final HistoriquePatient o1,
                       final HistoriquePatient o2) {
        return o2.getDate().compareTo(o1.getDate());
    }

}
