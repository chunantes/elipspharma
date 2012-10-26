package fr.pharma.eclipse.domain.criteria.design;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;

/**
 * Critère de recherche sur PrescriptionType.
 
 * @version $Revision$ $Date$
 */
public class PrescriptionTypeSearchCriteria
    extends AbstractSearchCriteria
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 5153759446259664654L;

    /**
     * Produit.
     */
    private Produit produit;

    /**
     * Conditionnement.
     */
    private Conditionnement conditionnement;

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear()
    {
        this.produit = null;
    }

    /**
     * Getter sur produit.
     * @return Retourne le produit.
     */
    public Produit getProduit()
    {
        return this.produit;
    }

    /**
     * Setter pour produit.
     * @param produit le produit à écrire.
     */
    public void setProduit(final Produit produit)
    {
        this.produit = produit;
    }

    /**
     * Getter sur conditionnement.
     * @return Retourne le conditionnement.
     */
    public Conditionnement getConditionnement()
    {
        return this.conditionnement;
    }

    /**
     * Setter pour conditionnement.
     * @param conditionnement le conditionnement à écrire.
     */
    public void setConditionnement(final Conditionnement conditionnement)
    {
        this.conditionnement = conditionnement;
    }

}
