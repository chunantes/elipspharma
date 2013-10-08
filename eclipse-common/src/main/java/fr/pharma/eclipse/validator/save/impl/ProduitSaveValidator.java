package fr.pharma.eclipse.validator.save.impl;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Classe en charge de valider la sauvegarde d'un bean Produit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ProduitSaveValidator implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4037191312142199353L;

    /**
     * Méthode en charge de valider un produit (unicité du login du produit pour
     * tous les types).
     * @param produit Produit à valider.
     */
    public void validate(final Produit produit) {
        // TODO
    }

    /**
     * Setter pour produitService.
     * @param produitService le produitService à écrire.
     */
    public void setProduitService(final GenericService<Produit> produitService) {
    }

}
