package fr.pharma.eclipse.service.prescription;

import java.util.List;

import fr.pharma.eclipse.domain.criteria.prescription.PrescriptionSearchCriteria;
import fr.pharma.eclipse.domain.dto.PrescriptionDTO;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Interface de service de gestion de prescription.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface PrescriptionService extends GenericService<Prescription> {
    /**
     * Retourne <true> si la sequence en paramètre est utilisée dans une
     * prescription.
     * @param sequence La séquence.
     * @return <true> si la sequence en paramètre est utilisée dans une
     * prescription.
     */
    boolean isSequenceUsedInPrescriptions(final Sequence sequence);

    /**
     * Méthode en charge de retourner les prescriptions associées au critère de
     * recherche.
     * @param criteria Critère de recherche.
     * @return Liste des prescriptions.
     */
    List<PrescriptionDTO> retrieveResults(final PrescriptionSearchCriteria criteria);

}
