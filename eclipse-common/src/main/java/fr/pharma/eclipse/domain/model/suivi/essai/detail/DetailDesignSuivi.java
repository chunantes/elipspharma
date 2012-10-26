package fr.pharma.eclipse.domain.model.suivi.essai.detail;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.essai.detail.design.DetailDesign;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Bean métier représentant les suivis de modifications sur le bean {@link DetailDesign}.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_detail_design_suivi")
public class DetailDesignSuivi
    extends Suivi
    implements Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -6254003327783271356L;

    /**
     * Bean suivi.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detail_design", nullable = false)
    @Index(name = "idx_suivi_detail_design")
    private DetailDesign detailDesign;

    /**
     * Getter sur detailDesign.
     * @return Retourne le detailDesign.
     */
    public DetailDesign getDetailDesign()
    {
        return this.detailDesign;
    }

    /**
     * Setter pour detailDesign.
     * @param detailDesign le detailDesign à écrire.
     */
    public void setDetailDesign(final DetailDesign detailDesign)
    {
        this.detailDesign = detailDesign;
    }

}
