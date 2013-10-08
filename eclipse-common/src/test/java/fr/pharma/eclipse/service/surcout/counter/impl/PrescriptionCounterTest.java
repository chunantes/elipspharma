package fr.pharma.eclipse.service.surcout.counter.impl;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.service.prescription.PrescriptionService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du compteur de dispensations : PrescriptionCounter.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PrescriptionCounterTest extends AbstractEclipseJUnitTest {

    /**
     * Counter.
     */
    private PrescriptionCounter counter;

    /**
     * Service destructions.
     */
    private PrescriptionService prescriptionService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.prescriptionService = Mockito.mock(PrescriptionService.class);
        this.counter = new PrescriptionCounter();
        this.counter.setPrescriptionService(this.prescriptionService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.prescriptionService = null;
        this.counter = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.prescriptionService);
        Assert.assertNotNull(this.counter);
    }

    /**
     * Test de la méthode process(final Essai essai, final Patient patient,
     * final Calendar dateDebut, final Calendar dateFin).
     */
    @Test
    public void testProcessPatientNull() {

        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);
        final Patient patient = new Patient();
        final Essai essai = new Essai();

        Mockito.when(this.prescriptionService.getAll(Matchers.any(MvtStockSearchCriteria.class))).thenReturn(new ArrayList<Prescription>());

        Assert.assertEquals(0, this.counter.process(essai, patient, dateDebut, dateFin));
        Mockito.verify(this.prescriptionService).getAll(Matchers.any(MvtStockSearchCriteria.class));

    }

    /**
     * Test de la méthode process(final Essai essai, final Patient patient,
     * final Calendar dateDebut, final Calendar dateFin).
     */
    @Test
    public void testProcessPrevisionPatientNull() {

        final Patient patient = new Patient();
        final Essai essai = new Essai();
        final DonneesPrevision prevision = new DonneesPrevision();
        prevision.setNbPrescriptions(5);
        Assert.assertEquals(5, this.counter.process(essai, patient, prevision));

    }
}
