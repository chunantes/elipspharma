package fr.pharma.eclipse.domain.model.stock;

import java.io.Serializable;
import java.util.Calendar;

import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Etat d'une ligne de stock (correspondant à un n° de lot.
 
 * @version $Revision$ $Date$
 */
public class EtatLigneStock
    implements Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -7326351034910156797L;

    /**
     * Numero de lot.
     */
    private String numLot;

    /**
     * Numero de traitement si présent.
     */
    private String numTraitement;

    /**
     * Date de péremption.
     */
    private Calendar datePeremption;

    /**
     * Quantité en stock.
     */
    private Integer qteEnStock;

    /**
     * Constructeur.
     * @param numLot Numero de lot.
     * @param numTraitement Numero de lot.
     * @param datePeremption Date de péremption.
     * @param qte Quantité.
     */
    public EtatLigneStock(
                          final String numLot,
                          final String numTraitement,
                          final Calendar datePeremption,
                          final Integer qte)
    {
        this.numLot = numLot;
        if (numTraitement != null)
        {
            this.numTraitement = numTraitement;
        }
        else
        {
            this.numTraitement = EclipseConstants.NON_APPLICABLE;
        }
        this.datePeremption = datePeremption;
        this.qteEnStock = qte;
    }

    /**
     * Getter sur numLot.
     * @return Retourne le numLot.
     */
    public String getNumLot()
    {
        return this.numLot;
    }

    /**
     * Getter sur numTraitement.
     * @return Retourne le numTraitement.
     */
    public String getNumTraitement()
    {
        return this.numTraitement;
    }

    /**
     * Getter sur datePeremption.
     * @return Retourne le datePeremption.
     */
    public Calendar getDatePeremption()
    {
        return this.datePeremption;
    }

    /**
     * Getter sur qteEnStock.
     * @return Retourne le qteEnStock.
     */
    public Integer getQteEnStock()
    {
        return this.qteEnStock;
    }

    /**
     * Setter pour numLot.
     * @param numLot le numLot à écrire.
     */
    public void setNumLot(final String numLot)
    {
        this.numLot = numLot;
    }

    /**
     * Setter pour numTraitement.
     * @param numTraitement le numTraitement à écrire.
     */
    public void setNumTraitement(final String numTraitement)
    {
        this.numTraitement = numTraitement;
    }

    /**
     * Setter pour datePeremption.
     * @param datePeremption le datePeremption à écrire.
     */
    public void setDatePeremption(final Calendar datePeremption)
    {
        this.datePeremption = datePeremption;
    }

    /**
     * Setter pour qteEnStock.
     * @param qteEnStock le qteEnStock à écrire.
     */
    public void setQteEnStock(final Integer qteEnStock)
    {
        this.qteEnStock = qteEnStock;
    }

}
