package fr.pharma.eclipse.domain.criteria.surcout;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de GrilleModeleSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GrilleModeleSearchCriteriaTest {

    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final GrilleModeleSearchCriteria criteria = new GrilleModeleSearchCriteria();
        criteria.setDateValidite(Calendar.getInstance());
        criteria.setNom("sdf");
        criteria.clear();
        Assert.assertNull(criteria.getNom());
        Assert.assertNull(criteria.getDateValidite());
    }

}
