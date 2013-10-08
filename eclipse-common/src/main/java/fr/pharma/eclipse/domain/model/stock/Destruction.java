package fr.pharma.eclipse.domain.model.stock;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;

import fr.pharma.eclipse.domain.enums.stock.RaisonSortie;
import fr.pharma.eclipse.domain.model.stock.document.DocumentDestruction;

/**
 * Bean métier représentant un mouvement de stock de destruction.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "mvt_destruction")
public class Destruction extends MvtStock {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6463161521426945421L;

    /**
     * Document de certificat de destruction.
     */
    @OneToOne(mappedBy = "mvtStock", cascade = CascadeType.ALL)
    @Where(clause = "type='DESTRUCTION'")
    @LazyCollection(LazyCollectionOption.TRUE)
    private DocumentDestruction documentDestruction;

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
     * Commentaire.
     */
    @Column(name = "commentaire", columnDefinition = "TEXT")
    private String commentaire;

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

    /**
     * Getter pour documentDestruction.
     * @return Le documentDestruction
     */
    public DocumentDestruction getDocumentDestruction() {
        return this.documentDestruction;
    }

    /**
     * Setter pour documentDestruction.
     * @param documentDestruction Le documentDestruction à écrire.
     */
    public void setDocumentDestruction(final DocumentDestruction documentDestruction) {
        this.documentDestruction = documentDestruction;
    }

    /**
     * Getter pour raisonSortie.
     * @return Le raisonSortie
     */
    public RaisonSortie getRaisonSortie() {
        return this.raisonSortie;
    }

    /**
     * Setter pour raisonSortie.
     * @param raisonSortie Le raisonSortie à écrire.
     */
    public void setRaisonSortie(final RaisonSortie raisonSortie) {
        this.raisonSortie = raisonSortie;
    }

    /**
     * Getter pour commentaireRaison.
     * @return Le commentaireRaison
     */
    public String getCommentaireRaison() {
        return this.commentaireRaison;
    }

    /**
     * Setter pour commentaireRaison.
     * @param commentaireRaison Le commentaireRaison à écrire.
     */
    public void setCommentaireRaison(final String commentaireRaison) {
        this.commentaireRaison = commentaireRaison;
    }

}
