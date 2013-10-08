package fr.pharma.eclipse.domain.criteria.stockage;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.localisation.Etablissement;

/**
 * Classe en charge de tester les méthodes de PharmacieSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PharmacieSearchCriteriaTest {

    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final PharmacieSearchCriteria criteria = new PharmacieSearchCriteria();
        final Etablissement etablissement = new Etablissement();
        criteria.setEtablissement(etablissement);
        criteria.setNom("nom");
        criteria.clear();
        Assert.assertNull(criteria.getNom());
        Assert.assertNull(criteria.getEtablissement());
    }

}
