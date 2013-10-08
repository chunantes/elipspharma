package fr.pharma.eclipse.domain.model.essai.detail.pharma.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;

/**
 * Bean métier représentant un document relatif à modalite de destruction de
 * l'essai clinique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("MODALITE_DESTRUCTION")
public class DocumentModaliteDestruction extends DocumentDonneesPharma {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -4462589712244354734L;

    /**
     * Constructeur par défaut.
     */
    public DocumentModaliteDestruction() {
        this.setType(TypeDocumentEssai.MODALITE_DESTRUCTION);
    }
}
