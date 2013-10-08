package fr.pharma.eclipse.domain.criteria.localisation;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.localisation.Pole;

/**
 * Classe en charge de tester les méthodes de ServiceSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ServiceSearchCriteriaTest {

    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final ServiceSearchCriteria criteria = new ServiceSearchCriteria();
        criteria.setNom("nom");
        final Pole pole = new Pole();
        criteria.setPole(pole);
        criteria.clear();
        Assert.assertNull(criteria.getNom());
        Assert.assertNull(criteria.getPole());
    }

}
