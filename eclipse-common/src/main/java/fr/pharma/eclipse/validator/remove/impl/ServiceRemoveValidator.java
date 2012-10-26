package fr.pharma.eclipse.validator.remove.impl;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.validator.remove.RemoveValidator;

/**
 * Classe de validation de suppression d'un objet Service.
 
 * @version $Revision$ $Date$
 */
public class ServiceRemoveValidator
    implements RemoveValidator<Service>, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -7753612509074901559L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final Service service)
    {
        // Vérification Relation Service-Investigateur
        if (!service.getInvestigateurs().isEmpty())
        {
            throw new ValidationException("remove",
                                          new String[]
                                          {"impossible" },
                                          service);
        }

        // Vérification Relation Service-ArcInvestigateur
        if (!service.getArcInvestigateurs().isEmpty())
        {
            throw new ValidationException("remove",
                                          new String[]
                                          {"impossible" },
                                          service);
        }

        // Vérification Relation Service-Essais
        if (!service.getEssais().isEmpty())
        {
            throw new ValidationException("remove",
                                          new String[]
                                          {"impossible" },
                                          service);
        }
    }

}
