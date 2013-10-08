package fr.pharma.eclipse.validator.save.impl.essai;

import java.util.Calendar;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.dates.DetailDates;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.validator.save.SaveValidator;

/**
 * Validateur de sauvegarde d'essai : détail dates.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiDetailDatesSaveValidator implements SaveValidator<Essai> {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -9136773551303948953L;

    /**
     * Constante pour la partie commune des codes d'erreur.
     */
    private static final String ERRCODE_COMMON_PART = "essai.detailDates.validation.";

    /**
     * Constante pour la valeur des erreurs levées.
     */
    private static final String ERRVALUE = "invalid_debut_fin";

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final Essai essai,
                         final GenericService<Essai> essaiService) {
        final DetailDates detailDates = essai.getDetailDates();
        // début/fin prévisionnelles de l'étude
        this.validateDates(detailDates.getDebutEtudePrev(), detailDates.getFinEtudePrev(), "etude_prev", essai);
        // début/fin prévisionnelles de l'inclusion
        this.validateDates(detailDates.getDebutInclusionPrev(), detailDates.getFinInclusionPrev(), "inclusion_prev", essai);
        // début/fin rééelles de l'étude
        this.validateDates(detailDates.getDebutEtude(), detailDates.getFinEtude(), "etude_reel", essai);
        // début/fin réelles de l'inclusion
        this.validateDates(detailDates.getDebutInclusion(), detailDates.getFinInclusion(), "inclusion_reel", essai);
        // pré-clôture/clôture
        this.validateDates(detailDates.getPreCloture(), detailDates.getCloture(), "cloture", essai);
    }

    /**
     * Méthode en charge de valider deux dates : vérifie que la date de début <br>
     * est bien antérieure à la date de fin.
     * @param dateDebut Date de début.
     * @param dateFin Date de fin.
     * @param specificErrCodePart Partie spécifique du code d'erreur.
     * @param essai Essai en cours de validation.
     */
    private void validateDates(final Calendar dateDebut,
                               final Calendar dateFin,
                               final String specificErrCodePart,
                               final Essai essai) {
        if ((dateDebut != null) && (dateFin != null) && dateDebut.after(dateFin)) {
            final String errorCode = new StringBuilder(EssaiDetailDatesSaveValidator.ERRCODE_COMMON_PART).append(specificErrCodePart).toString();
            throw new ValidationException(errorCode, new String[]{EssaiDetailDatesSaveValidator.ERRVALUE, }, essai);
        }

    }

}
