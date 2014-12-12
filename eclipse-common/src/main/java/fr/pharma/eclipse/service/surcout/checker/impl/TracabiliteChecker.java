package fr.pharma.eclipse.service.surcout.checker.impl;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.ProduitTherapeutique;
import fr.pharma.eclipse.service.surcout.checker.SurcoutChecker;

/**
 * Classe en charge de déterminer si la tracabilite doit être chiffrés ou non.
 * essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TracabiliteChecker implements SurcoutChecker, Serializable {
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
            if (p instanceof Medicament) {
                final Medicament m = (Medicament) p;
                if (m.getMds() || m.getStupefiant()) {
                    return true;
                }
            }
            if (p instanceof ProduitTherapeutique) {
                final ProduitTherapeutique pt = (ProduitTherapeutique) p;
                if (pt.getMds() || pt.getStupefiant()) {
                    return true;
                }
            }
        }

        return false;

    }

}
