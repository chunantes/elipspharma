package fr.pharma.eclipse.domain.model.surcout;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Classe représentant un résultat pour les calculs de couts.
 
 * @version $Revision$ $Date$
 */
public class Resultat
    implements Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -2205838768806626045L;

    /**
     * Montant.
     */
    private BigDecimal montant;

    /**
     * Nombre d'actes.
     */
    private Integer nbActes;

    /**
     * Constructeur.
     */
    public Resultat()
    {
        this.montant = new BigDecimal(0);
        this.nbActes = 0;
    }

    /**
     * Getter sur montant.
     * @return Retourne le montant.
     */
    public BigDecimal getMontant()
    {
        return this.montant;
    }

    /**
     * Getter sur nbActes.
     * @return Retourne le nbActes.
     */
    public Integer getNbActes()
    {
        return this.nbActes;
    }

    /**
     * Setter pour montant.
     * @param montant le montant à écrire.
     */
    public void setMontant(final BigDecimal montant)
    {
        this.montant = montant;
    }

    /**
     * Setter pour nbActes.
     * @param nbActes le nbActes à écrire.
     */
    public void setNbActes(final Integer nbActes)
    {
        this.nbActes = nbActes;
    }

}
