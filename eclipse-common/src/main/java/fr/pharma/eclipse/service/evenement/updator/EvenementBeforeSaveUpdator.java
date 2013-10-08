package fr.pharma.eclipse.service.evenement.updator;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.service.evenement.impl.EvenementServiceImpl;

/**
 * Interface des updators en charge de la mise à jour d'un bean Evenement avant
 * sa sauvegarde.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface EvenementBeforeSaveUpdator extends Serializable {
    /**
     * Met à jour l'evenement avant sa sauvegarde.
     * @param essai Essai.
     * @param service Service de l'essai.
     */
    void update(Evenement evenement,
                EvenementServiceImpl service);

    /**
     * Retourne <true> si l'updator supporte l'evenement en paramètre.
     * @param evenement L'evenement.
     * @return <true> si l'updator supporte l'evenement en paramètre.
     */
    boolean support(Evenement evenement);

}
