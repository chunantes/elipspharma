package fr.pharma.eclipse.domain.enums.stock;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'énumération de MotifRefus.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class MotifRefusTest {
    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final MotifRefus motifRefus = MotifRefus.COLIS_DEGRADE;
        Assert.assertEquals(motifRefus.getLibelle(), motifRefus.toString());
    }
}
