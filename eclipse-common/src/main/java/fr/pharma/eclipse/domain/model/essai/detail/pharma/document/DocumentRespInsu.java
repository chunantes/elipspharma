package fr.pharma.eclipse.domain.model.essai.detail.pharma.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;

/**
 * Bean métier représentant un document relatif à la responsabilité de l'insu de l'essai clinique.
 
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("RESPONSABILITE_INSU")
public class DocumentRespInsu
    extends DocumentDonneesPharma
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1244562678661672644L;

    /**
     * Constructeur par défaut.
     */
    public DocumentRespInsu()
    {
        this.setType(TypeDocumentEssai.RESPONSABILITE_INSU);
    }
}
