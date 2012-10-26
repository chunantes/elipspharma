package fr.pharma.eclipse.predicate.essai;

import java.io.Serializable;
import java.util.SortedSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.essai.EssaiElement;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;

/**
 * Classe de prédicat sur les element attchés à un essai qu'a le droit de voir l'utilisateur
 * courant (présence dans la liste des habilitations de l'essai).
 
 * @version $Revision$ $Date$
 */
public class EssaiElementHabilitationUserPredicate<BEAN extends EssaiElement>
    implements Predicate, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 8081721890893792597L;

    /**
     * Bean Personne correspondant à l'utilisateur connecté.
     */
    private final Personne personne;

    /**
     * Constructeur prenant en paramètre la Personne représentant l'utilisateur courant.
     * @param personne Personne.
     */
    public EssaiElementHabilitationUserPredicate(final Personne personne)
    {
        this.personne = personne;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate(final Object object)
    {
        final BEAN bean = (BEAN) object;
        final SortedSet<Habilitation> habilitations =
            bean.getEssai().getDetailContacts().getHabilitations();

        final Habilitation habilitation =
            (Habilitation) CollectionUtils.find(habilitations,
                                                new HabilitationPersonnePredicate(this.personne));

        return habilitation != null;
    }
}
