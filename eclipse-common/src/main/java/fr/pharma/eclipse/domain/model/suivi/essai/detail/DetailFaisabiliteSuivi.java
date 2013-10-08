package fr.pharma.eclipse.domain.model.suivi.essai.detail;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.essai.detail.faisabilite.DetailFaisabilite;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Bean métier représentant les suivis de modifications sur le bean
 * {@link DetailFaisabilite}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_detail_faisabilite_suivi")
public class DetailFaisabiliteSuivi extends Suivi {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2481675811695116939L;

    /**
     * Bean suivi.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detail_faisabilite", nullable = false)
    @Index(name = "idx_suivi_detail_faisabilite")
    private DetailFaisabilite detailFaisabilite;

    /**
     * Getter sur detailFaisabilite.
     * @return Retourne le detailFaisabilite.
     */
    public DetailFaisabilite getDetailFaisabilite() {
        return this.detailFaisabilite;
    }

    /**
     * Setter pour detailFaisabilite.
     * @param detailFaisabilite le detailFaisabilite à écrire.
     */
    public void setDetailFaisabilite(final DetailFaisabilite detailFaisabilite) {
        this.detailFaisabilite = detailFaisabilite;
    }

}
