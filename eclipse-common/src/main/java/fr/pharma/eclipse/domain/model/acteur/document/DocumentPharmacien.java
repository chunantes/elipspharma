package fr.pharma.eclipse.domain.model.acteur.document;

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

import fr.pharma.eclipse.domain.enums.document.EnumTypeDocument;
import fr.pharma.eclipse.domain.enums.document.TypeDocumentPharmacien;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.common.DocumentEclipse;

/**
 * Classe abstraite représentant un document de pharmacien.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "pharmacien_document_pharmacien")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class DocumentPharmacien extends DocumentEclipse {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 4487359409530832920L;

    /**
     * Objet auquel est rattaché le document.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pharmacien", nullable = false)
    @Index(name = "idx_pharmacien_document_pharmacien")
    private Pharmacien pharmacien;

    /**
     * Type de document.
     */
    @Column(name = "type", insertable = false, updatable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeDocumentPharmacien type;

    /**
     * {@inheritDoc}
     */
    @Override
    public EnumTypeDocument getTypeDocument() {
        return this.getType();
    }

    /**
     * Getter pour pharmacien.
     * @return Le pharmacien
     */
    public Pharmacien getPharmacien() {
        return this.pharmacien;
    }

    /**
     * Setter pour pharmacien.
     * @param pharmacien Le pharmacien à écrire.
     */
    public void setPharmacien(final Pharmacien pharmacien) {
        this.pharmacien = pharmacien;
    }

    /**
     * Getter pour type.
     * @return Le type
     */
    public TypeDocumentPharmacien getType() {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type Le type à écrire.
     */
    public void setType(final TypeDocumentPharmacien type) {
        this.type = type;
    }

}
