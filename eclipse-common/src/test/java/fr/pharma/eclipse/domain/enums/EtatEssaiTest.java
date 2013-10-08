package fr.pharma.eclipse.domain.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'enum EtatEssai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EtatEssaiTest {

    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final EtatEssai etatEssai = EtatEssai.EN_COURS;
        Assert.assertEquals(etatEssai.getLibelle(), etatEssai.toString());
    }

}
