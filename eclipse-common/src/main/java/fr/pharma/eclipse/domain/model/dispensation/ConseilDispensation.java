package fr.pharma.eclipse.domain.model.dispensation;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;

/**
 * Description de la classe.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ConseilDispensation implements Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 3566862218460002259L;

    /**
     * Produit prescrit.
     */
    private ProduitPrescrit produitPrescrit;

    /**
     * Nombre à sortir.
     */
    private Integer nbASortir;

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
     * Getter sur nbASortir.
     * @return Retourne le nbASortir.
     */
    public Integer getNbASortir() {
        return this.nbASortir;
    }

    /**
     * Setter pour nbASortir.
     * @param nbASortir le nbASortir à écrire.
     */
    public void setNbASortir(final Integer nbASortir) {
        this.nbASortir = nbASortir;
    }

}
