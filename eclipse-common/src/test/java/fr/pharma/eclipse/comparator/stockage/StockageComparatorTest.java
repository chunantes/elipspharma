package fr.pharma.eclipse.comparator.stockage;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.stockage.Stockage;

/**
 * Classe en charge de tester le comparator de Stockage.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class StockageComparatorTest {
    /**
     * Comparator à tester.
     */
    private StockageComparator comparator;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.comparator = new StockageComparator();
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.comparator = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.comparator);
    }

    /**
     * Méthode en charge de tester la comparaison OK.
     */
    @Test
    public void testCompareEquals() {
        final Stockage parent = new Stockage();
        parent.setNom("parent");

        final Stockage enfant1 = new Stockage();
        enfant1.setNom("enfant1");
        enfant1.setParent(parent);

        final Stockage enfant2 = new Stockage();
        enfant2.setNom("enfant1");
        enfant2.setParent(parent);

        parent.getEnfants().add(enfant1);
        parent.getEnfants().add(enfant2);

        Assert.assertEquals(0, this.comparator.compare(enfant1, enfant2));
    }

    /**
     * Méthode en charge de tester la comparaison inférieure.
     */
    @Test
    public void testCompareInferieure() {
        final Stockage parent = new Stockage();
        parent.setNom("parent");

        final Stockage enfant1 = new Stockage();
        enfant1.setNom("enfant1");
        enfant1.setParent(parent);

        final Stockage enfant2 = new Stockage();
        enfant2.setNom("enfant2");
        enfant2.setParent(parent);

        parent.getEnfants().add(enfant1);
        parent.getEnfants().add(enfant2);

        Assert.assertTrue(this.comparator.compare(enfant1, enfant2) < 0);
    }

    /**
     * Méthode en charge de tester la comparaison supérieure.
     */
    @Test
    public void testCompareSuperieure() {
        final Stockage parent = new Stockage();
        parent.setNom("parent");

        final Stockage enfant1 = new Stockage();
        enfant1.setNom("enfant1");
        enfant1.setParent(parent);

        final Stockage enfant2 = new Stockage();
        enfant2.setNom("enfant2");
        enfant2.setParent(parent);

        parent.getEnfants().add(enfant1);
        parent.getEnfants().add(enfant2);

        Assert.assertTrue(this.comparator.compare(enfant2, enfant1) > 0);
    }

    /**
     * Méthode en charge de tester l'ajout de nom de stockage sur les parents.
     */
    @Test
    public void testAddNomStockage() {
        final Stockage parent = new Stockage();
        parent.setNom("parent");

        final Stockage enfant1 = new Stockage();
        enfant1.setNom("enfant1");
        enfant1.setParent(parent);

        final Stockage enfant11 = new Stockage();
        enfant11.setNom("enfant11");
        enfant11.setParent(enfant1);
        enfant1.getEnfants().add(enfant11);

        final Stockage enfant2 = new Stockage();
        enfant2.setNom("enfant2");
        enfant2.setParent(parent);

        parent.getEnfants().add(enfant1);
        parent.getEnfants().add(enfant2);

        final String resultParent = this.comparator.addNomStockage(parent, StringUtils.EMPTY);
        Assert.assertEquals("", resultParent);

        final String resultEnfant1 = this.comparator.addNomStockage(enfant1, StringUtils.EMPTY);
        Assert.assertEquals("parent", resultEnfant1);

        final String resultEnfant2 = this.comparator.addNomStockage(enfant2, StringUtils.EMPTY);
        Assert.assertEquals("parent", resultEnfant2);

        final String resultEnfant11 = this.comparator.addNomStockage(enfant11, StringUtils.EMPTY);
        Assert.assertEquals("parentenfant1", resultEnfant11);
    }

}
