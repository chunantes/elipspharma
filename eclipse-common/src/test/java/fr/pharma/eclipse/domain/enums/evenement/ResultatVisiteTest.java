package fr.pharma.eclipse.domain.enums.evenement;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'enum ResultatVisite.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ResultatVisiteTest {
    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final ResultatVisite resultatVisite = ResultatVisite.EFFECTUE;
        Assert.assertEquals(resultatVisite.getLibelle(), resultatVisite.toString());
    }
}
