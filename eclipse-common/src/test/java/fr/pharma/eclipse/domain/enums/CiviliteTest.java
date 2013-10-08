package fr.pharma.eclipse.domain.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'enum Civilite.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class CiviliteTest {
    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final Civilite civilite = Civilite.MR;
        Assert.assertEquals(civilite.getLibelle(), civilite.toString());
    }

}
