package fr.pharma.eclipse.domain.model.essai.detail.faisabilite;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.enums.TypeCommentaireEssai;
import fr.pharma.eclipse.domain.model.essai.CommentaireEssai;

/**
 * Classe métier représentant un commentaire laissé sur le détail de l'étude de
 * faisabilité de l'essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_commentaire_detail_faisabilite")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class CommentaireEssaiFaisabilite extends CommentaireEssai {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 4372330351800835010L;

    /**
     * Objet auquel est rattaché le commentaire.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detailFaisabilite", nullable = false)
    @Index(name = "idx_essai_commentaire_detail_faisabilite")
    private DetailFaisabilite detailFaisabilite;

    /**
     * Type de commentaire.
     */
    @Column(name = "type", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private TypeCommentaireEssai type;

    /**
     * Getter sur detailFaisabilite.
     * @return Retourne le detailFaisabilite.
     */
    public DetailFaisabilite getDetailFaisabilite() {
        return this.detailFaisabilite;
    }

    /**
     * Setter pour detailFaisabilite.
     * @param detailFaisabilite le detailFaisabilite à écrire.
     */
    public void setDetailFaisabilite(final DetailFaisabilite detailFaisabilite) {
        this.detailFaisabilite = detailFaisabilite;
    }

    /**
     * Getter sur type.
     * @return Retourne le type.
     */
    public TypeCommentaireEssai getType() {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type le type à écrire.
     */
    public void setType(final TypeCommentaireEssai type) {
        this.type = type;
    }
}
