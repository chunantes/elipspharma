package fr.pharma.eclipse.domain.model.essai.detail.administratif.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;

/**
 * Bean métier représentant un document relatif à la convention de l'essai clinique et ses
 * avenants.
 
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("CONVENTION")
public class DocumentConvention
    extends DocumentAdministratif
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3552420140396626957L;

    /**
     * Constructeur par défaut.
     */
    public DocumentConvention()
    {
        this.setType(TypeDocumentEssai.CONVENTION);
    }

}
