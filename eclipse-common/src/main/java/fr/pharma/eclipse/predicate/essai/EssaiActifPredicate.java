package fr.pharma.eclipse.predicate.essai;

import java.util.Calendar;

import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.etat.DetailEtatEssai;

/**
 * Predicat en charge de déterminer si l'essai a été activé avant la date de fin
 * et qu'il n'a pas été cloturé avant la date de fin.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiActifPredicate implements Predicate {

    /**
     * Date de Fin.
     */
    private final Calendar dateFin;

    /**
     * Constructeur.
     * @param dateDebut Date de début.
     * @param dateFin Date de fin.
     */
    public EssaiActifPredicate(final Calendar dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate(final Object object) {
        final Essai essai = (Essai) object;

        boolean actif = false;

        // on regarde l'historique des etats.
        for (final DetailEtatEssai detail : essai.getDetailsEtatEssai()) {
            // s'il a été passé en cours, on vérfie que c'est avant la date de
            // fin.
            if (detail.getEtatEssai().equals(EtatEssai.EN_COURS) && detail.getDateMaj().before(this.dateFin)) {
                actif = true;
            }

            // s'il a été passé à cloturé avant la date de fin on retourne
            // false.
            if ((detail.getEtatEssai().equals(EtatEssai.CLOTURE) || detail.getEtatEssai().equals(EtatEssai.ARCHIVE)) && detail.getDateMaj().before(this.dateFin)) {
                return false;
            }

        }
        return actif;
    }

}
