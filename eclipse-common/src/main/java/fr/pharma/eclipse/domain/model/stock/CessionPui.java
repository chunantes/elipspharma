package fr.pharma.eclipse.domain.model.stock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.enums.stock.RaisonSortie;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Bean métier représentant un mouvement de stock de cession à une PUI (pharmacie).
 
 * @version $Revision$ $Date$
 */
@Entity(name = "mvt_cession_pui")
public class CessionPui
    extends MvtStock
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -917478153461060183L;

    /**
     * Commentaire.
     */
    @Column(name = "commentaire", columnDefinition = "TEXT")
    private String commentaire;

    /**
     * Pharmacie.
     */
    @ManyToOne
    @JoinColumn(name = "id_pharmacieDest")
    @Index(name = "idx_mvtstock_pharmacieDest")
    private Pharmacie pharmacieDest;

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
     * Getter pour pharmacieDest.
     * @return Le pharmacieDest
     */
    public Pharmacie getPharmacieDest()
    {
        return this.pharmacieDest;
    }

    /**
     * Setter pour pharmacieDest.
     * @param pharmacieDest Le pharmacieDest à écrire.
     */
    public void setPharmacieDest(final Pharmacie pharmacieDest)
    {
        this.pharmacieDest = pharmacieDest;
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
