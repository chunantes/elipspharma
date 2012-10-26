package fr.pharma.eclipse.validator.save.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Classe en charge de valider la sauvegarde d'un bean Produit.
 
 * @version $Revision$ $Date$
 */
public class ProduitSaveValidator
    implements Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4037191312142199353L;

    /**
     * Service de gestion de Produit.
     */
    @Resource(name = "produitService")
    private GenericService<Produit> produitService;

    /**
     * Méthode en charge de valider un produit (unicité du login du produit pour tous les types).
     * @param produit Produit à valider.
     */
    public void validate(final Produit produit)
    {
        // TODO
    }

    /**
     * Setter pour produitService.
     * @param produitService le produitService à écrire.
     */
    public void setProduitService(final GenericService<Produit> produitService)
    {
        this.produitService = produitService;
    }

}
