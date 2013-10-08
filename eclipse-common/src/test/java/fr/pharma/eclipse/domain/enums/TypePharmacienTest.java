package fr.pharma.eclipse.domain.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'enum TypePharmacien.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TypePharmacienTest {
    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final TypePharmacien typePharmacien = TypePharmacien.PREPARATEUR;
        Assert.assertEquals(typePharmacien.getLibelle(), typePharmacien.toString());
    }
}
