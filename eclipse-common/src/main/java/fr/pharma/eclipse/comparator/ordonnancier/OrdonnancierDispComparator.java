package fr.pharma.eclipse.comparator.ordonnancier;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.dispensation.Dispensation;

/**
 * Classe de comparator des beans Dispensation pour la gestion des ordonnanciers
 * de dispensation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class OrdonnancierDispComparator implements Comparator<Dispensation>, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1314609652296750204L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final Dispensation disp1,
                       final Dispensation disp2) {
        // Comparaison selon le numéro d'ordonnancier
        return this.buildKey(disp1).compareTo(this.buildKey(disp2));
    }

    /**
     * Méthode en charge de construire la clé pour l'objet en paramètre.
     *
     * @param objet
     *            L'objet.
     * @return La clé.
     */
    private Integer buildKey(final Dispensation objet) {
        Integer num = 0;
        if (null != objet.getNumOrdonnancier()) {
            num = objet.getNumOrdonnancier();
        }
        return num;
    }
}
