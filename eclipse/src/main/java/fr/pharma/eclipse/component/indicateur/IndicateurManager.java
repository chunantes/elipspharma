package fr.pharma.eclipse.component.indicateur;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;

import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Manager lié aux indicateurs.
 
 * @version $Revision$ $Date$
 */
public class IndicateurManager
    implements Serializable
{

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 8853155692467547507L;

    /**
     * Pharmacie.
     */
    private Pharmacie pharmacie;

    /**
     * Date Debut.
     */
    private Calendar dateDebut;

    /**
     * Date fin.
     */
    private Calendar dateFin;

    /**
     * Liste des indicateurs à afficher.
     */
    private Collection<Indicateur> indicateurs;

    /**
     * Méthode d'initialisation du manager.
     */
    public void init()
    {
        this.indicateurs = null;
        this.pharmacie = null;
        this.dateDebut = null;
        this.dateFin = null;
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

    /**
     * Getter pour indicateurs.
     * @return Le indicateurs
     */
    public Collection<Indicateur> getIndicateurs()
    {
        return this.indicateurs;
    }

    /**
     * Setter pour indicateurs.
     * @param indicateurs Le indicateurs à écrire.
     */
    public void setIndicateurs(final Collection<Indicateur> indicateurs)
    {
        this.indicateurs = indicateurs;
    }

}
