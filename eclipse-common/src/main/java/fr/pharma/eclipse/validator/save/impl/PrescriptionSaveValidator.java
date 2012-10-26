package fr.pharma.eclipse.validator.save.impl;

import java.io.Serializable;
import java.util.SortedSet;

import fr.pharma.eclipse.domain.enums.QualiteInsu;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.validator.save.SaveValidator;

/**
 * Classe en charge de valider la sauvegarde d'un bean Prescription.
 
 * @version $Revision$ $Date$
 */
public class PrescriptionSaveValidator
    implements SaveValidator<Prescription>, Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2171448742682665751L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final Prescription prescription,
                         final GenericService<Prescription> prescriptionService)
    {
        final SortedSet<ProduitPrescrit> produits = prescription.getProduitsPrescrits();

        final QualiteInsu insu =
            prescription.getEssai().getDetailDonneesPharma().getInfosGenerales().getQualiteInsu();

        // Vérification de la saisie d'au moins un produit prescrit

        if (insu == null
            || !insu.equals(QualiteInsu.ESSAI_DOUBLE_AVEUGLE))
        {
            if (produits.isEmpty())
            {
                throw new ValidationException("prescription.produitsPrescrits",
                                              new String[]
                                              {"notEmpty" },
                                              prescription);
            }
        }
    }

}
