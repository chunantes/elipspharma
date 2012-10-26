package fr.pharma.eclipse.service.patient.updator;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.service.patient.InclusionService;

/**
 * Interface des updators en charge de la mise à jour d'un bean Inclusion avant sa sauvegarde.
 
 * @version $Revision$ $Date$
 */
public interface InclusionBeforeSaveUpdator
    extends Serializable
{
    /**
     * Met à jour l'inclusion avant sa sauvegarde.
     * @param inclusion Inclusion.
     * @param service Service de l'inclusion.
     */
    void update(Inclusion inclusion,
                InclusionService service);
}
