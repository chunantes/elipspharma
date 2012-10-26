package fr.pharma.eclipse.component.acteur.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import fr.pharma.eclipse.comparator.acteur.PersonneComparator;
import fr.pharma.eclipse.domain.model.acteur.Personne;

/**
 * Helper pour Personne.
 
 * @version $Revision$ $Date$
 */
public class PersonneHelper
    implements Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 2967527814927728844L;

    /**
     * Méthode en charge de retourner une liste triée des personnes en paramètre.
     * @param personnes
     * @return la liste triée.
     */
    public List<Personne> sortPersonnes(final Collection<Personne> personnes)
    {
        final List<Personne> result = new ArrayList<Personne>(personnes);
        Collections.sort(result,
                         new PersonneComparator());

        return result;
    }
}
