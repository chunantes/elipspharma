package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation.helper;

import java.math.BigDecimal;

import org.junit.Assert;

import fr.pharma.eclipse.domain.enums.ConditionConservation;
import fr.pharma.eclipse.domain.enums.produit.UniteDosage;
import fr.pharma.eclipse.domain.enums.produit.UniteGestion;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanProduit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.detail.DetailStockage;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.jasper.constants.JasperConstants;
import fr.pharma.eclipse.utils.PharmacieUtils;
import fr.pharma.eclipse.utils.ProduitUtils;
import fr.pharma.eclipse.utils.StockageUtils;

/**
 * Classe utilitaire pour les tests des implémentations de la classe abstraite
 * {@link AbstractProduitsFillerHelper}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AbstractProduitsFillerHelperUtils {
    /**
     * Nom du produit test.
     */
    private static final String NOM_TEST = "nom du produit";

    /**
     * Code du produit test.
     */
    private static final String CODE_TEST = "pduit-001";

    /**
     * Conservation du produit test.
     */
    private static final ConditionConservation CONSERVATION_TEST = ConditionConservation.INF_30;

    /**
     * Méthode en charge de préparer un produit de test.
     * @param produit Produit de test.
     */
    public static void prepareProduitTest(final Produit produit) {
        produit.setDenomination(AbstractProduitsFillerHelperUtils.NOM_TEST);
        produit.setCode(AbstractProduitsFillerHelperUtils.CODE_TEST);
        produit.getDetailLogistique().setConditionConservation(AbstractProduitsFillerHelperUtils.CONSERVATION_TEST);

        long id = 100;
        final Pharmacie pharma1 = PharmacieUtils.makePharmacieTest(id++, "pharmacie 1");
        final Pharmacie pharma2 = PharmacieUtils.makePharmacieTest(id++, "pharmacie 2");
        final Pharmacie pharma3 = PharmacieUtils.makePharmacieTest(id++, "pharmacie 3");
        final Stockage stockage1 = StockageUtils.makeStockage(id++, "armoire1");
        final Stockage stockage2 = StockageUtils.makeStockage(id++, "armoire2", stockage1);
        final DetailStockage detailStock1 = new DetailStockage();
        detailStock1.setId(id++);
        detailStock1.setIdentifiantStockage("idStock1");
        detailStock1.setPharmacie(pharma1);
        detailStock1.setStockage(stockage1);
        final DetailStockage detailStock2 = new DetailStockage();
        detailStock2.setId(id++);
        detailStock2.setIdentifiantStockage("idStock2");
        detailStock2.setPharmacie(pharma2);
        detailStock2.setStockage(stockage2);
        final DetailStockage detailStock3 = new DetailStockage();
        detailStock3.setId(id++);
        detailStock3.setIdentifiantStockage(null);
        detailStock3.setPharmacie(pharma3);
        detailStock3.setStockage(null);
        final DetailStockage detailStock4 = new DetailStockage();
        detailStock4.setId(id++);
        detailStock4.setIdentifiantStockage(null);
        detailStock4.setPharmacie(null);
        detailStock4.setStockage(stockage2);

        produit.getDetailLogistique().getDetailsStockages().add(detailStock1);
        produit.getDetailLogistique().getDetailsStockages().add(detailStock2);
        produit.getDetailLogistique().getDetailsStockages().add(detailStock3);
        produit.getDetailLogistique().getDetailsStockages().add(detailStock4);

        final Conditionnement cond = ProduitUtils.makeConditionnementTest(id++, UniteGestion.COFFRET, new BigDecimal(3), UniteDosage.MICROGRAMME);
        produit.getConditionnements().add(cond);
    }

    /**
     * Méthode en charge de la vérification des données de jrProduit.<br>
     * ATTENTION: la donnée de test qui a permis de produire ce bean<br>
     * doit être passée dans la méthode prepareProduitTest.
     * @param jrProduit Bean jasper à vérifier.
     */
    public static void verify(final JRBeanProduit jrProduit) {
        Assert.assertEquals(AbstractProduitsFillerHelperUtils.NOM_TEST, jrProduit.getDenomination());
        Assert.assertEquals(AbstractProduitsFillerHelperUtils.CODE_TEST, jrProduit.getCode());
        Assert.assertEquals(AbstractProduitsFillerHelperUtils.CONSERVATION_TEST.getLibelle(), jrProduit.getConservation());

        final String expectedLieuxStockage = "pharmacie 1 : armoire1 (idStock1);" + " pharmacie 2 : armoire1-armoire2 (idStock2);" + " pharmacie 3;" + " armoire1-armoire2";
        Assert.assertEquals(expectedLieuxStockage, jrProduit.getLieuStockage());
        Assert.assertEquals(UniteGestion.COFFRET.getLibelle() + " : 3 µg", jrProduit.getConditionnement());
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, jrProduit.getPosologie());
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, jrProduit.getInfluAlimentation());
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, jrProduit.getDureeTraitement());
    }
}
