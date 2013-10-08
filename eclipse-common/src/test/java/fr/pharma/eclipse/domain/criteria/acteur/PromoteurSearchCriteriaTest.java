package fr.pharma.eclipse.domain.criteria.acteur;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.TypePromoteur;

/**
 * Classe en charge de tester les méthodes de PromoteurSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PromoteurSearchCriteriaTest {

    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final PromoteurSearchCriteria criteria = new PromoteurSearchCriteria();
        criteria.setRaisonSociale("raisonSociale");
        criteria.setType(TypePromoteur.ACADEMIQUE);
        criteria.setIdentifiant("identifiant");
        criteria.clear();
        Assert.assertNull(criteria.getRaisonSociale());
        Assert.assertNull(criteria.getType());
        Assert.assertNull(criteria.getIdentifiant());
    }

}
