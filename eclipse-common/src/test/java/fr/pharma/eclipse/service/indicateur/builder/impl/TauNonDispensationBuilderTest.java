package fr.pharma.eclipse.service.indicateur.builder.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.etat.DetailEtatEssai;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.DetailDonneesPharma;
import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.dispensation.DispensationService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du builder EssaiDispensationBuilder.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TauNonDispensationBuilderTest extends AbstractEclipseJUnitTest {
    /**
     * Builder.
     */
    private TauNonDispensationBuilder builder;

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
        this.builder = new TauNonDispensationBuilder();
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
     * @throws InterruptedException
     */
    @Test
    public void testBuild() throws InterruptedException {
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);

        final Essai e1 = new Essai();
        e1.setPharmaciePrincipale(pharmacie);
        final Essai e2 = new Essai();
        e2.setPharmaciePrincipale(new Pharmacie());
        e1.setId(1L);
        e1.setDetailDonneesPharma(new DetailDonneesPharma());
        e2.setId(2L);
        e2.setDetailDonneesPharma(new DetailDonneesPharma());
        final DetailEtatEssai detail = new DetailEtatEssai();
        detail.setEtatEssai(EtatEssai.EN_COURS);
        detail.setDateMaj(Calendar.getInstance());
        Thread.sleep(100);
        e1.getDetailsEtatEssai().add(detail);

        final List<Dispensation> total = new ArrayList<Dispensation>();
        final List<Dispensation> liste = new ArrayList<Dispensation>();
        final Dispensation d1 = new Dispensation();
        final Dispensation d2 = new Dispensation();
        final Dispensation d3 = new Dispensation();

        final Patient patient1 = new Patient();
        patient1.setId(1L);
        final Patient patient2 = new Patient();
        patient2.setId(2L);

        final Prescription p1 = new Prescription();
        p1.getProduitsPrescrits().add(new ProduitPrescrit());
        final Prescription p2 = new Prescription();
        p2.getProduitsPrescrits().add(new ProduitPrescrit());

        d1.setPrescription(p1);
        d2.setPrescription(p1);
        d3.setPrescription(p2);
        final Inclusion i1 = new Inclusion();
        i1.setPatient(patient1);
        final Inclusion i2 = new Inclusion();
        i2.setPatient(patient2);
        p1.setInclusion(i1);
        p2.setInclusion(i2);

        i1.setEssai(e1);
        i2.setEssai(e2);

        total.add(d1);
        total.add(d2);
        total.add(d3);
        liste.add(d1);
        Mockito.when(this.dispensationService.getAll(Matchers.any(SearchCriteria.class))).thenReturn(liste).thenReturn(total);
        final Indicateur indicateur = this.builder.build(pharmacie, Calendar.getInstance(), Calendar.getInstance());
        Assert.assertEquals("Indicateur", indicateur.getLibelle());
        Assert.assertEquals(0.333, indicateur.getValeur().doubleValue(), 0.001);
    }
}
