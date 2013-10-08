package fr.pharma.eclipse.domain.model.essai.detail.faisabilite;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Classe métier de commentaire sur les achats de produits par la PUI, dans
 * l'étude de faisabilité de l'essai clinique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("FAISABILITE_DISTRIB_PHARMA")
public class CommentaireFaisabiliteDistributionPharmacies extends CommentaireEssaiFaisabilite {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2787874612972637198L;

}
