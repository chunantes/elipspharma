package fr.pharma.eclipse.domain.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'enum TypePromoteur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TypePromoteurTest {
    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final TypePromoteur typePromoteur = TypePromoteur.INDUSTRIEL;
        Assert.assertEquals(typePromoteur.getLibelle(), typePromoteur.toString());
    }
}
