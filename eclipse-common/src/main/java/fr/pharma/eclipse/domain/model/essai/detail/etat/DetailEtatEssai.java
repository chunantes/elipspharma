package fr.pharma.eclipse.domain.model.essai.detail.etat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Classe métier représentant les informations de détail de l'état d'un essai
 * clinique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_detail_etat")
public class DetailEtatEssai extends Suivi {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5579277643301746594L;

    /**
     * Essai.
     */
    @ManyToOne
    @JoinColumn(name = "id_essai", nullable = false)
    @Index(name = "idx_detail_etat_essai")
    @NotNull
    private Essai essai;

    /**
     * Etat de l'essai.
     */
    @Column(name = "etat")
    @Enumerated(EnumType.STRING)
    @NotNull
    private EtatEssai etatEssai;

    /**
     * Commentaire.
     */
    @Column(name = "commentaire", columnDefinition = "TEXT")
    private String commentaire;

    /**
     * Getter pour essai.
     * @return Le essai
     */
    public Essai getEssai() {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai Le essai à écrire.
     */
    public void setEssai(final Essai essai) {
        this.essai = essai;
    }

    /**
     * Getter pour etatEssai.
     * @return Le etatEssai
     */
    public EtatEssai getEtatEssai() {
        return this.etatEssai;
    }

    /**
     * Setter pour etatEssai.
     * @param etatEssai Le etatEssai à écrire.
     */
    public void setEtatEssai(final EtatEssai etatEssai) {
        this.etatEssai = etatEssai;
    }

    /**
     * Getter pour commentaire.
     * @return Le commentaire
     */
    public String getCommentaire() {
        return this.commentaire;
    }

    /**
     * Setter pour commentaire.
     * @param commentaire Le commentaire à écrire.
     */
    public void setCommentaire(final String commentaire) {
        this.commentaire = commentaire;
    }

}
