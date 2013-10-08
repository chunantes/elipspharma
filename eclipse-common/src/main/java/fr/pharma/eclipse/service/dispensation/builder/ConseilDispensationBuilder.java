package fr.pharma.eclipse.service.dispensation.builder;

import fr.pharma.eclipse.domain.model.dispensation.ConseilDispensation;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;

/**
 * Interface définissant le comportement d'un fabriquant de conseils de
 * dispensation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface ConseilDispensationBuilder {
    /**
     * Méthode en charge de vérifier si le produit prescrit fourni supporte les
     * calculs nécessaires à la construction du conseil.
     * @param produitPrescrit Le produit prescrit.
     * @return <true> si le produit prescrit contient les informations
     * nécessaires à la construction d'un conseil.
     */
    boolean support(ProduitPrescrit produitPrescrit);

    /**
     * Méthode en charge de construire les conseils à la dispensation à partir
     * du produit prescrit.
     * @param produitPrescrit Le produit prescrit.
     * @return La liste de conseils à la dispensation.
     */
    ConseilDispensation build(ProduitPrescrit produitPrescrit);

    /**
     * Méthode en charge d'appeler le formatter associé au mode de prescriptin
     * du conseil.
     * @param conseil Le conseil.
     * @return Le message sous forme de chaine de caractère.
     */
    String format(ConseilDispensation conseil);
}
