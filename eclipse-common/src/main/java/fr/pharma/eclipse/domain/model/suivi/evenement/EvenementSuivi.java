package fr.pharma.eclipse.domain.model.suivi.evenement;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Bean métier représentant les suivis de modifications sur le bean Evenement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "evenement_suivi")
public class EvenementSuivi extends Suivi {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 4846457895726510430L;

    /**
     * Objet Evenement.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_evenement", nullable = false)
    @Index(name = "idx_suivi_evenement")
    private Evenement evenement;

    /**
     * Getter pour evenement.
     * @return Le evenement
     */
    public Evenement getEvenement() {
        return this.evenement;
    }

    /**
     * Setter pour evenement.
     * @param evenement Le evenement à écrire.
     */
    public void setEvenement(final Evenement evenement) {
        this.evenement = evenement;
    }

}
