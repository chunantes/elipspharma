package fr.pharma.eclipse.domain.criteria.dispensation;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.TypeElementToCheck;

/**
 * Classe en charge de tester les méthodes de ElementToCheckSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ElementToCheckSearchCriteriaTest {

    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final ElementToCheckSearchCriteria criteria = new ElementToCheckSearchCriteria();
        criteria.setType(TypeElementToCheck.CONDITIONNEMENT);
        criteria.clear();
        Assert.assertNull(criteria.getType());
    }

}
