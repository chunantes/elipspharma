package fr.pharma.eclipse.service.dispensation.builder.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.dispensation.ConseilDispensation;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.service.dispensation.checker.Checker;
import fr.pharma.eclipse.service.dispensation.formatter.ConseilFormatter;
import fr.pharma.eclipse.service.dispensation.processor.ConseilDispensationProcessor;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du builder ConseilDispensationBuilder.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ConseilDispensationBuilderTest extends AbstractEclipseJUnitTest {

    /**
     * Builder à tester.
     */
    private ConseilDispensationBuilderImpl builder;

    /**
     * Checker.
     */
    private Checker<ProduitPrescrit> mockedChecker;

    /**
     * Checker.
     */
    private Checker<ProduitPrescrit> mockedChecker2;

    /**
     * Processor mocké.
     */
    private ConseilDispensationProcessor mockedProcessor;

    /**
     * Formatter mocké.
     */
    private ConseilFormatter mockedFormatter;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedProcessor = Mockito.mock(ConseilDispensationProcessor.class);
        this.mockedFormatter = Mockito.mock(ConseilFormatter.class);
        this.mockedChecker = Mockito.mock(Checker.class);
        this.mockedChecker2 = Mockito.mock(Checker.class);
        this.builder = new ConseilDispensationBuilderImpl();
        final Map<ModePrescription, ConseilDispensationProcessor> processors = new HashMap<ModePrescription, ConseilDispensationProcessor>();
        processors.put(ModePrescription.DOSE, this.mockedProcessor);
        this.builder.setProcessors(processors);
        final Map<ModePrescription, ConseilFormatter> formatters = new HashMap<ModePrescription, ConseilFormatter>();
        formatters.put(ModePrescription.DOSE, this.mockedFormatter);
        this.builder.setFormatters(formatters);
        final List<Checker<ProduitPrescrit>> checkers = new ArrayList<Checker<ProduitPrescrit>>();
        checkers.add(this.mockedChecker);
        checkers.add(this.mockedChecker2);
        this.builder.setCheckers(checkers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.builder = null;
        this.mockedChecker = null;
        this.mockedChecker2 = null;
        this.mockedFormatter = null;
        this.mockedProcessor = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.builder);
        Assert.assertNotNull(this.mockedChecker);
        Assert.assertNotNull(this.mockedChecker2);
        Assert.assertNotNull(this.mockedFormatter);
        Assert.assertNotNull(this.mockedProcessor);
    }

    /**
     * Test de la méthode support.
     */
    @Test
    public void testSupportKo() {
        final ProduitPrescrit produitPrescrit = Mockito.mock(ProduitPrescrit.class);

        Mockito.when(this.mockedChecker.check(produitPrescrit)).thenReturn(false);
        Mockito.when(this.mockedChecker2.check(produitPrescrit)).thenReturn(true);
        Assert.assertFalse(this.builder.support(produitPrescrit));
        Mockito.verify(this.mockedChecker).check(produitPrescrit);
        Mockito.verify(this.mockedChecker2).check(produitPrescrit);
    }

    /**
     * Test de la méthode support.
     */
    @Test
    public void testSupportOk() {
        final ProduitPrescrit produitPrescrit = Mockito.mock(ProduitPrescrit.class);
        final Conditionnement c = new Conditionnement();
        c.setModePrescription(ModePrescription.DOSE);
        Mockito.when(produitPrescrit.getConditionnement()).thenReturn(c);
        Mockito.when(this.mockedChecker.check(produitPrescrit)).thenReturn(true);
        Mockito.when(this.mockedChecker2.check(produitPrescrit)).thenReturn(true);
        Assert.assertTrue(this.builder.support(produitPrescrit));
        Mockito.verify(this.mockedChecker).check(produitPrescrit);
        Mockito.verify(this.mockedChecker2).check(produitPrescrit);
    }

    /**
     * Test de la méthode build().
     */
    @Test
    public void testBuild() {
        final ProduitPrescrit produitPrescrit = new ProduitPrescrit();
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setModePrescription(ModePrescription.DOSE);
        produitPrescrit.setConditionnement(conditionnement);
        Mockito.when(this.mockedProcessor.process(Matchers.any(ProduitPrescrit.class))).thenReturn(new ConseilDispensation());
        Assert.assertNotNull(this.builder.build(produitPrescrit));
        Mockito.verify(this.mockedProcessor).process(Matchers.any(ProduitPrescrit.class));
    }

    /**
     * Test de la méthode build().
     */
    @Test
    public void testFormatter() {
        final ProduitPrescrit produitPrescrit = new ProduitPrescrit();
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setModePrescription(ModePrescription.DOSE);
        produitPrescrit.setConditionnement(conditionnement);
        final ConseilDispensation conseilDispensation = new ConseilDispensation();
        conseilDispensation.setProduitPrescrit(produitPrescrit);
        Mockito.when(this.mockedFormatter.format(Matchers.any(ConseilDispensation.class))).thenReturn("ok");
        Assert.assertEquals("ok", this.builder.format(conseilDispensation));
        Mockito.verify(this.mockedFormatter).format(Matchers.any(ConseilDispensation.class));
    }
}
