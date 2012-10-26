package fr.pharma.eclipse.domain.model.essai.detail.administratif.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;

/**
 * Bean métier représentant un document relatif à l'autorisation de distribution de l'essai
 * clinique.
 
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("AUTORISATION_DISTRIBUTION")
public class DocumentAutorisationDistribution
    extends DocumentAdministratif
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3552420140396626957L;

    /**
     * Constructeur par défaut.
     */
    public DocumentAutorisationDistribution()
    {
        this.setType(TypeDocumentEssai.AUTORISATION_DISTRIBUTION);
    }

}
