package fr.pharma.eclipse.domain.model.stock.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentStock;

/**
 * Bean métier représentant un document relatif à une sortie de stock de type RetourPromoteur.
 
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("RETOUR_PROMOTEUR")
public class DocumentRetourPromoteur
    extends DocumentMvtStock
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1885029387686972331L;

    /**
     * Constructeur par défaut.
     */
    public DocumentRetourPromoteur()
    {
        this.setType(TypeDocumentStock.RETOUR_PROMOTEUR);
    }

}
