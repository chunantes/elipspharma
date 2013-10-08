package fr.pharma.eclipse.service.surcout.counter.impl;

import java.io.Serializable;
import java.util.Calendar;

import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.enums.evenement.TypeVisite;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.service.surcout.counter.ActeCounter;

/**
 * Classe en charge de compter le nombre de visites de type audit (interne ou
 * externe) pour un essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AuditCounter extends EvenementCounter implements ActeCounter, Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2404915481729863287L;

    /**
     * Méthode en charge de compter le nombre de visites de type audit (interne
     * ou externe) pour l'essai (ou pour le patient si le patient n'est pas
     * null) dans l'intervalle en paramètre.
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
        // on vérifie que la règle est bien de perimètre "par essai"
        this.checkPatient(patient);

        final int nbAuditsExternes = this.nbVisites(essai, patient, dateDebut, dateFin, TypeVisite.AUDIT_EXTERNE);

        final int nbAuditsInternes = this.nbVisites(essai, patient, dateDebut, dateFin, TypeVisite.AUDIT_INTERNE);

        return nbAuditsExternes + nbAuditsInternes;
    }

    /**
     * Méthode en charge de compter le nombre de visites de type audit (interne
     * ou externe) pour l'essai. Le nombre de destructions par patient n'est pas
     * accessible.
     * @param essai L'essai.
     * @param patient Le patient (si null alors le calcul se fait pour l'essai).
     * @param prevision Les données prévisionnelles.
     * @return Le nombre de destructions.
     */
    @Override
    public int process(final Essai essai,
                       final Patient patient,
                       final DonneesPrevision prevision) {
        return prevision.getNbAudits();
    }

    /**
     * Calcul le nombre de visites d'un essai.
     * @param essai
     * @param patient
     * @param dateDebut
     * @param dateFin
     * @param suiviPhamacie
     * @param typeVisite
     */
    private int nbVisites(final Essai essai,
                          final Patient patient,
                          final Calendar dateDebut,
                          final Calendar dateFin,
                          final TypeVisite typeVisite) {
        return super.process(TypeEvenement.VISITE, typeVisite, essai, patient, dateDebut, dateFin).size();
    }
}
