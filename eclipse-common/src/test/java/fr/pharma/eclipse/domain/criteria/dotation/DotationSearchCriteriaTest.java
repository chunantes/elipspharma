package fr.pharma.eclipse.domain.criteria.dotation;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Classe en charge de tester les méthodes de DotationSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DotationSearchCriteriaTest {
    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final DotationSearchCriteria criteria = new DotationSearchCriteria();
        final Essai essai = new Essai();
        criteria.setEssai(essai);
        final Pharmacie pharmacie = new Pharmacie();
        criteria.setPharmacie(pharmacie);
        final Produit produit = new Medicament();
        criteria.setProduit(produit);
        final Service service = new Service();
        criteria.setService(service);
        criteria.setTraitee(Boolean.FALSE);
        criteria.clear();
        Assert.assertNull(criteria.getEssai());
        Assert.assertNull(criteria.getPharmacie());
        Assert.assertNull(criteria.getProduit());
        Assert.assertNull(criteria.getService());
        Assert.assertNull(criteria.getTraitee());
    }

}
