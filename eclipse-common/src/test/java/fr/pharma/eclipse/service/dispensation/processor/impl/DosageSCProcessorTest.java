package fr.pharma.eclipse.service.dispensation.processor.impl;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.design.embedded.Frequence;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.domain.model.dispensation.ConseilDispensation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.service.dispensation.processor.helper.FrequenceHelper;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe DosageSCProcessor.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DosageSCProcessorTest extends AbstractEclipseJUnitTest {

    /**
     * Processor à tester.
     */
    private DosageSCProcessor processor;

    /**
     * Helper mocké.
     */
    private FrequenceHelper mockedHelper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedHelper = Mockito.mock(FrequenceHelper.class);
        this.processor = new DosageSCProcessor();
        this.processor.setFrequenceHelper(this.mockedHelper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.mockedHelper = null;
        this.processor = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.processor);
        Assert.assertNotNull(this.mockedHelper);
    }

    /**
     * Test de la méthode process.
     */
    @Test
    public void testProcess() {

        final Patient patient = Mockito.mock(Patient.class);
        final ProduitPrescrit produitPrescrit = new ProduitPrescrit();
        produitPrescrit.setPrescription(new Prescription());
        produitPrescrit.getPrescription().setInclusion(new Inclusion());
        produitPrescrit.getPrescription().getInclusion().setEssai(new Essai());
        produitPrescrit.getPrescription().getInclusion().setPatient(patient);

        final Produit produit = new Medicament();
        produit.setDenomination("produit");
        produitPrescrit.setProduit(produit);

        Mockito.when(patient.getSurface()).thenReturn(1.8912);
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setModePrescription(ModePrescription.DOSE_SURFACE);
        produitPrescrit.setConditionnement(conditionnement);
        produitPrescrit.setNbUniteDosage(new BigDecimal(2));

        Mockito.when(this.mockedHelper.convertToInt(Matchers.any(TempsPrescription.class), Matchers.any(Frequence.class))).thenReturn(15);
        final ConseilDispensation conseils = this.processor.process(produitPrescrit);

        Assert.assertNotNull(conseils);
        Assert.assertSame(produitPrescrit, conseils.getProduitPrescrit());
        Assert.assertTrue(57 == conseils.getNbASortir());
    }
}
