package fr.pharma.eclipse.validator.prescription;

import java.util.Collection;

import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;

/**
 * Interface des validator relatifs à la gestion des produits prescrits.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface ProduitPrescritValidator {

    /**
     * Méthode en charge de valider si un produit prescrit équivalent (meme
     * produit et meme mode de prescription) a déjà été défini.
     * @param produitPrescrit Le produit prescrit à valider.
     * @param produitsPrescrits La liste de produits prescrits.
     */
    void validateAjoutProduitPrescrit(final ProduitPrescrit produitPrescrit,
                                      final Collection<ProduitPrescrit> produitsPrescrits);

}
