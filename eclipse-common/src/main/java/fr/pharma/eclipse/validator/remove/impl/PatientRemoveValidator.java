package fr.pharma.eclipse.validator.remove.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.patient.InclusionSearchCriteria;
import fr.pharma.eclipse.domain.criteria.prescription.PrescriptionSearchCriteria;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.patient.InclusionService;
import fr.pharma.eclipse.service.prescription.PrescriptionService;
import fr.pharma.eclipse.validator.remove.RemoveValidator;

/**
 * Classe de validation de suppression d'un objet Patient.
 
 * @version $Revision$ $Date$
 */
public class PatientRemoveValidator
    implements RemoveValidator<Patient>, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -6036962109873985932L;

    /**
     * Service de gestion des pharmacies.
     */
    @Resource(name = "prescriptionService")
    private PrescriptionService prescriptionService;

    /**
     * Service de gestion des inclsuions.
     */
    @Resource(name = "inclusionService")
    private InclusionService inclusionService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final Patient patient)
    {
        // Vérification Relation Patient-Inclusion
        final InclusionSearchCriteria crit = new InclusionSearchCriteria();
        crit.setPatient(patient);
        if (this.inclusionService.hasResult(crit))
        {
            throw new ValidationException("remove",
                                          new String[]
                                          {"impossible" },
                                          patient);
        }

        // Vérification Relation Patient-Prescription
        final PrescriptionSearchCriteria critPre = new PrescriptionSearchCriteria();
        critPre.setPatient(patient);
        if (this.prescriptionService.hasResult(critPre))
        {
            throw new ValidationException("remove",
                                          new String[]
                                          {"impossible" },
                                          patient);
        }

    }

    /**
     * Setter pour prescriptionService.
     * @param prescriptionService Le prescriptionService à écrire.
     */
    public void setPrescriptionService(final PrescriptionService prescriptionService)
    {
        this.prescriptionService = prescriptionService;
    }

    /**
     * Setter pour inclusionService.
     * @param inclusionService Le inclusionService à écrire.
     */
    public void setInclusionService(final InclusionService inclusionService)
    {
        this.inclusionService = inclusionService;
    }

}
