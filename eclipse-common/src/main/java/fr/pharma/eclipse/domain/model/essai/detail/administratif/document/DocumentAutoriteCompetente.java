package fr.pharma.eclipse.domain.model.essai.detail.administratif.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;

/**
 * Bean métier représentant un document rattaché à l'autorité compétente de
 * l'essai clinique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("AUTORITE_COMPETENTE")
public class DocumentAutoriteCompetente extends DocumentAdministratif {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3552420140396626957L;

    /**
     * Constructeur par défaut.
     */
    public DocumentAutoriteCompetente() {
        this.setType(TypeDocumentEssai.AUTORITE_COMPETENTE);
    }

}
