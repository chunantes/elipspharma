package fr.pharma.eclipse.validator.remove.impl;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.validator.remove.RemoveValidator;

/**
 * Classe de validation de suppression d'un objet Personne.
 
 * @version $Revision$ $Date$
 */
public class PersonneRemoveValidator
    implements RemoveValidator<Personne>, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2249834956549015567L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final Personne personne)
    {
        // Vérification des habilitations de la personne
        if (!personne.getHabilitations().isEmpty())
        {
            throw new ValidationException("remove",
                                          new String[]
                                          {"impossible" },
                                          personne);
        }
    }

}
