package fr.pharma.eclipse.domain.criteria.produit;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.enums.produit.TypeProduit;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.stockage.Stockage;

/**
 * Critère de recherche sur Produit.
 
 * @version $Revision$ $Date$
 */
public class ProduitSearchCriteria
    extends AbstractSearchCriteria
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -538780110242632603L;

    /**
     * Essai.
     */
    private Essai essai;

    /**
     * Type de personne.
     */
    private TypeProduit typeProduit;

    /**
     * Stockage.
     */
    private Stockage stockage;

    /**
     * Stockage retour.
     */
    private Stockage stockageRetour;

    /**
     * Getter sur essai.
     * @return Retourne le essai.
     */
    public Essai getEssai()
    {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai le essai à écrire.
     */
    public void setEssai(final Essai essai)
    {
        this.essai = essai;
    }

    /**
     * Getter sur typeProduit.
     * @return Retourne le typeProduit.
     */
    public TypeProduit getTypeProduit()
    {
        return this.typeProduit;
    }

    /**
     * Setter pour typeProduit.
     * @param typeProduit le typeProduit à écrire.
     */
    public void setTypeProduit(final TypeProduit typeProduit)
    {
        this.typeProduit = typeProduit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear()
    {
        this.essai = null;
        this.typeProduit = null;

    }

    /**
     * Getter pour stockage.
     * @return Le stockage
     */
    public Stockage getStockage()
    {
        return this.stockage;
    }

    /**
     * Setter pour stockage.
     * @param stockage Le stockage à écrire.
     */
    public void setStockage(final Stockage stockage)
    {
        this.stockage = stockage;
    }

    /**
     * Getter pour stockageRetour.
     * @return Le stockageRetour
     */
    public Stockage getStockageRetour()
    {
        return this.stockageRetour;
    }

    /**
     * Setter pour stockageRetour.
     * @param stockageRetour Le stockageRetour à écrire.
     */
    public void setStockageRetour(final Stockage stockageRetour)
    {
        this.stockageRetour = stockageRetour;
    }

}
