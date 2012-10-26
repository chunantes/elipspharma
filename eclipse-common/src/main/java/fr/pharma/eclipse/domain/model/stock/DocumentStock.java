package fr.pharma.eclipse.domain.model.stock;

import fr.pharma.eclipse.domain.enums.document.EnumTypeDocument;
import fr.pharma.eclipse.domain.enums.document.TypeDocumentStock;
import fr.pharma.eclipse.domain.model.common.DocumentEclipse;

/**
 * Classe abstraite représentant un document de stock.
 
 * @version $Revision$ $Date$
 */
public abstract class DocumentStock
    extends DocumentEclipse
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 4487359409530832920L;

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
    public abstract TypeDocumentStock getType();

    /**
     * Setter sur le type de document.
     * @param type Le type de document.
     */
    public abstract void setType(TypeDocumentStock type);

}
