package fr.pharma.eclipse.validator.save.impl;

import java.io.Serializable;
import java.util.SortedSet;

import fr.pharma.eclipse.domain.model.acteur.ArcInvestigateur;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.validator.save.SaveValidator;

/**
 * Classe en charge de valider la sauvegarde d'un bean ArcInvestigateur.
 
 * @version $Revision$ $Date$
 */
public class ArcInvestigateurSaveValidator
    implements SaveValidator<ArcInvestigateur>, Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 8623602063885915465L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final ArcInvestigateur arcInvestigateur,
                         final GenericService<ArcInvestigateur> beanService)
    {
        final SortedSet<Service> services = arcInvestigateur.getServices();

        // Vérification de la saisie d'au moins un service
        if (services.isEmpty())
        {
            throw new ValidationException("arcInvestigateur.services",
                                          new String[]
                                          {"notEmpty" },
                                          arcInvestigateur);
        }
    }

}
