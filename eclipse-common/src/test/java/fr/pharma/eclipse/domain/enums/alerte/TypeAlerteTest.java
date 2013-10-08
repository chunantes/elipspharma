package fr.pharma.eclipse.domain.enums.alerte;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'enum TypeAlerte.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TypeAlerteTest {
    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final TypeAlerte typeAlerte = TypeAlerte.DDES_DOTATIONS_A_TRAITER;
        Assert.assertEquals(typeAlerte.getLibelle(), typeAlerte.toString());
    }
}
