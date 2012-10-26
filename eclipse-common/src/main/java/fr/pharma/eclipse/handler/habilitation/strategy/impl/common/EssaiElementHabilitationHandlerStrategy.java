package fr.pharma.eclipse.handler.habilitation.strategy.impl.common;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.essai.EssaiElement;
import fr.pharma.eclipse.handler.habilitation.strategy.HabilitationHandlerStrategy;
import fr.pharma.eclipse.predicate.essai.EssaiElementHabilitationUserPredicate;

/**
 * Stratégie qui élimine les éléments attachés à un essai pour lesquels l'utilisateur n'a pas
 * d'habilitation active sur l'essai.
 * @param <BEAN> Type de l'élément attaché à l'essai
 
 * @version $Revision$ $Date$
 */
public class EssaiElementHabilitationHandlerStrategy<BEAN extends BeanObject, ELEMENT extends EssaiElement>
    implements HabilitationHandlerStrategy<BEAN>
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4131743922782329901L;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(final Personne personne)
    {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doPurge(final List<BEAN> beans,
                        final Personne personne)
    {
        // Récupération de la personne correspondant à l'utilisateur courant
        CollectionUtils.filter(beans,
                               new EssaiElementHabilitationUserPredicate<ELEMENT>(personne));
    }

}
