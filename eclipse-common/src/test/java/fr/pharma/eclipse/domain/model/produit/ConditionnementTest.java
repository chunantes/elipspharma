package fr.pharma.eclipse.domain.model.produit;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

/**
 * Description de la classe.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ConditionnementTest {

    @Test
    public void testSimpleDosage() {
        final Conditionnement c = new Conditionnement();
        c.setDosage(new BigDecimal("20"));
        Assert.assertEquals("20", c.getDosage().toString());
    }

    @Test
    public void testSimpleDosageII() {
        final Conditionnement c = new Conditionnement();
        c.setDosage(new BigDecimal("0.15000"));
        Assert.assertEquals("0.15", c.getDosage().toString());
    }

    @Test
    public void testDosageTrailingZeros() {
        final Conditionnement c = new Conditionnement();
        c.setDosage(new BigDecimal("20.000"));
        Assert.assertEquals("20", c.getDosage().toString());
    }

    @Test
    public void testDosageTrailingZerosII() {
        final Conditionnement c = new Conditionnement();
        c.setDosage(new BigDecimal("20.150"));
        Assert.assertEquals("20.15", c.getDosage().toString());
    }
}
