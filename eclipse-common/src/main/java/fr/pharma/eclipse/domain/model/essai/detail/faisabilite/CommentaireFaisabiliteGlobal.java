package fr.pharma.eclipse.domain.model.essai.detail.faisabilite;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Classe métier de commentaire global dans l'étude de faisabilité de l'essai
 * clinique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("FAISABILITE_ETUDE")
public class CommentaireFaisabiliteGlobal extends CommentaireEssaiFaisabilite {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -7068202216260817292L;

}
