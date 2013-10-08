package fr.pharma.eclipse.service.indicateur.builder.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.dispensation.DispensationService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du builder NbPatientBuilder.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class NbPatientBuilderTest extends AbstractEclipseJUnitTest {
    /**
     * Builder.
     */
    private NbPatientBuilder builder;

    /**
     * Service Dispensation.
     */
    private DispensationService dispensationService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.dispensationService = Mockito.mock(DispensationService.class);
        this.builder = new NbPatientBuilder();
        this.builder.setDispensationService(this.dispensationService);
        this.builder.setLibelle("Indicateur");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.dispensationService = null;
        this.builder = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.dispensationService);
        Assert.assertNotNull(this.builder);
    }

    /**
     * Test de la méthode build.
     */
    @Test
    public void testBuild() {
        final List<Dispensation> liste = new ArrayList<Dispensation>();
        final Dispensation d1 = new Dispensation();
        final Dispensation d2 = new Dispensation();
        final Dispensation d3 = new Dispensation();

        final Patient patient1 = new Patient();
        final Patient patient2 = new Patient();

        final Prescription p1 = new Prescription();
        final Prescription p2 = new Prescription();

        d1.setPrescription(p1);
        d2.setPrescription(p1);
        d3.setPrescription(p2);
        final Inclusion i1 = new Inclusion();
        i1.setPatient(patient1);
        final Inclusion i2 = new Inclusion();
        i2.setPatient(patient2);
        p1.setInclusion(i1);
        p2.setInclusion(i2);

        liste.add(d1);
        liste.add(d2);
        liste.add(d3);
        Mockito.when(this.dispensationService.getAll(Matchers.any(SearchCriteria.class))).thenReturn(liste);
        final Indicateur indicateur = this.builder.build(new Pharmacie(), Calendar.getInstance(), Calendar.getInstance());
        Assert.assertEquals("Indicateur", indicateur.getLibelle());
        Assert.assertEquals(new BigDecimal(1), indicateur.getValeur());
    }
}
