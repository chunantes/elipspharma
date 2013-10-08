package fr.pharma.eclipse.domain.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'enum Droit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DroitTest {
    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final Droit droit = Droit.INVESTIGATEUR_PRINCIPAL;
        Assert.assertEquals(droit.getLibelle(), droit.toString());
    }
}
