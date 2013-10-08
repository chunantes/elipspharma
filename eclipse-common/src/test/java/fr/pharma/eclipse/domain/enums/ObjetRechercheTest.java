package fr.pharma.eclipse.domain.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'enum ObjetRecherche.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ObjetRechercheTest {

    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final ObjetRecherche objetRecherche = ObjetRecherche.ALICAMENT;
        Assert.assertEquals(objetRecherche.getLibelle(), objetRecherche.toString());
    }

}
