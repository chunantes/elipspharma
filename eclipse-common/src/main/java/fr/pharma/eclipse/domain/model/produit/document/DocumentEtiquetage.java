package fr.pharma.eclipse.domain.model.produit.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentProduit;

/**
 * Bean métier représentant un document relatif à l'étiquetage d'un acte pharma.
 
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("ETIQUETAGE")
public class DocumentEtiquetage
    extends DocumentActesPharma
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3552420140396626957L;

    /**
     * Constructeur par défaut.
     */
    public DocumentEtiquetage()
    {
        this.setType(TypeDocumentProduit.ETIQUETAGE);
    }

}
