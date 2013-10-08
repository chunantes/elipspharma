package fr.pharma.eclipse.predicate.stock;

import java.io.Serializable;

import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stock.Sortie;

/**
 * Predicat sur les beans de sortie. <br />
 * Travaille sur le produit et le conditionnement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SortiePredicate implements Predicate, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1166105640730800753L;

    /**
     * Sortie de référence de comparaison pour le prédicat.
     */
    private final Sortie sortie;

    /**
     * Constructeur.
     * @param sortie Sortie.
     */
    public SortiePredicate(final Sortie sortie) {
        this.sortie = sortie;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate(final Object object) {
        final MvtStock mvtSortie = this.sortie.getMvtSortie();
        final Sortie s = (Sortie) object;
        final MvtStock mvt = s.getMvtSortie();
        return mvt.getProduit().equals(mvtSortie.getProduit()) && mvt.getConditionnement().equals(mvtSortie.getConditionnement());
    }
}
