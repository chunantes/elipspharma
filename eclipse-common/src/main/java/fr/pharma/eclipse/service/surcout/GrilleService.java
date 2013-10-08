package fr.pharma.eclipse.service.surcout;

import java.util.Calendar;
import java.util.Map;

import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.domain.model.surcout.Grille;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.Resultat;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Interface des services de gestion des grilles.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface GrilleService extends GenericService<Grille> {
    /**
     * Méthode en charge d'effectuer les calculs de couts réels sur la grille en
     * paramètre.
     * @param grille La grille.
     * @param dateDebut Date de début de l'intervalle de calcul.
     * @param dateFin Date de fin de l'intervalle de calcul.
     * @return Une Map associant un item et son cout.
     */
    Map<Item, Resultat> processReel(final Grille grille,
                                    final Calendar dateDebut,
                                    final Calendar dateFin);

    /**
     * Méthode en charge d'effectuer les calculs de couts prévisionnels sur la
     * grille en paramètre.
     * @param grille La grille.
     * @param prevision Les données prévisonnelles.
     * @return Une Map associant un item et son cout.
     */
    Map<Item, Resultat> processPrevision(Grille grille,
                                         DonneesPrevision prevision);
}
