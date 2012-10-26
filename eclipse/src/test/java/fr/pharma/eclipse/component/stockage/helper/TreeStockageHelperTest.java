package fr.pharma.eclipse.component.stockage.helper;

import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.primefaces.model.TreeNode;

import fr.pharma.eclipse.comparator.stockage.StockageComparator;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;

/**
 * Classe en charge de tester le helper d'arbre de stockage.
 
 * @version $Revision$ $Date$
 */
public class TreeStockageHelperTest
{
    /**
     * TreeStockageHelper à tester.
     */
    private TreeStockageHelper helper;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init()
    {
        this.helper = new TreeStockageHelper();
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end()
    {
        this.helper = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.helper);
    }

    /**
     * Méthode en charge de tester la méthode de décomposition de noeud.
     */
    @Test
    public void testDecomposeNodesNotEmpty()
    {
        final String result = this.helper.decomposeNodes("0_1_2");
        Assert.assertEquals("treeStockages_0_1_2,treeStockages_0_1,treeStockages_0",
                            result);
    }

    /**
     * Méthode en charge de tester la méthode de décomposition de noeud.
     */
    @Test
    public void testDecomposeNodesEmpty()
    {
        final String result = this.helper.decomposeNodes(StringUtils.EMPTY);
        Assert.assertEquals(StringUtils.EMPTY,
                            result);
    }

    /**
     * Méthode en charge de tester la méthode de construction d'arbre.
     */
    @Test
    public void testBuildTreeWithStockagesEmpty()
    {
        final Pharmacie pharmacie = new Pharmacie();
        final TreeNode tree = this.helper.buildTree(pharmacie);
        Assert.assertNotNull(tree);
    }

    /**
     * Méthode en charge de tester la méthode de construction d'arbre.
     */
    @Test
    public void testBuildTreeWithStockagesNotEmpty()
    {
        final Pharmacie pharmacie = new Pharmacie();
        final SortedSet<Stockage> stockages = new TreeSet<Stockage>(new StockageComparator());

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

        stockages.add(parent);
        stockages.add(enfant1);
        stockages.add(enfant2);

        pharmacie.setStockages(stockages);

        final TreeNode tree = this.helper.buildTree(pharmacie);
        Assert.assertNotNull(tree);
    }

    /**
     * Méthode en charge de tester la méthode de construction d'arbre.
     */
    @Test
    public void testBuildTreeWithStockagesNotEmptyNLevel()
    {
        final Pharmacie pharmacie = new Pharmacie();
        final SortedSet<Stockage> stockages = new TreeSet<Stockage>(new StockageComparator());

        final Stockage parent = new Stockage();
        parent.setNom("parent");
        final Stockage enfant1 = new Stockage();
        enfant1.setNom("enfant1");
        enfant1.setParent(parent);
        final Stockage enfant2 = new Stockage();
        enfant2.setNom("enfant2");
        enfant2.setParent(parent);
        final Stockage enfant11 = new Stockage();
        enfant11.setNom("enfant11");
        enfant11.setParent(enfant1);

        enfant1.getEnfants().add(enfant11);
        parent.getEnfants().add(enfant1);
        parent.getEnfants().add(enfant2);

        stockages.add(parent);
        stockages.add(enfant1);
        stockages.add(enfant2);
        stockages.add(enfant11);

        pharmacie.setStockages(stockages);

        final TreeNode tree = this.helper.buildTree(pharmacie);
        Assert.assertNotNull(tree);
    }

