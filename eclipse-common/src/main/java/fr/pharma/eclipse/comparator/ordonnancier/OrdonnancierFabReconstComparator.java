package fr.pharma.eclipse.comparator.ordonnancier;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.stock.PreparationEntree;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe de comparator des beans ElementToCheck pour la gestion des
 * ordonnanciers fabrication/reconstitution.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class OrdonnancierFabReconstComparator implements Comparator<PreparationEntree>, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1376479700932045310L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final PreparationEntree o1,
                       final PreparationEntree o2) {
        return this.buildKey(o1).compareTo(this.buildKey(o2));
    }

    private String buildKey(final PreparationEntree preparation) {
        String s = preparation.getNumLot();
        if (preparation.getNumTraitement() != null) {
            s += EclipseConstants.COMMA + preparation.getNumTraitement();
        }
        return s;
    }
}
