package fr.pharma.eclipse.validator.remove.impl;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.localisation.Site;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.validator.remove.RemoveValidator;

/**
 * Classe de validation de suppression d'un objet Site.
 
 * @version $Revision$ $Date$
 */
public class SiteRemoveValidator
    implements RemoveValidator<Site>, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -6555689550306380427L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final Site site)
    {
        // Si le site est présent au niveau d'une pharmacie
        if (!site.getPharmacies().isEmpty())
        {
            throw new ValidationException("remove",
                                          new String[]
                                          {"impossible" },
                                          site);
        }
    }

}
