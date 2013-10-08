package fr.pharma.eclipse.service.surcout.counter.impl;

import java.io.Serializable;
import java.util.Calendar;

import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.service.surcout.counter.ActeCounter;

/**
 * Classe en charge de compter le nombre de destructions pour un essai ou un
 * patient dans un essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DestructionCounter extends EvenementCounter implements ActeCounter, Serializable {
    /**
     * Serial UID.
     */
    private static final long serialVersionUID = 2253013872072014364L;

    /**
     * Méthode en charge de compter le nombre de destructions pour l'essai (ou
     * pour le patient si le patient n'est pas null) dans l'intervalle en
     * paramètre.
     * @param essai L'essai.
     * @param patient Le patient (si null alors le calcul se fait pour l'essai).
     * @param dateDebut Date Début de l'intervalle.
     * @param dateFin Date de fin de l'intervalle.
     * @return Le nombre de destructions.
     */
    @Override
    public int process(final Essai essai,
                       final Patient patient,
                       final Calendar dateDebut,
                       final Calendar dateFin) {
        return super.process(TypeEvenement.DESTRUCTION, null, essai, patient, dateDebut, dateFin).size();
    }

    /**
     * Méthode en charge de compter le nombre de destructions prévisionnels pour
     * l'essai. Le nombre de destructions par patient n'est pas accessible.
     * @param essai L'essai.
     * @param patient Le patient (si null alors le calcul se fait pour l'essai).
     * @param prevision Les données prévisionnelles.
     * @return Le nombre de destructions.
     */
    @Override
    public int process(final Essai essai,
                       final Patient patient,
                       final DonneesPrevision prevision) {
        this.checkPatient(patient);
        return prevision.getNbDestructions();
    }

}
