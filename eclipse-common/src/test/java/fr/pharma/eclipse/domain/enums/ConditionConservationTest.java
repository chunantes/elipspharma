package fr.pharma.eclipse.domain.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'enum ConditionConservation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ConditionConservationTest {
    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final ConditionConservation conditionConservation = ConditionConservation.INF_25;
        Assert.assertEquals(conditionConservation.getLibelle(), conditionConservation.toString());
    }

}
