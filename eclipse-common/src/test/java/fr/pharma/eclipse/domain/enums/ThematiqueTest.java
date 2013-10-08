package fr.pharma.eclipse.domain.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'enum Thematique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ThematiqueTest {
    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final Thematique thematique = Thematique.CANCEROLOGIE;
        Assert.assertEquals(thematique.getLibelle(), thematique.toString());
    }

}
