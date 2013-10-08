package fr.pharma.eclipse.domain.model.essai.detail.administratif.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;

/**
 * Bean métier représentant un document relatif à l'autorisation d'importation
 * de l'essai clinique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("AUTORISATION_IMPORTATION")
public class DocumentAutorisationImportation extends DocumentAdministratif {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3552420140396626957L;

    /**
     * Constructeur par défaut.
     */
    public DocumentAutorisationImportation() {
        this.setType(TypeDocumentEssai.AUTORISATION_IMPORTATION);
    }

}
