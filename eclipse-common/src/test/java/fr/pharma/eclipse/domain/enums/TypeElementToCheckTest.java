package fr.pharma.eclipse.domain.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'enum TypeElementToCheck.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TypeElementToCheckTest {
    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final TypeElementToCheck typeElementToCheck = TypeElementToCheck.RECONSTITUTION_PSM;
        Assert.assertEquals(typeElementToCheck.getLibelle(), typeElementToCheck.toString());
        Assert.assertNotNull(typeElementToCheck.getLibelleDisplay());
    }
}
