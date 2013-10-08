package fr.pharma.eclipse.domain.model.acteur.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentPharmacien;

/**
 * Bean métier représentant un document de validation de formation d'un
 * pharmacien.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("VALIDATION_FORMATION")
public class DocumentValidationFormation extends DocumentPharmacien {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -7996567370843643689L;

    /**
     * Constructeur par défaut.
     */
    public DocumentValidationFormation() {
        this.setType(TypeDocumentPharmacien.VALIDATION_FORMATION);
    }

}
