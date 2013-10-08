package fr.pharma.eclipse.domain.enums.document;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester l'enumération du type de document associé aux
 * mouvements de stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TypeDocumentStockTest {

    /**
     * Méthode en charge de tester la méthode de récupération du type de
     * document.
     */
    @Test
    public void testGetTypeEclipse() {
        final TypeDocumentStock t = TypeDocumentStock.DESTRUCTION;
        Assert.assertEquals(TypeDocumentEclipse.MVT_STOCK, t.getTypeEclipse());
        Assert.assertEquals("destruction", t.getRepertoire());
        Assert.assertEquals("documentDestruction", t.getPropriete());
    }

}
