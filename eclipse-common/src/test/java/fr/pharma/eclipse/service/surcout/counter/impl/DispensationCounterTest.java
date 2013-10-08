package fr.pharma.eclipse.service.surcout.counter.impl;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.service.dispensation.DispensationService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du compteur de dispensations : DispensationCounter.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationCounterTest extends AbstractEclipseJUnitTest {

    /**
     * Counter.
     */
    private DispensationCounter counter;

    /**
     * Service destructions.
     */
    private DispensationService dispensationService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.dispensationService = Mockito.mock(DispensationService.class);
        this.counter = new DispensationCounter();
        this.counter.setDispensationService(this.dispensationService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.dispensationService = null;
        this.counter = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.dispensationService);
        Assert.assertNotNull(this.counter);
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
        prevision.setNbDispensations(5);
        Assert.assertEquals(5, this.counter.process(essai, patient, prevision));

    }
}
