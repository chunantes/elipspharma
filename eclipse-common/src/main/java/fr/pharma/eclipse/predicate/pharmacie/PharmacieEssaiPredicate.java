package fr.pharma.eclipse.predicate.pharmacie;

import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Predicate en charge de filtrer les essais qui ne sont pas associé à la
 * pharmacie de référence. Le prédicat retourne false, cela signifie que la
 * pharmacie n'est pas associé à l'essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PharmacieEssaiPredicate implements Predicate {

    /**
     * Pharmacie.
     */
    private final Pharmacie pharmacie;

    /**
     * Constructeur.
     * @param pharmacie La pharmacie.
     */
    public PharmacieEssaiPredicate(final Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate(final Object object) {
        final Essai essai = (Essai) object;
        return essai.getPharmaciePrincipale().equals(this.pharmacie) || essai.getDetailDonneesPharma().getPharmacies().contains(this.pharmacie);
    }

}
