package fr.pharma.eclipse.domain.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'enum NatureRecherche.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class NatureRechercheTest {

    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final NatureRecherche natureRecherche = NatureRecherche.EPIDEMIOLOGIE;
        Assert.assertEquals(natureRecherche.getLibelle(), natureRecherche.toString());
    }

}
