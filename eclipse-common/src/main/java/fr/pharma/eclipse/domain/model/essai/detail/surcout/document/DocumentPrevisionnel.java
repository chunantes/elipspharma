package fr.pharma.eclipse.domain.model.essai.detail.surcout.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;

/**
 * Bean métier représentant un document relatif à aux surcouts prévisionnels.
 
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("PREVISIONNEL")
public class DocumentPrevisionnel
    extends DocumentSurcouts
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3552420140396626957L;

    /**
     * Constructeur par défaut.
     */
    public DocumentPrevisionnel()
    {
        this.setType(TypeDocumentEssai.PREVISIONNEL);
    }

}
