package fr.pharma.eclipse.comparator.incident;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.incident.Incident;

/**
 * Classe de comparator sur Incident.
 
 * @version $Revision$ $Date$
 */
public class IncidentComparator
    implements Comparator<Incident>, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -6877291607518684760L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final Incident o1,
                       final Incident o2)
    {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");

        final String str1 = sdf.format(o1.getDate().getTime())
                            + o1.getLibelle()
                            + o1.getCommentaire();
        final String str2 = sdf.format(o2.getDate().getTime())
                            + o2.getLibelle()
                            + o2.getCommentaire();
        return str1.compareTo(str2);
    }
}
