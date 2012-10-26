package fr.pharma.eclipse.domain.model.suivi.essai.detail;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.essai.detail.surcout.DetailSurcout;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Bean métier représentant les suivis de modifications sur le bean DetailSurcout.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_detail_surcout_suivi")
public class DetailSurcoutSuivi
    extends Suivi
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5642140098893756815L;

    /**
     * Objet Pharmacie.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detail_surcout", nullable = false)
    @Index(name = "idx_suivi_detail_surcout")
    private DetailSurcout detailSurcout;

    /**
     * Getter sur detailSurcout.
     * @return Retourne le detailSurcout.
     */
    public DetailSurcout getDetailSurcout()
    {
        return this.detailSurcout;
    }

    /**
     * Setter pour detailSurcout.
     * @param detailSurcout le detailSurcout à écrire.
     */
    public void setDetailSurcout(final DetailSurcout detailSurcout)
    {
        this.detailSurcout = detailSurcout;
    }

}
