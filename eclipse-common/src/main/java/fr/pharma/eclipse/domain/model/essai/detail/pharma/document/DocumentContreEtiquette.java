package fr.pharma.eclipse.domain.model.essai.detail.pharma.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;

/**
 * Bean métier représentant un document relatif aux contres etiquettes de
 * l'essai clinique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("CONTRE_ETIQUETTE")
public class DocumentContreEtiquette extends DocumentDonneesPharma {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -462413771999701742L;

    /**
     * Constructeur par défaut.
     */
    public DocumentContreEtiquette() {
        this.setType(TypeDocumentEssai.CONTRE_ETIQUETTE);
    }
}
