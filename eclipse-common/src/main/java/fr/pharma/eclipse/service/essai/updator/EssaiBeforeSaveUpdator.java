package fr.pharma.eclipse.service.essai.updator;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.service.essai.EssaiService;

/**
 * Interface des updators en charge de la mise à jour d'un bean Essai avant sa sauvegarde.
 
 * @version $Revision$ $Date$
 */
public interface EssaiBeforeSaveUpdator
    extends Serializable
{
    /**
     * Met à jour l'essai avant sa sauvegarde.
     * @param essai Essai.
     * @param service Service de l'essai.
     */
    void update(Essai essai,
                EssaiService service);
}
