package fr.pharma.eclipse.domain.model.suivi.localisation;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.localisation.Pole;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Bean métier représentant les suivis de modifications sur le bean Pole.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "pole_suivi")
public class PoleSuivi
    extends Suivi
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5085178908346730004L;

    /**
     * Objet Pole.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pole", nullable = false)
    @Index(name = "idx_suivi_pole")
    private Pole pole;

    /**
     * Getter sur pole.
     * @return Retourne le pole.
     */
    public Pole getPole()
    {
        return this.pole;
    }

    /**
     * Setter pour pole.
     * @param pole le pole à écrire.
     */
    public void setPole(final Pole pole)
    {
        this.pole = pole;
    }

}
