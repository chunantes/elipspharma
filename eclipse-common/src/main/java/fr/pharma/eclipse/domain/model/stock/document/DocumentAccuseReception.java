package fr.pharma.eclipse.domain.model.stock.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentStock;

/**
 * Bean métier représentant un document relatif à une sortie de stock de type
 * Cession PUI : Accusé de réception.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("DESTRUCTION")
public class DocumentAccuseReception extends DocumentMvtStock {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5307528464973353643L;

    /**
     * Constructeur par défaut.
     */
    public DocumentAccuseReception() {
        this.setType(TypeDocumentStock.DESTRUCTION);
    }

}
