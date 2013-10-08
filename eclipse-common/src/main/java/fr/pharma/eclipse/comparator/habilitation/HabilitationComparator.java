package fr.pharma.eclipse.comparator.habilitation;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe de comparator de suivi de modification.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class HabilitationComparator implements Comparator<Habilitation>, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6626775207036341566L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final Habilitation habilitation1,
                       final Habilitation habilitation2) {
        final String key1 = HabilitationComparator.buildKey(habilitation1);
        final String key2 = HabilitationComparator.buildKey(habilitation2);
        if ((key1 == null) && (key2 == null)) {
            return 0;
        } else if (key1 == null) {
            return -1;
        } else if (key2 == null) {
            return 1;
        } else {
            return key1.compareTo(key2);
        }
    }

    /**
     * Méthode en charge de construire la clé relative à une habilitation.
     * @param habilitation Habilitation dont on veut construire la clé.
     * @return La clé de l'habilitation.
     */
    private static String buildKey(final Habilitation habilitation) {
        if (habilitation == null) {
            return null;
        }

        // Tri par Actif (true<false) + Type de Personne + Droit + Nom de la
        // personne
        final Personne pers = habilitation.getPersonne();
        final StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(habilitation.isActive()).length()).append(EclipseConstants.COMMA); // subterfuge
                                                                                                         // pour
                                                                                                         // true<false
        builder.append(pers == null ? pers : pers.getType().getLibelle());
        builder.append(EclipseConstants.COMMA);
        builder.append(habilitation.getDroit() == null ? null : habilitation.getDroit().getLibelle());
        builder.append(EclipseConstants.COMMA);
        builder.append(pers == null ? null : pers.getNom());

        return builder.toString();
    }
}
