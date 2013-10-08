package fr.pharma.eclipse.domain.criteria.prescription;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;

/**
 * Critère de recherche sur ProduitPrescrit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ProduitPrescritSearchCriteria extends AbstractSearchCriteria {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 1542402167289694778L;

    /**
     * Conditionnement.
     */
    private Conditionnement conditionnement;

    /**
     * Produit.
     */
    private Produit produit;

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.conditionnement = null;
    }

    /**
     * Getter sur conditionnement.
     * @return Retourne le conditionnement.
     */
    public Conditionnement getConditionnement() {
        return this.conditionnement;
    }

    /**
     * Setter pour conditionnement.
     * @param conditionnement le conditionnement à écrire.
     */
    public void setConditionnement(final Conditionnement conditionnement) {
        this.conditionnement = conditionnement;
    }

    public Produit getProduit() {
        return this.produit;
    }

    public void setProduit(final Produit produit) {
        this.produit = produit;
    }

}
