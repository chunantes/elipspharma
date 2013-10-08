package fr.pharma.eclipse.domain.criteria.localisation;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.localisation.Etablissement;

/**
 * Classe en charge de tester les méthodes de PoleSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PoleSearchCriteriaTest {

    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final PoleSearchCriteria criteria = new PoleSearchCriteria();
        final Etablissement etablissement = new Etablissement();
        criteria.setEtablissement(etablissement);
        criteria.setNom("nom");
        criteria.clear();
        Assert.assertNull(criteria.getEtablissement());
        Assert.assertNull(criteria.getNom());
    }

}
