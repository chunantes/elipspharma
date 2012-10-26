package fr.pharma.eclipse.domain.model.indicateur;

import java.io.Serializable;
import java.math.BigDecimal;

import fr.pharma.eclipse.domain.model.common.BeanWithNom;

/**
 * Bean indicateur.
 
 * @version $Revision$ $Date$
 */
public class Indicateur
    implements Serializable, BeanWithNom
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 7557342377339315405L;

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Valeur.
     */
    private BigDecimal valeur;

    /**
     * Constructeur avec arguments.
     * @param libelle Le libellé.
     * @param valeur La valeur.
     */
    public Indicateur(final String libelle, final BigDecimal valeur)
    {
        this.libelle = libelle;
        this.valeur = valeur;
    }

    /**
     * Getter pour libelle.
     * @return Le libelle
     */
    public String getLibelle()
    {
        return this.libelle;
    }

    /**
     * Setter pour libelle.
     * @param libelle Le libelle à écrire.
     */
    public void setLibelle(final String libelle)
    {
        this.libelle = libelle;
    }

    /**
     * Getter pour valeur.
     * @return Le valeur
     */
    public BigDecimal getValeur()
    {
        return this.valeur;
    }

    /**
     * Setter pour valeur.
     * @param valeur Le valeur à écrire.
     */
    public void setValeur(final BigDecimal valeur)
    {
        this.valeur = valeur;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNom()
    {
        return this.libelle;
    }

}
