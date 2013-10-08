package fr.pharma.eclipse.domain.criteria.produit;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.produit.TypeProduit;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Classe en charge de tester les méthodes de ProduitSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ProduitSearchCriteriaTest {

    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final ProduitSearchCriteria criteria = new ProduitSearchCriteria();
        criteria.setTypeProduit(TypeProduit.MEDICAMENT);
        criteria.setEssai(new Essai());
        criteria.clear();
        Assert.assertNull(criteria.getTypeProduit());
        Assert.assertNull(criteria.getEssai());
    }

}
