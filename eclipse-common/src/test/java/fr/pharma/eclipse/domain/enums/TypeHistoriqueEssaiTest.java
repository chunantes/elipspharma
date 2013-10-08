package fr.pharma.eclipse.domain.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test des méthodes de l'énumération TypeHistoriqueEssai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TypeHistoriqueEssaiTest {

    /**
     * Méthode en charge de tester la méthode getProperty.
     */
    @Test
    public void testGetProperty() {
        final TypeHistoriqueEssai typeHistorique = TypeHistoriqueEssai.GENERAL;
        Assert.assertEquals("modifs", typeHistorique.getModifsPropertyFromEssai());
        Assert.assertNull(typeHistorique.getModifsParentPropertyFromEssai());
    }
}
