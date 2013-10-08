package fr.pharma.eclipse.domain.enums.stock;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de l'enum TypeMvtStock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TypeMvtStockTest {
    /**
     * Méthode en charge de tester la méthode toString.
     */
    @Test
    public void testToString() {
        final TypeMvtStock typeMouvement = TypeMvtStock.APPROVISIONNEMENT;
        Assert.assertEquals(typeMouvement.getLibelle(), typeMouvement.toString());
    }

}
