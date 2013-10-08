package fr.pharma.eclipse.validator.remove.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.prescription.PrescriptionService;
import fr.pharma.eclipse.validator.remove.RemoveValidator;

/**
 * Validateur de suppression d'une Séquence.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SequenceRemoveValidator implements RemoveValidator<Sequence>, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 3412573065849387110L;

    /**
     * Service prescription.
     */
    @Resource(name = "prescriptionService")
    private PrescriptionService service;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final Sequence sequence) {

        if ((sequence.getId() != null) && this.service.isSequenceUsedInPrescriptions(sequence)) {
            throw new ValidationException("remove", new String[]{"impossible" }, sequence);
        }
    }
    /**
     * Setter pour service.
     * @param service le service à écrire.
     */
    public void setService(final PrescriptionService service) {
        this.service = service;
    }

}
