package fr.pharma.eclipse.service.prescription;

import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Interface de service de gestion de prescription.
 
 * @version $Revision$ $Date$
 */
public interface PrescriptionService
    extends GenericService<Prescription>
{
    /**
     * Retourne <true> si la sequence en paramètre est utilisée dans une prescription.
     * @param sequence La séquence.
     * @return <true> si la sequence en paramètre est utilisée dans une prescription.
     */
    boolean isSequenceUsedInPrescriptions(final Sequence sequence);

}
