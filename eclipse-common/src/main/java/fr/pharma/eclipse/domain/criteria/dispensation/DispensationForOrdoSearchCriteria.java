package fr.pharma.eclipse.domain.criteria.dispensation;

import java.util.Calendar;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Critère de recherche des dispensations pour l'ordonnancier de dispensation.
 
 * @version $Revision$ $Date$
 */
public class DispensationForOrdoSearchCriteria
    extends AbstractSearchCriteria
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1266701044594304643L;

    /**
     * Pharmacie.
     */
    private Pharmacie pharmacie;

    /**
     * Date de début du mouvement associé à la dispensation.
     */
    private Calendar dateDebut;

    /**
     * Date de fin du mouvement associé à la dispensation.
     */
    private Calendar dateFin;

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear()
    {
        this.setDateDebut(null);
        this.setDateFin(null);
        this.setPharmacie(null);
    }

    /**
     * Getter pour pharmacie.
     * @return Le pharmacie
     */
    public Pharmacie getPharmacie()
    {
        return this.pharmacie;
    }

    /**
     * Setter pour pharmacie.
     * @param pharmacie Le pharmacie à écrire.
     */
    public void setPharmacie(final Pharmacie pharmacie)
    {
        this.pharmacie = pharmacie;
    }

    /**
     * Getter pour dateDebut.
     * @return Le dateDebut
     */
    public Calendar getDateDebut()
    {
        return this.dateDebut;
    }

    /**
     * Setter pour dateDebut.
     * @param dateDebut Le dateDebut à écrire.
     */
    public void setDateDebut(final Calendar dateDebut)
    {
        this.dateDebut = dateDebut;
    }

    /**
     * Getter pour dateFin.
     * @return Le dateFin
     */
    public Calendar getDateFin()
    {
        return this.dateFin;
    }

    /**
     * Setter pour dateFin.
     * @param dateFin Le dateFin à écrire.
     */
    public void setDateFin(final Calendar dateFin)
    {
        this.dateFin = dateFin;
    }
}
