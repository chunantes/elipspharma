package fr.pharma.eclipse.validator.save.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.dispensation.DispensationSearchCriteria;
import fr.pharma.eclipse.domain.criteria.prescription.PrescriptionSearchCriteria;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.patient.PatientService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du validateur DesinclusionPatientValidator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DesinclusionPatientValidatorTest extends AbstractEclipseJUnitTest {

    /**
     * Validateur.
     */
    private DesinclusionPatientValidator validator;

    /**
     * Service prescription.
     */
    private GenericService<Prescription> mockedPrescriptionService;

    /**
     * Service dispensation.
     */
    private GenericService<Dispensation> mockedDispensationService;

    /**
     * Service patient mocké.
     */
    private PatientService mockedPatientService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedPatientService = Mockito.mock(PatientService.class);
        this.mockedDispensationService = Mockito.mock(GenericService.class);
        this.mockedPrescriptionService = Mockito.mock(GenericService.class);
        this.validator = new DesinclusionPatientValidator();
        this.validator.setDispensationService(this.mockedDispensationService);
        this.validator.setPrescriptionService(this.mockedPrescriptionService);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.mockedDispensationService = null;
        this.mockedPrescriptionService = null;
        this.validator = null;
        this.mockedPatientService = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.validator);
        Assert.assertNotNull(this.mockedPrescriptionService);
        Assert.assertNotNull(this.mockedDispensationService);
        Assert.assertNotNull(this.mockedPatientService);
    }

    /**
     * Test de la méthode validate.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKoInclusion() {

        final Patient patient = Mockito.mock(Patient.class);
        Mockito.when(this.mockedPatientService.getInclusionCourante(patient)).thenReturn(null);
        this.validator.validate(patient, this.mockedPatientService);

        Mockito.verify(this.mockedPatientService).getInclusionCourante(patient);
    }

    /**
     * KO si patient a une prescription
     */
    @Test(expected = ValidationException.class)
    public void testValidateKoPrescription() {

        final Patient patient = Mockito.mock(Patient.class);
        Mockito.when(this.mockedPatientService.getInclusionCourante(patient)).thenReturn(new Inclusion());
        final List<Prescription> prescriptions = new ArrayList<Prescription>();
        prescriptions.add(new Prescription());
        final List<Dispensation> dispensations = new ArrayList<Dispensation>();
        Mockito.when(this.mockedPrescriptionService.getAll(Matchers.any(PrescriptionSearchCriteria.class))).thenReturn(prescriptions);
        Mockito.when(this.mockedDispensationService.getAll(Matchers.any(DispensationSearchCriteria.class))).thenReturn(dispensations);
        this.validator.validate(patient, this.mockedPatientService);

        Mockito.verify(this.mockedDispensationService, Mockito.never()).getAll(Matchers.any(DispensationSearchCriteria.class));
        Mockito.verify(this.mockedPrescriptionService).getAll(Matchers.any(PrescriptionSearchCriteria.class));
        Mockito.verify(this.mockedPatientService).getInclusionCourante(patient);
    }

    /**
     * KO si le patient a une dispensation.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKoDispensation() {

        final Patient patient = Mockito.mock(Patient.class);
        Mockito.when(this.mockedPatientService.getInclusionCourante(patient)).thenReturn(new Inclusion());
        final List<Prescription> prescriptions = new ArrayList<Prescription>();
        final List<Dispensation> dispensations = new ArrayList<Dispensation>();
        dispensations.add(new Dispensation());
        Mockito.when(this.mockedPrescriptionService.getAll(Matchers.any(PrescriptionSearchCriteria.class))).thenReturn(prescriptions);
        Mockito.when(this.mockedDispensationService.getAll(Matchers.any(DispensationSearchCriteria.class))).thenReturn(dispensations);
        this.validator.validate(patient, this.mockedPatientService);

        Mockito.verify(this.mockedDispensationService).getAll(Matchers.any(DispensationSearchCriteria.class));
        Mockito.verify(this.mockedPrescriptionService).getAll(Matchers.any(PrescriptionSearchCriteria.class));
        Mockito.verify(this.mockedPatientService).getInclusionCourante(patient);
    }

    /**
     * Test de la méthode validate.
     */
    @Test
    public void testValidateOk() {

        final Patient patient = Mockito.mock(Patient.class);
        Mockito.when(this.mockedPatientService.getInclusionCourante(patient)).thenReturn(new Inclusion());
        final List<Prescription> prescriptions = new ArrayList<Prescription>();
        final List<Dispensation> dispensations = new ArrayList<Dispensation>();
        Mockito.when(this.mockedPrescriptionService.getAll(Matchers.any(PrescriptionSearchCriteria.class))).thenReturn(prescriptions);
        Mockito.when(this.mockedDispensationService.getAll(Matchers.any(DispensationSearchCriteria.class))).thenReturn(dispensations);
        this.validator.validate(patient, this.mockedPatientService);

        Mockito.verify(this.mockedDispensationService).getAll(Matchers.any(DispensationSearchCriteria.class));
        Mockito.verify(this.mockedPrescriptionService).getAll(Matchers.any(PrescriptionSearchCriteria.class));
        Mockito.verify(this.mockedPatientService).getInclusionCourante(patient);

    }
}
