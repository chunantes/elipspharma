package fr.pharma.eclipse.comparator.common;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.common.BeanWithNom;

/**
 * Classe de comparator pour les objets ayant un nom.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BeanWithNomComparator implements Comparator<BeanWithNom>, Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7462968439226919349L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final BeanWithNom bean1,
                       final BeanWithNom bean2) {
        String nom1 = bean1 == null ? null : bean1.getNom();
        String nom2 = bean2 == null ? null : bean2.getNom();

        if (nom1 == null && nom2 == null) {
            return 0;
        } else if (nom1 == null) {
            return -1;
        } else if (nom2 == null) {
            return 1;
        } else {
            return nom1.compareTo(nom2);
        }
    }

}
