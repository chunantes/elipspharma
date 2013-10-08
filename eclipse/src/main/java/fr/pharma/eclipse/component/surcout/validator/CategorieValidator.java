package fr.pharma.eclipse.component.surcout.validator;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;

import fr.pharma.eclipse.domain.model.surcout.Categorie;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Validateur d'une catégorie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class CategorieValidator implements Serializable {

	
	private static final long serialVersionUID = -4271948792462654007L;

	/**
     * Utilitaire Faces.
     */
    @Resource(name = "facesUtils")
    private FacesUtils facesUtils;
    
    public void setFacesUtils(FacesUtils facesUtils) {
        this.facesUtils = facesUtils;
    }
    
    public boolean validate(Categorie categorie){
    	boolean valid = true;
    	if (categorie.getActe() == null || categorie.getActe().getLibelle().isEmpty()) {
            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "grilleModele.acte.notEmpty");
            valid = false;
        }
    	// Valorisation du paramètre de retour dans la requête.
        this.facesUtils.putCallbackValidityParam(valid);
        return valid;
    }
}
