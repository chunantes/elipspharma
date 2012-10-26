package fr.pharma.eclipse.predicate.essai;

import java.io.Serializable;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.essai.EssaiElement;

/**
 * Classe de prédicat sur les éléments attaché à un essai qu'a le droit de voir l'utilisateur
 * courant (présence dans la liste des habilitations de l'essai).
 
 * @version $Revision$ $Date$
 */
public class EssaiElementHabilitationPharmacienPredicate<BEAN extends EssaiElement>
    implements Predicate, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 8081721890893792597L;

    /**
     * Bean Personne correspondant à l'utilisateur connecté.
     */
    private final Pharmacien pharmacien;

    /**
     * Constructeur prenant en paramètre la Personne représentant l'utilisateur courant.
     * @param personne Pharmacien.
     */
    public EssaiElementHabilitationPharmacienPredicate(final Pharmacien personne)
    {
        this.pharmacien = personne;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate(final Object object)
    {
        final BEAN bean = (BEAN) object;

        // Si l'élément n'est rattaché à aucun essai alors on l'affiche.
        if (bean.getEssai() == null)
        {
            return true;
        }
        else if (this.pharmacien.getPharmacies().contains(bean
                .getEssai()
                .getPharmaciePrincipale()))
        {
            return true;
        }
        else
        {
            return CollectionUtils.containsAny(bean.getEssai()
                                                       .getDetailDonneesPharma()
                                                       .getPharmacies(),
                                               this.pharmacien.getPharmacies());
        }
    }
}
