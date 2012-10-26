package fr.pharma.eclipse.service.surcout;

import java.util.Calendar;

import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Interface de la façade d'appel aux méthodes permettant d'obtenir certains nombres pour les
 * calculs des surcouts.
 
 * @version $Revision$ $Date$
 */
public interface SurcoutFacade
{

    /**
     * Méthode en charge de compter le nombre de dispensations d'un essai dans l'intervalle des
     * dates.
     * @param essai L'essai.
     * @param dateDebut Date de début.
     * @param dateFin Date de fin.
     * @param inclu Patient inclu. Le patient peut avoir été désinclu.
     * @return Le nombre de dispensations.
     */
    public int countNbPatients(final Essai essai,
                               final Calendar dateDebut,
                               final Calendar dateFin,
                               final boolean inclu);

    /**
     * Méthode en charge de compter le nombre d'années.
     * @param essai L'essai.
     * @param dateDebut Date de début.
     * @param dateFin Date de fin.
     * @return Le nombre d'années.
     */
    int countNbAnnees(final Essai essai,
                      final Calendar dateDebut,
                      final Calendar dateFin);

    /**
     * Retourne <true> si la facturation de la premiere année est comprise dans l'intervalle en
     * paramètre.
     * @param essai L'essai.
     * @param dateDebut Date de début de l'intervalle.
     * @param dateFin Date de fin de l'intervalle.
     * @return <true> si la facturation de la premiere année est comprise dans l'intervalle en
     * paramètre.
     */
    boolean inPremiereAnnee(final Essai essai,
                            final Calendar dateDebut,
                            final Calendar dateFin);
}
