package fr.pharma.eclipse.domain.model.stock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import fr.pharma.eclipse.domain.enums.stock.RaisonSortie;

/**
 * Bean métier représentant un mouvement de stock de sortie autre.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "mvt_autre_sortie")
public class AutreSortie
    extends MvtStock
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2427219089581455904L;

    /**
     * Commentaire.
     */
    @Column(name = "commentaire", columnDefinition = "TEXT")
    private String commentaire;

    /**
     * Raison sortie. Setté si le type demouvement est une sortie.
     */
    @Column(name = "raisonSortie")
    @Enumerated(EnumType.STRING)
    private RaisonSortie raisonSortie;

    /**
     * Commentaire sur la raison de la sortie.
     */
    @Column(name = "commentaireRaison", columnDefinition = "TEXT")
    private String commentaireRaison;

    /**
     * Getter pour commentaire.
     * @return Le commentaire
     */
    public String getCommentaire()
    {
        return this.commentaire;
    }

    /**
     * Setter pour commentaire.
     * @param commentaire Le commentaire à écrire.
     */
    public void setCommentaire(final String commentaire)
    {
        this.commentaire = commentaire;
    }

    /**
     * Getter pour raisonSortie.
     * @return Le raisonSortie
     */
    public RaisonSortie getRaisonSortie()
    {
        return this.raisonSortie;
    }

    /**
     * Setter pour raisonSortie.
     * @param raisonSortie Le raisonSortie à écrire.
     */
    public void setRaisonSortie(final RaisonSortie raisonSortie)
    {
        this.raisonSortie = raisonSortie;
    }

    /**
     * Getter pour commentaireRaison.
     * @return Le commentaireRaison
     */
    public String getCommentaireRaison()
    {
        return this.commentaireRaison;
    }

    /**
     * Setter pour commentaireRaison.
     * @param commentaireRaison Le commentaireRaison à écrire.
     */
    public void setCommentaireRaison(final String commentaireRaison)
    {
        this.commentaireRaison = commentaireRaison;
    }

}
