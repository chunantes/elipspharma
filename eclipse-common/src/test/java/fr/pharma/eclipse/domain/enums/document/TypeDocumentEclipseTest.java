package fr.pharma.eclipse.domain.enums.document;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'enum de type de document de
 * l'application Eclipse.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TypeDocumentEclipseTest {
    /**
     * Méthode en charge de tester les types de documents .
     */
    @Test
    public void testDocStock() {
        Assert.assertEquals("mvtsStock", TypeDocumentEclipse.MVT_STOCK.getRepertoire());
    }

}
