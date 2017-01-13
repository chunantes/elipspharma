package fr.pharma.eclipse.service.surcout.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.dates.DetailDates;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.service.patient.InclusionService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la facade SurcoutFacade.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SurcoutFacadeTest extends AbstractEclipseJUnitTest {
    /**
     * Facade des surcouts.
     */
    private SurcoutFacadeImpl facade;

    /**
     * Service Inclusion mocké.
     */
    private InclusionService mockedInclusionService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedInclusionService = Mockito.mock(InclusionService.class);
        this.facade = new SurcoutFacadeImpl();
        this.facade.setInclusionService(this.mockedInclusionService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.mockedInclusionService = null;
        this.facade = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.facade);
        Assert.assertNotNull(this.mockedInclusionService);
    }

    /**
     * Test de la méthode testCountNbPatients.
     */
    @Test
    public void testCountNbPatients() {
        final List<Inclusion> liste = new ArrayList<Inclusion>();
        Mockito.when(this.mockedInclusionService.count(Matchers.any(SearchCriteria.class))).thenReturn(0L);
        Assert.assertEquals(0, this.facade.countNbPatients(new Essai(), Calendar.getInstance(), Calendar.getInstance(), false));
    }

    /**
     * Test de la méthode countPrescription.
     */
    @Test
    public void testCountNbPatientsNotEmpty() {
        final List<Inclusion> liste = new ArrayList<Inclusion>();
        liste.add(new Inclusion());
        Mockito.when(this.mockedInclusionService.count(Matchers.any(SearchCriteria.class))).thenReturn(1L);
        Assert.assertEquals(1, this.facade.countNbPatients(new Essai(), Calendar.getInstance(), Calendar.getInstance(), false));
    }

    /**
     * Test de la méthode inPremiereAnnee.
     */
    @Test
    public void inPremiereAnneeFalse2() {
        final Calendar debutEssai = Calendar.getInstance();
        final Essai essai = Mockito.mock(Essai.class);
        final DetailDates dates = Mockito.mock(DetailDates.class);
        Mockito.when(essai.getDetailDates()).thenReturn(dates);
        Mockito.when(dates.getDebutEtude()).thenReturn(debutEssai);

        final Calendar dateFin = Calendar.getInstance();
        dateFin.add(Calendar.MONTH, 3);

        Assert.assertFalse(this.facade.inPremiereAnnee(essai, debutEssai, dateFin));

    }

    /**
     * Test de la méthode inPremiereAnnee.
     */
    @Test
    public void inPremiereAnneeFalse3() {
        final Calendar debutEssai = Calendar.getInstance();
        final Essai essai = Mockito.mock(Essai.class);
        final DetailDates dates = Mockito.mock(DetailDates.class);
        Mockito.when(essai.getDetailDates()).thenReturn(dates);
        Mockito.when(dates.getDebutEtude()).thenReturn(debutEssai);

        Assert.assertFalse(this.facade.inPremiereAnnee(essai, debutEssai, debutEssai));

    }

    /**
     * Test de la méthode inPremiereAnnee.
     */
    @Test
    public void inPremiereAnneeFalse4() {
        final Calendar debutEssai = Calendar.getInstance();
        final Essai essai = Mockito.mock(Essai.class);
        final DetailDates dates = Mockito.mock(DetailDates.class);
        Mockito.when(essai.getDetailDates()).thenReturn(dates);
        Mockito.when(dates.getDebutEtude()).thenReturn(debutEssai);

        final Calendar c = Calendar.getInstance();
        c.set(2010, 1, 1);

        Assert.assertFalse(this.facade.inPremiereAnnee(essai, debutEssai, c));

    }

    /**
     * Test de countNbAnnees.
     */
    @Test
    public void testCountNbAnnees2() {
        final Calendar dateDebut = Calendar.getInstance();

        dateDebut.set(2005, 5, 2);

        final Calendar debutEssai = Calendar.getInstance();
        debutEssai.set(2001, 5, 2);
        final Essai essai = Mockito.mock(Essai.class);
        final DetailDates dates = Mockito.mock(DetailDates.class);
        Mockito.when(essai.getDetailDates()).thenReturn(dates);
        Mockito.when(dates.getDebutEtude()).thenReturn(debutEssai);

        final Calendar dateFin = Calendar.getInstance();
        dateFin.set(2009, 5, 2);
        final int result = this.facade.countNbAnnees(essai, dateDebut, dateFin);
        Assert.assertEquals(4, result);
    }

}
