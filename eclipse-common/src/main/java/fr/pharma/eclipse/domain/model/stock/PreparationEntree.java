package fr.pharma.eclipse.domain.model.stock;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.ordonnancier.OrdonnancierFabReconst;

/**
 * Bean métier représentant une entrée en preparation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "mvt_preparationEntree")
public class PreparationEntree extends Approvisionnement {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5654270804575540133L;

    /**
     * Composition.
     */
    @Column(name = "composition", columnDefinition = "TEXT")
    private String composition;

    /**
     * Date de fabrication.
     */
    @Column(name = "dateFabrication")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateFabrication;

    /**
     * Numéro de l'ordonnancier de fabrication/reconstitution.
     */
    @Column(name = "numOrdonnancier")
    private Integer numOrdonnancier;

    /**
     * Ordonnancier de fabrication / reconstitution.
     */
    @ManyToOne
    @JoinColumn(name = "id_ordonnancier")
    @Index(name = "idx_elementToCheck_ordon2")
    private OrdonnancierFabReconst ordonnancier;

    /**
     * Préparation stérile.
     */
    @Column(name = "sterile")
    private Boolean sterile;

    /**
     * Getter pour composition.
     * @return Le composition
     */
    public String getComposition() {
        return this.composition;
    }

    /**
     * Setter pour composition.
     * @param composition Le composition à écrire.
     */
    public void setComposition(final String composition) {
        this.composition = composition;
    }

    /**
     * Getter pour dateFabrication.
     * @return Le dateFabrication
     */
    public Calendar getDateFabrication() {
        return this.dateFabrication;
    }

    /**
     * Setter pour dateFabrication.
     * @param dateFabrication Le dateFabrication à écrire.
     */
    public void setDateFabrication(final Calendar dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    /**
     * Getter pour numOrdonnancier.
     * @return Le numOrdonnancier
     */
    public Integer getNumOrdonnancier() {
        return this.numOrdonnancier;
    }

    /**
     * Setter pour numOrdonnancier.
     * @param numOrdonnancier Le numOrdonnancier à écrire.
     */
    public void setNumOrdonnancier(final Integer numOrdonnancier) {
        this.numOrdonnancier = numOrdonnancier;
    }

    /**
     * Getter pour ordonnancier.
     * @return Le ordonnancier
     */
    public OrdonnancierFabReconst getOrdonnancier() {
        return this.ordonnancier;
    }

    /**
     * Setter pour ordonnancier.
     * @param ordonnancier Le ordonnancier à écrire.
     */
    public void setOrdonnancier(final OrdonnancierFabReconst ordonnancier) {
        this.ordonnancier = ordonnancier;
    }

    /**
     * Getter pour sterile.
     * @return Le sterile
     */
    public Boolean getSterile() {
        return this.sterile;
    }

    /**
     * Setter pour sterile.
     * @param sterile Le sterile à écrire.
     */
    public void setSterile(final Boolean sterile) {
        this.sterile = sterile;
    }

}
