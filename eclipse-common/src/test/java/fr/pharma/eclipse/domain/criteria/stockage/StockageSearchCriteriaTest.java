package fr.pharma.eclipse.domain.criteria.stockage;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de StockageSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class StockageSearchCriteriaTest {

    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final StockageSearchCriteria criteria = new StockageSearchCriteria();
        criteria.setNom("nom");
        criteria.clear();
        Assert.assertNull(criteria.getNom());
    }

}
