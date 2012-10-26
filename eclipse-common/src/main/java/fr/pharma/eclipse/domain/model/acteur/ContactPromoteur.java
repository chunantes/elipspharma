package fr.pharma.eclipse.domain.model.acteur;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

/**
 * Classe métier représentant un Contact Promoteur.
 
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("PROMOTEUR")
public class ContactPromoteur
    extends Personne
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5692153424263785792L;

    /**
     * Promoteur.
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_promoteur")
    @Index(name = "idx_promo_contactpromo")
    @NotNull
    private Promoteur promoteur;

    /**
     * Getter sur promoteur.
     * @return Retourne le promoteur.
     */
    public Promoteur getPromoteur()
    {
        return this.promoteur;
    }

    /**
     * Setter pour promoteur.
     * @param promoteur le promoteur à écrire.
     */
    public void setPromoteur(final Promoteur promoteur)
    {
        this.promoteur = promoteur;
    }

}