    /**
     * Méthode en charge de tester le calcul des noeuds à ouvrir à l'affichage.
     */
    @Test
    public void testCalculateNodesToExpand1()
    {
        final Pharmacie pharmacie = new Pharmacie();
        final SortedSet<Stockage> stockages = new TreeSet<Stockage>(new StockageComparator());

        final Stockage parent = new Stockage();
        parent.setNom("parent");
        final Stockage enfant1 = new Stockage();
        enfant1.setNom("enfant1");
        enfant1.setParent(parent);
        final Stockage enfant2 = new Stockage();
        enfant2.setNom("enfant2");
        enfant2.setParent(parent);
        final Stockage enfant11 = new Stockage();
        enfant11.setNom("enfant11");
        enfant11.setParent(enfant1);

        enfant1.getEnfants().add(enfant11);
        parent.getEnfants().add(enfant1);
        parent.getEnfants().add(enfant2);

        stockages.add(parent);
        stockages.add(enfant1);
        stockages.add(enfant2);
        stockages.add(enfant11);

        pharmacie.setStockages(stockages);
        final TreeNode tree = this.helper.buildTree(pharmacie);

        final String nodesToExpand = this.helper.calculateNodesToExpand(tree,
                                                                        enfant11);
        Assert.assertEquals("treeStockages_0_0,treeStockages_0",
                            nodesToExpand);
    }

    /**
     * Méthode en charge de tester le calcul des noeuds à ouvrir à l'affichage.
     */
    @Test
    public void testCalculateNodesToExpand2()
    {
        final Pharmacie pharmacie = new Pharmacie();
        final SortedSet<Stockage> stockages = new TreeSet<Stockage>(new StockageComparator());

        final Stockage parent = new Stockage();
        parent.setNom("parent");
        final Stockage enfant1 = new Stockage();
        enfant1.setNom("enfant1");
        enfant1.setParent(parent);
        final Stockage enfant2 = new Stockage();
        enfant2.setNom("enfant2");
        enfant2.setParent(parent);
        final Stockage enfant11 = new Stockage();
        enfant11.setNom("enfant11");
        enfant11.setParent(enfant1);

        enfant1.getEnfants().add(enfant11);
        parent.getEnfants().add(enfant1);
        parent.getEnfants().add(enfant2);

        stockages.add(parent);
        stockages.add(enfant1);
        stockages.add(enfant2);
        stockages.add(enfant11);

        pharmacie.setStockages(stockages);
        final TreeNode tree = this.helper.buildTree(pharmacie);

        final String nodesToExpand = this.helper.calculateNodesToExpand(tree,
                                                                        enfant2);
        Assert.assertEquals("treeStockages_0",
                            nodesToExpand);
    }

    /**
     * Méthode en charge de tester le calcul des noeuds à ouvrir à l'affichage.
     */
    @Test
    public void testCalculateNodesToExpand3()
    {
        final Pharmacie pharmacie = new Pharmacie();
        final SortedSet<Stockage> stockages = new TreeSet<Stockage>(new StockageComparator());

        final Stockage parent = new Stockage();
        parent.setNom("parent");
        final Stockage enfant1 = new Stockage();
        enfant1.setNom("enfant1");
        enfant1.setParent(parent);
        final Stockage enfant11 = new Stockage();
        enfant11.setNom("enfant11");
        enfant11.setParent(enfant1);
        final Stockage enfant2 = new Stockage();
        enfant2.setNom("enfant2");
        enfant2.setParent(parent);
        final Stockage enfant21 = new Stockage();
        enfant21.setNom("enfant21");
        enfant21.setParent(enfant2);

        enfant1.getEnfants().add(enfant11);
        enfant2.getEnfants().add(enfant21);
        parent.getEnfants().add(enfant1);
        parent.getEnfants().add(enfant2);

        stockages.add(parent);
        stockages.add(enfant1);
        stockages.add(enfant2);
        stockages.add(enfant11);
        stockages.add(enfant21);

        pharmacie.setStockages(stockages);
        final TreeNode tree = this.helper.buildTree(pharmacie);

        final String nodesToExpand = this.helper.calculateNodesToExpand(tree,
                                                                        enfant2);
        Assert.assertEquals("treeStockages_0_1,treeStockages_0",
                            nodesToExpand);
    }

}
