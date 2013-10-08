package fr.pharma.eclipse.domain.model.sigrec.acteur;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.sigrec.common.IntervenantSigrec;

/**
 * Classe du modèle d'import SIGREC représentant un ARC promoteur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "arc_promoteur_sigrec")
public class ARCPromoteurSigrec extends IntervenantSigrec implements Contactable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -8346341002237934459L;

    /**
     * Promoteur.
     */
    @ManyToOne
    @JoinColumn(name = "id_promoteur")
    @Index(name = "idx_promoteur_arc_promoteur_sigrec")
    private PromoteurSigrec promoteur;

    /**
     * Getter sur promoteur.
     * @return Retourne le promoteur.
     */
    public PromoteurSigrec getPromoteur() {
        return this.promoteur;
    }

    /**
     * Setter pour promoteur.
     * @param promoteur le promoteur à écrire.
     */
    public void setPromoteur(final PromoteurSigrec promoteur) {
        this.promoteur = promoteur;
    }

}
