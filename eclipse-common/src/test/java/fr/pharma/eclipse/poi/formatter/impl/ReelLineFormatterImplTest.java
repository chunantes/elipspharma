package fr.pharma.eclipse.poi.formatter.impl;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.surcout.ModeCalcul;
import fr.pharma.eclipse.domain.enums.surcout.PerimetreCout;
import fr.pharma.eclipse.domain.enums.surcout.TypeCout;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.Resultat;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du formatter ReelLineFormatterImpl.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ReelLineFormatterImplTest extends AbstractEclipseJUnitTest {

    /**
     * Formatter.
     */
    private ReelLineFormatterImpl formatter;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.formatter = new ReelLineFormatterImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.formatter = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.formatter);
    }

    /**
     * Test de la méthode format().
     */
    @Test
    public void testFormat() {
        final Resultat resultat = new Resultat();
        resultat.setMontant(new BigDecimal(5));
        final Item item = new Item();
        item.setTheme("theme");
        item.setCategorie("categorie");

        // regle fixe
        final Regle r1 = new Regle();
        r1.setType(TypeCout.FIXE);
        r1.setPremiereAnnee(new BigDecimal(200));
        r1.setAnneesSuivantes(new BigDecimal(100));
        r1.setPerimetre(PerimetreCout.ESSAI);
        item.getRegles().add(r1);

        // regle variable par patient lot forfaitaire
        final Regle r2 = new Regle();
        r2.setType(TypeCout.VARIABLE);
        r2.setMin(10);
        r2.setMax(20);
        r2.setMode(ModeCalcul.LOT_FORFAITAIRE);
        r2.setMontant(new BigDecimal(200));
        r2.setPerimetre(PerimetreCout.PATIENT);
        item.getRegles().add(r2);

        // regle variable par essai unitaires
        final Regle r3 = new Regle();
        r3.setType(TypeCout.VARIABLE);
        r3.setMin(10);
        r3.setMax(20);
        r3.setMode(ModeCalcul.UNITE);
        r3.setMontant(new BigDecimal(200));
        r3.setPerimetre(PerimetreCout.ESSAI);
        item.getRegles().add(r3);

        final String[] result = this.formatter.format(item, resultat);

        Assert.assertNotNull(result);
        Assert.assertEquals(8, result.length);
        Assert.assertEquals("theme", result[0]);
        Assert.assertEquals("categorie", result[1]);
        Assert.assertEquals("200€\n", result[2]);
        Assert.assertEquals("100€\n", result[3]);
        Assert.assertEquals("Cout Variable - Par patient - Lot forfaitaire - 10/20-200€\n", result[4]);
        Assert.assertEquals("0", result[5]);
        Assert.assertEquals("Cout Variable - Pour l'essai - Unité - 10/20-200€\n", result[6]);
        Assert.assertEquals("5€", result[7]);
    }
}
