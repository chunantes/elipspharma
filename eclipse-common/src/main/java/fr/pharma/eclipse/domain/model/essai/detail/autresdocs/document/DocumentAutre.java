package fr.pharma.eclipse.domain.model.essai.detail.autresdocs.document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;
import fr.pharma.eclipse.domain.model.essai.DocumentEssai;
import fr.pharma.eclipse.domain.model.essai.detail.autresdocs.DetailAutresDocuments;

/**
 * Bean métier représentant un document autre de l'essai clinique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_document_detail_autres_documents")
public class DocumentAutre extends DocumentEssai {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3552420140396626957L;

    /**
     * Objet auquel est rattaché le doc.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detail_autres_documents", nullable = false)
    private DetailAutresDocuments detailAutresDocuments;

    /**
     * Type de document.
     */
    @Column(name = "type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeDocumentEssai type;

    /**
     * Constructeur par défaut.
     */
    public DocumentAutre() {
        this.setType(TypeDocumentEssai.AUTRE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypeDocumentEssai getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setType(final TypeDocumentEssai type) {
        this.type = type;
    }

    /**
     * Getter sur detailAutresDocuments.
     * @return Retourne le detailAutresDocuments.
     */
    public DetailAutresDocuments getDetailAutresDocuments() {
        return this.detailAutresDocuments;
    }

    /**
     * Setter pour detailAutresDocuments.
     * @param detailAutresDocuments le detailAutresDocuments à écrire.
     */
    public void setDetailAutresDocuments(final DetailAutresDocuments detailAutresDocuments) {
        this.detailAutresDocuments = detailAutresDocuments;
    }

}
