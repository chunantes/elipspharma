package fr.pharma.eclipse.domain.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'enum PhaseRecherche.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PhaseRechercheTest {

    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final PhaseRecherche phaseRecherche = PhaseRecherche.I;
        Assert.assertEquals(phaseRecherche.getLibelle(), phaseRecherche.toString());
    }
}
