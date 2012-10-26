package fr.pharma.eclipse.validator.prescription.impl;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.predicate.prescription.ProduitPrescritPredicate;
import fr.pharma.eclipse.validator.prescription.ProduitPrescritValidator;

/**
 * Validateur en charge de vérifier qu'un produit prescrit n'est pas prescrit plusieurs fois.
 
 * @version $Revision$ $Date$
 */
public class ProduitPrescritValidatorImpl
    implements ProduitPrescritValidator
{

    /**
     * {@inheritDoc}
     */
    public void validateAjoutProduitPrescrit(final ProduitPrescrit produitPrescrit,
                                             final Collection<ProduitPrescrit> produitsPrescrits)
    {
        if (CollectionUtils.find(produitsPrescrits,
                                 new ProduitPrescritPredicate(produitPrescrit)) != null)
        {

            throw new ValidationException("produitPrescrit.enregistrement",
                                          new String[]
                                          {"alreadyExists" });
        }
    }
}
