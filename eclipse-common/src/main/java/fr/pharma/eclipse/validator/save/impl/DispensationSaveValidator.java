package fr.pharma.eclipse.validator.save.impl;

import java.io.Serializable;
import java.util.SortedSet;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.dispensation.ElementToCheck;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.predicate.GenericPredicate;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.validator.save.SaveValidator;

/**
 * Classe en charge de valider la sauvegarde d'un bean Dispensation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationSaveValidator implements SaveValidator<Dispensation>, Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2171448742682665751L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final Dispensation dispensation,
                         final GenericService<Dispensation> prescriptionService) {
        final SortedSet<ProduitPrescrit> produits = dispensation.getPrescription().getProduitsPrescrits();

        for (final ProduitPrescrit produit : produits) {

            // on vérifie que tous les produits prescrits sont dispenses.
            if (CollectionUtils.find(dispensation.getDispensationsProduit(), new GenericPredicate("produitPrescrit.id", produit.getId())) == null) {
                dispensation.setDispense(false);
                throw new ValidationException("dispensation.produitPrescrit", new String[]{"notComplete", produit.getProduit().getNom() }, dispensation);
            }

        }

        for (final ElementToCheck elem : dispensation.getElementsToCheck()) {
            if (!elem.getChecked()) {
                dispensation.setDispense(false);
                throw new ValidationException("dispensation.elementToCheck", new String[]{"notComplete" }, dispensation);
            }
        }
        if (dispensation.getDispensationsProduit().isEmpty()) {
            dispensation.setDispense(false);
            throw new ValidationException("dispensation.stock", new String[]{"notComplete" }, dispensation);

        }
    }

}
