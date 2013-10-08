package fr.pharma.eclipse.validator.remove.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.prescription.ProduitPrescritSearchCriteria;
import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.stock.MvtStockService;
import fr.pharma.eclipse.validator.remove.RemoveValidator;

/**
 * Classe de validation de suppression d'un objet Produit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ProduitRemoveValidator implements RemoveValidator<Produit>, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2249834956549015567L;

    /**
     * Service Dispensation.
     */
    @Resource(name = "produitPrescritService")
    private GenericService<ProduitPrescrit> produitPrescritService;

    /**
     * Service Dispensation.
     */
    @Resource(name = "mouvementStockService")
    private MvtStockService mvtStockService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final Produit produit) {
        final MvtStockSearchCriteria mvtStockCriteria = new MvtStockSearchCriteria();
        mvtStockCriteria.setProduit(produit);
        if (!this.mvtStockService.getAll(mvtStockCriteria).isEmpty()) {

            throw new ValidationException("remove", new String[]{"impossible" }, produit);
        }

        final ProduitPrescritSearchCriteria crit = new ProduitPrescritSearchCriteria();
        crit.setProduit(produit);
        if (!this.produitPrescritService.getAll(crit).isEmpty()) {

            throw new ValidationException("remove", new String[]{"impossible" }, produit);
        }
    }

    public void setMvtStockService(final MvtStockService mvtStockService) {
        this.mvtStockService = mvtStockService;
    }

    public void setProduitPrescritService(final GenericService<ProduitPrescrit> produitPrescritService) {
        this.produitPrescritService = produitPrescritService;
    }

}
