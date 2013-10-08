package fr.pharma.eclipse.domain.enums;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.patient.FormuleSurfaceCorporelle;

/**
 * Classe en charge de tester les méthodes de l'enum FormuleSurfaceCorporelle..
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class FormuleSurfaceCorporelleTest {
    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final FormuleSurfaceCorporelle formule = FormuleSurfaceCorporelle.DUBOIS;
        Assert.assertEquals(formule.getLibelle(), formule.toString());
    }
}
