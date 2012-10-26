package fr.pharma.eclipse.domain.model.produit.document;

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

import fr.pharma.eclipse.domain.enums.document.TypeDocumentProduit;
import fr.pharma.eclipse.domain.model.produit.DocumentProduit;
import fr.pharma.eclipse.domain.model.produit.Produit;

/**
 * Bean métier représentant un document lié aux actes pharma d'un produit.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "produit_document_actes_pharma")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class DocumentActesPharma
    extends DocumentProduit
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -7996567370843643689L;

    /**
     * Objet auquel est rattaché le doc.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produit", nullable = false)
    private Produit produit;

    /**
     * Type de document.
     */
    @Column(name = "type", insertable = false, updatable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeDocumentProduit type;

    /**
     * Getter sur produit.
     * @return Retourne le produit.
     */
    public Produit getProduit()
    {
        return this.produit;
    }

    /**
     * Setter pour produit.
     * @param produit le produit à écrire.
     */
    public void setProduit(final Produit produit)
    {
        this.produit = produit;
    }

    /**
     * Getter sur type.
     * @return Retourne le type.
     */
    @Override
    public TypeDocumentProduit getType()
    {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type le type à écrire.
     */
    @Override
    public void setType(final TypeDocumentProduit type)
    {
        this.type = type;
    }

}
