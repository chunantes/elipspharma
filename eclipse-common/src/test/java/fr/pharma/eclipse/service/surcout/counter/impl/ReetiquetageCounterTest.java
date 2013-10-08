package fr.pharma.eclipse.service.surcout.counter.impl;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.evenement.EvenementService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du compteur de reetiquetage : ReetiquetageCounter.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ReetiquetageCounterTest extends AbstractEclipseJUnitTest {

    /**
     * Counter.
     */
    private ReetiquetageCounter counter;

    /**
     * Service evenementService.
     */
    private EvenementService evenementService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.evenementService = Mockito.mock(EvenementService.class);
        this.counter = new ReetiquetageCounter();
        this.counter.setEvenementService(this.evenementService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.evenementService = null;
        this.counter = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.evenementService);
        Assert.assertNotNull(this.counter);
    }

    /**
     * Test de la méthode process(final Essai essai, final Patient patient,
     * final Calendar dateDebut, final Calendar dateFin).
     */
    @Test(expected = ValidationException.class)
    public void testProcessPatientNotNull() {

        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);
        final Patient patient = new Patient();
        final Essai essai = new Essai();

        this.counter.process(essai, patient, dateDebut, dateFin);

    }

    /**
     * Test de la méthode process(final Essai essai, final Patient patient,
     * final Calendar dateDebut, final Calendar dateFin).
     */
    @Test
    public void testProcessPatientNull() {

        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);
        final Patient patient = null;
        final Essai essai = new Essai();

        Mockito.when(this.evenementService.getAll(Matchers.any(MvtStockSearchCriteria.class))).thenReturn(new ArrayList<Evenement>());

        Assert.assertEquals(0, this.counter.process(essai, patient, dateDebut, dateFin));
        Mockito.verify(this.evenementService).getAll(Matchers.any(MvtStockSearchCriteria.class));

    }

    /**
     * Test de la méthode process(final Essai essai, final Patient patient,
     * DonneesPrevision).
     */
    @Test(expected = ValidationException.class)
    public void testProcessPrevisionPatientNotNull() {

        final Patient patient = new Patient();
        final Essai essai = new Essai();
        final DonneesPrevision prevision = new DonneesPrevision();

        this.counter.process(essai, patient, prevision);

    }

    /**
     * Test de la méthode process(final Essai essai, final Patient patient,
     * final Calendar dateDebut, final Calendar dateFin).
     */
    @Test
    public void testProcessPrevisionPatientNull() {

        final Patient patient = null;
        final Essai essai = new Essai();
        final DonneesPrevision prevision = new DonneesPrevision();
        prevision.setNbReetiquetages(5);
        Assert.assertEquals(5, this.counter.process(essai, patient, prevision));

    }
}
