package fr.pharma.eclipse.domain.model.essai.detail.faisabilite;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Classe métier de commentaire sur la distribution des produits à d'autyres pharmacies, dans
 * l'étude de faisabilité de l'essai clinique.
 
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("FAISABILITE_ACHAT_PROD")
public class CommentaireFaisabiliteAchatProduits
    extends CommentaireEssaiFaisabilite
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2787874612972637198L;

}
