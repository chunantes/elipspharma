package fr.pharma.eclipse.comparator.design;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import fr.pharma.eclipse.domain.enums.design.UniteTemps;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;

/**
 * Comparateur de TempsPrescription.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TempsPrescriptionComparator implements Comparator<TempsPrescription>, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -7656972767236694167L;

    /**
     * Map de conversion en jours.
     */
    private final Map<UniteTemps, Integer> mapJour;

    /**
     * Constructeur.
     */
    public TempsPrescriptionComparator() {
        this.mapJour = new HashMap<UniteTemps, Integer>();
        this.mapJour.put(UniteTemps.JOUR, 1);
        this.mapJour.put(UniteTemps.MOIS, 30);
        this.mapJour.put(UniteTemps.SEMAINE, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final TempsPrescription o1,
                       final TempsPrescription o2) {
        if (this.isNull(o1) || this.isNull(o2)) {
            return 1;
        }
        final int t1 = this.mapJour.get(o1.getUnite()) * o1.getNb();
        final int t2 = this.mapJour.get(o2.getUnite()) * o2.getNb();

        if (t1 < t2) {
            return -1;
        } else {
            return 1;
        }
    }
    /**
     * Retourne <true> si l'objet TempsPrescription ou une de ses propriétés est
     * null.
     * @param t TempsPrescription
     * @return <true> si l'objet TempsPrescription ou une de ses propriétés est
     * null.
     */
    private boolean isNull(final TempsPrescription t) {
        return (t == null) || (t.getNb() == null) || (t.getUnite() == null);
    }
}
