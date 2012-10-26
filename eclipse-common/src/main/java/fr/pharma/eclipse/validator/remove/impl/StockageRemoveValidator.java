package fr.pharma.eclipse.validator.remove.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.produit.ProduitSearchCriteria;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.validator.remove.RemoveValidator;

/**
 * Classe de validation de suppression d'un objet Stockage.
 
 * @version $Revision$ $Date$
 */
public class StockageRemoveValidator
    implements RemoveValidator<Stockage>, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2830958369731883278L;

    /**
     * Service de gestion des produits.
     */
    @Resource(name = "produitService")
    private ProduitService<Produit> produitService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final Stockage stockage)
    {
        // Vérification Relation Pole-Service
        final ProduitSearchCriteria criteria = new ProduitSearchCriteria();
        criteria.setStockage(stockage);
        if (this.produitService.hasResult(criteria))
        {
            throw new ValidationException("remove",
                                          new String[]
                                          {"impossible" },
                                          stockage);
        }
        criteria.setStockageRetour(stockage);
        criteria.setStockage(null);
        if (this.produitService.hasResult(criteria))
        {
            throw new ValidationException("remove",
                                          new String[]
                                          {"impossible" },
                                          stockage);
        }
    }

    /**
     * Setter pour produitService.
     * @param produitService Le produitService à écrire.
     */
    public void setProduitService(final ProduitService<Produit> produitService)
    {
        this.produitService = produitService;
    }

}
