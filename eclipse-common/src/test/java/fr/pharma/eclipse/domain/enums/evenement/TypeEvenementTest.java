package fr.pharma.eclipse.domain.enums.evenement;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'enum TypeEvenement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TypeEvenementTest {
    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final TypeEvenement typeEvenement = TypeEvenement.VISITE;
        Assert.assertEquals(typeEvenement.getLibelle(), typeEvenement.toString());
    }
}
