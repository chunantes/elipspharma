package fr.pharma.eclipse.component.produit.validator;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;

import fr.pharma.eclipse.domain.criteria.design.PrescriptionTypeSearchCriteria;
import fr.pharma.eclipse.domain.criteria.prescription.ProduitPrescritSearchCriteria;
import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.service.common.GenericService;
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

        if (!this.prescriptionTypeService.getAll(criteriaPrescriptionType).isEmpty()) {
            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "remove.impossible");
            return false;
        }

        final ProduitPrescritSearchCriteria crit = new ProduitPrescritSearchCriteria();
        crit.setConditionnement(conditionnement);

        if (!this.produitPrescritTypeService.getAll(crit).isEmpty()) {
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

}
