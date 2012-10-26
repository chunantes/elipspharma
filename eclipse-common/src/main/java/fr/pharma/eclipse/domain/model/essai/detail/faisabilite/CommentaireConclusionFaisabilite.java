package fr.pharma.eclipse.domain.model.essai.detail.faisabilite;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Classe métier de commentaire sur la conclusion de l'étude de faisabilité de l'essai clinique.
 
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("FAISABILITE_CONCL")
public class CommentaireConclusionFaisabilite
    extends CommentaireEssaiFaisabilite
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1376800169220475331L;

}
