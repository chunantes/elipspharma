package fr.pharma.eclipse.handler.habilitation.strategy.impl.pharmacie;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.handler.habilitation.strategy.HabilitationHandlerStrategy;

/**
 * Stratégie qui élimine les pharmacies pour lesquels l'utilisateur n'a pas d'habilitation active.
 * @param <BEAN> Type de pharmacie.
 
 * @version $Revision$ $Date$
 */
public class PharmacieHabilitationPharmacienStrategy<BEAN extends Pharmacie>
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
        final Collection<Pharmacie> pharmaciesDuPharmacien = pharmacien.getPharmacies();
        final Predicate predicate = new Predicate() {

            @SuppressWarnings("unchecked")
            @Override
            public boolean evaluate(final Object object)
            {
                final BEAN pharmacie = (BEAN) object;
                return pharmaciesDuPharmacien.contains(pharmacie);
            }
        };
        CollectionUtils.filter(beans,
                               predicate);
    }

}
