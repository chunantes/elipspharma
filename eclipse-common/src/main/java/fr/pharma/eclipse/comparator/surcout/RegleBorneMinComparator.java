package fr.pharma.eclipse.comparator.surcout;

import java.util.Comparator;

import fr.pharma.eclipse.domain.model.surcout.regle.Regle;

/**
 * Comparator utilisée pour trier les regles de type Variable selon leur borne
 * inférieure.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class RegleBorneMinComparator implements Comparator<Regle> {

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final Regle o1,
                       final Regle o2) {
        final Integer o1Min = o1 == null ? null : o1.getMin();
        final Integer o1Max = o1 == null ? null : o1.getMax();
        final Integer o2Min = o2 == null ? null : o2.getMin();
        final Integer o2Max = o2 == null ? null : o2.getMax();

        if ((o1Min == null) && (o2Min == null)) {
            return o1Max.compareTo(o2Max);
        } else if (o1Min == null) {
            return -1;
        } else if (o2Min == null) {
            return 1;
        } else {
            return o1Min.compareTo(o2Min);
        }

    }
}
