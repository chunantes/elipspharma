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
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.service.dispensation.processor.helper.FrequenceHelper;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe DosageKgProcessor.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DosageProcessorTest extends AbstractEclipseJUnitTest {

    /**
     * Processor à tester.
     */
    private DosageProcessor processor;

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
        this.processor = new DosageProcessor();
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

        final ProduitPrescrit produitPrescrit = new ProduitPrescrit();
        produitPrescrit.setPrescription(new Prescription());
        produitPrescrit.getPrescription().setInclusion(new Inclusion());
        produitPrescrit.getPrescription().getInclusion().setEssai(new Essai());

        final Produit produit = new Medicament();
        produit.setDenomination("produit");
        produitPrescrit.setProduit(produit);
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setModePrescription(ModePrescription.DOSE);
        produitPrescrit.setConditionnement(conditionnement);
        produitPrescrit.setNbUniteDosage(new BigDecimal(2));
        Mockito.when(this.mockedHelper.convertToInt(Matchers.any(TempsPrescription.class), Matchers.any(Frequence.class))).thenReturn(15);
        final ConseilDispensation conseils = this.processor.process(produitPrescrit);

        Assert.assertNotNull(conseils);
        Assert.assertSame(produitPrescrit, conseils.getProduitPrescrit());
        Assert.assertTrue(30 == conseils.getNbASortir());
    }

}
