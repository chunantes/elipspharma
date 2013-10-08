package fr.pharma.eclipse.component.surcout.validator;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;

import fr.pharma.eclipse.domain.enums.surcout.TypeCout;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Validateur des regles de calcul de cout.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class RegleValidator implements Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -6388997703265682541L;

    /**
     * Utilitaire Faces.
     */
    @Resource(name = "facesUtils")
    private FacesUtils facesUtils;

    /**
     * Cout variable validator.
     */
    @Resource(name = "coutVariableValidator")
    private CoutVariableValidator coutVariableValidator;

    /**
     * Méthode en charge de valider une règle.
     * @param regle La règle à valider.
     * @return <true> si la règle est valide.
     */
    public boolean validate(final Regle regle) {
        boolean valid = true;
        if (regle.getType() == null) {
            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "regle.type.notEmpty");
            valid = false;
        } else if (regle.getType().equals(TypeCout.FIXE)) {
            if ((regle.getAnneesSuivantes() == null) || (regle.getPremiereAnnee() == null)) {
                this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "regle.valeurs.notEmpty");
                valid = false;
            }
        } else if (regle.getType().equals(TypeCout.VARIABLE)) {
            valid = this.coutVariableValidator.validate(regle);
        }

        if (regle.getPerimetre() == null) {
            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "regle.perimetre.notEmpty");
            valid = false;
        }
        // Valorisation du paramètre de retour dans la requête.
        this.facesUtils.putCallbackValidityParam(valid);
        return valid;
    }
    /**
     * Setter pour facesUtils.
     * @param facesUtils le facesUtils à écrire.
     */
    public void setFacesUtils(final FacesUtils facesUtils) {
        this.facesUtils = facesUtils;
    }
    /**
     * Setter pour coutVariableValidator.
     * @param coutVariableValidator le coutVariableValidator à écrire.
     */
    public void setCoutVariableValidator(final CoutVariableValidator coutVariableValidator) {
        this.coutVariableValidator = coutVariableValidator;
    }
}
