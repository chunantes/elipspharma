package fr.pharma.eclipse.domain.model.suivi.essai.detail;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.essai.detail.pharma.DetailDonneesPharma;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Bean métier représentant les suivis de modifications sur le bean
 * {@link DetailDonneesPharma}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_detail_pharma_suivi")
public class DetailDonneesPharmaSuivi extends Suivi implements Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -6254003327783271356L;

    /**
     * Bean suivi.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detail_pharma", nullable = false)
    @Index(name = "idx_suivi_detail_pharma")
    private DetailDonneesPharma detailDonneesPharma;

    /**
     * Getter sur detailDonneesPharma.
     * @return Retourne le detailDonneesPharma.
     */
    public DetailDonneesPharma getDetailDonneesPharma() {
        return this.detailDonneesPharma;
    }

    /**
     * Setter pour detailDonneesPharma.
     * @param detailDonneesPharma le detailDonneesPharma à écrire.
     */
    public void setDetailDonneesPharma(final DetailDonneesPharma detailDonneesPharma) {
        this.detailDonneesPharma = detailDonneesPharma;
    }

}
