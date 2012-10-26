package fr.pharma.eclipse.domain.model.stock;

import java.io.Serializable;

/**
 * Bean représentant les informations de numéro de traitement.
 
 * @version $Revision$ $Date$
 */
public class NumTraitement
    implements Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -7848467283474929113L;

    /**
     * Numéro de traitement.
     */
    private String numTraitement;

    /**
     * Quantité.
     */
    private Integer quantite;

    /**
     * Getter pour numTraitement.
     * @return Le numTraitement
     */
    public String getNumTraitement()
    {
        return this.numTraitement;
    }

    /**
     * Setter pour numTraitement.
     * @param numTraitement Le numTraitement à écrire.
     */
    public void setNumTraitement(final String numTraitement)
    {
        this.numTraitement = numTraitement;
    }

    /**
     * Getter pour quantite.
     * @return Le quantite
     */
    public Integer getQuantite()
    {
        return this.quantite;
    }

    /**
     * Setter pour quantite.
     * @param quantite Le quantite à écrire.
     */
    public void setQuantite(final Integer quantite)
    {
        this.quantite = quantite;
    }

}
