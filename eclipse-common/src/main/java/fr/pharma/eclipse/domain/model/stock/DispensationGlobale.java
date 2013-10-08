package fr.pharma.eclipse.domain.model.stock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.dotation.Dotation;

/**
 * Bean métier représentant un mouvement de dispensation globale (dotation).
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "mvt_dispensation_globale")
public class DispensationGlobale extends MvtStock {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5038953126457918179L;

    /**
     * Dotation.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dotation", nullable = false)
    @Index(name = "idx_disp_globale_dotation")
    @NotNull
    private Dotation dotation;

    /**
     * Quantité déjà dispensé.
     */
    @Column(name = "quantiteDispensee")
    private Integer quantiteDispensee;

    /**
     * Getter pour dotation.
     * @return Le dotation
     */
    public Dotation getDotation() {
        return this.dotation;
    }

    /**
     * Setter pour dotation.
     * @param dotation Le dotation à écrire.
     */
    public void setDotation(final Dotation dotation) {
        this.dotation = dotation;
    }

    /**
     * Getter pour quantiteDispensee.
     * @return Le quantiteDispensee
     */
    public Integer getQuantiteDispensee() {
        return this.quantiteDispensee;
    }

    /**
     * Setter pour quantiteDispensee.
     * @param quantiteDispensee Le quantiteDispensee à écrire.
     */
    public void setQuantiteDispensee(final Integer quantiteDispensee) {
        this.quantiteDispensee = quantiteDispensee;
    }

}
