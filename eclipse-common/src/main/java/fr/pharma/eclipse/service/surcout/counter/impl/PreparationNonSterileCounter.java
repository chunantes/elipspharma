package fr.pharma.eclipse.service.surcout.counter.impl;

import java.io.Serializable;
import java.util.Calendar;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.service.surcout.counter.ActeCounter;

/**
 * Classe en charge de compter le nombre de preparations non steriles pour un essai ou un patient
 * dans un essai.
 
 * @version $Revision$ $Date$
 */
public class PreparationNonSterileCounter
    extends PreparationCounter
    implements ActeCounter, Serializable
{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2978785634267681162L;

    /**
     * Méthode en charge de compter le nombre de preparations non steriles pour l'essai (ou pour
     * le patient si le patient n'est pas null) dans l'intervalle en paramètre.
     * @param essai L'essai.
     * @param patient Le patient (si null alors le calcul se fait pour l'essai).
     * @param dateDebut Date Début de l'intervalle.
     * @param dateFin Date de fin de l'intervalle.
     * @return Le nombre de destructions.
     */
    public int process(final Essai essai,
                       final Patient patient,
                       final Calendar dateDebut,
                       final Calendar dateFin)
    {
        return super.process(essai,
                             patient,
                             dateDebut,
                             dateFin,
                             false);
    }

    /**
     * Méthode en charge de compter le nombre de preparations non steriles pour l'essai. Le nombre
     * de destructions par patient n'est pas accessible.
     * @param essai L'essai.
     * @param patient Le patient (si null alors le calcul se fait pour l'essai).
     * @param prevision Les données prévisionnelles.
     * @return Le nombre de destructions.
     */
    public int process(final Essai essai,
                       final Patient patient,
                       final DonneesPrevision prevision)
    {
        return prevision.getNbPreparationsNonSteriles();
    }

}
