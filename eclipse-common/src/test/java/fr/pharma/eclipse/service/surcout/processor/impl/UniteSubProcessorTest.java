package fr.pharma.eclipse.service.surcout.processor.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.patient.PatientSearchCriteria;
import fr.pharma.eclipse.domain.enums.surcout.Acte;
import fr.pharma.eclipse.domain.enums.surcout.PerimetreCout;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;
import fr.pharma.eclipse.service.patient.PatientService;
import fr.pharma.eclipse.service.surcout.counter.ActeCounter;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Description de la classe.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class UniteSubProcessorTest extends AbstractEclipseJUnitTest {

    /**
     * Processor à tester.
     */
    private UniteSubProcessor subProcessor;

    /**
     * Counter.
     */
    private ActeCounter counter;

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
        this.counter = Mockito.mock(ActeCounter.class);
        this.subProcessor = new UniteSubProcessor();
        final Map<Acte, ActeCounter> acteCounters = new HashMap<Acte, ActeCounter>();
        acteCounters.put(Acte.DESTRUCTION, this.counter);
        this.subProcessor.setActeCounters(acteCounters);
        this.subProcessor.setPatientService(this.patientService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.counter = null;
        this.patientService = null;
        this.subProcessor = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.patientService);
        Assert.assertNotNull(this.counter);
        Assert.assertNotNull(this.subProcessor);
    }

    /*****************************************************
     * Test de la méthode PROCESS avec un min à null
     *****************************************************/

    /**
     * Test de la mtéhode process.
     */
    @Test
    public void testProcessMinNull0() {
        final Item item = new Item();
        item.setActe(Acte.DESTRUCTION);
        final Regle regle = new Regle();
        regle.getItems().add(item);
        regle.setPerimetre(PerimetreCout.ESSAI);
        regle.setMax(50);
        regle.setMin(null);
        regle.setMontant(new BigDecimal(100));
        final Essai essai = Mockito.mock(Essai.class);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        Mockito.when(this.counter.process(essai, null, dateDebut, dateFin)).thenReturn(0);

        Assert.assertEquals(new BigDecimal(0), this.subProcessor.process(item, regle, essai, dateDebut, dateFin).getMontant());

    }

    /**
     * Test de la mtéhode process.
     */
    @Test
    public void testProcessMinNull5() {
        final Item item = new Item();
        item.setActe(Acte.DESTRUCTION);
        final Regle regle = new Regle();
        regle.getItems().add(item);
        regle.setPerimetre(PerimetreCout.ESSAI);
        regle.setMax(50);
        regle.setMin(null);
        regle.setMontant(new BigDecimal(100));
        final Essai essai = Mockito.mock(Essai.class);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        Mockito.when(this.counter.process(essai, null, dateDebut, dateFin)).thenReturn(5);

        Assert.assertEquals(new BigDecimal(500), this.subProcessor.process(item, regle, essai, dateDebut, dateFin).getMontant());

    }

    /**
     * Test de la mtéhode process.
     */
    @Test
    public void testProcessMinNull50() {
        final Item item = new Item();
        item.setActe(Acte.DESTRUCTION);
        final Regle regle = new Regle();
        regle.getItems().add(item);
        regle.setPerimetre(PerimetreCout.ESSAI);
        regle.setMax(50);
        regle.setMin(null);
        regle.setMontant(new BigDecimal(100));
        final Essai essai = Mockito.mock(Essai.class);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        Mockito.when(this.counter.process(essai, null, dateDebut, dateFin)).thenReturn(50);

        Assert.assertEquals(new BigDecimal(5000), this.subProcessor.process(item, regle, essai, dateDebut, dateFin).getMontant());

    }

    /**
     * Test de la mtéhode process.
     */
    @Test
    public void testProcessMinNull51() {
        final Item item = new Item();
        item.setActe(Acte.DESTRUCTION);
        final Regle regle = new Regle();
        regle.getItems().add(item);
        regle.setPerimetre(PerimetreCout.ESSAI);
        regle.setMax(50);
        regle.setMin(null);
        regle.setMontant(new BigDecimal(100));
        final Essai essai = Mockito.mock(Essai.class);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        Mockito.when(this.counter.process(essai, null, dateDebut, dateFin)).thenReturn(51);

        Assert.assertEquals(new BigDecimal(5000), this.subProcessor.process(item, regle, essai, dateDebut, dateFin).getMontant());

    }

    /*****************************************************
     * Test de la méthode PROCESS avec un max à null
     *****************************************************/

    /**
     * Test de la mtéhode process.
     */
    @Test
    public void testProcessMaxNull0() {
        final Item item = new Item();
        item.setActe(Acte.DESTRUCTION);
        final Regle regle = new Regle();
        regle.getItems().add(item);
        regle.setPerimetre(PerimetreCout.ESSAI);
        regle.setMax(null);
        regle.setMin(51);
        regle.setMontant(new BigDecimal(100));
        final Essai essai = Mockito.mock(Essai.class);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        Mockito.when(this.counter.process(essai, null, dateDebut, dateFin)).thenReturn(0);

        Assert.assertEquals(new BigDecimal(0), this.subProcessor.process(item, regle, essai, dateDebut, dateFin).getMontant());

    }

    /**
     * Test de la mtéhode process.
     */
    @Test
    public void testProcessMaxNull5() {
        final Item item = new Item();
        item.setActe(Acte.DESTRUCTION);
        final Regle regle = new Regle();
        regle.getItems().add(item);
        regle.setPerimetre(PerimetreCout.ESSAI);
        regle.setMax(null);
        regle.setMin(51);
        regle.setMontant(new BigDecimal(100));
        final Essai essai = Mockito.mock(Essai.class);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        Mockito.when(this.counter.process(essai, null, dateDebut, dateFin)).thenReturn(5);

        Assert.assertEquals(new BigDecimal(0), this.subProcessor.process(item, regle, essai, dateDebut, dateFin).getMontant());

    }

    /**
     * Test de la mtéhode process.
     */
    @Test
    public void testProcessMaxNull50() {
        final Item item = new Item();
        item.setActe(Acte.DESTRUCTION);
        final Regle regle = new Regle();
        regle.getItems().add(item);
        regle.setPerimetre(PerimetreCout.ESSAI);
        regle.setMax(null);
        regle.setMin(51);
        regle.setMontant(new BigDecimal(100));
        final Essai essai = Mockito.mock(Essai.class);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        Mockito.when(this.counter.process(essai, null, dateDebut, dateFin)).thenReturn(50);

        Assert.assertEquals(new BigDecimal(0), this.subProcessor.process(item, regle, essai, dateDebut, dateFin).getMontant());

    }

    /**
     * Test de la mtéhode process.
     */
    @Test
    public void testProcessMaxNull51() {
        final Item item = new Item();
        item.setActe(Acte.DESTRUCTION);
        final Regle regle = new Regle();
        regle.getItems().add(item);
        regle.setPerimetre(PerimetreCout.ESSAI);
        regle.setMax(null);
        regle.setMin(51);
        regle.setMontant(new BigDecimal(100));
        final Essai essai = Mockito.mock(Essai.class);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        Mockito.when(this.counter.process(essai, null, dateDebut, dateFin)).thenReturn(51);

        Assert.assertEquals(new BigDecimal(100), this.subProcessor.process(item, regle, essai, dateDebut, dateFin).getMontant());

    }

    /**
     * Test de la mtéhode process.
     */
    @Test
    public void testProcessMaxNull100() {
        final Item item = new Item();
        item.setActe(Acte.DESTRUCTION);
        final Regle regle = new Regle();
        regle.getItems().add(item);
        regle.setPerimetre(PerimetreCout.ESSAI);
        regle.setMax(null);
        regle.setMin(51);
        regle.setMontant(new BigDecimal(100));
        final Essai essai = Mockito.mock(Essai.class);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        Mockito.when(this.counter.process(essai, null, dateDebut, dateFin)).thenReturn(100);

        Assert.assertEquals(new BigDecimal(5000), this.subProcessor.process(item, regle, essai, dateDebut, dateFin).getMontant());

    }

    /***********************************************************
     * Test de la méthode PROCESS avec un min et un max non null
     ***********************************************************/

    /**
     * Test de la mtéhode process.
     */
    @Test
    public void testProcess0() {
        final Item item = new Item();
        item.setActe(Acte.DESTRUCTION);
        final Regle regle = new Regle();
        regle.getItems().add(item);
        regle.setPerimetre(PerimetreCout.ESSAI);
        regle.setMax(100);
        regle.setMin(51);
        regle.setMontant(new BigDecimal(100));
        final Essai essai = Mockito.mock(Essai.class);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        Mockito.when(this.counter.process(essai, null, dateDebut, dateFin)).thenReturn(0);

        Assert.assertEquals(new BigDecimal(0), this.subProcessor.process(item, regle, essai, dateDebut, dateFin).getMontant());

    }

    /**
     * Test de la mtéhode process.
     */
    @Test
    public void testProcess5() {
        final Item item = new Item();
        item.setActe(Acte.DESTRUCTION);
        final Regle regle = new Regle();
        regle.getItems().add(item);
        regle.setPerimetre(PerimetreCout.ESSAI);
        regle.setMax(100);
        regle.setMin(51);
        regle.setMontant(new BigDecimal(100));
        final Essai essai = Mockito.mock(Essai.class);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        Mockito.when(this.counter.process(essai, null, dateDebut, dateFin)).thenReturn(5);

        Assert.assertEquals(new BigDecimal(0), this.subProcessor.process(item, regle, essai, dateDebut, dateFin).getMontant());

    }

    /**
     * Test de la mtéhode process.
     */
    @Test
    public void testProcess50() {
        final Item item = new Item();
        item.setActe(Acte.DESTRUCTION);
        final Regle regle = new Regle();
        regle.getItems().add(item);
        regle.setPerimetre(PerimetreCout.ESSAI);
        regle.setMax(100);
        regle.setMin(51);
        regle.setMontant(new BigDecimal(100));
        final Essai essai = Mockito.mock(Essai.class);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        Mockito.when(this.counter.process(essai, null, dateDebut, dateFin)).thenReturn(50);

        Assert.assertEquals(new BigDecimal(0), this.subProcessor.process(item, regle, essai, dateDebut, dateFin).getMontant());

    }

    /**
     * Test de la mtéhode process.
     */
    @Test
    public void testProcess51() {
        final Item item = new Item();
        item.setActe(Acte.DESTRUCTION);
        final Regle regle = new Regle();
        regle.getItems().add(item);
        regle.setPerimetre(PerimetreCout.ESSAI);
        regle.setMax(100);
        regle.setMin(51);
        regle.setMontant(new BigDecimal(100));
        final Essai essai = Mockito.mock(Essai.class);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        Mockito.when(this.counter.process(essai, null, dateDebut, dateFin)).thenReturn(51);

        Assert.assertEquals(new BigDecimal(100), this.subProcessor.process(item, regle, essai, dateDebut, dateFin).getMontant());

    }

    /**
     * Test de la mtéhode process.
     */
    @Test
    public void testProcess100() {
        final Item item = new Item();
        item.setActe(Acte.DESTRUCTION);
        final Regle regle = new Regle();
        regle.getItems().add(item);
        regle.setPerimetre(PerimetreCout.ESSAI);
        regle.setMax(100);
        regle.setMin(51);
        regle.setMontant(new BigDecimal(100));
        final Essai essai = Mockito.mock(Essai.class);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        Mockito.when(this.counter.process(essai, null, dateDebut, dateFin)).thenReturn(100);

        Assert.assertEquals(new BigDecimal(5000), this.subProcessor.process(item, regle, essai, dateDebut, dateFin).getMontant());

    }

    /**
     * Test de la mtéhode process.
     */
    @Test
    public void testProcess101() {
        final Item item = new Item();
        item.setActe(Acte.DESTRUCTION);
        final Regle regle = new Regle();
        regle.getItems().add(item);
        regle.setPerimetre(PerimetreCout.ESSAI);
        regle.setMax(100);
        regle.setMin(51);
        regle.setMontant(new BigDecimal(100));
        final Essai essai = Mockito.mock(Essai.class);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        Mockito.when(this.counter.process(essai, null, dateDebut, dateFin)).thenReturn(101);

        Assert.assertEquals(new BigDecimal(5000), this.subProcessor.process(item, regle, essai, dateDebut, dateFin).getMontant());

    }

    /**
     * Test de la mtéhode process.
     */
    @Test
    public void testProcess150() {
        final Item item = new Item();
        item.setActe(Acte.DESTRUCTION);
        final Regle regle = new Regle();
        regle.getItems().add(item);
        regle.setPerimetre(PerimetreCout.ESSAI);
        regle.setMax(100);
        regle.setMin(51);
        regle.setMontant(new BigDecimal(100));
        final Essai essai = Mockito.mock(Essai.class);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        Mockito.when(this.counter.process(essai, null, dateDebut, dateFin)).thenReturn(150);

        Assert.assertEquals(new BigDecimal(5000), this.subProcessor.process(item, regle, essai, dateDebut, dateFin).getMontant());

    }

    /**
     * Test de la mtéhode process.
     */
    @Test
    public void testProcess101WithPatient() {
        final Item item = new Item();
        item.setActe(Acte.DESTRUCTION);
        final Regle regle = new Regle();
        regle.getItems().add(item);
        regle.setPerimetre(PerimetreCout.PATIENT);
        regle.setMax(100);
        regle.setMin(51);
        regle.setMontant(new BigDecimal(100));
        final Essai essai = Mockito.mock(Essai.class);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        final Patient patient = Mockito.mock(Patient.class);
        final Patient patient2 = Mockito.mock(Patient.class);
        final List<Patient> patients = new ArrayList<Patient>();
        patients.add(patient);
        patients.add(patient2);

        Mockito.when(this.patientService.getAll(Matchers.any(PatientSearchCriteria.class))).thenReturn(patients);

        Mockito.when(this.counter.process(essai, patient, dateDebut, dateFin)).thenReturn(101);

        Mockito.when(this.counter.process(essai, patient2, dateDebut, dateFin)).thenReturn(51);

        Assert.assertEquals(new BigDecimal(5100), this.subProcessor.process(item, regle, essai, dateDebut, dateFin).getMontant());

    }
}
