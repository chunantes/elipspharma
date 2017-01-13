package fr.pharma.eclipse.comparator.common;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.common.BeanObject;

/**
 * Comparator sur les listes d'id technique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EclipseListComparator implements Comparator<BeanObject>, Serializable {

    /**
     * Génération serial version id.
     */
    private static final long serialVersionUID = -2794625968967550973L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final BeanObject o1,
                       final BeanObject o2) {
    	int compare = 0;
        // Récupération des identifiants
        final Long id1 = o1.getId();
        final Long id2 = o2.getId();

        // Les 2 objets ont une clé de définie
        if ((id1 != null) && (id2 != null)) {
            // Les 2 identifiants sont positifs => les 2 enregs sont en base
            if ((id1 > 0) && (id2 > 0)) {
                compare = id1.compareTo(id2);
            }

            // Les 2 identifiants sont négatifs => les 2 enregs ne sont pas en
            // base
            if ((id1 < 0) && (id2 < 0)) {
            	compare = id2.compareTo(id1);
            }

            // Un seul des 2 ids est négatif
            if (id1 <= 0) {
            	compare = 1;
            }
            if (id2 <= 0) {
            	compare = -1;
            }
        }

        if ((id1 != null) && (id2 == null)) {
        	compare = -1;
        }
        if ((id1 == null) && (id2 != null)) {
        	compare = 1;
        }

        return compare;
    }

}
