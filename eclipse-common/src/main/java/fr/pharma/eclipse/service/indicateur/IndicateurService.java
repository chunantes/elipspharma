package fr.pharma.eclipse.service.indicateur;

import java.util.Calendar;
import java.util.SortedSet;

import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Service de gestion des indicateurs.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface IndicateurService {
    /**
     * Méthode en charge de retourner la liste des indicateurs générés trié par
     * ordre alphabétique.
     * @param pharmacie Pharmacie.
     * @param dateDebut Date de début de l'intervalle.
     * @param dateFin Date de fin de l'intervalle.
     * @return La liste des indicateurs générés.
     */
    public SortedSet<Indicateur> generateIndicateur(final Pharmacie pharmacie,
                                                    final Calendar dateDebut,
                                                    final Calendar dateFin);
}
