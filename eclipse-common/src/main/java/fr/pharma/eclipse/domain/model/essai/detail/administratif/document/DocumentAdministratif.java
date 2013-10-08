package fr.pharma.eclipse.domain.model.essai.detail.administratif.document;

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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;
import fr.pharma.eclipse.domain.model.essai.DocumentEssai;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.DetailAdministratif;

/**
 * Bean métier représentant un document d'essai clinique rattaché au détail
 * administratif/réglementaire.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_document_detail_administratif")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class DocumentAdministratif extends DocumentEssai {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -7996567370843643689L;

    /**
     * Objet auquel est rattaché le commentaire.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detailAdministratif", nullable = false)
    @Index(name = "idx_essai_document_detail_administratif")
    private DetailAdministratif detailAdministratif;

    /**
     * Type de document.
     */
    @Column(name = "type", insertable = false, updatable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeDocumentEssai type;

    /**
     * Getter sur detailAdministratif.
     * @return Retourne le detailAdministratif.
     */
    public DetailAdministratif getDetailAdministratif() {
        return this.detailAdministratif;
    }

    /**
     * Setter pour detailAdministratif.
     * @param detailAdministratif le detailAdministratif à écrire.
     */
    public void setDetailAdministratif(final DetailAdministratif detailAdministratif) {
        this.detailAdministratif = detailAdministratif;
    }

    /**
     * Getter sur type.
     * @return Retourne le type.
     */
    @Override
    public TypeDocumentEssai getType() {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type le type à écrire.
     */
    @Override
    public void setType(final TypeDocumentEssai type) {
        this.type = type;
    }

}
