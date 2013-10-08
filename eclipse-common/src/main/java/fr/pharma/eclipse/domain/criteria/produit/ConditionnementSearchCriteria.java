package fr.pharma.eclipse.domain.criteria.produit;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.produit.Produit;

/**
 * Critère de recherche sur Conditionnement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ConditionnementSearchCriteria extends AbstractSearchCriteria {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -997045680630442289L;

    /**
     * Produit.
     */
    private Produit produit;

    /**
     * Mode de prescription.
     */
    private ModePrescription modePrescription;

    /**
     * Getter sur modePrescription.
     * @return Retourne le modePrescription.
     */
    public ModePrescription getModePrescription() {
        return this.modePrescription;
    }

    /**
     * Setter pour modePrescription.
     * @param modePrescription le modePrescription à écrire.
     */
    public void setModePrescription(final ModePrescription modePrescription) {
        this.modePrescription = modePrescription;
    }

    /**
     * Getter sur produit.
     * @return Retourne le produit.
     */
    public Produit getProduit() {
        return this.produit;
    }

    /**
     * Setter pour produit.
     * @param produit le produit à écrire.
     */
    public void setProduit(final Produit produit) {
        this.produit = produit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.produit = null;
        this.modePrescription = null;
    }

}
