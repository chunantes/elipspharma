package fr.pharma.eclipse.domain.model.suivi.essai.detail;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.essai.detail.dates.DetailDates;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Bean métier représentant les suivis de modifications sur le bean {@link DetailDates}.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_detail_dates_suivi")
public class DetailDatesSuivi
    extends Suivi
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1116355748710884653L;

    /**
     * Bean suivi.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detail_dates", nullable = false)
    @Index(name = "idx_suivi_detail_dates")
    private DetailDates detailDates;

    /**
     * Getter sur detailDates.
     * @return Retourne le detailDates.
     */
    public DetailDates getDetailDates()
    {
        return this.detailDates;
    }

    /**
     * Setter pour detailDates.
     * @param detailDates le detailDates à écrire.
     */
    public void setDetailDates(final DetailDates detailDates)
    {
        this.detailDates = detailDates;
    }

}
