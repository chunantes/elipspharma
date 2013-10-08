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
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.service.dispensation.processor.helper.FrequenceHelper;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du processor ConditionnementPrimaireProcessor.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ConditionnementFormeProcessorTest extends AbstractEclipseJUnitTest {
    /**
     * Processor.
     */
    private ConditionnementFormeProcessor processor;

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
        this.processor = new ConditionnementFormeProcessor();
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
        Assert.assertNotNull(this.mockedHelper);
        Assert.assertNotNull(this.processor);
    }

    /**
     * Test de la méthode process.
     */
    @Test
    public void testProcessNull() {
        final ProduitPrescrit produitPrescrit = new ProduitPrescrit();

        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        produitPrescrit.setConditionnement(conditionnement);
        produitPrescrit.setNbUniteDosage(new BigDecimal(2));

        Mockito.when(this.mockedHelper.convertToInt(Matchers.any(TempsPrescription.class), Matchers.any(Frequence.class))).thenReturn(14);
        final ConseilDispensation conseils = this.processor.process(produitPrescrit);

        Assert.assertNull(conseils);
    }

    /**
     * Test de la méthode process.
     */
    @Test
    public void testProcessNotNull() {
        final ProduitPrescrit produitPrescrit = new ProduitPrescrit();

        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setNbUnitePrescription(new BigDecimal(10));
        conditionnement.setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        produitPrescrit.setConditionnement(conditionnement);
        produitPrescrit.setNbUniteDosage(new BigDecimal(2));

        Mockito.when(this.mockedHelper.convertToInt(Matchers.any(TempsPrescription.class), Matchers.any(Frequence.class))).thenReturn(14);
        final ConseilDispensation conseils = this.processor.process(produitPrescrit);

        Assert.assertNotNull(conseils);
        Assert.assertSame(produitPrescrit, conseils.getProduitPrescrit());
        Assert.assertTrue(3 == conseils.getNbASortir());
    }
}
