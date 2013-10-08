package fr.pharma.eclipse.validator.save.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.domain.criteria.dispensation.DispensationSearchCriteria;
import fr.pharma.eclipse.domain.criteria.prescription.PrescriptionSearchCriteria;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.predicate.GenericPredicate;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.patient.PatientService;
import fr.pharma.eclipse.validator.save.SaveValidator;

/**
 * Validateur appelé afin de vérifier que le patient peut bien être sorti d'un
 * essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DesinclusionPatientValidator implements SaveValidator<Patient>, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -2300617222090424453L;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Service Dispensation.
     */
    @Resource(name = "dispensationService")
    private GenericService<Dispensation> dispensationService;

    /**
     * Service Prescription.
     */
    @Resource(name = "prescriptionService")
    private GenericService<Prescription> prescriptionService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final Patient bean,
                         final GenericService<Patient> beanService) {
        final PatientService service = (PatientService) beanService;
        final Inclusion inclusion = service.getInclusionCourante(bean);

        // si il n'est inclu dans aucun essai.
        if (inclusion == null) {
            throw new ValidationException("patient.nonInclu", new String[]{"error" }, bean);
        }

        // si des prescriptions non dispenses sont associées au patient.
        if (!this.getPrescriptionsNonDispensees(bean).isEmpty()) {
            throw new ValidationException("patient.prescription.nondispensee", new String[]{"error" }, bean);
        }

        // si des dispensations non terminées sont associées au patient.
        if (!this.getDispensations(bean).isEmpty()) {
            throw new ValidationException("patient.dispensation", new String[]{"error" }, bean);
        }
    }
    /**
     * Méthode en charge de retourner les prescriptions non dispensées associées
     * au patient.
     * @param bean Un patient.
     * @return La liste de prescriptions.
     */
    private List<Prescription> getPrescriptionsNonDispensees(final Patient patient) {
        final PrescriptionSearchCriteria prescriptionCriteria = new PrescriptionSearchCriteria();
        prescriptionCriteria.setPatient(patient);
        prescriptionCriteria.setDispense(false);
        final Inclusion inclusionCourante = (Inclusion) CollectionUtils.find(patient.getInclusions(), new GenericPredicate("actif", true));
        if (inclusionCourante != null) {
            prescriptionCriteria.setEssai(inclusionCourante.getEssai());
        }
        return this.prescriptionService.getAll(prescriptionCriteria);
    }

    // /**
    // * Méthode en charge de retourner les prescriptions associées au patient.
    // * @param patient Un patient.
    // * @return La liste de prescriptions.
    // */
    // private List<Prescription> getPrescriptions(final Patient patient) {
    //
    // final PrescriptionSearchCriteria prescriptionCriteria = new
    // PrescriptionSearchCriteria();
    // prescriptionCriteria.setPatient(patient);
    //
    // // on récupère l'essai actuel du patient
    // final Inclusion inclusionCourante = (Inclusion)
    // CollectionUtils.find(patient.getInclusions(), new
    // GenericPredicate("actif", true));
    // if (inclusionCourante != null) {
    // prescriptionCriteria.setEssai(inclusionCourante.getEssai());
    // }
    // return this.prescriptionService.getAll(prescriptionCriteria);
    // }

    /**
     * Méthode en charge de retourner les dispensations associées au patient.
     * @param bean Un patient.
     * @return La liste de dispensations.
     */
    private List<Dispensation> getDispensations(final Patient patient) {
        final DispensationSearchCriteria dispensationCriteria = new DispensationSearchCriteria();
        dispensationCriteria.setPatient(patient);
        dispensationCriteria.setDispense(false);
        return this.dispensationService.getAll(dispensationCriteria);
    }

    /**
     * Setter pour dispensationService.
     * @param dispensationService le dispensationService à écrire.
     */
    public void setDispensationService(final GenericService<Dispensation> dispensationService) {
        this.dispensationService = dispensationService;
    }

    /**
     * Setter pour prescriptionService.
     * @param prescriptionService le prescriptionService à écrire.
     */
    public void setPrescriptionService(final GenericService<Prescription> prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

}
