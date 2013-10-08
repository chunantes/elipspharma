package fr.pharma.eclipse.service.indicateur.builder;

import java.util.Calendar;

import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Interfaces des builder en charge de construire les indicateurs.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface IndicateurBuilder {
    /**
     * Méthode en charge de construire l'indicateur en fonction des paramètres.
     * @param pharmacie La pharmacie.
     * @param dateDebut La date de début de l'intervalle.
     * @param dateFin La date de fin de l'intervalle.
     * @return L'indicateur.
     */
    public Indicateur build(Pharmacie pharmacie,
                            Calendar dateDebut,
                            Calendar dateFin);
}
