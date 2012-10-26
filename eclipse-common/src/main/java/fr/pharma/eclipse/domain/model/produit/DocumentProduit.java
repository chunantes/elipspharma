package fr.pharma.eclipse.domain.model.produit;

import javax.persistence.MappedSuperclass;

import fr.pharma.eclipse.domain.enums.document.EnumTypeDocument;
import fr.pharma.eclipse.domain.enums.document.TypeDocumentProduit;
import fr.pharma.eclipse.domain.model.common.DocumentEclipse;

/**
 * Classe abstraite représentant un document attaché à un produit.
 
 * @version $Revision$ $Date$
 */
@MappedSuperclass
public abstract class DocumentProduit
    extends DocumentEclipse
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 4636962835122037112L;

    /**
     * {@inheritDoc}
     */
    @Override
    public EnumTypeDocument getTypeDocument()
    {
        return this.getType();
    }

    /**
     * Getter sur le type du document.
     * @return Le type de document.
     */
    public abstract TypeDocumentProduit getType();

    /**
     * Setter sur le type de document.
     * @param type Le type de document.
     */
    public abstract void setType(TypeDocumentProduit type);
}
