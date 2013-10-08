package fr.pharma.eclipse.comparator.produit.detail.helper;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.PharmacieUtils;
import fr.pharma.eclipse.utils.StockageUtils;

/**
 * Test de la classe {@link DetailStockageComparatorHelper}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DetailStockageComparatorHelperTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private DetailStockageComparatorHelper helper;

    /**
     * COnstante de valeur par défaut.
     */
    private static final String DEFAULT = "-";

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.helper = new DetailStockageComparatorHelper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.helper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.helper);
    }

    /**
     * Test de la méthode appendPharmaciePart(java.lang.StringBuilder,
     * fr.pharma.eclipse.domain.model.stockage.Pharmacie, java.lang.String) -
     * valeur par défaut.
     */
    @Test
    public void testAppendPharmaciePartDefaut() {
        final StringBuilder builder = new StringBuilder();
        this.helper.appendPharmaciePart(builder, null, DetailStockageComparatorHelperTest.DEFAULT);
        Assert.assertEquals(DetailStockageComparatorHelperTest.DEFAULT, builder.toString());
    }

    /**
     * Test de la méthode appendPharmaciePart(java.lang.StringBuilder,
     * fr.pharma.eclipse.domain.model.stockage.Pharmacie, java.lang.String) -
     * cas nominal.
     */
    @Test
    public void testAppendPharmaciePart() {
        final StringBuilder builder = new StringBuilder();
        final long idPharma = 150;
        final Pharmacie pharmacie = PharmacieUtils.makePharmacieTest(idPharma);
        this.helper.appendPharmaciePart(builder, pharmacie, DetailStockageComparatorHelperTest.DEFAULT);
        Assert.assertEquals(String.valueOf(idPharma), builder.toString());
    }

    /**
     * Test de la méthode appendStockagePart(java.lang.StringBuilder,
     * fr.pharma.eclipse.domain.model.stockage.Stockage, java.lang.String) -
     * valeur par défaut.
     */
    @Test
    public void testAppendStockagePartDefaut() {
        final StringBuilder builder = new StringBuilder();
        this.helper.appendStockagePart(builder, null, DetailStockageComparatorHelperTest.DEFAULT);
        Assert.assertEquals(DetailStockageComparatorHelperTest.DEFAULT, builder.toString());
    }

    /**
     * Test de la méthode appendStockagePart(java.lang.StringBuilder,
     * fr.pharma.eclipse.domain.model.stockage.Stockage, java.lang.String) - cas
     * nominal.
     */
    @Test
    public void testAppendStockagePart() {
        final StringBuilder builder = new StringBuilder();
        long id = 1;
        final Stockage stockageParent = StockageUtils.makeStockage(id++, "Armoire A");
        final Stockage stockage = StockageUtils.makeStockage(id++, "Armoire B", stockageParent);
        this.helper.appendStockagePart(builder, stockage, DetailStockageComparatorHelperTest.DEFAULT);
        Assert.assertEquals(stockage.getNomComplet(), builder.toString());
    }

    /**
     * Test de la méthode appendIdStockagePart(java.lang.StringBuilder,
     * java.lang.String, java.lang.String) - valeur par défaut.
     */
    @Test
    public void testAppendIdStockagePartDefaut() {
        final StringBuilder builder = new StringBuilder();
        this.helper.appendIdStockagePart(builder, "     ", DetailStockageComparatorHelperTest.DEFAULT);
        Assert.assertEquals(DetailStockageComparatorHelperTest.DEFAULT, builder.toString());
    }

    /**
     * Test de la méthode appendIdStockagePart(java.lang.StringBuilder,
     * java.lang.String, java.lang.String) - cas nominal.
     */
    @Test
    public void testAppendIdStockagePart() {
        final StringBuilder builder = new StringBuilder();
        final String idStockage = "  premier casier sur la gauche   ";
        this.helper.appendIdStockagePart(builder, idStockage, DetailStockageComparatorHelperTest.DEFAULT);
        Assert.assertEquals(idStockage.trim(), builder.toString());
    }
}
