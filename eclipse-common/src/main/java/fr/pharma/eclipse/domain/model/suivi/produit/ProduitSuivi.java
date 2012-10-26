package fr.pharma.eclipse.domain.model.suivi.produit;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Bean métier représentant les suivis de modifications sur le bean Produit.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "produit_suivi")
public class ProduitSuivi
    extends Suivi
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8202610546008352361L;

    /**
     * Objet Essai.
     */
    @ManyToOne()
    @JoinColumn(name = "id_produit", nullable = false)
    @Index(name = "idx_suivi_produit")
    private Produit produit;

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

}
