package fr.pharma.eclipse.service.surcout.checker.impl;

import java.io.Serializable;

import fr.pharma.eclipse.domain.enums.ConditionConservation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.service.surcout.checker.SurcoutChecker;

/**
 * Classe en charge de déterminer si les coûts de conditions de conservations
 * doivent être calculés ou non.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ConditionConservationChecker implements SurcoutChecker, Serializable {
    /**
     * Serial UID.
     */
    private static final long serialVersionUID = 2253013872072014364L;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean check(final Essai essai) {

        for (final Produit p : essai.getDetailProduit().getProduits()) {
            final ConditionConservation c = p.getDetailLogistique().getConditionConservation();
            if ((c != null) && (c.equals(ConditionConservation.FROM_DEUX_TO_HUIT) || c.equals(ConditionConservation.MOINS_20) || c.equals(ConditionConservation.MOINS_30) || c.equals(ConditionConservation.MOINS_80))) {
                return true;
            }
        }

        return false;

    }

}
