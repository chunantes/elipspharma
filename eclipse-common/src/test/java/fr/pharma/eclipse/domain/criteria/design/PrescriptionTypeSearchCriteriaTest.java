package fr.pharma.eclipse.domain.criteria.design;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.produit.Medicament;

/**
 * Classe en charge de tester les méthodes du critère de recherche de
 * PrescriptionTypeSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PrescriptionTypeSearchCriteriaTest {

    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final PrescriptionTypeSearchCriteria criteria = new PrescriptionTypeSearchCriteria();
        criteria.setProduit(new Medicament());
        criteria.clear();
        Assert.assertNull(criteria.getProduit());
    }
}
