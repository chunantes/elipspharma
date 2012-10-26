package fr.pharma.eclipse.domain.model.essai.detail.pharma.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;

/**
 * Bean métier représentant un document relatif à l'aide aux dispensations de l'essai clinique.
 
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("AIDE_DISPENSATION")
public class DocumentAideDispensation
    extends DocumentDonneesPharma
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -462413771999701742L;

    /**
     * Constructeur par défaut.
     */
    public DocumentAideDispensation()
    {
        this.setType(TypeDocumentEssai.AIDE_DISPENSATION);
    }
}
