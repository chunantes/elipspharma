package fr.pharma.eclipse.service.dispensation.processor;

import fr.pharma.eclipse.domain.model.dispensation.ConseilDispensation;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;

/**
 * Interface définissant le comportement commun des processors en charge de caclculer les conseil
 * à la dispensation.
 
 * @version $Revision$ $Date$
 */
public interface ConseilDispensationProcessor
{

    /**
     * Méthode en charge d'appliquer le traitement afin de calculer le conseil à la dispensation
     * corresopndant au produit prescrit en paramètre.
     * @param produitPrescrit Le produit prescrit.
     * @return Le conseil dispensation.
     */
    public abstract ConseilDispensation process(final ProduitPrescrit produitPrescrit);

}
