package fr.pharma.eclipse.domain.model.produit.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentProduit;

/**
 * Bean métier représentant un document relatif à la reconstitution PSM d'un
 * médicament.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("RECONSTITUTION_PSM")
public class DocumentReconstitutionPSM extends DocumentActesPharma {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3552420140396626957L;

    /**
     * Constructeur par défaut.
     */
    public DocumentReconstitutionPSM() {
        this.setType(TypeDocumentProduit.RECONSTITUTION_PSM);
    }

}
