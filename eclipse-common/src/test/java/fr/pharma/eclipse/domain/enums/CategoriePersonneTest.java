package fr.pharma.eclipse.domain.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'enum CategoriePersonne.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class CategoriePersonneTest {
    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final CategoriePersonne categoriePersonne = CategoriePersonne.INTERNE;
        Assert.assertEquals(categoriePersonne.getLibelle(), categoriePersonne.toString());
    }
}
