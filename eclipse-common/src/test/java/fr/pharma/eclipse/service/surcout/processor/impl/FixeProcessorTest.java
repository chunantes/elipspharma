package fr.pharma.eclipse.service.surcout.processor.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.surcout.Acte;
import fr.pharma.eclipse.domain.enums.surcout.PerimetreCout;
import fr.pharma.eclipse.domain.enums.surcout.TypeCout;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;
import fr.pharma.eclipse.service.surcout.SurcoutFacade;
import fr.pharma.eclipse.service.surcout.checker.SurcoutChecker;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du processor FixeProcessor.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class FixeProcessorTest extends AbstractEclipseJUnitTest {
    /**
     * Processor à tester.
     */
    private FixeProcessor processor;

    /**
     * Facade.
     */
    private SurcoutFacade facade;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.facade = Mockito.mock(SurcoutFacade.class);
        this.processor = new FixeProcessor();
        this.processor.setCheckers(new HashMap<Acte, SurcoutChecker>());
        this.processor.setFacade(this.facade);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.facade = null;
        this.processor = null;
    }

    /**
     * {@inheritDoc}
     */
    @Test
    @Override
    public void testInit() {
        Assert.assertNotNull(this.facade);
        Assert.assertNotNull(this.processor);
    }

    /**
     * Test de la méthode process().
     */
    @Test
    public void testProcessNoRegle() {
        final Item item = new Item();

        final Essai essai = Mockito.mock(Essai.class);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        Assert.assertEquals(new BigDecimal(0), this.processor.process(item, essai, dateDebut, dateFin).getMontant());
    }

    /**
     * Test de la méthode process().
     */
    @Test
    public void testProcessUneRegleVariable() {
        final Item item = new Item();

        final Regle regle = new Regle();
        regle.setType(TypeCout.VARIABLE);
        item.getRegles().add(regle);

        final Essai essai = Mockito.mock(Essai.class);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        Assert.assertEquals(new BigDecimal(0), this.processor.process(item, essai, dateDebut, dateFin).getMontant());
    }

    /**
     * Test de la méthode process().
     */
    @Test
    public void testProcessUneRegleFixe() {
        final Item item = new Item();

        final Regle regle = new Regle();
        regle.setType(TypeCout.VARIABLE);
        final Regle r1 = new Regle();
        r1.setType(TypeCout.FIXE);
        r1.setPerimetre(PerimetreCout.ESSAI);
        r1.setPremiereAnnee(new BigDecimal(5));
        r1.setAnneesSuivantes(new BigDecimal(10));
        item.getRegles().add(r1);

        final Essai essai = Mockito.mock(Essai.class);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        Mockito.when(this.facade.countNbAnnees(essai, dateDebut, dateFin)).thenReturn(2);
        Mockito.when(this.facade.inPremiereAnnee(essai, dateDebut, dateFin)).thenReturn(true);

        Assert.assertEquals(new BigDecimal(25), this.processor.process(item, essai, dateDebut, dateFin).getMontant());
    }

    /**
     * Test de la méthode process().
     */
    @Test
    public void testProcessDeuxRegleFixe() {
        final Item item = new Item();

        final Regle regle = new Regle();
        regle.setType(TypeCout.VARIABLE);
        final Regle r1 = new Regle();
        r1.setType(TypeCout.FIXE);
        r1.setPerimetre(PerimetreCout.PATIENT);
        r1.setPremiereAnnee(new BigDecimal(5));
        r1.setAnneesSuivantes(new BigDecimal(10));
        item.getRegles().add(r1);
        final Regle r2 = new Regle();
        r2.setType(TypeCout.FIXE);
        r2.setPerimetre(PerimetreCout.ESSAI);
        r2.setPremiereAnnee(new BigDecimal(5));
        r2.setAnneesSuivantes(new BigDecimal(10));
        item.getRegles().add(r2);

        final Essai essai = Mockito.mock(Essai.class);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        Mockito.when(this.facade.countNbAnnees(essai, dateDebut, dateFin)).thenReturn(2);
        Mockito.when(this.facade.inPremiereAnnee(essai, dateDebut, dateFin)).thenReturn(true);
        Mockito.when(this.facade.countNbPatients(essai, dateDebut, dateFin, true)).thenReturn(50);

        Assert.assertEquals(new BigDecimal(1275), this.processor.process(item, essai, dateDebut, dateFin).getMontant());
    }

}
