package fr.pharma.eclipse.domain.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'enum TypeRecherche.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TypeRechercheTest {
    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final TypeRecherche typeRecherche = TypeRecherche.RECHERCHE_BIOMEDICALE;
        Assert.assertEquals(typeRecherche.getLibelle(), typeRecherche.toString());
    }

}
