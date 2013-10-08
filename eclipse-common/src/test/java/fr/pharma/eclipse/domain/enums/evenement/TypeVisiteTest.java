package fr.pharma.eclipse.domain.enums.evenement;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'enum TypeVisite.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TypeVisiteTest {
    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final TypeVisite typeVisite = TypeVisite.AUDIT_EXTERNE;
        Assert.assertEquals(typeVisite.getLibelle(), typeVisite.toString());
    }
}
