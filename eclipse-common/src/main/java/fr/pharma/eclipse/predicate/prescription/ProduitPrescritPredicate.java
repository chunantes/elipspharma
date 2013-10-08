package fr.pharma.eclipse.predicate.prescription;

import java.io.Serializable;

import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;

/**
 * Classe de prédicat sur les ProduitsPrescrits. Il repère si un produit
 * prescrit avec le meme produit et le meme mode de prescription est déjà
 * présent.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ProduitPrescritPredicate implements Predicate, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -6732278702953860464L;

    /**
     * Produit prescrit à controler.
     */
    private final ProduitPrescrit produitPrescrit;

    /**
     * Constructeur.
     * @param produitPrescrit ProduitPrescrit à controler.
     */
    public ProduitPrescritPredicate(final ProduitPrescrit produitPrescrit) {
        this.produitPrescrit = produitPrescrit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate(final Object object) {
        final ProduitPrescrit p = (ProduitPrescrit) object;

        if (p.getProduit().getId().equals(this.produitPrescrit.getProduit().getId())
            && p.getConditionnement().getModePrescription().equals(this.produitPrescrit.getConditionnement().getModePrescription())) {
            return true;
        }
        return false;

    }
}
