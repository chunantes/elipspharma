package fr.pharma.eclipse.domain.model.ordonnancier;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Bean abstrait contenant les champs de gestion des ordonnanciers.
 
 * @version $Revision$ $Date$
 */
@MappedSuperclass
public abstract class Ordonnancier
    extends Suivi
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -7960300941144806809L;

    /**
     * Pharmacie.
     */
    @ManyToOne
    @JoinColumn(name = "id_pharma", nullable = false)
    @NotNull
    private Pharmacie pharmacie;

    /**
     * Date de début.
     */
    @Column(name = "dateDebut")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Calendar dateDebut;

    /**
     * Date de fin.
     */
    @Column(name = "dateFin")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Calendar dateFin;

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

    /**
     * Méthode en charge de retourner la description d'un ordonnancier.
     * @return Description de l'ordonnancier.
     */
    public String getDescription()
    {
        final SimpleDateFormat sdf = new SimpleDateFormat(EclipseConstants.PATTERN_SIMPLE);
        final StringBuilder sb = new StringBuilder();
        sb.append(EclipseConstants.DU)
                .append(EclipseConstants.SPACE)
                .append(sdf.format(this.getDateDebut().getTime()))
                .append(EclipseConstants.SPACE)
                .append(EclipseConstants.AU)
                .append(EclipseConstants.SPACE)
                .append(sdf.format(this.getDateFin().getTime()));
        return sb.toString();
    }

}
