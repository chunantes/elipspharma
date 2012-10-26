package fr.pharma.eclipse.domain.model.essai.detail.administratif.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;

/**
 * Bean métier représentant un document relatif à l'assurance de l'essai clinique.
 
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("ASSURANCE")
public class DocumentAssurance
    extends DocumentAdministratif
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3552420140396626957L;

    /**
     * Constructeur par défaut.
     */
    public DocumentAssurance()
    {
        this.setType(TypeDocumentEssai.ASSURANCE);
    }

}
