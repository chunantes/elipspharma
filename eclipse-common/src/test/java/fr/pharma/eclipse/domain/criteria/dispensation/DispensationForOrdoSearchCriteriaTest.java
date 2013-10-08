package fr.pharma.eclipse.domain.criteria.dispensation;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Classe en charge de tester les méthodes de DispensationForOrdoSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationForOrdoSearchCriteriaTest {
    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final DispensationForOrdoSearchCriteria criteria = new DispensationForOrdoSearchCriteria();
        criteria.setDateDebut(Calendar.getInstance());
        criteria.setDateFin(Calendar.getInstance());
        criteria.setPharmacie(new Pharmacie());
        criteria.clear();
        Assert.assertNull(criteria.getDateDebut());
        Assert.assertNull(criteria.getDateFin());
        Assert.assertNull(criteria.getPharmacie());
    }
}
