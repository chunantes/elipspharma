package fr.pharma.eclipse.domain.criteria.stock;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester les méthodes de MvtStockSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class MvtStockSearchCriteriaTest {
    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        final Essai essai = new Essai();
        essai.setId(1L);
        criteria.setEssai(essai);
        criteria.setTypeMouvement(TypeMvtStock.APPROVISIONNEMENT);
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);
        criteria.setPharmacie(pharmacie);
        final Produit produit = new Medicament();
        produit.setId(1L);
        criteria.setProduit(produit);
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setId(1L);
        criteria.setConditionnement(conditionnement);
        criteria.setDateDebut(Calendar.getInstance(EclipseConstants.LOCALE));
        criteria.setDateFin(Calendar.getInstance(EclipseConstants.LOCALE));
        criteria.setNumLot("numLot");
        criteria.setDenominationProduit("denominationProduit");
        criteria.setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        criteria.clear();
        Assert.assertNull(criteria.getEssai());
        Assert.assertNull(criteria.getTypeMouvement());
        Assert.assertNull(criteria.getPharmacie());
        Assert.assertNull(criteria.getProduit());
        Assert.assertNull(criteria.getConditionnement());
        Assert.assertNull(criteria.getDateDebut());
        Assert.assertNull(criteria.getDateFin());
        Assert.assertNull(criteria.getTypesMouvement());
        Assert.assertNull(criteria.getNumLot());
        Assert.assertNull(criteria.getDenominationProduit());
        Assert.assertNull(criteria.getModePrescription());
    }

}
