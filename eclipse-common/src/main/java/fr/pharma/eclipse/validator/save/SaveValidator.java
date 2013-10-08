package fr.pharma.eclipse.validator.save;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Interface de validation sur la sauvegarde d'un objet.
 * @param <BEAN> Bean Objet Métier.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface SaveValidator<BEAN extends BeanObject> extends Serializable {
    /**
     * Méthode en charge de valider la sauvegarde d'un objet.
     * @param bean Bean à sauvegarder.
     * @param beanService Service appelant.
     */
    void validate(BEAN bean,
                  GenericService<BEAN> beanService);

}
