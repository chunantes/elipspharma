package fr.pharma.eclipse.domain.model.suivi.stockage;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Bean métier représentant les suivis de modifications sur le bean Pharmacie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "pharmacie_suivi")
public class PharmacieSuivi extends Suivi {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5642140098893756815L;

    /**
     * Objet Pharmacie.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pharmacie", nullable = false)
    @Index(name = "idx_suivi_pharmacie")
    private Pharmacie pharmacie;

    /**
     * Getter sur pharmacie.
     * @return Retourne le pharmacie.
     */
    public Pharmacie getPharmacie() {
        return this.pharmacie;
    }

    /**
     * Setter pour pharmacie.
     * @param pharmacie le pharmacie à écrire.
     */
    public void setPharmacie(final Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
    }

}
