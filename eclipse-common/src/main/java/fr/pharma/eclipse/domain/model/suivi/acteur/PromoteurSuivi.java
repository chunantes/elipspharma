package fr.pharma.eclipse.domain.model.suivi.acteur;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Bean métier représentant les suivis de modifications sur le bean Promoteur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "promoteur_suivi")
public class PromoteurSuivi extends Suivi {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 8072246426397740978L;

    /**
     * Objet Investigateur.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_promoteur", nullable = false)
    @Index(name = "idx_suivi_promoteur")
    private Promoteur promoteur;

    /**
     * Getter sur promoteur.
     * @return Retourne le promoteur.
     */
    public Promoteur getPromoteur() {
        return this.promoteur;
    }

    /**
     * Setter pour promoteur.
     * @param promoteur le promoteur à écrire.
     */
    public void setPromoteur(final Promoteur promoteur) {
        this.promoteur = promoteur;
    }

}
