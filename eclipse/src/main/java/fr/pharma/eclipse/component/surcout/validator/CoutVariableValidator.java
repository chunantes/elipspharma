package fr.pharma.eclipse.component.surcout.validator;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;

import fr.pharma.eclipse.domain.model.surcout.regle.Regle;
import fr.pharma.eclipse.utils.FacesUtils;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Validateur de couts variables.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class CoutVariableValidator implements Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 3520619873952508937L;

    /**
     * Faces utils.
     */
    @Resource(name = "facesUtils")
    private FacesUtils facesUtils;

    /**
     * Méthode en charge de valider une règle de type CoutVariable.
     * @param regle La regle à valider.
     * @return <true> si la règle est valide.
     */
    public boolean validate(final Regle regle) {
        boolean valid = true;

        // mode.
        if (regle.getMode() == null) {
            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "regle.mode.notEmpty");
            valid = false;
        } else {
            valid = this.applyValidator(regle, "min", "max", "montant");
        }

        return valid;
    }

    /**
     * Méthode en charge d'effectuer la validation sur une règle et les champs
     * en paramètre.
     * @param regle La règle.
     * @param min Le champs min.
     * @param max Le champs max.
     * @param montant Le champs montant.
     * @return <true> si la règle est valide.
     */
    private boolean applyValidator(final Regle regle,
                                   final String min,
                                   final String max,
                                   final String montant) {
        boolean valid = true;

        final Integer minValue = (Integer) BeanTool.getPropriete(regle, min);
        final Integer maxValue = (Integer) BeanTool.getPropriete(regle, max);
        final BigDecimal montantValue = (BigDecimal) BeanTool.getPropriete(regle, montant);

        // si c'est du lot forfaitaire
        if (montantValue == null) {

            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "regle.montant.notEmpty");
            valid = false;
        }
        if ((minValue == null) && (maxValue == null)) {

            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "regle.borne.notEmpty");
            valid = false;
        } else if ((minValue != null) && (maxValue != null) && (minValue > maxValue)) {

            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "regle.borne.incoherent");
            valid = false;
        }

        return valid;
    }

    /**
     * Setter pour facesUtils.
     * @param facesUtils le facesUtils à écrire.
     */
    public void setFacesUtils(final FacesUtils facesUtils) {
        this.facesUtils = facesUtils;
    }

}
