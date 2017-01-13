package fr.pharma.eclipse.comparator.evenement;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Comparator;

import org.apache.commons.lang3.ObjectUtils;

import fr.pharma.eclipse.domain.model.evenement.Evenement;

/**
 * Classe de comparator sur Evenement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EvenementComparator implements Comparator<Evenement>, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -6877291607518684760L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final Evenement o1,
                       final Evenement o2) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");

        final String str1 = sdf.format(o1.getDateDebut().getTime()) + o1.getTypeEvenement() + o1.getLibelle() + o1.getCommentaire() + ObjectUtils.defaultIfNull(o1.getId(), 0);
        final String str2 = sdf.format(o2.getDateDebut().getTime()) + o2.getTypeEvenement() + o2.getLibelle() + o2.getCommentaire() + ObjectUtils.defaultIfNull(o2.getId(), 0);
        return str1.compareTo(str2);
    }
}
