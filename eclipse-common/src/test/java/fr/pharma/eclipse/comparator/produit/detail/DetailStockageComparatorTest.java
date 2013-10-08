package fr.pharma.eclipse.comparator.produit.detail;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import fr.pharma.eclipse.comparator.produit.detail.helper.DetailStockageComparatorHelper;
import fr.pharma.eclipse.domain.model.produit.detail.DetailStockage;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.PharmacieUtils;
import fr.pharma.eclipse.utils.StockageUtils;

/**
 * Test de la classe DetailStockageComparator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DetailStockageComparatorTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private DetailStockageComparator comparator;

    /**
     * Helper mocké.
     */
    private DetailStockageComparatorHelper mockedHelper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedHelper = Mockito.mock(DetailStockageComparatorHelper.class);
        this.comparator = new DetailStockageComparator();
        Assert.assertNotNull(this.comparator.getHelper()); // helper initialisé
                                                           // par défaut
        this.comparator.setHelper(this.mockedHelper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.comparator = null;
        this.mockedHelper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.comparator);
        Assert.assertEquals(this.mockedHelper, this.comparator.getHelper());
    }

    /**
     * Test de la méthode
     * compare(fr.pharma.eclipse.domain.model.produit.detail.DetailStockage,
     * fr.pharma.eclipse.domain.model.produit.detail.DetailStockage).
     */
    @Test
    public void testCompareDiff1() {
        final String pharmacieKey1 = "230";
        final String stockageKey1 = "ArmoireA-ArmoireB";
        final String idStockageKey1 = "";
        final String expectedKey1 = "230;ArmoireA-ArmoireB;";
        final String pharmacieKey2 = "231";
        final String stockageKey2 = "ArmoireA-ArmoireB";
        final String idStockageKey2 = "idSto";
        final String expectedKey2 = "231;ArmoireA-ArmoireB;idSto";
        long id = 1;

        final Pharmacie pharmacie1 = PharmacieUtils.makePharmacieTest(id++);
        final Pharmacie pharmacie2 = PharmacieUtils.makePharmacieTest(id++);
        final Stockage stockage1 = StockageUtils.makeStockage(id++, "Armoire du fond");
        final Stockage stockage2 = StockageUtils.makeStockage(id++, "Armoire du fond");
        final String idStockage1 = "à droite de l'étagère";
        final String idStockage2 = "à droite de l'étagère";

        final DetailStockage detailStockage1 = new DetailStockage();
        detailStockage1.setId(id++);
        detailStockage1.setPharmacie(pharmacie1);
        detailStockage1.setStockage(stockage1);
        detailStockage1.setIdentifiantStockage(idStockage1);
        final DetailStockage detailStockage2 = new DetailStockage();
        detailStockage2.setId(id++);
        detailStockage2.setPharmacie(pharmacie2);
        detailStockage2.setStockage(stockage2);
        detailStockage2.setIdentifiantStockage(idStockage2);

        final Map<Object, String> mapPharma = new HashMap<Object, String>();
        mapPharma.put(pharmacie1, pharmacieKey1);
        mapPharma.put(pharmacie2, pharmacieKey2);
        final Map<Object, String> mapStockage = new HashMap<Object, String>();
        mapStockage.put(stockage1, stockageKey1);
        mapStockage.put(stockage2, stockageKey2);
        final Map<Object, String> mapIdstockage = new HashMap<Object, String>();
        mapIdstockage.put(idStockage1, idStockageKey1);
        mapIdstockage.put(idStockage2, idStockageKey2);

        final Answer<Object> answerPharma = new AnswerBuilder(mapPharma);
        final Answer<Object> answerStockage = new AnswerBuilder(mapStockage);
        final Answer<Object> answerIdStockage = new AnswerBuilder(mapIdstockage);
        Mockito.doAnswer(answerPharma).when(this.mockedHelper).appendPharmaciePart(Matchers.any(StringBuilder.class), Matchers.any(Pharmacie.class), Matchers.anyString());
        Mockito.doAnswer(answerStockage).when(this.mockedHelper).appendStockagePart(Matchers.any(StringBuilder.class), Matchers.any(Stockage.class), Matchers.anyString());
        Mockito.doAnswer(answerIdStockage).when(this.mockedHelper).appendIdStockagePart(Matchers.any(StringBuilder.class), Matchers.anyString(), Matchers.anyString());

        final int res = this.comparator.compare(detailStockage1, detailStockage2);
        Mockito.verify(this.mockedHelper, Mockito.times(2)).appendPharmaciePart(Matchers.any(StringBuilder.class), Matchers.any(Pharmacie.class), Matchers.anyString());
        Mockito.verify(this.mockedHelper, Mockito.times(2)).appendStockagePart(Matchers.any(StringBuilder.class), Matchers.any(Stockage.class), Matchers.anyString());
        Mockito.verify(this.mockedHelper, Mockito.times(2)).appendIdStockagePart(Matchers.any(StringBuilder.class), Matchers.anyString(), Matchers.anyString());
        Assert.assertEquals(expectedKey1.compareTo(expectedKey2), res);

    }

    /**
     * Test de la méthode
     * compare(fr.pharma.eclipse.domain.model.produit.detail.DetailStockage,
     * fr.pharma.eclipse.domain.model.produit.detail.DetailStockage).
     */
    @Test
    public void testCompareDiff2() {
        final String pharmacieKey1 = "230";
        final String stockageKey1 = "ArmoireA-ArmoireB";
        final String idStockageKey1 = "";
        final String expectedKey1 = "230;ArmoireA-ArmoireB;";
        final String pharmacieKey2 = "231";
        final String stockageKey2 = "ArmoireA-ArmoireB";
        final String idStockageKey2 = "idSto";
        final String expectedKey2 = "231;ArmoireA-ArmoireB;idSto";
        long id = 1;

        final Pharmacie pharmacie1 = PharmacieUtils.makePharmacieTest(id++);
        final Pharmacie pharmacie2 = PharmacieUtils.makePharmacieTest(id++);
        final Stockage stockage1 = StockageUtils.makeStockage(id++, "Armoire du fond");
        final Stockage stockage2 = StockageUtils.makeStockage(id++, "Armoire du fond");
        final String idStockage1 = "à droite de l'étagère";
        final String idStockage2 = "à droite de l'étagère";

        final DetailStockage detailStockage1 = new DetailStockage();
        detailStockage1.setId(id++);
        detailStockage1.setPharmacie(pharmacie1);
        detailStockage1.setStockage(stockage1);
        detailStockage1.setIdentifiantStockage(idStockage1);
        final DetailStockage detailStockage2 = new DetailStockage();
        detailStockage2.setId(id++);
        detailStockage2.setPharmacie(pharmacie2);
        detailStockage2.setStockage(stockage2);
        detailStockage2.setIdentifiantStockage(idStockage2);

        final Map<Object, String> mapPharma = new HashMap<Object, String>();
        mapPharma.put(pharmacie1, pharmacieKey1);
        mapPharma.put(pharmacie2, pharmacieKey2);
        final Map<Object, String> mapStockage = new HashMap<Object, String>();
        mapStockage.put(stockage1, stockageKey1);
        mapStockage.put(stockage2, stockageKey2);
        final Map<Object, String> mapIdstockage = new HashMap<Object, String>();
        mapIdstockage.put(idStockage1, idStockageKey1);
        mapIdstockage.put(idStockage2, idStockageKey2);

        final Answer<Object> answerPharma = new AnswerBuilder(mapPharma);
        final Answer<Object> answerStockage = new AnswerBuilder(mapStockage);
        final Answer<Object> answerIdStockage = new AnswerBuilder(mapIdstockage);
        Mockito.doAnswer(answerPharma).when(this.mockedHelper).appendPharmaciePart(Matchers.any(StringBuilder.class), Matchers.any(Pharmacie.class), Matchers.anyString());
        Mockito.doAnswer(answerStockage).when(this.mockedHelper).appendStockagePart(Matchers.any(StringBuilder.class), Matchers.any(Stockage.class), Matchers.anyString());
        Mockito.doAnswer(answerIdStockage).when(this.mockedHelper).appendIdStockagePart(Matchers.any(StringBuilder.class), Matchers.anyString(), Matchers.anyString());

        final int res = this.comparator.compare(detailStockage2, detailStockage1);
        Mockito.verify(this.mockedHelper, Mockito.times(2)).appendPharmaciePart(Matchers.any(StringBuilder.class), Matchers.any(Pharmacie.class), Matchers.anyString());
        Mockito.verify(this.mockedHelper, Mockito.times(2)).appendStockagePart(Matchers.any(StringBuilder.class), Matchers.any(Stockage.class), Matchers.anyString());
        Mockito.verify(this.mockedHelper, Mockito.times(2)).appendIdStockagePart(Matchers.any(StringBuilder.class), Matchers.anyString(), Matchers.anyString());
        Assert.assertEquals(expectedKey2.compareTo(expectedKey1), res);

    }

    /**
     * Test de la méthode
     * compare(fr.pharma.eclipse.domain.model.produit.detail.DetailStockage,
     * fr.pharma.eclipse.domain.model.produit.detail.DetailStockage).
     */
    @Test
    public void testCompareEquals() {
        final String pharmacieKey1 = "230";
        final String stockageKey1 = "ArmoireA-ArmoireB";
        final String idStockageKey1 = "";
        long id = 1;

        final Pharmacie pharmacie1 = PharmacieUtils.makePharmacieTest(id++);
        final Pharmacie pharmacie2 = PharmacieUtils.makePharmacieTest(id++);
        final Stockage stockage1 = StockageUtils.makeStockage(id++, "Armoire du fond");
        final Stockage stockage2 = StockageUtils.makeStockage(id++, "Armoire du fond");
        final String idStockage1 = "à droite de l'étagère";
        final String idStockage2 = "à droite de l'étagère";

        final DetailStockage detailStockage1 = new DetailStockage();
        detailStockage1.setId(id++);
        detailStockage1.setPharmacie(pharmacie1);
        detailStockage1.setStockage(stockage1);
        detailStockage1.setIdentifiantStockage(idStockage1);
        final DetailStockage detailStockage2 = new DetailStockage();
        detailStockage2.setId(id++);
        detailStockage2.setPharmacie(pharmacie2);
        detailStockage2.setStockage(stockage2);
        detailStockage2.setIdentifiantStockage(idStockage2);

        final Map<Object, String> mapPharma = new HashMap<Object, String>();
        mapPharma.put(pharmacie1, pharmacieKey1);
        mapPharma.put(pharmacie2, pharmacieKey1);
        final Map<Object, String> mapStockage = new HashMap<Object, String>();
        mapStockage.put(stockage1, stockageKey1);
        mapStockage.put(stockage2, stockageKey1);
        final Map<Object, String> mapIdstockage = new HashMap<Object, String>();
        mapIdstockage.put(idStockage1, idStockageKey1);
        mapIdstockage.put(idStockage2, idStockage1);

        final Answer<Object> answerPharma = new AnswerBuilder(mapPharma);
        final Answer<Object> answerStockage = new AnswerBuilder(mapStockage);
        final Answer<Object> answerIdStockage = new AnswerBuilder(mapIdstockage);
        Mockito.doAnswer(answerPharma).when(this.mockedHelper).appendPharmaciePart(Matchers.any(StringBuilder.class), Matchers.any(Pharmacie.class), Matchers.anyString());
        Mockito.doAnswer(answerStockage).when(this.mockedHelper).appendStockagePart(Matchers.any(StringBuilder.class), Matchers.any(Stockage.class), Matchers.anyString());
        Mockito.doAnswer(answerIdStockage).when(this.mockedHelper).appendIdStockagePart(Matchers.any(StringBuilder.class), Matchers.anyString(), Matchers.anyString());

        final int res = this.comparator.compare(detailStockage2, detailStockage1);
        Mockito.verify(this.mockedHelper, Mockito.times(2)).appendPharmaciePart(Matchers.any(StringBuilder.class), Matchers.any(Pharmacie.class), Matchers.anyString());
        Mockito.verify(this.mockedHelper, Mockito.times(2)).appendStockagePart(Matchers.any(StringBuilder.class), Matchers.any(Stockage.class), Matchers.anyString());
        Mockito.verify(this.mockedHelper, Mockito.times(2)).appendIdStockagePart(Matchers.any(StringBuilder.class), Matchers.anyString(), Matchers.anyString());
        Assert.assertEquals(0, res);

    }

    /**
     * Classe de réponse pour les mocks du helper.
     * @author Netapsys
     * @version $Revision$ $Date$
     */
    class AnswerBuilder implements Answer<Object> {
        /**
         * Map de couples expectedArg2/stringToAppend tels quel: <br>
         * - expectedArg2: Objet attendu comme deuxième paramètre. -
         * stringToAppend: Chaîne à ajouter au builder lors de l'appel.<br>
         */
        private final Map<Object, String> map;

        /**
         * Constructeur.
         * @param map Map de couples stringToAppend/expectedArg2 tels quel: <br>
         * - stringToAppend: Chaîne à ajouter au builder lors de l'appel.<br>
         * - expectedArg2: Objet attendu comme deuxième paramètre.
         */
        public AnswerBuilder(final Map<Object, String> map) {
            this.map = map;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Object answer(final InvocationOnMock invocation) throws Throwable {
            final StringBuilder builder = (StringBuilder) invocation.getArguments()[0];
            final Object arg2 = invocation.getArguments()[1];
            final String arg3 = (String) invocation.getArguments()[2];
            Assert.assertTrue(this.map.containsKey(arg2));
            Assert.assertEquals(StringUtils.EMPTY, arg3);

            builder.append(this.map.get(arg2));
            return null;
        }
    }
}
