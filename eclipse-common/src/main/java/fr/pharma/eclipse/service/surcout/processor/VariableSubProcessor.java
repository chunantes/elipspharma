package fr.pharma.eclipse.service.surcout.processor;

import java.util.Calendar;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.Resultat;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;

/**
 * Interface définissant le comportement des processor de cout variables spécifique en fonction du
 * mode de calcul.
 
 * @version $Revision$ $Date$
 */
public interface VariableSubProcessor
{
    /**
     * Méthode en charge de calculer le montant d'un cout reel à partir d'une regle et des
     * informations en paramètre.
     * @param item L'item.
     * @param regle La regle.
     * @param essai L'essai.
     * @param dateDebut La date de début.
     * @param dateFin La date de fin.
     * @return le montant du cout.
     */
    Resultat process(Item item,
                     Regle regle,
                     Essai essai,
                     Calendar dateDebut,
                     Calendar dateFin);
    /**
     * Méthode en charge de calculer le montant d'un cout previsionnel à partir d'une regle et des
     * informations en paramètre.
     * @param item L'item.
     * @param regle La regle.
     * @param essai L'essai.
     * @param prevision Les données prévisionnelles.
     * @return le montant du cout.
     */
    Resultat process(Item item,
                     final Regle regle,
                     final Essai essai,
                     final DonneesPrevision prevision);
}
