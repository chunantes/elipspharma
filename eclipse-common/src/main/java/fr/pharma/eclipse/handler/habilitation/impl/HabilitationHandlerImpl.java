package fr.pharma.eclipse.handler.habilitation.impl;

import java.util.List;

import fr.pharma.eclipse.domain.enums.RolePersonne;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.user.UserSecurity;
import fr.pharma.eclipse.handler.habilitation.HabilitationHandler;
import fr.pharma.eclipse.handler.habilitation.strategy.HabilitationHandlerStrategy;
import fr.pharma.eclipse.service.user.UserService;

/**
 * Classe en charge d'appliquer une liste de stratégies pour ne transmettre à l'utilisateur
 * connecté que les informations auxquelles il a accès.<br />
 * L'administrateur a tous les droits.
 * @param <BEAN> Type d'objets métiers gérés.
 
 * @version $Revision$ $Date$
 */
public class HabilitationHandlerImpl<BEAN extends BeanObject>
    implements HabilitationHandler<BEAN>
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8923684625282855579L;

    /**
     * Service de gestion des utilisateurs.
     */
    private UserService userService;

    /**
     * Liste de stratégies à prendre en compte.
     */
    private List<HabilitationHandlerStrategy<BEAN>> strategies;

    /**
     * {@inheritDoc}
     */
    @Override
    public void purge(final List<BEAN> beans)
    {
        final UserSecurity userSecurity = this.userService.getUser();
        final RolePersonne roleUser = userSecurity.getRole();
        if (RolePersonne.ADMIN.equals(roleUser)
            || userSecurity.getIsAdmin())
        {
            return;
        }

        final Personne personne = this.userService.getPersonne();
        for (final HabilitationHandlerStrategy<BEAN> strategy : this.strategies)
        {
            if (strategy.supports(personne))
            {
                strategy.doPurge(beans,
                                 personne);
                return;
            }
        }
    }
    /**
     * Getter sur userService.
     * @return Retourne le userService.
     */
    UserService getUserService()
    {
        return this.userService;
    }

    /**
     * Setter pour userService.
     * @param userService le userService à écrire.
     */
    public void setUserService(final UserService userService)
    {
        this.userService = userService;
    }

    /**
     * Getter sur strategies.
     * @return Retourne le strategies.
     */
    List<HabilitationHandlerStrategy<BEAN>> getStrategies()
    {
        return this.strategies;
    }

    /**
     * Setter pour strategies.
     * @param strategies le strategies à écrire.
     */
    public void setStrategies(final List<HabilitationHandlerStrategy<BEAN>> strategies)
    {
        this.strategies = strategies;
    }

}
