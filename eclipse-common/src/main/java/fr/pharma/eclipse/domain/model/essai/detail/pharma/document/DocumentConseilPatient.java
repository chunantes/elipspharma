package fr.pharma.eclipse.domain.model.essai.detail.pharma.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;

/**
 * Bean métier représentant un document relatif aux conseils patients de l'essai clinique.
 
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("CONSEIL_PATIENT")
public class DocumentConseilPatient
    extends DocumentDonneesPharma
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -462413771999701742L;

    /**
     * Constructeur par défaut.
     */
    public DocumentConseilPatient()
    {
        this.setType(TypeDocumentEssai.CONSEIL_PATIENT);
    }
}
