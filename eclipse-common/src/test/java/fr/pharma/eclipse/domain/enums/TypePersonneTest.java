package fr.pharma.eclipse.domain.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'enum TypePersonne.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TypePersonneTest {
    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final TypePersonne typePersonne = TypePersonne.INVESTIGATEUR;
        Assert.assertEquals(typePersonne.getLibelle(), typePersonne.toString());
    }

    /**
     * Méthode en charge de tester la méthode de récupération des droits
     * associés à un type de personne.
     */
    @Test
    public void testGetDroits() {
        final TypePersonne typePersonne = TypePersonne.INVESTIGATEUR;
        Assert.assertNotNull(typePersonne.getDroits());
    }

    /**
     * Méthode en charge de tester la méthode de récupération de la catégorie
     * associée au type de personne.
     */
    @Test
    public void testGetCategorie() {
        final TypePersonne typePersonne = TypePersonne.INVESTIGATEUR;
        Assert.assertNotNull(typePersonne.getCategorie());
    }

}
