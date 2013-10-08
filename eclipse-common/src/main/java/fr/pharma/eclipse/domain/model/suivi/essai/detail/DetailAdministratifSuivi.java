package fr.pharma.eclipse.domain.model.suivi.essai.detail;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.essai.detail.administratif.DetailAdministratif;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Bean métier représentant les suivis de modifications sur le bean
 * {@link DetailAdministratif}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_detail_administratif_suivi")
public class DetailAdministratifSuivi extends Suivi implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1116355748710884653L;

    /**
     * Bean suivi.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detail_administratif", nullable = false)
    @Index(name = "idx_suivi_detail_administratif")
    private DetailAdministratif detailAdministratif;

    /**
     * Getter sur detailAdministratif.
     * @return Retourne le detailAdministratif.
     */
    public DetailAdministratif getDetailAdministratif() {
        return this.detailAdministratif;
    }

    /**
     * Setter pour detailAdministratif.
     * @param detailAdministratif le detailAdministratif à écrire.
     */
    public void setDetailAdministratif(final DetailAdministratif detailAdministratif) {
        this.detailAdministratif = detailAdministratif;
    }

}
