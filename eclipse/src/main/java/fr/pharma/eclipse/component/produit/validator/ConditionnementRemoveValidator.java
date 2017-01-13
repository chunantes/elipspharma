package fr.pharma.eclipse.component.produit.validator;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;

import fr.pharma.eclipse.domain.criteria.design.PrescriptionTypeSearchCriteria;
import fr.pharma.eclipse.domain.criteria.prescription.ProduitPrescritSearchCriteria;
import fr.pharma.eclipse.domain.criteria.stock.LigneStockSearchCriteria;
import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.stock.MvtStockService;
import fr.pharma.eclipse.service.stock.StockService;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Classe de validation de suppression d'un objet Conditionnement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ConditionnementRemoveValidator implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2249834956549015567L;

    /**
     * Service prescriptionType.
     */
    @Resource(name = "prescriptionTypeService")
    private GenericService<PrescriptionType> prescriptionTypeService;

    /**
     * Service prescription.
     */
    @Resource(name = "produitPrescritService")
    private GenericService<ProduitPrescrit> produitPrescritTypeService;
    
    /**
     * Service mouvements stock.
     */
    @Resource(name = "mouvementStockService")
    private MvtStockService<MvtStock> mvtStockService;
    
    /**
     * Service Lignes stock.
     */
    @Resource(name = "stockService")
    private StockService ligneStockService;

    /**
     * Faces utils.
     */
    @Resource(name = "facesUtils")
    private FacesUtils facesUtils;

    /**
     * {@inheritDoc}
     */
    public boolean validate(final Conditionnement conditionnement) {
        if (conditionnement.getId() == null) {
            return true;
        }
        final boolean result = true;
        final PrescriptionTypeSearchCriteria criteriaPrescriptionType = new PrescriptionTypeSearchCriteria();
        criteriaPrescriptionType.setConditionnement(conditionnement);

        if (this.prescriptionTypeService.count(criteriaPrescriptionType) > 0) {
            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "remove.impossible");
            return false;
        }

        final ProduitPrescritSearchCriteria crit = new ProduitPrescritSearchCriteria();
        crit.setConditionnement(conditionnement);

        if (this.produitPrescritTypeService.count(crit) > 0) {
            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "remove.impossible");
            return false;
        }
        
        final MvtStockSearchCriteria mvtStockSearchCriteria = new MvtStockSearchCriteria();
        mvtStockSearchCriteria.setConditionnement(conditionnement);

        if(this.mvtStockService.count(mvtStockSearchCriteria) > 0){
        	this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "remove.impossible");
        	return false;
        }
        
        final LigneStockSearchCriteria ligneStockSearchCriteria = new LigneStockSearchCriteria();
        ligneStockSearchCriteria.setConditionnement(conditionnement);
        ligneStockSearchCriteria.setDatePeremptionNA(true);
        ligneStockSearchCriteria.setNumTraitementNA(true);
        
        if(this.ligneStockService.count(ligneStockSearchCriteria) > 0 ){
        	 this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "remove.impossible");
             return false;
        }
        
        return result;

    }

    /**
     * Setter pour prescriptionTypeService.
     * @param prescriptionTypeService Le prescriptionTypeService à écrire.
     */
    public void setPrescriptionTypeService(final GenericService<PrescriptionType> prescriptionTypeService) {
        this.prescriptionTypeService = prescriptionTypeService;
    }

    /**
     * Setter pour produitPrescritTypeService.
     * @param produitPrescritTypeService Le produitPrescritTypeService à écrire.
     */
    public void setProduitPrescritTypeService(final GenericService<ProduitPrescrit> produitPrescritTypeService) {
        this.produitPrescritTypeService = produitPrescritTypeService;
    }

    /**
     * Setter pour facesUtils.
     * @param facesUtils Le facesUtils à écrire.
     */
    public void setFacesUtils(final FacesUtils facesUtils) {
        this.facesUtils = facesUtils;
    }

	/**
	 * @return the mvtStockService
	 */
	public MvtStockService<MvtStock> getMvtStockService() {
		return mvtStockService;
	}

	/**
	 * @param mvtStockService the mvtStockService to set
	 */
	public void setMvtStockService(MvtStockService<MvtStock> mvtStockService) {
		this.mvtStockService = mvtStockService;
	}

	/**
	 * @return the ligneStockService
	 */
	public StockService getLigneStockService() {
		return ligneStockService;
	}

	/**
	 * @param ligneStockService the ligneStockService to set
	 */
	public void setLigneStockService(StockService ligneStockService) {
		this.ligneStockService = ligneStockService;
	}

}
