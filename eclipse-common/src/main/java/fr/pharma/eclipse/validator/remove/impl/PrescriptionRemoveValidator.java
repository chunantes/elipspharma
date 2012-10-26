package fr.pharma.eclipse.validator.remove.impl;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.validator.remove.RemoveValidator;

/**
 * Classe de validation de suppression d'un objet Prescription.
 
 * @version $Revision$ $Date$
 */
public class PrescriptionRemoveValidator
    implements RemoveValidator<Prescription>, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2830958369731883278L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final Prescription prescription)
    {
        if (!prescription.getDispensations().isEmpty())
        {

            throw new ValidationException("remove",
                                          new String[]
                                          {"impossible" },
                                          prescription);
        }
    }

}
