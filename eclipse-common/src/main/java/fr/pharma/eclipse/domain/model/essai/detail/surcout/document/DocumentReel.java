package fr.pharma.eclipse.domain.model.essai.detail.surcout.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;

/**
 * Bean métier représentant un document relatif à aux surcouts reels.
 
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("REEL")
public class DocumentReel
    extends DocumentSurcouts
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3552420140396626957L;

    /**
     * Constructeur par défaut.
     */
    public DocumentReel()
    {
        this.setType(TypeDocumentEssai.REEL);
    }

}
