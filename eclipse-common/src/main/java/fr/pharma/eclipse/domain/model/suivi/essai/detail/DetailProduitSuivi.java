package fr.pharma.eclipse.domain.model.suivi.essai.detail;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.essai.detail.produit.DetailProduit;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Bean métier représentant les suivis de modifications sur le bean
 * {@link DetailProduit}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_detail_produit_suivi")
public class DetailProduitSuivi extends Suivi {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2481675811695116939L;

    /**
     * Bean suivi.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detail_produit", nullable = false)
    @Index(name = "idx_suivi_detail_produit")
    private DetailProduit detailProduit;

    /**
     * Getter sur detailProduit.
     * @return Retourne le detailProduit.
     */
    public DetailProduit getDetailProduit() {
        return this.detailProduit;
    }

    /**
     * Setter pour detailProduit.
     * @param detailProduit le detailProduit à écrire.
     */
    public void setDetailProduit(final DetailProduit detailProduit) {
        this.detailProduit = detailProduit;
    }

}
