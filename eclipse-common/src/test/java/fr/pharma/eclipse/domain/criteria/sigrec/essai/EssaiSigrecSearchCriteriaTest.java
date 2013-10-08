package fr.pharma.eclipse.domain.criteria.sigrec.essai;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.sigrec.acteur.PromoteurSigrec;

/**
 * Classe en charge de tester les méthodes de EssaiSigrecSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiSigrecSearchCriteriaTest {
    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final EssaiSigrecSearchCriteria criteria = new EssaiSigrecSearchCriteria();
        criteria.setNom("nom");
        criteria.setNumSigrec("num");
        criteria.setPromoteur(new PromoteurSigrec());
        criteria.clear();
        Assert.assertNull(criteria.getNom());
        Assert.assertNull(criteria.getNumSigrec());
        Assert.assertNull(criteria.getPromoteur());
    }

}
