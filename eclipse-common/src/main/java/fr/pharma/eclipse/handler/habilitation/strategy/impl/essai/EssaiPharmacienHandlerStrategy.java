package fr.pharma.eclipse.handler.habilitation.strategy.impl.essai;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.handler.habilitation.strategy.HabilitationHandlerStrategy;
import fr.pharma.eclipse.predicate.essai.EssaiForPharmacienPredicate;

/**
 * Stratégie qui élimine les essais pour lesquels l'utilisateur n'a pas d'habilitation active.
 * @param <BEAN> Type d'objet dérivé de {@link Essai}.
 
 * @version $Revision$ $Date$
 */
public class EssaiPharmacienHandlerStrategy<BEAN extends Essai>
    implements HabilitationHandlerStrategy<BEAN>
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -832674412316527563L;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(final Personne personne)
    {
        return TypePersonne.PHARMACIEN.equals(personne.getType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doPurge(final List<BEAN> beans,
                        final Personne personne)
    {
        final Pharmacien pharmacien = (Pharmacien) personne;
        CollectionUtils.filter(beans,
                               new EssaiForPharmacienPredicate(pharmacien));
    }

}
