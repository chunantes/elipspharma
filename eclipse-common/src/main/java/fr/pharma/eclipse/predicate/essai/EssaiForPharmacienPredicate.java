package fr.pharma.eclipse.predicate.essai;

import java.io.Serializable;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Classe en charge de retourner si oui ou non, l'essai est lié à une des
 * pharmacie du pharmacien de référence.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiForPharmacienPredicate implements Predicate, Serializable

{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 9213013041661151543L;

    /**
     * Pharamcie à rechercher.
     */
    private final Pharmacien pharmacien;

    /**
     * Constructeur .
     * @param pharmacien Le pharmacien de référence.
     */
    public EssaiForPharmacienPredicate(final Pharmacien pharmacien) {
        this.pharmacien = pharmacien;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate(final Object object) {
        final Essai essai = (Essai) object;
        if (this.pharmacien.getPharmacies().contains(essai.getPharmaciePrincipale())) {
            return true;
        } else {
            return CollectionUtils.containsAny(essai.getDetailDonneesPharma().getPharmacies(), this.pharmacien.getPharmacies());
        }
    }

}
