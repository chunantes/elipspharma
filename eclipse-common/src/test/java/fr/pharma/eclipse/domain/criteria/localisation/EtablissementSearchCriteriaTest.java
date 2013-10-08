package fr.pharma.eclipse.domain.criteria.localisation;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes de EtablissementSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EtablissementSearchCriteriaTest {

    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final EtablissementSearchCriteria criteria = new EtablissementSearchCriteria();
        criteria.setNom("nom");
        criteria.clear();
        Assert.assertNull(criteria.getNom());
    }

}
