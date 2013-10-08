package fr.pharma.eclipse.service.patient.impl;

import java.util.Calendar;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.validation.ValidationException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.enums.patient.FormuleSurfaceCorporelle;
import fr.pharma.eclipse.domain.model.patient.HistoriquePatient;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.suivi.patient.PatientSuivi;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.patient.dictionary.SurfaceCorporelleDictionary;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.validator.save.impl.DesinclusionPatientValidator;
import fr.pharma.eclipse.validator.save.impl.PatientSaveValidator;

/**
 * Classe en charge de tester le service de gestion des patients.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PatientServiceImplTest {

    /**
     * Service de gestion de patients à tester.
     */
    private PatientServiceImpl service;

    /**
     * Dao mocké.
     */
    private GenericDao<Patient> mockDao;

    /**
     * Suivi Factory mocké.
     */
    private SuiviFactory<PatientSuivi> mockSuiviFactory;

    /**
     * Dictionary mocké.
     */
    private SurfaceCorporelleDictionary mockedDictionary;

    /**
     * Desinclusion patient validator.
     */
    private DesinclusionPatientValidator validator;

    /**
     * Validateur patient save.
     */
    private PatientSaveValidator saveValidator;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.saveValidator = Mockito.mock(PatientSaveValidator.class);
        this.validator = Mockito.mock(DesinclusionPatientValidator.class);
        this.mockDao = Mockito.mock(GenericDao.class);
        this.service = new PatientServiceImpl(this.mockDao);
        this.mockedDictionary = Mockito.mock(SurfaceCorporelleDictionary.class);
        this.mockSuiviFactory = Mockito.mock(SuiviFactory.class);
        this.service.setPatientSuiviFactory(this.mockSuiviFactory);
        this.service.setDictionary(this.mockedDictionary);
        this.service.setValidator(this.validator);
        this.service.setPatientSaveValidator(this.saveValidator);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.mockDao = null;
        this.service = null;
        this.mockSuiviFactory = null;
        this.validator = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.mockDao);
        Assert.assertNotNull(this.mockSuiviFactory);
        Assert.assertNotNull(this.validator);
    }

    /**
     * Méthode en charge de tester la sauvegarde des patients.
     */
    @Test
    public void testSavePatientOk() {
        final Patient patient = Mockito.mock(Patient.class);
        final PatientSuivi suivi = new PatientSuivi();
        Mockito.when(this.mockDao.reattach(patient)).thenReturn(patient);
        Mockito.when(this.mockSuiviFactory.getInitializedObject()).thenReturn(suivi);
        Mockito.when(patient.getModifs()).thenReturn(new TreeSet<PatientSuivi>());
        Mockito.when(this.mockDao.save(patient)).thenReturn(patient);
        final Patient result = this.service.save(patient);
        Mockito.verify(this.mockDao).reattach(patient);
        Mockito.verify(this.mockSuiviFactory).getInitializedObject();
        Assert.assertEquals(1, result.getModifs().size());
    }

    /**
     * Méthode en charge de tester la sauvegarde des patients.
     */
    @Test(expected = ValidationException.class)
    public void testSavePatientKo() {
        final Patient patient = Mockito.mock(Patient.class);
        final PatientSuivi suivi = new PatientSuivi();
        Mockito.when(this.mockDao.reattach(patient)).thenReturn(patient);
        Mockito.when(this.mockSuiviFactory.getInitializedObject()).thenReturn(suivi);
        Mockito.when(patient.getModifs()).thenReturn(new TreeSet<PatientSuivi>());
        Mockito.doThrow(new ValidationException()).when(this.saveValidator).validate(patient, this.service);
        Mockito.when(this.mockDao.save(patient)).thenReturn(patient);
        final Patient result = this.service.save(patient);
        Mockito.verify(this.mockDao).reattach(patient);
        Mockito.verify(this.mockSuiviFactory).getInitializedObject();
        Assert.assertEquals(1, result.getModifs().size());
    }

    /**
     * Test de la méthode getInclusionCourante().
     */
    @Test
    public void testGetInclusionActive() {
        final Patient patient = new Patient();
        final Inclusion i1 = new Inclusion();
        i1.setDateInclusion(Calendar.getInstance());
        final Inclusion i2 = new Inclusion();
        i2.setDateInclusion(Calendar.getInstance());
        i2.getDateInclusion().add(Calendar.YEAR, 1);
        i1.setActif(true);
        i1.setId(1L);
        i2.setId(2L);
        patient.getInclusions().add(i1);
        patient.getInclusions().add(i2);

        final Inclusion result = this.service.getInclusionCourante(patient);
        Assert.assertEquals(i1.getId(), result.getId());
    }

    /**
     * Test de la méthode updateSurfaceCorporelle.
     */
    @Test
    public void testUpdateSurfaceCorporelleKo1() {
        final HistoriquePatient historique = new HistoriquePatient();
        final Patient p = new Patient();
        historique.setPatient(p);
        this.service.updateSurfaceCorporelle(historique);
        Mockito.verify(this.mockedDictionary, Mockito.never()).process(Matchers.any(FormuleSurfaceCorporelle.class), Matchers.anyDouble(), Matchers.anyDouble());
    }

    /**
     * Test de la méthode updateSurfaceCorporelle.
     */
    @Test
    public void testUpdateSurfaceCorporelleKo2() {
        final HistoriquePatient historique = new HistoriquePatient();
        final Patient p = new Patient();
        p.setDateNaissance(Calendar.getInstance(EclipseConstants.LOCALE));
        historique.setPatient(p);
        this.service.updateSurfaceCorporelle(historique);
        Mockito.verify(this.mockedDictionary, Mockito.never()).process(Matchers.any(FormuleSurfaceCorporelle.class), Matchers.anyDouble(), Matchers.anyDouble());
    }

    /**
     * Test de la méthode updateSurfaceCorporelle.
     */
    @Test
    public void testUpdateSurfaceCorporelleKo3() {
        final HistoriquePatient historique = new HistoriquePatient();
        historique.setPoid(10.0);
        final Patient p = new Patient();
        p.setDateNaissance(Calendar.getInstance(EclipseConstants.LOCALE));
        historique.setPatient(p);
        this.service.updateSurfaceCorporelle(historique);
        Mockito.verify(this.mockedDictionary, Mockito.never()).process(Matchers.any(FormuleSurfaceCorporelle.class), Matchers.anyDouble(), Matchers.anyDouble());
    }

    /**
     * Test de la méthode updateSurfaceCorporelle.
     */
    @Test
    public void testUpdateSurfaceCorporelleOk() {
        final HistoriquePatient historique = new HistoriquePatient();
        historique.setPoid(10.0);
        historique.setTaille(10.0);
        final Patient p = new Patient();
        p.setDateNaissance(Calendar.getInstance(EclipseConstants.LOCALE));
        historique.setPatient(p);
        Mockito.when(this.mockedDictionary.process(FormuleSurfaceCorporelle.MOSTELLER, 10.0, 10.0)).thenReturn(15.0);
        this.service.updateSurfaceCorporelle(historique);
        Mockito.verify(this.mockedDictionary).process(Matchers.any(FormuleSurfaceCorporelle.class), Matchers.anyDouble(), Matchers.anyDouble());
        Assert.assertTrue(historique.getSurfaceCorporelle() == 15.0);
    }

    /**
     * Test de la méthode desinclurePatient.
     */
    @Test
    public void testDesinclurePatient() {
        final Patient patient = Mockito.mock(Patient.class);
        final Inclusion inclusion = new Inclusion();
        inclusion.setActif(true);
        final SortedSet<Inclusion> inclusions = new TreeSet<Inclusion>();
        inclusions.add(inclusion);
        Mockito.when(patient.getInclusions()).thenReturn(inclusions);
        final PatientSuivi suivi = new PatientSuivi();
        Mockito.when(this.mockDao.reattach(patient)).thenReturn(patient);
        Mockito.when(this.mockSuiviFactory.getInitializedObject()).thenReturn(suivi);
        Mockito.when(patient.getModifs()).thenReturn(new TreeSet<PatientSuivi>());
        this.service.desinclure(patient);

        Assert.assertFalse(inclusion.getActif());
        Mockito.verify(this.validator).validate(Matchers.any(Patient.class), Matchers.any(GenericService.class));
    }
}
