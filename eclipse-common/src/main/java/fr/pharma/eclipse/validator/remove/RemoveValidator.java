package fr.pharma.eclipse.validator.remove;

import fr.pharma.eclipse.domain.model.common.BeanObject;

/**
 * Interface de validation sur la suppression d'un objet.
 * @param <BEAN> Bean Objet Métier.
 
 * @version $Revision$ $Date$
 */
public interface RemoveValidator<BEAN extends BeanObject>
{
    /**
     * Méthode en charge de valider la suppression d'un objet.
     * @param bean Bean à supprimer.
     */
    void validate(BEAN bean);

}
