package fr.pharma.eclipse.domain.model.suivi.essai.detail;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.essai.detail.recherche.DetailRecherche;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Bean métier représentant les suivis de modifications sur le bean {@link DetailRecherche}.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_detail_recherche_suivi")
public class DetailRechercheSuivi
    extends Suivi
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2353382127287064800L;

    /**
     * Bean suivi.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detail_recherche", nullable = false)
    @Index(name = "idx_suivi_detail_recherche")
    private DetailRecherche detailRecherche;

    /**
     * Getter sur detailRecherche.
     * @return Retourne le detailRecherche.
     */
    public DetailRecherche getDetailRecherche()
    {
        return this.detailRecherche;
    }

    /**
     * Setter pour detailRecherche.
     * @param detailRecherche le detailRecherche à écrire.
     */
    public void setDetailRecherche(final DetailRecherche detailRecherche)
    {
        this.detailRecherche = detailRecherche;
    }

}
