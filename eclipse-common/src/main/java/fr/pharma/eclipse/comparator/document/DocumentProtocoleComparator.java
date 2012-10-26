package fr.pharma.eclipse.comparator.document;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.essai.detail.administratif.document.DocumentAdministratif;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.document.DocumentProtocole;

/**
 * Description de la classe.
 
 * @version $Revision$ $Date$
 */
public class DocumentProtocoleComparator
    implements Comparator<DocumentAdministratif>, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4892759319895507998L;

    /**
     * COnstruit la clé de comparaison.
     * @return la clé de comparaison.
     */
    private String buildKey(final DocumentAdministratif doc)
    {
        final StringBuffer sb = new StringBuffer();
        sb.append(doc.getDateMaj().getTimeInMillis())
                .append(doc.getMajPar())
                .append(((DocumentProtocole) doc).getVersion())
                .append(((DocumentProtocole) doc).getTypeDocumentProtocole());
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final DocumentAdministratif o1,
                       final DocumentAdministratif o2)
    {
        // Tri par ordre de date de mise à jour décroissant
        return this.buildKey(o2).compareTo(this.buildKey(o1));
    }
}
