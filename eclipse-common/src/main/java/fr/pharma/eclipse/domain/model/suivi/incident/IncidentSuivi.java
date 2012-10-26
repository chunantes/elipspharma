package fr.pharma.eclipse.domain.model.suivi.incident;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.incident.Incident;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Bean métier représentant les suivis de modifications sur le bean Incident.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "incident_suivi")
public class IncidentSuivi
    extends Suivi
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 4846457895726510430L;

    /**
     * Objet Evenement.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_incident", nullable = false)
    @Index(name = "idx_suivi_incident")
    private Incident incident;

    /**
     * Getter pour evenement.
     * @return Le evenement
     */
    public Incident getIncident()
    {
        return this.incident;
    }

    /**
     * Setter pour incident.
     * @param evenement Le incident à écrire.
     */
    public void setIncident(final Incident incident)
    {
        this.incident = incident;
    }

}
