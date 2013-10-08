package fr.pharma.eclipse.domain.model.stock.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentStock;

/**
 * Bean métier représentant un document relatif à une sortie de stock de type
 * Destruction.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("DESTRUCTION")
public class DocumentDestruction extends DocumentMvtStock {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5307528464973353643L;

    /**
     * Constructeur par défaut.
     */
    public DocumentDestruction() {
        this.setType(TypeDocumentStock.DESTRUCTION);
    }

}
