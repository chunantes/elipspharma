package fr.pharma.eclipse.validator.save.impl;

import java.io.Serializable;
import java.util.SortedSet;

import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.validator.save.SaveValidator;

/**
 * Classe en charge de valider la sauvegarde d'un bean Pharmacien.
 
 * @version $Revision$ $Date$
 */
public class PharmacienSaveValidator
    implements SaveValidator<Pharmacien>, Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2171448742682665751L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final Pharmacien pharmacien,
                         final GenericService<Pharmacien> pharmacienService)
    {
        final SortedSet<Pharmacie> pharmacies = pharmacien.getPharmacies();

        // Vérification de la saisie d'au moins une pharmacie
        if (pharmacies.isEmpty())
        {
            throw new ValidationException("pharmacien.pharmacies",
                                          new String[]
                                          {"notEmpty" },
                                          pharmacien);
        }
    }

}
