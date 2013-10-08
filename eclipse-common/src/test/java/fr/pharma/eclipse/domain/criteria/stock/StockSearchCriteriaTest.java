package fr.pharma.eclipse.domain.criteria.stock;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;

/**
 * Classe en charge de tester les méthodes de StockSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class StockSearchCriteriaTest {

    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final StockSearchCriteria criteria = new StockSearchCriteria();
        criteria.setDenominationProduit("denominationProduit");
        final Essai essai = new Essai();
        criteria.setEssai(essai);
        criteria.setNumLot("numLot");
        final Pharmacie pharmacie = new Pharmacie();
        criteria.setPharmacie(pharmacie);
        final Stockage stockage = new Stockage();
        criteria.setStockage(stockage);
        criteria.setDate(Calendar.getInstance());
        criteria.setHeuresMinutes("HH:MM");
        criteria.setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        final Conditionnement conditionnement = new Conditionnement();
        criteria.setConditionnement(conditionnement);
        criteria.clear();
        Assert.assertNull(criteria.getDenominationProduit());
        Assert.assertNull(criteria.getEssai());
        Assert.assertNull(criteria.getNumLot());
        Assert.assertNull(criteria.getPharmacie());
        Assert.assertNull(criteria.getStockage());
        Assert.assertNull(criteria.getDate());
        Assert.assertNull(criteria.getHeuresMinutes());
        Assert.assertNull(criteria.getModePrescription());
        Assert.assertNull(criteria.getConditionnement());
    }

}
