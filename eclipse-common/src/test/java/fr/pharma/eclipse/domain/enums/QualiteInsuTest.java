package fr.pharma.eclipse.domain.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'enum QualiteInsu.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class QualiteInsuTest {
    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final QualiteInsu qualiteInsu = QualiteInsu.ESSAI_SIMPLE_AVEUGLE;
        Assert.assertEquals(qualiteInsu.getLibelle(), qualiteInsu.toString());
    }

}
