package fr.pharma.eclipse.handler.habilitation;

import java.io.Serializable;
import java.util.List;

import fr.pharma.eclipse.domain.model.common.BeanObject;

/**
 * Classe en charge de gérer les opérations sur les beans métier par rapport aux habilitations de
 * l'utilisateur connecté.
 * @param <BEAN> Type des objets métiers gérés.
 
 * @version $Revision$ $Date$
 */
public interface HabilitationHandler<BEAN extends BeanObject>
    extends Serializable
{
    /**
     * Méthode en charge de purger les objets métier en fonction des habilitations de
     * l'utilisateur connecté.
     * @param beans Liste des objets à purger.
     */
    void purge(List<BEAN> beans);
}
