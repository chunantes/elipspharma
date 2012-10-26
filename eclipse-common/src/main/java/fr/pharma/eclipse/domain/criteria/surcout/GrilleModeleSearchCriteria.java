package fr.pharma.eclipse.domain.criteria.surcout;

import java.util.Calendar;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;

/**
 * Critere de recherche pour le bean GrilleModele.
 
 * @version $Revision$ $Date$
 */
public class GrilleModeleSearchCriteria
    extends AbstractSearchCriteria
{

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -1701661742665905942L;

    /**
     * Date de validité.
     */
    private Calendar dateValidite;

    /**
     * Nom.
     */
    private String nom;

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear()
    {
        this.dateValidite = null;
        this.nom = null;
    }

    /**
     * Getter sur dateValidite.
     * @return Retourne le dateValidite.
     */
    public Calendar getDateValidite()
    {
        return this.dateValidite;
    }

    /**
     * Setter pour dateValidite.
     * @param dateValidite le dateValidite à écrire.
     */
    public void setDateValidite(final Calendar dateValidite)
    {
        this.dateValidite = dateValidite;
    }

    /**
     * Getter sur nom.
     * @return Retourne le nom.
     */
    public String getNom()
    {
        return this.nom;
    }

    /**
     * Setter pour nom.
     * @param nom le nom à écrire.
     */
    public void setNom(final String nom)
    {
        this.nom = nom;
    }

}
