package fr.pharma.eclipse.comparator.document;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.essai.detail.administratif.document.DocumentAdministratif;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.document.DocumentBrochureProduits;

/**
 * Description de la classe.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DocumentBrochureComparator implements Comparator<DocumentAdministratif>, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4892759319895507998L;

    /**
     * COnstruit la clé de comparaison.
     * @return la clé de comparaison.
     */
    private String buildKey(final DocumentAdministratif doc) {
        final StringBuffer sb = new StringBuffer();
        sb.append(doc.getDateMaj().getTimeInMillis()).append(doc.getMajPar()).append(((DocumentBrochureProduits) doc).getVersionDoc())
                .append(((DocumentBrochureProduits) doc).getTypeDocumentBrochure());
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final DocumentAdministratif o1,
                       final DocumentAdministratif o2) {
        // Tri par ordre de date de mise à jour décroissant
        return this.buildKey(o2).compareTo(this.buildKey(o1));
    }
}
