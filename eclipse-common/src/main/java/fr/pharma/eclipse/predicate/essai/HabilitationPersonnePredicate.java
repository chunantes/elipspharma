package fr.pharma.eclipse.predicate.essai;

import java.io.Serializable;

import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;

/**
 * Classe en charge de retourner l'habilitation active correspondant à une personne.
 
 * @version $Revision$ $Date$
 */
public class HabilitationPersonnePredicate
    implements Predicate, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -784843260756704396L;

    /**
     * Personne.
     */
    private final Personne personne;

    /**
     * Constructeur prenant une Personne.
     * @param personne Droit.
     */
    public HabilitationPersonnePredicate(final Personne personne)
    {
        this.personne = personne;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate(final Object object)
    {
        final Habilitation habilitation = (Habilitation) object;
        return habilitation.isActive()
               && this.personne.equals(habilitation.getPersonne());
    }
}
