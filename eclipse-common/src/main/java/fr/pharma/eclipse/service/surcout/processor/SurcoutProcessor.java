package fr.pharma.eclipse.service.surcout.processor;

import java.util.Calendar;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.Resultat;

/**
 * Interface définissant le comportement des processors en charge d'appliquer les règles de
 * calculs des surcouts.
 
 * @version $Revision$ $Date$
 */
public interface SurcoutProcessor
{
    /**
     * Méthode en charge d'appliquer la règle de calcul sur l'essai réel.
     * @param essai L'essai.
     * @param dateDebut Date de début de l'intervalle sélectionné.
     * @param dateFin Date de fin de l'intervalle sélectionné.
     * @return le resultat.
     */
    Resultat process(final Item item,
                     final Essai essai,
                     final Calendar dateDebut,
                     final Calendar dateFin);
    /**
     * Méthode en charge d'appliquer la règle de calcul sur les prévisions.
     * @param essai L'essai.
     * @param prevision Les données prévisionnelles.
     * @return le resultat.
     */
    Resultat process(final Item item,
                     final Essai essai,
                     DonneesPrevision prevision);

}
