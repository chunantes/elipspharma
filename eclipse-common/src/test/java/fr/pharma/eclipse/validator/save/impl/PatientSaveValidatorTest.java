package fr.pharma.eclipse.validator.save.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.patient.PatientService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du validator PAtientSaveValidator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PatientSaveValidatorTest extends AbstractEclipseJUnitTest {

    /**
     * Validator testé.
     */
    private PatientSaveValidator<Patient> validator;

    /**
     * Service patient.
     */
    private PatientService patientService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.patientService = Mockito.mock(PatientService.class);
        this.validator = new PatientSaveValidator<Patient>();
        this.validator.setIppRegex("[0-9]+");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.validator = null;
        this.patientService = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.validator);
        Assert.assertNotNull(this.patientService);
    }

    /**
     * Test de la méthode Validate.
     */
    @Test
    public void testValidateOk() {
        final Patient patient = new Patient();
        patient.setNumeroIpp("111");

        Mockito.when(this.patientService.getAll(Matchers.any(SearchCriteria.class))).thenReturn(new ArrayList<Patient>());

        this.validator.validate(patient, this.patientService);
    }

    /**
     * Test de la méthode Validate.
     */
    @Test
    public void testValidateOkModify() {
        final Patient patient = new Patient();
        patient.setNumeroIpp("111");
        patient.setId(1L);

        final Patient exist = new Patient();
        exist.setId(1L);

        final List<Patient> liste = new ArrayList<Patient>();
        liste.add(exist);

        Mockito.when(this.patientService.getAll(Matchers.any(SearchCriteria.class))).thenReturn(liste);

        this.validator.validate(patient, this.patientService);
    }

    /**
     * Test de la méthode Validate.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKo() {
        final Patient patient = new Patient();
        patient.setNumeroIpp("111");
        patient.setId(1L);

        final Patient exist = new Patient();
        exist.setId(2L);

        final List<Patient> liste = new ArrayList<Patient>();
        liste.add(exist);

        Mockito.when(this.patientService.getAll(Matchers.any(SearchCriteria.class))).thenReturn(liste);

        this.validator.validate(patient, this.patientService);
    }
}
