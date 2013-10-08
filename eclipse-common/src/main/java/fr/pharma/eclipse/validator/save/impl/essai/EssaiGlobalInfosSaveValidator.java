package fr.pharma.eclipse.validator.save.impl.essai;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.validator.save.SaveValidator;

/**
 * Validateur de sauvegarde d'essai : informations générales.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiGlobalInfosSaveValidator implements SaveValidator<Essai>, Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5324562291444667875L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final Essai essai,
                         final GenericService<Essai> essaiService) {
        // Vérification de la sélection d'au moins un service.
        if (essai.getServices().isEmpty()) {
            throw new ValidationException("essai.service", new String[]{"notEmpty" }, essai);
        }
        if (StringUtils.isBlank(essai.getNumInterne())) {
            throw new ValidationException("essai.numInterne", new String[]{"notEmpty" }, essai);
        }
        if (StringUtils.isBlank(essai.getNumInterne())) {
            throw new ValidationException("essai.numInterne", new String[]{"notEmpty" }, essai);
        }
        if (StringUtils.isBlank(essai.getCodePromoteur())) {
            throw new ValidationException("essai.codePromoteur", new String[]{"notEmpty" }, essai);
        }
        if (StringUtils.isBlank(essai.getNom())) {
            throw new ValidationException("essai.nom", new String[]{"notEmpty" }, essai);
        }
        if (essai.getPromoteur() == null) {
            throw new ValidationException("essai.promoteur", new String[]{"notEmpty" }, essai);
        }
        if (essai.getTypePromoteur() == null) {
            throw new ValidationException("essai.typePromoteur", new String[]{"notEmpty" }, essai);
        }
        if (essai.getPharmaciePrincipale() == null) {
            throw new ValidationException("essai.pharmaciePrincipale", new String[]{"notEmpty" }, essai);
        }

    }

}
