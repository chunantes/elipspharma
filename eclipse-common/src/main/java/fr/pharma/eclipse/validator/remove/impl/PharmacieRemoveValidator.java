package fr.pharma.eclipse.validator.remove.impl;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.validator.remove.RemoveValidator;

/**
 * Classe de validation de suppression d'un objet Pharmacie.
 
 * @version $Revision$ $Date$
 */
public class PharmacieRemoveValidator
    implements RemoveValidator<Pharmacie>, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3474536105220649621L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final Pharmacie pharmacie)
    {
        // Vérification Relation Pharmacie-Essai
        if (!pharmacie.getDetailsDonneesPharma().isEmpty())
        {
            throw new ValidationException("remove",
                                          new String[]
                                          {"impossible" },
                                          pharmacie);
        }

        // Vérification Relation Pharmacie-Pharmacien
        if (!pharmacie.getPharmaciens().isEmpty())
        {
            throw new ValidationException("remove",
                                          new String[]
                                          {"impossible" },
                                          pharmacie);
        }
    }

}
