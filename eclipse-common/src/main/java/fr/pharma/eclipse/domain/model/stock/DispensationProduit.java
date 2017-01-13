package fr.pharma.eclipse.domain.model.stock;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;

/**
 * Bean métier représentant un mouvement de stock de dispensation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "mvt_dispensation")
public class DispensationProduit extends MvtStock implements Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -7316343047965720470L;

    public DispensationProduit() {
        super();
    }

    public DispensationProduit(ProduitPrescrit produitPrescrit, Dispensation dispensation) {
        this.produitPrescrit = produitPrescrit;
        this.dispensation = dispensation;
    }
    

    /**
     * Produit Prescrit.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produitPrescrit", nullable = false)
    @Index(name = "idx_produit_prescrit_dispensation_produit")
    @NotNull
    private ProduitPrescrit produitPrescrit;

    /**
     * Dispensation.
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "id_dispensation", nullable = false)
    @Index(name = "idx_dispensation_dispensation_produit")
    @NotNull
    private Dispensation dispensation;

    /**
     * Getter sur produitPrescrit.
     * @return Retourne le produitPrescrit.
     */
    public ProduitPrescrit getProduitPrescrit() {
        return this.produitPrescrit;
    }

    /**
     * Setter pour produitPrescrit.
     * @param produitPrescrit le produitPrescrit à écrire.
     */
    public void setProduitPrescrit(final ProduitPrescrit produitPrescrit) {
        this.produitPrescrit = produitPrescrit;
    }

    /**
     * Getter sur dispensation.
     * @return Retourne le dispensation.
     */
    public Dispensation getDispensation() {
        return this.dispensation;
    }

    /**
     * Setter pour dispensation.
     * @param dispensation le dispensation à écrire.
     */
    public void setDispensation(final Dispensation dispensation) {
        this.dispensation = dispensation;
    }

}
