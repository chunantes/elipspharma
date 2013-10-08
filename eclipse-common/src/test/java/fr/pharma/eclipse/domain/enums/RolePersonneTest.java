package fr.pharma.eclipse.domain.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'enum RolePersonne.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class RolePersonneTest {
    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final RolePersonne rolePersonne = RolePersonne.ARC_INVESTIGATEUR;
        Assert.assertEquals(rolePersonne.getLibelle(), rolePersonne.toString());
    }
}
