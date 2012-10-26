package fr.pharma.eclipse.domain.model.suivi.essai;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Bean métier représentant les suivis de modifications sur le bean Essai.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_suivi")
public class EssaiSuivi
    extends Suivi
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8202610546008352361L;

    /**
     * Objet Essai.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_essai", nullable = false)
    @Index(name = "idx_suivi_essai")
    private Essai essai;

    /**
     * Getter sur essai.
     * @return Retourne le essai.
     */
    public Essai getEssai()
    {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai le essai à écrire.
     */
    public void setEssai(final Essai essai)
    {
        this.essai = essai;
    }

}
