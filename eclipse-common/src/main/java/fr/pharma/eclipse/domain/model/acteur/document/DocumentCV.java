package fr.pharma.eclipse.domain.model.acteur.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentPharmacien;

/**
 * Bean métier représentant un document CV d'un pharmacien.
 
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("CV")
public class DocumentCV
    extends DocumentPharmacien
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -7996567370843643689L;

    /**
     * Constructeur par défaut.
     */
    public DocumentCV()
    {
        this.setType(TypeDocumentPharmacien.CV);
    }

}
