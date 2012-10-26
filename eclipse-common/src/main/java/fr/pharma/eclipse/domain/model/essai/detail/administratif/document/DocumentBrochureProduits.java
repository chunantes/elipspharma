package fr.pharma.eclipse.domain.model.essai.detail.administratif.document;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentBrochure;
import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;

/**
 * Bean métier représentant un document relatif à la brochure investigateur des produits de
 * l'essai clinique.
 
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("BROCHURE_PDUITS")
public class DocumentBrochureProduits
    extends DocumentAdministratif
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3552420140396626957L;

    /**
     * Version.
     */
    @Column(name = "version")
    private String version;

    /**
     * Type de document protocole (Protocole ou amendement).
     */
    @Column(name = "typeDocumentBrochure")
    @Enumerated(EnumType.STRING)
    private TypeDocumentBrochure typeDocumentBrochure;

    /**
     * Constructeur par défaut.
     */
    public DocumentBrochureProduits()
    {
        this.setType(TypeDocumentEssai.BROCHURE_PDUITS);
    }

    /**
     * Getter pour typeDocumentBrochure.
     * @return Le typeDocumentBrochure
     */
    public TypeDocumentBrochure getTypeDocumentBrochure()
    {
        return this.typeDocumentBrochure;
    }

    /**
     * Setter pour typeDocumentBrochure.
     * @param typeDocumentBrochure Le typeDocumentBrochure à écrire.
     */
    public void setTypeDocumentBrochure(final TypeDocumentBrochure typeDocumentBrochure)
    {
        this.typeDocumentBrochure = typeDocumentBrochure;
    }

    /**
     * Getter pour version.
     * @return Le version
     */
    public String getVersion()
    {
        return this.version;
    }

    /**
     * Setter pour version.
     * @param version Le version à écrire.
     */
    public void setVersion(final String version)
    {
        this.version = version;
    }

}
