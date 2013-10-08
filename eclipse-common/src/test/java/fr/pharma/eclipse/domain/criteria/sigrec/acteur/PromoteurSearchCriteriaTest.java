package fr.pharma.eclipse.domain.criteria.sigrec.acteur;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.model.sigrec.acteur.PromoteurSigrec;

/**
 * Classe en charge de tester les méthodes de PromoteurSigrecSearchCriteria.
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
        final PromoteurSigrecSearchCriteria criteria = new PromoteurSigrecSearchCriteria();
        new PromoteurSigrec();
        criteria.setIdentifiant("identifiant");
        criteria.setRaisonSociale("nom");
        criteria.setType(TypePromoteur.ACADEMIQUE);
        criteria.clear();
        Assert.assertNull(criteria.getIdentifiant());
        Assert.assertNull(criteria.getRaisonSociale());
        Assert.assertNull(criteria.getType());
    }
}
