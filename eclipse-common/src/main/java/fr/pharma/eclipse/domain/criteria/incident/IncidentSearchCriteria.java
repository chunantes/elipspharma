package fr.pharma.eclipse.domain.criteria.incident;

import java.util.Calendar;
import java.util.List;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Critère de recherche sur Incident.
 
 * @version $Revision$ $Date$
 */
public class IncidentSearchCriteria
    extends AbstractSearchCriteria
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6800377468370162836L;

    /**
     * Essai.
     */
    private Essai essai;

    /**
     * Liste d'essais.
     */
    private List<Essai> essais;

    /**
     * Date de début de la recherche.
     */
    private Calendar dateDebut;

    /**
     * Date de fin de la recherche.
     */
    private Calendar dateFin;

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear()
    {
        this.setEssai(null);
        this.setDateDebut(null);
        this.setDateFin(null);
        this.setEssais(null);
    }

    /**
     * Getter pour essai.
     * @return Le essai
     */
    public Essai getEssai()
    {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai Le essai à écrire.
     */
    public void setEssai(final Essai essai)
    {
        this.essai = essai;
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
     * Getter pour essais.
     * @return Le essais
     */
    public List<Essai> getEssais()
    {
        return this.essais;
    }

    /**
     * Setter pour essais.
     * @param essais Le essais à écrire.
     */
    public void setEssais(final List<Essai> essais)
    {
        this.essais = essais;
    }

}
