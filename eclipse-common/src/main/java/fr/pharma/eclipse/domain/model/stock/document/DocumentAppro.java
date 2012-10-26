package fr.pharma.eclipse.domain.model.stock.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentStock;

/**
 * Bean métier représentant un document relatif à un approvisionnement (certificat d'analyse).
 
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("APPRO")
public class DocumentAppro
    extends DocumentMvtStock
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8317391752561797600L;

    /**
     * Constructeur par défaut.
     */
    public DocumentAppro()
    {
        this.setType(TypeDocumentStock.APPRO);
    }

}
