package fr.pharma.eclipse.comparator.acteur;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.acteur.Personne;

/**
 * Comparateur de personne.
 
 * @version $Revision$ $Date$
 */
public class PersonneComparator
    implements Comparator<Personne>, Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 6093392204969755951L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final Personne o1,
                       final Personne o2)
    {

        return this.buildKey(o1).compareTo(this.buildKey(o2));
    }

    /**
     * Méthode en charge de construire la clé pour une personne.
     * @param p La personne.
     * @return La clé.
     */
    private String buildKey(final Personne p)
    {
        final StringBuffer sb = new StringBuffer();
        if (p.getType() != null)
        {
            sb.append(0).append(p.getType().getLibelle());
        }
        else
        {
            sb.append(1);
        }
        sb.append(p.getNom());
        sb.append(p.getPrenom());

        return sb.toString();
    }

}
