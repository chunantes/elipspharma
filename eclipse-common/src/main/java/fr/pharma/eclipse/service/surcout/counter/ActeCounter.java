package fr.pharma.eclipse.service.surcout.counter;

import java.util.Calendar;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;

/**
 * Interface commune des processors en charge de compter le nombre d'actes d'un
 * essai ou d'un patient.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface ActeCounter {
    /**
     * Méthode en charge de calculer le nombre d'acte par essai ou par patient
     * (si patient != null).
     * @param essai L'essai.
     * @param patient Le patient.
     * @param dateDebut Date de début de l'intervalle.
     * @param dateFin Date de fin de l'intervalle.
     * @return Le nombre d'actes.
     */
    int process(final Essai essai,
                final Patient patient,
                final Calendar dateDebut,
                final Calendar dateFin);
    /**
     * Méthode en charge de calculer le nombre d'acte prévisionnel.
     * @param essai L'essai.
     * @param patient Le patient.
     * @param prevision Les données prévisionnelles..
     * @return Le nombre d'actes.
     */
    int process(final Essai essai,
                final Patient patient,
                final DonneesPrevision prevision);
}
