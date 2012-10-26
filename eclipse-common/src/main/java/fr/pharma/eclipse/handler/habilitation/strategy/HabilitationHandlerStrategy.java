package fr.pharma.eclipse.handler.habilitation.strategy;

import java.io.Serializable;
import java.util.List;

import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.common.BeanObject;

/**
 * Interface des stratégies applicables par le handler d'habilitations.
 * @param <BEAN> Type d'objets métiers gérés.
 
 * @version $Revision$ $Date$
 */
public interface HabilitationHandlerStrategy<BEAN extends BeanObject>
    extends Serializable
{
    /**
     * Méthode qui indique si la stratégie s'applique à l'utilisateur connecté.
     * @param personne Personne connectée.
     * @return true ssi la stratégie doit être appliquée.
     */
    boolean supports(Personne personne);

    /**
     * Méthode en charge d'appliquer une stratégie à la liste de beans à partir du jeton de
     * sécurité.
     * @param beans Liste d'objets à purger.
     * @param personne Personne connectée.
     */
    void doPurge(List<BEAN> beans,
                 Personne personne);
}
