package fr.pharma.eclipse.validator.save.impl;

import java.io.Serializable;
import java.util.SortedSet;

import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.validator.save.SaveValidator;

/**
 * Classe en charge de valider la sauvegarde d'un bean Investigateur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class InvestigateurSaveValidator implements SaveValidator<Investigateur>, Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5845554348555093411L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final Investigateur investigateur,
                         final GenericService<Investigateur> investigateurService) {
        final SortedSet<Service> services = investigateur.getServices();

        // Vérification de la saisie d'au moins un service
        if (services.isEmpty()) {
            throw new ValidationException("investigateur.services", new String[]{"notEmpty" }, investigateur);
        }
    }

}
