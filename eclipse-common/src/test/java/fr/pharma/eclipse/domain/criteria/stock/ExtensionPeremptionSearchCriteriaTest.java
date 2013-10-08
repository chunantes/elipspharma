package fr.pharma.eclipse.domain.criteria.stock;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester les méthodes de
 * ExtensionDatePeremptionSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ExtensionPeremptionSearchCriteriaTest {
    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final ExtensionPeremptionSearchCriteria criteria = new ExtensionPeremptionSearchCriteria();
        final Essai essai = new Essai();
        criteria.setEssai(essai);
        final Pharmacie pharmacie = new Pharmacie();
        criteria.setPharmacie(pharmacie);
        final Produit produit = new Medicament();
        criteria.setProduit(produit);
        final Conditionnement conditionnement = new Conditionnement();
        criteria.setConditionnement(conditionnement);
        criteria.setNumLot("numLot");
        criteria.setNumTraitement("numTraitement");
        criteria.setDateDebut(Calendar.getInstance(EclipseConstants.LOCALE));
        criteria.setDateFin(Calendar.getInstance(EclipseConstants.LOCALE));
        criteria.clear();
        Assert.assertNull(criteria.getEssai());
        Assert.assertNull(criteria.getPharmacie());
        Assert.assertNull(criteria.getProduit());
        Assert.assertNull(criteria.getConditionnement());
        Assert.assertNull(criteria.getNumLot());
        Assert.assertNull(criteria.getNumTraitement());
        Assert.assertNull(criteria.getDateDebut());
        Assert.assertNull(criteria.getDateFin());
    }
}
