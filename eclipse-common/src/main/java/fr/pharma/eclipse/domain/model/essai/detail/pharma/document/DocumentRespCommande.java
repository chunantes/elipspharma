package fr.pharma.eclipse.domain.model.essai.detail.pharma.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;

/**
 * Bean métier représentant un document relatif à la responsabilité de commandes de l'essai
 * clinique.
 
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("RESPONSABILITE_COMMANDE")
public class DocumentRespCommande
    extends DocumentDonneesPharma
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -462413771999701742L;

    /**
     * Constructeur par défaut.
     */
    public DocumentRespCommande()
    {
        this.setType(TypeDocumentEssai.RESPONSABILITE_COMMANDE);
    }
}
