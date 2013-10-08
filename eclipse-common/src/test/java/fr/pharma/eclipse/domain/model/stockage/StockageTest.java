package fr.pharma.eclipse.domain.model.stockage;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de stockages.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class StockageTest {
    /**
     * Méthode en charge de tester la récupération du nom complet sans parent.
     */
    @Test
    public void testGetNomCompetSansParent() {
        final Stockage stockage = new Stockage();
        stockage.setNom("stockage");
        Assert.assertEquals("stockage", stockage.getNomComplet());
    }

    /**
     * Méthode en charge de tester la récupération du nom complet avec parent.
     */
    @Test
    public void testGetNomCompetAvecParent() {
        final Stockage parent = new Stockage();
        parent.setNom("parent");
        final Stockage enfant = new Stockage();
        enfant.setNom("enfant");
        enfant.setParent(parent);
        Assert.assertEquals("parent-enfant", enfant.getNomComplet());
    }

}
