package fr.pharma.eclipse.domain.model.essai.detail.administratif.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;

/**
 * Bean métier représentant un document rattaché au comité de protection des
 * personnes de l'essai clinique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("COMITE_PROTEC_PERS")
public class DocumentComiteProtectionPers extends DocumentAdministratif {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3552420140396626957L;

    /**
     * Constructeur par défaut.
     */
    public DocumentComiteProtectionPers() {
        this.setType(TypeDocumentEssai.COMITE_PROTEC_PERS);
    }

}
