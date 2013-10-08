package fr.pharma.eclipse.domain.criteria.sir;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester les méthodes du critère de recherche d'une
 * Personne Sir.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PersonneSirSearchCriteriaTest {

    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final PersonneSirSearchCriteria criteria = new PersonneSirSearchCriteria();
        criteria.setLogin("login");
        criteria.setNom("nom");
        criteria.setPrenom("prenom");
        criteria.setStrictSearchLogin(Boolean.TRUE);
        criteria.clear();
        Assert.assertNull(criteria.getLogin());
        Assert.assertNull(criteria.getNom());
        Assert.assertNull(criteria.getPrenom());
        Assert.assertNull(criteria.getStrictSearchLogin());
    }

}
