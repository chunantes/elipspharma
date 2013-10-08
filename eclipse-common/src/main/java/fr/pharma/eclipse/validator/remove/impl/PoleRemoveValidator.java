package fr.pharma.eclipse.validator.remove.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.localisation.ServiceSearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Pole;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.localisation.ServiceService;
import fr.pharma.eclipse.validator.remove.RemoveValidator;

/**
 * Classe de validation de suppression d'un objet Pole.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PoleRemoveValidator implements RemoveValidator<Pole>, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2830958369731883278L;

    /**
     * Service de gestion des services.
     */
    @Resource(name = "serviceService")
    private ServiceService serviceService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final Pole pole) {
        // Vérification Relation Pole-Service
        final ServiceSearchCriteria criteria = new ServiceSearchCriteria();
        criteria.setPole(pole);
        if (this.serviceService.hasResult(criteria)) {
            throw new ValidationException("remove", new String[]{"impossible" }, pole);
        }
    }

    /**
     * Setter pour serviceService.
     * @param serviceService le serviceService à écrire.
     */
    public void setServiceService(final ServiceService serviceService) {
        this.serviceService = serviceService;
    }

}
