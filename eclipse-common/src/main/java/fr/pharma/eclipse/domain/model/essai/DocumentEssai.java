package fr.pharma.eclipse.domain.model.essai;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import fr.pharma.eclipse.domain.enums.document.EnumTypeDocument;
import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;
import fr.pharma.eclipse.domain.model.common.DocumentEclipse;

/**
 * Classe abstraite représentant un document attaché (indirectement) à un essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@MappedSuperclass
public abstract class DocumentEssai extends DocumentEclipse {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 4636962835122037112L;

    /**
     * Commentaire.
     */
    @Column(name = "commentaire", columnDefinition = "TEXT")
    private String commentaire;

    /**
     * Getter sur commentaire.
     * @return Retourne le commentaire.
     */
    public String getCommentaire() {
        return this.commentaire;
    }

    /**
     * Setter pour commentaire.
     * @param commentaire le commentaire à écrire.
     */
    public void setCommentaire(final String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnumTypeDocument getTypeDocument() {
        return this.getType();
    }

    /**
     * Getter sur le type du document.
     * @return Le type de document.
     */
    public abstract TypeDocumentEssai getType();

    /**
     * Setter sur le type de document.
     * @param type Le type de document.
     */
    public abstract void setType(TypeDocumentEssai type);
}
