package fr.pharma.eclipse.helper;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.utils.Utils;

/**
 * Helper pour la gestion des documents sur l'IHM.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DocumentsHelper implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6000536334751253312L;

    /**
     * Méthode en charger de construire le message de l'info-bulle du nom d'un
     * document.
     * @param doc Dernier document.
     * @param messageAjouteLe Message 'Ajouté le'.
     * @param messageAjoutePar Message 'par'.
     * @param patternDate Pattern pour le formattage de la date.
     * @return La chaîne de caractères correspondant au title du composant IHM.
     */
    public String buildDocTitle(final DocumentEclipse doc,
                                final String messageAjouteLe,
                                final String messageAjoutePar,
                                final String patternDate) {
        final StringBuilder builder = new StringBuilder();
        if (doc != null) {
            builder.append(messageAjouteLe).append(" ").append(Utils.formatDate(doc.getDateMaj().getTime(), patternDate));
            builder.append(" ").append(messageAjoutePar).append(" ").append(doc.getMajPar());
        }
        return builder.toString();
    }

}
