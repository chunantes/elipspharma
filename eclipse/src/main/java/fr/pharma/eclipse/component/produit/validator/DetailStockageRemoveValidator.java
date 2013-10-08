package fr.pharma.eclipse.component.produit.validator;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;

import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.criteria.stock.RetourPatientSearchCriteria;
import fr.pharma.eclipse.domain.model.produit.detail.DetailStockage;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.service.stock.MvtStockService;
import fr.pharma.eclipse.service.stock.RetourPatientService;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Classe de validation de suppression d'un objet DetailStockage.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DetailStockageRemoveValidator implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2249834956549015567L;

    /**
     * Service des mouvements de stocks.
     */
    @Resource(name = "mouvementStockService")
    private MvtStockService<MvtStock> mvtService;

    /**
     * Service retour patient.
     */
    @Resource(name = "retourPatientService")
    private RetourPatientService retourPatientService;

    /**
     * Faces Utils.
     */
    @Resource(name = "facesUtils")
    private FacesUtils facesUtils;

    /**
     * {@inheritDoc}
     */
    public boolean validate(final DetailStockage detail) {
        boolean valid = true;
        if (detail.getId() != null) {
            final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
            criteria.setStockage(detail);
            if (!this.mvtService.getAll(criteria).isEmpty()) {
                this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "remove.impossible");
                valid = false;
            }

            final RetourPatientSearchCriteria criteria2 = new RetourPatientSearchCriteria();
            criteria2.setStockage(detail);
            if (!this.retourPatientService.getAll(criteria2).isEmpty() && valid) {
                this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "remove.impossible");
                valid = false;
            }
        }
        return valid;
    }

    /**
     * Setter pour mvtService.
     * @param mvtService Le mvtService à écrire.
     */
    public void setMvtService(final MvtStockService<MvtStock> mvtService) {
        this.mvtService = mvtService;
    }

    /**
     * Setter pour retourPatientService.
     * @param retourPatientService Le retourPatientService à écrire.
     */
    public void setRetourPatientService(final RetourPatientService retourPatientService) {
        this.retourPatientService = retourPatientService;
    }

    /**
     * Setter pour facesUtils.
     * @param facesUtils Le facesUtils à écrire.
     */
    public void setFacesUtils(final FacesUtils facesUtils) {
        this.facesUtils = facesUtils;
    }

}
