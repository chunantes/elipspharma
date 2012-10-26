package fr.pharma.eclipse.domain.model.essai.detail.pharma.document;

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
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;
import fr.pharma.eclipse.domain.model.essai.DocumentEssai;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.DetailDonneesPharma;

/**
 * Bean métier représentant un document d'essai clinique rattaché au détail Donnees Pharma.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_document_detail_pharma")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class DocumentDonneesPharma
    extends DocumentEssai
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 4617709406748301452L;

    /**
     * Objet auquel est rattaché le doc.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detail_pharma", nullable = false)
    private DetailDonneesPharma detailDonneesPharma;

    /**
     * Type de document.
     */
    @Column(name = "type", insertable = false, updatable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeDocumentEssai type;

    /**
     * Getter sur detailDonneesPharma.
     * @return Retourne le detailDonneesPharma.
     */
    public DetailDonneesPharma getDetailDonneesPharma()
    {
        return this.detailDonneesPharma;
    }

    /**
     * Setter pour detailDonneesPharma.
     * @param detailDonneesPharma le detailDonneesPharma à écrire.
     */
    public void setDetailDonneesPharma(final DetailDonneesPharma detailDonneesPharma)
    {
        this.detailDonneesPharma = detailDonneesPharma;
    }

    /**
     * Getter sur type.
     * @return Retourne le type.
     */
    @Override
    public TypeDocumentEssai getType()
    {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type le type à écrire.
     */
    @Override
    public void setType(final TypeDocumentEssai type)
    {
        this.type = type;
    }

}
